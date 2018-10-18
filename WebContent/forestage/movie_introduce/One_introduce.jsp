<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.movie_introduce.model.*"%>


<%
	Movie_IntroduceService introduceSvc = new Movie_IntroduceService();
    Movie_IntroduceVO movie_introduceVO = introduceSvc.getOneMovie_Itde(request.getQueryString());
	pageContext.setAttribute("movie_introduceVO", movie_introduceVO);
%>



<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<%@ include file="/forestage/template/link.jsp"%>
<link href="<%=request.getContextPath()%>/css/movieALL.css"
	rel="stylesheet" type="text/css">
<title>M&amp;S</title>
<style>
.thumb-contenido {
	margin-bottom: 5%;
	margin-left: 0;
	padding-left: 0;
}
a {color: #ff54a7;}
.breadcrumb{background:#f5f5f5;}
.hr2{ height:3px;border:none;border-top:3px double red;}
#title{}
</style>

</head>
<body class="body-template">
	<%@ include file="/forestage/template/header.jsp" %>


	<!-- -----------------------------瀏覽單一電影情報------------------------------------- -->




	<!-- Preloader -->
	<div class="preloader" id="preloader">
		<div class="lds-css ng-scope">
			<div class="lds-ripple">
				<div></div>
				<div></div>
			</div>
		</div>
	</div>
	<!-- Preloader End -->





	<div class="container">
		<div class="well">
			<div class="row">
				<div class="span6">
				<ul class="breadcrumb">
					<li>
						<a href="<%=request.getContextPath()%>/index.jsp">MS首頁</a> <span class="divider">></span>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/forestage/movie_introduce/List_introduce.jsp">電影情報</a> <span class="divider">></span>
					</li>
					<li class="active">${movie_introduceVO.title}</li>
				</ul>
				</div>
			<!-- left -->
				<div class="col-md-12">
					<div class="row hidden-md hidden-lg">
						<h1 class="text-center"></h1>							
					</div>

					
					<div class="introduce">
						<h1 class="hidden-xs hidden-sm" id="title">${movie_introduceVO.title}</h1>
						<hr>
						<span style="font-size:20px;">${movie_introduceVO.created_at}</span>
						<br> 						
						<span style="font-size:14px;"><strong>作者: ${movie_introduceVO.author}</strong></span>
						<span style="font-size:14px;"><strong>來源: ${movie_introduceVO.source}</strong></span>						
						<hr>
						<div>${movie_introduceVO.content} </div>

                        <hr class="hr2" />
					</div>
				</div>
				
				
			
        </div>       
		</div>
		</div>





	<!-- -----------------------------瀏覽單一電影情報------------------------------------- -->


	<%@ include file="/forestage/template/footer.jsp" %>

	<script src="<%=request.getContextPath()%>/js/template.js"></script>
	<script>
		$(document).ready(function() {
			$("li:contains('電影資訊')").addClass("custom-active");
		});
	</script>

	<!-- Preloader -->
	<script>
		$(window).on('load', function() {
			$('#preloader').fadeOut('slow');
		});
	</script>
	<!-- Preloader -->
</body>
</html>