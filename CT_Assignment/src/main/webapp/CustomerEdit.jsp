<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Edit</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Customer Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-light">
		<div>
			<a class="navbar-brand"> Customer Management Application </a>
		</div>
		<ul class="navbar-nav">
			<li><a
				href="<%=request.getContextPath()%>/CustomerServlet/dashboard"
				class="nav-link">Back to Dashboard</a></li>
		</ul>
	</nav>
	<div class="container col-md-6">
		<div class="card">
			<div class="card-body">
				<c:if test="${!empty customer.name}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${empty customer.name}">
					<form action="insert" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${!empty customer.name}">
Edit Customer
</c:if>
						<c:if test="${empty customer.name}">
Add New Customer
</c:if>
					</h2>
				</caption>
				<c:if test="${customer != null}">
					<input type="hidden" name="oriName"
						value="<c:out
value='${customer.name}' />" />
				</c:if>
				<fieldset class="form-group">
					<label>User Name</label> <input type="text"
						value="<c:out
value='${customer.name}' />" class="form-control"
						name="userName" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Password</label> <input type="text"
						value="<c:out
value='${customer.password}' />" class="form-control"
						name="password">
				</fieldset>
				<fieldset class="form-group">
					<label>User Email</label> <input type="text"
						value="<c:out
value='${customer.email}' />" class="form-control"
						name="email">
				</fieldset>
				<fieldset class="form-group">
					<label> Mobile</label> <input type="text"
						value="<c:out
value='${customer.mobile}' />" class="form-control"
						name="mobile">
				</fieldset>
				<fieldset class="form-group">
					<label> ID</label> <input type="text"
						value="<c:out
value='${customer.id}' />" class="form-control"
						name="id">
				</fieldset>
				<fieldset class="form-group">
					<label> Reward Points</label> <input type="number"
						value="<c:out
value='${customer.rewardpoints}' />" class="form-control"
						name="rewardpoints">
				</fieldset>				
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>