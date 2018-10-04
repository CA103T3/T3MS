<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<!doctype html>
<html>
<head>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


	<style type="text/css">
	
	.container {
		min-width: 992px !important;
	}

	.bg-white {
		background-color: lightyellow !important;
	}

	.rounded {
		border-radius: 0.5rem !important;
	}

	.align-items-center {
		-ms-flex-align: center !important;
		align-items: center !important;
	}

	.offset-sm-1 {
		margin-left: 8.333333%;
	}

	.form-row {
		display: -ms-flexbox;
		display: flex;
		-ms-flex-wrap: wrap;
		flex-wrap: wrap;
		margin-right: -5px;
		margin-left: -5px;
	}

	.form-row > .col,
	.form-row > [class*="col-"] {
		padding-right: 5px;
		padding-left: 5px;
	}

	.hrno{
		visibility: hidden;
		margin-top: 0px;
	}

	.bgm{
		background-color: lightyellow;
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
		
	}
	.btnp{
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
		background: -webkit-linear-gradient(left, #00FFFF, #00AAFF,#5555FF);
		background: -o-linear-gradient(left, #00FFFF, #00AAFF, #5555FF);
		background: -moz-linear-gradient(left, #00FFFF, #00AAFF, #5555FF);
		background: linear-gradient(left, #00FFFF, #00AAFF, #5555FF);
		position: relative;
		z-index: 1;

		-webkit-transition: all 0.4s;
		-o-transition: all 0.4s;
		-moz-transition: all 0.4s;
		transition: all 0.4s;
		}


		.errorm{
			font-size:8px;
			color:red;
			font-weight:bold;
		}
</style>



	<%@ include file="/forestage/template/link.jsp" %>
<title>register</title>
</head>
<body>


<jsp:useBean id="MemSrc" scope="page" class="com.member.model.MemService" />

<div class="bgm" >
	<div class="container shadow p-3 mb-5" >
	<%@ include file="/forestage/template/header_no_bar.jsp" %>
		<div class="row align-items-center bg-white rounded" style="margin-top:50px;">
			<div class="col-12 col-sm-10 offset-sm-1">
			
			<c:if test="${not empty errorMsgs}">
				<div class="alert alert-danger alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					
							<ul>
					   			<c:forEach var="message" items="${errorMsgs}">
									<li style="color:red;font-size:30px;font-weight:bold">${message.value}</li>
								</c:forEach>
							</ul>
				</div>
			</c:if>
			
			
				<form action="<%=request.getContextPath()%>/member/registerC.do" method="post">
					<div class="form-row" style="
    padding-top: 25px;">
						<div class="col-md-6 mb-3" style="
    padding-bottom: 25px;">
							<label for="validationDefault03">揪團名稱</label><span class="errorm">&nbsp ${errorMsgs.email}</span>
							<input type="text" class="form-control" id="validationDefault03" name=""
							value="${param.email}" required>
						</div>
						<div class="col-md-4 mb-3">
							<label for="validationDefaultUsername">截止時間</label>
							<div class="input-group">
								<input type="datetime-local" class="form-control" name="birthday" aria-describedby="inputGroupPrepend2" required>
							</div>
						</div>
						
					</div>
					<hr>
					<div class="form-row">
						<div class="col-md-6 mb-3" style="
    padding-bottom: 25px;">
							<label for="validationDefault03">集合地點</label><span class="errorm">&nbsp ${errorMsgs.email}</span>
							<input type="text" class="form-control" id="validationDefault03" name=""
							value="${param.email}" required>
						</div>
						<div class="col-md-4 mb-3">
							<label for="validationDefaultUsername">集合時間</label>
							<div class="input-group">
								<input type="datetime-local" class="form-control" name="birthday" aria-describedby="inputGroupPrepend2" required>
							</div>
						</div>
						
					</div>
					<hr>
				

					<div class="form-row"><div class="col-md-12 mb-12"></div></div>

					<div class="form-row">
						
						<div class="col-md-6 mb-3">
							<label>選擇場次</label>
							
							<span class="errorm">&nbsp ${errorMsgs.ad}&nbsp ${errorMsgs.dr}</span>
							
								
								<div class="form-group">
									<select class="form-control" id="dept3">
										<option>影城</option>
										
									</select>
								</div>
								<div class="my-selector-b"></div>
								<div class="form-group">
									<select class="form-control" id="dept3">
										<option>影廳</option>
										
									</select>
								</div>
								<div class="my-selector-b"></div>
								<div class="form-group">
									<select class="form-control" id="dept3">
										<option>日期</option>
										
									</select>
								</div>
								<div class="my-selector-b"></div>
								<div class="form-group">
									<select class="form-control" id="dept3">
										<option>場次</option>
										<option>研發部</option>
										
									</select>
								</div>
						</div>
						
					
					</div>

					<hr class="hrno">

					<div class="form-row">
						
					</div>

					<hr>
					<div class="form-row"><div class="col-md-12 mb-12"><label>詳細描述</label></div></div>

					<div class="policy">
						<textarea class="form-control" rows="10" >
依誠實信用、平等互惠原則，共商解決之道。
						</textarea>
					</div>
					
					<div class="form-group">
						
					</div>
					<button class="btnp" type="submit">註冊</button>
				</form>
				<hr>

			</div>
		</div>
	</div>
</div>


</body>



</html>