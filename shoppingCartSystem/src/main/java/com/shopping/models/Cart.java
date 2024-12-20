package com.shopping.models;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	private final Map<Integer, CartItem> items = new HashMap<>();
	
	public void addItem(Product product, int quantity) {
		items.putIfAbsent(product.getId(), new CartItem(product, 0));
		CartItem cartItem = items.get(product.getId());
		cartItem.setQuantuty(cartItem.getQuantity() + quantity);
	}
	
	public void removeItem(int productId) {
		items.remove(productId);
	}
	
	public double calculateTotalPrice() {
		return items.values().stream().mapToDouble(CartItem::getTotalPrice).sum();
	}
	
	public Map<Integer, CartItem> getItems(){
		return items;
	}
	
	@Override
	public String toString() {
		return "Cart{" +
				"items=" + items +
				'}';
	}
}
