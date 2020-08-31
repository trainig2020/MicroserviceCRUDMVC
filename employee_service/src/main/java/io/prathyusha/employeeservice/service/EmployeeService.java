package io.prathyusha.employeeservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.prathyusha.employeeservice.model.Employee;
import io.prathyusha.employeeservice.model.EmployeeList;
import io.prathyusha.employeeservice.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository  employeeRepository;
	
	public EmployeeList getAllEmployess(int edid){
		List<Employee> emplist = employeeRepository.findAllByEdId(edid);
		EmployeeList lst=new EmployeeList();
		lst.setListOfEmployees(emplist);
		return lst;
	}
	public Optional<Employee> GetEmployee(int empid) {
		return employeeRepository.findById(empid);
	}

	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	  public void deleteEmployee(int eDid) { 
		  employeeRepository.deleteByEdId(eDid);
	  } 
	  public void deleteEmployeeByEdid(int eDid,int empId) {
		  employeeRepository.deleteByEdidAndEmpid(eDid, empId); 
	  }
	
}	  
	  
	  
	  
	  
	  
	  
	  
	  
	  

	
	

