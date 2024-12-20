package com.hotelbooking.service;

import com.hotelbooking.models.Customer;
import com.hotelbooking.models.Reservation;
import com.hotelbooking.models.Room;
import com.hotelbooking.repository.CustomerRepository;
import com.hotelbooking.repository.ReservationRepository;
import com.hotelbooking.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;

public class HotelService {
	private final RoomService roomService;
	private final CustomerService customerService;
	private final ReservationService reservationService;
	
	public HotelService() {
		this.roomService = new RoomService(new RoomRepository());
		this.customerService = new CustomerService(new CustomerRepository());
		this.reservationService = new ReservationService(new ReservationRepository(), new RoomRepository());
	}
	
	// Load data from database into memory 
	public void initialize() {
		System.out.println("Loading data into memory...");
		roomService.getAvailableRooms();	// load rooms 
		reservationService.getAllReservations();	// load reservation 
	}
	
	public RoomService getRoomService() {
		return roomService;
	}
	
	// add a new room 
	public void addRoom(Room room) {
		roomService.addRoom(room);
	}
	
	// fetch available rooms 
	public List<Room> getAvailableRooms(){
		return roomService.getAvailableRooms();
	}
	
	// add a new customer 
	public void addCustomer(Customer customer) {
		customerService.addCustomer(customer);
	}
	
	// Book a room for a customer 
	public void bookRoom(int customerId, String roomType, LocalDate checkInDate, LocalDate checkOutDate) {
		Customer customer = customerService.getCustomerById(customerId);
		if(customer == null) {
			System.out.println("Customer not found");
			return;
		}
		
		Room availableRoom = roomService.getAvailableRooms().stream()
				.filter(room -> room.getType().equalsIgnoreCase(roomType) && room.isAvaialble())
				.findFirst()
				.orElse(null);
		
		if(availableRoom == null) {
			System.out.println("No available room of type: " + roomType);
			return;
		}
		
		availableRoom.setAvaialble(false);
		roomService.addRoom(availableRoom);
		
		double totalPrice = calculateDynamicPrice(availableRoom.getBasePrice(), checkInDate, checkOutDate);
		
		Reservation reservation = new Reservation(
				reservationService.getAllReservations().size()+1,
				customerId,
				availableRoom.getRoomId(),
				checkInDate,
				checkOutDate,
				totalPrice
				);
		reservationService.addReservation(reservation);
		
		int loyaltyPoints = (int) (totalPrice / 10);
		customerService.updateLoyaltyPoints(customerId, loyaltyPoints);
		
		System.out.println("Room booked successfully: " + reservation);
	}
	
	
	public void cancelReservation(int reservationId, LocalDate cancellationDate) {
		reservationService.cancelReservation(reservationId, cancellationDate);
	}
	
	public List<Reservation> getReservations(){
		return reservationService.getAllReservations();
	}
	
	private double calculateDynamicPrice(double basePrice, LocalDate checkInDate, LocalDate checkOutDate) {
		long days = java.time.temporal.ChronoUnit.DAYS.between(checkInDate, checkOutDate);
		double multiplier = checkInDate.getDayOfWeek().getValue() >= 5 ? 1.2 : 1.0;
		return basePrice * multiplier;
	}
}
