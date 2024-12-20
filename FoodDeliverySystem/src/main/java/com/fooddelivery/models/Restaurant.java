package com.fooddelivery.models;

import java.util.List;

public class Restaurant {
	private final int id;
	private final String name;
	private final Address location;
	private final List<MenuItem> menu;
	
	public Restaurant(int id, String name, Address location, List<MenuItem> menu) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.menu = menu;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Address getLocation() {
		return location;
	}
	
	public List<MenuItem> getMenu(){
		return menu;
	}
	
	@Override
	public String toString() {
		return "Restaurant{" +
				"id=" + id +
				", name='" + name + '\'' +
				", location=" + location +
				", menu=" + menu +
				'}';
	}
}
