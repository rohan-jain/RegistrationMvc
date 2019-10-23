<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>

	<!-- 
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-sweetalert/1.0.1/sweetalert.min.css"
	rel="stylesheet" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-sweetalert/1.0.1/sweetalert.min.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
 -->
 
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>

<style type="text/css">

.half-size {
	zoom: 0.5;
}

</style>
</head>

<body>
	<label id="msg" style="display: none">${msg}</label>
	<label id="pagename" style="display: none">${pagename}</label>
	<label id="type" style="display: none">${type}</label>
	<script>
		$(document).ready(function() {
			var m = $("#msg").text();
			var p = $("#pagename").text();
			var t = $("#type").text();
			
			Swal.fire({
					width: 400,
					position: 'top',
					title : "Alert!!!",
					customClass: {
					    icon: 'half-size'
					  },
					text : m,
					type : t
				}).then(function() {
				    window.location = p;
				});
		
		});
	</script>
</body>
</html>

