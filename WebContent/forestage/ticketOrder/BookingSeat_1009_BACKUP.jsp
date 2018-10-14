<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@page import="com.ticketType.model.TypeService"%>
<%@page import="com.ticketType.model.TypeVO"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.theater.model.TheaterVO"%>
<%@page import="jdk.nashorn.internal.parser.JSONParser"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.theater.model.*"%>
<%@ page import="com.session.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.movie.model.*" %>
<%@ page import="org.json.*"%>
<%@ page import="java.io.Reader"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
	//�|���s��
	String mem_no="M0001";
//  session.getAttribute("mem_no");
	
	//�����s��
	String session_no = "SES0000004";
// 	session.getAttribute("session_no");

	//���ؽs��
	String type_no = "TT00001";
// 	session.getAttribute("type_no");3333
	
	SessionService sessionSvc = new SessionService();
	MovieService movieSvc = new MovieService();
	SessionVO sessionVO = sessionSvc.getOneSession(session_no);
	
	//���o�q�v��T  movieVO.getMovie_name();
	String movie_no = sessionVO.getMovie_no();
	MovieVO movieVO = movieSvc.getOneMovie(movie_no);
	String movieName = movieVO.getMovie_name(); //�q�v�W��
// 	String movieName = "=== M O V I E N A M E ===";
	 
	//���o�����ɶ�
	Timestamp sessionVOtime = sessionVO.getSession_time();
	String strDateFormat = "HH:mm";
    SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
    String session_time= sdf.format(sessionVOtime); //����ɶ�
	System.out.println("========"+session_time);
	
	//���o���ظ�T
	TypeService typeSvc = new TypeService();
	TypeVO typeVO = typeSvc.getOneType(type_no);
	Integer price = typeVO.getPrice(); //����
	String identity = typeVO.getIdentity(); //���H��
	
	//���o�v�U��T 
	String theater_no = sessionVO.getTheater_no();
	TheaterService tSvc = new TheaterService();
	TheaterVO theaterVO = tSvc.getOneTheater(theater_no);
	String theater_name = theaterVO.getTheater_name(); //�v�U�W��
	
	
	
	
	String sid = request.getSession().getId();
	System.out.println("====servletSession:" + sid);
%>
<%-- <c:set var="session_NO" value="${sessionVO.session_no}" /> --%>
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
       
        
<!--        <div id="myID" class="WebSocket"></div> -->
			<span id="output" class="WebSocket"></span>
		<br>
		<!------------------------------------------------------------------------------------------------->
		<form method="post" action="BookingBuySeat.jsp" name="form1">
		<table class="table">
			<tr>
				<td><img style="width:250px; height:320px" id="movie_pic" src="<%=request.getContextPath() %>/DBGifReader?movie_no=<%=movie_no%>"></td>
			</tr>
		</table>
		<table class="wwFormTable" style="width:40%; ">
			<tr>
				<td><b>�v�U:<%=theater_name%></b></td>
				<td><b>����ɶ��G<%= session_time %></b></td>
				
				<!-- <td id="amount" style="color:#fff; font-weight:bold">���B�G0</td> -->
