package com.geekster.Doctor.controller;

import com.geekster.Doctor.model.Doctor;
import com.geekster.Doctor.model.Patient;
import com.geekster.Doctor.model.dto.AuthenticationInputDto;
import com.geekster.Doctor.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @GetMapping("doctors")
    public List<Doctor> getAllDoctors(@RequestBody @Valid AuthenticationInputDto authInfo){
        return doctorService.getAllDoctors(authInfo);
    }

    @GetMapping("doctor/{id}")
    public Doctor getADoctorById(@PathVariable Integer id){
        return doctorService.getADoctorById(id);
    }
}
