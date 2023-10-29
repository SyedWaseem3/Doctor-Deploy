package com.geekster.Doctor.service;

import com.geekster.Doctor.model.Appointment;
import com.geekster.Doctor.model.Doctor;
import com.geekster.Doctor.model.Patient;
import com.geekster.Doctor.model.dto.AuthenticationInputDto;
import com.geekster.Doctor.repo.IAppointmentRepo;
import com.geekster.Doctor.repo.IDoctorRepo;
import com.geekster.Doctor.repo.IPatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentService {
    @Autowired
    IAppointmentRepo appointmentRepo;

    @Autowired
    IPatientRepo patientRepo;

    @Autowired
    PTokenService pTokenService;

    @Autowired
    IDoctorRepo doctorRepo;
    public String scheduleAppointment(AuthenticationInputDto authInfo, Appointment appointment) {
        if(pTokenService.authenticate(authInfo)){

            //find patient
            String email = authInfo.getEmail();
            Patient existingPatient = patientRepo.findFirstByPatientEmail(email);

            appointment.setPatient(existingPatient);

            //find doctor
            Integer docId = appointment.getDoctor().getDocId();

            Doctor doctor = doctorRepo.findById(docId).orElseThrow();

            appointment.setDoctor(doctor);

            if(doctor != null){
                appointment.setAppointmentCreationTime(LocalDateTime.now());
                appointmentRepo.save(appointment);
                return "Appointment booked at time : " + appointment.getAppointmentScheduleTime() + " with Dr. " + doctor.getDocName();
            }else{
                return "Doctor does not exists!";
            }

        }else{
            return "Un Authenticated access!";
        }
    }

    public String cancelAppointment(AuthenticationInputDto authInfo, Integer appointmentId) {

        if(pTokenService.authenticate(authInfo)){

            //find patient
            String email = authInfo.getEmail();
            Patient existingPatient = patientRepo.findFirstByPatientEmail(email);

            Appointment existingAppointment = appointmentRepo.findById(appointmentId).orElseThrow();

            if(existingAppointment.getPatient().equals(existingPatient)){
                appointmentRepo.deleteById(appointmentId);
                return "appointment with " + existingAppointment.getDoctor().getDocName() + " has been canceled!";
            }else{
                return "UnAuthorized cancel appointment!";
            }


        }else{
            return "Un Authenticated access!";
        }
    }
}
