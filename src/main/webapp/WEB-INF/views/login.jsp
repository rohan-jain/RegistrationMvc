<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>

<spring:form action="loginController" modelAttribute="user">
	<table>
	<tr>
		<td>Enter username</td>	
		<td><spring:input path="username"/></td>
	</tr>
	<tr>
		<td>Enter email</td>	
		<td><spring:input path="password"/></td>
	</tr>
		
	<tr>
		<td><input type = "submit" value="Login"></td>	
		<td><input type = "reset" value="reset"></td>
	</tr>
</table>
</spring:form>
</body>
</html>
