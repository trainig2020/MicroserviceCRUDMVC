package io.prathyusha.departmentservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;


public class Employee {
	
	//@Id
	private int empId;
	private String empName;
	private int empAge;
	private int eDid;
	
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getEmpAge() {
		return empAge;
	}
	public void setEmpAge(int empAge) {
		this.empAge = empAge;
	}
	public int geteDid() {
		return eDid;
	}
	public void seteDid(int eDid) {
		this.eDid = eDid;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(int empId, String empName, int empAge, int eDid) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empAge = empAge;
		this.eDid = eDid;
	}
	
	
	
	
	
	
	
	
	
	
	

}
