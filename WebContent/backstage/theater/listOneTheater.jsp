<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.theater.model.*"%>
<%@ page import="com.cinema.model.*"%>
<%@ page import="java.io.Reader"%>
<%@ page import="org.json.*"%>
<%@ page import="java.io.IOException"%>
<%@ page import="javax.servlet.jsp.SkipPageException"%>

<%
    TheaterVO theaterVO = (TheaterVO) request.getAttribute("theaterVO");
    CinemaService cSvc = new CinemaService();
    CinemaVO cinemaVO = cSvc.getOneCinema(request.getParameter("cinema_no"));
    pageContext.setAttribute("cinemaVO",cinemaVO);
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
    </style>
</head>

<body class="fs16">
    <%@ include file="/backstage/template/header.jsp" %>
    <div id="wrapper" class="mt50">
        <%@ include file="/backstage/template/sidebar.jsp" %>
        <div class="flex-column" id="page-content-wrapper">
            <div class="container">
                <h3 class="page-header">
                    <label>影廳資訊&nbsp;&nbsp;</label>
                    <!-- class="dp-inline" does  not work here, use style -->
                    <form id="fm-mod" method="post" style="display:inline" action="<%=request.getContextPath()%>/theater/theater.do">
                        <button type="submit" id="mod-btn" class="btn btn-warning fs16 " >
                            <i class="fa fa-pencil-square-o" aria-hidden="true"></i>&nbsp;修改
                        </button>
                        <input type="hidden" name="theater_no" value="${theaterVO.theater_no}">
                        <input type="hidden" name="cinema_no" value="${param.cinema_no}">
                        <!--  <input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>"> -->
                        <input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
                        <input type="hidden" name="whichRecordIndex" value="${param.whichRecordIndex}">
                        <input type="hidden" name="action" value="toUpdatePage">
                    </form>
                    <!-- class="dp-inline" does  not work here, use style -->
                    <form id="fm-back" method="post" style="display:inline" action="<%=request.getContextPath()%>/backstage/theater/listAllTheater.jsp">
                        <button type="submit" id="back-btn" class="btn btn-default fs16 " >
                            <i class="fa fa-undo" aria-hidden="true"></i>&nbsp;返回
                        </button>
                        <input type="hidden" name="cinema_no" value="${param.cinema_no}">
                        <!-- <input type="hidden" name="theater_no" value="${theaterVO.theater_no}"> -->
                        <!-- <input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>"> -->
                        <input type="hidden" name="whichRecordIndex" value="${param.whichRecordIndex}">
                        <!-- <input type="hidden" name="action" value="view"> -->
                    </form>
                </h3>

                <div class="row form-group">
                    <label class="col-md-6 control-label text-right">影城名稱</label>
                    <div class="col-md-6">
                      <label>${cinemaVO.cinema_name}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-6 control-label text-right">影廳編號</label>
                    <div class="col-md-6">
                      <label>${theaterVO.theater_no}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-6 control-label text-right">影廳名稱</label>
                    <div class="col-md-6">
                      <label>${theaterVO.theater_name}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-6 control-label text-right">影廳設備</label>
                    <div class="col-md-6">
                      <label>${theaterVO.equipment}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-6 control-label text-right">排數</label>
                    <div class="col-md-6">
                      <label>${theaterVO.t_rows}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-6 control-label text-right">行數</label>
                    <div class="col-md-6">
                      <label>${theaterVO.t_columns}</label>
                    </div>
                </div>
                <div class="row form-group">
                    <label class="col-md-6 control-label text-right">座位數</label>
                    <div class="col-md-6">
                      <label>${theaterVO.seats}</label>
                    </div>
                </div>
                <div id="seat_div" class="row text-center mt20">
                </div>
            </div>
        </div>
    </div>

    <script src="<%=request.getContextPath()+"/js/back_index.js"%>"></script>
    <script type="text/javascript">
        //a little different from addTheater.jsp and addSession.jsp
        function gen_seat_disabled(row, col, model) {
            $("#seat_div").empty();
            //$('#loding_spinner').fadeIn(200);
            //$('#loding_spinner').fadeOut(300, function(){
                console.log("row: " + row + " col: " + col);
                let content = "";
                for(let i = 1; i <= row; i++) {
                    content += "<div class='form-group text-center'>";
                    content += "<i class='fa fa-location-arrow fa-lg' aria-hidden='true'></i>&nbsp;" + ('0'+i).slice(-2) + "&nbsp;&nbsp;";
                    for(let j = 1; j <= col; j++) {
                        content += "<button type='button' class='seat btn ";
                        let key = i + "_" + j ;
                        switch(model[key][1]) {
                            case "0":
                                content += "btn-nonseat";
                                break;
                            case "2":
                                content += "btn-default";
                                break;
                            case "3":
                                content += "btn-warning";
                                break;
                            default:
                                console.log("switch default: ", model[key][1]);
                                break;
                        }

                        content += " btn-md' id='btn_" + i + "_" + j + "' " ;
                        /*
                        if(model[key][1] == "0") {
                            content += "disabled";
                        }
                        */
                        content += " disabled>" + j + "</button>";
                        content += "<input type='hidden' name='input_" + i + "_" + j + "' id='input_" + i + "_" + j + "' value='" + model[key][1] + "'>&nbsp;";
                    }
                    content += "</div>";
                }
                $("#seat_div").append(content);
                $("#smtbtn").prop("disabled", false);
                $("#smtbtn").removeClass("btn-basic").addClass("btn-primary");
            //});
        }

        $(document).ready(function(){

            //show errorMsgs
            <c:if test="${not empty errorMsgs}">
              <c:forEach var="message" items="${errorMsgs}">
                toastr.error("${message}");
              </c:forEach>
            </c:if>

            <%-- generate seats --%>
            <%
            if(theaterVO != null) {
                // Reader model = theaterVO.getSeat_model();
                // char[] arr = new char[8 * 1024];
                // StringBuilder buffer = new StringBuilder();
                // int numCharsRead;
                // while ((numCharsRead = model.read(arr, 0, arr.length)) != -1) {
                    // buffer.append(arr, 0, numCharsRead);
                // }

                // model.close();
                // String strModel = buffer.toString();
                String strModel = theaterVO.getSeat_model();
                pageContext.setAttribute("strModel", strModel);
            }
            %>

            <c:if test="${not empty theaterVO}">
                let row = ${theaterVO.t_rows};
                let col = ${theaterVO.t_columns};
                let model = ${strModel};
                gen_seat_disabled(row, col, model);
            </c:if>
        });
    </script>
</body>
</html>