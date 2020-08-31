package io.prathyusha.depempmvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import io.prathyusha.depempmvc.model.Department;
import io.prathyusha.depempmvc.model.DepartmentList;
import io.prathyusha.depempmvc.model.Employee;
import io.prathyusha.depempmvc.model.EmployeeList;

public class DepEmpController {
	
	@Autowired
	private RestTemplate restTemplate;
    
	@RequestMapping(value = "/DeptList")
	 public ModelAndView  getAllDepartments(HttpServletRequest request,HttpServletResponse response) {
		 System.out.println("In Controller");
		DepartmentList deptlist  =  restTemplate.getForObject("http://gateway-service/Department/listDept", DepartmentList.class);
		 System.out.println(deptlist.getDeptList().get(0));
		 List<Department> lstdept = new ArrayList<>();
		 
		 for(int i = 0; i < deptlist.getDeptList().size(); i++) {
			 lstdept.add(deptlist.getDeptList().get(i));
		 }
		 for (Department department : lstdept) {
			System.out.println(department.getDeptId()+department.getDeptName());
		}
		 HttpSession session = request.getSession();
		 session.setAttribute("DeptList", lstdept);
		 ModelAndView modelAndView = new ModelAndView("home");
		 modelAndView.addObject("DeptList", lstdept);
		 modelAndView.addObject("homepage", "main");
		 return modelAndView;
	 }
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/NewDepartment" ,method =RequestMethod.GET )
	 public ModelAndView newDepartment(HttpServletRequest request) {
		 String Register = "newform";
		 HttpSession session1 = request.getSession();
		 List<Department> lst = (List<Department>) session1.getAttribute("DeptList");
		 session1.setAttribute("DeptList", lst);
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.addObject("Register", Register);
		 modelAndView.addObject("createdept", "newdept");
		 modelAndView.setViewName("home");
		 Department department = new Department();
		 modelAndView.addObject("department", department);
		 return modelAndView;
	 }
	 
	 @RequestMapping(value = "/CreateDepartment", method = RequestMethod.POST)
	 public ModelAndView insertDepartment(@ModelAttribute Department department) {
	     restTemplate.postForObject("http://gateway-service/Department/addDepartment",department,Department.class);
		 return new ModelAndView("redirect:/DeptList");
	 }
	 
	 @RequestMapping(value = "/UpdateDepartment", method = RequestMethod.POST)
	 public ModelAndView updateDepartment(@ModelAttribute Department department, HttpServletRequest request) {
		 int deptId =Integer.parseInt(request.getParameter("deptId"));
	     restTemplate.put("http://gateway-service/Department/updateDepartment/"+deptId,department);
		 return new ModelAndView("redirect:/DeptList");
	 }	 
	 
	 @RequestMapping(value = "/DeleteDepartment",method = RequestMethod.GET)
	 public ModelAndView deleteDepartment(HttpServletRequest request) {
		 int deptId =Integer.parseInt(request.getParameter("deptid"));
		 restTemplate.delete("http://gateway-service/Department/deleteDepartment/"+deptId);
		 return new ModelAndView("redirect:/DeptList");
	 }
	 
	 @SuppressWarnings("unchecked")
	@RequestMapping(value = "/GetDepartment",method = RequestMethod.GET)
	 public ModelAndView getDepartmentId(HttpServletRequest request) {
		int deptId =  Integer.parseInt(request.getParameter("deptId"));
		HttpSession session2 = request.getSession();
		List<Department> lst = (List<Department>) session2.getAttribute("DeptList");
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("DeptList", lst);
		modelAndView.addObject("departmentid", deptId);
		return  modelAndView;		
	 }
	 
