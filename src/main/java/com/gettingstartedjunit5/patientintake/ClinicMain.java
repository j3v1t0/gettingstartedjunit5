package com.gettingstartedjunit5.patientintake;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

//@SpringBootApplication
public class ClinicMain {

    private static ClinicCalendar calendar;
    public static void main(String[] args) throws Throwable {
        calendar = new ClinicCalendar(LocalDate.now());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Patient Intake Computer System!\n\n");
        String lastOption = "";
        while (!lastOption.equalsIgnoreCase("x")){
            lastOption = displayMenu(scanner);
        }
        System.out.println("\nExiting System...\n");
        //SpringApplication.run(ClinicMain.class, args);
    }

    private static String displayMenu(Scanner scanner) throws Throwable{
        System.out.println("Please Select an option:");
        System.out.println("1. Enter a Patient Appointment");
        System.out.println("2. View All Appointments");
        System.out.println("3. View Today's Appointments");
        System.out.println("X. Exit System.");
        System.out.println("Option:");
        String option = scanner.next();

        switch (option){
            case "1": performPatientEntry(scanner);
                    return option;
            case "2": performAllAppointments();
                return option;
            case "3": performTodayAppointments();
                return option;
            default: System.out.println("Invalid option, please re-enter");
                return option;
        }
    }

    private static void performPatientEntry(Scanner scanner){
        scanner.nextLine();
        System.out.println("\n\nPlease Enter Appointment Info:");
        System.out.println("    Patient Last Name:");
        String lastName = scanner.nextLine();
        System.out.println("    Patient First Name:");
        String firstName = scanner.nextLine();
        System.out.println("    Appointment Date (M/d/yyyy): h:m a");
        String when = scanner.nextLine();
        System.out.println("    Doctor Last Name:");
        String doctor = scanner.nextLine();

        try{
            calendar.addAppointment(firstName,lastName,doctor,when);
        }catch (Throwable t){
            System.out.println("Error! " + t.getMessage());
            return;
        }
        System.out.println("Patient entered successfully.\n\n");
    }

    private static void performAllAppointments() throws Throwable{
        System.out.println("\n\nAll Appointments in System:");
        for (PatientAppointment appointment : calendar.getAppointments()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a");
            String appTime = formatter.format(appointment.getAppointmentDateTime());
            System.out.println(String.format("%s: %s, %s\t\tDoctor: %s", appTime, appointment.getPatientLastName(),
                    appointment.getPatientFirstName(), appointment.getDoctor().getName()));
        }
        System.out.println("\nPress any key to continue...");
    }

    private static void performTodayAppointments() throws Throwable {
        System.out.println("\n\nAppointments for Today:");
        listAppointments(calendar.getTodayAppointments());
        System.out.println("\nPress any key to continue...");
        System.in.read();
        System.out.println("\n\n");
    }

    private static void listAppointments(List<PatientAppointment> appointments) {
        for (PatientAppointment appointment : appointments) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a");
            String apptTime = formatter.format(appointment.getAppointmentDateTime());
            System.out.println(String.format("%s:  %s, %s\t\tDoctor: %s", apptTime, appointment.getPatientLastName(),
                    appointment.getPatientFirstName(), appointment.getDoctor().getName()));
        }
    }
}
