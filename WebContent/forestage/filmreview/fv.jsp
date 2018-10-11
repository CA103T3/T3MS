<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.filmreview.model.*"%>
<%@ page import="com.movie.model.*"%>
<%@ page import="com.filmreview_msg.model.*"%>
<%@ page import="com.member.model.*"%>




<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");

	FilmreviewDAO fvSvc = new FilmreviewDAO();
	FilmreviewVO fv = fvSvc.findByPrimaryKey(request.getParameter("fr_no").trim());
	pageContext.setAttribute("fv", fv);

	Filmreview_MsgDAO fvmSvc = new Filmreview_MsgDAO();
	Set<Filmreview_MsgVO> fvm = fvmSvc.getAllByFrNo(request.getParameter("fr_no").trim());
	pageContext.setAttribute("fvm", fvm);
	
	
%>
<jsp:useBean id="mvSvc" scope="page" class="com.movie.model.MovieService" />
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemService" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<!-- include libraries(jQuery, bootstrap) -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="/T3MS/css/fv_tooltips.css">

<%@ include file="/forestage/template/link.jsp"%>

                                      <link rel="stylesheet" href="/T3MS/css/amodal.css">
 
<link rel="stylesheet" href="/T3MS/css/jsRapStar.css" />
<script src="/T3MS/js/jsRapStar.js"></script>
<title>M&amp;S</title>


<style>
.button1 {
    background-color: #4CAF50; /* Green */
    border: none;
    
   background-color:white; color: #aaa;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
}
.time-right {
	float: right;
	color: #aaa;
}

* {
	box-sizing: border-box;
}

input[type=text], select, textarea {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	resize: vertical;
}

input[type=submit] {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	float: center;
}

input[type=submit]:hover {
	background-color: #45a049;
}

.ctnr {
	border-radius: 5px;
	background-color: rgb(238, 238, 238);
	padding: 20px;
}

/* Clear floats after the columns */
.row:after {
	content: "";
	display: table;
	clear: both;
}

/* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other instead of next to each other */
@media screen and (max-width: 600px) {
	.col-25, .col-75, input[type=submit] {
		width: 100%;
		margin-top: 0;
	}
}


</style>


</head>
<body class="body-template">
	<%@ include file="/forestage/template/header.jsp"%>

	<div class="section">
		<div class="container ctnr">
			<div class="row">
				<div class="col-md-12">
					<h1 class="text-primary">
						&nbsp;
						<c:forEach var="mvVO" items="${mvSvc.all}">
							<c:if test="${fv.movie_no==mvVO.movie_no}">
	                   					 【${mvVO.movie_name}】
                    		</c:if>
						</c:forEach>${fv.title}</h1>
					<h3 class="text-left">
					<c:forEach var="memVO" items="${memSvc.all}">
							<c:if test="${fv.mem_no==memVO.memno}">
	                   			${memVO.lastname}${memVO.firstname} 
                    		</c:if>
					</c:forEach>
				
					</h3>
					<h3 class="text-right">${fv.updated_at}</h3>


				</div>

			</div>
		
			<div class="row">
				<p>${fv.content}</p>
			</div>
		
			<div class="row">




				<div class="col-md-12">
					<h3>電影評分:</h3>
				</div>
				<div class="col-md-2">
					<div id="dem5" start="${fv.evaluation}"></div>
				</div>
				<div class="col-md-10">
					<h4>${fv.evaluation}分</h4>
				</div>



				<div class="col-md-12">
					<h3>影評網址:</h3>
					<h3>${fv.url}</h3>
				</div>
				<div class="col-md-12">
					<h3>影評來源:</h3>
					<h3>${fv.source}</h3>
				</div>







				<div class="col-md-12">
					<hr>
				</div>
			</div>
		</div>

	</div>
		<div class="container ctnr">
	<form METHOD="post" ACTION="<%=request.getContextPath()%>/forestage/filmreview_msg/filmreview_msg.do" class="form-horizontal">
		<input type="hidden" name="fr_no" value="${fv.fr_no}"> <input type="hidden" name="mem_no" value="${fv.mem_no}">

			<div class="row">

				<textarea id="subject" name="content" placeholder="Write something.." style="height: 200px"></textarea>
			</div>
			<div class="text-right">
				<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <input type="hidden" name="action" value="insert"> <input type="submit" value="送出">
			</div>
	</form>





	
				<div class="row">
			<c:forEach var="fvm" items="${fvm}" varStatus="reportfilm">
					<div class="containe" style="height: 90px; background-color: white; padding-top: 20px;">
						<div class="col-sm-1 text-center">

							<c:forEach var="memVO" items="${memSvc.all}">
							<c:if test="${fvm.mem_no==memVO.memno}">

								<img src="<%=request.getContextPath() %>/DBGifReaderMem?memno=${memVO.memno}" class="center-block img-circle img-responsive">
								<p class="text-center">${memVO.firstname}${memVO.lastname}</p>
							</c:if>
						</c:forEach>

						</div>

					

            				<div class="tool-tip " style="float: right;">
								<i class="tool-tip__icon" style="color: white important;">檢</i>
								<p class="tool-tip__info">						
									<span class="info__title"><button type="button" class = "button1"  data-toggle="modal" data-target="#hanhan${reportfilm.index}">
										確認檢舉此留言？
									</button></span>									
								</p>
							</div>
						
				
						<p>${fvm.content}</p>				
						<span class="time-right">${fvm.updated_at}</span>
					</div>
			



		
					
<!-- ==============================================新增跳出的燈箱============================================== -->
						<div class="container">
							<div>
								<form action="<%=request.getContextPath()%>/report_filmreview_msgServlet/report_filmreview_msgServlet.do" method="POST">
									<div class="modal fade" id="hanhan${reportfilm.index}" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
										<div class="modal-dialog modal-md">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
													<h3 class="modal-title" id="myModalLabel">檢舉內容</h3>
												</div>
												<div class="modal-body">
													<textarea class="form-control" name="content" rows="6"></textarea>
												</div>
												<div class="modal-footer">
													<div class="input-group">
														<div class="input-group-btn"></div>
														
														<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
														<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
														<input type="hidden" name="fr_no" value="${fv.fr_no}">
														<input type="hidden" name="mem_no" value="${memVO.memno}">
														<input type="hidden" name="frm_no" value="${fvm.frm_no}">
														<!--只用於:istAllEmp.jsp-->
														<input type="hidden" class="form-control" name="action" value="insert">
														<input type="submit" class="btn btn-success" value="確認">
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
<!-- ==============================================新增跳出的燈箱============================================== -->


				
			</c:forEach>
		</div>
	</div>




	<%@ include file="/forestage/template/footer.jsp"%>

	<script src="<%=request.getContextPath()%>/js/template.js"></script>
	<script>
		$(document).ready(function() {
			$("li:contains('合作影城')").addClass("custom-active");
			$('#dem5').jsRapStar({
				length : 5,
				starHeight : 26,
				enabled : false,
			});

		});
	</script>

</body>
</html>