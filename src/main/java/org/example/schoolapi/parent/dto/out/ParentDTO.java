package org.example.schoolapi.parent.dto.out;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParentDTO {

    private String firstName;
    private String lastName;
}
