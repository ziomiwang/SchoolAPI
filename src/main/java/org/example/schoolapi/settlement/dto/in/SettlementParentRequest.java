package org.example.schoolapi.settlement.dto.in;

import lombok.Builder;
import lombok.Data;

import java.time.YearMonth;

@Data
@Builder
public class SettlementParentRequest {

    private Long parentId;
    private YearMonth month;
}
