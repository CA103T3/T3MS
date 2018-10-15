<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.io.Reader"%>
<%@ page import="org.json.*"%>
<%@ page import="com.report_filmreview_msg.model.*" %>
<%@ page import="com.filmreview_msg.model.*" %>
<%
	Report_Filmreview_MsgService rmSrc = new Report_Filmreview_MsgService();
	List<Report_Filmreview_MsgVO> list = rmSrc.getAll();
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="fmSrc" scope="page" class="com.filmreview_msg.model.Filmreview_MsgService" />
<jsp:useBean id="memSrc" scope="page" class="com.member.model.MemService" />

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
                <h3 class="page-header"><label>留言檢舉列表&nbsp;&nbsp;&nbsp;</label></h3>
                <table id="theaters" class="display" style="width:100%">
                    <thead>
                        <tr>
                        	<th>檢舉編號</th>
                            <th>留言編號</th>
                            <th>留言會員</th>
                            <th>留言內容</th>
                            <th>檢舉原因</th>
                            <th>檢舉會員</th>
                            
                            <th>執行動作</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="rmVO" items="${list}" varStatus="s" begin="<%=0%>" end="<%=list.size()%>">
                        <tr>
                            <td>${rmVO.rfm_no}</td>
                            <td>${rmVO.frm_no}</td>                          	
                            <td><c:forEach var="fmVO" items="${fmSrc.all}">
			                    <c:if test="${rmVO.frm_no==fmVO.frm_no}">
				                    <c:forEach var="memVO" items="${memSrc.all}">
					                    <c:if test="${memVO.memno==fmVO.mem_no}">
						                    ${memVO.lastname}${memVO.firstname}
					                    </c:if>
					                </c:forEach>
			                    </c:if>
			                </c:forEach></td>
                            <td><c:forEach var="fmVO" items="${fmSrc.all}">
			                    <c:if test="${rmVO.frm_no==fmVO.frm_no}">
				                    ${fmVO.content}
			                    </c:if>
			                </c:forEach></td>                        
                            <td>${rmVO.content}</td>
                            <td><c:forEach var="memVO" items="${memSrc.all}">
			                    <c:if test="${rmVO.mem_no==memVO.memno}">
				                    ${memVO.lastname}${memVO.firstname}
			                    </c:if>
			                </c:forEach></td>
                            
                            
                            <td>
                                <form id="fm-view-${s.index}" method="post" class="dp-inline" action="<%=request.getContextPath()%>/report_filmreview_msgServlet/report_filmreview_msgServlet.do">
                                    <button type="submit" id="view-btn-${s.index}" class="btn btn-success fs16 " >
                                        <i class="" aria-hidden="true"></i>&nbsp;取消
                                    </button>&nbsp;&nbsp;
                                    <input type="hidden" name="rfm_no" value="${rmVO.rfm_no}">
                                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>">
                                    <input type="hidden" name="whichRecordIndex" value="${s.index}">
                                    <input type="hidden" name="action" value="cancel">
                                </form>
                                <c:if test="${memVO.status!=2}">
                                <form id="fm-mod-${s.index}" method="post" class="dp-inline" action="<%=request.getContextPath()%>/report_filmreview_msgServlet/report_filmreview_msgServlet.do">
                                    <button type="submit" id="mod-btn-${s.index}" class="btn btn-danger fs16 " >
                                        <i class="" aria-hidden="true"></i>&nbsp;刪除
                                    </button>&nbsp;&nbsp;
                                    
<%--                                     <input type="submit" name="rfm_no" value="${rmVO.rfm_no}"> --%>
                                    <input type="hidden" name="rfm_no" value="${rmVO.rfm_no}">
                                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>">
                                    <input type="hidden" name="whichRecordIndex" value="${s.index}">
                                    <input type="hidden" name="action" value="delete">
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