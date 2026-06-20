package com.gov.health.insurance.controller;

import com.gov.health.insurance.client.RegistryServiceClient;
import com.gov.health.insurance.dto.CitizenRegistryProfileDto;
import com.gov.health.insurance.entity.CitizenPolicy;
import com.gov.health.insurance.service.AutomaticEligibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/insurance")
public class InsuranceEligibilityController {

    @Autowired
    private RegistryServiceClient registryClient; // Your OpenFeign Client

    @Autowired
    private AutomaticEligibilityService eligibilityService;

    @PostMapping("/check-and-enroll/{aadharCardNo}")
    public ResponseEntity<CitizenPolicy> checkAndEnroll(@PathVariable String aadharCardNo) {
        // 1. Fetch live citizen details from the registry microservice via OpenFeign
        CitizenRegistryProfileDto citizenProfile = registryClient.getCitizenByAadhar(aadharCardNo);
        
        // 2. Pass to the rule engine to verify eligibility and automatically save if approved
        CitizenPolicy finalPolicy = eligibilityService.processAutoEnrollment(citizenProfile);
        
        return ResponseEntity.ok(finalPolicy);
    }
}