package org.example.schoolapi.attendance.dto.in;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttendanceRequest {

    private Long childId;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
}
