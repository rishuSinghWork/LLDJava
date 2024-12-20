package com.fooddelivery.repository;

import com.fooddelivery.models.MenuItem;
import com.fooddelivery.server.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuItemsRepository {
	// add Menu item 
	public void addMenuItem(MenuItem menuItem, int restaurantId) throws SQLException {
		String query = "INSERT INTO menu_items (name, price, available,quantity, restaurant_id) VALUE (?,?,?,?,?)";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			//statement.setInt(1, menuItem.getId());
			statement.setString(1, menuItem.getName());
			statement.setDouble(2, menuItem.getPrice());
			statement.setBoolean(3, menuItem.isAvailable());
			statement.setInt(4, menuItem.getQuantity());
			statement.setInt(5, restaurantId);
			statement.executeUpdate();
		}
	}
	// get menu item by restaurant id 
	public List<MenuItem> getMenuItemsByRestaurantId(int restaurantId) throws SQLException {
		List<MenuItem> menuItems = new ArrayList<>();
		String query = "SELECT * FROM menu_items WHERE restaurant_id = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, restaurantId);
			try (ResultSet rs = statement.executeQuery()){
				while (rs.next()) {
					MenuItem menuItem = new MenuItem(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getDouble("price"),
							rs.getBoolean("available"),
							rs.getInt("quantity")
							);
					menuItems.add(menuItem);
				}
			}
		}
		return menuItems;
	}
	// update menu item availability 
	public void updatemenuItemAvailability(int menuItemId, boolean available) throws SQLException {
		String query = "UPDATE menu_items SET available = ? WHERE id = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setBoolean(1, available);
			statement.setInt(2, menuItemId);
			statement.executeUpdate();
		}
	}
	// remove menu item 
	public void removeMenuItem(int menuId) throws SQLException {
			String query = "DELETE FROM menu_items WHERE id = ?";
			try (Connection connection = DatabaseConnection.getConnection();
					PreparedStatement statement = connection.prepareStatement(query)){
				statement.setInt(1, menuId);
				statement.executeUpdate();
			}
		}
}
