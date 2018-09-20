<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.announcement.model.*"%>

<%
	request.setCharacterEncoding("utf-8");
	AnnouncementService annSvc = new AnnouncementService();
	AnnouncementVO annVO = new AnnouncementVO();
	List<AnnouncementVO> list = annSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>M&amp;S</title>
<%@ include file="/backstage/template/link.jsp"%>
<style>
table {
	margin-left: auto;
	margin-right: auto;
	text-align: center;
}

th {
	text-align: center;
}

textarea {
	resize: none;
}

input[type=text] {
	padding: 5px;
	border: 2px solid #ccc;
	-webkit-border-radius: 5px;
	border-radius: 5px;
}

input[type=text]:focus {
	border-color: #255;
}

input[type=submit] {
	padding: 5px 15px;
	background: #CCA;
	border: 0 none;
	cursor: pointer;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	border: 0 none;
}

tr:hover {
	background-color: #fcc;
}

th {
	background-color: #afa;
}
</style>
</head>

<body class="fs16">
	<%@ include file="/backstage/template/header.jsp"%>
	<div id="wrapper" class="mt50">
		<%@ include file="/backstage/template/sidebar.jsp"%>
		<div class="flex-column" id="page-content-wrapper">
			<div class="container-fluid mt20">
				<div class="container-fluid">
					<div class="col-sm-10 col-md-10">
						<div class="page-header">
							<h1>公告管理</h1>
							<%@ include file="page1.file"%>
						</div>
						<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#hanhan">新增公告</button>
						<br>
						<br>

						<!-- ==============================================新增跳出的燈箱============================================== -->
						<div class="modal fade" id="hanhan" tabindex="-1" role="dialog" aria-labelledby="hanhan" aria-hidden="true">
							<form action="<%=request.getContextPath()%>/backstage/announcement/ann.do" method="POST">
								<div class="modal-dialog modal-md">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											<h3 class="modal-title" id="myModalLabel">公告內容</h3>
										</div>
										
										<div class="modal-body">
											<textarea class="form-control" name="anc_con" rows="6"></textarea>
										</div>
										
										<div class="modal-footer">
											<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
											<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
											<input type="hidden" name="finalPage" value="<%=pageNumber + 1%>">
											<input type="hidden" name="whichPage" value="<%=whichPage%>">
											<!--只用於:istAllEmp.jsp-->
											<input type="hidden" class="form-control" name="action" value="insert">
											<input type="submit" class="btn btn-success" value="確認">
										</div>
									</div>
								</div>
							</form>
						</div>
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



				<table class="table">
					<tr>
						<th>公告編號</th>
						<th>內容</th>
						<th>建立人員編號</th>
						<th>建立時間</th>
						<th>修改時間</th>
						<th>公告狀態</th>
						<th>設定</th>
						<th></th>
					</tr>
					<c:forEach var="annVO" varStatus="s" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr ${(annVO.anc_no==param.anc_no) ? 'bgcolor=skyblue':''}>
							<td>${annVO.anc_no}</td>
							<td>
								<p>${annVO.anc_con}</p>
							</td>
							<td>${annVO.backstage_no}</td>
							<td>
								<fmt:formatDate value="${annVO.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
								<fmt:formatDate value="${annVO.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>${annVO.active}</td>

							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backstage/announcement/ann.do?anc_no=${annVO.anc_no}&action=getOne_bootstrap" style="margin-bottom: 0px;">
									<input type="submit" value="修改">
									<input type="hidden" name="anc_no" value="${annVO.anc_no}">
									<input type="hidden" name="active" value="${annVO.active}">
									<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
									<!--送出本網頁的路徑給Controller-->
									<input type="hidden" name="whichPage" value="<%=whichPage%>">
									<!--送出當前是第幾頁給Controller-->
								</FORM>
							</td>

							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backstage/announcement/ann.do" style="margin-bottom: 0px;">
									<input type="submit" value="刪除">
									<input type="hidden" name="anc_no" value="${annVO.anc_no}">
									<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
									<!--送出本網頁的路徑給Controller-->
									<input type="hidden" name="whichPage" value="<%=whichPage%>">
									<!--送出當前是第幾頁給Controller-->
									<input type="hidden" name="action" value="delete">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="page2.file"%>


				<ul class="pagination">
					<li>
						<a href="<%=request.getRequestURI()%>?whichPage=<%=whichPage - 1%>">&laquo;</a>
					</li>
					<%
						if (pageNumber > 1) {
					%>
					<%
						for (int i = 1; i <= pageNumber; i++) {
					%>
					<li>
						<a href="<%=request.getRequestURI()%>?whichPage=<%=i%>"><%=i%></a>
					</li>
					<%
						}
					%>
					<%
						}
					%>
					<li>
						<a href="<%=request.getRequestURI()%>?whichPage=<%=whichPage + 1%>">&raquo;</a>
					</li>
				</ul>


				<!-- =========================修改跳出的燈箱========================= -->
				<c:if test="${openupdatereplyform2!=null}">
					<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
						<form METHOD="post" action="<%=request.getContextPath()%>/backstage/announcement/ann.do?anc_no=${annVO.anc_no}&action=update">
							<div class="modal-dialog modal-md">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										<h3 class="modal-title" id="myModalLabel">公告內容</h3>
									</div>

									<div class="modal-body">
										<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
										<textarea class="form-control" name="anc_con" rows="8" style="resize: none">${annVO.anc_con}</textarea>
										<br>
										<c:choose>
											<c:when test="${annVO.active == 0}">
												<label>
													<input name="active" type="radio" value="0" checked="checked">
													下線
												</label>
												<label>
													<input name="active" type="radio" value="1">
													上線
												</label>
											</c:when>
											<c:otherwise>
												<label>
													<input name="active" type="radio" value="0">
													下線
												</label>
												<label>
													<input name="active" type="radio" value="1" checked="checked">
													上線
												</label>
											</c:otherwise>
										</c:choose>
										<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
									</div>
									<div class="modal-footer">
										<input type="hidden" name="anc_con">
										<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
										<!-- 送出本網頁的路徑給Controller -->
										<input type="hidden" name="whichPage" value="<%=whichPage%>">
										<!-- 送出當前是第幾頁給Controller -->
										<button type="submit" class="btn btn-primary">確認</button>
									</div>

								</div>
							</div>
						</form>
					</div>
					<script>
						$("#basicModal").modal({
							show : true
						});
					</script>
				</c:if>
				<!-- =========================修改跳出的燈箱========================= -->
			</div>
		</div>
	</div>
	</div>
	</div>


	<script src="<%=request.getContextPath() + "/js/back_index.js"%>"></script>
</body>
<script>
	$(document).ready(function() {
		var len = 10; // 超過10個字以"..."取代
		$("p").each(function(i) {
			if ($(this).text().length > len) {
				$(this).attr("title", $(this).text());
				var text = $(this).text().substring(0, len - 1) + "  ...";
				$(this).text(text);
			}
		});

		<c:if test="${not empty errorMsgs}">
		<c:forEach var="message" items="${errorMsgs}">
		toastr.error("${message}");
		</c:forEach>
		</c:if>
	});
</script>
</html>