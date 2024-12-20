package prac8;

import java.time.LocalDate;
import java.util.*;

class EventManagementService {
	private Map<Integer, Event> events = new HashMap<>();
	private Map<Integer, Attendee> attendees = new HashMap<>();
	private List<Ticket> tickets = new ArrayList<>();
	private int nextTicketId = 1;
	
	// Add a new event 
	public void addEvent(int eventId, String name, LocalDate date, String location, int capacity) {
		if(events.containsKey(eventId)) {
			System.out.println("Event ID already exists.");
			return;
		}
		events.put(eventId, new Event(eventId, name, date, location, capacity));
		System.out.println("Event added: " + name);
	}
	
	// Register a new event 
	public void registerAttendee(int attendeeId, String name, String contact) {
		if(attendees.containsKey(attendeeId)) {
			System.out.println("Attendee ID already exists.");
			return;
		}
		attendees.put(attendeeId, new Attendee(attendeeId, name, contact));
		System.out.println("Attendee registered: " + name);
	}
	
	// Issue a ticket to an attendee for a specific event 
	public void issueTicket(int eventId, int attendeeId) {
		Event event = events.get(eventId);
		Attendee attendee = attendees.get(attendeeId);
		
		if (event == null) {
			System.out.println("Event not found.");
			return;
		}
		if (attendee == null) {
			System.out.println("Attendee not found.");
			return;
		}
		if(event.isFull()) {
			System.out.println("Event is full. Cannot issue more tickets.");
			return;
		}
		
		// Check for duplicate tickets 
		boolean ticketExists = tickets.stream()
				.anyMatch(ticket -> ticket.getEvent().getEventId() == eventId && ticket.getAttendee().getAttendeeId() == attendeeId);
		
		if(ticketExists) {
			System.out.println("Duplicate ticket issuance is not allowed.");
			return;
		}
		Ticket ticket = new Ticket(nextTicketId++, event, attendee);
		tickets.add(ticket);
		event.incrementAttendees();
		System.out.println("Ticket issued: " + ticket);
	}
	
	// view attendees for a specific event 
	public void viewAttendeesForEvent(int eventId) {
		Event event = events.get(eventId);
		if (event == null) {
			System.out.println("Event not found.");
			return;
		}
		
		System.out.println("Attendees for Event: " + event);
		tickets.stream()
	        .filter(ticket -> ticket.getEvent().getEventId() == eventId)
	        .map(Ticket :: getAttendee)
	        .forEach(System.out::println);
	}
	
	// List all events attended by a specific attendee
	public void listEventsForAttendee(int attendeeId) {
		Attendee attendee = attendees.get(attendeeId);
		if (attendee == null) {
			System.out.println("Attendee not found.");
			return;
		}
		
		System.out.println("Events attended by " + attendee + ":");
		tickets.stream()
			.filter(ticket -> ticket.getAttendee().getAttendeeId() == attendeeId)
			.map(Ticket::getEvent)
			.forEach(System.out::println);
	}
}
