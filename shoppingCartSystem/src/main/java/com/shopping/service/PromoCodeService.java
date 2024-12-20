package com.shopping.service;

import java.util.HashMap;
import java.util.Map;


public class PromoCodeService {
	private final Map<String, Double> promoCodes = new HashMap<>();
	
	public PromoCodeService() {
		promoCodes.put("DISCOUNT10", 0.10);
		promoCodes.put("DISCOUNT20", 0.20);
	}
	
	public double applyPromoCode(String code, double total) {
		if (promoCodes.containsKey(code)) {
			double discount = promoCodes.get(code) * total;
			System.out.println("Promo applied! Discount: " + discount);
			return total - discount;
		} else {
			System.out.println("Invalid PromoCode.");
			return total;
		}
	}
}
