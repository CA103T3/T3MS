<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cinema.model.*"%>

<%
  CinemaVO cinemaVO = (CinemaVO) request.getAttribute("cinemaVO");
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
                <h3 class="page-header"><label>新增影城</label></h3>
                <form class="form-horizontal" method="post" action="<%=request.getContextPath()%>/cinema/cinema.do">
                    <div class="form-group">
                      <label class="col-md-5 control-label">影城名稱</label>
                      <div class="col-md-3">
                        <input class="form-control" id="" type="text" name="cinema_name" value="<%= (cinemaVO==null) ? "" : cinemaVO.getCinema_name() %>" >
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