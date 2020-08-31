package io.prathyusha.employeeservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.prathyusha.employeeservice.model.Employee;
import io.prathyusha.employeeservice.model.EmployeeList;
import io.prathyusha.employeeservice.service.EmployeeService;
@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping("/listEmp/{eDid}")
	public EmployeeList getAllEmployees(@PathVariable int eDid){
		return employeeService.getAllEmployess(eDid);
	}
	
	
	@GetMapping("/{empId}")
	public Optional<Employee> getEmployee(@PathVariable int empId) {
		return employeeService.GetEmployee(empId);
	}
	
	  @PostMapping("/{eDid}/addEmployee") public void addEmployee(@RequestBody
	  Employee employee,@PathVariable int eDid) {
	  employee.seteDid(eDid);
	  employeeService.addEmployee(employee); 
	  }
	 
	
	@PutMapping("/{eDid}/updateEmployee/{empId}")
	public void updateEmployee(@RequestBody Employee employee,@PathVariable int empId,@PathVariable int eDid ) {
		employee.setEmpId(empId);
		employee.seteDid(eDid);
		employeeService.updateEmployee(employee);
	}
	
	
	  @DeleteMapping("/deleteEmployee/{eDid}") 
	   public void deleteEmployee(@PathVariable int eDid) {
	    employeeService.deleteEmployee(eDid);
	    
	  }
	 
	
	  @DeleteMapping("/deleteAll/{eDid}/{empId}")
	  public void deleteEmployeeByEdidAndEmpid(@PathVariable int eDid,@PathVariable int empId)
	  { 
		  employeeService.deleteEmployeeByEdid(eDid, empId);
		}
	  
}

