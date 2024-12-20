package prac7;

import java.util.List;

class Order {
	private int orderId;
	private Customer customer;
	private List<Product> products;
	private String status;
	private double totalPrice;
	private String paymentStatus = "pending";
	private String shippingAddress;
	
	public Order(int orderId, Customer customer, List<Product> products, String status, String shippingAddress) {
		this.orderId = orderId;
		this.customer = customer;
		this.products = products;
		this.status = status;
		this.shippingAddress = shippingAddress;
		this.totalPrice = calculateTotalPrice();
	}
	
	public double calculateTotalPrice() {
		return products.stream().mapToDouble(Product::getPrice).sum();
	}
	
	public int getOrderId() {
		return orderId;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	@Override
	public String toString(){
		return "Order{"+
				"orderId="+orderId+
				", customer="+customer+
				", products="+products+
				", status='"+status+'\''+
				", totalPrice="+totalPrice+
				", paymentStatus='"+paymentStatus+'\''+
				", shippingAddress='"+shippingAddress+'\''+
				'}';
	}
}

