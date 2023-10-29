package com.geekster.Doctor.repo;

import com.geekster.Doctor.model.Appointment;
import com.geekster.Doctor.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepo extends JpaRepository<Appointment, Integer> {
}
