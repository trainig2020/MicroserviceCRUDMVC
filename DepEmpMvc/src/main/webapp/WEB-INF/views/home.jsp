<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
h1 {
	color: green;
}
header {
	background-color: black;
	width: 100%;
	height: 50px;
	text-align: center;
}
 #sidebar-left {
	float: left;
	width: 15%;
	background-color:#83A0FF;
}
#main {
	float: left;
	width: 85%;
	background-color: lightgray;
}
#footer {
	clear: both;
	height: 50px;
	width: 100%;
	text-align: center;
	background-color:#ffa483;
}
#sidebar-left {
	min-height: 500px
}
table.d, td {
	table-layout: fixed;
	width: 30%;
	height: 10px;
	padding: 4px;
}
input[type=text] {
	border: 1px solid black;
	background-color: light-grey;
}
</style>
<title>Get All</title>
</head>
<body>
	 <div id="header">
		<h2>
			DEPARTMENT EMPLOYEE APPLICATION
		</h2>
	</div>
	
	<div id="sidebar-left">
        <a href="showdepartments">[+]</a>departments
        <c:if test="${name eq 'names'}">
				<c:forEach var="dept" items="${DeptListemp}">
					<c:if test="${not empty dept}">

						<input type="hidden" name="deptid" value="${dept.deptId}">

						<br>
				             &nbsp <a href="DeptList"><b>=></b></a>&nbsp <button type="button"
							class="btn btn-success" style="width:100px;">
							<a href="emplist?deptid=${dept.deptId}"><font color="lightgreen">${dept.deptName}
							</font></a>
						    </button>
						<br>

					</c:if>
				</c:forEach>
			</c:if>
	</div>

<div id =main>
 <div align="center">
 <c:if test="${homepage ne 'emppage'}">
	<c:if test="${Register eq 'newform'}">
		<form action="CreateDepartment" method="post">
	</c:if>
	<c:if test="${Register ne 'newform'}">
		<form action="UpdateDepartment" method="post">
	</c:if>
	<table class="t"  border="1 px solid black" align ="center"  >
		<thead>
			<tr>
				<th colspan="2"  style="text-align: center"><h1>Department Details</th>
				<th colspan="2" style="text-align: center"><a href="NewDepartment"><h2>Add New Department</h2></a></th>
			</tr>
			<tr>
				<th>DeptID</th>
				<th>DeptName</th>
				
				<th>Update</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${DeptList}" var="dept">

				<c:if test="${departmentid eq dept.deptid}">
					<tr>
						<td><input type="text" name="deptId" value="${dept.deptId}"
							readonly="readonly" /></td>
						<td><input type="text" name="deptName"
							value="${dept.deptName}" /></td>
					   
						<td><input type="submit"
							value="Update" /></td>
					</tr>
				</c:if>
				<c:if test="${departmentid ne dept.deptid}">
					<tr>
						<td><c:out value="${dept.deptId}" /></td>
						<td><c:out value="${dept.deptName}" /></td>
					
						<td><a href="GetDepartment?deptId=${dept.deptId}">Update</a></td>
						<td><a href="DeleteDepartment?deptId=${dept.deptId}">Delete</a></td>
					</tr>
				</c:if>
			</c:forEach>
			<c:if test="${createdept eq 'newdept'}">
				<tr>
					<td><input type="text" name="deptId" /></td>
					<td><input type="text" name="deptName" /></td>
					
					<td colspan="2"><input type="submit"value="Save"/></td>
				</tr>
			</c:if>
		</tbody>
	</table>
</form>
</c:if>
</div> 
<c:if test="${homepage eq 'emppage'}">
    
	
	<c:if test="${Register eq 'NewForm'}">
		<form action="saveEmployee" method="post">
	</c:if>
	<c:if test="${Register ne 'NewForm'}">
		<form action="updateEmployee" method="post">
	</c:if>

	<table border="2" style="width: 100%"background-color:#eee;>
		<thead>
		    <tr>
				<th colspan="4"  style="text-align: center"><h1>Employee Details</h1></th>
				<th colspan="2" style="text-align: center"> <a href="newEmployee"><h2>Insert New Employee</h2></a></th>
			</tr>
			<tr>
				<th>EMPID</th>
				<th>EDID</th>
				<th>EMPNAME</th>
				<th>EmpAGE</th>
				<th>Update</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${EmpList}" var="emp">  		
				<c:if test="${employeeId eq emp.empId}">
				   <c:if test="${Did eq emp.eDid}">
					<tr>
						<td><input type="text" name="empId"
							value="${emp.empId}" readonly="readonly" /></td>
						<td><input type="text" name="eDid"
							value="${emp.eDid}" readonly="readonly" /></td>
						<td><input type="text" name="empName"
							value="${emp.empName}" /></td>
						<td><input type="text" name="empAge"
							value="${emp.empAge}" /></td>
						<td><input type="submit" value="update" /></td>
					</tr>
				</c:if>
				</c:if>
				<c:if test="${employeeid ne emp.empid}">
					<tr>
						
                    <td>${emp.empId}</td>
                    <td>${emp.eDid}</td>
                    <td>${emp.empName}</td>
                    <td>${emp.empAge}</td>
                    <td><a href="getEmployee?id=${emp.empId}&did=${emp.eDid}">Update</a></td>
                     <td><a href="deleteEmployee?id=${emp.empId}&did=${emp.eDid}">Delete</a></td>
					</tr>
				</c:if>
				</c:forEach>
					<c:if test="${insertEmployee eq 'newemployee'}">
						<tr>
							<td><input type="text" name="empId" /></td>
							<td><input type="text" name="eDid"  /></td>
							<td><input type="text" name="empName" /></td>
							<td><input type="text" name="empAge" /></td>
							<td colspan="2"><input type="submit" value="save"/></td>
						</tr>
					</c:if>	
		</tbody>
	</table>
</form>
</c:if>
</div>
</body>
</html>