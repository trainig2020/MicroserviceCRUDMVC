package io.prathyusha.departmentservice.model;

import java.util.List;

public class DepartmentList {
	
	
	private List<Department> deptList;

	public List<Department> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<Department> deptList) {
		this.deptList = deptList;
	}

	public DepartmentList(List<Department> deptList) {
		super();
		this.deptList = deptList;
	}

	public DepartmentList() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
