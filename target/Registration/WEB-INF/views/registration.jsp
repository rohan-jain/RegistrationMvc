<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<spring:form action="registerController" modelAttribute="user">
	<table>
	<tr>
		<td>Enter username</td>	
		<td><spring:input path="username"/></td>
	</tr>
	<tr>
		<td>Enter email</td>	
		<td><spring:input path="email"/></td>
	</tr>
	<tr>
		<td>Enter address</td>	
		<td><spring:input path="address"/></td>
	</tr>
	<tr>
		<td>Enter mobileno</td>	
		<td><spring:input path="mobileno"/></td>
	</tr>
	<tr>
		<td>Enter organization</td>	
		<td><spring:input path="organization.organizationName"/></td>
	</tr>
	
	<tr>
		<td><input type = "submit" value="register"></td>	
		<td><input type = "reset" value="reset"></td>
	</tr>
	
	
	</table>
</spring:form>
</body>
</html>
