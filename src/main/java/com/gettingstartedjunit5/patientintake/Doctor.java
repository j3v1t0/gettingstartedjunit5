package com.gettingstartedjunit5.patientintake;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Doctor {
    avery("Ralph Avery"),
    johnson("Beth Johnson"),
    murphy("Pat Murfy");

    private String name;
}
