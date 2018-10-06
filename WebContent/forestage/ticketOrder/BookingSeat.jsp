<%@page import="com.theater.model.TheaterVO"%>
<%@page import="jdk.nashorn.internal.parser.JSONParser"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.theater.model.*"%>
<%@ page import="com.session.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="org.json.*"%>
<%@ page import="java.io.Reader"%>

<%
	String memno="M0001";
	
	MemService memSvc = new MemService();

	SessionService sessionSvc = new SessionService();
	SessionVO sessionVO = sessionSvc.getOneSession("SES0000002");
	String theater_no = sessionVO.getTheater_no();
	
	TheaterService tSvc = new TheaterService();
	TheaterVO theaterVO = tSvc.getOneTheater(theater_no);
	
	
	
// 	MemVO memVO = memSvc.getMemVO();
	
	
	
	
	
	
	String sid = request.getSession().getId();
	System.out.println("111:" + sid);
%>
<c:set var="session_NO" value="${sessionVO.session_no}" />
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <%@ include file="/forestage/template/link.jsp" %>
        <title>M&amp;S</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/Booking.css" />
        
    </head>
    <body onLoad="connect();" onunload="disconnect();" class="body-template">
        <%@ include file="/forestage/template/header.jsp" %>
        
    <div class="container" style="color: #ffffff;font-size: 20px;">
        
        
<!--        	 <div id="myID" class="WebSocket"></div> -->
			<span id="output" class="WebSocket"></span>
		<br>
		<!------------------------------------------------------------------------------------------------->
		<form method="post" action="BookingBuySeat.jsp" name="form1">
		<table class="wwFormTable">
			<tr>
			<td><b>廳院:</b><%=theaterVO.getTheater_name()%></td>
