package org.example.schoolapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

import org.example.schoolapi.attendance.service.AttendanceService;
import org.example.schoolapi.child.service.ChildService;
import org.example.schoolapi.domain.Attendance;
import org.example.schoolapi.domain.Child;
import org.example.schoolapi.domain.Parent;
import org.example.schoolapi.domain.School;
import org.example.schoolapi.parent.service.ParentService;
import org.example.schoolapi.school.service.SchoolService;
import org.example.schoolapi.settlement.dto.in.SettlementParentRequest;
import org.example.schoolapi.settlement.dto.in.SettlementSchoolRequest;
import org.example.schoolapi.settlement.dto.out.SettlementResponse;
import org.example.schoolapi.settlement.service.SettlementService;
import org.example.schoolapi.web.SchoolController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SchoolControllerTest {

    private SchoolService schoolService = mock(SchoolService.class);
    private AttendanceService attendanceService = mock(AttendanceService.class);
    private ChildService childService = mock(ChildService.class);
    private ParentService parentService = mock(ParentService.class);
    private SettlementService settlementService = new SettlementService(schoolService, attendanceService, childService, parentService);

    private final SchoolController schoolController = new SchoolController(settlementService);

    @BeforeEach
    void setup() {
        initMocksForParent();
        initMocksForSchool();
    }

    @Test
    void settleParentFees() {
        final SettlementParentRequest request = SettlementParentRequest.builder()
                .parentId(1L)
                .month(YearMonth.of(2024, 10))
                .build();

        final ResponseEntity<SettlementResponse> settlementResponseResponseEntity = schoolController.settleParentFees(request);

        assertThat(settlementResponseResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        final SettlementResponse body = settlementResponseResponseEntity.getBody();
        assertThat(body.getTotalFees()).isEqualTo(2026.44);
        assertThat(body.getParents()).hasSize(1);
        assertThat(body.getFeePerChild()).hasSize(3);
    }

    @Test
    void settleSchoolFees() {
        final SettlementSchoolRequest request = SettlementSchoolRequest.builder()
                .schoolId(1L)
                .month(YearMonth.of(2024, 10))
                .build();

        final ResponseEntity<SettlementResponse> settlementResponseResponseEntity = schoolController.settleSchoolFees(request);

        assertThat(settlementResponseResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        final SettlementResponse body = settlementResponseResponseEntity.getBody();
        assertThat(body.getTotalFees()).isEqualTo(3212.04);
        assertThat(body.getParents()).hasSize(2);
        assertThat(body.getFeePerChild()).hasSize(3);
    }

    private void initMocksForParent() {
        when(parentService.getParentById(anyLong()))
                .thenReturn(Parent.builder()
                        .firstName("David")
                        .school(School.builder()
                                .hourPrice(12.99)
                                .name("School")
                                .build())
                        .build());
    }

    private void initMocksForSchool() {
        when(schoolService.getSchoolById(any()))
                .thenReturn(School.builder()
                        .name("School 1")
                        .hourPrice(20.59)
                        .build());

        when(attendanceService.findAllByChildIdAndMonth(any(), any()))
                .thenReturn(List.of(Attendance.builder()
                                .entryDate(LocalDateTime.of(2024, 10, 2, 6, 59))
                                .exitDate(LocalDateTime.of(2024, 10, 2, 23, 59))
                                .build(),
                        Attendance.builder()
                                .entryDate(LocalDateTime.of(2024, 10, 3, 6, 59))
                                .exitDate(LocalDateTime.of(2024, 10, 3, 23, 59))
                                .build(),
                        Attendance.builder()
                                .entryDate(LocalDateTime.of(2024, 10, 4, 6, 59))
                                .exitDate(LocalDateTime.of(2024, 10, 4, 23, 59))
                                .build(),
                        Attendance.builder()
                                .entryDate(LocalDateTime.of(2024, 11, 2, 6, 59))
                                .exitDate(LocalDateTime.of(2024, 11, 2, 23, 59))
                                .build()));

        when(childService.findAllBySchoolId(any()))
                .thenReturn(List.of(
                        Child.builder()
                                .firstName("David")
                                .build(),
                        Child.builder()
                                .firstName("Rob")
                                .build(),
                        Child.builder()
                                .firstName("Hubert")
                                .build()

                ));

        when(childService.findAllByParentId(any()))
                .thenReturn(List.of(
                        Child.builder()
                                .firstName("David")
                                .build(),
                        Child.builder()
                                .firstName("Rob")
                                .build(),
                        Child.builder()
                                .firstName("Hubert")
                                .build()

                ));

        when(parentService.findAllParentsBySchool(anyLong()))
                .thenReturn(List.of(
                        Parent.builder()
                                .firstName("David")
                                .build(),
                        Parent.builder()
                                .firstName("John")
                                .build()
                ));
    }
}

