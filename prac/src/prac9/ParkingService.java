package prac9;

import java.util.*;

class ParkingService {
	private  Map<Integer, ParkingSpot> spots = new HashMap<>();
	private Map<String, ParkingTicket> activeTickets = new HashMap<>();
	private int nextTicketId = 1;
	
	// Add a parking spot 
	public void addParkingSpot(int spotId, String type) {
		if (spots.containsKey(spotId)) {
			System.out.println("Spot ID already exists.");
			return;
		}
		spots.put(spotId, new ParkingSpot(spotId, type));
		System.out.println("Parking spot added: "+spotId);
	}
	
	// Park a vehicle 
	public void parkVehicle(String licensePlate, String type) {
		Vehicle vehicle = new Vehicle(licensePlate, type);
		
		Optional<ParkingSpot> availableSpot = spots.values().stream()
			    .filter(spot -> spot.getType().equalsIgnoreCase(type) && !spot.isOccupied())
			    .findFirst(); 

		
		if(!availableSpot.isPresent()) {
			System.out.println("No available spots for " + type +".");
			return;
		}
		
		ParkingSpot spot = availableSpot.get();
		spot.occupy();
		ParkingTicket ticket = new ParkingTicket(nextTicketId++, vehicle, spot);
		activeTickets.put(licensePlate, ticket);
		System.out.println("Vehicle parked: "+vehicle+" in spot "+spot);
	}
	
	
	// un-park a vehicle 
	public void unparkVehicle(String licensePlate) {
		ParkingTicket ticket = activeTickets.remove(licensePlate);
		if(ticket == null) {
			System.out.println("No active ticket found for vehicle: " + licensePlate);
			return;
		}
		ticket.exit();
		ticket.getSpot().release();
		System.out.println("Vehicle unparked: "+ticket);
	}
	// View available spot 
	public void viewAvailableSpots(String  type) {
		System.out.println("Available spots for: " + type);
		spots.values().stream()
			.filter(spot -> spot.getType().equalsIgnoreCase(type)&& !spot.isOccupied())
			.forEach(System.out::println);
	}
}