<%-- 				<td><b>�U�|�W��:</b> <input type="text" name="hall_name" value="<%=theaterVO.getTheater_name()%>" class="text-field"></td> --%>
			</tr>
			<tr>
				<td colspan="2" style="border-radius: 10px;"><b>�q�v�W�١G<%= movieName %></b></td>
			</tr>
		</table>
		<br>
		<table class="wwFormTable">
			<tr>
				<td><b>�ù�</b></td>
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
							<input type="checkbox" id="checkboxG${s1.count}_${s2.count}" ${isBooked? "class='css-checkbox2-red '":"class='css-checkbox1-blue'"} name="seats" value="${s1.count}_${s2.count}" onclick="sendMessage('${s1.count}_${s2.count}'); isChecked();" ${isBooked? "checked disabled":""}> 
							<label id="${s1.count}_${s2.count}" for="checkboxG${s1.count}_${s2.count}" ${isBooked? "class='css-label2-red'":"class='css-label1-blue'"}></label> <!-- ${s1.count}_${s2.count} -->
						</td>
					</c:forEach>
				</tr>
			</c:forEach>		
		</table>

		<hr>
		<input type="hidden" name="movie_name" value="<%=movieName%>">
		<input type="hidden" name="theater_name" value="<%=theater_name%>">
		<input type="hidden" name="type_no" value="<%=type_no %>" />
		<input type="hidden" name="session_no" value="<%=session_no %>" />
		<input type="hidden" name="price" value="<%=price %>" />
		<input class="myButton" type="button" value="�U�@�B" onClick="checkup()">
	</form>

  </div>
  
  
  
        <script type="text/javascript">
		
			<%if (sessionVO != null) {

				JSONObject json = new JSONObject(sessionVO.getSeat_table());
				System.out.println(json);
				JSONArray ary = null;%>
				
			<%Iterator<?> keys = json.keys();
				while (keys.hasNext()) {
					String key = (String) keys.next();
					ary = json.getJSONArray(key);

					switch ((String) ary.get(1)) {

						case "0" :%>
						document.getElementById("<%=key%>").style.visibility="hidden";
						<%break;
						
						case "1" :%>
							document.getElementById("<%=key%>").className="css-purple";
							document.getElementById("checkboxG<%=key%>").disabled = true;
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
		var MyPoint = "/MyBookingServer2/<%=session_no%>/<%=mem_no%>";
		var host = window.location.host;
		//���oweb�D��������W host:localhost:8081
		var path = window.location.pathname;
		//���o�ثe���������|�M�ɮצW path:/0927-T3MS/forestage/template/Booking.jsp
		var webCtx = path.substring(0, path.indexOf('/', 1)); 
		var endPointURL = "ws://" + host + webCtx + MyPoint;
		var webSocket;
		var myID;

		function connect() {
			// �إ� websocket ����
			webSocket = new WebSocket(endPointURL);
			
			webSocket.onopen = function(event){
				console.log("WebSocket Connection Success");
			}

			webSocket.onmessage = function(event) {
// 				var data = event.data;
// 				console.log("data"+data);
				if (event.data.indexOf('myID=') == 0) {
<%-- 					myID = "<%=mem_no%>"; --%>
					myID = event.data.substring(5);
	console.log("myid=" + myID);
					// document.getElementById("myID").innerHTML = event.data;
	console.log("event.data*:" + event.data);
					// event.data=@true@1-3@1b
				} else if ((event.data).substring(0, 1) == '@') {
					check_WebSocket(event.data);
					console.log("event...data=" + event.data);
				} else {
					//�����s�ɶ�
					var mySpan = document.getElementById("output");
					mySpan.innerHTML = event.data;
				}
			};

			webSocket.onerror = function(event) {
				console.log("Error ", event);
			}

			webSocket.onclose = function(event) {
				var mySpan = document.getElementById("output");
				mySpan.innerHTML = "WebSocket�s�u�w����";
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
		//********************************** �]�w�s���v�� **********************************

		// �e�X�e���ˬd
		function checkup() {
// 			if (form1.hall_name.value == "") {
// 				window.alert("�п�J�U�|�W��");
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
				window.alert("�п�ܤ@�Ӯy��");
				return;
			}

			form1.submit(); //�e�X��椤�����
		}
	</script>
        
        <!-- footer -->
        
        <%@ include file="/forestage/template/footer.jsp" %>
        <script src="<%=request.getContextPath()%>/js/template.js"></script>
       
        <script>
        $(document).ready(function(){
            $("li:contains('�X�@�v��')").addClass("custom-active");
        });
        </script>
        
 <script type="text/javascript">
	function isChecked(){
		
		var count = 0;
		for(var i=1 ; i <= <%=theaterVO.getT_rows()%>; i++){		
			
			for(var j=1 ; j <= <%=theaterVO.getT_columns()%>; j++){
				if($("#checkboxG"+i+"_"+j).is(":checked")) {
					$("#amount").html("���B�G"+(200*count));
					console.log('=============count:'+count);
				}
				
				if(!($(":checkbox").is(":checked"))){
					$("#amount").html("���B�G0");
				}
			}
		}
	}
 </script>
    </body>
</html>