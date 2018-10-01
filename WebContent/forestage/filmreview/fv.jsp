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

<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<%@ include file="/forestage/template/link.jsp"%>


<link rel="stylesheet" href="/T3MS/css/jsRapStar.css" />
<script src="/T3MS/css/jsRapStar.js"></script>
<title>M&amp;S</title>


<style>
.containe {
	border: 2px solid #dedede;
	background-color: #f1f1f1;
	border-radius: 5px;
	padding: 10px;
	margin: 10px 0;
}

.containe::after {
	content: "";
	clear: both;
	display: table;
}

.containe img {
	float: left;
	max-width: 60px;
	width: 100%;
	margin-right: 20px;
	border-radius: 50%;
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
				<p>${fv.content}</p>
			</div>
		</div>
	</div>



	<div class="section">
		<div class="container ctnr">
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
		<div class="container ctnr">
			<div class="row">
				<div class="containe">
					<img src="http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png" alt="Avatar" style="width: 100%;">
					<p>Hello. How are you today?</p>
					<span class="time-right">11:00</span>
				</div>
			</div>
		</div>
	</div>



	<!-- 	<div class="section"> -->
	<!-- 		<div class="container ctnr" style="background-color: rgb(238, 238, 238) !important;"> -->
	<!-- 			<div class="row"> -->
	<!-- 				<div class="col-md-12"> -->
	<!-- 					<div class="containe"> -->
	<!-- 						<img src="http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png" alt="Avatar" style="width: 100%;"> -->
	<!-- 						<p style="margin-bottom: 35px;">Hello. How are you today?<span class="time-right">11:00</span></p> -->
	<!-- 							<div class="containe" style="margin-left: 80px;"> -->
	<!-- 								<img src="http://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png" alt="Avatar" style="width: 100%;"> -->
	<!-- 								<p>Hello. How are you today?</p> -->
	<!-- 								<span class="time-right">11:00</span> -->
	<!-- 						</div>						 -->
	<!-- 					</div> -->
	<!-- 				</div> -->
	<!-- 			</div>		 -->
	<!-- 		</div> -->
	<!-- 	</div> -->





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