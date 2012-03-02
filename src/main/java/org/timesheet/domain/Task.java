package org.timesheet.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task {

	private List<Employee> assignedEmployees = new ArrayList<Employee>();
	private Manager manager;
	
	public Task(Manager manager, Employee... employees) {
		this.manager = manager;
		assignedEmployees.addAll(Arrays.asList(employees));
	}

	public Manager getManager() {
		return manager;
	}
	
	public List<Employee> getAssignedEmployees() {
		return assignedEmployees;
	}
	
	public void addEmployee(Employee e) {
		assignedEmployees.add(e);
	}
	
	public void removeEmployee(Employee e) {
		assignedEmployees.remove(e);
	}
}
