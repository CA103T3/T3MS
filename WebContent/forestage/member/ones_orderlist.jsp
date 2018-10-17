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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<%@ include file="/forestage/template/link.jsp"%>
<meta charset="UTF-8">
<title>MemUpdate</title>
</head> 



<%
	MemVO memVO = (MemVO)session.getAttribute("memVO");
	MemService mSvc = new MemService();
	Set<Mem_Ticket_SearchVO> tsSet = new TreeSet<>(); 
	tsSet = mSvc.mem_ticket_search(memVO.getmemno());
	Object[] ob = tsSet.toArray();
	
	
	Ticket_OrderService ticket_OrderSvc = new Ticket_OrderService();
	List<Ticket_OrderVO> list = ticket_OrderSvc.findAllOrdersByMember(memVO.getmemno());
// 	pageContext.setAttribute("ttlist", ttlist);
// 	DetailTest teda = new DetailTest();
%>
<body>
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
						<th>電影</th>	
						<th>電影名稱</th>
						<th>場次時間</th>
						<th>單價</th>
						<th>總金額</th>
						<th>訂單內容</th>
					</tr>
					<%
						for(int i=0; i<ob.length; i++){
							Mem_Ticket_SearchVO mvo = (Mem_Ticket_SearchVO) ob[i];
					%>
					
								<tr>
									<td><img style="width:80px;height:80px;" id="movie_pic" src="<%=request.getContextPath() %>/DBGifReader?movie_no=<%=mvo.getMovie_no()%>"></td>
									<td><%=mvo.getMovie_name() %></td>
									<td><%=mvo.getSession_time() %></td>
									<td><%=mvo.getPrice() %></td>
									<td><%=mvo.getAmount() %></td>
									
									<td>
										<form method="post" action="<%=request.getContextPath()%>/ticketOrder/ticketOrder.do">
											
												<input type="hidden" name="action" value="search_ticketDetail_seats"/>
												<input type="hidden" name="uuid" value="<%=mvo.getUuid() %>"/>
												<input type="hidden" name="session_no" value="<%=mvo.getSession_no() %>" />
												<input type="hidden" name="price" value="<%=mvo.getPrice() %>" />
												<input type="hidden" name="amount" value="<%=mvo.getAmount()%>" />
												<button class="btn btn-default" type="submit">詳細內容</button>
										</form>
									</td>
									
									
									
								</tr>
								
								
					<%}%>
				</table>
			
			</div>
		</div>
		<div class="col-md-1"></div>
		</div>
</body>


</html>