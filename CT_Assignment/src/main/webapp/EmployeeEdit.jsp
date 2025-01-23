<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Edit</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Employee Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-light">
		<div>
			<a class="navbar-brand"> Employee Management Application </a>
		</div>
		<ul class="navbar-nav">
			<li><a
				href="<%=request.getContextPath()%>/EmployeeServlet/dashboard"
				class="nav-link">Back to Dashboard</a></li>
		</ul>
	</nav>
	<div class="container col-md-6">
		<div class="card">
			<div class="card-body">
				<c:if test="${!empty employee.name}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${empty employee.name}">
					<form action="insert" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${!empty employee.name}">
Edit Employee
</c:if>
						<c:if test="${empty employee.name}">
Add New Employee
</c:if>
					</h2>
				</caption>
				<c:if test="${employee != null}">
					<input type="hidden" name="oriName"
						value="<c:out
value='${employee.name}' />" />
				</c:if>
				<fieldset class="form-group">
					<label>User Name</label> <input type="text"
						value="<c:out
value='${employee.name}' />" class="form-control"
						name="userName" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Password</label> <input type="text"
						value="<c:out
value='${employee.password}' />" class="form-control"
						name="password">
				</fieldset>
				<fieldset class="form-group">
					<label>User Email</label> <input type="text"
						value="<c:out
value='${employee.email}' />" class="form-control"
						name="email">
				</fieldset>
				<fieldset class="form-group">
					<label> Mobile</label> <input type="text"
						value="<c:out
value='${employee.mobile}' />" class="form-control"
						name="mobile">
				</fieldset>
				<fieldset class="form-group">
					<label> ID</label> <input type="text"
						value="<c:out
value='${employee.id}' />" class="form-control"
						name="id">
				</fieldset>
				<fieldset class="form-group">
					<label> Position</label> <input type="text"
						value="<c:out
value='${employee.position}' />" class="form-control"
						name="position">
				</fieldset>
				<fieldset class="form-group">
					<label> Salary</label> <input type="number"
						value="<c:out
value='${employee.salary}' />" class="form-control"
						name="salary">
				</fieldset>				
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>