package prac3;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * here we manage all rooms in the hotel
 * - adding rooms
 * - checking availability 
 * - booking rooms 
 * 
 * - addRoom(String roomId, int capacity)
 * - isRoomAvailable(String roomId, LocalDate date)
 * - bookRoom(String roomId, LocalDate date)
 * - displayAllRooms()
 */
public class HotelBookingService {
	private Map<String, Room> rooms = new HashMap<>();
	
	// method to add a room in the hotel 
	public void addRoom(String roomId, int capacity) {
		if (rooms.containsKey(roomId)) {
			System.out.println("Room Id already in use");
		} else {
			rooms.put(roomId, new Room(roomId, capacity));
			System.out.println("Room added successfully");
		}
	}
	
	// Method to check if a room is available on a specific date 
	public boolean isRoomAvailable(String roomId, LocalDate date) {
		Room room = rooms.get(roomId);
		if (room == null) {
			System.out.println("Room not found");
			return false;
		}
		return room.isAvailable(date);
	}
	
	// Method to book a room for a specific date if available 
	public boolean bookRoom(String roomId, LocalDate date) {
		Room room = rooms.get(roomId);
		if (room == null) {
			System.out.println("Room not found");
			return false;
		}
		if (room.isAvailable(date)) {
			room.bookDate(date);
			System.out.println("Room successfully booked for " + date);
			return true;
		} else {
			System.out.println("Room is already booked for this date.");
			return false;
		}
		
	}
	
	// Method to display all the rooms
	public void displayAllRooms() {
		if (rooms.isEmpty()) {
			System.out.println("No rooms available");
		} else {
			for (Room room : rooms.values()) {
				System.out.println(room);
			}
		}
	}
	
}
