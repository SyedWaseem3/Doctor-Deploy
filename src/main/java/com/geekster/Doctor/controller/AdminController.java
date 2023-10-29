package com.geekster.Doctor.controller;

import com.geekster.Doctor.model.*;
import com.geekster.Doctor.model.dto.AuthenticationInputDto;
import com.geekster.Doctor.service.DoctorService;
import com.geekster.Doctor.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class AdminController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;

    @GetMapping("patients")
    public List<Patient> getAllPatient(){
        return patientService.getAllPatient();
    }

    @PostMapping("doctor")
    public String addDoctor(@RequestBody @Valid Doctor newDoctor){
        return doctorService.addDoctor(newDoctor);
    }

    @GetMapping("patients/bloodGroup/{bloodGroup}")
    public List<Patient> getAllPatientsByBloodGroup(@PathVariable BloodGroup bloodGroup){
        List<Patient> patients = patientService.getAllPatientsByBloodGroup(bloodGroup);

        for(Patient patient : patients){
            patient.setAppointments(null);
        }

        return patients;
    }
}
