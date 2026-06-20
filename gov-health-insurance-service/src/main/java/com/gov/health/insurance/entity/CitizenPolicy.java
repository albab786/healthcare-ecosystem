package com.gov.health.insurance.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "citizen_policies", indexes = {
    @Index(name = "idx_policy_aadhar", columnList = "aadhar_card_no")
})
@Data
public class CitizenPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "aadhar_card_no", nullable = false)
    private String aadharCardNo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "insurance_plan_id", nullable = false)
    private InsurancePlan insurancePlan;

    @Column(name = "policy_number", unique = true, nullable = false)
    private String policyNumber;

    @Column(name = "applied_subsidy_percentage", nullable = false)
    private Integer appliedSubsidyPercentage;

    @Column(name = "final_payable_premium", nullable = false)
    private BigDecimal finalPayablePremium;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    @Column(nullable = false)
    private String status;
}