<%@page import="com.cinema.model.CinemaService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.*"%>
<%@ page import="com.movie.model.*"%>
<%@ page import="com.session.model.*"%>
<%@ page import="com.cinema.model.*"%>
<%@ page import="com.theater.model.*"%>


<%
	MovieService movieSvc = new MovieService();
	MovieVO movieVO = movieSvc.getOneMovie(request.getQueryString());
	pageContext.setAttribute("movieVO", movieVO);
%>

<%
	SessionService sessionSvc = new SessionService();
	List<SessionVO> list = sessionSvc.getNowMoment(movieVO.getMovie_no());
	pageContext.setAttribute("list", list);
%>
<%
	CinemaService cinemaSvc = new CinemaService();
	List<CinemaVO> cvo = cinemaSvc.getAll();
	pageContext.setAttribute("cvo", cvo);
%>

<%
	TheaterService theaterSvc = new TheaterService();
	List<TheaterVO> thVO = theaterSvc.getAll();
	pageContext.setAttribute("thVO", thVO);
%>

<%
	SessionVO sessionVO = (SessionVO) request.getAttribute("sessionVO");
%>

<jsp:useBean id="now" scope="page" class="java.util.Date" />

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<!--star-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/m_Moment.css">
<link href="<%=request.getContextPath()%>/css/introduceM.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/T3MS/css/btn_moment.css">
<link rel="stylesheet" href="/T3MS/css/btn_light.css">

<%@ include file="/forestage/template/link.jsp"%>
<title>M&amp;S</title>
<style>
body {
	background-color: #fff !important
}

.container-fluid {
	background-color: #404040;
	height: 100px;
}

.text-warning {
	color: #ffe661;
}

.btnx {
	position: absolute;
	top: 260px;
	left: 10px;
	z-index: 999;
}

#time {
	margin-top: 12%;
	width: 90px;
	padding: 5px;
	font-size:14px;
}

#ctype {
	width: 100px;
	height: 55px;
	background-color: rgba(29, 26, 26, 0.829);
	box-shadow: 10px 10px 10px #888;
	padding: 10px;
	margin: 10px;
	text-align: center;
	color: #fff;
	border-radius: 3px;
	font-weight: 300;
}

.control-label {
	margin-top: 20px;
	font-size: 20px;
}

.btn-info {
	position: absolute;
	margin-top: 50px;
}

.breadcrumb-item {
	font-size: 18px;
}

.pulse-button:hover {
	background-color: #e7008c;
}

.love_m{font-size:20px}
</style>

