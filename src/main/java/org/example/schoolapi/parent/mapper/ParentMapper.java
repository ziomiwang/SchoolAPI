package org.example.schoolapi.parent.mapper;

import org.example.schoolapi.domain.Parent;
import org.example.schoolapi.parent.dto.in.ParentDTO;
import org.springframework.stereotype.Service;

@Service
public class ParentMapper {

    public Parent map(ParentDTO parentDTO){
        return Parent.builder()
                .firstName(parentDTO.getFirstName())
                .lastName(parentDTO.getLastName())
                .build();
    }
}
