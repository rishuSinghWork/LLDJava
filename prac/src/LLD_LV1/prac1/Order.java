package LLD_LV1.prac1;

import java.util.ArrayList;
import java.util.List;

public class Order {
/**
 * structure :
 * 	- orderId
 * 	- customer 
 * 	- items <List>
 * 
 * getItems 
 * setItems 
 * return the object 
 */
	private int orderId;
	private Customer customer;
	private List<Item> items;
	
	public Order(int orderId, Customer customer, List<Item> items) {
		this.orderId = orderId;
		this.customer = customer;
		this.items = new ArrayList<>(items);
	}
	
	public List<Item> getItems(){
		return items;
	}
	
//	public void setItems(List<Item> items) {
//		this.items = items;
//	}
	public void addItem(Item item) {
		items.add(item);
		System.out.println("Item added : " + item);
	}
	
	public void removeItem(String itemName) {
		items.removeIf(item -> item.getItemName().equals(itemName));
		System.out.println("Item removed : "+ itemName);
	}
	
	@Override
	public String toString() {
		return "Order{"+
				"orderId='" + orderId +
				", customer=" + customer +
				", items=" + items + 
				'}';
	}
}
