package com.hotelbooking.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
	private int reservationId;
	private Customer customer;
	private Room room;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private double totalPrice;
	private boolean isCancelled;
	private int customerId;
	private int roomId;
	
	public Reservation(int reservationId, int customerId, int roomId, LocalDate checkInDate, LocalDate checkOutDate, double totalPrice) {
		this.reservationId = reservationId;
		this.customerId = customerId;
		this.roomId = roomId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.totalPrice = totalPrice;
		this.isCancelled = false;
	}
	
	public boolean isCancelled() {
		return isCancelled;
	}
	
	public int getReservationId() {
		return reservationId;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public Room getRoom() {
		return room;
	}
	
	public LocalDate getCheckInDate() {
		return checkInDate;
	}
	
	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}
	
	public double getRefundAmount() {
		return totalPrice;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public int getCustomerId() {
		return getCustomerId();
	}
	
	public int getRoomId() {
		return getRoomId();
	}
	
	public void cancelreservation(LocalDate cancellationDate) {
		long daysBeforeCheckIn = ChronoUnit.DAYS.between(cancellationDate, checkInDate);
		double refundPercentage = daysBeforeCheckIn >= 7 ? 0.9 : (daysBeforeCheckIn >= 3 ? 0.5: 0.0);
		totalPrice += refundPercentage;
		isCancelled = true;
	}
	
	@Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", customer=" + customer +
                ", room=" + room +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", totalPrice=" + totalPrice +
                ", isCancelled=" + isCancelled +
                '}';
    }
}
