package com.hotelbooking.repository;

import com.hotelbooking.models.Reservation;
import com.hotelbooking.server.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {
	public void addReservation(Reservation reservation) {
		String query = "INSERT INTO reservations (reservation_id, customer_id, room_id, check_in_date, check_out_date, total_price, is_cancelled) VALUES (?, ?, ? ,?, ?, ?, ?)";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			
			statement.setInt(1, reservation.getReservationId());
			statement.setInt(2, reservation.getCustomerId());
			statement.setInt(3, reservation.getRoomId());
			statement.setDate(4, Date.valueOf(reservation.getCheckInDate()));
			statement.setDate(5, Date.valueOf(reservation.getCheckOutDate()));
			statement.setDouble(6, reservation.getTotalPrice());
			statement.setBoolean(7, reservation.isCancelled());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Reservation> getAllReservations(){
		List<Reservation> reservations = new ArrayList<>();
		String query = "SELECT * FROM reservations";
		
		try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery()){
			
			while (resultSet.next()) {
				Reservation reservation = new Reservation(
						resultSet.getInt("reservation_id"),
						resultSet.getInt("customer_id"),
						resultSet.getInt("room_id"),
						resultSet.getDate("check_in_date").toLocalDate(),
						resultSet.getDate("check_out_date").toLocalDate(),
						resultSet.getDouble("total_price")
						);
				reservations.add(reservation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservations;
	}
}
