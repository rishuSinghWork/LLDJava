package cabbookingsystem.models;

public class Driver {
	private int id;
	private String name;
	private String carDetails;
	private boolean availability;
	private double earnings;
	private double ratings;
	private boolean isApproved;
	
	public Driver (int id, String name, String carDetails, boolean availability) {
		this.id = id;
		this.name = name;
		this.carDetails = carDetails;
		this.availability = availability;
		this.earnings = 0.0;
		this.ratings = 0.0;
		this.isApproved = false;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCarDetails() {
		return carDetails;
	}
	
	public boolean isAvailable() {
		return availability;
	}
	
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	
	public double getEarnings() {
		return earnings;
	}
	
	public void addEarnings(double amount) {
		this.earnings += amount;
	}
	
	public double getRatings() {
		return ratings;
	}
	
	public void updateRatings(double newRating) {
		this.ratings = (this.ratings + newRating)/2;
	}
	
	public boolean isApproved() {
		return isApproved;
	}
	
	public void setApproval(boolean isApproved) {
		this.isApproved = isApproved;
	}
	@Override
	public String toString() {
		return "Driver{"+
				"id=" + id +
				", name='" + name + '\'' +
				", carDetails='" + carDetails + '\'' +
				", availability=" + availability +
				", earnings=" + earnings +
				", ratings=" + ratings +
				'}';
	}
}
