package com.hotelbooking.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
	private Customer customer;
	
	@BeforeEach
	public void setUp() {
		customer = new Customer(1, "Alice", "alice@example.com");
	}
	
	@Test
	public void testCustomerCreation() {
		assertEquals(1, customer.getCustomerId());
		assertEquals("Alice", customer.getName());
		assertEquals("alice@example.com", customer.getEmail());
		assertEquals(0, customer.getLoyaltyPoints());
	}
	
	@Test
	public void testAddLoyaltyPoints() {
		customer.addLoyaltyPoints(50);
		assertEquals(50, customer.getLoyaltyPoints());
	}
	
	@Test
	public void testRedeemLoyaltyPoints() {
		customer.addLoyaltyPoints(100);
		customer.redeemPoints(50);
		assertEquals(50, customer.getLoyaltyPoints());
	}
}
