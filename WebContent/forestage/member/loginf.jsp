<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login V16</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="stylesheet" type="text/css" href="/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/util.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css">
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100" style="background-image: url('/T3MS/images/bg-01.jpg');">
			<div class="wrap-login100 p-t-30 p-b-50">
			
				<span class="login100-form-title p-b-41">
					<img style="width:400px;" src='/T3MS/images/login.png'>
				</span>
				<form class="login100-form validate-form p-b-33 p-t-5" action="<%=request.getContextPath()%>/member/loginC.do"  method="post">

					<div class="wrap-input100 validate-input" data-validate = "Enter username">
						<input class="input100" type="text" name="email" placeholder="帳號">
						<span class="focus-input100" data-placeholder="&#xe82a;"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate="Enter password">
						<input class="input100" type="password" name="paw" placeholder="密碼">
						<span class="focus-input100" data-placeholder="&#xe80f;"></span>
					</div>
                  
					<div class="container-login100-form-btn m-t-32">
						<button class="login100-form-btn">
							登入
						</button>
					</div>
				</form>
				<div class="container" style="align-content: right">
					<div class="align-items-end">
					<a href="registerf.jsp">註冊會員</a>
					<a href="">忘記密碼</a></div>
				</div>
			</div>
		</div>
	</div>
	


	<div id="dropDownSelect1"></div>
	<script src="/vendor/jquery/jquery-3.2.1.min.js"></script>
	<script src="/js/main.js"></script>

</body>
</html>