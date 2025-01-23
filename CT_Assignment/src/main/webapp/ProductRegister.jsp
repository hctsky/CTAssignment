<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Product</title>
</head>
<body>
	<h1> Add New Essential Oil Product to the Product Database~~~</h1>
	<form action="ProductRegisterServlet" method="Post">
		Name : 	    <input type="text" 		name="productName"><br> 
		Price : 	<input type="number" 	name="price"><br> 
		Quantity :  <input type="number" 	name="quantity"><br> 
		<input type="submit" value="Submit" />
	</form>
</body>
</html>