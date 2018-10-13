<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.nav_backstage.model.*"%>
<%@ page import="java.io.Reader"%>
<%@ page import="org.json.*"%>


<%
	NavService nSvc = new NavService();
	List<NavVO> nlist = nSvc.getall();
	pageContext.setAttribute("nlist",nlist);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>


					<div class="form-group">
					 	<select class="form-control" id="nav_no" name="nav_no">
                          <c:forEach var="navVO" items="${nlist}" varStatus="s" begin="<%=0%>" end="<%=nlist.size()%>">                         
                              <option value="${navVO.listitem_no}">${navVO.listitem_name}</option>                            
                          </c:forEach>
                        </select>
					</div>

</body>
</html>