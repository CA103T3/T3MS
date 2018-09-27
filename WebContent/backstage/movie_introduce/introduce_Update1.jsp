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



			<!-- movie_introduce_Update -->

				<div class="section">
					<div class="container">
						<div class="row">
							<div class="col-md-10">
								<div class="page-header text-warning">
									<h1>電影介紹修改
										 <font color="#777777"> 
											<span style="font-size: 23.4px; line-height: 23.4px;">Introduce Update</span>												
										</font>
									</h1>
								</div>

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
									<div class="form-group has-error">
										<label class="control-label">電影介紹編號:*</label>
										<div>${param.introd_no}</div>
									</div>
																		
									<div class="form-group">
										<label class="control-label">電影編號:</label> <input
											class="form-control" type="text" name="movie_no" value="<%=movie_introduceVO.getMovie_no()%>">
									</div>
															
									<div class="form-group hidden-md hidden-sm has-feedback">
										<label class="control-label">來源:</label> <input
											class="form-control" type="text" name="source" value="<%=movie_introduceVO.getSource()%>">
									</div>
									
									<div class="form-group hidden-md hidden-sm has-feedback">
										<label class="control-label">網址：</label> <input
											class="form-control" type="text" name="url" value="<%=movie_introduceVO.getUrl()%>">
									</div>
									
									<div class="form-group hidden-md hidden-sm has-feedback">
										<label class="control-label">作者:</label> <input
											class="form-control" type="text" name="author" value="<%=movie_introduceVO.getAuthor()%>">
									</div>
									
									<div class="form-group hidden-md hidden-sm has-feedback">
										<label class="control-label">標題：</label> <input
											class="form-control" type="text" name="title" value="<%=movie_introduceVO.getTitle()%>">
									</div>
																		
									<div class="form-group">
										<label class="control-label">簡介:</label>
										<textarea class="form-control" id="summernote" name="content" rows="20" ><%=movie_introduceVO.getContent()%></textarea>
									</div>
									
									<div class="form-group">
										<label class="control-label">狀態:</label>
											<c:choose>
									         <c:when test="<%=movie_introduceVO.getActive() == 0%>">
									          <label><input name="active" type="radio" value="0"
									           checked="checked">下線</label>
									          <label><input name="active" type="radio" value="1">上線</label>
									         </c:when>
									         <c:otherwise>
									          <label><input name="active" type="radio" value="0">下線</label>
									          <label><input name="active" type="radio" value="1"
									           checked="checked">上線</label>
									         </c:otherwise>
								           </c:choose>										
									</div>
									
																				
								<input type="hidden" name="action" value="update">
								<input type="hidden" name="introd_no"  value="<%=movie_introduceVO.getIntrod_no()%>">
								<input type="hidden" name="active" value="<%=movie_introduceVO.getActive()%>">
								<input type="hidden" name="content">
								
								
								<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
								<input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--只用於:movie_introduce_List.jsp-->
								<input type="submit" value="送出修改">
									
								</form>


							</div>
						</div>
					</div>
				</div>



				<!-- movie_introduce_Update -->
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