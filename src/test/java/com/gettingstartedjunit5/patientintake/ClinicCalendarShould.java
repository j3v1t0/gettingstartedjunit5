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
        assertEquals("Jim", enteredAppt.getPatientFirstName());
        assertEquals("Weaver", enteredAppt.getPatientLastName());
        assertSame(Doctor.avery, enteredAppt.getDoctor());
        assertEquals("9/1/2023 02:00 PM",
                enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a", Locale.US)));
    }

    @Test
    void returnTrueForHasAppointmentsIfThereAreAppointments(){
        System.out.println("Has appts...");
        calendar.addAppointment("Jim", "Weaver", "avery", "09/01/2023 12:00 pm");
        assertTrue(calendar.hasAppointment(LocalDate.of(2023,9,1)));
    }
    @Test
    void returnFalseForHasAppointmentsIfThereAreAppointments(){
        System.out.println("No appts...");
        assertFalse(calendar.hasAppointment(LocalDate.of(2023,9,1)));
    }

    @Test
    @Disabled
    void returnCurrentDaysAppointments() {
        System.out.println("current days appts...");
        calendar.addAppointment("Jim", "Weaver", "avery", "04/27/2023 2:00 pm");
        calendar.addAppointment("Carlos", "Almonte", "avery", "04/27/2023 4:00 pm");

        assertEquals(2, calendar.getTodayAppointments().size());
        assertIterableEquals(calendar.getTodayAppointments(), calendar.getAppointments());
    }
    @AfterEach
    void tearDownEachTest(){
        System.out.println("After each...");
    }

    @AfterAll
    static void tearDownTestClass(){
        System.out.println("After all...");
    }
}