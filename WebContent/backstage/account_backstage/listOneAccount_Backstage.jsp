<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.account_backstage.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	Account_BackstageVO account_BackstageVO = (Account_BackstageVO) request.getAttribute("account_BackstageVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/backstage/template/link.jsp" %>
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


<title>員工資料 - listOneAccount_Backstage.jsp</title>

</head>

<body bgcolor='white'>
<%@ include file="/backstage/template/header.jsp" %>
 <div id="wrapper" class="mt50">
        <%@ include file="/backstage/template/sidebar.jsp" %>

<center>
	
		 <h3 style="margin-left:100px;">員工資料 - ListOneAccount_Backstage.jsp</h3>
		 <h4 style="margin-left:100px;"><a href="select_page.jsp">回首頁</a></h4>
	
<div class="container" style="margin-left:100px;">
<table class="table table-hover">
<thead>
	<tr class="info">
		<th>帳號編號</th>
		<th>帳號名稱</th>
		<th>角色編號</th>
		<th>影城編號</th>
		<th>帳號密碼</th>
		<th>電子郵件</th>
		<th>聯絡電話</th>
		<th>上次在線時間</th>
		<th>帳號狀態</th>
	</tr>
</thead>
<tbody>	
	<tr>
		<td><%=account_BackstageVO.getBs_acc_no()%></td>
		<td><%=account_BackstageVO.getBs_acc_name()%></td>
		<td><%=account_BackstageVO.getRole_no()%></td>
		<td><%=account_BackstageVO.getCinema_no()%></td>
		<td><%=account_BackstageVO.getBs_acc_psw()%></td>
		<td><%=account_BackstageVO.getEmail()%></td>
		<td><%=account_BackstageVO.getTel()%></td>
		<td><%=account_BackstageVO.getLast_online_time()%></td>
		<td><%=account_BackstageVO.getState()%></td>
		
	</tr>
</tbody>
</table>
</div>
</body>
</html>