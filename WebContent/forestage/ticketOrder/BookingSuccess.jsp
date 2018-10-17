<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.session.model.SessionVO"%>
<%@page import="com.session.model.SessionService"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
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
            
	    <%
		  	//取得要改變狀態(已購買)的座位
			String bookingSeats = request.getParameter("bookingSeats").trim();
	    	String[] bookiStringSeats = bookingSeats.split("@");
	    	int bookingLeng = bookiStringSeats.length;
System.out.println(bookingSeats);

	    	String session_no = request.getParameter("session_no").trim(); //場次編號
	    	SessionService sessionSvc = new SessionService();
	    	SessionVO sessionVO = sessionSvc.getOneSession(session_no);
	    	//取該場次座位，並修改狀態
	    	String seats_table = sessionVO.getSeat_table();  //目前場次座位資訊
	    	JSONObject jsonObject = new JSONObject(seats_table);
	    	JSONArray jsonArray = null;
	    	//將所有key取出  key:1_1，1_2，...
	    	Iterator<?> keys = jsonObject.keys();
	    	while(keys.hasNext()){
	    		String keyStr = (String) keys.next();
System.out.println(keyStr);
				String containStr = null;
				for(int j=0; j<bookingLeng; j++){
					containStr = bookiStringSeats[j];
					if(keyStr.equals(containStr)){
						jsonArray = jsonObject.getJSONArray(keyStr);
System.out.println("==befor=="+jsonArray);
						jsonArray.put(1, "1");
System.out.println("==after=="+jsonArray);
					}
				}
	    	}
	    	
	    	//修改完狀態，準備將狀態寫入SessionVO的seat_table
// 	    	sessionVO.setSeat_table(jsonObject.toString());
// 	    	sessionVO.setSession_no(session_no);
	    	sessionSvc.updateSession(session_no, sessionVO.getTheater_no(), sessionVO.getMovie_no(), sessionVO.getSession_time(), jsonObject.toString());
	    	System.out.println("=========="+jsonObject.toString());
	    %>
	    
    	<%@ include file="/forestage/ticketOrder/genQRcode.jsp" %>
        <%@ include file="/forestage/template/header.jsp" %>
        <div class="container" style="color: #ffffff;font-size: 20px;">
        <!-- ==========================start============================= -->
       
			<%
				String TempFileName = request.getParameter("TempFileName").trim();
				String TempFilePath = request.getParameter("TempFilePath").trim();
				String uuid = request.getParameter("uuid").trim();
				
				String TempResult = genQRCode(140, 140, uuid, TempFilePath + TempFileName);
			%>
			
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12">
						<div class="panel panel-default">
						  <div class="panel-heading text-center">QR Code已寄到您的信箱，請至現場掃描取票，謝謝！</div>
							<table class="table text-center">
								<tr>
									<td><% out.print("<img src='"+ request.getContextPath()+"/img/QRcode/" + TempFileName + "'>");%></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
<%-- 				<form method="post" action="<%=request.getContextPath()%>/ticketOrder/ticketOrder.do"> --%>
<!-- 				<div class="row">  -->
<!-- 					<div class="col-xd-12 col-sm-12 col-md-12"> -->
<!-- 						<div class="panel panel-default"> -->
<!-- 							<div class="panel-heading text-center">***</div> -->
<!-- 							  <button type="submit" class="btn btn-lg btn-danger btn-block">退票</button> -->
<!-- 					    </div> -->
<!-- 					</div> -->
<!-- 				</div> -->
				
				<input type="hidden" name="action" value="search_ticketDetail_seats"/>
				<input type="hidden" name="uuid" value="<%=uuid %>"/>
				<input type="hidden" name="session_no" value="<%=session_no %>" />
				<input type="hidden" name="price" value="<%=request.getParameter("price") %>" />
				<input type="hidden" name="amount" value="<%=request.getParameter("amount") %>" />
				</form>
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