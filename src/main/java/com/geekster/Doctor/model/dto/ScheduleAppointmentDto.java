package com.geekster.Doctor.model.dto;

import com.geekster.Doctor.model.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleAppointmentDto {

    AuthenticationInputDto authInfo;
    Appointment appointment;
}
