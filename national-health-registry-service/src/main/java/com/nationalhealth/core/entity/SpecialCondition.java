package com.nationalhealth.core.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;


@Entity
@Table(name = "special_conditions", indexes = {
    @Index(name = "idx_special_cond_aadhar", columnList = "aadhar_card_no")
})
@Data
public class SpecialCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Direct link using the national key instead of object mapping
    @Column(name = "aadhar_card_no", nullable = false)
    private String aadharCardNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition_category", nullable = false)
    private ConditionCategory conditionCategory;

    @Column(name = "disability_percentage")
    private Integer disabilityPercentage; 

    @Column(name = "certificate_number", unique = true)
    private String certificateNumber; 

    @Column(name = "issued_date")
    private LocalDate issuedDate;

    @Column(name = "is_permanent", nullable = false)
    private boolean isPermanent;
}