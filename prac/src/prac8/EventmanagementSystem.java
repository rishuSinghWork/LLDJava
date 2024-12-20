package prac8;
import java.time.LocalDate;
public class EventmanagementSystem {

	public static void main(String[] args) {
		EventManagementService service = new EventManagementService();
		
		// Add events
		service.addEvent(1, "Tech Conference", LocalDate.of(2024, 12, 1), "New York",2);
		service.addEvent(2, "Art Festival", LocalDate.of(2024, 12, 5), "Paris",1);
		
		// Register Attendees
		service.registerAttendee(101, "Alice", "alice@example.com");
        service.registerAttendee(102, "Bob", "bob@example.com");
        service.registerAttendee(103, "Charlie", "charlie@example.com");

		// Issue Tickets
		service.issueTicket(1, 101);
		service.issueTicket(1, 102);
		service.issueTicket(2, 101);
		service.issueTicket(1, 101);
		service.issueTicket(2, 102);
		
		// View Attendees for a Tech Conference 
		System.out.println("\nTech Conference Attendees:");
		service.viewAttendeesForEvent(1);
		
		// View Attendee for a Art festival
		System.out.println("\nArt Festival Attendees:");
		service.viewAttendeesForEvent(2);
		
		// List events for Alice
		System.out.println("\nEvents attended by Alice.");
		service.listEventsForAttendee(101);
	}

}
