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
			<h3 class="text-center">List of Customers</h3>
			<hr>
			<div class="container text-left">
				<!-- Add new user button redirects to the register.jsp page -->
				<a href="<%=request.getContextPath()%>/EmployeeServlet/edit"
					class="btn btn-success">Add New Customer</a>
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
						<th>Reward Points</th>
					</tr>
				</thead>
				<!-- Pass in the list of users receive via the Servlet response to a loop testing CT-->
				<tbody>
					<c:forEach var="customer" items="${listCustomer}">
						<!-- For each user in the database, display their information accordingly -->
						<tr>
							<td><c:out value="${customer.name}" /></td>
							<td><c:out value="${customer.password}" /></td>
							<td><c:out value="${customer.email}" /></td>
							<td><c:out value="${customer.mobile}" /></td>
							<td><c:out value="${customer.id}" /></td>
							<td><c:out value="${customer.rewardpoints}" /></td>
							
							<!-- For each user in the database, Edit/Delete buttons which invokes the edit/delete functions -->
							<td><a href="edit?name=<c:out value='${customer.name}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?name=<c:out value='${customer.name}' />">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>