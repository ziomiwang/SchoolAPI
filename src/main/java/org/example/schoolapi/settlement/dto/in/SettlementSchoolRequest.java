package org.example.schoolapi.settlement.dto.in;

import lombok.Data;

import java.time.YearMonth;

@Data
public class SettlementSchoolRequest {


    private Long schoolId;
    private YearMonth month;
}
