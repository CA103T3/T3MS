<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.theater.model.*"%>
<%@ page import="java.io.Reader"%>
<%@ page import="org.json.*"%>

<%
    TheaterService tSvc = new TheaterService();
	List<TheaterVO> list = tSvc.getAllofCinema(request.getParameter("cinema_no"));
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
                <h3 class="page-header"><label>檢視影廳</label></h1>
                    <table id="theaters" class="display" style="width:100%">
                        <thead>
                            <tr>
                                <th>影廳編號</th>
                                <th>影廳名稱</th>
                                <th>排數</th>
                                <th>行數</th>
                                <th>座位數</th>
                                <th>影廳設備</th>
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tbody>
						<c:forEach var="theaterVO" items="${list}" begin="<%=0%>" end="<%=list.size()%>">
                            <tr>
                                <td>${theaterVO.theater_no}</td>
                                <td>${theaterVO.theater_name}</td>
                                <td>${theaterVO.t_rows}</td>
                                <td>${theaterVO.t_columns}</td>
                                <td>${theaterVO.seats}</td>
                                <td>${theaterVO.equipment}</td>
                                <td>$320,800</td>
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
    } );
    </script>
</body>
</html>