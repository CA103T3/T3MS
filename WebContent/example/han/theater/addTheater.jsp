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
    <style type="text/css">
      .popover-content {
        padding: 0px;
      }
      .dp-none {
        display: none;
      }
      /* http://blog.koalite.com/bbg/ */
      .btn-nonseat { 
        color: #FFFFFF; 
        background-color: #3E3B42; 
        border-color: #4430A6; 
      } 

      .btn-nonseat:hover, 
      .btn-nonseat:focus, 
      .btn-nonseat:active, 
      .btn-nonseat.active, 
      .open .dropdown-toggle.btn-nonseat { 
        color: #FFFFFF; 
        background-color: #52627A; 
        border-color: #4430A6; 
      } 

      .btn-nonseat:active, 
      .btn-nonseat.active, 
      .open .dropdown-toggle.btn-nonseat { 
        background-image: none; 
      } 

      .btn-nonseat.disabled, 
      .btn-nonseat[disabled], 
      fieldset[disabled] .btn-nonseat, 
      .btn-nonseat.disabled:hover, 
      .btn-nonseat[disabled]:hover, 
      fieldset[disabled] .btn-nonseat:hover, 
      .btn-nonseat.disabled:focus, 
      .btn-nonseat[disabled]:focus, 
      fieldset[disabled] .btn-nonseat:focus, 
      .btn-nonseat.disabled:active, 
      .btn-nonseat[disabled]:active, 
      fieldset[disabled] .btn-nonseat:active, 
      .btn-nonseat.disabled.active, 
      .btn-nonseat[disabled].active, 
      fieldset[disabled] .btn-nonseat.active { 
        background-color: #3E3B42; 
        border-color: #4430A6; 
      } 

      .btn-nonseat .badge { 
        color: #3E3B42; 
        background-color: #FFFFFF; 
      }
    </style>
</head>

<body class="fs16">
    <%@ include file="/backstage/template/header.jsp" %>
    <div id="wrapper" class="mt50">
        <%@ include file="/backstage/template/sidebar.jsp" %>
        <div class="flex-column" id="page-content-wrapper">
            <div class="container-fluid">
                <h3 class="page-header"><label>新增影廳</label></h1>
                <%-- 錯誤表列 --%>
                <%-- <%=request.getAttribute("errorMsgs")%> --%>
                <%-- ${errorMsgs.size()} --%>
