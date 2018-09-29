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
        <style>
/* .zxx_text_overflow_5{   */
/* width:27em;    */
/* white-space:nowrap;    */
/* text-overflow:ellipsis;   */
/* -o-text-overflow:ellipsis;    */
/* overflow:hidden;   */
/* }   */
        </style>
        
    </head>
    <body class="body-template">
        <%@ include file="/forestage/template/header_no_bar.jsp" %>
        
        
     <!-- --------------------------------電影介紹list------------------------------------------------ -->
        
        
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
                
<%@ include file="/resources/page_code/page1.file"%>

 <!-- ---------------------------電影情報內容----------------------------------------- -->  
			   <c:forEach var="movie_introduceVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                <div class="section">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-6">
                                <img src="http://pingendo.github.io/pingendo-bootstrap/assets/blurry/800x600/11.jpg" class="img-responsive">
                            </div>
                            <div class="col-md-6">
                                <h3 contenteditable="true">${movie_introduceVO.created_at}</h3>
                                <h1 class="text-primary">
                                    ${movie_introduceVO.title}
                                </h1>
                                <div class="zxx_text_overflow_5"><p>${movie_introduceVO.content}</p></div>
                                <p class="text-primary">
                                    <a href="#"><b>READ MORE</b></a>
                                </p>
                                    
                            
                            </div>
                        </div>
                    </div>
                </div>
               </c:forEach>  
              
              
      
              
            </div>
        </div> 
        
        
 <!-- ---------------------------分頁處理----------------------------------------- -->     
         <div>
				<c:choose>
					<%-- 總頁數小於5 設定迴圈 開始1 結束5 --%>
					<c:when test="<%=pageNumber <= 5%>">
						<c:set var="begin" value="1" />
						<c:set var="end" value="<%=pageNumber%>" />
					</c:when>
					<%-- 大於5 設定迴圈 開始為目前頁數 結束為目前頁數+4 --%>
					<c:otherwise>
						<c:set var="begin" value="<%=whichPage%>" />
						<c:set var="end" value="<%=whichPage + 4%>" />
						<%-- 若是第一頁就設定開始為1 最末頁5 --%>
						<c:if test="${begin -1 <=0} ">
							<c:set var="begin" value="1" />
							<c:set var="end" value="5" />
						</c:if>
						<%-- 若end超過最末頁 開始頁設定最大頁-4 --%>
						<c:if test="${end} > <%=pageNumber%>">
							<c:set var="begin" value="<%=pageNumber - 4%>" />
							<c:set var="end" value="<%=pageNumber%>" />
						</c:if>
					</c:otherwise>
				</c:choose>
 				<ul class="pagination">
					<%-- 判斷第一頁時無法按上一頁 --%>
					<c:choose>
						<c:when test="<%=whichPage <= 1%>">
							<li class="disabled"><a href="#">&laquo;</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="<%=request.getRequestURI()%>?whichPage=<%=whichPage - 1%>">&laquo;</a></li>
						</c:otherwise>
					</c:choose>
 					<c:forEach var="i" begin="${begin}" end="${end}">
						<c:choose>
							<c:when test="${i == page1.whichPage}">
								<li class="active"><a href="#">${i}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="<%=request.getRequestURI()%>?whichPage=${i}">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
 					<%-- 判斷最末頁無法按下一頁 --%>
					<c:choose>
						<c:when test="<%=whichPage >= pageNumber%>">
							<li class="disabled"><a href="#">&raquo;</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="<%=request.getRequestURI()%>?whichPage=<%=whichPage + 1%>">&raquo;</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
<%@ include file="/resources/page_code/page2.file"%>
        
        
        
        

        
        
        
        
        
        
        <!-- --------------------------------電影介紹list------------------------------------------------ -->
        
        <%@ include file="/forestage/template/footer.jsp" %>
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
    </body>
</html>