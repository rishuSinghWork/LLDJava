package com.shopping.server;

import com.shopping.api.CartController;
import com.shopping.models.Product;
import com.shopping.models.User;
import com.shopping.repository.CartRepository;
import com.shopping.repository.ProductRepository;
import com.shopping.repository.UserRepository;
import com.shopping.service.PromoCodeService;
import com.shopping.service.ShoppingCartService;
import com.shopping.ui.SwingConsole;

//import java.util.Scanner;

public class ShoppingCartServer {
	private static final String WELCOME_ART = """
		      _   _      _ _
		     | | | |    | | |
		     | |_| | ___| | | ___
		     |  _  |/ _ \\ | |/ _ \\
		     | | | |  __/ | | (_) |
		     \\_| |_/\\___|_|_|\\___/
		    """;
	
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();
        CartRepository cartRepository = new CartRepository();
        UserRepository userRepository = new UserRepository();

        ShoppingCartService cartService = new ShoppingCartService(productRepository);
        PromoCodeService promoService = new PromoCodeService();
        CartController cartController = new CartController(cartService, promoService);

        //Scanner scanner = new Scanner(System.in);
        SwingConsole console = new SwingConsole("Shopping Cart System");
        console.setVisible(true);
        console.printAsciiArt(WELCOME_ART);
        console.println("Welcome to Shopping Cart System !");
        console.printSeparator();

        while (true) {
            console.println("\nMain Menu:");
            console.println("1. Manage Cart");
            console.println("2. Add New Product");
            console.println("3. Add New User");
            console.println("4. Exit");
            console.println("Choose an option: ");

//            int choice = scanner.nextInt();
//            scanner.nextLine(); // Consume the newline
            String choice = console.getInput();
            
            switch (choice) {
                case "1" -> {
                	console.println("Enter User ID: ");
                    int userId = Integer.parseInt(console.getInput());
                    User user = userRepository.getUserById(userId);
                    if (user != null) {
                        cartController.manageCart(user, console);
                    } else {
                    	console.println("User not found!");
                    }
                }
                case "2" -> {
                	console.println("Enter Product Name: ");
                    String name = console.getInput();
                    console.println("Enter Product Price: ");
                    double price = Double.parseDouble(console.getInput());                    
                    console.println("Enter Product Category: ");
                    String category = console.getInput();

                    productRepository.addProduct(new Product(0, name, price, category));
                }
                case "3" -> {
                	console.println("Enter User ID: ");
                    int userId = Integer.parseInt(console.getInput());
                    console.println("Enter User Name: ");
                    String userName = console.getInput();
                    User user = new User(userId, userName);
                    userRepository.saveUser(user);
                }
                case "4" -> {
                	console.println("Exiting. Goodbye!");
                    System.exit(0);
                }
                default -> console.println("Invalid choice. Try again.");
            }
        }
    }
}
