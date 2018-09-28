<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.filmreview.model.*"%>



<%
	FilmreviewDAO fvSvc = new FilmreviewDAO();
	FilmreviewVO fv = fvSvc.findByPrimaryKey(request.getQueryString());
	pageContext.setAttribute("fv", fv);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<!-- include libraries(jQuery, bootstrap) -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<%@ include file="/forestage/template/link.jsp"%>
<title>M&amp;S</title>


<style>
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
					<h1 class="text-primary">&nbsp;[${fv.movie_no}]-${fv.title}</h1>
					<h3 class="text-left">${fv.mem_no}</h3>
					<h3 class="text-right">${fv.updated_at}</h3>


				</div>

			</div>
			<div class="row">
				<div class="col-md-12">
					<hr>
				</div>
			</div>
		</div>
	</div>

	<div class="section">
		<div class="container ctnr">
			<div class="row">
				<p>
				${fv.content}
				</p>
			</div>
		</div>
	</div>



	<div class="section">
		<div class="container ctnr">
			<div class="row">
				<div class="col-md-12">
					<hr>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<h3>電影評分:</h3>
					<h3>${fv.evaluation}</h3>
					<h3>影評網址:</h3>
					<h3>${fv.url}</h3>
					<h3>影評來源:</h3>
					<h3>${fv.source}</h3>


				</div>

			</div>

			<div class="row">
				<div class="col-md-12">
					<hr>
				</div>
			</div>
		</div>
	</div>
	<div class="container ctnr">
		<form action="/action_page.php">

			<div class="row">


				<textarea id="subject" name="subject" placeholder="Write something.." style="height: 200px"></textarea>

			</div>
			<div class="text-right">
				<input type="submit" value="送出">
			</div>
		</form>
	</div>

	<div class="section">
		<div class="container ctnr" style="background-color: rgb(238, 238, 238) !important;">
			<div class="row">
				<div class="col-md-1">
					<img src="http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png" class="img-circle img-responsive">
				</div>
				<div class="col-md-11">
					<div>
						<h3 class="text-left">
							John Doe <small>5秒前</small>
						</h3>
					</div>
					<p>wtf!!!</p>
				</div>
			</div>
		</div>
	</div>
	<div class="section">
		<div class="container ctnr" style="background-color: rgb(238, 238, 238) !important;">
			<div class="row">
				<div class="col-md-1">
					<img src="http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png" class="img-circle img-responsive">
				</div>
				<div class="col-md-11">
					<div>
						<h3 class="text-left">
							John Doe <small>5秒前</small>
						</h3>
					</div>
					<p>wtf!!!</p>
				</div>
			</div>
		</div>
	</div>


	<%@ include file="/forestage/template/footer.jsp"%>

	<script src="<%=request.getContextPath()%>/js/template.js"></script>
	<script>
		$(document).ready(function() {
			$("li:contains('合作影城')").addClass("custom-active");
		});
	</script>

</body>
</html>