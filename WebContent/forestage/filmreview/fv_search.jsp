<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.filmreview.model.*"%>

<jsp:useBean id="fv_search" scope="request" type="java.util.Set<FilmreviewVO>" />




<!DOCTYPE html>


<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css" rel="stylesheet" type="text/css">

<%@ include file="/forestage/template/link.jsp" %>
        <title>M&amp;S</title>



<style>

	body {background-color:white !important;}
	
</style>
</head>
<body class="body-template">

  <%@ include file="/forestage/template/header_no_bar.jsp" %>



	<!-- 	<div class="section"> -->
	<!-- 		<div class="container"> -->
	<!-- 			<div class="row"> -->
	<!-- 				<div class="col-md-12 text-right"> -->
	<%-- 					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/filmreview/filmreview.do" style="margin-bottom: 0px;"> --%>
	<%-- 						<a href="fv_list.jsp" class="btn btn-primary">我的影評</a> <input type="hidden" name="Fr_no" value="${filmreviewVO.Fr_no}"> <input type="hidden" name="action" value="fv_list"> --%>
	<!-- 					</FORM> -->

	<!-- 				</div> -->
	<!-- 			</div> -->
	<!-- 		</div> -->
	<!-- 	</div> -->



	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1 class="text-center">影評搜尋</h1>
				</div>
			</div>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/filmreview/filmreview.do">
				<div class="row">
					<div class="col-md-offset-3 col-md-6">

						<div class="form-group">
							<div class="input-group">


								<input type="text" class="form-control" name="movie_no" placeholder="請輸入電影名稱">
								<div class="input-group-btn">
									<input class="btn btn-primary" type="submit" value="送出"> <input type="hidden" name="action" value="getOne_For_Display">
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
				<div class="col-md-12">
					<a href="<%=request.getContextPath()%>/forestage/filmreview/fv_writing.jsp" class="btn btn-block btn-lg btn-primary">寫影評</a>
				</div>
			</div>
		</div>
	</div>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<div class="page-header">
						<h3>
									您的搜尋結果如下:					
							<small>共<% out.print(fv_search.size()); %>筆</small>
							
								
						</h3>
					</div>
				</div>
			</div>
		</div>
	</div>








	<c:forEach var="fv" items="${fv_search}" >

		<div class="section">
			<div class="container">
				<div class="row">


					<div class="col-md-1">
						<a href="#"> <img src="http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png" class="center-block img-circle img-responsive">
						</a>
					</div>

					<div class="col-md-7">
						<a href="/T3MS//forestage/filmreview/fv.jsp?${fv.fr_no}"><h2>${fv.title}</h2></a>
					</div>

					<div class="col-md-1">
						<h3 class="text-center">${fv.created_at}</h3>
					</div>

					<div class="col-md-1">
						<a href="#"> <img src="http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png" class="center-block img-circle img-responsive">
							<div class="text-center">${fv.mem_no}</div>
						</a>
					</div>

					<div class="col-md-1">
						<h3 class="text-center">${fv.movie_no}</h3>
					</div>

					<div class="col-md-1">
						<h3 class="text-center">${fv.evaluation}</h3>
					</div>

				</div>
			</div>
		</div>


	</c:forEach>




	<%@ include file="/forestage/template/footer.jsp"%>

	<script src="<%=request.getContextPath()%>/js/template.js"></script>
	<script>
		$(document).ready(function() {
			$("li:contains('電影資訊')").addClass("custom-active");
		});
	</script>


</body>
</html>