<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.movie_introduce.model.*"%>
<%@ page import="com.movie.model.*"%>


<%
	Movie_IntroduceService introduceSvc = new Movie_IntroduceService();
	List<Movie_IntroduceVO> list = introduceSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="movieSvc" scope="request" class="com.movie.model.MovieService" />


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>M&amp;S</title>
<%@ include file="/backstage/template/link.jsp"%>
<!-- movie_back_movie CSS -->
<link href="<%=request.getContextPath()%>/css/movie_back_movie.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/introduce.css" rel="stylesheet" type="text/css">
<style>
.row {
  margin-left: -80px;
  margin-right: -80px;
}

</style>

</head>

<body class="fs16">
	<%@ include file="/backstage/template/header.jsp"%>
	<div id="wrapper" class="mt50">
		<%@ include file="/backstage/template/sidebar.jsp"%>
		<div class="flex-column" id="page-content-wrapper">

			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<!-- movie_introduce管理 -->

			<div class="section">
				<div class="container">
					<div class="row">
						<div class=" col-md-8 text-left">
							<div class="page-header">
								<h1>
									電影介紹管理 <font color="#777777"> 
										
 						<span style="font-size: 23.4px; line-height: 23.4px;"> Movie Introduction &nbsp; </span> 
											
																						
										<a href="introduce_Add.jsp" target="_blank">
											<button id="btn6">新增電影介紹</button>
									    </a>
										
									</font>
								</h1>
							</div>
						</div>
					</div>

				</div>
			</div>

			<div class="section">
				<div class="container">
					<div class="hidden-md row">
						<div class="col-md-10 text-justify">

							<form METHOD="post" ACTION="movie_introduce.do" id="src_movie">
								<input type="text" name="introd_no" placeholder="Search..">
								<input type="hidden" name="action" value="getOne_For_Display">
								<input type="submit" value="送出">
							</form>

							<table class="blueTable">
								<thead>
									<tr>
										<th>介紹編號</th>
										<th>電影編號+名稱</th>
										<th>網址</th>
										<th>是否上線</th>
										<th>創建日期</th>
										<th>修改</th>
										<th>刪除</th>
									</tr>
								</thead>
							
								<tbody>
								<%@ include file="/resources/page_code/page1.file"%>
								<c:forEach var="movie_introduceVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">									
									<tr ${(movie_introduceVO.introd_no==param.introd_no) ? 'bgcolor=#FFB6C1':''}>
										<td>${movie_introduceVO.introd_no}</td>
										<td>
											<c:forEach var="movieVO" items="${movieSvc.all}" >
	                    						<c:if test="${movie_introduceVO.movie_no == movieVO.movie_no}">
		                    						${movieVO.movie_no}【${movieVO.movie_no} - ${movieVO.movie_name}】
	                    						</c:if>
	                						</c:forEach>
										</td>
										<td>${movie_introduceVO.url}</td>
										<td>${movie_introduceVO.active}</td>
										<td>${movie_introduceVO.created_at}</td>
										<td id="upbtn">
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/backstage/movie_introduce/movie_introduce.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="修改"> <input
													type="hidden" name="introd_no" value="${movie_introduceVO.introd_no}">
												<input type="hidden" name="requestURL"
													value="<%=request.getServletPath()%>">
												<!--送出本網頁的路徑給Controller-->
												<input type="hidden" name="whichPage" value="<%=whichPage%>">
												<!--送出當前是第幾頁給Controller-->
												<input type="hidden" name="action" value="getOne_For_Update">
											</FORM>
										</td>
										<td id="delbtn">
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/backstage/movie_introduce/movie_introduce.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="刪除"> <input
													type="hidden" name="introd_no" value="${movie_introduceVO.introd_no}">
												<input type="hidden" name="requestURL"
													value="<%=request.getServletPath()%>">
												<!--送出本網頁的路徑給Controller-->
												<input type="hidden" name="whichPage" value="<%=whichPage%>">
												<!--送出當前是第幾頁給Controller-->
												<input type="hidden" name="action" value="delete">
											</FORM>
										</td>
									</tr>
									</c:forEach>
								</tbody>
								
							</table>
							
								<div>
				<c:choose>
					<%-- 總頁數小於5 設定迴圈 開始1 結束5 --%>
					<c:when test="<%=pageNumber <= 5%>">
						<c:set var="begin" value="1" />
						<c:set var="end" value="<%=pageNumber%>" />
					</c:when>
					<%-- 大於5 設定迴圈 開始為目前頁數 結束為目前頁數+4 --%>
					<c:otherwise>
						<c:set var="begin" value="<%=whichPage%>" />
						<c:set var="end" value="<%=whichPage + 4%>" />
						<%-- 若是第一頁就設定開始為1 最末頁5 --%>
						<c:if test="${begin -1 <=0} ">
							<c:set var="begin" value="1" />
							<c:set var="end" value="5" />
						</c:if>
						<%-- 若end超過最末頁 開始頁設定最大頁-4 --%>
						<c:if test="${end} > <%=pageNumber%>">
							<c:set var="begin" value="<%=pageNumber - 4%>" />
							<c:set var="end" value="<%=pageNumber%>" />
						</c:if>
					</c:otherwise>
				</c:choose>
 				<ul class="pagination">
					<%-- 判斷第一頁時無法按上一頁 --%>
					<c:choose>
						<c:when test="<%=whichPage <= 1%>">
							<li class="disabled"><a href="#">&laquo;</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="<%=request.getRequestURI()%>?whichPage=<%=whichPage - 1%>">&laquo;</a></li>
						</c:otherwise>
					</c:choose>
 					<c:forEach var="i" begin="${begin}" end="${end}">
						<c:choose>
							<c:when test="${i == page1.whichPage}">
								<li class="active"><a href="#">${i}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="<%=request.getRequestURI()%>?whichPage=${i}">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
 					<%-- 判斷最末頁無法按下一頁 --%>
					<c:choose>
						<c:when test="<%=whichPage >= pageNumber%>">
							<li class="disabled"><a href="#">&raquo;</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="<%=request.getRequestURI()%>?whichPage=<%=whichPage + 1%>">&raquo;</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
<%@ include file="/resources/page_code/page2.file"%>
							


							
						</div>
					</div>
				</div>
			</div>



			<!-- movie_introduce管理內容  -->

		</div>
	</div>
	<script src="<%=request.getContextPath() + "/js/back_index.js"%>"></script>
	
</body>
</html>