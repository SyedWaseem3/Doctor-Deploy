package com.geekster.Doctor.service;

import com.geekster.Doctor.model.PatientAuthenticationToken;
import com.geekster.Doctor.model.dto.AuthenticationInputDto;
import com.geekster.Doctor.repo.IPTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PTokenService {
    @Autowired
    IPTokenRepo ipTokenRepo;

    public void createToken(PatientAuthenticationToken token) {
        ipTokenRepo.save(token);
    }

    public void deleteToken(String tokenValue) {
        PatientAuthenticationToken token = ipTokenRepo.findFirstByTokenValue(tokenValue);

        ipTokenRepo.delete(token);
    }

    public boolean authenticate(AuthenticationInputDto authInfo) {
        String email = authInfo.getEmail();
        String tokenValue = authInfo.getTokenValue();
        PatientAuthenticationToken token = ipTokenRepo.findFirstByTokenValue(tokenValue);
        if(token != null){
            return token.getPatient().getPatientEmail().equals(email);
        }else{
            return false;
        }
    }
}
