package org.example.schoolapi.school.mapper;

import org.example.schoolapi.domain.School;
import org.example.schoolapi.school.dto.in.SchoolRequest;
import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {

    public School map(SchoolRequest schoolDTO){
        return School.builder()
                .name(schoolDTO.getName())
                .hourPrice(schoolDTO.getHourPrice())
                .build();
    }
}
