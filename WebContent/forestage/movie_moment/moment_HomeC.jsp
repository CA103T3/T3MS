<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.movie.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	MovieService movieSvc = new MovieService();
	List<MovieVO> list = movieSvc.getComingsoon();
	pageContext.setAttribute("list", list);
%>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <%@ include file="/forestage/template/link.jsp" %>
        <title>M&amp;S</title>
        <!-- moment_Home CSS -->
        <link href="<%=request.getContextPath()%>/css/movie_list.css" rel="stylesheet" type="text/css">
        <link href="<%=request.getContextPath()%>/css/movieALL.css" rel="stylesheet" type="text/css">
        
        <style>
			.text-warning {color:#ffe661;}
			.dateC {margin-top:10px;}
			.name_m {font-size:18px;}
        </style>
        
    </head>
    <body class="body-template">
        <%@ include file="/forestage/template/header.jsp" %>
        
        
        
    <!-- Preloader -->
	    <div class="preloader" id="preloader" >
	        <div class="lds-css ng-scope">
	            <div class="lds-ripple">
	                <div></div>
	                <div></div>
	            </div>
	        </div>
	    </div>
    <!-- Preloader End -->
        
<!-- -----------------moment_HomeC-------------------- -->
        
<%@ include file="/resources/page_code/pagemf.file"%>  
    
  <!--   nav -->
  <nav class="mar">
    <ul class="navbarbar">
      <li>
        <a href="<%=request.getContextPath()%>/forestage/movie_moment/moment_Home.jsp">現正熱映</a>
      </li>
      <li>
        <a href="<%=request.getContextPath()%>/forestage/movie_moment/moment_HomeC.jsp">即將上映</a>
      </li>
    </ul>
  </nav>


  <!-- card_list_1 -->
  <div class="container">
    <div class="rowrow">
<c:forEach var="movieVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
      <div class="col-md-3" >

        <div class="card">
          <div class="card_img_box">
           <div> <img src="<%=request.getContextPath() %>/DBGifReader?movie_no=${movieVO.movie_no}"> </div>
            <div class="img_hover">
              <a href="<%=request.getContextPath()%>/forestage/movie_moment/moment_One2.jsp?${movieVO.movie_no}" class="card-img-a">電影簡介</a>
              <a href="<%=request.getContextPath()%>/forestage/movie_moment/moment_One.jsp?${movieVO.movie_no}" class="card-img-a2">電影時刻</a>
            </div>
          </div>
          <div class="dateC">上映日期:<strong>${movieVO.relased}</strong></div>
          <div class="name_m"><strong>${movieVO.movie_name}</strong></div>
          <p>${movieVO.eng_name}</p>
          <div class="grade">${movieVO.rating}</div>
          <p></p>
        </div>

      </div>
      

</c:forEach>  

      </div>
    </div>

  <!-- card_list_1 -->
  



<%-- <i class="fas fa-star ${(productVO.productScore >=1)? 'text-warning':''}"></i> --%>

  <!--Pagination-->
  
  
<%@ include file="/resources/page_code/pagemb.file"%>
        
        
        
        
        
        
    	
        
        
        
        
<!-- -----------------moment_HomeC-------------------- -->        
        
        <%@ include file="/forestage/template/footer.jsp" %>

        <script src="<%=request.getContextPath()%>/js/template.js"></script>
        <script>
        $(document).ready(function(){
            $("li:contains('電影資訊')").addClass("custom-active");
            
          

        });
        </script>
        
        <script src="/T3MS/css/jsRapStar.js"></script>
<!-- Preloader -->

<script>
$(window).on('load', function () {
    $('#preloader').fadeOut('slow');
});
</script>
    </body>
</html>