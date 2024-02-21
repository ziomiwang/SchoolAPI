package org.example.schoolapi.child.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.schoolapi.child.dto.in.ChildRequest;
import org.example.schoolapi.child.mapper.ChildMapper;
import org.example.schoolapi.child.repository.ChildRepository;
import org.example.schoolapi.domain.Child;
import org.example.schoolapi.domain.Parent;
import org.example.schoolapi.domain.School;
import org.example.schoolapi.parent.service.ParentService;
import org.example.schoolapi.school.service.SchoolService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChildService {

    private final ChildRepository childRepository;
    private final SchoolService schoolService;
    private final ParentService parentService;
    private final ChildMapper childMapper;

    public void createChild(final ChildRequest childDTO){
        Parent parentById = parentService.getParentById(childDTO.getParentId());
        School schoolById = schoolService.getSchoolById(childDTO.getParentId());

        Child child = childMapper.map(childDTO);

        child.setSchool(schoolById);
        child.setParent(parentById);

        childRepository.save(child);
    }

    public Child findChildById(final Long childId){
        return childRepository.findById(childId)
                .orElseThrow();
    }

    public List<Child> findAllByParentId(final Long parentId) {
        return childRepository.findAllByParentId(parentId);
    }

    public List<Child> findAllBySchoolId(final Long schoolId){
        return childRepository.findAllBySchoolId(schoolId);
    }
}
