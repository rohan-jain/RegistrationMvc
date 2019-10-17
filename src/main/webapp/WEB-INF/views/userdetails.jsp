<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isELIgnored = "false" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
  function myFunction(clickedId)
  {
    	 document.getElementById(clickedId).contentEditable='true';
         document.getElementById(clickedId).style.backgroundColor="green";
  }
  

  function getFormField(name, clickedId) {
	  value = document.getElementById(name + clickedId).innerHTML;
 	  formField = document.createElement('input');
 	  formField.type = 'hidden';
 	  formField.name = name;
 	  formField.value = value;
 	  return formField;
   }
  
  function myPost(clickedId) {

	  // The rest of this code assumes you are not using a library.
	  // It can be made less wordy if you use one.
	  const form = document.createElement('form');
	  form.method = 'post';
	  form.action = 'updateUserController';
	
      form.appendChild(getFormField("userid", clickedId));
      form.appendChild(getFormField("username", clickedId));
      form.appendChild(getFormField("email", clickedId));
      form.appendChild(getFormField("mobileno", clickedId));
      form.appendChild(getFormField("address", clickedId));
      form.appendChild(getFormField("organizationId", clickedId));
      
	  document.body.appendChild(form);
	  form.submit();
	}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="">
<jsp:include page="Headers.jsp"></jsp:include>
	<h3>Hell 00-00 dd tes tt</h3>
	<center>
		<c:if test="${u.role=='admin'}" >
		<table class="table">
			<tr>
				<th>Userid</th>
				<th>Username</th>
				<th>Email</th>
				<th>Mobile</th>
				<th>Address</th>
				<th>OrganizationId</th>
				<th>Organization</th>
				<th>Edit|Delete</th>
			</tr>
			
			<c:forEach items="${users}" var="user">
				<tr id="${user.userid}">
					<td id="userid${user.userid}">${user.userid}</td>
					<td id="username${user.userid}">${user.username}</td>
					<td id="email${user.userid}">${user.email}</td>
					<td id="mobileno${user.userid}">${user.mobileno}</td>
					<td id="address${user.userid}">${user.address}</td>
					<td id="organizationId${user.userid}">${user.organization.organizationID}</td>
					<td id="organizationName${user.userid}">${user.organization.organizationName}</td>

					<td> <button onclick="myPost(${user.userid})" class="btn btn-primary">Sumbit Details</button> <button onclick="myFunction(${user.userid})" class="btn btn-danger">Edit Details</button>| <a href="deletecontroller?userid=${user.userid}" class="btn btn-secondary">Delete</a>
					
				</tr>
			</c:forEach>
		</table>
		</c:if>
		<c:if test="${u.role=='user'}" >
			welcome user
		</c:if>
	</center>
</body>
</html>