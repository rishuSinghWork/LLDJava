package com.hotelbooking.api;

import com.hotelbooking.models.Room;
import com.hotelbooking.service.RoomService;

import java.util.Scanner;

public class RoomController {
	private final RoomService roomService;
	
	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}
	
	public void manageRooms() {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("\nRoom Management");
			System.out.println("1. Add Room");
			System.out.println("2. View Available Rooms");
			System.out.println("3. Exit");
			System.out.println("Choose an option: ");
			int choice = scanner.nextInt();
			
			switch(choice) {
			case 1 -> addRoom(scanner);
			case 2 -> viewAvailableRooms();
			case 3 -> {
				return;
			}
			default -> System.out.println("Invalid choice. Try again.");
			}
		}
	}
	
	private void viewAvailableRooms() {
		roomService.getAvailableRooms().forEach(System.out::println);
	}
	
	public void addRoom(Scanner scanner) {
		System.out.println("Enter Room ID: ");
		int roomId = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter Room Type (Single, Double, Suite): ");
		String type = scanner.nextLine();
		System.out.println("Enter Base Price: ");
		double basePrice = scanner.nextDouble();
		
		Room room = new Room(roomId, type, basePrice, true);
		roomService.addRoom(room);
		System.out.println("Room added successfully.");
	}
}
