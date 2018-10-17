<%@page import="com.cinema.model.CinemaVO"%>
<%@page import="com.cinema.model.CinemaService"%>
<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@page import="com.ticketType.model.TypeService"%>
<%@page import="com.ticketType.model.TypeVO"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.theater.model.TheaterVO"%>
<%@page import="jdk.nashorn.internal.parser.JSONParser"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.theater.model.*"%>
<%@ page import="com.session.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.movie.model.*"%>
<%@ page import="org.json.*"%>
<%@ page import="java.io.Reader"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
	//會員編號
	// 	String mem_no="M001";
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	String mem_no = memVO.getmemno();

	//場次編號
	// 	String session_no = "SES0000004";
	String session_no = request.getParameter("session_no").trim();

	//票種編號
	TypeService TypeSvc = new TypeService();

	// 	String type_no = "TT00001";

	SessionService sessionSvc = new SessionService();
	MovieService movieSvc = new MovieService();
	SessionVO sessionVO = sessionSvc.getOneSession(session_no);
	String theater_no = sessionVO.getTheater_no(); //影廳編號

	TypeVO typeVO = TypeSvc.getOneTypeByTheaterNo(theater_no);
	String type_no = typeVO.getType_no();

	//取得電影資訊  movieVO.getMovie_name();
	String movie_no = sessionVO.getMovie_no();
	MovieVO movieVO = movieSvc.getOneMovie(movie_no);
	String movieName = movieVO.getMovie_name(); //電影名稱
	Date date = movieVO.getRelased(); // 上映日期
	String rating = movieVO.getRating(); // 電影級別

	//取得場次時間
	Timestamp sessionVOtime = sessionVO.getSession_time();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	String session_time = sdf.format(sessionVOtime); //播放時間

	//取得票種資訊
	TypeService typeSvc = new TypeService();
	Integer price = typeVO.getPrice(); //票價
	String identity = typeVO.getIdentity(); //成人票

	//取得影廳資訊 
	// 	String theater_no = "T00001";
// 	String theater_no = sessionVO.getTheater_no();
	TheaterService tSvc = new TheaterService();
	TheaterVO theaterVO = tSvc.getOneTheater(theater_no);
	String theater_name = theaterVO.getTheater_name(); //影廳名稱
	String cinema_no = theaterVO.getCinema_no(); //影城編號

	//影城資訊
	CinemaService cinemaSvc = new CinemaService();
	CinemaVO cinemaVO = cinemaSvc.getOneCinema(cinema_no);
	String cinemaName = cinemaVO.getCinema_name(); //影城名稱

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
<%@ include file="/forestage/template/link.jsp"%>
<title>M&amp;S</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Booking.css" />

