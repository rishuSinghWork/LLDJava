package cabbookingsystem.service;

import cabbookingsystem.models.Driver;
import cabbookingsystem.models.Admin;
import cabbookingsystem.repositories.AdminRepository;
import cabbookingsystem.repositories.DriverRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class AdminService {
	private static final Logger logger = LoggerFactory.getLogger(AdminService.class);
	
	private final AdminRepository adminRepository;
	private final DriverRepository driverRepository;
	
	public AdminService(AdminRepository adminRepository, DriverRepository driverRepository) {
		this.adminRepository  = adminRepository;
		this.driverRepository = driverRepository;
	}
	
	// approve a driver Registration 
	public void approveDriver(int driverId) throws SQLException {
		Driver driver = driverRepository.getDriverById(driverId);
		if (driver != null) {
			driver.setApproval(true);
			driverRepository.updateDriverApproval(driver);
			logger.info("Driver ID {} approved", driverId);
		} else {
			logger.warn("Driver ID {} not found", driverId);
		}
	}
	// view ride stats
	public void viewRideStatistics() throws SQLException {
		// Implement logic to show ride stats
		logger.info("Ride Statistics viewed");
	}
	// monitor passenger reviews 
	public void monitorReviews() throws SQLException {
		// implement logic to fetch and monitor passenger reviews
		logger.info("Passenger reviews monitored");
	}
	// get admin by ID 
	public Admin getAdminById(int adminId) throws SQLException {
		return adminRepository.getAdminById(adminId);
	}
}
