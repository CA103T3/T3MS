<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticketType.model.*"%>
<%@ page import="com.theater.model.*"%>

<%
    TypeVO typeVO = (TypeVO) request.getAttribute("typeVO");
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
                    <label>票種票價資訊&nbsp;&nbsp;</label>
                    <!-- class="dp-inline" does  not work here, use style -->
                    <form id="fm-mod" method="post" style="display:inline" action="<%=request.getContextPath()%>/ticketType/ticketType.do">
                        <button type="submit" id="mod-btn" class="btn btn-warning fs16 " >
                            <i class="fa fa-pencil-square-o" aria-hidden="true"></i>&nbsp;修改
                        </button>
                        <input type="hidden" name="cinema_no" value="${param.cinema_no}">
                        <input type="hidden" name="type_no" value="${typeVO.type_no}">
                        <!--  <input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>"> -->
                        <input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
                        <input type="hidden" name="whichRecordIndex" value="${param.whichRecordIndex}">
                        <input type="hidden" name="action" value="toUpdatePage">
                    </form>
                    <!-- class="dp-inline" does  not work here, use style -->
                    <form id="fm-back" method="post" style="display:inline" action="<%=request.getContextPath()%>/backstage/ticketType/listAllTicketType.jsp?cinema_no=${param.cinema_no}">
                        <button type="submit" id="back-btn" class="btn btn-default fs16 " >
                            <i class="fa fa-undo" aria-hidden="true"></i>&nbsp;返回
                        </button>
                        <!-- <input type="hidden" name="cinema_no" value="${cinemaVO.cinema_no}"> -->
                        <!-- <input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>"> -->
                        <input type="hidden" name="whichRecordIndex" value="${param.whichRecordIndex}">
                        <!-- <input type="hidden" name="action" value="view"> -->
                    </form>
                </h3>
                <div class="row form-group">
                    <label class="col-md-6 control-label text-right">票種票價編號</label>
                    <div class="col-md-6">
                      <label>${typeVO.type_no}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-6 control-label text-right">影廳名稱</label>
                    <div class="col-md-6">
                      <label>${typeVO.theaterVO.theater_name}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-6 control-label text-right">購票身分</label>
                    <div class="col-md-6">
                      <label>${(typeVO.identify=="ADULT") ? "全票" : ''}${(typeVO.identify=="COMPLIMENTARY") ? "優待票" : ''}${(typeVO.identify=="GROUP") ? "團體票" : ''}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-6 control-label text-right">放映時段</label>
                    <div class="col-md-6">
                      <label>${(typeVO.time=="NORMAL") ? "一般" : ''}${(typeVO.time=="MAITNEE") ? "早場" : ''}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-6 control-label text-right">票價</label>
                    <div class="col-md-6">
                      <label>${typeVO.price}</label>
                    </div>
                </div>
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