package com.hotelbooking.repository;

import com.hotelbooking.models.Customer;
import com.hotelbooking.server.DatabaseConnection;

import java.sql.*;
import java.util.Optional;

public class CustomerRepository {
	public void addCustomer(Customer customer) {
		String query = "INSER INTO customers (cusotmer_id, name, email, loyalty_points) VALUES (?,?,?,?)";
		try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
				
				statement.setInt(1, customer.getCustomerId());
				statement.setString(2, customer.getName());
				statement.setString(3, customer.getEmail());
				statement.setInt(4, customer.getLoyaltyPoints());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Optional<Customer> getCustomerById(int customerId){
		String query = "SELCT * FROM customers WHERE customer_id = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, customerId);
			try (ResultSet resultSet = statement.executeQuery()){
				if (resultSet.next()) {
				return Optional.of(new Customer(
						resultSet.getInt("customer_Id"),
						resultSet.getString("name"),
						resultSet.getString("email")
						));
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	} 	
}
