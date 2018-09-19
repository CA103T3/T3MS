<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.filmreview.model.*"%>

<%
	FilmreviewVO filmreviewVO = (FilmreviewVO) request.getAttribute("filmreviewVO");
%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<!-- include libraries(jQuery, bootstrap) -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<%@ include file="/forestage/template/link.jsp" %>
        <title>M&amp;S</title>


<style>
	.toto {margin-top: 60px;}
	body {background-color:white !important;}
	
</style>



</head>

<body data-spy="scroll" class="body-template" >
<%@ include file="/forestage/template/header.jsp" %>
	<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>
	<script>
		$(document).ready(function() {
			$('#summernote').summernote();
		});
	</script>



	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<form METHOD="post" ACTION="<%=request.getContextPath()%>/forestage/filmreview/filmreview.do" class="form-horizontal" role="form">
		
		<div class="container">
			<div class="row ">
				<div class="col-md-12 toto">

					<div class="form-group ">
						<div class="col-sm-2">
							<label for="inputPassword3" class="control-label">電影編號</label>
						</div>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputEmail3" name="movie_no" value="<%=(filmreviewVO == null) ? "編號" : filmreviewVO.getMovie_no()%>" placeholder="電影編號">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-2">
							<label for="inputEmail3" class="control-label">影評標題</label>
						</div>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputEmail3" name="title" value="<%=(filmreviewVO == null) ? "影評標題" : filmreviewVO.getTitle()%>" placeholder="影評標題">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<label for="inputPassword3" class="control-label">評分</label>
						</div>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputEmail3" name="evaluation" value="<%=(filmreviewVO == null) ? "5.0" : filmreviewVO.getEvaluation()%>" placeholder="評分">

						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-sm-2">
						<label for="inputPassword3" class="control-label">內容</label>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="alert alert-dismissable alert-success">
							<strong>提醒!</strong>
							圖片請上傳2Mb以下的檔案
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-12">

						<input type="hidden" name="action" value="insert">
						<textarea id="summernote" name="content">
							
						</textarea>

					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-md-12">


					<div class="form-group">
						<div class="col-sm-2">
							<label for="inputPassword3" class="control-label">會員編號</label>
						</div>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputEmail3" name="mem_no" value="<%=(filmreviewVO == null) ? "編號" : filmreviewVO.getMem_no()%>" placeholder="會員編號">

						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<label for="inputPassword3" class="control-label">作者</label>
						</div>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputEmail3" name="author" value="<%=(filmreviewVO == null) ? "作者" : filmreviewVO.getAuthor()%>" placeholder="作者">

						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-2">
							<label for="inputEmail3" class="control-label">影評來源</label>
						</div>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputEmail3" name="source" value="<%=(filmreviewVO == null) ? "影評來源" : filmreviewVO.getSource()%>" placeholder="影評來源">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<label for="inputPassword3" class="control-label">影評網址</label>
						</div>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputEmail3" name="url" value="<%=(filmreviewVO == null) ? "影評網址" : filmreviewVO.getUrl()%>" placeholder="影評網址">
						</div>
					</div>
					<div class="section">
						<div class="container">
							<div class="row">
								<div class="col-md-12 text-center">
									<input type="hidden" name="action" value="insert">
									<button type="submit" class="btn btn-default">發佈</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	
	<%@ include file="/forestage/template/footer.jsp" %>

        <script src="<%=request.getContextPath()%>/js/template.js"></script>
        <script>
        $(document).ready(function(){
            $("li:contains('合作影城')").addClass("custom-active");
        });
        </script>
	
</body>
</html>