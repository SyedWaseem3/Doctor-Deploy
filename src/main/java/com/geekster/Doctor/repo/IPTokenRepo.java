package com.geekster.Doctor.repo;

import com.geekster.Doctor.model.PatientAuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPTokenRepo extends JpaRepository<PatientAuthenticationToken, Integer> {
    PatientAuthenticationToken findFirstByTokenValue(String tokenValue);

}
