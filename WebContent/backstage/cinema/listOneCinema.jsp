<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cinema.model.*"%>

<%
  //CinemaVO cinemaVO = (CinemaVO) request.getAttribute("cinemaVO"); //can comment it, due to using EL
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
                    <label>影城資訊&nbsp;&nbsp;</label>
                    <!-- class="dp-inline" does  not work here, use style -->
                    <form id="fm-mod" method="post" style="display:inline" action="<%=request.getContextPath()%>/cinema/cinema.do">
                        <button type="submit" id="mod-btn" class="btn btn-warning fs16 " >
                            <i class="fa fa-pencil-square-o" aria-hidden="true"></i>&nbsp;修改
                        </button>
                        <input type="hidden" name="cinema_no" value="${cinemaVO.cinema_no}">
                        <!--  <input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>"> -->
                        <input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
                        <input type="hidden" name="whichRecordIndex" value="${param.whichRecordIndex}">
                        <input type="hidden" name="action" value="toUpdatePage">
                    </form>
                    <!-- class="dp-inline" does  not work here, use style -->
                    <form id="fm-back" method="post" style="display:inline" action="<%=request.getContextPath()%>/backstage/cinema/listAllCinema.jsp">
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
                    <label class="col-md-2 control-label text-right">影城編號</label>
                    <div class="col-md-10">
                      <label>${cinemaVO.cinema_no}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-2 control-label text-right">影城名稱</label>
                    <div class="col-md-10">
                      <label>${cinemaVO.cinema_name}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-2 control-label text-right">影城英文名稱</label>
                    <div class="col-md-10">
                      <label>${cinemaVO.cinema_engname}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-2 control-label text-right">影城地址</label>
                    <div class="col-md-10">
                      <label>${cinemaVO.cinema_address}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-2 control-label text-right">服務專線</label>
                    <div class="col-md-10">
                      <label>${cinemaVO.cinema_tel}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-2 control-label text-right">上線</label>
                    <div class="col-md-10">
                      <label>${cinemaVO.active==1 ? "是" : "否"}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-2 control-label text-right">合作狀態</label>
                    <div class="col-md-10">
                      <label>${cinemaVO.state==1 ? "是" : "否"}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-2 control-label text-right">圖片名稱</label>
                    <div class="col-md-10">
                      <label>${cinemaVO.photo_title}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-2 control-label text-right">圖片</label>
                    <div class="col-md-10">
                      <img src="${cinemaVO.photo_path}" style="max-height: 500px">
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-2 control-label text-right">交通資訊</label>
                    <div class="col-md-10">
                      <textarea class="form-control" id="traffic" name="traffic" rows="6" readonly>${cinemaVO.traffic}</textarea>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-2 control-label text-right">影城介紹</label>
                    <div class="col-md-10">
                      <textarea class="form-control" id="traffic" name="introduction" rows="10" readonly>${cinemaVO.introduction}</textarea>
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