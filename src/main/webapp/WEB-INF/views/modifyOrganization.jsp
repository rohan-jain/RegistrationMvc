<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isELIgnored = "false" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
  function makeEditable(clickedId)
  {
    	 document.getElementById("organizationName" + clickedId).contentEditable='true';
    	 
         document.getElementById("organizationName" + clickedId).style.backgroundColor="#b3ffcc";
         document.getElementById("organizationName" + clickedId).style.color="black";
         document.getElementById("organizationName" + clickedId).style.border="2px solid black";
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
	  form.action = 'updateOrganizationController';
	
      form.appendChild(getFormField("organizationID", clickedId));
      form.appendChild(getFormField("organizationName", clickedId));
      
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
	
		<c:if test="${u.role=='admin'}" >
		<table class="table table-striped table-bordered ">
			<tr>	
				<th>OrganizationId</th>
				<th>OrganizationName</th>
				<th>Edit|Update|Delete</th>
			</tr>
	<!-- 	
	private int organizationID;
	
	@Column
	private String organizationName;
	 -->	
			<c:forEach items="${organizations}" var="organization">
				<tr id="${organization.organizationID}">
					<td id="organizationID${organization.organizationID}">${organization.organizationID}</td>
					<td id="organizationName${organization.organizationID}">${organization.organizationName}</td>

					<td> 
					<button onclick="makeEditable(${organization.organizationID})" class="btn btn-primary"><i class="fa fa-edit"></i></button>
					<button onclick="myPost(${organization.organizationID})"  class="btn" style="background:#19aa8d;color:white"><i class="fa fa-send-o"></i></button>
					<a href="deleteorgcontroller?organizationID=${organization.organizationID}" class="btn btn-danger"><i class="fa fa-trash"></i></a>
					
				</tr>
			</c:forEach>
		</table>
		</c:if>
	</div>

</body>
</html>