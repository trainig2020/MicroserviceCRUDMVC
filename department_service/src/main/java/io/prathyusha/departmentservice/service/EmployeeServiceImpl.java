package io.prathyusha.departmentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import io.prathyusha.departmentservice.model.Department;
import io.prathyusha.departmentservice.model.Employee;
import io.prathyusha.departmentservice.model.EmployeeList;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	  @Autowired private RestTemplate restTemplate;
	  
	  
	  @Override public EmployeeList getAllEmployees(int eDid) { 
	  EmployeeList listEmp =restTemplate.getForObject("http://employee-service:8087/employees/listEmp/"+eDid, EmployeeList.class); 
	  return listEmp;
	  }
	  
	
	  @Override public Employee getEmployee(int empId) { 
	  Employee employee=restTemplate.getForObject("http://employee-service:8087/employees/"+empId,Employee.class); 
	  return employee;
	  }
	  
	  @Override public Employee addEmployee(Employee employee,int eDid) { 
	  return restTemplate.postForObject("http://employee-service:8087/employees/"+eDid+"/addEmployee",employee, Employee.class); 
	  }
	  
	
	
	  @Override public void updateEmployee(Employee employee,int eDid,int empId) {
	  restTemplate.put("http://employee-service:8087/employees/"+eDid+ "/updateEmployee/"+empId,employee);
	  }
	 
	  @Override public void deleteEmployee(int eDid) {
	  restTemplate.delete("http://employee-service:8087/employees/deleteEmployee/"+eDid, eDid);
	  
	  }
	
	  @Override public void deleteSingleEmployee(int eDid, int empId) {
	  restTemplate.delete("http://employee-service:8087/employees/deleteAll/"+eDid+ "/"+empId);
	  
	  }
	  
}

