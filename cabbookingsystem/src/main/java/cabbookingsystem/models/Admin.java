package cabbookingsystem.models;

public class Admin {
	private int id;
	private String name;
	
	public Admin(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "Admin{" + 
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
