package LLD_LV1.prac1;

public class Item {
/**
 * -item name 
 * -price 
 * -quantity 
 * 
 * - return the item thingy 
 */
	private String itemName;
	private double price;
	private int quantity;
	
	public Item(String itemName, double price, int quantity) {
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}
	
	public String getItemName() {
		return itemName; 
	}
	
	@Override
	public String toString() {
		return "Item{" + 
				"ItemName='" + itemName + '\''+
				", price=" + price +
				", quantity=" + quantity + 
				'}';
	}
}
