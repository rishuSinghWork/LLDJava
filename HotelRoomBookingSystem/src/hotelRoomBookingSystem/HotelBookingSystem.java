package hotelRoomBookingSystem;

import java.time.LocalDate;

public class HotelBookingSystem {

	public static void main(String[] args) {
		HotelService service = new HotelService();
		
		// Add rooms
		service.addRoom(101, "Single", 100);
		service.addRoom(102, "Double", 150);
		service.addRoom(103, "Suit", 250);
		
		// Register Customers
		service.registerCustomer(1, "Alice", "alice@example.com");
        service.registerCustomer(2, "Bob", "bob@example.com");
        
		// Book rooms
        service.bookRoom(1, "Single", LocalDate.of(2024, 12, 1), LocalDate.of(2024, 12, 5));
        service.bookRoom(2, "Double", LocalDate.of(2024, 12, 3), LocalDate.of(2024, 12, 6));
		
        //View reservations
		System.out.println("\nReservations:");
		service.viewReservations();
		
		// Cancel reservation
		service.cancelReservation(1, LocalDate.of(2024, 11, 25));
		
		// View reservation after cancellation 
		System.out.println("\nReservatios after calcellation:");
		service.viewReservations();
	}

}
