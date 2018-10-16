<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.account.model.*"%>
<%@ page import="com.cinema.model.*"%>
<%@ page import="com.role.model.*"%>
<%@ page import="java.io.Reader"%>
<%@ page import="org.json.*"%>

<%
	AccountService aSvc = new AccountService();
    List<AccountVO> list = aSvc.getAll();
    pageContext.setAttribute("list",list);
    
%>
<jsp:useBean id="cSrc" scope="page" class="com.cinema.model.CinemaService" />
<jsp:useBean id="rSrc" scope="page" class="com.role.model.RoleService" />



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
        table.dataTable thead .sorting {
          background: url("<%=request.getContextPath()%>/img/sort_both.png") no-repeat center right;
        }
        table.dataTable thead .sorting_asc {
          background: url("<%=request.getContextPath()%>/img/sort_asc.png") no-repeat center right;
        }
        table.dataTable thead .sorting_desc {
          background: url("<%=request.getContextPath()%>/img/sort_desc.png") no-repeat center right;
        }
        table.dataTable thead .sorting_asc_disabled {
          background: url("<%=request.getContextPath()%>/img/sort_asc_disabled.png") no-repeat center right;
        }
        table.dataTable thead .sorting_desc_disabled {
          background: url("<%=request.getContextPath()%>/img/sort_desc_disabled.png") no-repeat center right;
        }
    </style>
</head>

<body class="fs16">
    <%@ include file="/backstage/template/header.jsp" %>
    <div id="wrapper" class="mt50">
        <%@ include file="/backstage/template/sidebar.jsp" %>
        <div class="flex-column" id="page-content-wrapper">
            <div class="container">
                <h3 class="page-header"><label>後臺帳號列表&nbsp;&nbsp;&nbsp;</label></h3>
                <table id="theaters" class="display" style="width:100%">
                    <thead>
                        <tr>
                            <th>編號</th>
                            <th>帳號</th>
                            <th>角色</th>
                            <th>信箱</th>
                            <th>影城</th>
                            <th>電話</th>
                            <th>上次在線</th>
                            <th>狀態</th>
                            <th>執行動作</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="aVO" items="${list}" varStatus="s" begin="<%=0%>" end="<%=list.size()%>">
                        <tr>
                            <td>${aVO.bs_acc_no}</td>
                            <td>${aVO.bs_acc_name}</td>
                            <td><c:forEach var="rVO" items="${rSrc.all}">
					            <c:if test="${rVO.br_no==aVO.role_no}">${rVO.br_name}</c:if></c:forEach></td>
                            <td>${aVO.email}</td>
                            <td><c:forEach var="cVO" items="${cSrc.all}">
					            	<c:if test="${cVO.cinema_no==aVO.cinema_no}">${cVO.cinema_name}</c:if></c:forEach></td>
                            <td>${aVO.tel}</td>
                            <td>${aVO.last_online_time}</td>
                            <td>${(aVO.state==1)?'<span style="color:green;">啟用</span>':'<span style="color:red;">停用</span>'}</td>
                            <td>
                                <form id="fm-view-${s.index}" method="post" class="dp-inline" action="<%=request.getContextPath()%>/account/account.do">
                                    <button type="submit" id="view-btn-${s.index}" class="btn btn-warning fs16 " >
                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>&nbsp;修改
                                    </button>&nbsp;&nbsp;
                                    <input type="hidden" name="bs_acc_no" value="${aVO.bs_acc_no}">
                                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>">
                                    <input type="hidden" name="whichRecordIndex" value="${s.index}">
                                    <input type="hidden" name="action" value="view">
                                </form>
                                
                                <c:if test="${aVO.state==1}">
                                <form id="fm-mod-${s.index}" method="post" class="dp-inline" action="<%=request.getContextPath()%>/account/account.do">
                                    <button type="submit" id="mod-btn-${s.index}" class="btn btn-danger fs16 " >
                                        <i class="" aria-hidden="true"></i>&nbsp;停權
                                    </button>&nbsp;&nbsp;
                                    <input type="hidden" name="bs_acc_no" value="${aVO.bs_acc_no}">
                                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>">
                                    <input type="hidden" name="whichRecordIndex" value="${s.index}">
                                    <input type="hidden" name="action" value="stop">
                                </form>
                                </c:if>
                                <c:if test="${aVO.state==0}">
                                <form id="fm-mod-${s.index}" method="post" class="dp-inline" action="<%=request.getContextPath()%>/account/account.do">
                                    <button type="submit" id="mod-btn-${s.index}" class="btn btn-success fs16 " >
                                        <i class="" aria-hidden="true"></i>&nbsp;解鎖
                                    </button>&nbsp;&nbsp;
                                    <input type="hidden" name="bs_acc_no" value="${aVO.bs_acc_no}">
                                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>">
                                    <input type="hidden" name="whichRecordIndex" value="${s.index}">
                                    <input type="hidden" name="action" value="unstop">
                                </form>                                                     
                                </c:if>

                        
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <script src="<%=request.getContextPath()+"/js/back_index.js"%>"></script>
    <script type="text/javascript">
    $(document).ready(function() {
        $('#theaters').DataTable({
          "language": {
            "url": "<%=request.getContextPath()%>/resources/Chinese-traditional.json"
          }
        });

        <c:if test="${not empty list}">
            <c:if test="${not empty param.whichRecordIndex}">
                setTimeout(function(){
                    var table = $('#theaters').DataTable();
                    if(table.rows().count()==${param.whichRecordIndex}) { //when delete last row
                        table.row(table.rows().count() - 1).show().draw(false);
                        console.log("last row: ", (table.rows().count() - 1), " show()");
                    } else {
                        table.row(${param.whichRecordIndex}).show().draw(false);
                        console.log("row: ", ${param.whichRecordIndex}, " show()");
                    }

                }, 100);
                // not work, use setTimeout
                // var table = $('#theaters').DataTable();
                // table.row(${param.whichRecordIndex}).show().draw(false);
            </c:if>
        </c:if>

       
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