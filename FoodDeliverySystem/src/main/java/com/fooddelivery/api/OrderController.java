package com.fooddelivery.api;

import com.fooddelivery.models.Customer;
import com.fooddelivery.models.MenuItem;
import com.fooddelivery.models.Order;
import com.fooddelivery.models.Restaurant;
import com.fooddelivery.service.OrderService;
import com.fooddelivery.service.RestaurantService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderController {
	private final OrderService orderService;
	private final RestaurantService restaurantService;
	
	public OrderController(OrderService orderService, RestaurantService restaurantService) {
		this.orderService = orderService;
		this.restaurantService = restaurantService;
	}
	
	// place a new order 
	public void placeOrder(Customer customer, Scanner scanner) throws SQLException {
		List<Restaurant> restaurants = restaurantService.getAllRestaurants();
		if (restaurants.isEmpty()) {
			System.out.println("No restaurants available.");
			return;
		}
		
		System.out.println("Available Restaurants: ");
		for (Restaurant restaurant : restaurants) {
			System.out.println("ID: " + restaurant.getId() + " | Name: " + restaurant.getName());
		}
		System.out.println("Enter Restaurant ID to view its menu: ");
		int restaurantId = scanner.nextInt();
		scanner.nextLine();
		
		Restaurant selectedRestaurant = restaurantService.getRestaurantById(restaurantId);
		if (selectedRestaurant == null) {
			System.out.println("Invalid Restaurant ID.");
			return;
		}
		
		List<MenuItem> menu = selectedRestaurant.getMenu();
		if (menu.isEmpty()) {
			System.out.println("This restaurant has no menu items.");
			return;
		}
		
		System.out.println("Menu for " + selectedRestaurant.getName() + ":");
		for (MenuItem item : menu) {
			System.out.println("ID: " + item.getId() + " | Name: " + item.getName() + " | Price: " + item.getPrice());
		}
		
		
		List<MenuItem> selectedItems = new ArrayList<>();
		while (true) {
			System.out.println("Enter Menu Item ID to add to cart (or type 'done' to finish): ");
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("done")) break;
			
			try {
				int menuItemId = Integer.parseInt(input);
				MenuItem menuItem = menu.stream()
						.filter(item -> item.getId() == menuItemId)
						.findFirst()
						.orElse(null);
				
				if (menuItem != null) {
					System.out.println("Enter Quntity: ");
					int quantity = Integer.parseInt(scanner.nextLine());
					menuItem.setQuantity(quantity);
					selectedItems.add(menuItem);
				} else {
					System.out.println("invalid Menu Item ID.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid number.");
			}
		}
			
			if (selectedItems.isEmpty()) {
				System.out.println("no items selected. Ordered canclled.");
				return;
			}
			
			// assign an available delivery partner 
			Order order = new Order(0, customer, selectedRestaurant, null, selectedItems, "Pending");
			boolean deliveryPartnerAssigned = orderService.assignDeliveryPartner(order);
			
			if (deliveryPartnerAssigned) {
				orderService.placeOrder(order);
				System.out.println("Order placed successfully!");
			} else {
				System.out.println("No delivery partner available. Please try again later.");
			}
		}
	
	// view details of order 
	public void viewOrderDeatails(Scanner scanner) throws SQLException {
		System.out.println("Enter Order ID: ");
		int orderId = scanner.nextInt();
		scanner.nextLine();
		
		Order order = orderService.getOrderById(orderId);
		if (order != null) {
			System.out.println(order);
		} else {
			System.out.println("Order not found.");
		}
	}
	
	// update status of the order 
	public void updateOrderStatus(Scanner scanner) throws SQLException {
		System.out.println("Enter Order ID: ");
		int orderId = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Enter New Status (e.g. Pending, Completed, Canceled): ");
		String status = scanner.nextLine();
		
		orderService.updateOrderStatus(orderId, status);
		System.out.println("order status updated successfully!");
	}
}