</head>
<body onLoad="connect();" onunload="disconnect();" class="body-template">
	<%@ include file="/forestage/template/header.jsp"%>

	<br>
	<br>
	<br>
	<!--     <div class="container" style="color: #ffffff;font-size: 20px;"> -->
	<form method="post" action="BookingBuySeat.jsp" name="form1">
		<div class="row">
			<div class="col-sm-8 col-md-8">
				<table class="wwFormTable" style="width: 60%;">
					<tr>
						<td>
							<b>螢幕</b>
						</td>
					</tr>
				</table>
				<table class="wwFormTable" style="width: 80%; color: #fff;">
					<tr>
						<td>
							<b>
								<img src="<%=request.getContextPath()%>/img/sofa-skyblue.png" />
								&nbsp;&nbsp;可選位
							</b>
						</td>
						<td>
							<b>
								<img src="<%=request.getContextPath()%>/img/sofa-blue.png" />
								&nbsp;&nbsp;已選位
							</b>
						</td>
						<td>
							<b>
								<img src="<%=request.getContextPath()%>/img/sofa-yellow.png" />
								&nbsp;&nbsp;保留位
							</b>
						</td>
						<td>
							<b>
								<img src="<%=request.getContextPath()%>/img/sofa-red2.png" />
								&nbsp;&nbsp;選位中
							</b>
						</td>
						<td>
							<b>
								<img src="<%=request.getContextPath()%>/img/sofa-purple.png" />
								&nbsp;&nbsp;已售出
							</b>
						</td>
					</tr>
				</table>


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
								<b>
									<script>document.write(String.fromCharCode(64+ ${s1.count}));</script>
								</b>
							</td>
							<c:forEach var="col" begin="1" end="<%=theaterVO.getT_columns()%>" varStatus="s2">
								<c:set scope="page" var="isBooked" value="${bookingMap.keySet().contains(s1.count.toString().concat('_').concat(s2.count.toString()))}" />
								<td class="left1">
									<input type="checkbox" id="checkboxG${s1.count}_${s2.count}" ${isBooked? "class='css-checkbox2-red '":"class='css-checkbox1-blue'"} name="seats" value="${s1.count}_${s2.count}" onclick="sendMessage('${s1.count}_${s2.count}'); isChecked();" ${isBooked? "checked disabled":""}>
									<label id="${s1.count}_${s2.count}" for="checkboxG${s1.count}_${s2.count}" ${isBooked? "class='css-label2-red'":"class='css-label1-blue'"}></label>
									<!-- ${s1.count}_${s2.count} -->
								</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
				<!--        			 col-sm-offset-2 col-md-offset-2 -->
			</div>
			<div class="col-sm-3 col-md-3">
				<table class="wwFormTable" style="width: 100%; font-size: 25px;">
					<tr>
						<td>
							<b>
								影廳：<%=theater_name%></b>
						</td>
						<td>
							<b>
								級別：<%=rating%></b>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<b>
								影城：<%=cinemaName%></b>
						</td>
					</tr>
				</table>

				<table class="wwFormTable" style="width: 100%; font-size: 25px;">
					<tr>
						<td colspan="2">
							<b>
								場次時間：<%=session_time%></b>
						</td>
					</tr>
				</table>

				<table class="wwFormTable" style="width: 100%;">
					<tr>
						<td colspan="2">
							<b>
								電影名稱：<%=movieName%></b>
						</td>
					</tr>
				</table>

				<table class="wwFormTable">
					<tr>
						<td>
							<img style="width: 350px; height: 450px" id="movie_pic" src="<%=request.getContextPath()%>/DBGifReader?movie_no=<%=movie_no%>">
						</td>
					</tr>
				</table>
			</div>
		</div>

		<!------------------------------------------------------------------------------------------------->
		<hr>
		<input type="hidden" name="mem_no" value="<%=mem_no%>" />
		<input type="hidden" name="movie_no" value="<%=movie_no%>" />
		<input type="hidden" name="movie_name" value="<%=movieName%>" />
		<input type="hidden" name="theater_name" value="<%=theater_name%>" />
		<input type="hidden" name="type_no" value="<%=type_no%>" />
		<input type="hidden" name="session_no" value="<%=session_no%>" />
		<input type="hidden" name="price" value="<%=price%>" />
		<button type="button" class="btn btn-lg btn-primary btn-block" onClick="checkup()" id="save">下一步</button>
		<!-- 		<input class="myButton" type="button" value="下一步" onClick="checkup()"> -->
	</form>

	<div class="col-sm-12 text-center" style="font-size:20px;margin-top:20px;">
		<a href="<%=request.getContextPath()%>/index.jsp"><img src="<%=request.getContextPath()%>/img/House-Icon.png" style="height:40px;edith:40px;"></a>
	</div>
	<%@ include file="/forestage/template/footer.jsp"%>
	

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
				window.alert("請選擇一個座位");
				return;
			}

			form1.submit(); //送出表單中的資料
		}
	</script>

	<!-- footer -->

	<script src="<%=request.getContextPath()%>/js/template.js"></script>

	<script>
        $(document).ready(function(){
            $("li:contains('合作影城')").addClass("custom-active");
        });
        </script>

	<script type="text/javascript">
	function isChecked(){
		
		var count = 0;
		for(var i=1 ; i <= <%=theaterVO.getT_rows()%>; i++){		
			
			for(var j=1 ; j <= <%=theaterVO.getT_columns()%>; j++){
				if($("#checkboxG"+i+"_"+j).is(":checked")) {
					$("#amount").html("金額："+(200*count));
					console.log('=============count:'+count);
				}
				
				if(!($(":checkbox").is(":checked"))){
					$("#amount").html("金額：0");
				}
			}
		}
	}
	</script>
</body>
</html>