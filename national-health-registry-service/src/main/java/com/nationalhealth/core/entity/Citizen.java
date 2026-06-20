package com.nationalhealth.core.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "citizens")
@Data
public class Citizen {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "aadhar_card_no", unique = true, nullable = false)
    private String aadharCardNo;

    @Column(name = "full_name", nullable = false)
    private String fullName;


    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;
    
    private String community;
    
    private String region; 


}