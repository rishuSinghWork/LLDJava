package LLD_LV1.prac1;

public class Customer {
	
	private String name;
	private String email;
	private String address;
	
	// Constructor class
	public Customer(String name, String email, String address) {
		this.name = name;
		this.email = email;
		this.address = address;
	}
	
	// printing the object 
	@Override
	public String toString() {
		return "Customer{" + 
				"name='" + name + '\''+
				", email='" + email + '\''+
				", address='" + address + '\''+
				'}';
	}
/**
 * details of the customer 
 * -name 
 * -address
 * -email 
 * 
 * - it should return customer details 
 */
}


