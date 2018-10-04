<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.io.Reader"%>
<%@ page import="org.json.*"%>
<%@ page import="java.io.IOException"%>
<%@ page import="javax.servlet.jsp.SkipPageException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>

<%
MemVO memVO = (MemVO) request.getAttribute("memVO");
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
String str = memVO.getBirthday();
Date date1 = df.parse(str);
String str1 = df1.format(date1);
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/addTheater.css">
    <style type="text/css">
      /*
      #loding_spinner{
          position:fixed;
          _position:absolute;
          top:40%;              /* center */
          left:50%;
          z-index:9999;         /* in front */
      }
      */
      .dp-inline {
          display:inline;
      }
    </style>
</head>

<body class="fs16">
    <%@ include file="/backstage/template/header.jsp" %>
    <div id="wrapper" class="mt50">
        <%@ include file="/backstage/template/sidebar.jsp" %>
        <div class="flex-column" id="page-content-wrapper">
            <div class="container">
                <h3 class="page-header">
                    <label>會員資訊&nbsp;&nbsp;</label>
                    <form id="fm-back" method="post" style="display:inline" action="<%=request.getContextPath()%>/backstage/member/listAllMember.jsp?memno=${memVO.memno}">
                        <button type="submit" id="back-btn" class="btn btn-default fs16 " >
                            <i class="fa fa-undo" aria-hidden="true"></i>&nbsp;返回
                        </button>
                        <!-- <input type="hidden" name="theater_no" value="${theaterVO.theater_no}"> -->
                        <!-- <input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>"> -->
                        <input type="hidden" name="whichRecordIndex" value="${param.whichRecordIndex}">
                        <!-- <input type="hidden" name="action" value="view"> -->
                    </form>
                </h3>

                
                <div class="row form-group">
                    <label class="col-md-6 control-label text-right">會員編號</label>
                    <div class="col-md-6">
                      <label>${memVO.memno}</label>
                    </div>
                </div>
                 <div class="row form-group">
                 	<label class="col-md-6 control-label text-right">照片</label>
                    <div class="col-md-6">
                      <img align="middle" src="<%=request.getContextPath() %>/DBGifReaderMem?memno=${memVO.memno}"
								alt="大頭照" style="width:50%;border-style:solid;border-width:3px;padding:1px;border-color:black;">
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-6 control-label text-right">會員名稱</label>
                    <div class="col-md-6">
                      <label>${memVO.lastname}${memVO.firstname}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-6 control-label text-right">帳號</label>
                    <div class="col-md-6">
                      <label>${memVO.email}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-6 control-label text-right">生日</label>
                    <div class="col-md-6">
                      <label><%=str1%></label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-6 control-label text-right">身分證字號</label>
                    <div class="col-md-6">
                      <label>${memVO.IDNUM}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-6 control-label text-right">性別</label>
                    <div class="col-md-6">
                      <label>${(memVO.gender==0)?'男':'女'}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-6 control-label text-right">通訊地址</label>
                    <div class="col-md-6">
                      <label>${memVO.addr}&nbsp&nbsp${memVO.locno}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-6 control-label text-right">電話</label>
                    <div class="col-md-6">
                      <label>${memVO.phone}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-6 control-label text-right">影評身分</label>
                    <div class="col-md-6">
                      <label>${(memVO.type==0)?'否':'是'}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-6 control-label text-right">狀態</label>
                    <div class="col-md-6">
                      <label>${(memVO.status==2)?'<span style="color:red;">封鎖</span>':(memVO.status==0)?'<span style="color:orange;">未認證</span>':'<span style="color:green;">認證</span>'}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-6 control-label text-right">違規次數</label>
                    <div class="col-md-6">
                      <label>${memVO.violation}</label>
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
    </script>
</body>
</html>