	 @SuppressWarnings("unchecked")
	@RequestMapping(value = "/showdepartments",method = RequestMethod.GET)
	 public ModelAndView showDepartments(HttpServletRequest request) {
		 HttpSession session3 = request.getSession();
		 List<Department> lstdept1 = (List<Department>) session3.getAttribute("DeptList");
		 session3.setAttribute("DeptListemp", lstdept1);
		 ModelAndView modelAndView = new ModelAndView("home");
   	 modelAndView.addObject("DeptListemp", lstdept1);
		 int deptId =  lstdept1.get(0).getDeptId();
		 modelAndView.addObject("name", "names");
		 return new ModelAndView("redirect:/emplist?deptId="+deptId);
	 }
	 
	 @SuppressWarnings("unchecked")
	@RequestMapping(value = "/emplist")
		public ModelAndView getAllEmployees(HttpServletRequest request) {
			int deptId =Integer.parseInt(request.getParameter("deptId"));
			List<Employee> lstemp = new ArrayList<>();
			EmployeeList lst = restTemplate.getForObject("http://gateway-service/Department/"+deptId+"/employees", EmployeeList.class);
			for (int i = 0; i < lst.getListOfEmployees().size(); i++) {
				lstemp.add(lst.getListOfEmployees().get(i));
			}
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("EmpList", lstemp);
			List<Department> lstdept1 = (List<Department>) httpSession.getAttribute("DeptList");
			ModelAndView modelAndView = new ModelAndView("home");
			modelAndView.addObject("DeptListemp", lstdept1);
			modelAndView.addObject("EmpList", lstemp);
			modelAndView.addObject("homepage", "emppage");
			modelAndView.addObject("name", "names");		
			return modelAndView;
			}
		
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/newEmployee", method = RequestMethod.GET)
		public ModelAndView newContact(HttpServletRequest request) {
			String Register  = "NewForm";
			HttpSession session1 = request.getSession();
			List<Employee> lst =(List<Employee>)session1.getAttribute("EmpList");
			ModelAndView model = new ModelAndView("home");
			model.addObject("EmpList", lst);
			model.addObject("Register", Register);
			model.addObject("insertEmployee", "newemployee");
			model.addObject("homepage", "emppage");		
			return model;	
		}

		@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
		public ModelAndView saveEmployee(@ModelAttribute Employee employee,HttpServletRequest request) {
			int eDid =  Integer.parseInt(request.getParameter("edid"));
			restTemplate.postForObject("http://gateway-service/Department/employees/"+eDid+"/addEmployee", employee, Employee.class);	
			return new ModelAndView("redirect:/emplist?deptId="+eDid);		
		}

		@RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
		public ModelAndView deleteEmployee(HttpServletRequest request) {
			int employeeId = Integer.parseInt(request.getParameter("id"));
			int eDid =  Integer.parseInt(request.getParameter("did"));
			restTemplate.delete("http://gateway-service/Department/employees/"+eDid+"/deleteEmployee/"+employeeId);
			return new ModelAndView("redirect:/emplist?deptid="+eDid);	
		}

		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/getEmployee", method = RequestMethod.GET)
		public ModelAndView editContact(HttpServletRequest request) {
			int employeeId = Integer.parseInt(request.getParameter("id"));
			int Did =  Integer.parseInt(request.getParameter("Did"));
			HttpSession session2 = request.getSession();
			List<Employee> lst =(List<Employee>) session2.getAttribute("EmpList");
			session2.setAttribute("EmpList", lst);
			ModelAndView model = new ModelAndView("home");
			model.addObject("homepage", "emppage");
			model.addObject("EmpList", lst);
			model.addObject("employeeid", employeeId);
			model.addObject("Did", Did);
			return model;
		}
		
		@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
		public ModelAndView updateEmployee(@ModelAttribute Employee employee,HttpServletRequest request) {
			int employeeId = Integer.parseInt(request.getParameter("empId"));
			int Did =  Integer.parseInt(request.getParameter("eDid"));
			restTemplate.put("http://gateway-service/Department/employees/"+Did+"/updateEmployee/"+employeeId, employee);
			return new ModelAndView("redirect:/emplist?deptId="+Did);
		}
	

}
