package com.nationalhealth.core.service;

import com.nationalhealth.core.dto.SpecialConditionRequestDto;
import com.nationalhealth.core.entity.SpecialCondition;
import com.nationalhealth.core.repository.SpecialConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SpecialConditionService {

    @Autowired
    private SpecialConditionRepository specialConditionRepository;

    // POST execution: Adds a disability or chronic illness record
    public SpecialCondition addSpecialCondition(SpecialConditionRequestDto dto) {
        // Prevent duplicate certificate registrations
        if (dto.getCertificateNumber() != null) {
            Optional<SpecialCondition> existingCert = specialConditionRepository.findByCertificateNumber(dto.getCertificateNumber());
            if (existingCert.isPresent()) {
                throw new RuntimeException("A disability certificate with this number is already registered.");
            }
        }

        SpecialCondition condition = new SpecialCondition();
        condition.setAadharCardNo(dto.getAadharCardNo());
        condition.setConditionCategory(dto.getConditionCategory());
        condition.setDisabilityPercentage(dto.getDisabilityPercentage());
        condition.setCertificateNumber(dto.getCertificateNumber());
        condition.setIssuedDate(dto.getIssuedDate());
        condition.setPermanent(dto.isPermanent());

        return specialConditionRepository.save(condition);
    }

    // GET execution: Returns all conditions bound to an Aadhaar number
    public List<SpecialCondition> getConditionsByAadhar(String aadharCardNo) {
        return specialConditionRepository.findByAadharCardNo(aadharCardNo);
    }
}