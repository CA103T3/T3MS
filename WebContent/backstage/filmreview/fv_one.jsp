<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.filmreview.model.*"%>
<%@ page import="com.movie.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.io.Reader"%>
<%@ page import="org.json.*"%>
<%@ page import="java.io.IOException"%>
<%@ page import="javax.servlet.jsp.SkipPageException"%>

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
</head>

<body class="fs16">
    <%@ include file="/backstage/template/header.jsp" %>
    <div id="wrapper" class="mt50">
        <%@ include file="/backstage/template/sidebar.jsp" %>
        <div class="flex-column" id="page-content-wrapper">
            <div class="container">
                <h3 class="page-header">
                    <label>影評資訊&nbsp;&nbsp;</label>
                    <!-- class="dp-inline" does  not work here, use style -->
                    <form id="fm-mod" method="post" style="display:inline" action="<%=request.getContextPath()%>/filmreview/filmreview.do">
                        <button type="submit" id="mod-btn" class="btn btn-warning fs16 " >
                            <i class="fa fa-pencil-square-o" aria-hidden="true"></i>&nbsp;修改
                        </button>
                        <input type="hidden" name="fr_no" value="${filmreviewVO.fr_no}">
                      
                        <!--  <input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>"> -->
                        <input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
                        <input type="hidden" name="whichRecordIndex" value="${param.whichRecordIndex}">
                        <input type="hidden" name="action" value="toUpdatePage">
                    </form>
                    <!-- class="dp-inline" does  not work here, use style -->
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

                <div class="row form-group">
                    <label class="col-md-2 control-label text-right">影評編號</label>
                    <div class="col-md-10">
                      <label>${filmreviewVO.fr_no}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-2 control-label text-right">影評標題</label>
                    <div class="col-md-10">
                      <label>${filmreviewVO.title}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-2 control-label text-right">電影編號</label>
                    <div class="col-md-10">
                      <label>
                      <c:forEach var="mvVO" items="${mvSvc.all}">
									<c:if test="${filmreviewVO.movie_no==mvVO.movie_no}">
	                   					 【${mvVO.movie_name}】
                    				</c:if>
					  </c:forEach>
					  </label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-2 control-label text-right">建立日期</label>
                    <div class="col-md-10">
                      <label>${filmreviewVO.created_at}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-2 control-label text-right">修改日期</label>
                    <div class="col-md-10">
                      <label>${filmreviewVO.updated_at}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-2 control-label text-right">影評內容</label>
                    <div class="col-md-10">
                      <label>${filmreviewVO.content}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-2 control-label text-right">評分</label>
                    <div class="col-md-10">
                      <label>${filmreviewVO.evaluation}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-2 control-label text-right">影評來源</label>
                    <div class="col-md-10">
                      <label>${filmreviewVO.source}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-2 control-label text-right">影評網址</label>
                    <div class="col-md-10">
                      <label>${filmreviewVO.url}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-2 control-label text-right">會員編號</label>
                    <div class="col-md-10">
                      <label>${filmreviewVO.mem_no}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-2 control-label text-right">作者</label>
                    <div class="col-md-10">
                      <label>${filmreviewVO.author}</label>
                    </div>
                </div>
                
            </div>
        </div>
    </div>

    <script src="<%=request.getContextPath()+"/js/back_index.js"%>"></script>
    <script type="text/javascript">
        //a little different from addTheater.jsp and addSession.jsp
        

       
    </script>
</body>
</html>