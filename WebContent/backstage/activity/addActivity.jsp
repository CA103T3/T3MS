<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="java.util.*"%>

<%
	ActivityVO actVO = new ActivityVO();
	ActivityService actSvc = new ActivityService();
	List<ActivityVO> list = actSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>M&amp;S</title>
<%@ include file="/backstage/template/link.jsp"%>

<style type="text/css">
.table tbody tr td {
	line-height: 219px;
	font-size: 18px;
}

img {
    border: 1px solid #ddd;
    border-radius: 4px;
    padding: 5px;
    width: 140px;
}
</style>
</head>

<body class="fs16">
	<%@ include file="/backstage/template/header.jsp"%>
	<div id="wrapper" class="mt50">
		<%@ include file="/backstage/template/sidebar.jsp"%>
		<div class="flex-column" id="page-content-wrapper">
			<div class="container-fluid mt20">
				<!-- start -->
				
				
				
				
				
				
				<!-- End -->
			</div>
		</div>
	</div>
	<script src="<%=request.getContextPath() + "/js/back_index.js"%>"></script>
</body>
</html>