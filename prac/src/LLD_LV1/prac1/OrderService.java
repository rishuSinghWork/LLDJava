package LLD_LV1.prac1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
/**
 * this contains method to add, update, retrieve orders 
 * methods to implement 
 * 
 * -to add a new order --DONE 
 * -to update an existing order's item list --DONE
 * -to retrieve an order by ID --DONE
 * -to display all orders	--DONE 
 */
	private Map<Integer, Order> orderMap = new HashMap<>();
	
	public void addOrder(int orderId, Customer customer, List<Item> items) {
		if(orderMap.containsKey(orderId)) {
			System.out.println("Order ID already exists. Use updateOrder to modify.");
			return;
		}
		Order order = new Order(orderId, customer, items);
		orderMap.put(orderId, order);
		System.out.println("Order added succesfully.");
	}
	
	public void updateOrder(int orderId, List<Item> itemsToAdd, List<String> itemsToRemove) {
//		Order order = orderMap.get(orderId);
//		if (order == null) {
//			System.out.println("Order not found");
//			return;
//		}
//		order.setItems(items);
//		System.out.println("Order update successfully.");
		Order order = orderMap.get(orderId);
		if(order == null) {
			System.out.println("Order not found!");
			return;
		}
		
		// Add new items
		if (itemsToAdd != null) {
			for (Item item : itemsToAdd) {
				order.addItem(item);
			}
		}
		// Remove an item 
		if (itemsToRemove != null) {
			for (String itemName : itemsToRemove) {
				order.removeItem(itemName);
			}
		}
		
		System.out.println("Order updated successfully.");
	}
	
	public Order getOrder(int orderId) {
		Order order = orderMap.get(orderId);
		if (order == null) {
			System.out.println("Order not found.");
			return null;
		}
		return order;
	}
	
	public void displayAllOrders() {
		for (Order order : orderMap.values()) {
			System.out.println(order);
		}
	}
	
}
