package org.example.schoolapi.school.mapper;

import org.example.schoolapi.domain.School;
import org.example.schoolapi.school.dto.in.SchoolDTO;
import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {

    public School map(SchoolDTO schoolDTO){
        return School.builder()
                .name(schoolDTO.getName())
                .hourPrice(schoolDTO.getHourPrice())
                .build();
    }
}
