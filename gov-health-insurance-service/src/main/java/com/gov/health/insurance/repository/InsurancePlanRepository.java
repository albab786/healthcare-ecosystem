package com.gov.health.insurance.repository;

import com.gov.health.insurance.entity.InsurancePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface InsurancePlanRepository extends JpaRepository<InsurancePlan, Long> {
    
    // Used by the service layer to fetch the specific welfare package configuration
    Optional<InsurancePlan> findByPlanName(String planName);
}