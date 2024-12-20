package cabbookingsystem.repositories;

import cabbookingsystem.models.Ride;
import cabbookingsystem.models.Driver;
import cabbookingsystem.models.Passenger;
import cabbookingsystem.server.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RideRepository {
	private static final Logger logger = LoggerFactory.getLogger(RideRepository.class);
	
	public void addRide(Ride ride) throws SQLException {
		String query = "INSERT INTO rides (passenger_id, driver_id, pickup_location, drop_location, fare, status) VALUES (?,?,?,?,?,?)";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			statement.setInt(1, ride.getPassenger().getId());
			statement.setInt(2, ride.getDriver() != null ? ride.getDriver().getId() : null);
			statement.setString(3, ride.getPickupLocation());
			statement.setString(4, ride.getDropLocation());
			statement.setDouble(5, ride.getFare());
			statement.setString(6, ride.getStatus());
			statement.executeUpdate();
			
			try (ResultSet generatedKeys = statement.getGeneratedKeys()){
				if (generatedKeys.next()) {
					ride.setId(generatedKeys.getInt(1));
					logger.info("Ride added with ID: {}", ride.getId());
				}
			}
		}
	}
	
	public List<Ride> getRideByPassengerId(int passengerId) throws SQLException {
		String query = "SELECT * FROM rides WHERE passenger_id = ?)";
		List<Ride> rides = new ArrayList<>();
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, passengerId);
			try (ResultSet resultSet = statement.executeQuery()){
				while (resultSet.next()) {
					rides.add(new Ride(
							resultSet.getInt("id"),
							null,
							null,
							resultSet.getString("pickup_location"),
							resultSet.getString("drop_location"),
							resultSet.getDouble("fare"),
							resultSet.getString("status")
							));
				}
			}
		}
		logger.info("Rides retrieved for passenger ID {}: {}", passengerId, rides.size());
		return rides;
	}
	
	public void assignDriverToRide(int rideId, int driverId) throws SQLException {
		String query  = "UPDATE rides SET driver_id = ?, status = 'Ongoing' WHERE id = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, driverId);
			statement.setInt(2, rideId);
			statement.executeUpdate();
			logger.info("Driver ID {} asssigned to Ride ID {}", driverId, rideId);
		}
	}
	
	public Ride getRideById(int rideId) throws SQLException {
		String query = "SELECT * FROM rides WHERE id = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, rideId);
			try (ResultSet resultSet = statement.executeQuery()){
				if (resultSet.next()) {
					Ride ride = new Ride(
							resultSet.getInt("id"),
							null,
							null,
							resultSet.getString("pickup_location"),
							resultSet.getString("drop_location"),
							resultSet.getDouble("fare"),
							resultSet.getString("status")
							);
					logger.info("Ride retrieved: {}", ride);
					return ride;
				}
			}
		}
		logger.warn("Ride ID {} not found", rideId);
		return null;
	}
	
	public void updateRide(Ride ride) throws SQLException {
		String query = "UPDATE rides SET driver_id = ?, status = ?, fare = ? WHERE id = ?";
		try (Connection connection  = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, ride.getDriver() != null ? ride.getDriver().getId() : null);
			statement.setString(2, ride.getStatus());
			statement.setDouble(3, ride.getFare());
			statement.setInt(4, ride.getId());
			statement.executeUpdate();
			logger.info("Ride ID {} updated", ride.getId());
		}
	}
}
