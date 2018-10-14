<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.movie.model.*"%>

<jsp:useBean id="movie_search" scope="request" type="java.util.Set<MovieVO>" />

<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
      <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
    rel="stylesheet" type="text/css">
    <link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
    rel="stylesheet" type="text/css">
        <%@ include file="/forestage/template/link.jsp" %>
        <title>M&amp;S</title>
        <style>
			body{color: #f5f5f5;}
			
			.text_m{font-size:50px;}
			.ser_m{color: #f5f5f5;font-size:30px;}
			#movie_pic{width:192px;height:263px;}
        </style>
        
    </head>
    <body class="body-template">
        <%@ include file="/forestage/template/header.jsp" %>
        <%@ include file="/forestage/template/search_bar.jsp" %>

     
     
     <!--  ------------------ 電影收尋LIST ---------------------- -->
     
    
     <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="page-header">
              <div class="text_m"></div>   
            <div class="ser_m">您搜尋結果共<b style="color:#0ff ;font-size:42px;" ><% out.print(movie_search.size()); %></b>筆</div>
            </div>
          </div>
        </div>
      </div>
    </div>
     
     
     
     
     <c:forEach var="ms" items="${movie_search}" > 
       <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-3">
          <a href="<%=request.getContextPath()%>/forestage/movie_moment/moment_One.jsp?${ms.movie_no}">
            <img id="movie_pic" src="<%=request.getContextPath() %>/DBGifReader?movie_no=${ms.movie_no}">
          </a>  
          </div>
          <div class="col-md-9">
            <h1 style="color:#d41872;">${ms.movie_name}<small style="color:#aaa">${ms.eng_name}</small></h1>
            <h3>上映日期:${ms.relased}</h3>
            <p>${ms.brief_intro}</p>
          </div>
        </div>
      </div>
    </div>
    
   </c:forEach>  
     <!--  ------------------ 電影收尋LIST ---------------------- -->
     
        
        <%@ include file="/forestage/template/footer.jsp" %>

        <script src="<%=request.getContextPath()%>/js/template.js"></script>
        <script>
        $(document).ready(function(){
            $("li:contains('合作影城')").addClass("custom-active");
        });
        </script>
    </body>
</html>