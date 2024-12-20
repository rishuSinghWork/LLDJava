package hotelRoomBookingSystem;

class Room {

	private int roomId;
	private String type;
	private double basePrice;
	private boolean isAvailable;
	
	// constructor 
	public Room(int roomId, String type, double basePrice) {
		this.roomId = roomId;
		this.type = type;
		this.basePrice = basePrice;
		this.isAvailable = true;
	}
	
	public int getRoomId() {
		return roomId;
	}
	
	public String getRoomType() {
		return type;
	}
	
	public double getBasePrice() {
		return basePrice;
	}
	
	public boolean isAvailable() {
		return isAvailable;
	}
	
	public void setAvailable(boolean available) {
		isAvailable = available;
	}
	
	@Override
	public String toString() {
		return "Room{" +
				"roomId=" + roomId +
				", type='" + type + '\'' +
				", basePrice=" + basePrice +
				", isAvailable=" + isAvailable +
				'}';
	}
}
