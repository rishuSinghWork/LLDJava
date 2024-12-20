package hotelRoomBookingSystem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class Reservation {
	private int reservationId;
	private Customer customer;
	private Room room;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private double totalPrice;
	private boolean isCancelled;
	
	public Reservation(int reservationId, Customer customer, Room room, LocalDate checkInDate, LocalDate checkOutDate, double totalPrice) {
		this.reservationId = reservationId;
		this.customer = customer;
		this.room = room;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.totalPrice = totalPrice;
		this.isCancelled = false;
	}
	
	public void cancelReservation(LocalDate cancellationDate) {
		long daysBeforeCheckIn = ChronoUnit.DAYS.between(cancellationDate, checkInDate);
		double refundPercentage = daysBeforeCheckIn >= 7 ? 0.9 : (daysBeforeCheckIn >= 3 ? 0.5 : 0.0); 
		totalPrice *= refundPercentage;
		isCancelled = true;
	}
	
	public boolean isCancelled() {
		return isCancelled;
	}
	
	public int getReservationId() {
		return reservationId;
	}
	
	public Room getRoom() {
		return room;
	}
	
	public double getRefundAmount() {
        return totalPrice;
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
