package com.fooddelivery.service;

import com.fooddelivery.models.DeliveryPartner;
import com.fooddelivery.models.Order;
import com.fooddelivery.repository.DeliveryPartnerRepository;
import com.fooddelivery.repository.OrderRepository;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
	private final OrderRepository orderRepository;
	private final DeliveryPartnerRepository deliveryPartnerRepository;
	
	public OrderService(OrderRepository orderRepository, DeliveryPartnerRepository deliveryPartnerRepository) {
		this.orderRepository = orderRepository;
		this.deliveryPartnerRepository = deliveryPartnerRepository;
	}
	
	// Place a new order
	public void placeOrder(Order order) throws SQLException {
		orderRepository.addOrder(order);
	}
	
	// retrieve an order by id 
	public Order getOrderById(int id) throws SQLException {
		return orderRepository.getOrderById(id);
	}
	
	// retrieve all orders 
	public List<Order> getAllOrders() throws SQLException {
		return orderRepository.getAllOrders();
	}
	
	// update status of an order 
	public void updateOrderStatus(int orderId, String status) throws SQLException {
		orderRepository.updateOrderStatus(orderId, status);
	}
	
	// assign a delivery partner to an order 
	public boolean assignDeliveryPartner(Order order) throws SQLException {
		DeliveryPartner availablePartner = deliveryPartnerRepository.getAvailablePartner();
		if (availablePartner != null) {
			availablePartner.setAvailable(false);
			deliveryPartnerRepository.updateDeliveryPartnerAvailability(availablePartner.getId(), false);
			order.setDeliveryPartner(availablePartner);
			return true;
		}
		return false;
	}
}
