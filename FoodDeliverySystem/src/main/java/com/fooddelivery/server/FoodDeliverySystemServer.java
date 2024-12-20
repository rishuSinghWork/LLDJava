package com.fooddelivery.server;

import com.fooddelivery.api.CustomerController;
import com.fooddelivery.api.OrderController;
import com.fooddelivery.api.RestaurantController;
import com.fooddelivery.api.DeliveryController;
import com.fooddelivery.models.Customer;
import com.fooddelivery.repository.CustomerRepository;
import com.fooddelivery.repository.DeliveryPartnerRepository;
import com.fooddelivery.repository.MenuItemsRepository;
import com.fooddelivery.repository.OrderRepository;
import com.fooddelivery.repository.RestaurantRepository;
import com.fooddelivery.service.CustomerService;
import com.fooddelivery.service.DeliveryService;
import com.fooddelivery.service.OrderService;
import com.fooddelivery.service.RestaurantService;

import java.sql.SQLException;
import java.util.Scanner;

public class FoodDeliverySystemServer {
    private final RestaurantController restaurantController;
    private final CustomerController customerController;
    private final OrderController orderController;
    private final DeliveryController deliveryController;
    private final CustomerService customerService;

    public FoodDeliverySystemServer(RestaurantController restaurantController, CustomerController customerController,
                                    OrderController orderController, DeliveryController deliveryController,
                                    CustomerService customerService) {
        this.restaurantController = restaurantController;
        this.customerController = customerController;
        this.orderController = orderController;
        this.deliveryController = deliveryController;
        this.customerService = customerService;
    }

    public void start(Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("\nFood Order Delivery System:");
            System.out.println("1. Manage Restaurants");
            System.out.println("2. Manage Customers");
            System.out.println("3. Manage Delivery Partners");
            System.out.println("4. Place an Order");
            System.out.println("5. View Order Details");
            System.out.println("6. Exit");
            System.out.println("Enter your choice:");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> manageRestaurants(scanner);
                case "2" -> manageCustomers(scanner);
                case "3" -> manageDeliveryPartners(scanner);
                case "4" -> placeOrder(scanner);
                case "5" -> viewOrderDetails(scanner);
                case "6" -> {
                    System.out.println("Exiting Food Order Delivery System. Thank you!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void manageRestaurants(Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("\nRestaurant Management:");
            System.out.println("1. Register Restaurant");
            System.out.println("2. Add Menu Items");
            System.out.println("3. View All Restaurants");
            System.out.println("4. Back to Main Menu");
            System.out.println("Enter your choice:");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> restaurantController.registerRestaurant(scanner);
                case "2" -> restaurantController.addMenuItems(scanner);
                case "3" -> restaurantController.viewAllRestaurants();
                case "4" -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void manageCustomers(Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("\nCustomer Management:");
            System.out.println("1. Register Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. Back to Main Menu");
            System.out.println("Enter your choice:");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> customerController.registerCustomer(scanner);
                case "2" -> customerController.viewAllCustomers();
                case "3" -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void manageDeliveryPartners(Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("\nDelivery Partner Management:");
            System.out.println("1. Register Delivery Partner");
            System.out.println("2. Update Delivery Partner Availability");
            System.out.println("3. Assign Delivery Partner");
            System.out.println("4. Back to Main Menu");
            System.out.println("Enter your choice:");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> deliveryController.registerDeliveryPartner(scanner);
                case "2" -> deliveryController.updateAvailability(scanner);
                case "3" -> deliveryController.assignDeliveryPartner();
                case "4" -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void placeOrder(Scanner scanner) throws SQLException {
        System.out.println("Enter Customer ID:");
        int customerId = Integer.parseInt(scanner.nextLine());

        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Invalid Customer ID.");
        } else {
            orderController.placeOrder(customer, scanner);
        }
    }
    
    private void viewOrderDetails(Scanner scanner) throws SQLException {
    	orderController.viewOrderDeatails(scanner);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Setting up repositories
            CustomerRepository customerRepository = new CustomerRepository();
            RestaurantRepository restaurantRepository = new RestaurantRepository(new MenuItemsRepository());
            DeliveryPartnerRepository deliveryPartnerRepository = new DeliveryPartnerRepository();
            OrderRepository orderRepository = new OrderRepository(customerRepository, restaurantRepository, deliveryPartnerRepository);

            // Setting up services
            CustomerService customerService = new CustomerService(customerRepository);
            RestaurantService restaurantService = new RestaurantService(restaurantRepository);
            DeliveryService deliveryService = new DeliveryService(deliveryPartnerRepository);
            OrderService orderService = new OrderService(orderRepository, deliveryPartnerRepository);

            // Setting up controllers
            RestaurantController restaurantController = new RestaurantController(restaurantService);
            CustomerController customerController = new CustomerController(customerService);
            OrderController orderController = new OrderController(orderService, restaurantService);
            DeliveryController deliveryController = new DeliveryController(deliveryService);

            // Starting the server
            FoodDeliverySystemServer server = new FoodDeliverySystemServer(restaurantController, customerController, orderController, deliveryController, customerService);
            server.start(scanner);
        } catch (SQLException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
