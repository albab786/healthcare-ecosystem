package com.nationalhealth.core.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CitizenRequestDto {
    private String aadharCardNo;
    private String fullName;
    private LocalDate dateOfBirth;
    private String community;
    private String region;
}