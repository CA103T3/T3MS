<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.session.model.*"%>
<%@ page import="com.movie.model.*"%>
<%@ page import="com.theater.model.*"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.io.Reader"%>
<%@ page import="org.json.*"%>

<%
    SessionVO sessionVO = (SessionVO) request.getAttribute("sessionVO");

    TheaterService tSvc = new TheaterService();
    List<TheaterVO> tList = tSvc.getAllofCinema(request.getParameter("cinema_no"));
    pageContext.setAttribute("tList",tList);

    MovieService mSvc = new MovieService();
    List<MovieVO> mList = mSvc.getAll();
    pageContext.setAttribute("mList",mList);
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
                    <label>新增電影場次&nbsp;&nbsp;</label>
                    <form id="fm-back" method="post" style="display:inline" action="<%=request.getContextPath()%>/backstage/session/listAllSession.jsp?cinema_no=<%=request.getParameter("cinema_no")%>">
                        <button type="submit" id="back-btn" class="btn btn-default fs16 " >
                            <i class="fa fa-undo" aria-hidden="true"></i>&nbsp;返回
                        </button>
                        <!-- <input type="hidden" name="session_no" value="${sessionVO.session_no}"> -->
                        <!-- <input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>"> -->
                        <input type="hidden" name="whichRecordIndex" value="${param.whichRecordIndex}">
                        <!-- <input type="hidden" name="action" value="view"> -->
                    </form>
                </h3>
                <form class="form-horizontal" method="post" action="<%=request.getContextPath()%>/session/session.do">
                    <div class="form-group">
                      <label class="col-md-5 control-label">影廳名稱</label>
                      <div class="col-md-3">
                        <select class="form-control" id="theater_no" name="theater_no">
                          <c:forEach var="theaterVO" items="${tList}" varStatus="s" begin="<%=0%>" end="<%=tList.size()%>">
                            <option value="${theaterVO.theater_no}" ${(theaterVO.theater_no==sessionVO.theater_no)? 'selected': '' }>${theaterVO.theater_name}</option>
                          </c:forEach>
                        </select>
                      </div>
                      <div class="col-md-4">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">電影名稱</label>
                      <div class="col-md-3">
                        <select class="form-control" id="movie_no" name="movie_no">
                          <c:forEach var="movieVO" items="${mList}" varStatus="s" begin="<%=0%>" end="<%=mList.size()%>">
                            <c:if test="${movieVO.active==1}">
                              <option value="${movieVO.movie_no}" ${(movieVO.movie_no==sessionVO.movie_no)? 'selected': '' }>${movieVO.movie_name}</option>
                            </c:if>
                          </c:forEach>
                        </select>
                      </div>
                      <div class="col-md-4">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">場次時間</label>
                      <div class="col-md-3">
                        <%
                            //https://yq.aliyun.com/articles/70182
                            if(sessionVO != null) {
                                if(sessionVO.getSession_time() != null) {
                                    Timestamp tt = sessionVO.getSession_time();
                                    Date date = new Date(tt.getTime());
                                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                                    String session_time = dateFormat.format(date);
                                    pageContext.setAttribute("session_time", session_time);
                                }
                            }
                        %>
                        <input class="form-control" id="session_time" type="text" name="session_time" value='${(sessionVO==null) ? "" : session_time}' >
                      </div>
                      <div class="col-md-4">
                      </div>
                    </div>
                    <div id="seat_div">
                    </div>
                    <div class="form-group text-center">
                      <input type="hidden" name="action" value="insert">
                      <input class="form-control" id="" type="hidden" name="cinema_no" value="${param.cinema_no}" readonly>
                      <button class="btn btn-primary fs16" id="smtbtn" type="submit"><i class="fa fa-paper-plane" aria-hidden="true"></i>&nbsp;送出</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
    <div id="loding_spinner" style="display: none;">
      <i class="fa fa-spinner fa-pulse fa-3x fa-fw"></i>
      <span class="sr-only">座位產生中...</span>
    </div>
    <script src="<%=request.getContextPath()+"/js/back_index.js"%>"></script>
    <script type="text/javascript">
        //generate seats from ajax done or sessionVO (failureView.forward from SessionServlet)
        function gen_seat_session(row, col, model) {
            $("#seat_div").empty();
            //$('#loding_spinner').fadeIn(200);
            $('#loding_spinner').fadeOut(300, function(){
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
                        if(model[key][1] == "0") {
                            content += "disabled";
                        }
                        content += " >" + j + "</button>";
                        content += "<input type='hidden' name='input_" + i + "_" + j + "' id='input_" + i + "_" + j + "' value='" + model[key][1] + "'>&nbsp;";
                    }
                    content += "</div>";
                }
                $("#seat_div").append(content);
                $("#smtbtn").prop("disabled", false);
                $("#smtbtn").removeClass("btn-basic").addClass("btn-primary");
            });
        }

        $(document).ready(function(){
            //show errorMsgs
            <c:if test="${not empty errorMsgs}">
              <c:forEach var="message" items="${errorMsgs}">
                toastr.error("${message}");
              </c:forEach>
            </c:if>

            $.datetimepicker.setLocale('zh'); // kr ko ja en
            $('#session_time').datetimepicker({
                theme: '',          //theme: 'dark',
                timepicker: true,   //timepicker: false,
                step: 1,            //step: 60 (這是timepicker的預設間隔60分鐘)
                format: 'Y-m-d H:i:00',
                value: new Date(),
                //disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
                //startDate:         '2017/07/10',  // 起始日
                //minDate:           '-1970-01-01', // 去除今日(不含)之前
                //maxDate:           '+1970-01-01'  // 去除今日(不含)之後
            });

            $(document).on("click", ".seat", function(event){
                console.log("click event.currentTarget.id: ", event.currentTarget.id);
                let tid = event.currentTarget.id;
                let pos = tid.substring(3);
                console.log(pos);
                $("#"+tid).popover({
                    html:true,
                    placement: "top",
                    content: function(){
                        return "<div class='btn-group-vertical' style='border:0px'>" +
                                  //"<button type='button' class='btn btn-nonseat optbtn' data-btn='"+ tid +"' data-value='0' id='opt"+ pos +"_0' data-input='input"+ pos +"'>非座位</button>" +
                                  "<button type='button' class='btn btn-default optbtn' data-btn='"+ tid +"' data-value='2' id='opt"+ pos +"_2' data-input='input"+ pos +"' style='border:0px'>可售座</button>" +
                                  "<button type='button' class='btn btn-warning optbtn' data-btn='"+ tid +"' data-value='3' id='opt"+ pos +"_3' data-input='input"+ pos +"' style='border:0px'>保留座</button>" +
                               "</div>";
                    }
                });
                $('#'+tid).popover("toggle");
            });

            //$(".optbtn").on("click", function(event){
            $(document).on("click", ".optbtn", function(event){
                console.log("event.currentTarget.id: ", event.currentTarget.id);
                let tid = event.currentTarget.id;
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

            <%-- generate seats from theater (initialize seats) --%>
            <%
            if(tList.size() != 0) {
                if(tList.get(0) != null && sessionVO == null) {
                    Reader model = tList.get(0).getSeat_model();
                    char[] arr = new char[8 * 1024];
                    StringBuilder buffer = new StringBuilder();
                    int numCharsRead;
                    while ((numCharsRead = model.read(arr, 0, arr.length)) != -1) {
                        buffer.append(arr, 0, numCharsRead);
                    }

                    model.close();
                    String strModel = buffer.toString();
                    pageContext.setAttribute("strModel", strModel);
                }
            }
            %>
            <c:if test="${not empty tList && empty sessionVO}">
                let row_init = ${tList.get(0).t_rows};
                let col_init = ${tList.get(0).t_columns};
                let model_init = ${strModel};
                gen_seat_session(row_init, col_init, model_init);
            </c:if>

            <%
            /* generate seats from sessionVO (failureView.forward from SessionServlet) */
            if(sessionVO != null) {
                String seat_table = sessionVO.getSeat_table();
                String theater_no = sessionVO.getTheater_no();
                TheaterVO tVO = tSvc.getOneTheater(theater_no);
                Integer row = tVO.getT_rows();
                Integer col = tVO.getT_columns();
            %>
                let row = <%=row%> ;
                let col = <%=col%> ;
                let model = <%=seat_table%> ;
                gen_seat_session(row, col, model);
            <%
            }

            %>

            $(document).on('change', '#theater_no', function (event) {

                $.ajax({
                    type: "POST",
                    cache: false,
                    url: "<%=request.getContextPath()%>/session/session.do",
                    data: {'action': 'get_seat_model', 'theater_no': $("#theater_no").val()},
                    dataType: 'json',
                    beforeSend: function (xhr) {
                        $('#loding_spinner').fadeIn(200);
                    }
                }).done(function (data, textStatus) {
                    //console.log(JSON.stringify(data, undefined, 2));
                    //console.log(textStatus);

                    if (data.status == "done") {
                        gen_seat_session(data.t_rows, data.t_columns, data.seat_model);
                    }

                }).fail(function (jqXHR, textStatus, errorThrown) {
                    console.log('jqXHR.responseText: ' + jqXHR.responseText);
                    console.log('jqXHR.status: ' + jqXHR.status);

                }).always(function (jqXHR, textStatus) {
                    //$('#loding_spinner').fadeOut(300);
                });

            });

        });
    </script>
</body>
</html>