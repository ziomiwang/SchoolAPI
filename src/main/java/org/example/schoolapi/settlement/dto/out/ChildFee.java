package org.example.schoolapi.settlement.dto.out;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChildFee {

    private ChildData childData;
    private Double fee;
    private Double totalTimeSpent;
}
