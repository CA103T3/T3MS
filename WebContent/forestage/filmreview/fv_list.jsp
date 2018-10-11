<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.filmreview.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.movie.model.*"%>
<%
	FilmreviewDAO fvSvc = new FilmreviewDAO();
	List<FilmreviewVO> list = fvSvc.findByMem(request.getParameter("mem_no"));
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="mvSvc" scope="page" class="com.movie.model.MovieService" />
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
                    <a class="btn btn-primary" type="submit">搜尋</a>
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
          <div class="col-md-1">
						<c:forEach var="mvVO" items="${mvSvc.all}">
							<c:if test="${FilmreviewVO.movie_no==mvVO.movie_no}">
								<a href="#"> <img src="<%=request.getContextPath() %>/DBGifReader?movie_no=${mvVO.movie_no}" class="center-block img-circle img-responsive">
								</a>
							</c:if>
						</c:forEach>
					</div>
          <div class="col-md-5">
            <h2>${FilmreviewVO.title}</h2>
          </div>
          <div class="col-md-1">
            <h3 class="text-center">${FilmreviewVO.created_at}</h3>
          </div>
          <div class="col-md-2">
            <h3 class="text-center"><c:forEach var="mvVO" items="${mvSvc.all}">
								<c:if test="${FilmreviewVO.movie_no==mvVO.movie_no}">
	                   					 ${mvVO.movie_name}
                    			</c:if>
							</c:forEach></h3>
          </div>
          <div class="col-md-1">
            <h3 class="text-center">${FilmreviewVO.evaluation}</h3>
          </div>
          <div class="col-md-1 text-center ">
          <form action="<%=request.getContextPath()%>/filmreview/filmreview.do" method="POST">
		<input type="submit" class="btn  btn-primary center-vertical" value="修改" >
          <input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>">
		<input type="hidden" name="finalPage" value="<%=pageIndex + rowsPerPage%>"> 
		<input type="hidden" name="whichPage" value="<%=whichPage%>">
		<input type="hidden" name="fr_no" value="${FilmreviewVO.fr_no}">
		<input type="hidden" class="form-control" name="action" value="toUpdate">
            </form>
          </div>
          <div class="col-md-1 text-center">
          
            <button type="button" class="btn btn-primary center-vertical" data-toggle="modal" data-target="#hanhan">刪除</button>
          </div>
        </div>
      </div>
    </div>
    
  

	<div class="container">
		<div>
			<form action="<%=request.getContextPath()%>/filmreview/filmreview.do" method="POST">
				<div class="modal fade" id="hanhan" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
					<div class="modal-dialog modal-md">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h3 class="modal-title" id="myModalLabel">是否刪除?</h3>
							</div>
						
							<div class="modal-footer text-center">
								<div class="input-group">
								
									<div class="input-group-btn"></div>
									<input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>">
									<input type="hidden" name="finalPage" value="<%=pageIndex + rowsPerPage%>"> 
									<input type="hidden" name="whichPage" value="<%=whichPage%>">
									<input type="hidden" name="fr_no" value="${FilmreviewVO.fr_no}">
									<input type="hidden" class="form-control" name="action" value="delete"> 
									<input type="submit" class="btn btn btn-danger text-right" value="確定刪除" style="margin-left: 400px;">
									<input type="button" class="btn btn-primary " value="取消" onclick="location.href='/T3MS/forestage/filmreview/fv_list.jsp?mem_no=<%=request.getParameter("mem_no")%>'">
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!--- if erroMsg open lightbox again--->
	<c:if test="${openupdatereplyform!=null}">
		<script>
			$("#hanhan").modal({
				show : true
			});
		</script>
	</c:if>
	<!--- if erroMsg --->

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