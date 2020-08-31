package io.prathyusha.employeeservice.model;

import java.util.List;

public class EmployeeList {
	
	private List<Employee> listOfEmployees;

	public List<Employee> getListOfEmployees() {
		return listOfEmployees;
	}

	public void setListOfEmployees(List<Employee> listOfEmployees) {
		this.listOfEmployees = listOfEmployees;
	}

	public EmployeeList(List<Employee> listOfEmployees) {
		super();
		this.listOfEmployees = listOfEmployees;
	}

	public EmployeeList() {
		super();
		// TODO Auto-generated constructor stub
	}
		

}


