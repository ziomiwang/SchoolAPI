package org.example.schoolapi.parent.service;

import java.util.List;

import org.example.schoolapi.domain.Parent;
import org.example.schoolapi.parent.dto.in.ParentRequest;
import org.example.schoolapi.parent.mapper.ParentMapper;
import org.example.schoolapi.parent.repository.ParentRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParentService {

    private final ParentRepository parentRepository;
    private final ParentMapper parentMapper;

    public void createParent(ParentRequest parentDTO) {
        parentRepository.save(parentMapper.map(parentDTO));
    }

    public Parent getParentById(final Long parentId) {
        return parentRepository.findById(parentId)
                .orElseThrow();
    }

    public List<Parent> findAllParentsBySchool(final long schoolId) {
        return parentRepository.findAllBySchool_Id(schoolId);
    }
}
