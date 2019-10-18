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
<style type="text/css">
    body {
		font-family: 'Varela Round', sans-serif;
	}
	.modal-login {
		width: 350px;
	}
	.modal-login .modal-content {
		padding: 20px;
		border-radius: 5px;
		border: none;
	}
	.modal-login .modal-header {
		border-bottom: none;
        position: relative;
		justify-content: center;
	}
	.modal-login .close {
        position: absolute;
		top: -10px;
		right: -10px;
	}
	.modal-login h4 {
		color: #636363;
		text-align: center;
		font-size: 26px;
		margin-top: 0;
	}
	.modal-login .modal-content {
		color: #999;
		border-radius: 1px;
    	margin-bottom: 15px;
        background: #fff;
		border: 1px solid #f3f3f3;
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        padding: 25px;
    }
	.modal-login .form-group {
		margin-bottom: 20px;
	}
	.modal-login label {
		font-weight: normal;
		font-size: 13px;
	}
	.modal-login .form-control {
		min-height: 38px;
		padding-left: 5px;
		box-shadow: none !important;
		border-width: 0 0 1px 0;
		border-radius: 0;
	}
	.modal-login .form-control:focus {
		border-color: #ccc;
	}
	.modal-login .input-group-addon {
		max-width: 42px;
		text-align: center;
		background: none;
		border-width: 0 0 1px 0;
		padding-left: 5px;
		border-radius: 0;
	}
    .modal-login .btn {        
        font-size: 16px;
        font-weight: bold;
		background: #19aa8d;
        border-radius: 3px;
		border: none;
		min-width: 140px;
        outline: none !important;
    }
	.modal-login .btn:hover, .modal-login .btn:focus {
		background: #179b81;
	}
	.modal-login .hint-text {
		text-align: center;
		padding-top: 5px;
		font-size: 13px;
	}
	.modal-login .modal-footer {
		color: #999;
		border-color: #dee4e7;
		text-align: center;
		margin: 0 -25px -25px;
		font-size: 13px;
		justify-content: center;
	}
	.modal-login a {
		color: #fff;
		text-decoration: underline;
	}
	.modal-login a:hover {
		text-decoration: none;
	}
	.modal-login a {
		color: #19aa8d;
		text-decoration: none;
	}	
	.modal-login a:hover {
		text-decoration: underline;
	}
	.modal-login .fa {
		font-size: 21px;
	}
	.trigger-btn {
		display: inline-block;
		margin: 100px auto;
	}
	.error
	{
		color:red;
	}
</style>
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
						<span class="input-group-addon"><i class="fa fa-send-o"></i></span>
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