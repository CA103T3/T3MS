<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.theater.model.*"%>
<%@ page import="com.filmreview.model.*"%>
<%@ page import="com.movie.model.*"%>
<%@ page import="java.io.Reader"%>
<%@ page import="org.json.*"%>

<%
	FilmreviewDAO fvSvc = new FilmreviewDAO();
	List<FilmreviewVO> list = fvSvc.getAll();
	pageContext.setAttribute("list", list);
	

%>
<jsp:useBean id="mvSvc" scope="page" class="com.movie.model.MovieService" />
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
                	<label>檢視影評&nbsp;&nbsp;&nbsp;</label>
                	<a href="<%=request.getContextPath()%>/backstage/filmreview/fv_add.jsp" class="btn btn-primary">
                	
                		<i class="fa fa-plus-circle" aria-hidden="true"></i>
                		<span class="fs16">&nbsp;新增影評</span>
                	</a>
                </h3>
                <table id="theaters" class="display" style="width:110%">
                    <thead>
                        <tr>
                            <th>影評編號</th>
                            <th>影評標題</th>
                            <th>建立日期</th>
                            <th>修改日期</th>
                            <th>電影名稱</th>
                            <th>會員</th>
                            <th>執行動作</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="filmreviewVO" items="${list}" varStatus="s" begin="<%=0%>" end="<%=list.size()%>">
                        <tr>
                            <td>${filmreviewVO.fr_no}</td>
                            <td>${filmreviewVO.title}</td>
                            <td>${filmreviewVO.created_at}</td>
                            <td>${filmreviewVO.updated_at}</td>
							<td><c:forEach var="mvVO" items="${mvSvc.all}">
									<c:if test="${filmreviewVO.movie_no==mvVO.movie_no}">
	                   					 【${mvVO.movie_name}】
                    				</c:if>
								</c:forEach>
							</td>
							<td>${filmreviewVO.mem_no}</td>
                            <td>
                                <form id="fm-view-${s.index}" method="post" class="dp-inline" action="<%=request.getContextPath()%>/filmreview/filmreview.do">
                                    <button type="submit" id="view-btn-${s.index}" class="btn btn-success fs16 " >
                                        <i class="fa fa-eye" aria-hidden="true"></i>&nbsp;檢視
                                    </button>&nbsp;&nbsp;
                                    <input type="hidden" name=fr_no value="${filmreviewVO.fr_no}">
                                  
                                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>">
                                    <input type="hidden" name="whichRecordIndex" value="${s.index}">
                                    <input type="hidden" name="action" value="getOne">
                                </form>
                                <form id="fm-mod-${s.index}" method="post" class="dp-inline" action="<%=request.getContextPath()%>/filmreview/filmreview.do">
                                    <button type="submit" id="mod-btn-${s.index}" class="btn btn-warning fs16 " >
                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>&nbsp;修改
                                    </button>&nbsp;&nbsp;
                                    <input type="hidden" name="fr_no" value="${filmreviewVO.fr_no}">
                                 
                                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>">
                                    <input type="hidden" name="whichRecordIndex" value="${s.index}">
                                    <input type="hidden" name="action" value="toUpdatePage">
                                </form>
                                <form id="fm-del-${s.index}" method="post" class="dp-inline" action="<%=request.getContextPath()%>/filmreview/filmreview.do">
                                    <button type="button" id="del-btn-${s.index}" class="btn btn-danger fs16 del-btn" data-name="${filmreviewVO.fr_no}" data-form="fm-del-${s.index}">
                                        <i class="fa fa-trash-o" aria-hidden="true"></i>&nbsp;刪除
                                    </button>
                                    <input type="hidden" name="fr_no" value="${filmreviewVO.fr_no}">
                                                            
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
           
            </c:if>
        </c:if>

        $(document).on("click", ".del-btn", function(event){
            console.log("event.currentTarget.id: ", event.currentTarget.id);
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