<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <%@ include file="/forestage/template/link.jsp" %>
        <title>M&amp;S</title>
        <style>

        </style>
        
    </head>
    <body class="body-template">
        <%@ include file="/forestage/template/header.jsp" %>
        <%@ include file="/forestage/template/search_bar.jsp" %>

        <div class="container text-center" style="color: #ffffff;font-size: 20px;">
        	<img src="<%=request.getContextPath() %>/img/SystemBusy.png"/>
        </div>
        
        <div class="col-sm-12 text-center" style="font-size:20px;margin-top:20px;">
			<a href="<%=request.getContextPath()%>/index.jsp"><img src="<%=request.getContextPath()%>/img/House-Icon.png" style="height:40px;edith:40px;"></a>
		</div>
       
        <%@ include file="/forestage/template/footer.jsp" %>

        <script src="<%=request.getContextPath()%>/js/template.js"></script>
        <script>
        $(document).ready(function(){
            $("li:contains('合作影城')").addClass("custom-active");
        });
        </script>
    </body>
</html>