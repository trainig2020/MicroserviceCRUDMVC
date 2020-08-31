package io.prathyusha.departmentservice.controller;

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
import org.springframework.web.bind.annotation.RestController;

import io.prathyusha.departmentservice.model.Department;
import io.prathyusha.departmentservice.model.DepartmentList;
import io.prathyusha.departmentservice.model.Employee;
import io.prathyusha.departmentservice.model.EmployeeList;
//import io.prathyusha.departmentservice.model.DepartmentList;
import io.prathyusha.departmentservice.service.DepartmentService;
import io.prathyusha.departmentservice.service.EmployeeService;

@RestController
@RequestMapping("/Department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	
    @Autowired 
	private EmployeeService employeeService;
	 
	
   @RequestMapping("/listDept")
		public DepartmentList getAllDepartments(){
		List<Department> list = departmentService.getAllDepartments();
		DepartmentList listOfDepartments = new DepartmentList();
		listOfDepartments.setDeptList(list);
	    return listOfDepartments;	
	     }
	
	@RequestMapping("/{deptId}")
	   public Optional<Department> getDepartment(@PathVariable("deptId") int deptId) {
		return departmentService.getDepartment(deptId);
	}
	
	
	@PostMapping("/addDepartment")
	    public void addDepartment(@RequestBody Department department) {
		 departmentService.addDepartment(department);
	}
	
	
	 @PutMapping("/updateDepartment/{deptId}")
	      public void updateDepartment(@RequestBody Department department, @PathVariable int deptId) { 
		  department.setDeptId(deptId);
	      departmentService.updateDepartment(department);
	  }
	
	@DeleteMapping("/deleteDepartment/{deptId}")
	      public void deleteDepartment(@PathVariable int deptId) {
		   departmentService.deleteDepartment(deptId);
	      employeeService.deleteEmployee(deptId);
	}
	
	
	  @GetMapping("{eDid}/employees") public EmployeeList
	  getAllEmployees(@PathVariable int eDid){ 
		  EmployeeList lst =employeeService.getAllEmployees(eDid);
	  System.out.println(lst.getListOfEmployees().size()); 
	  System.out.println(lst.getListOfEmployees().size()); return lst;
	  
	  }
	   
	  @GetMapping("/employees/{empId}") public Employee getEmployee(@PathVariable int empId) {
		  return employeeService.getEmployee(empId); 
		  
	  }
	  
	  
	  
	  @PostMapping("/employees/{eDid}/addEmployee") 
	  public void addEmployee(@RequestBody Employee employee,@PathVariable int eDid) {
	  employee.seteDid(eDid); employeeService.addEmployee(employee,eDid);
	  }
	  
	  
	  
	  @PutMapping("/employees/{eDid}/updateEmployee/{empId}")
	  public void updateEmployee(@RequestBody Employee employee,@PathVariable int empId,@PathVariable int eDid) {
	  employee.setEmpId(empId);
	  employee.seteDid(eDid);
	  employeeService.updateEmployee(employee,eDid,empId);
	  }
	  
	  
	  @DeleteMapping("/employees/deleteEmployee/{eDid}") public void
	  deleteEmployee(@PathVariable int eDid) {
	  employeeService.deleteEmployee(eDid); 
	  }
	  
	  
	  @DeleteMapping("/employees/{eDid}/deleteEmployee/{empId}")
	  public void deleteSingleEmployee(@PathVariable int eDid,@PathVariable int empId) {
	  employeeService.deleteSingleEmployee(eDid, empId); }
	  
	 
}


