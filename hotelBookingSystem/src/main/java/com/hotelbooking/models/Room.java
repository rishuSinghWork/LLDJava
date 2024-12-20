package com.hotelbooking.models;

public class Room {
	private int roomId;
	private String type;
	private double basePrice;
	private boolean isAvailable;
	
	public Room(int roomId, String type, double basePrice, boolean isAvailable) {
		this.roomId = roomId;
		this.type = type;
		this.basePrice = basePrice;
		this.isAvailable = isAvailable;
	}
	
	public int getRoomId() {
		return roomId;
	}
	
	public String getType() {
		return type;
	}
	
	public double getBasePrice() {
		return basePrice;
	}
	
	public boolean isAvaialble() {
		return isAvailable;
	}
	
	public void setAvaialble(boolean isAvailable) {
		this.isAvailable = isAvailable;
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