<%--                 <c:if test="${not empty errorMsgs}"> --%>
<!--                   <font style="color:red">請修正以下錯誤:</font> -->
<!--                   <ul> -->
<%--                     <c:forEach var="message" items="${errorMsgs}"> --%>
<%--                       <li style="color:red">${message}</li> --%>
<%--                     </c:forEach> --%>
<!--                   </ul> -->
<%--                 </c:if> --%>
                <form class="form-horizontal" method="post" action="<%=request.getContextPath()%>/theater/TheaterServletTest">
                    <div class="form-group">
                      <label class="col-md-5 control-label">影城</label>
                      <div class="col-md-3">
                        <input class="form-control" id="" type="text" value="威秀影城" readonly>
                        <input class="form-control" id="" type="hidden" name="cinema_no" value="C001" readonly>
                      </div>
                      <div class="col-md-4">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">影廳名稱</label>
                      <div class="col-md-3">
                        <input class="form-control" id="" type="text" name="theater_name" value="<%= (theaterVO==null) ? "" : theaterVO.getTheater_name() %>" >
                      </div>
                      <div class="col-md-4">
                      </div> 
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">影廳設備</label>
                      <div class="col-md-3">
                        <select class="form-control" name="equipment">
                           <option value="數位" ${(theaterVO.equipment=="數位") ? 'selected': '' }>數位</option>
                           <option value="2D" ${(theaterVO.equipment=="2D")? 'selected': '' }>2D</option>
                           <option value="3D" ${(theaterVO.equipment=="3D")? 'selected': '' }>3D</option>
                           <option value="4DX" ${(theaterVO.equipment=="4DX")? 'selected': '' }>4DX</option>
                           <option value="4DX 3D" ${(theaterVO.equipment=="4DX 3D")? 'selected': '' }>4DX 3D</option>
                           <option value="IMAX" ${(theaterVO.equipment=="IMAX")? 'selected': '' }>IMAX</option>
                           <option value="IMAX 3D" ${(theaterVO.equipment=="IMAX 3D")? 'selected': '' }>IMAX 3D</option>
                           <option value="GC DIG" ${(theaterVO.equipment=="GC DIG")? 'selected': '' }>GC DIG</option>
                           <option value="GC 3D DIG" ${(theaterVO.equipment=="GC 3D DIG")? 'selected': '' }>GC 3D DIG</option>
                        </select>
                      </div>
                      <div class="col-md-4">
                      </div> 
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">排數</label>
                      <div class="col-md-3">
                        <select class="form-control" id="row" name="t_rows">
                           <option value="5" ${(theaterVO.t_rows=="5")? 'selected': '' }>5</option>
                           <option value="6" ${(theaterVO.t_rows=="6")? 'selected': '' }>6</option>
                           <option value="7" ${(theaterVO.t_rows=="7")? 'selected': '' }>7</option>
                           <option value="8" ${(theaterVO.t_rows=="8")? 'selected': '' }>8</option>
                           <option value="9" ${(theaterVO.t_rows=="9")? 'selected': '' }>9</option>
                           <option value="10"  ${(theaterVO.t_rows=="10")? 'selected': '' }>10</option>
                           <option value="11"  ${(theaterVO.t_rows=="11")? 'selected': '' }>11</option>
                           <option value="12"  ${(theaterVO.t_rows=="12")? 'selected': '' }>12</option>
                           <option value="13"  ${(theaterVO.t_rows=="13")? 'selected': '' }>13</option>
                           <option value="14"  ${(theaterVO.t_rows=="14")? 'selected': '' }>14</option>
                           <option value="15"  ${(theaterVO.t_rows=="15")? 'selected': '' }>15</option>
                           <option value="16"  ${(theaterVO.t_rows=="16")? 'selected': '' }>16</option>
                           <option value="17"  ${(theaterVO.t_rows=="17")? 'selected': '' }>17</option>
                           <option value="18"  ${(theaterVO.t_rows=="18")? 'selected': '' }>18</option>
                           <option value="19"  ${(theaterVO.t_rows=="19")? 'selected': '' }>19</option>
                           <option value="20"  ${(theaterVO.t_rows=="20")? 'selected': '' }>20</option>
                           <option value="21"  ${(theaterVO.t_rows=="21")? 'selected': '' }>21</option>
                           <option value="22"  ${(theaterVO.t_rows=="22")? 'selected': '' }>22</option>
                           <option value="23"  ${(theaterVO.t_rows=="23")? 'selected': '' }>23</option>
                           <option value="24"  ${(theaterVO.t_rows=="24")? 'selected': '' }>24</option>
                           <option value="25"  ${(theaterVO.t_rows=="25")? 'selected': '' }>25</option>
                           <option value="26"  ${(theaterVO.t_rows=="26")? 'selected': '' }>26</option>
                        </select>
                      </div>
                      <div class="col-md-4">
                      </div> 
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">行數</label>
                      <div class="col-md-3">
                        <select class="form-control" id="col" name="t_columns">
                           <option value="5" ${(theaterVO.t_columns=="5")? 'selected': '' }>5</option>
                           <option value="6" ${(theaterVO.t_columns=="6")? 'selected': '' }>6</option>
                           <option value="7" ${(theaterVO.t_columns=="7")? 'selected': '' }>7</option>
                           <option value="8" ${(theaterVO.t_columns=="8")? 'selected': '' }>8</option>
                           <option value="9" ${(theaterVO.t_columns=="9")? 'selected': '' }>9</option>
                           <option value="10" ${(theaterVO.t_columns=="10")? 'selected': '' }>10</option>
                           <option value="11" ${(theaterVO.t_columns=="11")? 'selected': '' }>11</option>
                           <option value="12" ${(theaterVO.t_columns=="12")? 'selected': '' }>12</option>
                           <option value="13" ${(theaterVO.t_columns=="13")? 'selected': '' }>13</option>
                           <option value="14" ${(theaterVO.t_columns=="14")? 'selected': '' }>14</option>
                           <option value="15" ${(theaterVO.t_columns=="15")? 'selected': '' }>15</option>
                           <option value="16" ${(theaterVO.t_columns=="16")? 'selected': '' }>16</option>
                           <option value="17" ${(theaterVO.t_columns=="17")? 'selected': '' }>17</option>
                           <option value="18" ${(theaterVO.t_columns=="18")? 'selected': '' }>18</option>
                           <option value="19" ${(theaterVO.t_columns=="19")? 'selected': '' }>19</option>
                           <option value="20" ${(theaterVO.t_columns=="20")? 'selected': '' }>20</option>
                        </select>
                      </div>
                      <div class="col-md-1">
                        <a href="#seat_div" class="btn btn-primary btn-md" id="gen_seat">
                          <span class="glyphicon glyphicon-cog"></span>&nbsp;產生座位
                        </a>
                      </div>
                      <div class="col-md-3">
                      </div>
                    </div>
                    <div id="seat_div">
