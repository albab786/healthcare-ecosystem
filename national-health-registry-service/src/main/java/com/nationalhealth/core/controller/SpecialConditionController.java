package com.nationalhealth.core.controller;

import com.nationalhealth.core.dto.SpecialConditionRequestDto;
import com.nationalhealth.core.entity.SpecialCondition;
import com.nationalhealth.core.service.SpecialConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/special-conditions")
public class SpecialConditionController {

    @Autowired
    private SpecialConditionService specialConditionService;

    // 1. POST Endpoint - Logs a condition/disability parameter
    @PostMapping("/add")
    public ResponseEntity<?> addCondition(@RequestBody SpecialConditionRequestDto requestDto) {
        try {
            SpecialCondition savedCondition = specialConditionService.addSpecialCondition(requestDto);
            return new ResponseEntity<>(savedCondition, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 2. GET Endpoint - Fetches all health conditions logged under an Aadhaar number
    @GetMapping("/citizen/{aadharCardNo}")
    public ResponseEntity<List<SpecialCondition>> getCitizenConditions(@PathVariable String aadharCardNo) {
        List<SpecialCondition> conditions = specialConditionService.getConditionsByAadhar(aadharCardNo);
        return ResponseEntity.ok(conditions);
    }
}