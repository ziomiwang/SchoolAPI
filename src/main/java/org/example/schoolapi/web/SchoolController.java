package org.example.schoolapi.web;

import lombok.RequiredArgsConstructor;
import org.example.schoolapi.settlement.dto.in.SettlementParentRequest;
import org.example.schoolapi.settlement.dto.in.SettlementSchoolRequest;
import org.example.schoolapi.settlement.dto.out.SettlementResponse;
import org.example.schoolapi.settlement.service.SettlementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/settlements")
public class SchoolController {

    private final SettlementService settlementService;

    @PostMapping("/school")
    public ResponseEntity<SettlementResponse> settleSchoolFees(@RequestBody final SettlementSchoolRequest settlementRequest) {

        return ResponseEntity.ok(null);
    }

    @PostMapping("/parent")
    public ResponseEntity<SettlementResponse> settleParentFees(@RequestBody final SettlementParentRequest settlementRequest) {

        return ResponseEntity.ok(null);
    }
}
