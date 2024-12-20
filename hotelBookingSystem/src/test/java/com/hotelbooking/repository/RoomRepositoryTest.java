package com.hotelbooking.repository;

import com.hotelbooking.models.Room;
import com.hotelbooking.server.DatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoomRepositoryTest {
	private RoomRepository roomRepository;
	
	@BeforeEach
	public void setUp() throws Exception {
		roomRepository = new RoomRepository();
		try(Connection connection = DatabaseConnection.getConnection();
				Statement statement = connection.createStatement()){
			statement.executeUpdate("DELETE FROM rooms");
			statement.executeUpdate("INSERT INTO rooms (room_id, type, base_price, is_available) VALUES (101, 'Single', 100.0, TRUE)");
		}
	}
	
	@Test
	public void testGetAvailableRooms() {
		List<Room> rooms = roomRepository.getAvailableRooms();
		assertEquals(1, rooms.size());
		assertEquals("Single", rooms.get(0).getType());
	}
	
	@Test
	public void testAddRoom() {
		Room room = new Room(102, "Double", 150.0, true);
		roomRepository.addRoom(room);
		
		List<Room> rooms = roomRepository.getAvailableRooms();
		assertEquals(2, rooms.size());
	}
}
