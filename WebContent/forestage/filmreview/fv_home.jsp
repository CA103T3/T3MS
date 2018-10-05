<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.filmreview.model.*"%>


<%
	FilmreviewDAO fvSvc = new FilmreviewDAO();
	List<FilmreviewVO> list = fvSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>


<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css" rel="stylesheet" type="text/css">

<%@ include file="/forestage/template/link.jsp" %>
        <title>M&amp;S</title>


<style>
	.toto {margin-top: 60px;}
	body {background-color:white !important;}
	 .ctnr {
	border-radius: 5px;
	background-color: rgb(238, 238, 238);
	padding: 20px;
}
</style>



</head>
<body class="body-template">



  <%@ include file="/forestage/template/header_no_bar.jsp" %>

	  <div class="section">
	      <div class="container">
	        <div class="row">
	          <div class="col-md-12 text-right">

				    <a href="<%=request.getContextPath()%>/forestage/filmreview/fv_list.jsp?mem_no=<%=request.getParameter("mem_no")%>" class="btn btn-primary">我的影評</a>


	          </div>
	        </div>
	      </div>
	    </div>




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
									<input class="btn btn-primary" type="submit" value="送出"> 
									<input type="hidden" name="action" value="getOne_For_Display">
									<div>
									<c:if test="${not empty errorMsgs}">
										<ul>
											<c:forEach var="message" items="${errorMsgs}">
												<li style="color: red">${message}</li>
											</c:forEach>
										</ul>
									</c:if></div>
								</div>

							</div>
						</div>

					</div>
				</div>

			</FORM>
			<div class="row">
				<div class="col-md-12 ">
				<div class="text-center">
					<c:if test="${not empty errorMsgs}">
<!-- 						<font style="color: red">請修正以下錯誤:</font> -->
						
							<c:forEach var="message" items="${errorMsgs}">
								<div style="color: red">${message}</div>
							</c:forEach>
						
					</c:if>
				</div>
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
						<h1>
							最近影評 <small>熱門影評</small>
						</h1>
					</div>
				</div>
			</div>
		</div>
	</div>








	<%@ include file="/resources/page_code/pagef.file"%>
	<c:forEach var="FilmreviewVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

		<div class="section">
			<div class="container">
				<div class="row">

					<div class="col-md-1">
						<a href="#"> <img src="http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png" class="center-block img-circle img-responsive">
						</a>
					</div>

					<div class="col-md-7">
						<a href="<%=request.getContextPath()%>/forestage/filmreview/fv.jsp?fr_no=${FilmreviewVO.fr_no}"><h2>${FilmreviewVO.title}</h2></a>
					</div>

					<div class="col-md-1">
						<h3 class="text-center">${FilmreviewVO.created_at}</h3>
					</div>

					<div class="col-md-1">
						<a href="#"> <img src="http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png" class="center-block img-circle img-responsive">
							<p class="text-center">${FilmreviewVO.mem_no}</p>
						</a>
					</div>

					<div class="col-md-1">
						<h3 class="text-center">${FilmreviewVO.movie_no}</h3>
					</div>

					<div class="col-md-1">
						<h3 class="text-center">${FilmreviewVO.evaluation}</h3>
					</div>

				</div>
			</div>
		</div>

	</c:forEach>
	<%@ include file="/resources/page_code/pageb.file"%>
	



	<%@ include file="/forestage/template/footer.jsp"%>

	<script src="<%=request.getContextPath()%>/js/template.js"></script>
	<script>
		$(document).ready(function() {
			$("li:contains('電影資訊')").addClass("custom-active");
		});
	</script>

</body>
</html>