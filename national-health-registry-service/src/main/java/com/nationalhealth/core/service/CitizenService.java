package com.nationalhealth.core.service;

import com.nationalhealth.core.dto.CitizenRequestDto;
import com.nationalhealth.core.entity.Citizen;
import com.nationalhealth.core.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CitizenService {

    @Autowired
    private CitizenRepository citizenRepository;

    // POST business logic processing
    public Citizen registerCitizen(CitizenRequestDto dto) {
        // Validate uniqueness of Aadhaar card
        Optional<Citizen> existing = citizenRepository.findByAadharCardNo(dto.getAadharCardNo());
        if (existing.isPresent()) {
            throw new RuntimeException("Citizen with this Aadhaar card number is already registered in the national registry.");
        }

        // Map DTO values into the Database Entity structure
        Citizen citizen = new Citizen();
        citizen.setAadharCardNo(dto.getAadharCardNo());
        citizen.setFullName(dto.getFullName());
        citizen.setDateOfBirth(dto.getDateOfBirth());
        citizen.setCommunity(dto.getCommunity());
        citizen.setRegion(dto.getRegion());

        return citizenRepository.save(citizen);
    }

    // GET business logic processing
    public Optional<Citizen> getCitizenByAadhar(String aadharCardNo) {
        return citizenRepository.findByAadharCardNo(aadharCardNo);
    }
}