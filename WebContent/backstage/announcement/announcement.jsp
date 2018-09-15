<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>M&amp;S</title>
<%@ include file="/backstage/template/link.jsp"%>
<style type="text/css">
</style>
</head>

<body class="fs16">
	<%@ include file="/backstage/template/header.jsp"%>
	<div id="wrapper" class="mt50">
		<%@ include file="/backstage/template/sidebar.jsp"%>
		<div class="flex-column" id="page-content-wrapper">
			<div class="container-fluid mt20">
				<div class="container-fluid">
					<!-- s -->
					<div class="col-md-2"></div>
					<div class="col-md-10">
						<div class="page-header">
							<h1>公告管理</h1>
						</div>
						<div class="container">
							<button type="button" class="btn btn-info btn-md"
								data-toggle="modal" data-target="#myModal">新增公告</button>
							<div class="modal fade" id="myModal" role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">公告</h4>
										</div>
										<div class="modal-body">
											<textarea class="form-control" id="deblock_udid"
												name="deblock_udid" rows="16" style="resize: none"></textarea>
										</div>
										<div class="modal-footer">
											<form action="" method="POST">
												<div class="input-group">
													<div class="input-group-btn"></div>
													<input type="submit" class="form-control"
														id="exampleInputAmount">
												</div>
											</form>
											<!-- <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
										</div>
									</div>
								</div>
							</div>
						</div>
						<table class="table">
							<head class="label">
							<thead>
								<tr>
									<th>公告編號</th>
									<th>建立人員編號</th>
									<th>公告狀態</th>
									<th>設定</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th scope="row">1</th>
									<td>公告1</td>
									<td>
										<div role="presentation" class="dropdown">
											<button class="btn btn-default dropdown-toggle"
												data-toggle="dropdown" href="#" role="button"
												aria-haspopup="true" aria-expanded="false">
												下線&nbsp; <span class="caret"></span>
											</button>
											<ul class="dropdown-menu">
												<li><a href="#">上線</a></li>
											</ul>
										</div>
									</td>
									<td>
										<div role="presentation" class="dropdown">
											<button class="btn btn-default dropdown-toggle"
												data-toggle="dropdown" href="#" role="button"
												aria-haspopup="true" aria-expanded="false">
												選項&nbsp; <span class="caret"></span>
											</button>
											<ul class="dropdown-menu">
												<li><a href="#">編輯</a></li>
												<li><a href="#">刪除</a></li>
											</ul>
										</div>
									</td>
								</tr>
							</tbody>
							</head>
						</table>
					</div>
					<!-- 分頁 -->
					<div class="col-md-6">
						<nav class="pull-right">
							<ul class="pagination">
								<li class="disabled"><a href="#" aria-label="Previous">
										<span aria-hidden="true">&laquo;</span>
								</a></li>
								<li class="active"><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#">6</a></li>
								<li><a href="#"> <span aria-hidden="true">&raquo;</span>
								</a></li>
							</ul>
						</nav>
					</div>
					<!-- 分頁 -->
				</div>

			</div>
		</div>
	</div>
	<script src="<%=request.getContextPath() + "/js/back_index.js"%>"></script>
</body>
</html>