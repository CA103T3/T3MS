<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/util.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/login.css">
<style>
  #myBtn {
      width: 300px;
      padding: 10px;
      font-size:20px;
      position: absolute;
      margin: 0 auto;
      right: 0;
      left: 0;
      bottom: 50px;
      z-index: 9999;
  }
</style>
<title>waitformail</title>
</head>
<body>

<%MemVO memVO = (MemVO)session.getAttribute("memVO"); %>



<div class="limiter">
	<div class="container-login100" style="background-image: url('/T3MS/img/bg-01.jpg');">
		<div class="wrap-login100 p-t-30 p-b-50">
		
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
		
			
			<form class="login100-form validate-form p-b-10 p-t-5" action="<%=request.getContextPath()%>/member/password.do" method="post">
				<div class="wrap-input100 validate-input">
						<input class="input100" type="text" name="paw" placeholder="請輸入新密碼">
						<span class="focus-input100" data-placeholder="&#xe802;"></span>	
				</div>
				<div class="wrap-input100 validate-input">
						<input class="input100" type="text" name="paw2" placeholder="請確認密碼">
						<span class="focus-input100" data-placeholder="&#xe802;"></span>	
						<input type="hidden" name="email" value="${memVO.email}">
						<input type="hidden" name="action" value="change">
				</div>
				<div class="container-login100-form-btn m-t-32">
						<button class="login100-form-btn">更改</button>
				</div>
			</form>
		</div>
	</div>
</div>


</body>
</html>