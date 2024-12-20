package com.fooddelivery.models;

public class MenuItem {
	private final int id;
	private final String name;
	private final double price;
	private boolean available;
	private int quantity;
	
	public MenuItem(int id, String name, double price, boolean available, int quantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.available = available;
		this.quantity = quantity;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "MenuItem{" + 
				"id=" + id +
				", name='" + name + '\'' +
				", price=" + price + 
				", available=" + available + 
				'}';
	}
}

