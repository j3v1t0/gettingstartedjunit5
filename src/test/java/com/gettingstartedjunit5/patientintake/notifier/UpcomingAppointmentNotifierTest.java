package com.gettingstartedjunit5.patientintake.notifier;

import com.gettingstartedjunit5.patientintake.ClinicCalendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UpcomingAppointmentNotifierTest {
    private EmailNotifierTestDouble emailDouble;

    @BeforeEach
    void init() {
        emailDouble = new EmailNotifierTestDouble();
    }

    @Test
    void sendNotificationWithCorrectFormat() {
        ClinicCalendar calendar = new ClinicCalendar(LocalDate.of(2023, 4, 27));
        calendar.addAppointment("Jim", "Weaver", "avery",
                "04/28/2023 2:00 pm");
        UpcomingAppointmentNotifier notifier = new UpcomingAppointmentNotifier(calendar, emailDouble);

        notifier.run();

        assertEquals(1, emailDouble.receivedMessages.size());
        EmailNotifierTestDouble.Message expectedMessage = emailDouble.receivedMessages.get(0);
        assertAll(
                () -> assertEquals("weaverjim@mail.com", expectedMessage.toAddress),
                () -> assertEquals("Appointment Reminder", expectedMessage.subject),
                () -> assertEquals("You have an appointment tomorrow at 2:00 PM" +
                        " with Dr. Ralph Avery.", expectedMessage.body)
        );
    }
}