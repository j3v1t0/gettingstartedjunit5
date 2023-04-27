package com.gettingstartedjunit5.patientintake;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeConverterShould {

    @Test
    void convertTodayStringCorrectly(){
        LocalDateTime result = DateTimeConverter.convertStringToDateTime("today 1:00 pm",
                LocalDate.of(2023,4,27));
        assertEquals(result, LocalDateTime.of(2023,4,27,13,0));
    }

    @Test
    void convertCorrectPatternToDateTime(){
        LocalDateTime result = DateTimeConverter.convertStringToDateTime("4/27/2023 1:00 pm",
                LocalDate.of(2023,4,27));
        assertEquals(result, LocalDateTime.of(2023,4,27,13,0));
    }

    @Test
    void throwExceptionIfIcorrectPatternProvided(){
        Throwable error = assertThrows(RuntimeException.class, () ->
                DateTimeConverter.convertStringToDateTime("today 100 pm",
                LocalDate.of(2023,4,27)));

        assertEquals("Unable to create date time from: [TODAY 100 PM], please enter with the format [M/d/yyyy h:mm a]", error.getMessage());
    }

}