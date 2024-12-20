package com.hotelbooking.service;

import com.hotelbooking.models.Room;
import com.hotelbooking.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class RoomServiceTest {
	private RoomService roomService;
	private RoomRepository mockRoomRepository;
	
	@BeforeEach
	public void setUp() {
		mockRoomRepository = mock(RoomRepository.class);
		roomService = new RoomService(mockRoomRepository);
	}
	
	@Test
	public void testGetAvailableRooms() {
		when(mockRoomRepository.getAvailableRooms()).thenReturn(List.of(new Room(101, "Single", 100.0, true)));
		List<Room> rooms = roomService.getAvailableRooms();
		assertEquals(1, rooms.size());
		verify(mockRoomRepository, times(1)).getAvailableRooms();
	}
	
	@Test
	public void testAddRoom() {
		Room room = new Room(102, "Double", 150.0, true);
		roomService.addRoom(room);
		verify(mockRoomRepository, times(1)).addRoom(room);
	}
}
