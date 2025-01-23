<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SPRINT 2 Increment</title>
</head>
<body>
	<h1>Hello! Welcome to the Awesome Essential Oil eCommerce Website!</h1>
	<h2>Select below for sprint 2 functionalities</h2>
		<ul class="navbar-nav">
			<li><a
				href="<%=request.getContextPath()%>/ProductServlet/dashboard"
				class="nav-link">Go to Product Listings</a>
			</li>
			<li><a
				href="<%=request.getContextPath()%>/CustomerServlet/dashboard"
				class="nav-link">Go to Customer Listings</a>
			</li>
			<li><a
				href="<%=request.getContextPath()%>/EmployeeServlet/dashboard"
				class="nav-link">Go to Employee Listings</a>
			</li>
		</ul>

</body>
</html>