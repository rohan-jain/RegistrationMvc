<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<jsp:include page="Headers.jsp"></jsp:include>
<spring:form action="registrationController" modelAttribute="user">
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
		<td>Enter Password</td>	
		<td><spring:password path="password"/></td>
	</tr>
	<tr>
	<td>Select Organization</td>
	<td>
	<spring:select path="organization.organizationID">
		<c:forEach items="${organizations}" var="org">
		<option value="${org.organizationID}">${org.organizationName}</option>
		</c:forEach>
	</spring:select>
	</td>
	</tr>	
	<tr>
		<td><input type = "submit" value="register"></td>	
		<td><input type = "reset" value="reset"></td>
	</tr>
	
	
	</table>
</spring:form>
</body>
</html>
