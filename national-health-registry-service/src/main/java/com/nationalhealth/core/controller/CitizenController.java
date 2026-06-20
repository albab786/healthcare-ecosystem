package com.nationalhealth.core.controller;

import com.nationalhealth.core.dto.CitizenRequestDto;
import com.nationalhealth.core.entity.Citizen;
import com.nationalhealth.core.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patients")
public class CitizenController {

    @Autowired
    private CitizenService citizenService;

    // 1. POST Endpoint - Registers a new citizen
    @PostMapping("/register")
    public ResponseEntity<?> registerCitizen(@RequestBody CitizenRequestDto requestDto) {
        try {
            Citizen savedCitizen = citizenService.registerCitizen(requestDto);
            return new ResponseEntity<>(savedCitizen, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 2. GET Endpoint - Looks up profile by Aadhaar number (Called by OpenFeign)
    @GetMapping("/{aadharCardNo}")
    public ResponseEntity<Citizen> getCitizenByAadhar(@PathVariable String aadharCardNo) {
        return citizenService.getCitizenByAadhar(aadharCardNo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}