<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.io.Reader"%>
<%@ page import="org.json.*"%>
<%@ page import="com.report_filmreview_msg.model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<%
	Report_Filmreview_MsgService rmSrc = new Report_Filmreview_MsgService();
	List<Report_Filmreview_MsgVO> list = rmSrc.getAll();
	pageContext.setAttribute("list", list);
%>
	<c:forEach var="rmVO" items="${list}">
	${rmVO.content }
	</c:forEach>

</body>
</html>