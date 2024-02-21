package org.example.schoolapi.settlement.dto.in;

import lombok.Data;

import java.time.YearMonth;

@Data
public class SettlementParentRequest {

    private Long parentId;
    private YearMonth month;
}
