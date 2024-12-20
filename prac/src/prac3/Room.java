package prac3;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * things it has to have 
 * 	- UID
 * 	- capacity
 * 	- List of booked dates 
 * 
 * 
 * methods it needs 
 * 	- check if room is available on the date 
 * 	- book room if it is available 
 */
class Room {
	private String roomId;
	private int capacity;
	private Set<LocalDate> bookedDates;
	
	// constructor 
	public Room (String roomId, int capacity) {
		this.roomId = roomId;
		this.capacity = capacity;
		this.bookedDates = new HashSet<>();
	}
	
	public String getRoomId() {
		return roomId;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public Set<LocalDate> getBookedDates(){
		return bookedDates;
	}
	
	public boolean isAvailable(LocalDate date) {
		return !bookedDates.contains(date);
	}
	
	public void bookDate(LocalDate date) {
		bookedDates.add(date);
	}
	
	@Override
	public String toString() {
		return "Room{" +
				"roomId='" + roomId + '\'' +
				", capacity='" + capacity + 
				", bookedDates=" + bookedDates +
				'}';
	}
}
