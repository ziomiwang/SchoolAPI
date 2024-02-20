package org.example.schoolapi.child.dto.in;

import lombok.Data;

@Data
public class ChildDTO {

    private String firstName;
    private String lastName;
    private Long parentId;
    private Long schoolId;
}
