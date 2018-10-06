<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<%@ include file="/forestage/template/link.jsp"%>
<title>M&amp;S</title>
<style>
</style>

</head>
<body class="body-template">
	<%@ include file="/forestage/template/header.jsp"%>

	<div class="container" style="color: #ffffff; font-size: 20px;">

		<%
			String hall_name = request.getParameter("hall_name");
			out.println(hall_name);
			String[] seats = request.getParameterValues("seats");

			out.println("<br>");
			out.println("seats.length=" + seats.length);
			out.println("<br>");
			for (String seat : seats)
				out.println("訂購的位置：" + seat+ "<br>");
		%>

	</div>

	<%@ include file="/forestage/template/footer.jsp"%>

	<script src="<%=request.getContextPath()%>/js/template.js"></script>
	<script>
		$(document).ready(function() {
			$("li:contains('合作影城')").addClass("custom-active");
		});
	</script>
</body>
</html>