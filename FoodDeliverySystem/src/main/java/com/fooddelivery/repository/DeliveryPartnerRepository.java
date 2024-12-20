package com.fooddelivery.repository;

import com.fooddelivery.models.DeliveryPartner;
import com.fooddelivery.server.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryPartnerRepository {
	// add delivery partner 
	public void addDeliveryPartner(DeliveryPartner partner) throws SQLException {
		String query = "INSERT INTO delivery_partners (name, available) VALUES (?,?)";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setString(1, partner.getName());
			statement.setBoolean(2, partner.isAvailable());
			statement.executeUpdate();
		}
	}
	
	// get available partners
	public DeliveryPartner getAvailablePartner() throws SQLException {
		String query = "SELECT * FROM delivery_partners WHERE available = TRUE LIMIT 1";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
			ResultSet rs = statement.executeQuery()){
			if (rs.next()) {
				return new DeliveryPartner (
						rs.getInt("id"),
						rs.getString("name"),
						rs.getBoolean("available")
						);
			}
		}
		return null;
	}
	
	// get delivery partner by id 
	public DeliveryPartner getDeliveryPartnerById(int id) throws SQLException {
		String query = "SELECT * FROM delivery_partners WHERE id = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, id);
			try (ResultSet rs = statement.executeQuery()){
				if (rs.next()) {
					return new DeliveryPartner(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getBoolean("available")
							);
				}
			}
		}
		return null;
	}
	
	// update availability 
	public void updateDeliveryPartnerAvailability(int id, boolean available) throws SQLException {
        String query = "UPDATE delivery_partners SET available = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, available);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
    }
}

