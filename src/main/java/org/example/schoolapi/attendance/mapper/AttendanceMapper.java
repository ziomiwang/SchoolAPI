package org.example.schoolapi.attendance.mapper;

import org.example.schoolapi.attendance.dto.in.AttendanceDTO;
import org.example.schoolapi.domain.Attendance;
import org.springframework.stereotype.Service;

@Service
public class AttendanceMapper {

    public Attendance map(final AttendanceDTO attendanceDTO) {
        return Attendance.builder()
                .exitDate(attendanceDTO.getExitDate())
                .entryDate(attendanceDTO.getEntryDate())
                .build();
    }
}
