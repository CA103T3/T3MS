<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.account_backstage.model.*"%>


<%
	Account_BackstageService account_BackstageService = new Account_BackstageService();
    List<Account_BackstageVO> list = account_BackstageService.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">	
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
body {
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	margin-left: 0px;
	height: 700px;
    background-color: red; /* For browsers that do not support gradients */
    background-image: linear-gradient(to top, #afeeee, #e0ffff); /* Standard syntax (must be last) */
}

.contain {
    height: 1000px;
    background-color: red; /* For browsers that do not support gradients */
    background-image: linear-gradient(to top, #afeeee, #e0ffff); /* Standard syntax (must be last) */
}
</style>

<title>所有員工資料 - listAllAccount_Backstage.jsp</title>


</head>
<body bgcolor='white'>


<center>
	
		 <h3>所有員工資料 - listAllAccount_Backstage.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<div class="container">
<table class="table table-hover">
<thead>
	<tr>
		<th>帳號編號</th>
		<th>帳號名稱</th>
		<th>角色編號</th>
		<th>影城編號</th>
		<th>帳號密碼</th>
		<th>電子郵件</th>
		<th>聯絡電話</th>
		<th>上次在線時間</th>
		<th>帳號狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
 </thead>
 <tbody>	
	<%@ include file="/resources/page_code/page1.file"%>
	<c:forEach var="account_BackstageVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${account_BackstageVO.bs_acc_no}</td>
			<td>${account_BackstageVO.bs_acc_name}</td>
			<td>${account_BackstageVO.role_no}</td>
			<td>${account_BackstageVO.cinema_no}</td>
			<td>${account_BackstageVO.bs_acc_psw}</td>
			<td>${account_BackstageVO.email}</td> 
			<td>${account_BackstageVO.tel}</td>
			<td><fmt:formatDate value="${account_BackstageVO.last_online_time}" pattern="yyyy-MM-dd  HH:mm:ss"/></td>
			<td>${account_BackstageVO.state}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/account_backstage/account_backstageServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="bs_acc_no"  value="${account_BackstageVO.bs_acc_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
				
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/account_backstage/account_backstageServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="bs_acc_no"  value="${account_BackstageVO.bs_acc_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</tbody>	
</table>
</div>
<%@ include file="/resources/page_code/page2.file"%>
</center>
</body>
</html>