package prac2;

public class EmployeeDirectoryManagementSystem {

	public static void main(String[] args) {
		EmployeeDirectoryService directoryService = new EmployeeDirectoryService();
		
		// Creating Employees
		Employee emp1 = new Employee(101, "Alice", "Developer");
		Employee emp2 = new Employee(102, "Bob", "Designer");
		Employee emp3 = new Employee(103, "Charlie", "Manager");
		
		
		// Adding employees to departments
		directoryService.addEmployee("IT", emp1);
		directoryService.addEmployee("IT", emp2);
		directoryService.addEmployee("HR", emp3);
		
		// Retrieve and display an employee
		System.out.println("Retrieve Employee: " + directoryService.getEmployee("IT", 101));
		
		// List all employees in the department
		System.out.println("\nEmployees in the IT department");
		directoryService.listEmployeesInDepartment("IT");
		
		// List all departments and their employees
		System.out.println("\n All department and Employees:");
		directoryService.listAllDepartmentAndEmployees();
	}

}
