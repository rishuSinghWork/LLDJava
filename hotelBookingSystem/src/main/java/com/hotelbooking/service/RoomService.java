package com.hotelbooking.service;

import com.hotelbooking.models.Room;
import com.hotelbooking.repository.RoomRepository;

import java.util.List;

public class RoomService {
	private final RoomRepository roomRepository;
	
	public RoomService(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}
	
	public void addRoom(Room room) {
		roomRepository.addRoom(room);
	}
	
	public List<Room> getAvailableRooms(){
		return roomRepository.getAvailableRooms();
	}
}

