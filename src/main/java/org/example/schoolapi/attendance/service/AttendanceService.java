package org.example.schoolapi.attendance.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.schoolapi.attendance.dto.in.AttendanceRequest;
import org.example.schoolapi.attendance.mapper.AttendanceMapper;
import org.example.schoolapi.attendance.repository.AttendanceRepository;
import org.example.schoolapi.child.service.ChildService;
import org.example.schoolapi.domain.Attendance;
import org.example.schoolapi.domain.Child;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final ChildService childService;
    private final AttendanceMapper attendanceMapper;

    public void createAttendance(final AttendanceRequest attendanceDTO) {
        Child childById = childService.findChildById(attendanceDTO.getChildId());
        Attendance attendance = attendanceMapper.map(attendanceDTO);

        attendance.setChild(childById);

        attendanceRepository.save(attendance);
    }

    public List<Attendance> findAllByChildIdAndMonth(final Long childId, final Month month){
        final LocalDateTime start = LocalDateTime.of(LocalDate.now()
                .withDayOfMonth(1)
                .withMonth(month.getValue()), LocalTime.of(0, 0));
        final LocalDateTime end = LocalDateTime.of(LocalDate.now()
                .withDayOfMonth(month.maxLength())
                .withMonth(month.getValue()), LocalTime.of(23, 59));

        return attendanceRepository.findAllByEntryDateAfterAndExitDateBeforeAndChild_Id(start, end, childId);
    }
}
