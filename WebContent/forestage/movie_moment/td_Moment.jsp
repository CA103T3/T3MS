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




<html>
<head><title></title>

<style>
 
</style>

</head>
<body>











<!-- 取得系統日期 -->

				<%
					Calendar cal = Calendar.getInstance();
				

					cal.setTime(cal.getTime());
				
					// 			取得日期 一個禮拜
					cal.add(Calendar.DATE, 0);
					

					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

					String tStr = format.format(cal.getTime());
				
					System.out.println(tStr);
				%>

				<!-- 取得系統日期 -->






	
	
<!-- ----------------當日影城+時刻表--------------------- -->
				
<!--   ------------------  影城FOREach   ------------------  -->				
				<c:forEach var="cvo" items="${cvo}">
				

					<div class="panel panel-primary" style="margin-top:30px!important;">

						<div class="panel-heading">
							<h3 class="panel-title">${cvo.cinema_name}</h3>


						</div>


						<div class="panel-body">
							<div class="row">


									<c:forEach var="sessionVO" items="${list}">
											<c:if test="${(sessionVO.cinemaVO.cinema_no==cvo.cinema_no) and (movieVO.movie_no==sessionVO.movie_no) and (sessionVO.theaterVO.theater_no==theaterVO.theater_no)
											and (!empty sessionVO.session_time)}">
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

				</c:forEach>
				<!-- ----------------當日影城+時刻表--------------------- -->

			



			



</body>
</html>