package cabbookingsystem.service;

import cabbookingsystem.models.Driver;
import cabbookingsystem.repositories.DriverRepository;
import cabbookingsystem.repositories.PassengerRepository;
import cabbookingsystem.repositories.RideRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class DriverService {
	private static final Logger logger = LoggerFactory.getLogger(DriverService.class);
	
	private final DriverRepository driverRepository;
	
	public DriverService(DriverRepository driverRepository) {
		this.driverRepository = driverRepository;
	}
	
	// Register a new driver 
	public Driver regiterDriver(String name, String carDetails) throws SQLException {
		Driver driver = new Driver(0, name, carDetails, true);
		driverRepository.addDriver(driver);
		logger.info("Driver registered: {}", driver);
		return driver;
	}
	
	// Set driver availability 
	public void setAvailability(int driverId, boolean availability) throws SQLException {
		Driver driver = driverRepository.getDriverById(driverId);
		if (driver != null) {
			driver.setAvailability(availability);
			driverRepository.updateDriverAvailability(driver);
			logger.info("Driver ID {} availability set to {}", driverId, availability);
		} else {
			logger.info("Driver ID {} not found", driverId);
		}
	}
	
	// Accept a ride 
	public void acceptRide(int driverId, int rideId) throws SQLException {
		RideService rideService = new RideService(new RideRepository(), this, new PassengerService(new PassengerRepository(), new RideRepository()));
		rideService.assignDriverToRide(rideId, driverId);
		logger.info("Driver ID {} accepted ride ID {}", driverId, rideId);
	}
	
	// Update driver earnings 
	public void updateEarnings(int driverId, double amount) throws SQLException {
		Driver driver = driverRepository.getDriverById(driverId);
		if (driver != null) {
			driver.addEarnings(amount);
			driverRepository.updateDriverEarnings(driver);
			logger.info("Driver ID {} earnings updated by ${}", driverId, amount);
		} else {
			logger.warn("Driver ID {} not found", driverId);
		}
	}
	// Update driver ratings 
	public void updateDriverRating(int driverId, double rating) throws SQLException {
		Driver driver = driverRepository.getDriverById(driverId);
		if (driver != null) {
			driver.updateRatings(rating);
			driverRepository.updateDriverRatings(driver);
			logger.info("Driver ID {} rating updated to {}", driverId);
		} else {
			logger.warn("Driver ID {} not found");
		}
	}
	// get all available drivers 
	public List<Driver> getAvaialableDrivers() throws SQLException {
		List<Driver> drivers = driverRepository.getAvailableDrivers();
		logger.info("Retrieved {} available drivers", drivers.size());
		return drivers;
	}
	// get driver by ID
	public Driver getDriverById(int driverId) throws SQLException {
		return driverRepository.getDriverById(driverId);
	}
}
