<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Employee</title>
</head>
<body>
	<h1> Add New Employee to the Employee Database~~~</h1>
	<form action="EmployeeRegisterServlet" method="Post">
		Name : 	   <input type="text" 		name="userName"><br> 
		Password : <input type="password" 	name="password"><br> 
		Email :    <input type="text" 		name="email"><br> 
		Mobile :   <input type="text" 		name="mobile"><br> 
		ID : 	   <input type="text" 		name="id"><br> 
		Position : <input type="text" 		name="position"><br> 
		Salary :   <input type="number" 	name="salary"><br> 
		<input type="submit" value="Submit" />
	</form>
</body>
</html>