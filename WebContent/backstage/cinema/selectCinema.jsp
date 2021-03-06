<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cinema.model.*"%>

<%
    CinemaService cSvc = new CinemaService();
    List<CinemaVO> list = cSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>M&amp;S</title>
    <%@ include file="/backstage/template/link.jsp" %>
<!--     <link rel="stylesheet" href="<%=request.getContextPath()%>/css/addTheater.css"> -->
    <style type="text/css">

    </style>
</head>

<body class="fs16">
    <%@ include file="/backstage/template/header.jsp" %>
    <div id="wrapper" class="mt50">
        <%@ include file="/backstage/template/sidebar.jsp" %>
        <div class="flex-column" id="page-content-wrapper">
            <div class="container">
                <h3 class="page-header"><label>請選擇影城</label></h3>
                <form class="form-horizontal" method="post" 
                    <c:if test="${param.target.equals('theater')}" >
                        action="<%=request.getContextPath()%>/backstage/theater/listAllTheater.jsp"
                    </c:if>
                    <c:if test="${param.target.equals('session')}" >
                        action="<%=request.getContextPath()%>/backstage/session/listAllSession.jsp"
                    </c:if>
                    <c:if test="${param.target.equals('ticketType')}" >
                        action="<%=request.getContextPath()%>/backstage/ticketType/listAllTicketType.jsp"
                    </c:if>
                >
                    <div class="form-group">
                      <label class="col-md-5 control-label">影城名稱</label>
                      <div class="col-md-3">
                        <select class="form-control" id="cinema_no" name="cinema_no">
                          <c:forEach var="cinemaVO" items="${list}" varStatus="s" begin="<%=0%>" end="<%=list.size()-1%>">
                            <option value="${cinemaVO.cinema_no}">${cinemaVO.cinema_name}</option>
                          </c:forEach>
                        </select>
                      </div>
                      <div class="col-md-4">
                      </div>
                    </div>
                    <div class="form-group text-center">
                      <input type="hidden" name="action" value="insert">
                      <button class="btn btn-primary fs16" id="smtbtn" type="submit"><i class="fa fa-paper-plane" aria-hidden="true"></i>&nbsp;送出</button>
                    </div>
                </form>

            </div>
        </div>
    </div>

    <script src="<%=request.getContextPath()+"/js/back_index.js"%>"></script>
    <script type="text/javascript">
      $(document).ready(function(){
        //show errorMsgs
        <c:if test="${not empty errorMsgs}">
          <c:forEach var="message" items="${errorMsgs}">
            toastr.error("${message}");
          </c:forEach>
        </c:if>

      });
    </script>
</body>
</html>