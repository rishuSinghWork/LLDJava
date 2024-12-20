package com.hotelbooking.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {
	private Room room;
	
	@BeforeEach
	public void setUp() {
		room = new Room(101, "Single", 100.0, true);
	}
	
	@Test
	public void testRoomCreation() {
		assertEquals(101, room.getRoomId());
		assertEquals("Single", room.getType());
		assertEquals(100.0, room.getBasePrice(), 0.01);
		assertTrue(room.isAvaialble());
	}
	
	@Test
	public void testSetAvailability() {
		room.setAvaialble(false);
		assertFalse(room.isAvaialble());
	}
}
