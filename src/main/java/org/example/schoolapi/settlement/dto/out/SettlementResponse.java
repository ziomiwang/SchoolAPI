package org.example.schoolapi.settlement.dto.out;

import lombok.Builder;
import lombok.Data;

import org.example.schoolapi.parent.dto.out.ParentDTO;

import java.util.List;

@Data
@Builder
public class SettlementResponse {

    private List<ParentDTO> parents;
    private Double totalFees;
    private List<ChildFee> feePerChild;
}
