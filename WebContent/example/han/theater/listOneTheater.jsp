<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.theater.model.*"%>
<%@ page import="java.io.Reader"%>
<%@ page import="org.json.*"%>

<%
  TheaterVO theaterVO = (TheaterVO) request.getAttribute("theaterVO");
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
    </style>
</head>

<body class="fs16">
    <%@ include file="/backstage/template/header.jsp" %>
    <div id="wrapper" class="mt50">
        <%@ include file="/backstage/template/sidebar.jsp" %>
        <div class="flex-column" id="page-content-wrapper">
            <div class="container">
                <h3 class="page-header"><label>影廳資訊</label></h1>

                <div class="row">
                    <label class="col-md-6 control-label text-right">影城</label>
                    <div class="col-md-6">
                      <label>${theaterVO.cinema_no} cinema_name(to be continued)</label>
                    </div>
                </div>
                <div class="row">
                    <label class="col-md-6 control-label text-right">影廳編號</label>
                    <div class="col-md-6">
                      <label>${theaterVO.theater_no}</label>
                    </div>
                </div>
                <div class="row">
                    <label class="col-md-6 control-label text-right">影廳名稱</label>
                    <div class="col-md-6">
                      <label>${theaterVO.theater_name}</label>
                    </div>
                </div>
                <div class="row">
                    <label class="col-md-6 control-label text-right">影廳設備</label>
                    <div class="col-md-6">
                      <label>${theaterVO.equipment}</label>
                    </div>
                </div>
                <div class="row">
                    <label class="col-md-6 control-label text-right">排數</label>
                    <div class="col-md-6">
                      <label>${theaterVO.t_rows}</label>
                    </div>
                </div>
                <div class="row">
                    <label class="col-md-6 control-label text-right">行數</label>
                    <div class="col-md-6">
                      <label>${theaterVO.t_columns}</label>
                    </div>
                </div>
                <div class="row">
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
      $(document).ready(function(){

        //show errorMsgs
        <c:if test="${not empty errorMsgs}">
          <c:forEach var="message" items="${errorMsgs}">
            toastr.error("${message}");
          </c:forEach>
        </c:if>

        <%-- generate seats --%>
        <c:if test="${not empty theaterVO}">
          $("#seat_div").empty();
          let row = ${theaterVO.t_rows};
          //let row = ${rows};
          let col = ${theaterVO.t_columns};
          //let col = ${cols};
          console.log("row: " + row + " col: " + col);
          let content = "";
          for(let i = 1; i <= row; i++) {
              content += "<div class='row text-center'>";
              content += "<i class='fa fa-location-arrow fa-lg' aria-hidden='true'></i>&nbsp;" + ('0'+i).slice(-2) + "&nbsp;&nbsp;";
              for(let j = 1; j <= col; j++) {
                  content += "<button type='button' class='seat btn btn-default btn-md' id='btn_" + i + "_" + j + "' disabled>" + j + "</button>";
                  content += "<input type='hidden' name='input_" + i + "_" + j + "' id='input_" + i + "_" + j + "' value='2'>&nbsp;";
              }
              content += "</div>";
          }
          $("#seat_div").append(content);
          $("#smtbtn").prop("disabled", false);
          $("#smtbtn").removeClass("btn-basic").addClass("btn-primary");
        </c:if>

        <%-- set status of seats --%>
        <%
        if(theaterVO != null) {
            Reader model = theaterVO.getSeat_model();
            char[] arr = new char[8 * 1024];
            StringBuilder buffer = new StringBuilder();
            int numCharsRead;
            while ((numCharsRead = model.read(arr, 0, arr.length)) != -1) {
                buffer.append(arr, 0, numCharsRead);
            }

            model.close();
            String str = buffer.toString();
            JSONObject json = new JSONObject(str);
            Iterator<?> keys = json.keys();
            JSONArray ary = null;
            while(keys.hasNext()){
                String key = (String)keys.next();
                ary = json.getJSONArray(key);

                switch((String)ary.get(1)) {
                    case "0":
        %>
                        $("#btn_<%=key%>").attr("class", "seat btn btn-nonseat btn-md");
        <%
                        break;
                    case "2":
        %>
                        $("#btn_<%=key%>").attr("class", "seat btn btn-default btn-md");
        <%
                        break;
                    case "3":
        %>
                        $("#btn_<%=key%>").attr("class", "seat btn btn-warning btn-md");
        <%
                        break;
                    default:
                        break;
                }
        %>
                $("#input_<%=key%>").val(<%=ary.get(1)%>);
<%--         <%=key%> <%=" "%> <%=json.get(key)%> <%=" "%> <%=ary.get(0)%> <%=" "%> <%=ary.get(1)%> --%>
        <%
            }
        }
        %>

      });
    </script>
</body>
</html>