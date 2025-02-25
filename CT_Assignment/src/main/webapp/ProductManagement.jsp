<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Management</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<div class="row">
		<div class="container">
			<h3 class="text-center">List of Products Updated for A2!</h3>
			<hr>
			<div class="container text-left">
				<!-- Add new product button redirects to the ProductRegister.jsp page -->
				<a href="<%=request.getContextPath()%>/ProductServlet/edit"
					class="btn btn-success">Add New Product</a>
					
		<ul class="navbar-nav">
			<li><a
				href="<%=request.getContextPath()%>/index.jsp"
				class="nav-link">Back to Home</a></li>
		</ul>					
					
			</div>
			<br>
			<!-- Create a table to list out all current product information -->
			<table class="table">
				<thead>
					<tr>
						<th>Name</th>
						<th>Price</th>
						<th>Quantity</th>
						<th>Actions</th>
					</tr>
				</thead>
				<!-- Pass in the list of products receive via the Servlet response to a loop -->
				<tbody>
					<c:forEach var="product" items="${listProducts}">
						<!-- For each user in the database, display their information accordingly -->
						<tr>
							<td><c:out value="${product.name}" /></td>
							<td><c:out value="${product.price}" /></td>
							<td><c:out value="${product.quantity}" /></td>
							
							<!-- For each product in the database, Edit/Delete buttons which invokes the edit/delete functions -->
							<td><a href="edit?name=<c:out value='${product.name}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?name=<c:out value='${product.name}' />">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>