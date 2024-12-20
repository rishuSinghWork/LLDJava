package prac3;
import java.time.LocalDate;
/**
 * For testing the thingy 
 */
public class HotelBookingSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HotelBookingService bookingService = new HotelBookingService();
		//Adding rooms
		bookingService.addRoom("101", 2);
		bookingService.addRoom("102", 3);
		
		// Check Availability of the room
		LocalDate date = LocalDate.of(2023, 12, 25);
		
		System.out.println("Room 101 is avaialble on "+ date + ": " + bookingService.isRoomAvailable("101", date));
		bookingService.bookRoom("101", date);
		System.out.println("Room 101 is available on "+ date + " after booking: " + bookingService.isRoomAvailable("101", date));
		
		
		// Attempt to book the room on the same date again 
		bookingService.bookRoom("101", date);
		
		// Display all the rooms and their booking 
		System.out.println("\nAll Rooms: ");
		bookingService.displayAllRooms();
		
	}

}
