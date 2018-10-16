<%@page import="oracle.net.aso.s"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
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
	String movie_no = movieVO.getMovie_no();
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
	List<Movie_Session_Temp_VO> mSessionTemp_VO_list = new ArrayList<>();
	mSessionTemp_VO_list = movieSvc.find_Movie_Session_All(movie_no);
%>



<html>
<head>
<title></title>

<style>
</style>

</head>
<body>
	<%
	Map<String,Integer> cinemaMap = new TreeMap<>();
	Set<String> cinemaSet = new TreeSet<>();
	String[] cinemaArr2 = null;
	int j = 1;
	int b = 0;
	System.out.println("mSessionTemp_VO_list.size="+mSessionTemp_VO_list.size());
	String[] cinemaArr = {"C001","C002","C003","C004","C005","C006","C007","C008","C009",
			"C010","C011","C012","C013","C014","C015","C016","C017","C018","C019","C020",
			"C021","C022","C023","C024","C025","C026","C027","C028","C029","C030",
			"C031","C032","C033","C034","C035","C036","C037","C038","C039","C040"};
	
		for(int i=0; i<mSessionTemp_VO_list.size(); i++){
			cinemaSet.add(mSessionTemp_VO_list.get(i).getCinema_no());
			cinemaArr2 = (String[]) cinemaSet.toArray((new String[cinemaSet.size()]));
			for(int x=0; x<mSessionTemp_VO_list.size(); x++){
				if(mSessionTemp_VO_list.get(x).getCinema_no().equals(cinemaArr[b])){
					cinemaMap.put(cinemaArr[b], j++);
// 					System.out.println("j="+j);
				}
				
			}
			if(b<39){
				b++;	
			}
			
			System.out.println("cinemaMap="+cinemaMap);
			j=1;
		}
		
	%>
	<c:if test="${(empty mSessionTemp_VO_list) or (mSessionTemp_VO_list == null) }">
		<!-- ----------------當日影城+時刻表--------------------- -->
		
			<%for(int i=0; i<cinemaArr2.length; i++){%>
		
			<!--   ------------------  影城FOREach   ------------------  -->
			<div class="panel panel-primary" style="margin-top: 30px !important;">
				<div class="panel-heading">
					<h3 class="panel-title"><%=mSessionTemp_VO_list.get(i).getCinema_name() %></h3>
				</div>
				<!--   ------------------  時刻FOREach   ------------------  -->
				
			<% for(int a=0; a<cinemaMap.get(cinemaArr[a]); a++){
				System.out.println("========"+cinemaMap.get(cinemaArr[a]));
			%>
			
				<div class="panel-body">
					<div class="row">
						<div class="col-md-3">
							<div class="row">
								<div class="col-md-6">
									<div id="ctype">
										<h4><%=mSessionTemp_VO_list.get(a).getEquipment() %></h4>
									</div>
								</div>
								<div class="col-md-1 text-center">
									<div class="sisson">
										<div id="time" style="padding-top: 29px;">
										<%System.out.println("session_time="+mSessionTemp_VO_list.get(a).getSession_time()); %>
											
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<%} %>
			</div>
			<!-- ----------------當日影城+時刻表--------------------- -->
	<% }%>
	</c:if>
</body>
</html>