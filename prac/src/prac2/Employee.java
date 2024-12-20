package prac2;

public class Employee {
	private int employeeId;
	private String name;
	private String position;
	
	public Employee(int employeeId, String name, String position) {
		this.employeeId = employeeId;
		this.name = name;
		this.position = position;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	
	@Override
	public String toString() {
		return "Employee{" +
				"employeeId=" + employeeId +
				", name ='" + name + '\'' +
				", position='" + position + '\'' +
				'}';
	}

}
