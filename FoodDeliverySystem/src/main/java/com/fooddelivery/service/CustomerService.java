package com.fooddelivery.service;

import com.fooddelivery.models.Address;
import com.fooddelivery.models.Customer;
import com.fooddelivery.repository.CustomerRepository;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {
	private final CustomerRepository customerRepository;
	
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public void registerCustomer(String name, String email, String street, String city, String state, String zipCode) throws SQLException {
		Address address = new Address(street, city, state, zipCode);
		Customer customer = new Customer(0, name, email, address);
		customerRepository.addCustomer(customer);
	}
	
	public Customer getCustomerById(int id) throws SQLException {
		return customerRepository.getCustomerById(id);
	}
	
	public List<Customer> getAllCustomers() throws SQLException {
		return customerRepository.getAllCustomers();
	}
}
