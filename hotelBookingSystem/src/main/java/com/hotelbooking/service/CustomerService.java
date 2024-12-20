package com.hotelbooking.service;

import com.hotelbooking.models.Customer;
import com.hotelbooking.repository.CustomerRepository;

import java.util.Optional;

public class CustomerService {
	private final CustomerRepository customerRepository;
	
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public void addCustomer(Customer customer) {
		customerRepository.addCustomer(customer);
		System.out.println("Customer added successfully: " + customer.getName());
	}
	
	public Customer getCustomerById(int customerId) {
		Optional<Customer> customer = customerRepository.getCustomerById(customerId);
		if(customer.isPresent()) {
			return customer.get();
		} else {
			System.out.println("Customer with ID " + customerId + " not found.");
			return null;
		}
	}
	
	public void updateLoyaltyPoints(int customerId, int points) {
		Customer customer = getCustomerById(customerId);
		if (customer != null) {
			customer.addLoyaltyPoints(points);
			customerRepository.addCustomer(customer); // this can be improved 
			System.out.println("Loyalty points updated for Customer ID " + customerId);
		}
	}
}
