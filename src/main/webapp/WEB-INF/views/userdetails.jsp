<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<table border="1">
			<tr>
				<th>Userid</th>
				<th>Username</th>
				<th>Email</th>
				<th>Mobile</th>
				<th>Address</th>
				<th>Organization</th>
				<th>Edit|Delete</th>
			</tr>
			
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.userid}</td>
					<td>${user.username}</td>
					<td>${user.email}</td>
					<td>${user.mobileno}</td>
					<td>${user.address}</td>
					<td>${user.organization.organizationName}</td>
					<td><a href="editController?userid=${user.userid}&username=${user.username}&email=${user.email}&mobileno=${user.mobileno}">Edit</a> | <a href="deletecontroller?userid=${user.userid}">Delete</a>
				</tr>
			</c:forEach>
		</table>
	</center>
</body>
</html>