<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap Flat Modal Login Modal Form</title>
<link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!--
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 -->
 <jsp:include page="Headers.jsp"></jsp:include>
<link href="css/formStyles.css" rel="stylesheet" type="text/css" />
</head>

<body>

<div class="modal-dialog modal-login">
	<div class="modal-content">
		<div class="modal-header">				
			<h4 class="modal-title">Register New User</h4>
		</div>
		<div class="modal-body">
			<spring:form action="registrationController" modelAttribute="user">
			
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<spring:input           type="text" path="username" class="form-control" placeholder="Username" />
					</div>
					<spring:errors path="username" cssClass="error"></spring:errors>
				</div>
				
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-lock"></i></span>
						<spring:password                     class="form-control" path="password" placeholder="Password"/>
					</div>
					<spring:errors path="password" cssClass="error"></spring:errors>
				</div>

				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-envelope"></i></span>
						<spring:input           type="text" path="email" class="form-control" placeholder="EmailId" />
					</div>
					<spring:errors path="email" cssClass="error"></spring:errors>
				</div>
				
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-address-card"></i></span>
						<spring:input           type="text" path="address" class="form-control" placeholder="Address" />
					</div>
					<spring:errors path="address" cssClass="error"></spring:errors>
				</div>
				
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-mobile"></i></span>
						<spring:input           type="text" path="mobileno" class="form-control" placeholder="Mobile No" />
					</div>
					<spring:errors path="mobileno" cssClass="error"></spring:errors>
				</div>
				
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-building-o"></i></span>
						
						<spring:select type="text" path="organization.organizationID" class="form-control" placeholder="Organization Id" >
							<c:forEach items="${organizations}" var="org">
								<option value="${org.organizationID}">${org.organizationName}</option>
							</c:forEach>
						</spring:select>
					</div>
				</div>
				
				<div class="form-group">
					<button type="submit" class="btn btn-primary btn-block btn-lg">Register User</button>
				</div>
			</spring:form>
		</div>
		
	</div>
</div>
<h2>test23  run</h2>
</body>
</html>                   