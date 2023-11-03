package com.oasis;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReservationSystem
{
    private static final Map<String, String> validCredentials = new HashMap<>();
    private static String currentUser;
    private static Map<String, Reservation> reservations = new HashMap<>();

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        while (true) 
        {
            if (currentUser == null) 
            {
                System.out.println("Welcome to the Online Reservation System");
                System.out.print("Enter your username (or type 'exit' to quit): ");
                String username = scanner.nextLine();

                if (username.equalsIgnoreCase("exit"))
                {
                    System.out.println("Goodbye!");
                    break;
                }

                System.out.print("Enter your password: ");
                String password = scanner.nextLine();

                if (isValidCredentials(username, password)) 
                {
                    currentUser = username;
                    System.out.println("Login successful. You can now access the main system.\n");
                }
                else 
                {
                    System.out.println("Invalid credentials. Access denied.\n");
                }
            } 
            else
            {
                System.out.println("Welcome, " + currentUser + "!");
                System.out.println("1. Book a Ticket");
                System.out.println("2. Cancel a Ticket");
                System.out.println("3. Logout");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice)
                {
                    case 1:
                        registerTicket();
                        break;
                    case 2:
                        cancelTicket();
                        break;
                    case 3:
                        logout();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.\n");
                }
            }
        }
    }

    private static boolean isValidCredentials(String username, String password)
    {
       
        return true; 
    }

    private static void registerTicket() 
    {
        System.out.println("Booking a ticket for " + currentUser);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your full name: ");
        String fullName = scanner.nextLine();

        System.out.print("Enter the train number: ");
        String trainNumber = scanner.nextLine();

       
        String trainName = getTrainName(trainNumber);

        System.out.print("Enter class type (e.g., first class, second class): ");
        String classType = scanner.nextLine();

        System.out.print("Enter the date of journey: ");
        String dateOfJourney = scanner.nextLine();

        System.out.print("Enter the departure place: ");
        String fromPlace = scanner.nextLine();

        System.out.print("Enter the destination: ");
        String toDestination = scanner.nextLine();

 
        String pnr = generatePNR();
        Reservation reservation = new Reservation(fullName, trainNumber, trainName, classType, dateOfJourney, fromPlace, toDestination);
        reservations.put(pnr, reservation);

        System.out.println("Ticket booked successfully. Your PNR is: " + pnr + "\n");
    }

    private static String getTrainName(String trainNumber)
    {
        return "Sample Train Name";
    }

    private static String generatePNR() 
    {
        
        return "PNR4563"; 
    }

    private static void cancelTicket()
    {
        System.out.println("Canceling a ticket for " + currentUser);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your PNR (Passenger Name Record) number: ");
        String pnr = scanner.nextLine();

        Reservation reservation = reservations.get(pnr);

        if (reservation != null)
        {
            System.out.println("Ticket details:");
            System.out.println(reservation);
            System.out.print("Do you want to confirm cancellation (yes/no)? ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("yes"))
            {
                reservations.remove(pnr);
                System.out.println("Ticket with PNR " + pnr + " has been canceled.\n");
            } 
            else
            {
                System.out.println("Cancellation not confirmed. Your ticket is still valid.\n");
            }
        }
        else
        {
            System.out.println("Ticket with PNR " + pnr + " not found. Please check the PNR and try again.\n");
        }
    }

    private static void logout() {
        System.out.println("Logout successful. Goodbye, " + currentUser + "!");
        currentUser = null;
    }
}
class Reservation 
{
    private String fullName;
    private String trainNumber;
    private String trainName;
    private String classType;
    private String dateOfJourney;
    private String fromPlace;
    private String toDestination;

    public Reservation(String fullName, String trainNumber, String trainName, String classType, String dateOfJourney, String fromPlace, String toDestination) 
    {
        this.fullName = fullName;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.fromPlace = fromPlace;
        this.toDestination = toDestination;
    }


    public String toString()
    {
    	
        return "Full Name: " + fullName + "\n" +
               "Train Number: " + trainNumber + "\n" +
               "Train Name: " + trainName + "\n" +
               "Class Type: " + classType + "\n" +
               "Date of Journey: " + dateOfJourney + "\n" +
               "Departure Place: " + fromPlace + "\n" +
               "Destination: " + toDestination;
    }
}