<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.filmreview.model.*"%>
<%@ page import="com.movie.model.*"%>
<%@ page import="java.io.Reader"%>
<%@ page import="org.json.*"%>

<%
FilmreviewVO filmreviewVO = (FilmreviewVO) request.getAttribute("filmreviewVO");
%>
<jsp:useBean id="mvSvc" scope="page" class="com.movie.model.MovieService" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>M&amp;S</title>
    <%@ include file="/backstage/template/link.jsp" %>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/addTheater.css">
    <style type="text/css">
    </style>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>
</head>

<body class="fs16">

    <%@ include file="/backstage/template/header.jsp" %>
    <div id="wrapper" class="mt50">
        <%@ include file="/backstage/template/sidebar.jsp" %>
        <div class="flex-column" id="page-content-wrapper">
            <div class="container">
                <h3 class="page-header">
                    <label>修改影評&nbsp;&nbsp;</label>
                    <form id="fm-back" method="post" style="display:inline" action="<%=request.getContextPath()%>/backstage/filmreview/fv_b.jsp">
                        <button type="submit" id="back-btn" class="btn btn-default fs16 " >
                            <i class="fa fa-undo" aria-hidden="true"></i>&nbsp;返回
                        </button>
                        <!-- <input type="hidden" name="theater_no" value="${theaterVO.theater_no}"> -->
                        <!-- <input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>"> -->
                        <input type="hidden" name="whichRecordIndex" value="${param.whichRecordIndex}">
                        <!-- <input type="hidden" name="action" value="view"> -->
                    </form>
                </h3>
                <%-- 錯誤表列 --%>
                <%-- <%=request.getAttribute("errorMsgs")%> --%>
                <%-- ${errorMsgs.size()} --%>
                
                <form class="form-horizontal" method="post" action="<%=request.getContextPath()%>/filmreview/filmreview.do">
                 <div class="form-group">
                      <label class="col-md-5 control-label"></label>
                      <div class="col-md-3 text-center">
                      <c:if test="${not empty errorMsgs}">                                  
                    <c:forEach var="message" items="${errorMsgs}">
                      <div style="color:red">${message}</div>
                    </c:forEach>                  
                </c:if>
                      </div>
                      <div class="col-md-4">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">影評編號</label>
                      <div class="col-md-3">
                        <input class="form-control" id="" type="text" name="fr_no" value="<%=request.getParameter("fr_no")%>" readonly>
                      
                      </div>
                      <div class="col-md-4">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">影評標題</label>
                      <div class="col-md-3">
                        <input class="form-control" id="" type="text" name="title" value="" >
                      
                      </div>
                      <div class="col-md-4">
                      </div>
                    </div>
                  
                    <div class="form-group">
                      <label class="col-md-5 control-label">電影名稱</label>
                      <div class="col-md-3">
                        <select class="form-control" name="movie_no">
                        <c:forEach var="mVO" items="${mvSvc.all}" >
                           <option value="${mVO.movie_no}">${mVO.movie_name}</option>
                        </c:forEach>>
                        </select>
                      </div>
                      <div class="col-md-4">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label" style="padding-bottom:15px;">內容</label>
                      <div class="col-md-3">
                      
                      </div>
                      <div class="col-md-4">
                      </div>
                      <div class="col-md-12">
                        <textarea id="summernote" name="content" >
							
						</textarea>
                      
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">評分</label>
                      <div class="col-md-3">
                        <input class="form-control" id="" type="text" name="evaluation" value="" >
                      
                      </div>
                      <div class="col-md-4">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">作者</label>
                      <div class="col-md-3">
                        <input class="form-control" id="" type="text" name="author" value="" >
                      
                      </div>
                      <div class="col-md-4">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">影評來源</label>
                      <div class="col-md-3">
                        <input class="form-control" id="" type="text" name="source" value="" >
                      
                      </div>
                      <div class="col-md-4">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">影評網址</label>
                      <div class="col-md-3">
                        <input class="form-control" id="" type="text" name="url" value="" >
                      
                      </div>
                      <div class="col-md-4">
                      </div>
                    </div>
                    
                  
                   
                   
                   
                  <div class="col-md-12 text-center">
							<input type="hidden" name="mem_no" value="${filmreviewVO.mem_no}">
							<input type="hidden" name="action" value="update">
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>">
							<button type="submit" class="btn btn-default">發佈</button>
						</div>
                </form>

            </div>
        </div>
    </div>
<script>
  $(document).ready(function() {
			$('#summernote').summernote();});
  </script>
    <script src="<%=request.getContextPath()+"/js/back_index.js"%>"></script>
    <script type="text/javascript">


       
    </script>
</body>
</html>