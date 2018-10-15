<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.movie_introduce.model.*"%>

<%
	Movie_IntroduceService introduceSvc = new Movie_IntroduceService();
	List<Movie_IntroduceVO> list = introduceSvc.getAll();
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
        <!-- MOVIE_INTRODUCE CSS&JS -->
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
      
        <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="<%=request.getContextPath()%>/css/introduceM.css" rel="stylesheet" type="text/css">	
        <link href="<%=request.getContextPath()%>/css/movieALL.css" rel="stylesheet" type="text/css">
      <style>
		     #movie_pic{width:500px;height:300px;}
	
		     .text-primary {color: #86abcc;font-weight:bold;}
          
             a {color: #ff54a7;}
             .breadcrumb{background-color:#42474b; margin-top：50px;}
            
             
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
        
     <!-- --------------------------------電影情報list------------------------------------------------ -->
        
        
         <div id="fullcarousel-example" data-interval="2000" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="item active">
                    <img src="<%=request.getContextPath()%>/img/Test_UP_IMG/MI001.jpg">
                    <div class="carousel-caption">
                        <h2>電影情報</h2>
                        <p class="lead">Movie &nbsp;Intelligence</p>
                    </div>
                </div>
                <div class="hidden-sm item">
                    <img src="<%=request.getContextPath()%>/img/Test_UP_IMG/MI002.jpg">
                    <div class="carousel-caption">
                        <h1>電影情報</h1>
                        <p class="lead">Movie &nbsp;Intelligence</p>
                    </div>
                </div>
                <div class="item">
                    <img src="<%=request.getContextPath()%>/img/Test_UP_IMG/MI003.jpg">
                    <div class="carousel-caption">
                        <h1 contenteditable="true">電影情報
                            <small>&nbsp;</small>
                        </h1>
                        <p class="lead">Movie &nbsp;Intelligence</p>
                    </div>
                </div>
                
 <%@ include file="/forestage/template/search_bar.jsp" %>
<%@ include file="/resources/page_code/pagef.file"%>

 <!-- ---------------------------電影情報內容----------------------------------------- --> 
 				<div class="container">
                        <div class="row">
 							<div class="span6">
							  <ul class="breadcrumb">
								<li>
									<a href="<%=request.getContextPath()%>/index.jsp">MS首頁</a> <span class="divider">></span>
								</li>
								<li class="active" style="color:#ffffff;">電影情報</li>
							 </ul>
							</div>
						</div>
 				</div>
 				
			   <c:forEach var="movie_introduceVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                <div class="section">
                    <div class="container">
                        <div class="row">
                        	
                            <div class="col-md-6">
                            	<img id="movie_pic" src="${movie_introduceVO.photo_path}" class="hvr-float-shadow"> 
                            	                   
                            </div>
                            <div class="col-md-6">
                                <h3 contenteditable="true">${movie_introduceVO.created_at}</h3>
                                <h1 class="text-primary">
                                    ${movie_introduceVO.title}
                                </h1>
                                <div class="zxx_text_overflow_5"><p>${movie_introduceVO.content}</p></div>
                                <p class="text-primary">
                                    <a href="<%=request.getContextPath()%>/forestage/movie_introduce/One_introduce.jsp?${movie_introduceVO.introd_no}"><b>READ MORE</b></a>
                                </p>
                                    
                            
                            </div>
                        </div>
                    </div>
                </div>
               </c:forEach>  
                            
            </div>
        </div> 
        
        
			
        

<%@ include file="/resources/page_code/pageb.file"%>
        
             
        <!-- --------------------------------電影情報list------------------------------------------------ -->
        
        <%@ include file="/forestage/template/footer.jsp" %>
        
<!-- text Indentation -->
<script>
$(document).ready(function(){   
	$(".zxx_text_overflow_5").each(function()  
	{ var maxwidth=100;  
	 if($(this).text().length>maxwidth){  
	 $(this).text($(this).text().substring(0,maxwidth));   
	$(this).html($(this).html()+'...');   
	}   
	});  
	 }); 
 </script>
 <script>
        $(document).ready(function(){
            $("li:contains('電影資訊')").addClass("custom-active");
        });
 </script>
 

<!-- Preloader -->

<script>
$(window).on('load', function () {
    $('#preloader').fadeOut('slow');
});
</script>
    </body>
</html>