package org.example.schoolapi.web;

import lombok.RequiredArgsConstructor;
import org.example.schoolapi.attendance.dto.in.AttendanceRequest;
import org.example.schoolapi.attendance.service.AttendanceService;
import org.example.schoolapi.child.dto.in.ChildRequest;
import org.example.schoolapi.child.service.ChildService;
import org.example.schoolapi.parent.dto.in.ParentRequest;
import org.example.schoolapi.parent.service.ParentService;
import org.example.schoolapi.school.dto.in.SchoolRequest;
import org.example.schoolapi.school.service.SchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DomainController {
    private final SchoolService schoolService;
    private final ChildService childService;
    private final ParentService parentService;
    private final AttendanceService attendanceService;

    @PostMapping("/school")
    public ResponseEntity<String> createSchool(@RequestBody final SchoolRequest schoolDTO){
        schoolService.createSchool(schoolDTO);
        return ResponseEntity.noContent()
                .build();
    }

    @PostMapping("/child")
    public ResponseEntity<String> createChild(@RequestBody final ChildRequest childDTO){
        childService.createChild(childDTO);
        return ResponseEntity.noContent()
                .build();
    }

    @PostMapping("/parent")
    public ResponseEntity<String> createParent(@RequestBody final ParentRequest parentDTO){
        parentService.createParent(parentDTO);
        return ResponseEntity.noContent()
                .build();
    }

    @PostMapping("/attendance")
    public ResponseEntity<String> createAttendance(@RequestBody final AttendanceRequest attendanceDTO){
        attendanceService.createAttendance(attendanceDTO);
        return ResponseEntity.noContent()
                .build();
    }
}
