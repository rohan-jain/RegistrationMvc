
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-sweetalert/1.0.1/sweetalert.min.css" rel="stylesheet"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-sweetalert/1.0.1/sweetalert.min.js"></script>
    </head>
    <body>
        <label id="msg" style="display:none">${msg}</label>
        <label id="pagename" style="display:none">${pagename}</label>
        <label id="type" style="display:none">${type}</label>
        <script>
            $(document).ready(function()
            {
                var m=$("#msg").text();
                var p=$("#pagename").text();
                var t=$("#type").text();
                setTimeout(function() 
                {
                    swal({
                        title: "Alert!!!",
                        text: m,
                       type: t
                 }, function() {
                    window.location = p;
                    });
                }, 1);
            });
        </script>
       
    </body>
</html>

