<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
send password to email id

	<form action="emailController" id="form" method="POST">
		<table>
		<tr>
			<td>
				Email Id : <input type="text" name="emailId"/>
			</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="Submit"/>
			</td>
		</tr>
	</table>
</form>

</body>
</html>