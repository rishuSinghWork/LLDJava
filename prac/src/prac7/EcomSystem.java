package prac7;

import java.util.Arrays;


public class EcomSystem {

	public static void main(String[] args) {
		
		OrderManagementService service = new OrderManagementService();
		
		// add products
		service.addProduct(101, "Laptop", 1200.0);
		service.addProduct(102, "Smartphone", 800.0);
        service.addProduct(103, "Headphones", 150.0);
        
		// add customers
        service.addCustomer(1, "Alice", "alice@example.com");
        service.addCustomer(2, "Bob", "bob@example.com");
		
        // place orders --> LOOK AT THIS BITCHES <<--------------------------------------<<
		service.placeOrder(1, Arrays.asList(101, 102), "123 Main St, City");
		service.placeOrder(2, Arrays.asList(103, 999), "456 Elm St, City");
		
		// cancel orders
		service.cancelOrder(1);
		
		// view order history 
		service.viewOrderHistory(1, null);
		service.viewOrderHistory(2, "Placed");
	}

}
