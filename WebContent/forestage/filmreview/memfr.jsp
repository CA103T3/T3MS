<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.filmreview.model.*"%>
<%@ page import="com.movie.model.*"%>
<%@ page import="com.member.model.*"%>
<%
FilmreviewDAO fd = new FilmreviewDAO();
List<FilmreviewVO> mfvo = fd.findByMem(request.getParameter("mem_no")); 
pageContext.setAttribute("mfvo",mfvo);
%>

<jsp:useBean id="mvSvc" scope="page" class="com.movie.model.MovieService" />
<jsp:useBean id="mSvc" scope="page" class="com.member.model.MemService" />
<!DOCTYPE html>


<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="/T3MS/css/circle1440.css" rel="stylesheet" type="text/css">
<link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css" rel="stylesheet" type="text/css">

<%@ include file="/forestage/template/link.jsp" %>
        <title>M&amp;S</title>



<style>

	body {background-color:#aaa ;}
	
</style>
</head>
<body class="body-template">

  <%@ include file="/forestage/template/header.jsp" %>
 <div id="loader-wrapper">

                <div id="loader"></div>
                <div class="loader-section section-left"></div>
                <div class="loader-section section-right"></div>
        
            </div>
<c:if test="${memVO.memno!=null}">
 <div class="section"style="padding-bottom: 2px;">
	      <div class="container">
	        <div class="row">
	          <div class="col-md-12 text-right">

				    <a href="<%=request.getContextPath()%>/forestage/filmreview/fv_list.jsp" class="btn btn-primary">我的影評</a>


	          </div>
	        </div>
	      </div>
	    </div>
</c:if>


	<div class="section" style=" padding-bottom: 2px;">
		<div class="container" >
			<div class="row">
				<div class="col-md-12">
					<h1 class="text-center" style="color:white !important;">影評搜尋</h1>
				</div>
			</div>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/filmreview/filmreview.do">
				<div class="row">
					<div class="col-md-offset-3 col-md-6">

						<div class="form-group">
							<div class="input-group">


								<input type="text" class="form-control" name="movie_name" placeholder="請輸入電影名稱">
								<div class="input-group-btn">
									<input class="btn btn-primary" type="submit" value="送出" style="border-top-right-radius:5px;border-bottom-right-radius:5px;border-top-width: 0px;border-bottom-width: 0px;height: 34px;"> <input type="hidden" name="action" value="getOne_For_Display">
								</div>


							</div>
						</div>

					</div>
				</div>

			</FORM>
			<div class="row">
				<div class="col-md-12">

					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<div class="page-header">
						<h3 style="color:white !important;">
									您搜尋的作者文章如下:					
							<small style="color: #aaa;">
							
							共<b style="color:#00FFFF;"><% out.print(mfvo.size()); %></b>筆</small>
							
								
						</h3>
					</div>
				</div>
			</div>
		</div>
	</div>








	<c:forEach var="fv" items="${mfvo}" >

		<div class="section" style=" padding-bottom: 2px; padding-top: 2px;">
			<div class="container" style="background-color:white;">
				<div class="row">


					<div class="col-md-1">
						
								<a href="<%=request.getContextPath()%>/forestage/movie_moment/moment_One2.jsp?${fv.movie_no}"> <img src="<%=request.getContextPath() %>/DBGifReader?movie_no=${fv.movie_no}" class="center-block img-circle img-responsive" style="border-radius: 1%;">
								</a>
							
					</div>

					<div class="col-md-6">
						<a href="/T3MS//forestage/filmreview/fv.jsp?fr_no=${fv.fr_no}"><h2>${fv.title}</h2></a>
					</div>

					<div class="col-md-1">
						<h3 class="text-center">${fv.created_at}</h3>
					</div>

					<div class="col-md-1" style="margin-top: 10px;">
						<c:forEach var="mVO" items="${mSvc.all}">
							<c:if test="${fv.mem_no==mVO.memno}">

								<img src="<%=request.getContextPath() %>/DBGifReaderMem?memno=${mVO.memno}" class="center-block img-circle img-responsive">
								<p class="text-center">${mVO.lastname}${mVO.firstname}</p>
							</c:if>
						</c:forEach>
						<c:if test="${fv.mem_no==null}">

								<img src="<%=request.getContextPath() %>/img/M&S-05Z.png" class="center-block img-circle img-responsive">
								<p class="text-center">M&S</p>
							</c:if>
					</div>

					<div class="col-md-2">
						<h3 class="text-center"><c:forEach var="mvVO" items="${mvSvc.all}">
									<c:if test="${fv.movie_no==mvVO.movie_no}">
	                   					 ${mvVO.movie_name}
                    				</c:if>
								</c:forEach></h3>
					</div>

					<div class="col-md-1">
						<h3 class="text-center">${fv.evaluation}</h3>
					</div>

				</div>
			</div>
		</div>


	</c:forEach>
<div class="col-sm-12 text-center" style="font-size:20px;margin-top:20px;">
<a href="/T3MS/forestage/filmreview/fv_home.jsp"><img src="/T3MS/img/House-Icon.png" style="height:40px;edith:40px;"></a>
</div>
	<%@ include file="/forestage/template/footer.jsp"%>

	<script src="<%=request.getContextPath()%>/js/template.js"></script>
	<script>
		$(document).ready(function() {
			$("li:contains('電影資訊')").addClass("custom-active");
		});
		$('body').addClass('loaded');
	</script>


</body>
</html>