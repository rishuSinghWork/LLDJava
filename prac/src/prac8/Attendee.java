package prac8;

class Attendee {
	private int attendeeId;
	private String name;
	private String contact;
	
	public Attendee(int attendeeId, String name, String contact) {
		this.attendeeId = attendeeId;
		this.name = name;
		this.contact = contact;
	}
	
	public int getAttendeeId() {
		return attendeeId;
	}
	
	@Override
	public String toString() {
		return "Attendee{" +
				"attendeeId=" + attendeeId +
				", name='" + name + '\'' +
				", contact='" + contact + '\''+
				'}';
	}
}

