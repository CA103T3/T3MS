<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.filmreview.model.*"%>    
    
    
<%
	FilmreviewDAO fvSvc = new FilmreviewDAO();
	List<FilmreviewVO> list = fvSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>


<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  
  
 
  <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-right">
            <a href="fv_list.jsp" class="btn btn-primary">我的影評</a>
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
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-6">
            <div class="page-header">
              <h1>最近影評
                <small>熱門影評</small>
              </h1>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    
    
     
    
    
    
        
    	<%@ include file="pagef.file" %> 
		<c:forEach var="FilmreviewVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
        
        <div class="section">
      <div class="container">
        <div class="row">
        
          <div class="col-md-1">
          <a href="#">
            <img src="http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png" class="center-block img-circle img-responsive">
			</a>          
          </div>
          
          <div class="col-md-7">
            <p>${FilmreviewVO.title}</p>
          </div>
          
          <div class="col-md-1">
            <h3 class="text-center">${FilmreviewVO.created_at}</h3>
          </div>
          
          <div class="col-md-1">
          <a href="#">
            <img src="http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png" class="center-block img-circle img-responsive">
            <p class="text-center">${FilmreviewVO.mem_no}</p>
            </a>
          </div>
          
          <div class="col-md-1">
            <h3 class="text-center">${FilmreviewVO.movie_no}</h3>
          </div>
          
          <div class="col-md-1">
            <h3 class="text-center">${FilmreviewVO.evaluation}</h3>
          </div>
          
            </div>
      </div>
    </div>
   
          </c:forEach>
         <%@ include file="pageb.file" %>
          
      
  
   
   
  
  
  
  
  
  
  

</body>
</html>