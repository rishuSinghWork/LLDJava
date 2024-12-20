package com.shopping.api;

import java.util.Map;

import com.shopping.models.CartItem;
import com.shopping.models.Product;
import com.shopping.models.User;
import com.shopping.service.PromoCodeService;
import com.shopping.service.ShoppingCartService;
import com.shopping.ui.SwingConsole;


public class CartController {
	private final ShoppingCartService cartService;
	private final PromoCodeService promoService;
	
	public CartController(ShoppingCartService cartService, PromoCodeService promoService) {
		this.cartService = cartService;
		this.promoService = promoService;
	}
	
	public void manageCart(User user, SwingConsole console) {
        while (true) {
            console.println("\nCart Management:");
            console.println("1. Add Product");
            console.println("2. Remove Product");
            console.println("3. View Cart");
            console.println("4. Checkout");
            console.println("5. Back to Main Menu");
            console.println("Enter your choice: ");

            String choice = console.getInput();
            switch (choice) {
                case "1" -> {
                    console.println("Enter Product ID: ");
                    int productId = Integer.parseInt(console.getInput());
                    console.println("Enter Quantity: ");
                    int quantity = Integer.parseInt(console.getInput());
                    cartService.addProductToCart(user.getCart(), productId, quantity);
                }
                case "2" -> {
                    console.println("Enter Product ID: ");
                    int productId = Integer.parseInt(console.getInput());
                    cartService.removeProducts(user.getCart(), productId);
                }
                case "3" -> viewCart(user, console);
                case "4" -> {
                    double total = cartService.calculatetotal(user.getCart());
                    console.println("Total Price before discount: " + total);
                    
                    console.println("Enter Promo Code (or press Enter to skip): ");
                    String promoCode = console.getInput();
                    if (!promoCode.isEmpty()) {
                    	total = promoService.applyPromoCode(promoCode, total);
                    }
                    console.println("Total Price after discount: " + total);
                    console.println("Checkout complete. Thankyou for shopping!");
                    return;
                }
                case "5" -> {
                    return;
                }
                default -> console.println("Invalid choice. Try again.");
            }
        }
    }
	
	
	private void viewCart(User user, SwingConsole console) {
		String emptyCartArt = """
		          _____________________
		         |                     |
		         |   Your Shopping     |
		         |       Cart          |
		         |_____________________|
		               ||         ||
		         ------||---------||--
		        |                     |
		         ---------------------
		        """;

		    String fullCartArt = """
		          _____________________
		         |                     |
		         |   Your Shopping     |
		         |       Cart          |
		         |_____________________|
		               ||         ||
		         ------||---------||----
		        |  [x] [x] [x] [x]  [x] |
		         -----------------------
		        """;
		    
		    Map<Integer, CartItem> items = user.getCart().getItems();

		    
		    if (items.isEmpty()) {
		    	console.println(emptyCartArt);
		    	console.println("Your cart is empty!");
		    } else {
		    	console.printAsciiArt(fullCartArt);
		    	console.println("Items in your cart:");
		    	
		    	for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
		            CartItem cartItem = entry.getValue();
		            String productName = cartItem.getProduct().getName();
		            int quantity = cartItem.getQuantity();
		            double totalPrice = cartItem.getProduct().getPrice() * quantity;

		            console.println("- " + productName + " (x" + quantity + ") - $" + totalPrice);
		        }
		    	console.println("Total: $" + cartService.calculatetotal(user.getCart()));
		    }
		    console.printSeparator();
	}
}
