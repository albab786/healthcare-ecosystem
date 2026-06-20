package com.gov.health.insurance.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "insurance_plans")
@Data
public class InsurancePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plan_name", nullable = false, unique = true)
    private String planName; // e.g., "Ayushman Bharat Suraksha", "LIC Swasthya Bima"

    @Column(name = "plan_type", nullable = false)
    private String planType; // "FREE_WELFARE" or "SUBSIDIZED_ENDOWMENT"

    @Column(name = "max_coverage_amount", nullable = false)
    private BigDecimal maxCoverageAmount; // e.g., 500000.00 (5 Lakhs cover)

    @Column(name = "standard_annual_premium")
    private BigDecimal standardAnnualPremium; // 0.00 for free plans, numeric for paid ones
}