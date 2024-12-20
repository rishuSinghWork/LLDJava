package com.shopping.service;

import com.shopping.models.Cart;
import com.shopping.models.Product;
import com.shopping.repository.ProductRepository;

public class ShoppingCartService {
	private final ProductRepository productRepository;
	
	public ShoppingCartService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	// add product to cart 
	public void addProductToCart(Cart cart, int productId, int quantity) {
		Product product = productRepository.getProductById(productId);
		if (product != null) {
			cart.addItem(product, quantity);
		} else {
			System.out.println("Product not found.");
		}
	}
	// remove product from cart 
	public void removeProducts(Cart cart, int productId) {
		cart.removeItem(productId);
	}
	
	// give total amount 
	public double calculatetotal(Cart cart) {
		return cart.calculateTotalPrice();
	}
}
