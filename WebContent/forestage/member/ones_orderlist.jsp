<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>
<%@ page import="com.ticketorder.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.ticketdetail.model.*"%>

<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="widtd=device-widtd, initial-scale=1, shrink-to-fit=no">
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
	border-widtd: 1px 0 0 0;
	border-radius: 20px;
}

.style-seven:before {
	display: block;
	content: "";
	height: 30px;
	margin-top: -31px;
	border-style: solid;
	border-color: black;
	border-widtd: 0 0 1px 0;
	border-radius: 20px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<%@ include file="/forestage/template/link.jsp"%>
<meta charset="UTF-8">

<title>MemUpdate</title>

</head> 
<%@ include file="/forestage/template/header.jsp"%>
<%
	MemVO memVO = (MemVO)session.getAttribute("memVO");
	MemService mSvc = new MemService();
	String fullName = memVO.getLastname().concat(memVO.getFirstname());
		
	
	Set<Mem_Ticket_SearchVO> tsSet = new TreeSet<>(); 
	
	tsSet = mSvc.mem_ticket_search(memVO.getmemno());
	Object[] ob = tsSet.toArray();
	
	Ticket_OrderService ticket_OrderSvc = new Ticket_OrderService();
	List<Ticket_OrderVO> list = ticket_OrderSvc.findAllOrdersByMember(memVO.getmemno());
	
%>
<body class="body-template">
<%-- <jsp:useBean id="tdSrc" scope="page" class="com.ticketdetail.model.DetailTest" /> --%>
	<div class="container" style="font-size:22px">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12">
			<br><br>
				<div class="panel panel-info">
					<div class="panel-heading text-center"><b><%=fullName %> 的訂票紀錄</b></div>
						<table class="table text-center">
							<tr>
								<td>電影</td>	
								<td>電影名稱</td>
								<td>場次時間</td>
								<td>單價</td>
								<td>總金額</td>
								<td>訂單內容</td>
							</tr>
							<%
									for(int i=0; i<ob.length; i++){
										Mem_Ticket_SearchVO mvo = (Mem_Ticket_SearchVO) ob[i];
										if(mvo.getAmount() != 0 || mvo.getMovie_name()!=null){
										System.out.println(mvo.getMovie_name());
							%>
								<tr>
									<td><img style="widtd:80px;height:80px;" id="movie_pic" src="<%=request.getContextPath() %>/DBGifReader?movie_no=<%=mvo.getMovie_no()%>"></td>
									<td><%=mvo.getMovie_name() %></td>
									<td><fmt:formatDate value="<%=mvo.getSession_time() %>" pattern="yyyy年MM月dd日  HH點mm分" /></td> 
									<td><%=mvo.getPrice() %></td>
									<td><%=mvo.getAmount() %></td>
									<td>
										<form method="post" action="<%=request.getContextPath()%>/ticketOrder/ticketOrder.do">
												<input type="hidden" name="action" value="search_ticketDetail_seats"/>
												<input type="hidden" name="uuid" value="<%=mvo.getUuid() %>"/>
												<input type="hidden" name="session_no" value="<%=mvo.getSession_no() %>" />
												<input type="hidden" name="price" value="<%=mvo.getPrice() %>" />
												<input type="hidden" name="amount" value="<%=mvo.getAmount()%>" />
												<button class="btn btn-primary" style="font-size:20px" type="submit">訂單明細</button>
										</form>
									</td>
								</tr>
								<%}
								}%>
						</table>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-12 text-center" style="font-size:20px;margin-top:20px;">
		<a href="<%=request.getContextPath()%>/index.jsp"><img src="<%=request.getContextPath()%>/img/House-Icon.png" style="height:40px;edith:40px;"></a>
	</div>
</body>

<%@ include file="/forestage/template/footer.jsp"%>
</html>