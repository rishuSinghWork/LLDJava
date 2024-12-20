package com.fooddelivery.models;

public class Customer {
	private final int id;
	private final String email;
	private final String name;
	private final Address address;
	
	public Customer(int id, String name, String email, Address address) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Address getAddress() {
		return address;
	}
	
	@Override
	public String toString() {
		return "Customer{" +
				"id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", address=" + address +
				'}';
	}
}

