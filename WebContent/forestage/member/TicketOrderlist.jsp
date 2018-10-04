<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>
<%@ page import="com.ticketorder.model.*"%>
<%@ page import="com.ticketdetail.model.*"%>

<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- 地址 -->
<script src="/T3MS/js/tw-city-selector.min.js"></script>



<style type="text/css">

.style-seven {
	height: 30px;
	border-style: solid;
	border-color: black;
	border-width: 1px 0 0 0;
	border-radius: 20px;
}

.style-seven:before {
	display: block;
	content: "";
	height: 30px;
	margin-top: -31px;
	border-style: solid;
	border-color: black;
	border-width: 0 0 1px 0;
	border-radius: 20px;
}

</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<%MemVO memVO = (MemVO)session.getAttribute("memVO"); 
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); 
SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
String str =memVO.getBirthday();
Date date1 = df.parse(str);     
String str1 = df1.format(date1);
%>
<%@ include file="/forestage/template/link.jsp"%>
<meta charset="UTF-8">
<title>MemUpdate</title>
</head>
<body>

<%
	Ticket_testDAO test = new Ticket_testDAO();
	List<Ticket_OrderVO> list = test.getAll(memVO.getmemno());
	pageContext.setAttribute("list", list);
	DetailTest teda = new DetailTest();
%>
<jsp:useBean id="tdSrc" scope="page" class="com.ticketdetail.model.DetailTest" />

	<div class="container">
		<div class="row"></div>
		<div class="col-md-12">
			<hr class="style-seven">
		</div>
		<div class="col-md-1"></div>
		<div class="col-md-9">
			<h2 style="color: pink; font-weight: bold;">訂票紀錄</h2>
			<div class="whitebord">
				<table class="table">
					<tr>
						<th>日期</th>
						<th>金額</th>
						<th>座位</th>
						<th>票種</th>
					</tr>
					<c:forEach var="tVO" items="${list}">			
								<tr>
									<td>${tVO.created_at}</td>
									<td>${tVO.amount}</td>
									<td><c:forEach var="tdVO" items="${tdSrc.all}">
					                    	<c:if test="${tVO.order_no==tdVO.order_no}">
						                   		${tdVO.seat}
					                    	</c:if>
							            </c:forEach>
									</td>
									<td><c:forEach var="tdVO" items="${tdSrc.all}">
					                    	<c:if test="${tVO.order_no==tdVO.order_no}">
						                   		${tdVO.ticketType_no}
					                    	</c:if>
							            </c:forEach>
									</td>
								</tr>
								</c:forEach>
				</table>
			</div>
		</div>
		<div class="col-md-1"></div>
		</div>
</body>


</html>