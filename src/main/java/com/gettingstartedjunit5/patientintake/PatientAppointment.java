package com.gettingstartedjunit5.patientintake;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;
@Getter
@Setter
@RequiredArgsConstructor
public class PatientAppointment {

    private final String patientFirstName;
    private final String patientLastName;
    private final LocalDateTime appointmentDateTime;
    private final Doctor doctor;
    private double apptBmi;
}
