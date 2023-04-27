package com.gettingstartedjunit5.patientintake;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
public class PatientAppointment {

    private String patientFirstName;
    private String patientLastName;
    private LocalDateTime appointmentDateTime;
    private Doctor doctor;
}
