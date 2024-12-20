package com.fooddelivery.models;

import java.util.List;

public class Order {
	private final int id;
	private final Customer customer;
	private final Restaurant restaurant;
	private DeliveryPartner deliveryPartner;
	private final List<MenuItem> items;
	private String status;
	
	public Order(int id, Customer customer, Restaurant restaurant, DeliveryPartner deliveryPartner, List<MenuItem> items, String status) {
		this.id = id;
		this.customer = customer;
		this.restaurant = restaurant;
		this.deliveryPartner = deliveryPartner;
		this.items = items;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	public DeliveryPartner getDeliveryPartner() {
		return deliveryPartner;
	}
	
	public List<MenuItem> getItems() {
		return items;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void updateStatus(String status) {
		this.status = status;
	}
	
	public void setDeliveryPartner(DeliveryPartner deliveryPartner) {
		this.deliveryPartner = deliveryPartner;
	}
	
	@Override
	public String toString() {
		return "Order{" + 
				"id=" + id + 
				", customer=" + customer +
				", restaurant=" + restaurant +
				", deliveryPartner=" + deliveryPartner +
				", items=" + items +
				", status='" + status + '\'' +
				'}';
	}
}
