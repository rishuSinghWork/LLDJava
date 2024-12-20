package prac9;

class Vehicle {

	private String licensePlate;
	private String type;
	
	public Vehicle(String licensePlate, String type) {
		this.licensePlate = licensePlate;
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public String getLicensePlate() {
		return licensePlate;
	}
	
	
	@Override
	public String toString() {
		return "Vehicle{" +
				"licensePlate='" + licensePlate +'\'' +
				", type='" + type + '\''+
				'}';
	}
}
