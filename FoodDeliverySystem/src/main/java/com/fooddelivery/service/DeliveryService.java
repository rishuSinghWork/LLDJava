package com.fooddelivery.service;

import com.fooddelivery.models.DeliveryPartner;
import com.fooddelivery.repository.DeliveryPartnerRepository;

import java.sql.SQLException;


public class DeliveryService {
	private final DeliveryPartnerRepository deliveryPartnerRepository;
	
	public DeliveryService(DeliveryPartnerRepository deliveryPartnerRepository) {
		this.deliveryPartnerRepository = deliveryPartnerRepository;
	}
	
	// add delivery partner 
	public void addDeliveryPartner(DeliveryPartner deliveryPartner) throws SQLException {
		deliveryPartnerRepository.addDeliveryPartner(deliveryPartner);
	}
	
	// update availability of a partner 
	public void updateDeliveryPartnerAvailablity(int id, boolean available) throws SQLException{
		deliveryPartnerRepository.updateDeliveryPartnerAvailability(id, available);
	}
	
	// assign the first available delivery partner 
	public DeliveryPartner assignDeliveryPartner() throws SQLException {
		return deliveryPartnerRepository.getAvailablePartner();
	}
}
