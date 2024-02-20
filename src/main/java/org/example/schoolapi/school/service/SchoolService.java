package org.example.schoolapi.school.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.schoolapi.domain.School;
import org.example.schoolapi.school.dto.in.SchoolDTO;
import org.example.schoolapi.school.mapper.SchoolMapper;
import org.example.schoolapi.school.repository.SchoolRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    public void createSchool(SchoolDTO schoolDTO) {
        schoolRepository.save(schoolMapper.map(schoolDTO));
    }

    public School getSchoolById(final Long schoolId){
        return schoolRepository.findById(schoolId)
                .orElseThrow();
    }
}
