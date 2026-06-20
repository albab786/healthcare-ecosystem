package com.nationalhealth.core.dto;

import com.nationalhealth.core.entity.ConditionCategory;
import lombok.Data;
import java.time.LocalDate;

@Data
public class SpecialConditionRequestDto {
    private String aadharCardNo;
    private ConditionCategory conditionCategory;
    private Integer disabilityPercentage;
    private String certificateNumber;
    private LocalDate issuedDate;
    private boolean isPermanent;
}