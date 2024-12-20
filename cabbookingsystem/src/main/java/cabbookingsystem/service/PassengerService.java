package cabbookingsystem.service;

import cabbookingsystem.models.Passenger;
import cabbookingsystem.models.Ride;
import cabbookingsystem.repositories.DriverRepository;
import cabbookingsystem.repositories.PassengerRepository;
import cabbookingsystem.repositories.RideRepository;
import cabbookingsystem.server.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class PassengerService {
	private static final Logger logger = LoggerFactory.getLogger(PassengerService.class);
	
	private final PassengerRepository passengerRepository;
	private final RideRepository rideRepository;
	
	public PassengerService(PassengerRepository passengerRepository, RideRepository rideRepository) {
		this.passengerRepository = passengerRepository;
		this.rideRepository = rideRepository;
	}
	
	public Passenger registerPassenger(String name, String email, String location) throws SQLException {
		Passenger passenger = new Passenger(0, name, email, location);
		passengerRepository.addPassenger(passenger);
		logger.info("Passenger registered: {}", passenger);
		return passenger;
	}
	
	public Ride bookRide(Passenger passenger, String pickupLocation, String dropLocation) throws SQLException {
		Ride ride = new Ride(0, passenger, null, pickupLocation, dropLocation, 0.0, "Pending");
		// Fare logic to be added 
		rideRepository.addRide(ride);
		logger.info("Ride booked: {}", ride);
		return ride;
	}
	
	public List<Ride> viewRideHistory(int passengerId) throws SQLException {
		List<Ride> rides = rideRepository.getRideByPassengerId(passengerId);
		logger.info("Ride history for passenger ID {}: {} rides", passengerId, rides.size());
		return rides;
	}
	
	public void rateDriver(int driverId, double rating) throws SQLException {
		DriverService driverService = new DriverService(new DriverRepository());
		driverService.updateDriverRating(driverId, rating);
		logger.info("Driver ID {} rated with {}", driverId, rating);
	}
	
	// get passenger by ID 
	public Passenger getPassengerById(int id) throws SQLException {
		return passengerRepository.getPassengerById(id);
	}
}
