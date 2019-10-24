<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<!--  
#todo uncomment if you want to recover this file into views
<jsp:include page="Headers.jsp"></jsp:include>
-->
<spring:form action="organizatiocontroller" modelAttribute="organization">
	<table>
	<tr>
		<td>Enter organization name</td>	
		<td><spring:input path="organizationName"/></td>
	</tr>
		<tr>
		<td><input type = "submit" value="Add Organization"></td>	
		<td><input type = "reset" value="reset"></td>
	</tr>
	
	
	</table>
</spring:form>
</body>
</html>