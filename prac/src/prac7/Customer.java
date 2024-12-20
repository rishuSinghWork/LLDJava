package prac7;

class Customer {
	private int uid;
	private String name;
	private String email;
	
	public Customer(int uid, String name, String email) {
		this.uid = uid;
		this.name = name;
		this.email = email;
	}
	
	public int getCustomerId() {
		return uid;
	}
	
	@Override 
	public String toString() {
		return "Customer{" +
				"customerId="+uid+
				", name='"+name+'\''+
				", email='"+email+'\''+
				'}';
	}
}
