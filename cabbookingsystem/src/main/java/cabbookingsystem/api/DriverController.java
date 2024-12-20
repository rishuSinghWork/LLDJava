package cabbookingsystem.api;

import cabbookingsystem.models.Driver;
import cabbookingsystem.service.DriverService;

import java.sql.SQLException;
import java.util.Scanner;

public class DriverController {
	private final DriverService driverService;
	
	public DriverController(DriverService driverService) {
		this.driverService = driverService;
	}
	
	public void driverMenu(Scanner scanner) {
		while (true) {
			System.out.println("\nDriver Menu:");
			System.out.println("1. Register");
			System.out.println("2. Set Availability");
			System.out.println("3. Accept a Ride");
			System.out.println("4. Back to Main Menu");
			System.out.println("Enter your choice: ");
			
			String choice = scanner.nextLine();
			try {
				switch (choice) {
				case "1" :
					registerDriver(scanner);
					break;
				case "2" :
					setDriverAvailability(scanner);
					break;
				case "3" :
					acceptRide(scanner);
					break;
				case "4" :
					return;
				
				default : 
					System.out.println("Invalid choice. Please try again.");
				}
			} catch (SQLException e) {
				System.err.println("An error occured: " + e.getMessage());
			}
		}
	}
	
	private void registerDriver(Scanner scanner) throws SQLException {
		System.out.println("Enter your name: ");
		String name = scanner.nextLine();
		System.out.println("Enter your car details: ");
		String carDetails = scanner.nextLine();
		
		Driver driver = driverService.regiterDriver(name, carDetails);
		System.out.println("Regsitered successfully! Your ID: " + driver.getId());
	}
	
	private void setDriverAvailability(Scanner scanner) throws SQLException {
		System.out.println("Enter your Driver ID: ");
		int driverId = Integer.parseInt(scanner.nextLine());
		System.out.println("Are you available? (true/false)");
		boolean availabilty = Boolean.parseBoolean(scanner.nextLine());
		
		driverService.setAvailability(driverId, availabilty);
		System.out.println("Availability updated successfully!");
	}
	
	public void acceptRide(Scanner scanner) throws SQLException {
		System.out.println("Enter your Driver ID: ");
		int driverId = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter Ride ID: ");
		int rideID = Integer.parseInt(scanner.nextLine());
		
		driverService.acceptRide(driverId, rideID);
		System.out.println("Ride accepted successfully!");
	}
}