<%-- 				<td><b>廳院名稱:</b> <input type="text" name="hall_name" value="<%=theaterVO.getTheater_name()%>" class="text-field"></td> --%>
		</tr>
		</table>

		<table class="wwFormTable">
			<tr>
				<td class="screen">螢幕</td>
		</tr>
		</table>
		<br>
		<table class="wwFormTable">
			<c:forEach var="row" begin="1" end="<%=theaterVO.getT_rows()%>" varStatus="s1">
				
				<tr>
					<c:if test="${row==1}">
						<td>&nbsp;</td>
							<c:forEach var="numRow" begin="1" end="<%=theaterVO.getT_columns()%>" varStatus="r1">
								<td>
									<b>${r1.count}</b>
								</td>
							</c:forEach>
					</c:if>
				</tr>
				
				<tr>
					<td>
						<b><script>document.write(String.fromCharCode(64+ ${s1.count}));</script></b>
					</td>
					<c:forEach var="col" begin="1" end="<%=theaterVO.getT_columns()%>" varStatus="s2">
						<c:set scope="page" var="isBooked" value="${bookingMap.keySet().contains(s1.count.toString().concat('_').concat(s2.count.toString()))}" />
						<td class="left1">
							<input type="checkbox" id="checkboxG${s1.count}_${s2.count}" ${isBooked? "class='css-checkbox2-red '":"class='css-checkbox1-blue'"} name="seats" value="${s1.count}_${s2.count}" onclick="sendMessage('${s1.count}_${s2.count}');" ${isBooked? "checked disabled":""}> 
							<label id="${s1.count}_${s2.count}" for="checkboxG${s1.count}_${s2.count}" ${isBooked? "class='css-label2-red'":"class='css-label1-blue'"}></label> <!-- ${s1.count}_${s2.count} -->
						</td>
					</c:forEach>
							
				</tr>
			</c:forEach>		
		</table>
	
		<hr>
		<input class="myButton" type="button" value="送出新增" onClick="checkup()">
	</form>



	
	
  </div>
        <script type="text/javascript">
		
			<%if (sessionVO != null) {

				JSONObject json = new JSONObject(sessionVO.getSeat_table());
				System.out.println(json);%>
			<%Iterator<?> keys = json.keys();
				JSONArray ary = null;
				while (keys.hasNext()) {
					String key = (String) keys.next();
					ary = json.getJSONArray(key);

					switch ((String) ary.get(1)) {

						case "0" :%>
						document.getElementById("<%=key%>").style.visibility="hidden";
						<%break;

						case "3" :%>
						document.getElementById("<%=key%>").className="css-label1-yellow";
						document.getElementById("checkboxG<%=key%>").disabled = true;
						<%break;
					}%>
		
				<%}%>
		
			<%}%>
	</script>
        
        
        <script type="text/javascript">
		var MyPoint = "/MyBookingServer2/SES0000004/M0004";
		var host = window.location.host;
		//取得web主機的網域名 host:localhost:8081
		var path = window.location.pathname;
		//取得目前頁面的路徑和檔案名 path:/0927-T3MS/forestage/template/Booking.jsp
		var webCtx = path.substring(0, path.indexOf('/', 1)); 
		var endPointURL = "ws://" + host + webCtx + MyPoint;
		var webSocket;
		var myID;

		function connect() {
			// 建立 websocket 物件
			webSocket = new WebSocket(endPointURL);
			

			webSocket.onmessage = function(event) {
	console.log("SSSSS");
	console.log("myid=" + myID);
// 				var data = event.data;
// 				console.log("data"+data);
				if (event.data.indexOf('myID=') == 0) {
					myID = event.data.substring(5);
	console.log("myid=" + myID);
					// document.getElementById("myID").innerHTML = event.data;
	console.log("event.data*:" + event.data);
					// event.data=@true@1-3@1b
				} else if ((event.data).substring(0, 1) == '@') {
					check_WebSocket(event.data);
					console.log("event...data=" + event.data);
				} else {
					//持續更新時間
					var mySpan = document.getElementById("output");
					mySpan.innerHTML = event.data;
				}
			};

			webSocket.onerror = function(event) {
				console.log("Error ", event);
			}

			webSocket.onclose = function(event) {
				var mySpan = document.getElementById("output");
				mySpan.innerHTML = "WebSocket連線已關閉";
			};
		}

		function sendMessage(seat) {
			var b = document.getElementById('checkboxG' + seat).checked;
			webSocket.send('@' + b + '@' + seat + '@' + myID);
		}

		function disconnect() {
			webSocket.close();
		}
	</script>
	<script>
		window.onbeforeunload = function() {
			websocket.close();
			alert('asd');
		}
	</script>

	<script>
		function check_WebSocket(booking) {
			var str = booking.split('@');
			var b = str[1];
			var seat = str[2];
			var id = str[3];
console.log('myID='+myID);
console.log('id='+id);
console.log('seat='+seat);
			if (b == 'true')
				document.getElementById('checkboxG' + seat).checked = true;
			else
				document.getElementById('checkboxG' + seat).checked = false;

			if (myID != id) {
				if (b == 'true')
					document.getElementById('checkboxG' + seat).disabled = true;
				else
					document.getElementById('checkboxG' + seat).disabled = false;

				document.getElementById('checkboxG' + seat).className = 'css-checkbox2-red';
				document.getElementById(seat).className = 'css-label2-red';
			} else {
				document.getElementById('checkboxG' + seat).className = 'css-checkbox1-blue';
				document.getElementById(seat).className = 'css-label1-blue';
			}
		}
		//********************************** 設定群組權限 **********************************

		// 送出前的檢查
		function checkup() {
// 			if (form1.hall_name.value == "") {
// 				window.alert("請輸入廳院名稱");
// 				document.form1.elements(0).focus();
// 				return;
// 			}

			var num = 0;
			var seats = document.getElementsByName("seats");
			for (var i = 0; i < seats.length; i++) {
				if (seats[i].checked && !seats[i].disabled)
					num++;
			}
			if (num == 0) {
				window.alert("請至少選擇一個座位");
				return;
			}

			form1.submit(); //送出表單中的資料
		}
	</script>
        
        
        
        <!-- footer -->
        
        <%@ include file="/forestage/template/footer.jsp" %>
        <script src="<%=request.getContextPath()%>/js/template.js"></script>
       
        <script>
        $(document).ready(function(){
            $("li:contains('合作影城')").addClass("custom-active");
        });
        </script>
    </body>
</html>