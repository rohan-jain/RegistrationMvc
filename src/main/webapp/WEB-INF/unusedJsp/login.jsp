<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

<c:if test="${not empty isInvalidLogin}">
	<script type="text/javascript">
	function showAlertIfLoginInvalid() {
	    if(${isInvalidLogin}) {
	    	alert('Login credentials invalid, click \"Forgot Password\" if username/password lost');
	    }
	    
	}
	window.onload = showAlertIfLoginInvalid();
	</script>
</c:if>

<style>
.myForm
{
	margin:0 auto;
}
</style>
</head>
<body>
<!--  
#todo uncomment if you want to recover this file into views
<jsp:include page="Headers.jsp"></jsp:include>
-->


	<div class="container">
<spring:form action="loginController" modelAttribute="user" class="col-lg-6 col-md-6 col-sm-12 col-xs-12 myForm">

		<h2> login</h2>
		
		<div class="form-group">
			<label>Enter username</label>	
			<spring:input path="username" class="form-control"/>
		</div>
		<div class="form-group">
			<label>Enter Password</label>	
			<spring:password path="password" class="form-control"/>
		</div>
		<a href="forgotPassword">Forgot password?</a>
		<djv>
			<input type = "submit" value="Login" class="btn btn-primary btn-block">	
			<input type = "reset" value="reset"class="btn btn-secondary btn-block">
		</div>
		
		</spring:form>
		
</div>

</body>
</html>
