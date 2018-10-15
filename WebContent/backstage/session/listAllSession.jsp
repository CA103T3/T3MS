<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.session.model.*"%>
<%@ page import="com.theater.model.*"%>
<%@ page import="com.movie.model.*"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<%
    SessionService sSvc = new SessionService();
    List<SessionVO> list = sSvc.getAllofJoinTheaterMovieWhereTheaterNoCinema(request.getParameter("cinema_no"));
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
                <h3 class="page-header">
                    <label>檢視電影場次&nbsp;&nbsp;&nbsp;</label>
                    <a href="<%=request.getContextPath()%>/backstage/session/addSession.jsp?cinema_no=${param.cinema_no}" class="btn btn-primary"><i class="fa fa-plus-circle" aria-hidden="true"></i><span class="fs16">&nbsp;新增電影場次</span></a>
                </h3>
                <table id="sessions" class="display" style="width:100%">
                    <thead>
                        <tr>
                            <th>場次編號</th>
                            <th>影廳名稱</th>
                            <th>電影名稱</th>
                            <th>場次時間</th>
                            <th>執行動作</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="sessionVO" items="${list}" varStatus="s" begin="<%=0%>" end="<%=list.size()-1%>">
                        <tr>
                            <td>${sessionVO.session_no}</td>
                            <td>${sessionVO.theaterVO.theater_name}</td>
                            <td>${sessionVO.movieVO.movie_name}</td>
<%--                             <c:set value="${sessionVO}" var="sessionVO" scope="page"></c:set> --%>
                            <%
                                //https://yq.aliyun.com/articles/70182
                                SessionVO sessionVO = (SessionVO) pageContext.getAttribute("sessionVO");
                                Timestamp session_time = sessionVO.getSession_time();
                                Date date = new Date(session_time.getTime());
                                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                                String strDate = dateFormat.format(date);
                                pageContext.setAttribute("strDate", strDate);
                            %>
                            <td>${strDate}</td>
                            <td>
                                <form id="fm-view-${s.index}" method="post" class="dp-inline" action="<%=request.getContextPath()%>/session/session.do">
                                    <button type="submit" id="view-btn-${s.index}" class="btn btn-success fs16 " >
                                        <i class="fa fa-eye" aria-hidden="true"></i>&nbsp;檢視
                                    </button>&nbsp;&nbsp;
                                    <input type="hidden" name="session_no" value="${sessionVO.session_no}">
                                    <input type="hidden" name="cinema_no" value="${param.cinema_no}">
                                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>">
                                    <input type="hidden" name="whichRecordIndex" value="${s.index}">
                                    <input type="hidden" name="action" value="view">
                                </form>
                                <form id="fm-mod-${s.index}" method="post" class="dp-inline" action="<%=request.getContextPath()%>/session/session.do">
                                    <button type="submit" id="mod-btn-${s.index}" class="btn btn-warning fs16 " >
                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>&nbsp;修改
                                    </button>&nbsp;&nbsp;
                                    <input type="hidden" name="session_no" value="${sessionVO.session_no}">
                                    <input type="hidden" name="cinema_no" value="${param.cinema_no}">
                                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>">
                                    <input type="hidden" name="whichRecordIndex" value="${s.index}">
                                    <input type="hidden" name="action" value="toUpdatePage">
                                </form>
                                <form id="fm-del-${s.index}" method="post" class="dp-inline" action="<%=request.getContextPath()%>/session/session.do">
                                    <button type="button" id="del-btn-${s.index}" class="btn btn-danger fs16 del-btn" data-name="${sessionVO.session_no}" data-form="fm-del-${s.index}">
                                        <i class="fa fa-trash-o" aria-hidden="true"></i>&nbsp;刪除
                                    </button>
                                    <input type="hidden" name="session_no" value="${sessionVO.session_no}">
                                    <input type="hidden" name="cinema_no" value="${param.cinema_no}">
                                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>">
                                    <input type="hidden" name="whichRecordIndex" value="${s.index}">
                                    <input type="hidden" name="action" value="delete">
                                </form>
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
        $('#sessions').DataTable({
          "language": {
            "url": "<%=request.getContextPath()%>/resources/Chinese-traditional.json"
          }
        });

        <c:if test="${not empty list}">
            <c:if test="${not empty param.whichRecordIndex}">
                setTimeout(function(){
                    var table = $('#sessions').DataTable();
                    if(table.rows().count()==${param.whichRecordIndex}) { //when delete last row
                        table.row(table.rows().count() - 1).show().draw(false);
                        console.log("last row: ", (table.rows().count() - 1), " show()");
                    } else {
                        table.row(${param.whichRecordIndex}).show().draw(false);
                        console.log("row: ", ${param.whichRecordIndex}, " show()");
                    }

                }, 100);
                // not work, use setTimeout
                // var table = $('#sessions').DataTable();
                // table.row(${param.whichRecordIndex}).show().draw(false);
            </c:if>
        </c:if>

        $(document).on("click", ".del-btn", function(event){
            console.log("event.currentTarget.id: ", event.target.id);
            let tid = event.currentTarget.id;
            let form = $("#"+tid).attr("data-form");
            let name = $("#"+tid).attr("data-name");
            console.log("form id: ", form);
            $.confirm({
                title: name,
                content: '確定要刪除嗎?',
                buttons: {
                    confirm: {
                        text: '確定刪除',
                        btnClass: 'btn-red',
                        action: function(){
                            $("#"+form).submit();
                        }
                    },
                    cancel: {
                        text: '取消',
                        btnClass: 'btn-default',
                        action: function(){
                            //close
                        }
                    }
                }
            });
        });

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