package prac2;

import java.util.HashMap;
import java.util.Map;

public class EmployeeDirectoryService {
	private Map<String, Map<Integer, Employee>> directory = new HashMap<>();
	/**
	 * directory -> [ departmentId , Department ]
	 * 		
	 * @param departmentId
	 * @param employee
	 */
	// Method to add an employee to a department 
	public void addEmployee(String departmentId, Employee employee) {
		// Get department map and create it if it does not exists 
		Map<Integer, Employee> departmentEmployees = directory.computeIfAbsent(departmentId, k -> new HashMap<>());
		
		// Add the employee to the department 
		if(departmentEmployees.containsKey(employee.getEmployeeId())) {
			System.out.println("Employee ID already exists in the department.");
		} else {
			departmentEmployees.put(employee.getEmployeeId(), employee);
			System.out.println("Employee addded successfully.");
		}
	}
	
	// Method to retrieve an employee by department ID and employee ID 
	public Employee getEmployee(String departmentId, int employeeId) {
		Map<Integer, Employee> departmentEmployees = directory.get(departmentId);
		if (departmentEmployees == null) {
			System.out.println("Department not found.");
			return null;
		}
		
		Employee employee = departmentEmployees.get(employeeId);
		if (employee == null) {
			System.out.println("Employee not found.");
		}
		return employee;
	}
	
	// Method to list all employees in a specific department
	public void listEmployeesInDepartment(String departmentId) {
		Map<Integer, Employee> departmentEmployees = directory.get(departmentId);
		if (departmentEmployees == null || departmentEmployees.isEmpty()) {
			System.out.println("No employee found in this department");
		} else {
			for (Employee employee : departmentEmployees.values()) {
				System.out.println(employee);
			}
		}
	}
	
	// Method to list all department and their employees
	public void listAllDepartmentAndEmployees() {
		if (directory.isEmpty()) {
			System.out.println("No department found.");
			return;
		}
		for (Map.Entry<String, Map<Integer, Employee>> departmentEntry : directory.entrySet()) {
			System.out.println("Department ID: " + departmentEntry.getKey());
			Map<Integer, Employee> departmentEmployees = departmentEntry.getValue();
			for (Employee employee : departmentEmployees.values()) {
				System.out.println(" " + employee);
			}
		}
	}
}
