package com.fooddelivery.models;

public class DeliveryPartner {
	private final int id;
	private final String name;
	private boolean available;
	
	public DeliveryPartner(int id, String name, boolean available) {
		this.id = id;
		this.name = name;
		this.available = available;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	@Override
	public String toString() {
		return "DeliveryPartner{" +
				"id=" + id +
				", name='" + name + '\'' +
				", available=" + available +
				'}';
	}
}
