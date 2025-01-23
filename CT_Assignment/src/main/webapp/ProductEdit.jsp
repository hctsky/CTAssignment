<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Edit</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Product Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-light">
		<div>
			<a class="navbar-brand"> Product Management Application </a>
		</div>
		<ul class="navbar-nav">
			<li><a
				href="<%=request.getContextPath()%>/ProductServlet/dashboard"
				class="nav-link">Back to Dashboard</a></li>
		</ul>
	</nav>
	<div class="container col-md-6">
		<div class="card">
			<div class="card-body">
				<c:if test="${!empty product.name}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${empty product.name}">
					<form action="insert" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${!empty product.name}"> Edit Product</c:if>
						<c:if test="${empty product.name}"> Add New Product </c:if>
					</h2>
				</caption>
				<c:if test="${product != null}">
					<input type="hidden" name="oriName"
						value="<c:out 
					value='${product.name}' />" />
				</c:if>
				<fieldset class="form-group">
					<label>Product Name</label> <input type="text"
						value="<c:out
value='${product.name}' />" class="form-control"
						name="productName" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Price</label> <input type="number"
						value="<c:out
value='${product.price}' />" class="form-control"
						name="price">
				</fieldset>
				<fieldset class="form-group">
					<label>Quantity</label> <input type="number"
						value="<c:out
value='${product.quantity}' />"
						class="form-control" name="quantity">
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>