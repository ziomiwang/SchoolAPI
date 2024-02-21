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

    public List<Attendance> findAllByChildId(final Long childId){
        return attendanceRepository.findAllByChildId(childId);
    }
}
