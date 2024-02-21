package org.example.schoolapi.attendance.mapper;

import org.example.schoolapi.attendance.dto.in.AttendanceRequest;
import org.example.schoolapi.domain.Attendance;
import org.springframework.stereotype.Service;

@Service
public class AttendanceMapper {

    public Attendance map(final AttendanceRequest attendanceDTO) {
        return Attendance.builder()
                .exitDate(attendanceDTO.getExitDate())
                .entryDate(attendanceDTO.getEntryDate())
                .build();
    }
}
