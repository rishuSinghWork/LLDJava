package cabbookingsystem.service;

import cabbookingsystem.models.Driver;
import cabbookingsystem.models.Passenger;
import cabbookingsystem.models.Ride;
import cabbookingsystem.repositories.RideRepository;
import cabbookingsystem.repositories.DriverRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class RideService {
	private static final Logger logger = LoggerFactory.getLogger(RideService.class);
	
	private final RideRepository rideRepository;
	private final DriverService driverService;
	private final PassengerService passengerService;
	
	public RideService(RideRepository rideRepository, DriverService driverService, PassengerService passengerService) {
		this.rideRepository = rideRepository;
		this.driverService = driverService;
		this.passengerService = passengerService;
	}
	
	// match passenger with nearest available driver 
	public Ride matchRide(Passenger passenger, String pickupLocation, String dropLocation) throws SQLException {
		List<Driver> availableDrivers = driverService.getAvaialableDrivers();
		if (availableDrivers.isEmpty()) {
			logger.warn("No available drivers for passenger ID {}", passenger.getId());
			return null;
		}
		
		Driver driver = availableDrivers.get(0); // why now use lambda function here ??
		Ride ride = new Ride(0, passenger, driver, pickupLocation, dropLocation, calculateFare(), "Assigned");
		rideRepository.addRide(ride);
		
		driverService.setAvailability(driver.getId(), false);
		logger.info("Ride ID {} assigned to driver ID {}", ride.getId(), driver.getId());
		
		return ride;
	} 
	
	// calculate fare (simplified)
	private double calculateFare() {
		// Implement fare calculation here properly placeholder for now
		return 20.0;
	}
	// assign driver to ride 
	public void assignDriverToRide(int rideId, int driverId) throws SQLException {
		Ride ride = rideRepository.getRideById(rideId);
		Driver driver = driverService.getDriverById(driverId);
		if (ride != null && driver != null && driver.isAvailable()) {
			ride.setDriver(driver);
			ride.setStatus("Ongoing");
			rideRepository.updateRide(ride);
			
			driverService.setAvailability(driverId, false);
			logger.info("Ride ID {} assigned to driver ID {}", rideId, driverId);
		} else {
			logger.warn("cannot assign driver ID {} to ride ID {}", driverId, rideId);
		}
	}
	
	// complete a ride 
	public void completeRide(int rideId) throws SQLException {
		Ride ride = rideRepository.getRideById(rideId);
		if (ride != null) {
			ride.setStatus("Completed");
			rideRepository.updateRide(ride);
			
			double fare = ride.getFare();
			driverService.updateEarnings(ride.getDriver().getId(), fare);
			driverService.setAvailability(ride.getDriver().getId(), true);
			
			logger.info("Ride ID {} completed", rideId);
		} else {
			logger.warn("Ride ID {} not found", rideId);
		}
	}
	// get ride by ID
	public Ride getRideById(int rideId) throws SQLException {
		return rideRepository.getRideById(rideId);
	}
}
