package io.prathyusha.employeeservice;

import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import io.prathyusha.employeeservice.model.Employee;
import io.prathyusha.employeeservice.repository.EmployeeRepository;
import io.prathyusha.employeeservice.service.EmployeeService;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeeServiceApplicationTests {

	
	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private EmployeeRepository employeeRepository;

	

		@Test
		public void getAllEmployees() {
			when(employeeRepository.findAllByEdId(1))
			.thenReturn(Stream.of(new Employee(1, "Prathyusha", 28, 1), new Employee(2, "Mahesh", 16, 1))
					.collect(Collectors.toList()));
			assertEquals(2, employeeService.getAllEmployess(1).getListOfEmployees().size());
		}
		

	 

	@Test
	public void addEmployee() {
		Employee employee = new Employee(1, "Anusha", 21, 1);
		when(employeeRepository.save(employee)).thenReturn(employee);
		assertEquals(employee, employeeService.addEmployee(employee));
	}

	@Test
	public void updateEmployee() {
		Employee employee = new Employee(1, "anusha", 21, 1);
		employee.setEmpAge(22);
		employee.setEmpName("Ameshu");
		when(employeeRepository.save(employee)).thenReturn(employee);
		assertEquals(employee, employeeService.updateEmployee(employee));
	}

	@Test
	public void deleteEmployee() {
		Employee employee = new Employee(1, "anusha", 21, 1);
		verify(employeeRepository, times(0)).deleteByEdidAndEmpid(employee.geteDid(), employee.getEmpId());
	}
	


}
