package com.hotelbooking.repository;

import com.hotelbooking.models.Room;
import com.hotelbooking.server.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RoomRepository {
	public List<Room> getAvailableRooms(){
		List<Room> rooms = new ArrayList<>();
		String query = "SELECT * FROM rooms WHERE is_available = TRUE";
		
		try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery()){
			
			while(resultSet.next()) {
				Room room = new Room(
						resultSet.getInt("room_id"),
						resultSet.getString("type"),
						resultSet.getDouble("base_price"),
						resultSet.getBoolean("is_available")
						);
				rooms.add(room);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rooms;
	}
	
	public void addRoom(Room room) {
		String query = "INSERT INTO rooms (room_id, type, base_price, is_available) VALUES (?, ?, ?, ?)";
		
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, room.getRoomId());
			statement.setString(2, room.getType());
			statement.setDouble(3, room.getBasePrice());
			statement.setBoolean(4, room.isAvaialble());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
