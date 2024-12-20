package com.shopping.repository;

import com.shopping.models.CartItem;
import com.shopping.models.Product;
import com.shopping.server.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartRepository {
	// add cart items 
	public void addCartItem(int userId, CartItem cartItem) {
		String query = "INSERT INTO cart_items (user_id, product_id, quantity) VALUES (?,?,?) " +
				"ON DUPLICATE KEY UPDATE quantity = quantity + ?";
		
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, userId);
			statement.setInt(2, cartItem.getProduct().getId());
			statement.setInt(3, cartItem.getQuantity());
			statement.setInt(4, cartItem.getQuantity());
			statement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// get cart items by user id
	public List<CartItem> getCartItemsByUserId(int userId){
		String query = "SELECT ci.product_id, ci.quantity, p.name, p.price, p.category " +
                "FROM cart_items ci JOIN products p ON ci.product_id = p.id WHERE ci.user_id = ?";
		List<CartItem> cartItems = new ArrayList<>();
		
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			
			statement.setInt(1, userId);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				Product product = new Product(
						rs.getInt("product_id"),
						rs.getString("name"),
						rs.getDouble("price"),
						rs.getString("category")
						);
				
				CartItem cartItem = new CartItem(product,rs.getInt("quantity"));
				cartItems.add(cartItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cartItems;
	}
}

