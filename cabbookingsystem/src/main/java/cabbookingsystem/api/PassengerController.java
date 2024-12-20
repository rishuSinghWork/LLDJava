package cabbookingsystem.api;

import cabbookingsystem.models.Passenger;
import cabbookingsystem.models.Ride;
import cabbookingsystem.service.PassengerService;

import java.sql.SQLException;
import java.util.Scanner;

public class PassengerController {
	private final PassengerService passengerService;
	
	public PassengerController(PassengerService passengerService) {
		this.passengerService = passengerService;
	}
	
	public void passengerMenu(Scanner scanner) {
		while (true) {
			System.out.println("\nPassenger Menu:");
			System.out.println("1. Register");
			System.out.println("2. Book a Ride");
			System.out.println("3. View Ride History");
			System.out.println("4. Rate Driver");
			System.out.println("5. Back to Main Menu");
			System.out.println("Enter your choice: ");
			
			String choice = scanner.nextLine();
			try {
                switch (choice) {
                    case "1" :
                    	registerPassenger(scanner);
                    	break;
                    case "2" :
                    	bookRide(scanner);
                    	break;
                    case "3" :
                    	viewRideHistory(scanner);
                    	break;
                    case "4" :
                    	rateDriver(scanner);
                    	break;
                    case "5" : {
                        return;
                    }
                    default : System.out.println("Invalid choice. Please try again.");
                }
            } catch (SQLException e) {
                System.err.println("An error occurred: " + e.getMessage());
            }

		}
	}
	
	// Register passenger 
	private void registerPassenger(Scanner scanner) throws SQLException {
		System.out.println("Enter your name: ");
		String name = scanner.nextLine();
		System.out.println("Enter your email: ");
		String email = scanner.nextLine();
		System.out.println("Enter ylur location: ");
		String location = scanner.nextLine();
		
		Passenger passenger = passengerService.registerPassenger(name, email, location);
		System.out.println("Register successful! Your ID is: " + passenger.getId());
	}
	
	// book Ride 
	private void bookRide(Scanner scanner) throws SQLException {
		System.out.println("Enter your Passenger ID: ");
		int passengerId = Integer.parseInt(scanner.nextLine());
		Passenger passenger = passengerService.getPassengerById(passengerId);
		if (passenger == null) {
			System.out.println("Passenger not found.");
			return;
		}
		
		System.out.println("Enter Pickup Location: ");
		String pickup = scanner.nextLine();
		System.out.println("Enter drop Location: ");
		String drop = scanner.nextLine();
		
		Ride ride = passengerService.bookRide(passenger, pickup, drop);
		if (ride != null) {
			System.out.println("Ride booked sucessfully!");
		} else {
			System.out.println("Failed to book a ride. Please try again.");
		}
	}
	
	// view Ride history 
	private void viewRideHistory(Scanner scanner) throws SQLException {
		System.out.println("Enter your Passenger ID: ");
		int passengerId = Integer.parseInt(scanner.nextLine());
		passengerService.viewRideHistory(passengerId).forEach(System.out::println);
	}
	// rate driver 
	private void rateDriver(Scanner scanner) throws SQLException {
		System.out.println("Enter Driver ID: ");
		int driverId = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter Rating (1-5): ");
		double rating = Double.parseDouble(scanner.nextLine());
		
		passengerService.rateDriver(driverId, rating);
		System.out.println("Thank you for your feedback!");
	}
}
