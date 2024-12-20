package cabbookingsystem.repositories;

import cabbookingsystem.models.Driver;
import cabbookingsystem.server.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DriverRepository {
	private static final Logger logger = LoggerFactory.getLogger(DriverRepository.class);
	
	public void addDriver(Driver driver) throws SQLException {
		String query = "INSERT INTO drivers (name, car_details, availability) VALUES (?,?,?)";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			statement.setString(1, driver.getName());
			statement.setString(2, driver.getCarDetails());
			statement.setBoolean(3, driver.isAvailable());
			statement.executeUpdate();
			
			try (ResultSet generatedKeys = statement.getGeneratedKeys()){
				if (generatedKeys.next()) {
					driver.setId(generatedKeys.getInt(1));
					logger.info("Driver added with ID: {}", driver.getId());
				}
			}
		}
	}
	
	public Driver getDriverById(int id) throws SQLException {
		String query = "SELECT * FROM drivers WHERE id = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()){
				if (resultSet.next()) {
					Driver driver = new Driver(
							resultSet.getInt("id"),
							resultSet.getString("name"),
							resultSet.getString("car_details"),
							resultSet.getBoolean("availability")
							);
					driver.addEarnings(resultSet.getDouble("earnings"));
					driver.updateRatings(resultSet.getDouble("ratings"));
					logger.info("Driver retrieved: {}", driver);
					return driver;
				}
			}
		}
		logger.warn("Driver with ID {} not found", id);
		return null;
	}
	
	public List<Driver> getAllDrivers() throws SQLException {
		String query = "SELECT * FROM drivers";
		List<Driver> drivers = new ArrayList<>();
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery()){
			while (resultSet.next()) {
				Driver driver = new Driver(
						resultSet.getInt("id"),
						resultSet.getString("name"),
						resultSet.getString("car_details"),
						resultSet.getBoolean("availability")
						);
				driver.addEarnings(resultSet.getDouble("earnings"));
				driver.updateRatings(resultSet.getDouble("ratings"));
				drivers.add(driver);
			}
		}
		logger.info("All drivers retrieved: {}", drivers.size());
		return drivers;
	}
	
	public void updateDriverApproval(Driver driver) throws SQLException {
		String query = "UPDATE drivers SET approved = ? WHERE id = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setBoolean(1, driver.isApproved());
			statement.setInt(2, driver.getId());
			statement.executeUpdate();
			logger.info("Driver ID {} approval updated to {}", driver.getId(), driver.isApproved());
		}
	}
	
	public void updateDriverAvailability(Driver driver) throws SQLException {
		String query = "UPDATE drivers SET availability = ? WHERE id = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setBoolean(1, driver.isAvailable());
			statement.setInt(2, driver.getId());
			statement.executeUpdate();
			logger.info("Driver ID {} availability updated to {}", driver.getId(), driver.isAvailable());
		}
	}
	
	public void updateDriverEarnings(Driver driver) throws SQLException {
		String query = "UPDATE drivers SET earnings = ? WHERE id = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setDouble(1, driver.getEarnings());
			statement.setDouble(2, driver.getId());
			statement.executeUpdate();
			logger.info("Driver ID {} earnings updated to {}", driver.getId(), driver.getEarnings());
		}
	}
	
	public List<Driver> getAvailableDrivers() throws SQLException {
		String query = "SELECT * FROM drivers WHERE availability = true";
		List<Driver> drivers = new ArrayList<>();
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery()){
			while (resultSet.next()) {
				Driver driver = new Driver(
						resultSet.getInt("id"),
						resultSet.getString("name"),
						resultSet.getString("car_details"),
						true
				);
				driver.addEarnings(resultSet.getDouble("earnings"));
				driver.updateRatings(resultSet.getDouble("ratings"));
				drivers.add(driver);
			}
		}
		logger.info("Available drivers retrieved: {}", drivers.size());
		return drivers;
	}
	
	public void updateDriverRatings(Driver driver) throws SQLException {
		String query = "UPDATE drivers SET ratings = ? WHERE id = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setDouble(1, driver.getRatings());
			statement.setInt(2, driver.getId());
			statement.executeUpdate();
			logger.info("Driver ID {} ratings updated to {}", driver.getId(), driver.getRatings());
		}
	}
}
