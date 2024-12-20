package prac8;

class Ticket {
	private int ticketId;
	private Event event;
	private Attendee attendee;
	
	public Ticket(int ticketId, Event event, Attendee attendee) {
		this.ticketId = ticketId;
		this.event = event;
		this.attendee = attendee;
	}
	
	public Event getEvent() {
        return event;
    }

    public Attendee getAttendee() {
        return attendee;
    }
	@Override
	public String toString() {
		return "Ticket{" +
				"ticketId=" + ticketId + 
				", event=" + event +
				", attendee=" + attendee +
				'}';
	}
}

