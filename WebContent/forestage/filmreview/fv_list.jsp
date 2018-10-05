<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.filmreview.model.*"%>
<%
	FilmreviewDAO fvSvc = new FilmreviewDAO();
	List<FilmreviewVO> list = fvSvc.findByMem(request.getParameter("mem_no"));
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

.center-vertical{

position: relative;

top: 50%;

transform: translateY(50%);
}
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
            <a href="fv_home.jsp" class="btn btn-primary">瀏覽影評</a>
          </div>
        </div>
      </div>
    </div>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <h1 class="text-center">搜尋</h1>
          </div>
        </div>
        <div class="row">
          <div class="col-md-offset-3 col-md-6">
            <form role="form">
              <div class="form-group">
                <div class="input-group">
                  <input type="text" class="form-control" placeholder="請輸入電影名稱">
                  <span class="input-group-btn">
                    <a class="btn btn-primary" type="submit">Go</a>
                  </span>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <a href="fv_writing.jsp" class="btn btn-block btn-lg btn-primary">寫影評</a>
          </div>
        </div>
      </div>
    </div>
   <%@ include file="/resources/page_code/pagef.file"%>
	<c:forEach var="FilmreviewVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
    <div class="section">
      <div class="container">
        <div class="row">
          
          <div class="col-md-6">
            <h3>${FilmreviewVO.title}<br><br></h3>
          </div>
          <div class="col-md-1">
            <h4 class="text-center">${FilmreviewVO.created_at}</h4>
          </div>
          <div class="col-md-2">
            <h3 class="text-center">${FilmreviewVO.movie_no}</h3>
          </div>
          <div class="col-md-1">
            <h3 class="text-center">${FilmreviewVO.evaluation}</h3>
          </div>
          <div class="col-md-1 text-center ">
            <a class="btn btn-primary center-vertical" data-toggle="button">修改</a>
          </div>
          <div class="col-md-1 text-center">
            <a class="btn btn-primary center-vertical">刪除</a>
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