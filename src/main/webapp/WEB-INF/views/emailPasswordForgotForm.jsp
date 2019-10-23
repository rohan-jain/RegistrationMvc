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
<link href="css/formStyles.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="modal-dialog modal-login">
	<div class="modal-content">
		<div class="modal-header">				
			<h4 class="modal-title">Get Password on Email</h4>
		</div>
		<div class="modal-body">
			<spring:form action="emailController" id="form" method="POST">
			
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-envelope"></i></span>
						<input type="text" name="emailId" class="form-control" placeholder="Email Id" required="required"/>
					</div>
				</div>
				
				<div class="form-group">
					<button type="submit" class="btn btn-primary btn-block btn-lg">Get Password</button>
				</div>
				
			</spring:form>
		</div>
		
	</div>
</div>
</body>
</html>                   