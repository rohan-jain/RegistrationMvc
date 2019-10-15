<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<Script>
	function edit(userid)
	{
		$("#update").show();
		$("#cancel").show();
		$("#edit").hide();
		$("#data"+userid).hide();
		$(".userdata"+userid).show();
	}
	function cancel()
	{
		$("#update").hide();
		$("#cancel").hide();
		$("#edit").show();
		$("#data").show();

		$(".userdata").hide();
	}
</Script>
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
				<tr id="data${user.userid}">
					<td>${user.userid}</td>
					<td>${user.username}</td>
					<td>${user.email}</td>
					<td>${user.mobileno}</td>
					<td>${user.address}</td>
					<td>${user.organization.organizationName}</td>
					<td><a href="">Edit</a> | <a href="deletecontroller?userid=${user.userid}">Delete</a>
				</tr>
				<tr  id="userdata${user.userid}" class="userdata" style="display:none">
					<spring:form action="updatecontroller" modelAttribute="user">
					<td><spring:input path="userid"/></td>
					<td><spring:input path="username"/></td>
					<td><spring:input path="email"/></td>
					<td><spring:input path="mobileno"/></td>
					<td><spring:input path="address"/></td>
					<td><spring:input path="organization.organizationName" /></td>
					<td><a href="" onclick="edit(${user.userid})" id="edit">Edit</a> <a href="" id="cancel" style="display:none" onclick="cancel()">Cancel</a> | <a href="deletecontroller?userid=${user.userid}">Delete</a>
					<td><input type="submit" id="update" value="Update" style="display:none" /></td>
					</spring:form>
				</tr>
			</c:forEach>
		</table>
	</center>
</body>
</html>