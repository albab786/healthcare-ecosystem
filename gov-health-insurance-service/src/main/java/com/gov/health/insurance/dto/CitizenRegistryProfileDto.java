package com.gov.health.insurance.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class CitizenRegistryProfileDto {
    private String aadharCardNo;
    private String fullName;
    private LocalDate dateOfBirth;
    private String community;
    private String region;
    private List<SpecialConditionDto> specialConditions;

    @Data
    public static class SpecialConditionDto {
        private String conditionCategory; // e.g., "VISUAL_IMPAIRMENT", "LOCOMOTOR_DISABILITY"
        private Integer disabilityPercentage;
    }
}