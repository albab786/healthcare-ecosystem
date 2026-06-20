package com.gov.health.insurance.service;

import com.gov.health.insurance.dto.CitizenRegistryProfileDto;
import com.gov.health.insurance.entity.CitizenPolicy;
import com.gov.health.insurance.entity.InsurancePlan;
import com.gov.health.insurance.repository.CitizenPolicyRepository;
import com.gov.health.insurance.repository.InsurancePlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Service
public class AutomaticEligibilityService {

    @Autowired
    private CitizenPolicyRepository policyRepository;

    @Autowired
    private InsurancePlanRepository planRepository;

    public CitizenPolicy processAutoEnrollment(CitizenRegistryProfileDto profile) {
        
        // 1. Calculate Age dynamically from DOB
        int age = Period.between(profile.getDateOfBirth(), LocalDate.now()).getYears();

        // 2. Check PwD status (Any category with a percentage recorded)
        boolean isPwD = profile.getSpecialConditions() != null && !profile.getSpecialConditions().isEmpty();

        // 3. Check Scheduled Tribe status
        boolean isScheduledTribe = "ST".equalsIgnoreCase(profile.getCommunity());

        // 4. Evaluate the Government rule
        if (age >= 60 || isScheduledTribe || isPwD) {
            
            // Check if they already have an active policy to prevent duplicate entries
            return policyRepository.findByAadharCardNo(profile.getAadharCardNo())
                .orElseGet(() -> createNewGovernmentWelfarePolicy(profile.getAadharCardNo()));
        }
        
        throw new RuntimeException("Citizen does not meet the automatic government welfare criteria.");
    }

    private CitizenPolicy createNewGovernmentWelfarePolicy(String aadharNo) {
        // Fetch or mock the base 50,000 welfare plan from your catalog
        InsurancePlan welfarePlan = planRepository.findByPlanName("Govt Free Welfare Cover")
            .orElseGet(() -> {
                InsurancePlan defaultPlan = new InsurancePlan();
                defaultPlan.setPlanName("Govt Free Welfare Cover");
                defaultPlan.setPlanType("FREE_WELFARE");
                defaultPlan.setMaxCoverageAmount(new BigDecimal("50000.00"));
                defaultPlan.setStandardAnnualPremium(BigDecimal.ZERO);
                return planRepository.save(defaultPlan);
            });

        // Construct the policy entry for the DB
        CitizenPolicy policy = new CitizenPolicy();
        policy.setAadharCardNo(aadharNo);
        policy.setInsurancePlan(welfarePlan);
        policy.setPolicyNumber("GOV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        policy.setAppliedSubsidyPercentage(100); // 100% free
        policy.setFinalPayablePremium(BigDecimal.ZERO);
        policy.setStartDate(LocalDate.now());
        policy.setExpiryDate(LocalDate.now().plusYears(1)); // Valid for 1 year
        policy.setStatus("ACTIVE");

        return policyRepository.save(policy);
    }
}