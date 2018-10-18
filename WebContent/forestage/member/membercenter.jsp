<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- 地址 -->
<script src="/T3MS/js/tw-city-selector.min.js"></script>



<style type="text/css">
.bgm {
	background-image: url('/T3MS/img/bg-01.jpg');
	width: 100%;
	min-height: 100vh;
	display: -webkit-box;
	display: -webkit-flex;
	display: -moz-box;
	display: -ms-flexbox;
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
	align-items: center;
	padding: 15px;
	background-repeat: no-repeat;
	background-position: center;
	background-size: cover;
	position: relative;
	z-index: 1;
}

.bgm::before {
	content: "";
	display: block;
	position: absolute;
	z-index: -1;
	width: 100%;
	height: 100%;
	top: 0;
	left: 0;
	background-color: rgba(0, 0, 0, 0.65);
}

.headframe {
	height: 200px;
	width: 300px;
	position: relative;
}

.headimg {
	max-height: 100%;
	max-width: 100%;
	width: auto;
	height: auto;
	position: absolute;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	margin: auto;
}

.memberinfo {
	color: white;
	font-size: 20px;
	font-weight: bold;
	box-shadow: 1px 1px 3px gray;
	margin-right: 8px;
}

.cantch {
	color: deeppink;
	font-size: 18px;
	box-shadow: 1px 1px 3px red;
	margin-right: 8px;
}

.container-btnp {
	width: 100%;
	display: -webkit-box;
	display: -webkit-flex;
	display: -moz-box;
	display: -ms-flexbox;
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
}

