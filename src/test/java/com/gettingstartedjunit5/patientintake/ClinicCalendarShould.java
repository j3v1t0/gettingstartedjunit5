package com.gettingstartedjunit5.patientintake;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarShould {

    private ClinicCalendar calendar;

    @BeforeAll
    static void testClassSetup(){
        System.out.println("Before all...");
    }
    @BeforeEach
    void init(){
        System.out.println("Before each...");
        calendar = new ClinicCalendar(LocalDate.of(2023,4,27));
    }
    @Test
    void allowEntryOfAnAppointment(){
        System.out.println("Entry of appointment...");
        calendar.addAppointment("Jim", "Weaver", "avery", "09/01/2023 2:00 pm");
        List<PatientAppointment> appointments = calendar.getAppointments();
        assertNotNull(appointments);
        assertEquals(1, appointments.size());
        PatientAppointment enteredAppt = appointments.get(0);
        assertAll(
                () -> assertEquals("Jim", enteredAppt.getPatientFirstName()),
                () -> assertEquals("Weaver", enteredAppt.getPatientLastName()),
                () -> assertSame(Doctor.avery, enteredAppt.getDoctor()),
                () -> assertEquals("9/1/2023 02:00 PM",
                enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a",
                        Locale.US)))
        );
    }

    @Nested
    @DisplayName("indicate if there are appointments correctly")
    class HasAppointments {

        @Test
        @DisplayName("when there are appointments")
        void returnTrueForHasAppointmentsIfThereAreAppointments() {
            calendar.addAppointment("Jim", "Weaver", "avery",
                    "09/01/2018 2:00 pm");
            assertTrue(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
        }

        @Test
        @DisplayName("when there are no appointments")
        void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
            assertFalse(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
        }
    }

    @Nested
    @DisplayName("return appointments for a given day correctly")
    class AppointmentsForDay {

        @Test
        @DisplayName("for today")
        void returnCurrentDaysAppointments() {
            calendar.addAppointment("Jim", "Weaver", "avery",
                    "04/27/2023 2:00 pm");
            calendar.addAppointment("Jim", "Weaver", "avery",
                    "04/27/2023 3:00 pm");
            calendar.addAppointment("Jim", "Weaver", "avery",
                    "09/01/2023 2:00 pm");
            assertEquals(2, calendar.getTodayAppointments().size());
        }

        @Test
        @DisplayName("for tomorrow")
        void returnTommorowsAppointments() {
            calendar.addAppointment("Jim", "Weaver", "avery",
                    "04/28/2023 2:00 pm");
            calendar.addAppointment("Jim", "Weaver", "avery",
                    "04/28/2023 2:00 pm");
/*            calendar.addAppointment("Jim", "Weaver", "avery",
                    "04/28/2023 3:00 pm");*/
            assertEquals(2, calendar.getTomorrowAppointments().size());
        }
    }
    @Nested
    @DisplayName("return upcoming appointments")
    class UpcomingAppointments {

        @Test
        @DisplayName("as empty list when there are none")
        void whenThereAreNone() {
            List<PatientAppointment> appointments = calendar.getUpcomingAppointments();
            assertEquals(0, appointments.size());
        }

        @Test
        @DisplayName("correctly when there are some in the past as well")
        void whenThereAreSomePastAndFuture() {
            calendar.addAppointment("Jim", "Weaver", "avery",
                    "07/27/2021 2:00 pm");
            calendar.addAppointment("Jim", "Weaver", "avery",
                    "07/27/2022 2:00 pm");
            calendar.addAppointment("Jim", "Weaver", "avery",
                    "08/27/2023 2:00 pm");
            assertEquals(1, calendar.getUpcomingAppointments().size());
        }

    }
}