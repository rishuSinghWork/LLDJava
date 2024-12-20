package cabbookingsystem.server;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/cab_booking";
	private static final String DATABASE_USER = "root";
	private static final String DATABASE_PASSWORD = "Reeshu@24";
	
	private static final HikariDataSource dataSource;
	
	static {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(DATABASE_URL);
		config.setUsername(DATABASE_USER);
		config.setPassword(DATABASE_PASSWORD);
		
		config.setMaximumPoolSize(10);
		config.setMinimumIdle(2);
		config.setIdleTimeout(30000);
		config.setMaxLifetime(1800000);
		config.setConnectionTimeout(20000);
		config.setLeakDetectionThreshold(2000);
		
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2408");
		config.addDataSourceProperty("useServerPrepStmts", "true");
		
		dataSource = new HikariDataSource(config);
	}
	
	private DatabaseConnection() {}
	
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
	public static void close() {
		if (dataSource != null && !dataSource.isClosed()) {
			dataSource.close();
		}
	}
}