.btnp {
	font-family: Ubuntu-Bold;
	font-size: 18px;
	color: #fff;
	line-height: 1.2;
	text-transform: uppercase;
	display: -webkit-box;
	display: -webkit-flex;
	display: -moz-box;
	display: -ms-flexbox;
	display: flex;
	justify-content: center;
	align-items: center;
	padding: 0 20px;
	min-width: 160px;
	height: 42px;
	border-radius: 21px;
	background: #d41872;
	background: -webkit-linear-gradient(left, #a445b2, #d41872, #fa4299);
	background: -o-linear-gradient(left, #a445b2, #d41872, #fa4299);
	background: -moz-linear-gradient(left, #a445b2, #d41872, #fa4299);
	background: linear-gradient(left, #a445b2, #d41872, #fa4299);
	position: relative;
	z-index: 1;
	-webkit-transition: all 0.4s;
	-o-transition: all 0.4s;
	-moz-transition: all 0.4s;
	transition: all 0.4s;
}

.btnp::before {
	content: "";
	display: block;
	position: absolute;
	z-index: -1;
	width: 100%;
	height: 100%;
	border-radius: 21px;
	background-color: #555555;
	top: 0;
	left: 0;
	opacity: 0;
	-webkit-transition: all 0.4s;
	-o-transition: all 0.4s;
	-moz-transition: all 0.4s;
	transition: all 0.4s;
}

.btnp:hover {
	background-color: transparent;
}

.btnp:hover:before {
	opacity: 1;
}


.style-seven {
	height: 30px;
	border-style: solid;
	border-color: black;
	border-width: 1px 0 0 0;
	border-radius: 20px;
}

.style-seven:before {
	display: block;
	content: "";
	height: 30px;
	margin-top: -31px;
	border-style: solid;
	border-color: black;
	border-width: 0 0 1px 0;
	border-radius: 20px;
}

a {
	color: gray;
	font-size: 16px;
}

.whitebord {
	box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
	min-width: 992px !important;
	background-color: #fff !important;
	border-radius: 0.5rem !important;
	-ms-flex-align: center !important;
	align-items: center !important;
}

.errorm {
	font-size: 8px;
	color: red;
	font-weight: bold;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
	String str = memVO.getBirthday();
	Date date1 = df.parse(str);
	String str1 = df1.format(date1);
%>
<%@ include file="/forestage/template/link.jsp"%>
<meta charset="UTF-8">
<title>MemUpdate</title>
</head>
<body>
	<div class="bgm">
		<c:forEach var="message" items="${errorMsgs}"></c:forEach>
		<div class="container">
			<%@ include file="/forestage/template/header.jsp"%>
			<form class="form-inline"
				action="<%=request.getContextPath()%>/member/update.do"
				method="post" enctype="multipart/form-data">
				<div class="row">
					<div class="col-md-12">
						<img style="display: block; margin: 0 auto; width: 400px;"
							src='/T3MS/img/memcenter.png'>
						<hr class="style-seven">
					</div>
					<div class="col-md-5">
						<div class="headframe">
							<img id="blah"
								src="<%=request.getContextPath() %>/DBGifReaderMem?memno=${memVO.memno}"
								alt="大頭照" class="img-thumbnail img-responsive headimg">
						</div>
						<input type="file" style="position: absolute; right: 88px;"
							id="imgInp" class="custom-file-input" name="memimg">
					</div>

					<div class="col-md-7">

						<table class="table">
							<tr>
								<td><span class="memberinfo">帳號:</span><span class="cantch">${memVO.email}</span></td>
								<td><span class="memberinfo">密碼:</span><span class="cantch">●●●●&nbsp</span>&nbsp&nbsp&nbsp&nbsp<a
									href="#"
									onclick="window.open('changpaw.jsp', '更改密碼', config='height=500,width=500');">修改</a></td>
							</tr>
							<tr>
								<td><label for="lname"><span class="memberinfo">姓氏:</span></label><input
									id="lname" name="lname" class="form-control" type="text"
									value="${memVO.lastname}"></td>
								<td><label for="fname"><span class="memberinfo">名字:</span></label><input
									id="fname" name="fname" class="form-control" type="text"
									value="${memVO.firstname}"></td>
							</tr>
							<tr>
								<td><span class="memberinfo">生日:</span><span class="cantch"><%=str1%></span></td>
								<td><span class="memberinfo">身分證:</span><span
									class="cantch">A123456789</span></td>
							</tr>
							<tr>
								<td><span class="memberinfo">性別:</span><span class="cantch">${(memVO.gender==0)?'男':'女'}</span></td>
								<td><label for="phone"><span class="memberinfo">電話:</span></label><input
									id="phone" name="phone" class="form-control" type="text"
									value="${memVO.phone}"><span class="errorm">&nbsp
										${errorMsgs.phone}</span></td>
							</tr>
							<tr>
								<td><label for="addr"><span class="memberinfo">地址:</span></label><input
									id="addr" name="addr" class="form-control" type="text"
									value="${memVO.addr}"></td>
								<td><label for="locno"><span class="memberinfo">郵區:</span></label><input
									id="locno" name="locno" class="form-control" type="text"
									value="${memVO.locno}"></td>
							</tr>
							
						</table>
							<button style="float: right;" class="btnp" type="submit">修改</button>
									
						
					</div>
					
					<div class="row">
						<div class="col-md-12">					
							<hr class="style-seven">
						</div>
						<div class="col-md-5"></div>
						<div class="col-md-7">
								<a href="<%=request.getContextPath()%>/forestage/member/ones_orderlist.jsp">
									<button style="float: right;" type="button" class="btnp">查詢訂單</button>
								</a>
									<br>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">					
							<hr class="style-seven">
						</div>
						<div class="col-md-5"></div>
						<div class="col-md-7">	
								<a href="<%=request.getContextPath()%>/forestage/Management_tracking/Management_tracking.jsp">
									<button style="float: right;" type="button" class="btnp">電影收藏</button>
								</a>
						</div>
					</div>
				</div>
			</form>


			<%@ include file="/forestage/template/footer.jsp"%>
		</div>
	</div>



	<script>
		function readURL(input) {

			if (input.files && input.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('#blah').attr('src', e.target.result);
				}

				reader.readAsDataURL(input.files[0]);
			}
		}

		$("#imgInp").change(function() {
			readURL(this);
		});
	</script>
</body>


</html>