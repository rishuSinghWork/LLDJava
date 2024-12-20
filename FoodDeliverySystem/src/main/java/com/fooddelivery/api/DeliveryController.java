package com.fooddelivery.api;

import com.fooddelivery.models.DeliveryPartner;
import com.fooddelivery.service.DeliveryService;

import java.sql.SQLException;
import java.util.Scanner;

public class DeliveryController {
	public final DeliveryService deliveryService;
	
	public DeliveryController(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}
	
	// register a new delivery partner 
	public void registerDeliveryPartner(Scanner scanner) throws SQLException {
	    System.out.println("Enter Delivery Partner ID: ");
	    int id = scanner.nextInt();
	    scanner.nextLine();  // Consume the newline character left by nextInt()

	    System.out.println("Enter Delivery Partner name: ");
	    String name = scanner.nextLine();

	    System.out.println("Is Delivery Partner Available? (true/false): ");
	    boolean available = Boolean.parseBoolean(scanner.nextLine());

	    DeliveryPartner deliveryPartner = new DeliveryPartner(id, name, available);
	    deliveryService.addDeliveryPartner(deliveryPartner);
	    System.out.println("Delivery Partner registered successfully!");
	}
	
	// update availability of delivery partner 
	public void updateAvailability(Scanner scanner) throws SQLException {
		System.out.println("Enter Delivery Partner ID: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Is Delivery Partner Available? (true/false): ");
		boolean available = Boolean.parseBoolean(scanner.nextLine());
		deliveryService.updateDeliveryPartnerAvailablity(id, available);
		System.out.println("Availability updated successfully!");
	}
	
	// assign an available delivery partner 
	public void assignDeliveryPartner() throws SQLException {
		DeliveryPartner deliveryPartner = deliveryService.assignDeliveryPartner();
		if (deliveryPartner != null) {
			System.out.println("Assigned Delivery Partner: " + deliveryPartner.getName());
		} else {
			System.out.println("No delivery partners currently available.");
		}
	}
}
