package com.fooddelivery.api;

import com.fooddelivery.models.Customer;
import com.fooddelivery.service.CustomerService;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerController {
	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	// register Customer 
	public void registerCustomer(Scanner scanner) throws SQLException{
		System.out.println("Enter Customer ID: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter Cusotmer Name: ");
		String name = scanner.nextLine();
		System.out.println("Enter Customer Email: ");
		String email = scanner.nextLine();
		System.out.println("Enter Address Details: ");
		System.out.println("Street: ");
		String street = scanner.nextLine();
		System.out.println("City: ");
		String city = scanner.nextLine();
		System.out.println("State: ");
		String state = scanner.nextLine();
		System.out.println("Zip code: ");
		String zipCode = scanner.nextLine();
		
	
		customerService.registerCustomer(name, email, street, city, state, zipCode);
		System.out.println("Customer registered successfully!");
	}
	
	public void viewCustomerById(Scanner scanner) throws SQLException {
        System.out.println("Enter Customer ID:");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        Customer customer = customerService.getCustomerById(customerId);
        if (customer != null) {
            System.out.println(customer);
        } else {
            System.out.println("Customer not found.");
        }
    }
	
	public void viewAllCustomers() throws SQLException {
		customerService.getAllCustomers().forEach(System.out::println);
	}
}
