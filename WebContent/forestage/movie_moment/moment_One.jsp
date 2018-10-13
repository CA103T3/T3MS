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
	List<SessionVO> list = sessionSvc.getNowMoment();
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
	pageContext.setAttribute("thVO",thVO);
%>

<% SessionVO sessionVO = (SessionVO) request.getAttribute("sessionVO"); %>

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
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.datetimepicker.css" />
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
	top: 230px;
	z-index: 999;
}

#time{
margin-top: 12%;
width: 90px;
padding: 5px;
}


#ctype{
width:100px;
height:55px;
background-color:rgba(29, 26, 26, 0.829);
box-shadow:10px 10px 10px #888;
padding:10px;
margin:10px;
text-align: center;
color: #fff;
border-radius: 3px;
font-weight: 300;
}
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}

.control-label{margin-top:20px;font-size:20px;}
.btn-info{position: absolute;margin-top:50px;}
</style>

</head>
<body class="body-template">
	<%@ include file="/forestage/template/header_no_bar.jsp"%>



	<div class="row">
		<div class="col-md-12 text-center btnx">
			<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
				data-target="#hanhan" id="btnbtn">播放</button>
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



	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-6">

					<div class="col-xs-12 col-sm-6">
						<img
							src="<%=request.getContextPath() %>/DBGifReader?movie_no=${movieVO.movie_no}"
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

				</div>
			</div>
		</div>
	</div>
	

	<!-- movie Moment   -->
	<div class="section">
		<div class="container">



			<div class="tab">
				<button class="tablinks" onclick="openCity(event, 'Moment')"
					id="defaultOpen">電影時刻</button>
				<button class="tablinks" onclick="openCity(event, 'Introd')">電影簡介</button>
			</div>

			<div id="Moment" class="tabcontent">
						
																		
			<!-- ---------------------------------------------------電影日期------------------------------------------------------------- -->
			<form METHOD="post" ACTION="<%=request.getContextPath()%>/forestage/movie_moment/moment.do" name="form1">
				<div class="container">			
					<div class="col-md-2">	
					
					
					   <%
                            //https://yq.aliyun.com/articles/70182
                            if(sessionVO != null) {
                                if(sessionVO.getSession_time() != null) {
                                    Timestamp tt = sessionVO.getSession_time();
                                    Date date = new Date(tt.getTime());
                                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    String session_time = dateFormat.format(date);
                                    pageContext.setAttribute("session_time", session_time);
                                }
                            }
                        %>
					
					
							<div class="form-group hidden-md hidden-sm has-feedback">
								<label class="control-label">選擇場次日期:</label>
								<input class="form-control" type="text" name="s_time" id="s_time" value="${sessionVO.session_time}">
							</div>		
			    	  </div>
							<input type="hidden" name="action" value="selectD">
							<input type="hidden" name="movie_no" value="${movieVO.movie_no}">
							
							<span><input  type="submit" class="btn btn-info" value="送出"></span>	
				</div>
				
			</form>
			<!-- ---------------------------------------------------電影日期------------------------------------------------------------- -->		
			
			
					
<!-- ----------------當日影城+時刻表--------------------- -->
<!--   ------------------  影城FOREach   ------------------  -->				
				<c:forEach var="cvo" items="${cvo}">
<%-- 				<c:if test="${sessionVO.session_time!=null}"> --%>
				

					<div class="panel panel-primary">

						<div class="panel-heading">
							<h3 class="panel-title">${cvo.cinema_name}</h3>


						</div>


						<div class="panel-body">
							<div class="row">


									<c:forEach var="sessionVO" items="${list}">
											<c:if test="${(sessionVO.cinemaVO.cinema_no==cvo.cinema_no) and (movieVO.movie_no==sessionVO.movie_no) and (sessionVO.theaterVO.theater_no==theaterVO.theater_no)}">
<!--   ------------------  時刻FOREach   ------------------  -->	
	
								<div class="col-md-3">

									<div class="row">
								
										<div class="col-md-6"> 
											<div id="ctype">
												<h4>${sessionVO.theaterVO.equipment}</h4>
											</div>	
										</div>	
																													
										<div class="col-md-1 text-center">
											<div class="sisson">
												<div id="time" style=" padding-top: 29px;">
												<a href="<%=request.getContextPath()%>/forestage/ticketOrder/BookingSeat.jsp?session_no=${sessionVO.session_no}" class="card-img-a2">
													<fmt:formatDate value="${sessionVO.session_time}"
														pattern="MM/dd HH:mm" /></a>
														
												</div>
											</div>
										</div>

									</div>

								</div>
											</c:if>
										</c:forEach>

							</div>
						</div>

					</div>

<%-- </c:if> --%>
				</c:forEach>
				<!-- ----------------當日影城+時刻表--------------------- -->
			</div>

<%if (request.getAttribute("selectD")!=null){%>
       <jsp:include page="tom_Moment.jsp" />
<%} %>

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





<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.datetimepicker.full.js"></script>

	<!-- ----------------moment One ----------------->
	<%@ include file="/forestage/template/footer.jsp"%>
     
	<script src="<%=request.getContextPath()%>/js/template.js"></script>
	<script>
        $(document).ready(function(){
            $("li:contains('電影資訊')").addClass("custom-active");
            
            var somedate1 = new Date();
            var somedate2 = new Date(somedate1.getTime()+2*24*60*60*1000);
            $('#s_time').datetimepicker({
                theme: '',          //theme: 'dark',
                timepicker: false,   //timepicker: false,
                step: 1,            //step: 60 (這是timepicker的預設間隔60分鐘)
     	       format: 'Y-m-d',
     	       value: new Date(),
                //disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
                //startDate:	        '2017/07/10',  // 起始日
	     	    minDate:somedate1,
	            maxDate:somedate2
             });
                             
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
                tablinks[i].className = tablinks[i].className.replace(" active", "");
            }
            document.getElementById(cityName).style.display = "block";
            evt.currentTarget.className += " active";
        }
        // Get the element with id="defaultOpen" and click on it
        document.getElementById("defaultOpen").click();
      </script>


</body>

</html>