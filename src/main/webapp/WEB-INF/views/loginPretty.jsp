<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap Flat Modal Login Modal Form</title>

<jsp:include page="Headers.jsp"></jsp:include>
<!--
<link href="<c:url value="/resources/css/form-styles.css" />" rel="stylesheet">
 -->
<link href="css/formStyles.css" rel="stylesheet" type="text/css" />
</head>

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


<body>
<div class="modal-dialog modal-login">
	<div class="modal-content">
		<div class="modal-header">				
			<h4 class="modal-title">Sign In</h4>
		</div>
		<div class="modal-body">
			<spring:form action="loginController" modelAttribute="user">
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<spring:input   path="username" class="form-control" placeholder="Username" required="required"/>
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-lock"></i></span>
						<spring:password  class="form-control" path="password" placeholder="Password" required="required"/>
					</div>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary btn-block btn-lg">Sign In</button>
				</div>
				<p class="hint-text"><a href="forgotPassword">Forgot Password?</a></p>
			</spring:form>
		</div>
	</div>
</div>
</body>
</html>                   