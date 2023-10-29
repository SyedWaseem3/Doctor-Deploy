package com.geekster.Doctor.repo;

import com.geekster.Doctor.model.Doctor;
import com.geekster.Doctor.model.Qualification;
import com.geekster.Doctor.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDoctorRepo extends JpaRepository<Doctor, Integer> {
    List<Doctor> findByDocSpecializationOrDocQualification(Specialization specialization, Qualification qualification);

}
