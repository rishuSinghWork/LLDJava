package cabbookingsystem.models;

import java.util.ArrayList;
import java.util.List;

public class Passenger {
	private int id;
	private String name;
	private String email;
	private String location;
	private List<Ride> rideHistory;
	
	public Passenger(int id, String name, String email, String location) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.location = location;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		// should not be implement as using auto increment.
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public List<Ride> getRideHistory(){
		return rideHistory;
	}
	
	public void addRide(Ride ride) {
		this.rideHistory.add(ride);
	}
	
	@Override
	public String toString() {
			return "Passenger{" +
					"id=" + id + 
					", name='" + name + '\'' +
					", email='" + email + '\'' +
					", location='" + location + '\'' +
					", rideHistory=" + rideHistory +
					'}';
	}
}
