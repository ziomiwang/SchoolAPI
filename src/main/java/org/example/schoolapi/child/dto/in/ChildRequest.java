package org.example.schoolapi.child.dto.in;

import lombok.Data;

@Data
public class ChildRequest {

    private String firstName;
    private String lastName;
    private Long parentId;
    private Long schoolId;
}
