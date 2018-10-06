<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.io.Reader"%>
<%@ page import="org.json.*"%>

<%
	MemService memSvc = new MemService();
    List<MemVO> list = memSvc.getall();
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
                <h3 class="page-header"><label>待審核列表&nbsp;&nbsp;&nbsp;</label></h3>
                <table id="theaters" class="display" style="width:100%">
                    <thead>
                        <tr>
                            <th>會員編號</th>
                            <th>會員名</th>
                            <th>信箱</th>
                            <th>執行動作</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="memVO" items="${list}" varStatus="s" begin="<%=0%>" end="<%=list.size()%>">
                    <c:if test="${memVO.type!=0}">
                        <tr>
                            <td>${memVO.memno}</td>
                            <td>${memVO.lastname}${memVO.firstname}</td>
                            <td>${memVO.email}</td>
                            <td>
                            <c:if test="${memVO.type==1}">
                                <form id="fm-view-${s.index}" method="post" class="dp-inline" action="<%=request.getContextPath()%>/member/Bmember.do">
                                    <button type="submit" id="view-btn-${s.index}" class="btn btn-success fs16 " >
                                        <i class="fa fa-eye" aria-hidden="true"></i>&nbsp;准許
                                    </button>&nbsp;&nbsp;
                                    <input type="hidden" name="memno" value="${memVO.memno}">
                                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>">
                                    <input type="hidden" name="whichRecordIndex" value="${s.index}">
                                    <input type="hidden" name="action" value="becomeFC">
                                </form>
                                </c:if>
                                <c:if test="${memVO.type==2}">
                                <form id="fm-mod-${s.index}" method="post" class="dp-inline" action="">
                                    <button type="submit" id="mod-btn-${s.index}" class="btn fs16 " >
                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>&nbsp;降級
                                    </button>&nbsp;&nbsp;
                                    <input type="hidden" name="memno" value="${memVO.memno}">
                                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>">
                                    <input type="hidden" name="whichRecordIndex" value="${s.index}">
                                    <input type="hidden" name="action" value="">
                                </form>
                                </c:if>
                            </td>
                        </tr>
                        </c:if>
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