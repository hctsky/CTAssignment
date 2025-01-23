<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Management</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<div class="row">
		<div class="container">
			<h3 class="text-center">List of Employees</h3>
			<hr>
			<div class="container text-left">
				<!-- Add new user button redirects to the register.jsp page -->
				<a href="<%=request.getContextPath()%>/EmployeeServlet/edit"
					class="btn btn-success">Add New Employee</a>
		<ul class="navbar-nav">
			<li><a
				href="<%=request.getContextPath()%>/index.jsp"
				class="nav-link">Back to Home</a></li>
		</ul>								
			</div>
			<br>
			<!-- Create a table to list out all current customer information -->
			<table class="table">
				<thead>
					<tr>
						<th>Name</th>
						<th>Password</th>
						<th>Email</th>
						<th>Mobile</th>
						<th>ID</th>
						<th>Position</th>
						<th>Salary</th>
						<th>Actions</th>
					</tr>
				</thead>
				<!-- Pass in the list of users receive via the Servlet response to a loop -->
				<tbody>
					<c:forEach var="employee" items="${listEmployees}">
						<!-- For each user in the database, display their information accordingly -->
						<tr>
							<td><c:out value="${employee.name}" /></td>
							<td><c:out value="${employee.password}" /></td>
							<td><c:out value="${employee.email}" /></td>
							<td><c:out value="${employee.mobile}" /></td>
							<td><c:out value="${employee.id}" /></td>
							<td><c:out value="${employee.position}" /></td>
							<td><c:out value="${employee.salary}" /></td>
							
							<!-- For each user in the database, Edit/Delete buttons which invokes the edit/delete functions -->
							<td><a href="edit?name=<c:out value='${employee.name}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?name=<c:out value='${employee.name}' />">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>