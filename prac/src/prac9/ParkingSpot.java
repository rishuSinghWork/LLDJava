package prac9;

class ParkingSpot {

	private int spotId;
	private String type;
	private boolean isOccupied;
	
	public ParkingSpot(int spotId, String type) {
		this.spotId = spotId;
		this.type = type;
		this.isOccupied = false;
	}
	
	public int getSpotId() {
		return spotId;
	}
	
	public String getType() {
		return type;
	}
	
	public boolean isOccupied() {
		return isOccupied;
	}
	
	public void occupy() {
		this.isOccupied = true;
	}
	
	public void release() {
		this.isOccupied = false;
	}
	
	@Override
	public String toString() {
		return "ParkingSpot{" + 
				"spotId=" + spotId +
				", type='" + type + '\'' +
				", isOccupied=" + isOccupied +
				'}';
	}
}
