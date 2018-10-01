<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.filmreview.model.*"%>


<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>
<%FilmreviewVO fvVO = (FilmreviewVO) request.getAttribute("fvVO");%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">



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

<title>Insert title here</title>
</head>
<body>
<div class="section">
		<div class="container ctnr" style="width:1350px">
			<div class="row">
				<p calss="text-center">
				${fvVO.content}
				</p>
			</div>
		</div>
	</div>
</body>
</html>