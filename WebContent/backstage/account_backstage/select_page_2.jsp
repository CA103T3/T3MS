<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en" >

<head>
	<%@ include file="/backstage/template/link.jsp" %>
  <meta charset="UTF-8">
  <title>MOVIES : Home</title>
  <link rel="stylesheet" href="/T3MS/css/style_3.css">
<style>
  .red:focus{
		outline-color: pink;
	}
</style> 

  
</head>

<body>
<%@ include file="/backstage/template/header.jsp" %>
 <div id="wrapper" class="mt50">
        <%@ include file="/backstage/template/sidebar.jsp" %>
 <%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	
</c:if> 
    	
  <div class="wrapper">
	<div class="container" style="margin-top:-50px;margin-left:540px;">
		<h1>Welcome</h1>
		<FORM class="form" METHOD="post" ACTION="<%=request.getContextPath()%>/backstage/account_backstage/account_backstageServlet.do" >
		
			<input type="text" placeholder="輸入帳號編號" name="bs_acc_no" class="red">
			<input type="password" placeholder="輸入帳號密碼" name="bs_acc_psw" class="red">
			<input type="hidden" name="action" value="getOne_For_Display">
			<input type="reset" value="重設">
			<button type="submit" id="login-button"><a href='addaccount_backstage.jsp'>登入</a></button>
		</form>
	</div>
	
	<ul class="bg-bubbles">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
</div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

  

    <script  src="/T3MS/js/index_6.js"></script>




</body>

</html>
