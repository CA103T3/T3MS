<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="green">
<% MemVO memVO=(MemVO)session.getAttribute("memVO"); %>

<h1 align="center">~成功~</h1>
<h2 align="center">${memVO.lastname} ${memVO.firstname}你好!<br>你的身分證:${memVO.IDNUM}</h2>
<h4 align="center"><a href="/T3MS/forestage/member/membercenter.jsp">修改會員資料</a></h4><br>
<form action="<%=request.getContextPath()%>/member/logout.do" method="post">
<div align="center"><input type="submit" value="登出" onclick="logout()"></div>
</form>


<script>
function logout() {
　alert("已登出");
}

</script>


</body>
</html>