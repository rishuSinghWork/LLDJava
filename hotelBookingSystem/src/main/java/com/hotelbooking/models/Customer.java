package com.hotelbooking.models;

public class Customer {
	private int customerId;
	private String name;
	private String email;
	private int loyaltyPoints;
	
	public Customer(int customerId, String name, String email) {
		this.customerId = customerId;
		this.name = name;
		this.email = email;
		this.loyaltyPoints = 0;
	}
	
	
	public int getCustomerId() {
		return customerId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}
	
	public void addLoyaltyPoints(int points) {
		loyaltyPoints += points;
	}
	
	public boolean redeemPoints(int points) {
		if (loyaltyPoints >= points) {
			loyaltyPoints -= points;
			return true;
		}
		return false;
	}
	
	@Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", loyaltyPoints=" + loyaltyPoints +
                '}';
    }
}
