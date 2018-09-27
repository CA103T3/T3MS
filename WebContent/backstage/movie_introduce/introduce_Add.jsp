<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<link href="/css/movie_back_movie.css" rel="stylesheet" type="text/css">
<!-- summernote CSS -->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css" rel="stylesheet">
<style type="text/css">
textarea{
resize: none;
}
</style>
</head>

<body class="fs16" ata-spy="scroll">
	<%@ include file="/backstage/template/header.jsp"%>
	<div id="wrapper" class="mt50">
		<%@ include file="/backstage/template/sidebar.jsp"%>
		<div class="flex-column" id="page-content-wrapper">


<!--introduce_Add -->

			<div class="section">
				<div class="container">
					<div class="row">
						<div class="col-md-10">

							<div class="page-header text-warning">
								<h1>
									電影介紹新增
									<font color="#777777">
										<span style="font-size: 23.4px; line-height: 23.4px;">Introduce Add</span>
									</font>
								</h1>
							</div>

							<!-- 錯誤表列 -->

							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>



							<form METHOD="post" ACTION="<%=request.getContextPath()%>/backstage/movie_introduce/movie_introduce.do" name="form1" >

							
								<div class="form-group">
									<label class="control-label">電影編號:</label>
									<input type="TEXT" class="form-control" name="movie_no" size="45" value="<%=(movie_introduceVO == null) ? "MV" : movie_introduceVO.getMovie_no()%>" />

								</div>

								<div class="form-group">
									<label class="control-label">來源:</label>
									<input type="TEXT" class="form-control" name="source" size="45" value="<%=(movie_introduceVO == null) ? "請輸入來源" : movie_introduceVO.getSource()%>" />

								</div>
			
								<div class="form-group hidden-md hidden-sm has-feedback">
									<label class="control-label">網址:</label>
									<input type="TEXT" class="form-control" name="url" size="45" value="<%=(movie_introduceVO == null) ? "請輸入網址" : movie_introduceVO.getUrl()%>" />

								</div>

								<div class="form-group hidden-md hidden-sm has-feedback">
									<label class="control-label">作者:</label>
									<input type="TEXT" class="form-control" name="author" size="45" value="<%=(movie_introduceVO == null) ? "請輸入作者" : movie_introduceVO.getAuthor()%>" />

								</div>
								
								<div class="form-group hidden-md hidden-sm has-feedback">
									<label class="control-label">標題:</label>
									<input type="TEXT" class="form-control" name="title" size="45" value="<%=(movie_introduceVO == null) ? "請輸入標題" : movie_introduceVO.getTitle()%>" />

								</div>
											
								<div class="form-group">
									<label class="control-label">內容:</label>
									<textarea class="form-control" id="summernote" name="content" rows="20"><%=(movie_introduceVO == null) ? "請輸入內容" : movie_introduceVO.getContent()%></textarea>									
								</div>

								<div>
									<input type="radio" name="active" value="0" checked="checked">
									<label for="contactChoice1">下線</label>
									<input type="radio" name="active" value="1">
									<label for="contactChoice2">上線</label>
								</div>

								<input type="hidden" name="action" value="insert">
								<input type="hidden" name="content">							
								<input type="submit" value="送出新增">

							</form>

						</div>
					</div>
				</div>
			</div>

<!-- introduce_Add -->

		</div>
	</div>
	<script src="<%=request.getContextPath() + "/js/back_index.js"%>"></script>
	
<!-- summernote JS -->	
    <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>
    
<script>
      $(document).ready(function () {
          $('#summernote').summernote({        	  
        	  tabsize: 2,
              height: 600,
              minHeight: 600, // set minimum height of editor
              maxHeight: 600,
              focus: true                  // set focus to editable area after initializing summe
          });
          
      });
 </script>    
</body>
</html>