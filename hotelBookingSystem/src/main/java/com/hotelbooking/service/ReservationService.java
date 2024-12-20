package com.hotelbooking.service;

import com.hotelbooking.models.Reservation;
import com.hotelbooking.repository.ReservationRepository;
import com.hotelbooking.models.Room;
import com.hotelbooking.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;

public class ReservationService {
	private final ReservationRepository reservationRepository;
	private final RoomRepository roomRepository;
	
	public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository) {
		this.reservationRepository = reservationRepository;
		this.roomRepository = roomRepository;
	}
	
	// add reservation
	public void addReservation(Reservation reservation) {
		reservationRepository.addReservation(reservation);
		System.out.println("Reservation added successfully: Reservation ID " + reservation.getReservationId());
	}
	// list all reservation 
	public List<Reservation> getAllReservations(){
		return reservationRepository.getAllReservations();
	}
	
	
	// cancel reservation 
	public void cancelReservation(int reservationId, LocalDate cancellationDate) {
		List<Reservation> reservations = getAllReservations();
		for(Reservation reservation : reservations) {
			if (reservation.getReservationId() == reservationId && !reservation.isCancelled()) {
				reservation.cancelreservation(cancellationDate);
				reservationRepository.addReservation(reservation);
				
				Room room = roomRepository.getAvailableRooms().stream()
						.filter(r -> r.getRoomId() == reservation.getRoomId())
						.findFirst()
						.orElse(null);
				if(room != null) {
					room.setAvaialble(true);
					roomRepository.addRoom(room);
				}
				
				System.out.println("Reservation cancelled. Refund: $" + reservation.getRefundAmount());
				return;
			}
		}
		System.out.println("Reservation ID " + reservationId + " not found or already cancelled.");
	}
}
