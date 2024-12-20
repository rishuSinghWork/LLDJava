package com.hotelbooking.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.mockito.Mockito.*;

import com.hotelbooking.models.Room;
import com.hotelbooking.service.RoomService;
class RoomControllerTest {

	private RoomService mockRoomService;
	private RoomController roomController;
	
	@BeforeEach
	public void setUp() {
		mockRoomService = mock(RoomService.class);
		roomController = new RoomController(mockRoomService);
	}
	
	@Test
	public void testManageRooms_AddRoom() {
		String input = "1\n101\nSingle\n100\n3\n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		roomController.manageRooms();
		verify(mockRoomService, times(1)).addRoom(any(Room.class));
	}
	
	@Test
	public void testManageRooms_ViewAvailableRoom() {
		when(mockRoomService.getAvailableRooms()).thenReturn(List.of(new Room(101, "Single", 100.0, true)));
		String input = "2\n3\n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		roomController.manageRooms();
		verify(mockRoomService, times(1)).getAvailableRooms();
	}
}
