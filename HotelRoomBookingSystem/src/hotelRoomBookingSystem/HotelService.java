package hotelRoomBookingSystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

class HotelService {

	// Data structures to hold data 
	private Map<Integer, Room> rooms = new ConcurrentHashMap<>();
	private Map<Integer, Customer> customers = new HashMap<>();
	private List<Reservation> reservations = Collections.synchronizedList(new ArrayList<>());
	private AtomicInteger nextReservationId = new AtomicInteger(1);
	
	
	// Add a room to hotel
	public void addRoom(int roomId, String type, double basePrice) {
		rooms.put(roomId, new Room(roomId, type, basePrice));
		System.out.println("Room added: " + roomId);
	}
	
	// Register a customer 
	public void registerCustomer(int customerId, String name, String email) {
		customers.put(customerId, new Customer(customerId, name, email));
		System.out.println("Customer registered: " + name);
	}
	
	// Book a room 
	public void bookRoom(int customerId, String roomType, LocalDate checkInDate, LocalDate checkOutDate) {
		Customer customer = customers.get(customerId);
		if(customer == null) {
			System.out.println("Customer not found.");
			return;
		}
		
		Room availableRoom = rooms.values().stream()
				.filter(room -> room.getRoomType().equalsIgnoreCase(roomType) && room.isAvailable())
				.findFirst()
				.orElse(null);
		
		if(availableRoom == null) {
			System.out.println("No available room of type: " + roomType);
			return;
		}
		
		availableRoom.setAvailable(false);
		double totalPrice = calculateDynamicPrice(availableRoom.getBasePrice(), checkInDate, checkOutDate);
		Reservation reservation = new Reservation(nextReservationId.getAndIncrement(), customer, availableRoom, checkInDate, checkOutDate, totalPrice);
		reservations.add(reservation);
		
		int loyaltyPoints = (int)(totalPrice / 10);
		customer.addLoyaltyPoints(loyaltyPoints);
		
		System.out.println("Room booked successfully: " + reservation);
		System.out.println("Loyalty Points Earned: " + loyaltyPoints);
	}
	
	// Cancel a reservation
	public void cancelReservation(int reservationId, LocalDate cancellationDate) {
		synchronized (reservations) {
			for (Reservation reservation : reservations) {
				if (reservation.getReservationId() == reservationId && !reservation.isCancelled()) {
					reservation.cancelReservation(cancellationDate);
					reservation.getRoom().setAvailable(true);
					System.out.println("Reservation cancelled: " + reservationId);
					System.out.println("Refund Amount: $" + reservation.getRefundAmount());
					return;
				}
			}
		}
		System.out.println("Reservation not found or already cancelled.");
	}
	
	// Calculate dynamic pricing
	private double calculateDynamicPrice(double basePrice, LocalDate checkInDate, LocalDate checkOutDate) {
		long days = java.time.temporal.ChronoUnit.DAYS.between(checkInDate, checkOutDate);
		double dynamicFactor = 1.0;
		
		// High price on weekend 
		if (checkInDate.getDayOfWeek() == DayOfWeek.FRIDAY || checkInDate.getDayOfWeek() == DayOfWeek.SATURDAY)
			dynamicFactor += 0.2;
		
		// Discount on low demand period 
		if (checkInDate.isBefore(LocalDate.of(2024, 12, 15)))
			dynamicFactor -= 0.1;
		
		return basePrice * days * dynamicFactor;
	}
	
	// View all reservations
	public void viewReservations() {
		reservations.forEach(System.out::println);
	}
}
