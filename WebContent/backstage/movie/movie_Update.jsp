<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.movie.model.*"%>

<%
	MovieVO movieVO = (MovieVO) request.getAttribute("movieVO"); //MovieServlet.java (Concroller) 存入req的movieVO物件 (包括幫忙取出的movieVO, 也包括輸入資料錯誤時的movieVO物件)
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>M&amp;S</title>
<%@ include file="/backstage/template/link.jsp"%>

<!-- movie_back_movie CSS -->
<link
	href="/css/movie_back_movie.css"
	rel="stylesheet" type="text/css">
<style type="text/css">
</style>
</head>

<body class="fs16" ata-spy="scroll">
	<%@ include file="/backstage/template/header.jsp"%>
	<div id="wrapper" class="mt50">
		<%@ include file="/backstage/template/sidebar.jsp"%>
		<div class="flex-column" id="page-content-wrapper">



			<!-- movie_Update -->

				<div class="section">
					<div class="container">
						<div class="row">
							<div class="col-md-10">
								<div class="page-header text-warning">
									<h1>
										電影修改 <font color="#777777"> <span
											style="font-size: 23.4px; line-height: 23.4px;">Movie
												Update</span>
										</font>
									</h1>
								</div>

								<%-- 錯誤表列 --%>
								<c:if test="${not empty errorMsgs}">
									<font style="color: red">請修正以下錯誤:</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color: red">${message}</li>
										</c:forEach>
									</ul>
								</c:if>


								<form METHOD="post" ACTION="<%=request.getContextPath()%>/backstage/movie/movie.do" name="form1" enctype="multipart/form-data">
									<div class="form-group has-error">
										<label class="control-label">電影編號:*</label>
										<div><%=movieVO.getMovie_no()%></div>
									</div>
									<div class="form-group">
										<label class="control-label">電影種類:</label> <select
											class="form-control" name="movie_type">
											<option value="動作片"
												${movieVO.movie_type.equals('動作片')?'selected':'' }>動作片
											
											<option value="喜劇片"
												${movieVO.movie_type.equals('喜劇片')?'selected':'' }>喜劇片
											
											<option value="恐怖片"
												${movieVO.movie_type.equals('恐怖片')?'selected':'' }>恐怖片
											
											<option value="劇情片"
												${movieVO.movie_type.equals('劇情片')?'selected':'' }>劇情片
											
											<option value="驚悚片"
												${movieVO.movie_type.equals('驚悚片')?'selected':'' }>驚悚片
											
											<option value="冒險片"
												${movieVO.movie_type.equals('冒險片')?'selected':'' }>冒險片
											
											<option value="科幻片"
												${movieVO.movie_type.equals('科幻片')?'selected':'' }>科幻片
											
											<option value="戰爭片"
												${movieVO.movie_type.equals('戰爭片')?'selected':'' }>戰爭片
											
										</select>
									</div>
									<div class="form-group">
										<label class="control-label">電影名稱:</label> <input
											class="form-control" type="text" name="movie_name" value="<%=movieVO.getMovie_name()%>">
									</div>
									<div class="form-group">
										<label class="control-label">英文名稱:</label> <input
											class="form-control" type="text" name="eng_name" value="<%=movieVO.getEng_name()%>">
									</div>
									<div class="form-group">
										<label class="control-label">電影圖片:</label> <input
											class="form-control" type="file" name="movie_pic" >
									</div>
									<div class="form-group hidden-md hidden-sm has-feedback">
										<label class="control-label">上映日期:</label> 
										<input class="form-control" type="date" name="relased" id="f_date1">
									</div>
									<div class="form-group hidden-md hidden-sm has-feedback">
										<label class="control-label">發行商:</label> <input
											class="form-control" type="text" name="distributed" value="<%=movieVO.getDistributed()%>">
									</div>
									<div class="form-group hidden-md hidden-sm has-feedback">
										<label class="control-label">片長:</label> <input
											class="form-control" type="text" name="length" value="<%=movieVO.getLength()%>">
									</div>
									<div class="form-group hidden-md hidden-sm has-feedback">
										<label class="control-label">語言:</label> <input
											class="form-control" type="text" name="language" value="<%=movieVO.getLanguage()%>">
									</div>
									<div class="form-group hidden-md hidden-sm has-feedback">
										<label class="control-label">產地:</label> <input
											class="form-control" type="text" name="madein" value="<%=movieVO.getMadein()%>">
									</div>
									<div class="form-group hidden-md hidden-sm has-feedback">
										<label class="control-label">IMDB評分:</label> <input
											class="form-control" type="text" name="imdb" value="<%=movieVO.getImdb()%>">
									</div>
									<div class="form-group hidden-md hidden-sm has-feedback">
										<label class="control-label">爛番茄評分:</label> <input
											class="form-control" type="text" name="tomato" value="<%=movieVO.getTomato()%>">
									</div>
									<div class="form-group">
										<label class="control-label">分級制度:</label> <select
											class="form-control" name="rating">
											<option value="普遍級" ${movieVO.rating.equals('普遍級')?'selected':'' }>普遍級
											<option value="保護級" ${movieVO.rating.equals('保護級')?'selected':'' }>保護級
											<option value="輔12級" ${movieVO.rating.equals('輔12級')?'selected':'' }>輔12級
											<option value="輔15級" ${movieVO.rating.equals('輔15級')?'selected':'' }>輔15級
											<option value="限制級" ${movieVO.rating.equals('限制級')?'selected':'' }>限制級
										</select>
									</div>
									<div class="form-group hidden-md hidden-sm has-feedback">
										<label class="control-label">預告片:</label> <input
											class="form-control" type="text" name="trailer_url" value="<%=movieVO.getTrailer_url()%>">
									</div>
									<div class="form-group">
										<label class="control-label">簡介:</label>
										<textarea class="form-control" name="brief_intro" rows="6"><%=movieVO.getBrief_intro()%></textarea>
									</div>
									
									<c:choose>
							         <c:when test="<%=movieVO.getActive() == 0%>">
							          <label><input name="active" type="radio" value="0"
							           checked="checked">下線</label>
							          <label><input name="active" type="radio" value="1">上線</label>
							         </c:when>
							         <c:otherwise>
							          <label><input name="active" type="radio" value="0">下線</label>
							          <label><input name="active" type="radio" value="1"
							           checked="checked">上線</label>
							         </c:otherwise>
							        </c:choose>
									
									<div class="form-group">
										<label class="control-label">導演:</label> <input
											class="form-control" type="text" name="director" value="<%=movieVO.getDirector()%>">
									</div>
									<div class="form-group">
										<label class="control-label">主演:</label> <input
											class="form-control" type="text" name="starring" value="<%=movieVO.getStarring()%>">
									</div>

									
								<input type="hidden" name="action" value="update">
								<input type="hidden" name="movie_no"  value="<%=movieVO.getMovie_no()%>">
								<input type="hidden" name="active" value="<%=movieVO.getActive()%>">
								<input type="hidden" name="brief_intro">
								
								
								<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
								<input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--只用於:movie_List.jsp-->
								<input type="submit" value="送出修改">
									
								</form>


							</div>
						</div>
					</div>
				</div>



				<!-- movie_Update -->
		</div>
	</div>
	<script src="<%=request.getContextPath() + "/js/back_index.js"%>"></script>
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
  		   value: '<%=movieVO.getRelased()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>