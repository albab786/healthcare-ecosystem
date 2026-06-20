package com.gov.health.insurance.client;

import com.gov.health.insurance.dto.CitizenRegistryProfileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "national-health-registry-service", 
    url = "${app.registry-service-url:http://localhost:8081}"
)
public interface RegistryServiceClient {

    // Calls the endpoint exposed by national-health-registry-service
    @GetMapping("/api/v1/patients/{aadharCardNo}")
    CitizenRegistryProfileDto getCitizenByAadhar(@PathVariable("aadharCardNo") String aadharCardNo);
}