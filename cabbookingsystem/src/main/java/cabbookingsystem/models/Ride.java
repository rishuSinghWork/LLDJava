package cabbookingsystem.models;

public class Ride {
	private int id;
	private Passenger passenger;
	private Driver driver;
	private String pickupLocation;
	private String dropLocation;
	private double fare;
	private String status;
	
	public Ride(int id, Passenger passenger, Driver driver, String pickupLocation, String dropLocation, double fare ,String status) {
		this.id = id;
		this.passenger = passenger;
		this.driver = driver;
		this.pickupLocation = pickupLocation;
		this.dropLocation = dropLocation;
		this.fare = fare;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
		// maybe wont ever need tit 
	}
	
	public Passenger getPassenger() {
		return passenger;
	}
	
	public Driver getDriver() {
		return driver;
	}
	
	public void setDriver(Driver driver) {
		this.driver= driver;
	}
	
	public String getPickupLocation() {
		return pickupLocation;
	}
	
	public String getDropLocation() {
		return dropLocation;
	}
	
	public double getFare() {
		return fare;
	}
	
	public void setFare(double fare) {
		this.fare = fare;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Ride{" + 
				"id=" + id +
				", passenger=" + passenger +
				", driver=" + driver +
				", pickupLocation='" + pickupLocation + '\'' +
				", dropLocation='" + dropLocation +'\'' +
				", fare=" + fare +
				", status='" + status + '\'' +
				'}';
	}
}
