<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body bgcolor="green">

<h1 align="center">~成功~</h1>
<h2 align="center"><%= session.getAttribute("lname") %><%= session.getAttribute("fname") %> 你好!</h2>
</body>
</html>