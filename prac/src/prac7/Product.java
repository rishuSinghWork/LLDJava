package prac7;

class Product {
	private int productId;
	private String productName;
	private double price;
	
	public Product(int productId, String productName, double price) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String toString() {
		return "Product{"+
				"productId="+productId+
				", productName='"+productName+'\''+
				", price="+price+
				'}';
	}
} 
