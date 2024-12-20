package com.fooddelivery.repository;

import com.fooddelivery.models.Address;
import com.fooddelivery.models.Customer;
import com.fooddelivery.server.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
	// add customer 
	public void addCustomer(Customer customer) throws SQLException {
		String query = "INSERT INTO customers (name, email, street, city, state, zip_code) VALUES (?,?,?,?,?,?)";
		
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setString(1, customer.getName());
			statement.setString(2, customer.getEmail());
			statement.setString(3, customer.getAddress().getStreet());
			statement.setString(4, customer.getAddress().getCity());
			statement.setString(5, customer.getAddress().getState());
			statement.setString(6, customer.getAddress().getZipCode());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// get customer 
	public Customer getCustomerById(int id)throws SQLException {
		String query = "SELECT * FROM customers WHERE id = ?";
		
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				return new Customer (
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						new Address (
								rs.getString("street"),
								rs.getString("city"),
								rs.getString("state"),
								rs.getString("zip_code")
								)
						);
			}
		}
		return null;
	}
	
	// get all the customers 
	public List<Customer> getAllCustomers() throws SQLException {
		List<Customer> customers = new ArrayList<>();
		String query = "SELECT * FROM customers";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet rs = statement.executeQuery()){
			while (rs.next()) {
				Address address = new Address(
						rs.getString("string"),
						rs.getString("city"),
						rs.getString("state"),
						rs.getString("zip_code")
				);
				customers.add(new Customer (
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						address
				));
			}
		}
		return customers;
	}
}

