package prac9;

import java.time.Duration;
import java.time.LocalDateTime;

class ParkingTicket {

	private int ticketId;
	private Vehicle vehicle;
	private ParkingSpot spot;
	private LocalDateTime entryTime;
	private LocalDateTime exitTime;
	private double fee;
	
	public ParkingTicket(int ticketId, Vehicle vehicle, ParkingSpot spot) {
		this.ticketId = ticketId;
		this.vehicle = vehicle;
		this.spot = spot;
		this.entryTime = LocalDateTime.now();
	}
	
	public ParkingSpot getSpot() {
		return spot;
	}
	public void exit() {
		this.exitTime = LocalDateTime.now();
		this.fee = calculateFee();
	}
	
	private double calculateFee() {
		Duration duration = Duration.between(entryTime, exitTime);
		long hours = Math.max(1, duration.toHours());
		switch (vehicle.getType().toLowerCase()) {
		case "car" : return hours * 10;
		case "bike" : return hours * 5;
		case "truck" : return hours * 15;
		default : return hours * 8;
		}
	}
	
	@Override
	public String toString() {
		return "ParkingTicket{" +
				"ticketId=" + ticketId +
				", vehicle=" + vehicle +
				", spot=" + spot +
				", entryTIme=" + entryTime +
				", exitTIme=" + exitTime +
				", fee=" + fee +
				'}';
	}
}
