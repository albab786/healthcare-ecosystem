package com.nationalhealth.core.repository;

import com.nationalhealth.core.entity.SpecialCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SpecialConditionRepository extends JpaRepository<SpecialCondition, Long> {
    
    // Crucial for checking if a citizen has any PwD criteria flags
    List<SpecialCondition> findByAadharCardNo(String aadharCardNo);

    // Helps verify if a certificate number is already registered to prevent duplicates
    Optional<SpecialCondition> findByCertificateNumber(String certificateNumber);
}