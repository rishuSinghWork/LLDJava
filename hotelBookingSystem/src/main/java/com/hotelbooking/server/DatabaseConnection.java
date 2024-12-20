package com.hotelbooking.server;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/hotel_booking";
	private static final String USER = "root";
	private static final String PASSWORD = "Reeshu@24";
	private static HikariDataSource dataSource;
	
	
	static {
		try {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(URL);
			config.setUsername(USER);
			config.setPassword(PASSWORD);
			
			// other HikariCP
			config.setMaximumPoolSize(5);
			config.setMinimumIdle(2);
			config.setIdleTimeout(30000);
			config.setMaxLifetime(1800000);
			config.setConnectionTimeout(30000);
			
			dataSource = new HikariDataSource(config);
			
			System.out.println("Database connection pool initialized.");
		} catch (Exception e) {
			throw new RuntimeException("Error initializing the databaase connection pool", e);
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
