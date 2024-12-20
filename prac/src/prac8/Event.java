package prac8;

import java.time.LocalDate;

class Event {
	private int eventId;
	private String name;
	private LocalDate date;
	private String location;
	private int capacity;
	private int registeredAttendees;
	
	public Event(int eventId, String name, LocalDate date, String location, int capacity) {
		this.eventId = eventId;
		this.name = name;
		this.date = date;
		this.location = location;
		this.capacity = capacity;
		this.registeredAttendees = 0;
	}
	
	public int getEventId() {
		return eventId;
	}
	
	public boolean isFull() {
		return registeredAttendees >= capacity;
	}
	
	public void incrementAttendees() {
		registeredAttendees++;
	}
	
	 public String toString() {
		 return "Event{" +
				 "eventId="+eventId+
				 ", name'"+name+'\''+
				 ", date="+date+
				 ", location='"+location+'\''+
				 '}';
	 }
	 
}
