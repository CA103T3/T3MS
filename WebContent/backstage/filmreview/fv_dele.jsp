
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.filmreview.model.*"%><!DOCTYPE html>

<jsp:useBean id="fvDAO"  scope="page" class="com.filmreview.model.FilmreviewDAO" />

<html>
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Page Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="main.css" />
    <!--DateTable css-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"><!--no-->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">
    <%@ include file="/backstage/template/link.jsp" %>

    <style>
       
    </style>
    
</head>
<body class="fs16">



    <%@ include file="/backstage/template/header.jsp" %>
    <div id="wrapper" class="mt50">
        <%@ include file="/backstage/template/sidebar.jsp" %>
        
        
        <c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
        <div class="container">
            <div class="row">               
                <div class="col-10">
                        <table id="aaa" class="dataTable" style="width:100%">
                                <thead>
                                    <tr>
                                        <th>影評編號:</th>
                                        <th>電影編號:</th>
                                        <th>建立日期:</th>
                                        <th>修改日期:</th>
                                        <th>影評內容:</th>
                                        <th>評分:</th>
                                        <th>影評標題:</th>
                                        <th>影評來源:</th>
                                        <th>影評網址:</th>
                                        <th>會員編號:</th>
                                        <th>作者:</th>
                                        <th>刪除:</th>
                                    </tr>
                                </thead>
                                
                                
                            
                                <tbody>
                                <c:forEach var="fvVO" items="${fvDAO.all}" varStatus="s">
                                    <tr>
                                        <td>${fvVO.fr_no}</td>
                                        <td>${fvVO.movie_no}</td>
                                        <td>${fvVO.created_at}</td>
                                        <td>${fvVO.updated_at}</td>
                           <td><a href="<%=request.getContextPath()%>/filmreview/filmreview.do?fr_no=${fvVO.fr_no}&action=getOne_From06"><div class="zxx_text_overflow_5">${fvVO.content}</div></a></td>
                                        <td>${fvVO.evaluation}</td>
                                        <td>${fvVO.title}</td>
                                        <td>${fvVO.source}</td>
                                        <td>${fvVO.url}</td>
                                        <td>${fvVO.mem_no}</td>
                                        <td>${fvVO.author}</td>
                                        <td>
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/filmreview/filmreview.do" style="margin-bottom: 0px;">
											<button type="submit" class="btn btn-danger">Danger</button>
											<input type="hidden" name="fr_no" value="${fvVO.fr_no}">
											
											<input type="hidden" name="action" value="delete">
										</FORM>
									</td>
                                    </tr>        
                                 </c:forEach>                                                            
                            </table>        
                </div>
            </div>
        </div>
        






    </div>
    
    
    
    <c:if test="${openModal!=null}">

<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog modal-lg" style="width:1500px">
		<div class="modal-content">
				
			<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title" id="myModalLabel">The Bootstrap modal-header</h3>
            </div>
			
			<div class="modal-body">
<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
               <jsp:include page="listOneEmp.jsp" />
<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
			</div>
			
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
		
		</div>
	</div>
</div>

        <script>
    		 $("#basicModal").modal({show: true});
        </script>
     </c:if>
    <script src="<%=request.getContextPath()+"/js/back_index.js"%>"></script>
</body>

<script src="https://code.jquery.com/jquery-3.3.1.js"></script><!--no-->
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script><!--no-->


<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>

<script>
    $(document).ready(function() {
	
    	 $(".zxx_text_overflow_5").each(function()  
    			 { var maxwidth=20;  
    			  if($(this).text().length>maxwidth){  
    			  $(this).text($(this).text().substring(0,maxwidth));   
    			 $(this).html($(this).html()+'...');   
    			 }   
    			 });  
    	 
    
    $('#aaa').DataTable();
} );
</script>
</html>