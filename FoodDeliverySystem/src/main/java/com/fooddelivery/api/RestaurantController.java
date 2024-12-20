package com.fooddelivery.api;

import com.fooddelivery.service.RestaurantService;

import java.sql.SQLException;
import java.util.Scanner;

public class RestaurantController {
	private final RestaurantService restaurantService;
	
	public RestaurantController(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}
	
	// register restaurant 
	public void registerRestaurant(Scanner scanner) throws SQLException {
		System.out.println("Enter Restaurant Name:");
		String name = scanner.nextLine();
		
		System.out.println("Enter Address Details:");
		System.out.println("Street: ");
		String street = scanner.nextLine();
		System.out.println("City: ");
		String city = scanner.nextLine();
		System.out.println("State: ");
		String state = scanner.nextLine();
		System.out.println("Zip Code: ");
		String zipCode = scanner.nextLine();
		
		restaurantService.registerRestaurant(name, street, city, state, zipCode);
		System.out.println("Restarant registered successfully!");
	}
	// add menu items 
	public void addMenuItems(Scanner scanner) throws SQLException {
		System.out.println("Enter Restaurant ID to add menu items:");
		int restaurantId = Integer.parseInt(scanner.nextLine());
		
		while (true) {
			System.out.println("Enter Menu Item Details:");
			System.out.println("Name: ");
			String itemName = scanner.nextLine();
			System.out.println("Price: ");
			double price = Double.parseDouble(scanner.nextLine());
			System.out.println("Available (true/false): ");
			boolean available = Boolean.parseBoolean(scanner.nextLine());
			System.out.println("Quantity: ");
			int quantity = Integer.parseInt(scanner.nextLine());
			
			restaurantService.addMenuItem(restaurantId, itemName, price, available, quantity);
			System.out.println("Menu item added successfully");
			
			System.out.println("Add another item? (yes/no):");
			if (!scanner.nextLine().equalsIgnoreCase("yes")) {
				break;
			}
		}
	}
	
	// view all restaurant 
	public void viewAllRestaurants() throws SQLException {
		restaurantService.getAllRestaurants().forEach(System.out::println);
	}
}
