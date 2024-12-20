package com.fooddelivery.repository;

import com.fooddelivery.models.Restaurant;
import com.fooddelivery.models.Address;
import com.fooddelivery.models.MenuItem;
import com.fooddelivery.server.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository {
	private final MenuItemsRepository menuItemsRepository;
	
	public RestaurantRepository(MenuItemsRepository menuItemsRepository) {
		this.menuItemsRepository = menuItemsRepository;
	}
	
	// add restaurants
	public void addRestaurant (Restaurant restaurant) throws SQLException {
		 String query = "INSERT INTO restaurants (name, street, city, state, zip_code) VALUES (?,?,?,?,?)";
		 try (Connection connection = DatabaseConnection.getConnection();
				 PreparedStatement statement = connection.prepareStatement(query)){
			 statement.setString(1, restaurant.getName());
			 statement.setString(2, restaurant.getLocation().getStreet());
			 statement.setString(3, restaurant.getLocation().getCity());
			 statement.setString(4, restaurant.getLocation().getState());
			 statement.setString(5, restaurant.getLocation().getZipCode());
			 statement.executeUpdate();
		 }
	}
	
	// add menu items to restaurant 
	public void addMenuItem(int restaurantId, String itemName, double price, boolean available, int quantity) throws SQLException {
		String query = "INSERT INTO menu_items (name, price, available, quantity, restaurant_id) VALUES ()?,?,?,?,?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setString(1, itemName);
			statement.setDouble(2, price);
			statement.setBoolean(3, available);
			statement.setInt(4, quantity);
			statement.setInt(5, restaurantId);
			statement.executeUpdate();
		}
	}
	
	// remove restaurant 
		public void removeRestaurant(int id) throws SQLException {
			String query = "DELETE FROM restaurants WHERE id = ?";
			try (Connection connection = DatabaseConnection.getConnection();
					PreparedStatement statement = connection.prepareStatement(query)){
				statement.setInt(1, id);
				statement.executeUpdate();
			}
		}
	
	// get all restaurants 
	public List<Restaurant> getAllRestaurants() throws SQLException {
		List<Restaurant> restaurants = new ArrayList<>();
		String query = "SELECT * FROM restaurants";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet rs = statement.executeQuery()){
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Address address = new Address(
						rs.getString("street"),
						rs.getString("city"),
						rs.getString("state"),
						rs.getString("zip_code")
						);
				List<MenuItem> menu = getMenuItemsByRestaurantId(id);
				restaurants.add(new Restaurant(id, name, address, menu));
			}
		}
		return restaurants;
	}
	
	// get menu by restaurant id 
	private List<MenuItem> getMenuItemsByRestaurantId(int restaurantId) throws SQLException {
		List<MenuItem> menu = new ArrayList<>();
		String query = "SELECT * FROM menu_items WHERE restaurant_id= ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, restaurantId);
			
			try(ResultSet rs = statement.executeQuery()){
				while (rs.next()) {
					menu.add(new MenuItem(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getDouble("price"),
							rs.getBoolean("available"),
							rs.getInt("quantity")
					));
				}
			}
		}
		return menu;
	}
	
	
	// get restaurants by id 
	public Restaurant getRestaurantById(int id) throws SQLException {
		String query = "SELECT * FROM restaurants WHERE id = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, id);
			try (ResultSet rs = statement.executeQuery()){
				if (rs.next()) {
					List<MenuItem> menu = menuItemsRepository.getMenuItemsByRestaurantId(id);
					
					return new Restaurant(
							rs.getInt("id"),
							rs.getString("name"),
							new Address(
									rs.getString("street"),
									rs.getString("city"),
									rs.getString("state"),
									rs.getString("zip_code")
									),
							menu
							);
				}
			}
		}
		return null;
	}
	
}
