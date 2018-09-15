<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>M&amp;S</title>
    <%@ include file="/backstage/template/link.jsp" %>
    <!-- <link rel="stylesheet" href="<%=request.getContextPath()+"/example/han/theater/css/btn-arrow.css"%>"> -->
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
            <div class="container-fluid mt20">
                <form class="form-horizontal">
                    <div class="form-group">
                      <label class="col-md-5 control-label">影城</label>
                      <div class="col-md-3">
                        <input class="form-control" id="" type="text" value="威秀影城" readonly>
                      </div>
                      <div class="col-md-4">
                      </div> 
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">影廳名稱</label>
                      <div class="col-md-3">
                        <input class="form-control" id="" type="text" value="" >
                      </div>
                      <div class="col-md-4">
                      </div> 
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">影廳設備</label>
                      <div class="col-md-3">
                        <select class="form-control">
                           <option value="數位">數位</option>
                           <option value="2D">2D</option>
                           <option value="3D">3D</option>
                           <option value="4DX">4DX</option>
                           <option value="4DX 3D">4DX 3D</option>
                           <option value="IMAX">IMAX</option>
                           <option value="IMAX 3D">IMAX 3D</option>
                           <option value="GC DIG">GC DIG</option>
                           <option value="GC 3D DIG">GC 3D DIG</option>
                        </select>
                      </div>
                      <div class="col-md-4">
                      </div> 
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">排數</label>
                      <div class="col-md-3">
                        <select class="form-control" id="row">
                           <option value="5">5</option>
                           <option value="6">6</option>
                           <option value="7">7</option>
                           <option value="8">8</option>
                           <option value="9">9</option>
                           <option value="10">10</option>
                           <option value="11">11</option>
                           <option value="12">12</option>
                           <option value="13">13</option>
                           <option value="14">14</option>
                           <option value="15">15</option>
                           <option value="16">16</option>
                           <option value="17">17</option>
                           <option value="18">18</option>
                           <option value="19">19</option>
                           <option value="20">20</option>
                           <option value="21">21</option>
                           <option value="22">22</option>
                           <option value="23">23</option>
                           <option value="24">24</option>
                           <option value="25">25</option>
                           <option value="26">26</option>
                        </select>
                      </div>
                      <div class="col-md-4">
                      </div> 
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">行數</label>
                      <div class="col-md-3">
                        <select class="form-control" id="col">
                           <option value="5">5</option>
                           <option value="6">6</option>
                           <option value="7">7</option>
                           <option value="8">8</option>
                           <option value="9">9</option>
                           <option value="10">10</option>
                           <option value="11">11</option>
                           <option value="12">12</option>
                           <option value="13">13</option>
                           <option value="14">14</option>
                           <option value="15">15</option>
                           <option value="16">16</option>
                           <option value="17">17</option>
                           <option value="18">18</option>
                           <option value="19">19</option>
                           <option value="20">20</option>
                        </select>
                      </div>
                      <div class="col-md-1">
                        <a href="#" class="btn btn-primary btn-md" id="gen_seat">
                          <span class="glyphicon glyphicon-cog"></span>&nbsp;產生座位
                        </a>
                      </div>
                      <div class="col-md-3">
                      </div> 
                    </div>
                    <div id="seat_div">
                      <div class="form-group text-center">
                        <i class="fa fa-location-arrow fa-lg" aria-hidden="true">1</i>
                        <button type="button" class="seat btn btn-basic btn-md" id="btn_1_1">1</button><input type='text' class="dp-none" name='input_1_1' id='input_1_1' value='2'>
                        <button type="button" class="seat btn btn-default btn-md" id="btn_1_2">2</button>
                        <button type="button" class="seat btn btn-warning btn-md" id="btn_1_3">3</button>
                        <button type="button" class="seat btn btn-default btn-md" id="btn_1_4">4</button>
                        <button type="button" class="seat btn btn-warning btn-md" id="btn_1_5">1</button>
                        <button type="button" class="seat btn btn-success btn-md" id="btn_1_6">2</button>
                        <button type="button" class="seat btn btn-danger btn-md" id="btn_1_7">3</button>
                        <button type="button" class="seat btn btn-default btn-md" id="btn_1_8">4</button>
                        <button type="button" class="seat btn btn-warning btn-md" id="btn_1_9">1</button>
                        <button type="button" class="seat btn btn-success btn-md" id="btn_1_10">2</button>
                      </div>
                      <div class="form-group text-center">
                        <i class="fa fa-location-arrow fa-lg" aria-hidden="true">2</i>
                        <button type="button" class="seat btn btn-warning btn-md" id="btn_2_1">1</button>
                        <button type="button" class="seat btn btn-success btn-md" id="btn_2_2">2</button>
                        <button type="button" class="seat btn btn-danger btn-md" id="btn_2_3">3</button>
                        <button type="button" class="seat btn btn-default btn-md" id="btn_2_4">4</button>
                        <button type="button" class="seat btn btn-warning btn-md" id="btn_2_5">1</button>
                        <button type="button" class="seat btn btn-success btn-md" id="btn_2_6">2</button>
                        <button type="button" class="seat btn btn-danger btn-md" id="btn_2_7">3</button>
                        <button type="button" class="seat btn btn-default btn-md" id="btn_2_8">4</button>
                        <button type="button" class="seat btn btn-warning btn-md" id="btn_2_9">1</button>
                        <button type="button" class="seat btn btn-success btn-md" id="btn_2_10">2</button>
                      </div>
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
            $("#"+input).val($("#"+tid).attr("data-value"));
            let bid = $("#"+tid).attr("data-btn");
            console.log("bid ", "#"+bid);
            console.log("bid class", $("#"+bid).attr("class"));
            $("#"+bid).attr("class", ($("#"+tid).attr("class"))).removeClass("optbtn");
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
              content += "<i class='fa fa-location-arrow fa-lg' aria-hidden='true'>"+ i +"</i>&nbsp;";
              for(let j = 1; j <= col; j++) {
                  content += "<button type='button' class='seat btn btn-default btn-md' id='btn_"+ i + "_" + j + "'>" + j + "</button>";
                  content += "<input type='text' class='dp-none' name='input_" + i + "_" + j + "' id='input_" + i + "_" + j + "' value='2'>&nbsp;";
              }
              content += "</div>";
          }
          $("#seat_div").append(content);
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
                              "<button type='button' class='btn btn-nonseat optbtn' data-btn='"+ tid +"' data-value='0' id='opt_"+ pos +"_0' data-input='input_"+ pos +"'>非座位</button>" +
                              "<button type='button' class='btn btn-default optbtn' data-btn='"+ tid +"' data-value='2' id='opt_"+ pos +"_2' data-input='input_"+ pos +"' style='border:0px'>可售座</button>" +
                              "<button type='button' class='btn btn-warning optbtn' data-btn='"+ tid +"' data-value='3' id='opt_"+ pos +"_3' data-input='input_"+ pos +"' style='border:0px'>保留座</button>" +
                           "</div>";
                }
            });
            $('#'+tid).popover("toggle");
        });

      });
    </script>
</body>
</html>