</head>
<body class="body-template">
	<%@ include file="/forestage/template/header.jsp"%>



	<div class="row">
		<div class="col-md-12 btnx">
			<button type="button" class="pulse-button" data-toggle="modal"
				data-target="#hanhan" id="btnbtn" style="background: #337ab7;">播放</button>
		</div>
	</div>

	<!-- ==============================================新增跳出的燈箱============================================== -->
	<div class="modal fade" id="hanhan" tabindex="-1" role="dialog"
		aria-labelledby="hanhan" aria-hidden="true">
		<form
			action="<%=request.getContextPath()%>/backstage/announcement/ann.do"
			method="POST">
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h3 class="modal-title" id="myModalLabel">預告片</h3>
					</div>

					<div class="modal-body">
						<div class="embed-responsive embed-responsive-16by9">
							<iframe class="embed-responsive-item"
								src="${movieVO.trailer_url}"></iframe>
						</div>
					</div>

					<div class="modal-footer">

						<button type="button" class="btn btn-danger" data-dismiss="modal">關閉</button>

					</div>
				</div>
			</div>
		</form>
	</div>


	<!-- ==============================================新增跳出的燈箱============================================== -->





	<!-- ----------------moment One ----------------->


	<div class="youtudem">

		<iframe width="100%" height="315" src="${movieVO.trailer_url}"
			frameborder="0" allow="autoplay; encrypted-media" allowfullscreen>

		</iframe>
	</div>



	<div class="section" style="padding-top: 230px !important;">
		<div class="container">
			<div class="row">
				<div class="col-md-6">

					<div class="col-xs-12 col-sm-6">
						<img
							src="<%=request.getContextPath()%>/DBGifReader?movie_no=${movieVO.movie_no}"
							class="img-responsive img-rounded" id="imgm">
					</div>

					<div class="col-xs-12 col-sm-6">

						<h2 class="text-left" id="tittle">${movieVO.movie_name}</h2>
						<p class="text-left">${movieVO.eng_name}</p>
						<div class="label label-default">${movieVO.rating}</div>
						<div class="time2">上映日期 : ${movieVO.relased}</div>
						<div class="time2">片長 : ${movieVO.length}分鐘</div>
						<div class="sor">MS評分:</div>
						<span
							class="fa fa-star ${(movieVO.imdb >=2.0)? 'text-warning':''}"></span>
						<span
							class="fa fa-star ${(movieVO.imdb >=4.0)? 'text-warning':''}"></span>
						<span
							class="fa fa-star ${(movieVO.imdb >=6.0)? 'text-warning':''}"></span>
						<span
							class="fa fa-star ${(movieVO.imdb >=8.0)? 'text-warning':''}"></span>
						<span
							class="fa fa-star ${(movieVO.imdb >=10.0)? 'text-warning':''}"></span>
						<div class="sor">IMDB:</div>
						<div class="sor1">${movieVO.imdb}</div>
						<div class="sor">爛番茄:</div>
						<div class="sor1">${movieVO.tomato}</div>
					</div>

					<!-- ---------------------電影收藏------------------------------ -->
					<div>
						<img src="<%=request.getContextPath()%>/img/Test_UP_IMG/heart.png"
							id="heart" title="加入收藏"> <span class="love_m">電影收藏</span>
					</div>
					<!-- ---------------------電影收藏------------------------------ -->

				</div>
			</div>
		</div>

	
		
	</div>




	<!-- movie Moment   -->
	<div class="section">
		<div class="container">


			<div class="container">
				<div class="col-md-12">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a
							href="<%=request.getContextPath()%>/index.jsp">首頁</a></li>
						<li class="breadcrumb-item"><a
							href="<%=request.getContextPath()%>/forestage/movie_moment/moment_Home.jsp">現正熱映</a></li>
						<li class="breadcrumb-item active">電影時刻</li>
					</ol>
				</div>
			</div>

			<div class="tab">
				<button class="tablinks" onclick="openCity(event, 'Moment')"
					id="defaultOpen">電影時刻</button>
				<button class="tablinks" onclick="openCity(event, 'Introd')">電影簡介</button>
			</div>

			<div id="Moment" class="tabcontent">


				<!-- ---------------------------------------------------電影日期------------------------------------------------------------- -->


				<!-- 取得系統日期 -->

				<%
					Calendar cal = Calendar.getInstance();
					Calendar cal1 = Calendar.getInstance();
					Calendar cal2 = Calendar.getInstance();
					Calendar cal3 = Calendar.getInstance();
					Calendar cal4 = Calendar.getInstance();
					Calendar cal5 = Calendar.getInstance();

					cal.setTime(cal.getTime());
					cal1.setTime(cal.getTime());
					cal2.setTime(cal.getTime());
					cal3.setTime(cal.getTime());
					cal4.setTime(cal.getTime());
					cal5.setTime(cal.getTime());
					// 			取得日期 一個禮拜
					cal.add(Calendar.DATE, 1);
					cal1.add(Calendar.DATE, 2);
					cal2.add(Calendar.DATE, 3);
					cal3.add(Calendar.DATE, 4);
					cal4.add(Calendar.DATE, 5);
					cal5.add(Calendar.DATE, 6);

					SimpleDateFormat format = new SimpleDateFormat("MM/dd");

					String tomorrowStr = format.format(cal.getTime());
					String three = format.format(cal1.getTime());
					String four = format.format(cal2.getTime());
					String five = format.format(cal3.getTime());
					String six = format.format(cal4.getTime());
					String seven = format.format(cal5.getTime());
				%>

				<!-- 取得系統日期 -->




				<label class="control-label">選擇場次日期:</label>
				<div class="container" style="margin-top: 20px;">
					<div class="row">
						<form METHOD="post"
							ACTION="<%=request.getContextPath()%>/forestage/movie_moment/moment.do"
							name="form1">
							<div class="col-md-1">



								<input type="hidden" name="action" value="selectA"> <input
									type="hidden" name="movie_no" value="${movieVO.movie_no}">
								<span><input type="submit" class="pulse-button"
									value="<fmt:formatDate value="${now}" pattern="MM/dd"/>"
									style="margin-top: 0px !important"></span>

							</div>
						</form>
						<form METHOD="post"
							ACTION="<%=request.getContextPath()%>/forestage/movie_moment/moment.do"
							name="form2">
							<div class="col-md-1">



								<input type="hidden" name="action" value="selectB"> <input
									type="hidden" name="movie_no" value="${movieVO.movie_no}">
								<span><input type="submit" class="pulse-button"
									value="<%=tomorrowStr%>" style="margin-top: 0px !important"></span>
							</div>
						</form>
						<form METHOD="post"
							ACTION="<%=request.getContextPath()%>/forestage/movie_moment/moment.do"
							name="form3">
							<div class="col-md-1">



								<input type="hidden" name="action" value="selectC"> <input
									type="hidden" name="movie_no" value="${movieVO.movie_no}">
								<span><input type="submit" class="pulse-button"
									value="<%=three%>" style="margin-top: 0px !important"></span>
							</div>
						</form>
						<form METHOD="post"
							ACTION="<%=request.getContextPath()%>/forestage/movie_moment/moment.do"
							name="form4">
							<div class="col-md-1">



								<input type="hidden" name="action" value="selectD"> <input
									type="hidden" name="movie_no" value="${movieVO.movie_no}">
								<span><input type="submit" class="pulse-button"
									value="<%=four%>" style="margin-top: 0px !important"></span>
							</div>
						</form>
						<form METHOD="post"
							ACTION="<%=request.getContextPath()%>/forestage/movie_moment/moment.do"
							name="form5">
							<div class="col-md-1">



								<input type="hidden" name="action" value="selectE"> <input
									type="hidden" name="movie_no" value="${movieVO.movie_no}">
								<span><input type="submit" class="pulse-button"
									value="<%=five%>" style="margin-top: 0px !important"></span>
							</div>
						</form>
						<form METHOD="post"
							ACTION="<%=request.getContextPath()%>/forestage/movie_moment/moment.do"
							name="form6">
							<div class="col-md-1">



								<input type="hidden" name="action" value="selectF"> <input
									type="hidden" name="movie_no" value="${movieVO.movie_no}">
								<span><input type="submit" class="pulse-button"
									value="<%=six%>" style="margin-top: 0px !important"></span>
							</div>
						</form>
						<form METHOD="post"
							ACTION="<%=request.getContextPath()%>/forestage/movie_moment/moment.do"
							name="form7">
							<div class="col-md-1">



								<input type="hidden" name="action" value="selectG"> <input
									type="hidden" name="movie_no" value="${movieVO.movie_no}">
								<span><input type="submit" class="pulse-button"
									value="<%=seven%>" style="margin-top: 0px !important"></span>
							</div>
						</form>


					</div>
				</div>


				<!-- ---------------------------------------------------電影日期------------------------------------------------------------- -->



				<!-- ----------------當日影城+時刻表--------------------- -->
				<!--   ------------------  影城FOREach   ------------------  -->



				<%
					if (request.getAttribute("selectA") != null) {
				%>
				<jsp:include page="td_Moment.jsp" />
				<%
					;
					} else if (request.getAttribute("selectB") != null) {
				%>
				<jsp:include page="tom_Moment.jsp" />
				<%
					;
					} else if (request.getAttribute("selectC") != null) {
				%>
				<jsp:include page="C_Moment.jsp" />
				<%
					;
					} else if (request.getAttribute("selectD") != null) {
				%>
				<jsp:include page="D_Moment.jsp" />
				<%
					;
					} else if (request.getAttribute("selectE") != null) {
				%>
				<jsp:include page="E_Moment.jsp" />
				<%
					;
					} else if (request.getAttribute("selectF") != null) {
				%>
				<jsp:include page="F_Moment.jsp" />
				<%
					;
					} else if (request.getAttribute("selectG") != null) {
				%>
				<jsp:include page="G_Moment.jsp" />
				<%
					;
					} else if (request.getAttribute("selectA") == null) {
				%>
				<jsp:include page="td_Moment.jsp" />
				<%
					}
				%>

			</div>
			<!-- ----------------當日影城+時刻表--------------------- -->





			<!-- movie_Introd   -->
			<div id="Introd" class="tabcontent">
				<div style="display: inline;">
					<strong>導演:</strong>
				</div>
				<span style="display: inline;">${movieVO.director}</span>
				<div style="display: inline;">
					<strong>演員:</strong>
				</div>
				<span style="display: inline;">${movieVO.starring}</span>
				<p>${movieVO.brief_intro}</p>
			</div>



		</div>
	</div>





	<!-- ----------------moment One ----------------->
	<%@ include file="/forestage/template/footer.jsp"%>

	<script src="<%=request.getContextPath()%>/js/template.js"></script>
	<script>
		$(document).ready(function() {
			$("li:contains('電影資訊')").addClass("custom-active");
		});
	</script>

	<script>
		function openCity(evt, cityName) {
			var i, tabcontent, tablinks;
			tabcontent = document.getElementsByClassName("tabcontent");
			for (i = 0; i < tabcontent.length; i++) {
				tabcontent[i].style.display = "none";
			}
			tablinks = document.getElementsByClassName("tablinks");
			for (i = 0; i < tablinks.length; i++) {
				tablinks[i].className = tablinks[i].className.replace(
						" active", "");
			}
			document.getElementById(cityName).style.display = "block";
			evt.currentTarget.className += " active";
		}
		// Get the element with id="defaultOpen" and click on it
		document.getElementById("defaultOpen").click();
	</script>

<!-- ---------------------電影收藏------------------------------ -->
	<script>
//加入收藏 或 取消收藏
function switchFavorite(){

	var heart = document.getElementById("heart");

	if (heart.title == "加入收藏") {
		heart.src = "<%=request.getContextPath()%>/img/Test_UP_IMG/heart2.png";
		heart.title = "取消收藏" ;

	}else{
		heart.src = "<%=request.getContextPath()%>/img/Test_UP_IMG/heart.png";
		heart.title ="加入收藏";
	}


}

function init(){
  //設定[加入收藏 或 取消收藏]的點按事件
	
  	document.getElementById("heart").onclick = switchFavorite;

}//init
window.onload = init;
</script> 

<!-- ---------------------電影收藏------------------------------ -->

</body>

</html>