<!--                       <div class="form-group text-center"> -->
<!--                         <i class="fa fa-location-arrow fa-lg" aria-hidden="true">1</i> -->
<!--                         <button type="button" class="seat btn btn-basic btn-md" id="btn_1_1">1</button><input type='text' class="dp-none" name='input_1_1' id='input_1_1' value='2'> -->
<!--                         <button type="button" class="seat btn btn-default btn-md" id="btn_1_2">2</button> -->
<!--                         <button type="button" class="seat btn btn-warning btn-md" id="btn_1_3">3</button> -->
<!--                         <button type="button" class="seat btn btn-default btn-md" id="btn_1_4">4</button> -->
<!--                         <button type="button" class="seat btn btn-warning btn-md" id="btn_1_5">1</button> -->
<!--                         <button type="button" class="seat btn btn-success btn-md" id="btn_1_6">2</button> -->
<!--                         <button type="button" class="seat btn btn-danger btn-md" id="btn_1_7">3</button> -->
<!--                         <button type="button" class="seat btn btn-default btn-md" id="btn_1_8">4</button> -->
<!--                         <button type="button" class="seat btn btn-warning btn-md" id="btn_1_9">1</button> -->
<!--                         <button type="button" class="seat btn btn-success btn-md" id="btn_1_10">2</button> -->
<!--                       </div> -->
<!--                       <div class="form-group text-center"> -->
<!--                         <i class="fa fa-location-arrow fa-lg" aria-hidden="true">2</i> -->
<!--                         <button type="button" class="seat btn btn-warning btn-md" id="btn_2_1">1</button> -->
<!--                         <button type="button" class="seat btn btn-success btn-md" id="btn_2_2">2</button> -->
<!--                         <button type="button" class="seat btn btn-danger btn-md" id="btn_2_3">3</button> -->
<!--                         <button type="button" class="seat btn btn-default btn-md" id="btn_2_4">4</button> -->
<!--                         <button type="button" class="seat btn btn-warning btn-md" id="btn_2_5">1</button> -->
<!--                         <button type="button" class="seat btn btn-success btn-md" id="btn_2_6">2</button> -->
<!--                         <button type="button" class="seat btn btn-danger btn-md" id="btn_2_7">3</button> -->
<!--                         <button type="button" class="seat btn btn-default btn-md" id="btn_2_8">4</button> -->
<!--                         <button type="button" class="seat btn btn-warning btn-md" id="btn_2_9">1</button> -->
<!--                         <button type="button" class="seat btn btn-success btn-md" id="btn_2_10">2</button> -->
<!--                       </div> -->
                    </div>
                    <div class="form-group text-center">
                      <input type="hidden" name="action" value="insert">
                      <button class="btn btn-basic" id="smtbtn" type="submit" disabled><i class="fa fa-paper-plane" aria-hidden="true"></i>&nbsp;送出</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
    <script src="<%=request.getContextPath()+"/js/back_index.js"%>"></script>
    <script type="text/javascript">
      $(document).ready(function(){
        /*
        $(document).on("click", "#btn_1_1", function(event){
            console.log("click event.target.id: ", event.target.id);
            $('#btn_1_1').popover({
                html:true,
                placement: "top",
                content: function(){
                    return "<div class='btn-group-vertical' style='border:0px' style='display:none'>" +
                              "<button type='button' class='btn btn-nonseat optbtn' data-btn='btn_1_1' data-value='0' id='opt_1_1_0' data-input='input_1_1'>非座位</button>" +
                              "<button type='button' class='btn btn-default optbtn' data-btn='btn_1_1' data-value='2' id='opt_1_1_2' data-input='input_1_1' style='border:0px'>可售座</button>" +
                              "<button type='button' class='btn btn-warning optbtn' data-btn='btn_1_1' data-value='3' id='opt_1_1_3' data-input='input_1_1' style='border:0px'>保留座</button>" +
                           "</div>";
                }
            });
            $('#btn_1_1').popover("toggle");
        });
        */
        //$(".optbtn").on("click", function(event){
        $(document).on("click", ".optbtn", function(event){
            console.log("event.target.id: ", event.target.id);
            let tid = event.target.id;
            let input = $("#"+tid).attr("data-input");
            console.log("input id: ", input);
            console.log("input id: ", $("#"+input).attr("id"));
            console.log(input + " val: " + $("#"+input).val());
            $("#"+input).val($("#"+tid).attr("data-value"));
            let bid = $("#"+tid).attr("data-btn");
            console.log("bid ", "#"+bid);
            console.log("bid class", $("#"+bid).attr("class"));
            $("#"+bid).attr("class", ($("#"+tid).attr("class"))).removeClass("optbtn").addClass("seat").addClass("btn-md");
            $("#"+bid).popover("hide");
            //$("#"+bid).popover("destroy");
        });

        //$("#gen_seat").click(function(){
        $(document).on("click", "#gen_seat", function(event){
            $("#seat_div").empty();
            let row = $("#row").val();
            let col = $("#col").val();
            console.log("row: " + row + " col: " + col);
            let content = "";
            for(let i = 1; i <= row; i++) {
                content += "<div class='form-group text-center'>";
                content += "<i class='fa fa-location-arrow fa-lg' aria-hidden='true'></i>&nbsp;" + i + "&nbsp;&nbsp;";
                for(let j = 1; j <= col; j++) {
                    content += "<button type='button' class='seat btn btn-default btn-md' id='btn_" + i + "_" + j + "'>" + j + "</button>";
                    content += "<input type='hidden' name='input_" + i + "_" + j + "' id='input_" + i + "_" + j + "' value='2'>&nbsp;";
                }
                content += "</div>";
            }
            $("#seat_div").append(content);
            $("#smtbtn").prop("disabled", false);
            $("#smtbtn").removeClass("btn-basic").addClass("btn-primary");
        });

        $(document).on("click", ".seat", function(event){
            console.log("click event.target.id: ", event.target.id);
            let tid = event.target.id;
            let pos = tid.substring(3);
            console.log(pos);
            $("#"+tid).popover({
                html:true,
                placement: "top",
                content: function(){
                    return "<div class='btn-group-vertical' style='border:0px'>" +
                              "<button type='button' class='btn btn-nonseat optbtn' data-btn='"+ tid +"' data-value='0' id='opt"+ pos +"_0' data-input='input"+ pos +"'>非座位</button>" +
                              "<button type='button' class='btn btn-default optbtn' data-btn='"+ tid +"' data-value='2' id='opt"+ pos +"_2' data-input='input"+ pos +"' style='border:0px'>可售座</button>" +
                              "<button type='button' class='btn btn-warning optbtn' data-btn='"+ tid +"' data-value='3' id='opt"+ pos +"_3' data-input='input"+ pos +"' style='border:0px'>保留座</button>" +
                           "</div>";
                }
            });
            $('#'+tid).popover("toggle");
        });

        //show errorMsgs
        <c:if test="${not empty errorMsgs}">
          <c:forEach var="message" items="${errorMsgs}">
            toastr.error("${message}");
          </c:forEach>
        </c:if>

        <c:if test="${not empty theaterVO}">
          $("#seat_div").empty();
          let row = ${theaterVO.t_rows};
          let col = ${theaterVO.t_columns};
          console.log("row: " + row + " col: " + col);
          let content = "";
          for(let i = 1; i <= row; i++) {
              content += "<div class='form-group text-center'>";
              content += "<i class='fa fa-location-arrow fa-lg' aria-hidden='true'></i>&nbsp;" + i + "&nbsp;&nbsp;";
              for(let j = 1; j <= col; j++) {
                  content += "<button type='button' class='seat btn btn-default btn-md' id='btn_" + i + "_" + j + "'>" + j + "</button>";
                  content += "<input type='hidden' name='input_" + i + "_" + j + "' id='input_" + i + "_" + j + "' value='2'>&nbsp;";
              }
              content += "</div>";
          }
          $("#seat_div").append(content);
          $("#smtbtn").prop("disabled", false);
          $("#smtbtn").removeClass("btn-basic").addClass("btn-primary");
        </c:if>

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