package org.example.schoolapi.child.mapper;

import org.example.schoolapi.child.dto.in.ChildDTO;
import org.example.schoolapi.domain.Child;
import org.springframework.stereotype.Service;

@Service
public class ChildMapper {

    public Child map(ChildDTO childDTO){
        return Child.builder()
                .firstName(childDTO.getFirstName())
                .lastName(childDTO.getLastName())
                .build();
    }
}
