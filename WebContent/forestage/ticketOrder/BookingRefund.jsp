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
    </head>
    <body class="body-template">
        <%@ include file="/forestage/template/header.jsp" %>
        <div class="container text-center" style="color: #ffffff;font-size: 20px;"><br><br><br>
        <!-- ==========================start============================= -->
       		  <div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 text-center">
						<div class="panel panel-default">
						  <div class="panel-heading text-center">請選擇要退票的座位</div>
							<form method="post" action="<%=request.getContextPath()%>/ticketOrder/ticketOrder.do?price=<%=request.getParameter("price") %>&amount=<%=request.getParameter("amount") %>&uuid=<%=request.getParameter("uuid")%>&session_no=<%=request.getParameter("session_no") %>">
								    <%
								    String str = "";
								   
										String[] seatArr = request.getParameterValues("seat");
										
										for(int i=0; i<seatArr.length; i++){
											String[] s = seatArr[i].split("_");
											String temp = (char)(Integer.parseInt(s[0]) + 64) +" 排 "+ s[1] + " 號";
											str+=seatArr[i]+"@";
									%>
										<button type="button" class="btn btn-lg btn-info" data-toggle="modal" data-target="#myModal<%=s[0]+"_"+s[1]%>"><%=temp %></button>
								<!-- ============================================================================== -->
											<div class="modal fade" id="myModal<%=s[0]+"_"+s[1]%>" role="dialog">
												<div class="modal-dialog">
													<div class="modal-content">
														<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal">&times;</button>
															<h3 class="modal-title" style="color:#000;">退票</h3>
														</div>
														<div class="modal-body" style="color:#000;">
															<p>是否退票? 位置:【<%=temp %>】</p>
														</div>
														<div class="modal-footer">
															<button type="submit" id="seat<%=s[0]+"_"+s[1]%>" class="btn btn-danger" value="<%=s[0]+"_"+s[1]%>">退票</button>
														</div>
													</div>
												</div>
											</div>
								<!-- ============================================================================== -->			
										<script>
											$("#seat<%=s[0]+"_"+s[1]%>").click(function(){
												$('#a_seat').val("<%=s[0]+"_"+s[1]%>");
											});
										</script>
								<% }%>
										<input type="hidden" name="a_seat" id="a_seat" value="" />
<%-- 										<input type="hidden" name="session_no" value=<%=request.getParameter("session_no") %> /> --%>
<%-- 										<input type="hidden" name="uuid" value="<%=request.getParameter("uuid")%>"/> --%>
<%-- 										<input type="hidden" name="price" value="<%=request.getParameter("price") %>" /> --%>
<%-- 										<input type="hidden" name="amount" value="<%=request.getParameter("amount") %>" /> --%>
										<input type="hidden" name="delSeats" value="<%=str%>"/>
										<input type="hidden" name="action" value="del_ticket_open_seat"/>
							</form>
						</div>
					</div>
						<a href="<%=request.getContextPath()%>/forestage/member/ones_orderlist.jsp">
							<button type="button" class="btn btn-lg btn-primary text-center">上一頁</button>
						</a>
				</div>
		<!-- ==========================End============================= -->        
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