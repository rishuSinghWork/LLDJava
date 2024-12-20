package prac7;

import java.util.*;
import java.util.stream.Collectors;

class OrderManagementService {
	private Map<Integer, Product> products = new HashMap<>();
	private Map<Integer, Customer> customers = new HashMap<>();
	private List<Order> orders = new ArrayList<>();
	private int nextOrderId = 1;
	
	// Add product to catalog
	public void addProduct(int productId, String productName, double price) {
		products.put(productId, new Product(productId, productName, price));
		System.out.println("Product added:" + productName);
	}
	
	// Add a customer to system 
	public void addCustomer(int uid, String name, String email) {
		customers.put(uid, new Customer(uid, name, email));
		System.out.println("New Customer added: "+name);
	}
	
	// place an order
	public void placeOrder(int uid, List<Integer> productIds, String shippingAddress) {
		Customer customer = customers.get(uid);
		if (customer == null) {
			System.out.println("Customer does not exists.");
			return;
		}
		
		List<Product> orderProducts = new ArrayList<>();
		for (int productId : productIds) {
			Product product = products.get(productId);
			if(product != null) {
				orderProducts.add(product);
			} else {
				System.out.println("Product with ID "+productId+" does not exists.");
			}
		}
		
		if(orderProducts.isEmpty()) {
			System.out.println("No valid products found. Order not placed.");
			return;
		}
		
		Order order = new Order(nextOrderId++, customer, orderProducts,"Placed", shippingAddress);
		orders.add(order);
		return;
	}
	
	// cancel an order
	public void cancelOrder(int orderId) {
		for(Order order : orders) {
			if(order.getOrderId() == orderId) {
				order.setStatus("Cancelled");
				order.setPaymentStatus("Refund intitaed.");
				System.out.println("Order cancelled: "+orderId);
				return;
			}
		}
		System.out.println("Order not found.");
	}
	
	// view order history of a customer
	public void viewOrderHistory(int uid, String filterStatus) {
		System.out.println("Order history for Customer "+uid+":");
		List<Order> filteredOrders = orders.stream()
				.filter(order -> order.getCustomer().getCustomerId()== uid)
				.filter(order -> filterStatus == null || order.getStatus().equalsIgnoreCase(filterStatus))
				.collect(Collectors.toList());
		if(filteredOrders.isEmpty()) {
			System.out.println("No orders found");
		} else {
			filteredOrders.forEach(System.out::println);
		}
	}
	/**
	 * not using set as for lookup we have to iterate over entire set where as for 
	 * map it can be done in O(1) TC
	 * also 
	 * Frequent need for efficient lookups (e.g., finding products by ID during order placement).
	   Simplified management of updates and validation.
	 */
}

