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
         document.getElementById(clickedId).style.backgroundColor="#b3ffcc";
         document.getElementById(clickedId).style.color="black";
         document.getElementById(clickedId).style.border="2px solid black";
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
<style type="text/css">
.table
{	
	font-family: 'Varela Round', sans-serif;
}
</style>
<title>Insert title here</title>
</head>
<body bgcolor="">

<jsp:include page="Headers.jsp"></jsp:include>	
	<div class="container">
	
		<c:if test="${u.role=='admin'}" >
		<table class="table table-striped table-bordered ">
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

					<td> <button onclick="myPost(${user.userid})"  class="btn" style="background:#19aa8d;color:white"><i class="fa fa-send"></i></button>
					<button onclick="myFunction(${user.userid})" class="btn btn-primary"><i class="fa fa-edit"></i></button>
					<a href="deletecontroller?userid=${user.userid}" class="btn btn-danger"><i class="fa fa-trash"></i></a>
					
				</tr>
			</c:forEach>
		</table>
		</c:if>
		<c:if test="${u.role=='user'}" >
			welcome user
		</c:if>
	</div>

</body>
</html>