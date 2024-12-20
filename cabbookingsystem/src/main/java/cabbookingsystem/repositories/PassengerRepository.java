package cabbookingsystem.repositories;

import cabbookingsystem.models.Passenger;
import cabbookingsystem.server.DatabaseConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerRepository {
	private static final Logger logger = LoggerFactory.getLogger(PassengerRepository.class);
	
	public void addPassenger(Passenger passenger) throws SQLException {
		String query = "INSERT INTO passengers (name, email, location) VALUES (?,?,?)";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			statement.setString(1, passenger.getName());
			statement.setString(2, passenger.getEmail());
			statement.setString(3, passenger.getLocation());
			statement.executeUpdate();
			
			try (ResultSet generatedKeys = statement.getGeneratedKeys()){
				if (generatedKeys.next()) {
					passenger.setId(generatedKeys.getInt(1));
					logger.info("Passenger added with ID: {}", passenger.getId());
				}
			}
		}
	}
	
	public Passenger getPassengerById(int id) throws SQLException {
		String query = "SELECT * FROM passenger WHERE id = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()){
				if (resultSet.next()) {
					Passenger passenger = new Passenger(
							resultSet.getInt("id"),
							resultSet.getString("name"),
							resultSet.getString("email"),
							resultSet.getString("location")
							);
					logger.info("Passenger retrieved: {}", passenger);
					return passenger;
				}
			}
		}
		logger.warn("Passenger with ID {} not found", id);
		return null;
	}
	
	public List<Passenger> getAllPassengers() throws SQLException {
		String query = "SELECT * FROM passengers";
		List<Passenger> passengers = new ArrayList<>();
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery()){
			while (resultSet.next()) {
				Passenger passenger = new Passenger(
						resultSet.getInt("id"),
						resultSet.getString("name"),
						resultSet.getString("email"),
						resultSet.getString("location")
						);
				passengers.add(passenger);
			}
		}
		logger.info("All passengers retrived: {}", passengers.size());
		return passengers;
	}
}
