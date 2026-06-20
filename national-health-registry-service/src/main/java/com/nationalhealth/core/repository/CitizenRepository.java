package com.nationalhealth.core.repository;

import com.nationalhealth.core.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {
    Optional<Citizen> findByAadharCardNo(String aadharCardNo);
}