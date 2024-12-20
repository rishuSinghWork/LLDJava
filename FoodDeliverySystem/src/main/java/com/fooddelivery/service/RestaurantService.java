package com.fooddelivery.service;

import com.fooddelivery.models.Address;
import com.fooddelivery.models.Restaurant;
import com.fooddelivery.repository.RestaurantRepository;

import java.sql.SQLException;
import java.util.List;

public class RestaurantService {
	private final RestaurantRepository restaurantRepository;
	
	public RestaurantService(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}
	
	public void registerRestaurant(String name, String street, String city, String state, String zipCode ) throws SQLException {
		Address address = new Address(street, city, state, zipCode);
		Restaurant restaurant = new Restaurant(0, name, address, List.of());
		restaurantRepository.addRestaurant(restaurant);
	}
	
	public void addMenuItem(int restaurantId, String itemName, double price, boolean available, int quantity) throws SQLException {
		restaurantRepository.addMenuItem(restaurantId, itemName, price, available, quantity);
	}
	
	public List<Restaurant> getAllRestaurants() throws SQLException {
		return restaurantRepository.getAllRestaurants();
	}
	
	public Restaurant getRestaurantById(int id) throws SQLException {
		return restaurantRepository.getRestaurantById(id);
	}
	
}
