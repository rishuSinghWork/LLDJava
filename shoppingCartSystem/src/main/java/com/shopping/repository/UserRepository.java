package com.shopping.repository;

import com.shopping.models.User;
import com.shopping.server.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepository {
	// save user 
		public void saveUser(User user) {
			String query = "INSERT INTO users (id, name) VALUES (?,?)";
			
			try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
				
				statement.setInt(1, user.getId());
				statement.setString(2, user.getName());
				statement.executeUpdate();
				
				System.out.println("user added succefully: " + user);
			} catch (SQLException e) {
				System.err.println("Error adding user to the database: " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		// get user by id
		public User getUserById(int userId) {
			String query = "SELECT * FROM users WHERE id = ?";
			
			try(Connection connection = DatabaseConnection.getConnection();
					PreparedStatement statement = connection.prepareStatement(query)){
				
				statement.setInt(1, userId);
				var rs = statement.executeQuery();
				
				if (rs.next()) {
					return new User(rs.getInt("id"), rs.getString("name"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
}
