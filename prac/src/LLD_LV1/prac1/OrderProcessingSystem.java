package LLD_LV1.prac1;

import java.util.ArrayList;
import java.util.List;

public class OrderProcessingSystem {

	public static void main(String[] args) {
		OrderService orderService = new OrderService();
		
		// Create a customer 
		Customer customer = new Customer("Alice", "alice@123.com", "123 Main St.");
		
		// Create list of items 
		List<Item> items = new ArrayList<>();
		items.add(new Item("Laptop", 1200.00, 1));
		items.add(new Item("Mouse", 25.45, 2));
		
		// Adding a new order 
		orderService.addOrder(1, customer, items);
		
		// Retrieve and display order 
		Order order = orderService.getOrder(1);
		System.out.println("Retrieve Order : " + order);
		
		
		
		List<Item> itemsToAdd = new ArrayList<>();
		itemsToAdd.add(new Item("Keyboard", 45.00, 1));
		
		List<String> itemsToRemove = new ArrayList<>();
		itemsToRemove.add("Mouse");
		
		orderService.updateOrder(1, itemsToAdd, itemsToRemove);
		
		// retrieve and display with a new item list 
		order = orderService.getOrder(1);
		System.out.println("Updated Order : " + order);
		
		// display all orders 
		System.out.println("\nAll Orders: ");
		orderService.displayAllOrders();
	}

}


/*
 * order : order ID , customer details (name , address), list of items 
 * Map [
 * 		key : order ID 
 * 		value : order details (customer details + list of items) 
 * 			]
 * 
 * 	-implement methods -> 
 * 		1. add new order - put()
 * 		2. update the order item list - put()
 * 		3. retrieve order - getOrDefault()
 * 
 * 
 * customer class -> customer details [Done]
 * order class -> aggregate of the order details 
 * item class -> rep item of an order 
 * OrderProcessingSystem class -> main operation class 
 */