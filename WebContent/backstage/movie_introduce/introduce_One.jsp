<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.movie_introduce.model.*"%>


<%
  Movie_IntroduceVO movie_introduceVO = (Movie_IntroduceVO) request.getAttribute("movie_introduceVO"); //Movie_IntroduceServlet.java (Concroller) 存入req的movie_introduceVO物件 (包括幫忙取出的movie_introduceVO, 也包括輸入資料錯誤時的movie_introduceVO物件)
%>



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

		
<!--  ----------查詢單一 movie_introduce資訊--------------   -->
			<div class="section">
				<div class="container">
					<div class="row">
						<div class=" col-md-8 text-left">
							<div class="page-header">
								<h1>
									電影情報單一查詢 <font color="#777777"> 
										
 						<span style="font-size: 23.4px; line-height: 23.4px;"> Movie Introduction Search &nbsp; </span> 
																																																				
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
							
							<table class="blueTable">
								<thead>
									<tr>
										<th>電影情報編號</th>
										<th>電影編號</th>
										<th>網址</th>
										<th>作者</th>
										<th>標題</th>
										<th>是否上線</th>
										<th>創建日期</th>										
									</tr>
								</thead>
							
								<tbody>
																	
									<tr>
										<td>${movie_introduceVO.introd_no}</td>
										<td>${movie_introduceVO.movie_no}</td>
										<td>${movie_introduceVO.url}</td>
										<td>${movie_introduceVO.author}</td>										
										<td><p>${movie_introduceVO.title}</p></td>
										<td>${movie_introduceVO.active}</td>
										<td>${movie_introduceVO.created_at}</td>
																														
									</tr>
									
								</tbody>
								
							</table>
			


							
						</div>
					</div>
				</div>
			</div>



<!--  ----------查詢單一 movie_introduce資訊--------------   -->

		</div>
	</div>
	<script src="<%=request.getContextPath() + "/js/back_index.js"%>"></script>
<script>
	$(document).ready(function() {
		var len = 10; // 超過10個字以"..."取代
		$("p").each(function(i) {
			if ($(this).text().length > len) {
				$(this).attr("title", $(this).text());
				var text = $(this).text().substring(0, len - 1) + "  ...";
				$(this).text(text);
			}
		});
		
	});
</script>
</body>
</html>