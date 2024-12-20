package com.shopping.repository;

import com.shopping.models.Product;
import com.shopping.server.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
	// get all products 
	public List<Product> getAllProducts(){
		List<Product> products = new ArrayList<>();
		String query = "SELECT * FROM products";
		
		try (Connection connection = DatabaseConnection.getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)){
			
			while (rs.next()) {
				Product product = new Product (
						rs.getInt("id"),
						rs.getString("name"),
						rs.getDouble("price"),
						rs.getString("category")
						);
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
	
	// get product by id 
	public Product getProductById(int productId) {
		String query = "SELECT * FROM products WHERE id = ?";
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, productId);
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				return new Product(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getDouble("price"),
						rs.getString("category")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// add product to DB
	public void addProduct(Product product) {
		String query = "INSERT INTO products (name, price, category) VALUES (?,?,?)";
		
		try (Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query)){
				
				statement.setString(1, product.getName());
				statement.setDouble(2, product.getPrice());
				statement.setString(3, product.getCategory());
				statement.executeUpdate();
				
				System.out.println("Product added successfully: " + product);
		} catch (SQLException e) {
			System.err.println("Error adding product to the database: " + e.getMessage());
			e.printStackTrace();
		}
	}
}

