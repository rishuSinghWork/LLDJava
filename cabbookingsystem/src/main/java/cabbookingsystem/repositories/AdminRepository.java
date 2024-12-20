package cabbookingsystem.repositories;

import cabbookingsystem.models.Admin;
import cabbookingsystem.server.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository {
	private static final Logger logger = LoggerFactory.getLogger(AdminRepository.class);
	
	public Admin getAdminById(int id) throws SQLException {
		String query = "SELECT * FROM admins WHERE id = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()){
				if (resultSet.next()) {
					Admin admin = new Admin(resultSet.getInt("id"), resultSet.getString("name"));
					logger.info("Admin retrieved: {}", admin);
					return admin;
				}
			}
		}
		logger.warn("Admin with ID {} not found", id);
		return null;
	}
}
