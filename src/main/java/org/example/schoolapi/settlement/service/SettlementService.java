package org.example.schoolapi.settlement.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Function;

import org.example.schoolapi.attendance.service.AttendanceService;
import org.example.schoolapi.child.service.ChildService;
import org.example.schoolapi.domain.Attendance;
import org.example.schoolapi.domain.Child;
import org.example.schoolapi.domain.Parent;
import org.example.schoolapi.domain.School;
import org.example.schoolapi.parent.dto.out.ParentDTO;
import org.example.schoolapi.parent.service.ParentService;
import org.example.schoolapi.school.service.SchoolService;
import org.example.schoolapi.settlement.dto.in.SettlementParentRequest;
import org.example.schoolapi.settlement.dto.in.SettlementSchoolRequest;
import org.example.schoolapi.settlement.dto.out.ChildData;
import org.example.schoolapi.settlement.dto.out.ChildFee;
import org.example.schoolapi.settlement.dto.out.SettlementResponse;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SettlementService {

    private final LocalTime FREE_HOURS_START = LocalTime.of(7, 0);
    private final LocalTime FREE_HOURS_END = LocalTime.of(12, 0);

    private final SchoolService schoolService;
    private final AttendanceService attendanceService;
    private final ChildService childService;
    private final ParentService parentService;

    public SettlementResponse calculateSchoolSettlement(SettlementSchoolRequest settlementSchoolRequest) {
        School schoolById = schoolService.getSchoolById(settlementSchoolRequest.getSchoolId());
        final List<Parent> allParentsBySchool = parentService.findAllParentsBySchool(settlementSchoolRequest.getSchoolId());
        List<Child> allBySchoolId = childService.findAllBySchoolId(settlementSchoolRequest.getSchoolId());
        List<ChildFee> childFeeList = calculateChildFee(allBySchoolId, schoolById.getHourPrice(), settlementSchoolRequest.getMonth()
                .getMonth());
        Double totalFees = childFeeList.stream()
                .map(ChildFee::getFee)
                .mapToDouble(Double::doubleValue)
                .sum();

        return SettlementResponse.builder()
                .parents(allParentsBySchool.stream()
                        .map(parent -> ParentDTO.builder()
                                .firstName(parent.getFirstName())
                                .lastName(parent.getLastName())
                                .build())
                        .toList())
                .totalFees(totalFees)
                .feePerChild(childFeeList)
                .build();
    }

    public SettlementResponse calculateParentSettlement(SettlementParentRequest settlementParentRequest) {
        final Parent parentById = parentService.getParentById(settlementParentRequest.getParentId());
        final List<Child> children = childService.findAllByParentId(settlementParentRequest.getParentId());

        final List<ChildFee> childFees = calculateChildFee(children, parentById.getSchool()
                .getHourPrice(), settlementParentRequest.getMonth()
                .getMonth());

        Double totalFees = childFees.stream()
                .map(ChildFee::getFee)
                .mapToDouble(Double::doubleValue)
                .sum();

        return SettlementResponse.builder()
                .parents(List.of(
                        ParentDTO.builder()
                                .firstName(parentById.getFirstName())
                                .lastName(parentById.getLastName())
                                .build()
                ))
                .totalFees(totalFees)
                .feePerChild(childFees)
                .build();
    }

    private List<ChildFee> calculateChildFee(List<Child> children, double hourPrice, final Month month) {

        return children.stream()
                .map(child -> {
                    List<Attendance> allByChildId = attendanceService.findAllByChildIdAndMonth(child.getId(), month);
                    Double totalFee = calculateTotalFee(allByChildId, hourPrice);

                    return ChildFee.builder()
                            .fee(totalFee)
                            .childData(ChildData.builder()
                                    .firstName(child.getFirstName())
                                    .lastName(child.getLastName())
                                    .build())
                            .build();
                })
                .toList();
    }

    private Double calculateTotalFee(List<Attendance> attendances, double hourPrice) {
        double totalFee = 0.0;

        for (Attendance attendance : attendances) {
            long totalBillableHours = calculateBillableHours(attendance.getEntryDate(), attendance.getExitDate());
            totalFee += totalBillableHours * hourPrice;
        }
        return totalFee;
    }

    private long calculateBillableHours(LocalDateTime entryDate, LocalDateTime exitDate) {
        Duration before = Duration.of(0, ChronoUnit.MINUTES);
        Duration after = Duration.of(0, ChronoUnit.MINUTES);
        int numberOfMinutesInHour = 60;
        long additionalHour = 1L;

        if (entryDate.toLocalTime()
                .isBefore(FREE_HOURS_START)) {
            before = Duration.between(entryDate.toLocalTime(), FREE_HOURS_START);
        }

        if (exitDate.toLocalTime()
                .isAfter(FREE_HOURS_END)) {
            after = Duration.between(FREE_HOURS_END, exitDate.toLocalTime());
        }

        Function<Long, Long> checkOurs = minutes -> minutes / numberOfMinutesInHour + (minutes % numberOfMinutesInHour == 0 ? 0 : additionalHour);

        return checkOurs.apply(before.toMinutes()) + checkOurs.apply(after.toMinutes());
    }
}
