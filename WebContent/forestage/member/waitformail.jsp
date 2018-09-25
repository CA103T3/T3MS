<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/util.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/login.css">

<title>waitformail</title>
</head>
<body>

<div class="limiter">
	<div class="container-login100" style="background-image: url('/T3MS/img/bg-01.jpg');">
		<div class="wrap-login100 p-t-30 p-b-50">
			<form class="login100-form validate-form p-b-10 p-t-5" action="<%=request.getContextPath()%>/member/check.do"  method="post">
				<div class="wrap-input100 validate-input">
						<input class="input100" type="text" name="code" placeholder="請輸入驗證碼">
						<span class="focus-input100" data-placeholder="&#xe88a;"></span>	
				</div>
				<div class="container-login100-form-btn m-t-32">
						<button class="login100-form-btn">認證</button>
				</div>
			</form>
			<div class="container" style="align-content: right">
					<div class="align-items-end">
					<a style="color:deeppink;" href="registerf.jsp">重新寄送</a>
			</div>
		</div>
	</div>
</div>

</body>
</html>