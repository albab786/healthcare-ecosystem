package com.gov.health.insurance.repository;

import com.gov.health.insurance.entity.CitizenPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CitizenPolicyRepository extends JpaRepository<CitizenPolicy, Long> {
    
    // Crucial for the rule engine to prevent duplicate 50,000 policy allocations
    Optional<CitizenPolicy> findByAadharCardNo(String aadharCardNo);

    // Used by hospitals or billing services to verify policy state via policy number
    Optional<CitizenPolicy> findByPolicyNumber(String policyNumber);
}