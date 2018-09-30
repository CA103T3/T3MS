<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.session.model.*"%>
<%@ page import="com.movie.model.*"%>
<%@ page import="com.theater.model.*"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

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
    <style type="text/css">
    #loding_spinner{
        position:fixed;
        _position:absolute;
        top:40%;              /* center */
        left:50%;
        z-index:9999;         /* in front */
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
                  <label>新增電影場次</label>
                    <form id="fm-back" method="post" style="display:inline" action="<%=request.getContextPath()%>/backstage/session/listAllSession.jsp?cinema=<%=request.getParameter("cinema_no")%>">
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
                                Timestamp tt = sessionVO.getSession_time();
                                Date date = new Date(tt.getTime());
                                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                                String session_time = dateFormat.format(date);
                                pageContext.setAttribute("session_time", session_time);
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
                console.log(JSON.stringify(data, undefined, 2));
                console.log(textStatus);
                /*
                if (data.status == "done") {
                    // weird, prevent form submitting
                    if (data.empty) {
                        $('form').on('submit', function () {return false;});
                        console.log("empty");
                    }
                    $('tbody').html(data.tbody);
                    $('#summary').html(data.summary);
                }*/
            }).fail(function (jqXHR, textStatus, errorThrown) {
                console.log('jqXHR.responseText: ' + jqXHR.responseText);
                console.log('jqXHR.status: ' + jqXHR.status);
                /*
                msg = JSON.parse(jqXHR.responseText);
                // jAlert(msg.error.message, '注意');
                if (jqXHR.status == 400) {
                    // console.log(typeof msg.error.message.msg);
                    // console.log(msg.error.message.msg);
                    // console.log(msg);
                    res = JSON.parse(msg.error.message);
                    // console.log('res: ' + res);
                    // console.log('typeof res.stock: ' + typeof res.stock);
                    console.log('res.stock: ' + res.stock);
                    // console.log('next p: ' + $(this).next('p').html()); //failed, due to not original this
                    console.log('self next p: ' + $(self).next('p').html());
                    console.log('input p: ' + $('#quantity_' + bookid).find('input[name^="book"]').next('p').html());

                    if(res.stock != undefined) {
                        jAlert(res.msg, '注意', function () {
                            // $(this).next('p').replaceWith(res.stock); //failed, due to not original this
                            $(self).next('p').replaceWith(res.stock);
                            // $('#quantity_' + bookid).find('input[name^="book"]').next('p').replaceWith(res.stock);
                            $('#stock_' + bookid).val(res.stock_val);
                        });
                    } else
                        jAlert(msg.error.message, '注意');
                }
                */

            }).always(function (jqXHR, textStatus) {
                $('#loding_spinner').fadeOut(300);
            });

        });


      });
    </script>
</body>
</html>