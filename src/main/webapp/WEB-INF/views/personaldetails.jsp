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
    	 document.getElementById("organizationName" + clickedId).contentEditable='false';
    	 document.getElementById("userid" + clickedId).contentEditable='false';
    	 
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
<title>Home Page</title>
</head>
<body bgcolor="">

<jsp:include page="Headers.jsp"></jsp:include>	
	<div class="container">
	
			<div></div>
			<h2>Welcome ${u.username}</h2>
			<div></div>
			<h3>Personal Details</h5>
			<h4>UserId: ${u.userid}</h4>
			<h4>Email: ${u.email}</h4>
			<h4>Address: ${u.address}</h4>
			<h4>MobileNo: ${u.mobileno}</h4>

	</div>

</body>
</html>