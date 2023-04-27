package com.gettingstartedjunit5.patientintake;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@Tag("dateTime")
@DisplayName("DateTimeConverter should")
class DateTimeConverterShould {

    @Nested
    @DisplayName("convert string with 'today' keyboard")
    class TodayTests{
        @Test
        @DisplayName("correctly")
        void convertTodayStringCorrectly(){
            LocalDateTime today = LocalDateTime.of(2023, 4, 27, 13, 0);
            LocalDateTime result = DateTimeConverter.convertStringToDateTime("today 1:00 pm",
                    LocalDate.of(2023,4,27));
            assertEquals(result, LocalDateTime.of(2023,4,27,13,0),
                    () -> "Failed to convert 'today' string to expected date time, today passed was: " + today);
        }

        @Test
        @DisplayName("regardless of case")
        void convertTodayStringCorrectlyCaseInsensitive(){
            LocalDateTime today = LocalDateTime.of(2023, 4, 27, 13, 0);
            LocalDateTime result = DateTimeConverter.convertStringToDateTime("ToDay 1:00 pm",
                    LocalDate.of(2023,4,27));
            assertEquals(result, LocalDateTime.of(2023,4,27,13,0),
                    () -> "Failed to convert 'today' string to expected date time, today passed was: " + today);
        }
    }
    @Test
    @DisplayName("convert expected date time pattern in string correctly")
    void convertCorrectPatternToDateTime(){
        LocalDateTime result = DateTimeConverter.convertStringToDateTime("4/27/2023 1:00 pm",
                LocalDate.of(2023,4,28));
        assertEquals(result, LocalDateTime.of(2023,4,27,13,0));
    }
    @Test
    @DisplayName("throw exception if entered pattern of string incorrect")
    void throwExceptionIfIcorrectPatternProvided(){
        Throwable error = assertThrows(RuntimeException.class, () ->
                DateTimeConverter.convertStringToDateTime("today 100 pm",
                LocalDate.of(2023,4,27)));

        assertEquals("Unable to create date time from: [TODAY 100 PM], please enter with the format [M/d/yyyy h:mm a]", error.getMessage());
    }

}