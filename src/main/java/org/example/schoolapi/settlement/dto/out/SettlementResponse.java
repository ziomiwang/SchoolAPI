package org.example.schoolapi.settlement.dto.out;

import lombok.Builder;
import org.example.schoolapi.parent.dto.out.ParentDTO;

import java.util.List;

@Builder
public class SettlementResponse {

    private List<ParentDTO> parents;
    private Double totalFees;
    private List<ChildFee> feePerChild;
}
