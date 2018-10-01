<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticketType.model.*"%>
<%@ page import="com.theater.model.*"%>

<%
    TypeVO typeVO = (TypeVO) request.getAttribute("typeVO");

    TheaterService tSvc = new TheaterService();
    List<TheaterVO> tList = tSvc.getAllofCinema(request.getParameter("cinema_no"));
    pageContext.setAttribute("tList",tList);
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
                <h3 class="page-header">
                    <label>新增票種票價&nbsp;&nbsp;</label>
                    <form id="fm-back" method="post" style="display:inline" action="<%=request.getContextPath()%>/backstage/ticketType/listAllTicketType.jsp?cinema_no=<%=request.getParameter("cinema_no")%>">
                        <button type="submit" id="back-btn" class="btn btn-default fs16 " >
                            <i class="fa fa-undo" aria-hidden="true"></i>&nbsp;返回
                        </button>
                        <!-- <input type="hidden" name="session_no" value="${sessionVO.session_no}"> -->
                        <!-- <input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>"> -->
                        <input type="hidden" name="whichRecordIndex" value="${param.whichRecordIndex}">
                        <!-- <input type="hidden" name="action" value="view"> -->
                    </form>
                </h3>
                <form class="form-horizontal" method="post" action="<%=request.getContextPath()%>/ticketType/ticketType.do">
                    <div class="form-group">
                      <label class="col-md-5 control-label">影廳名稱</label>
                      <div class="col-md-3">
                        <select class="form-control" id="theater_no" name="theater_no">
                          <c:forEach var="theaterVO" items="${tList}" varStatus="s" begin="<%=0%>" end="<%=tList.size()%>">
                            <option value="${theaterVO.theater_no}" ${(theaterVO.theater_no==typeVO.theater_no)? 'selected': '' }>${theaterVO.theater_name}</option>
                          </c:forEach>
                        </select>
                      </div>
                      <div class="col-md-4">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">購票身分</label>
                      <div class="col-md-3">
                        <select class="form-control" name="identify">
                           <option value="ADULT" ${(typeVO.identify=="ADULT") ? 'selected': '' }>全票</option>
                           <option value="COMPLIMENTARY" ${(typeVO.identify=="COMPLIMENTARY")? 'selected': '' }>優待票</option>
                           <option value="GROUP" ${(typeVO.identify=="GROUP")? 'selected': '' }>團體票</option>
                        </select>
                      </div>
                      <div class="col-md-4">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">放映時段</label>
                      <div class="col-md-3">
                        <select class="form-control" name="time">
                           <option value="NORMAL" ${(typeVO.time=="NORMAL") ? 'selected': '' }>一般</option>
                           <option value="MAITNEE" ${(typeVO.time=="MAITNEE")? 'selected': '' }>早場</option>
                        </select>
                      </div>
                      <div class="col-md-4">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">票價</label>
                      <div class="col-md-3">
                        <input class="form-control" id="" type="text" name="price" value="<%= (typeVO==null) ? "" : typeVO.getPrice() %>" >
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