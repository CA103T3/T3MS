<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<!-- <link href="<%=request.getContextPath()%>/css/movie_back_movie.css" rel="stylesheet" type="text/css"> -->
<style type="text/css">
textarea{
resize: none;
}
</style>
</head>

<body class="fs16" ata-spy="scroll">
	<%@ include file="/backstage/template/header.jsp"%>
	<div id="wrapper" class="mt50">
		<%@ include file="/backstage/template/sidebar.jsp"%>
		<div class="flex-column" id="page-content-wrapper">


<!--movie_Add -->

			<div class="section">
				<div class="container">
					<div class="row">
						<div class="col-md-10">

							<div class="page-header text-warning">
								<h1>
									電影新增
									<font color="#777777">
										<span style="font-size: 23.4px; line-height: 23.4px;"> Movie Add</span>
									</font>
								</h1>
							</div>

							<!-- 錯誤表列 -->

							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>



							<form METHOD="post" ACTION="<%=request.getContextPath()%>/movie/movie.do" name="form1" enctype="multipart/form-data">

								<div class="form-group">
									<label class="control-label">電影種類:</label>
									<select class="form-control" name="movie_type">
										<option value="動作片">動作片
										<option value="喜劇片">喜劇片
										<option value="恐怖片">恐怖片
										<option value="愛情片">愛情片
										<option value="動畫片">動畫片
										<option value="戰爭片">戰爭片
										<option value="情色片">情色片
										<option value="懸疑片">懸疑片
										<option value="科幻片">科幻片
									</select>
								</div>



								<div class="form-group">
									<label class="control-label">電影名稱:</label>
									<input type="TEXT" class="form-control" name="movie_name" size="45" value="<%=(movieVO == null) ? "請輸入電影名稱" : movieVO.getMovie_name()%>" />

								</div>

								<div class="form-group">
									<label class="control-label">英文名稱:</label>
									<input type="TEXT" class="form-control" name="eng_name" size="45" value="<%=(movieVO == null) ? "peter1" : movieVO.getEng_name()%>" />

								</div>

								<div class="form-group">
									<label class="control-label">電影圖片:</label>
									<input type="file" name="movie_pic" class="form-control" accept="image/*">
								</div>

								<div class="form-group hidden-md hidden-sm has-feedback">
									<label class="control-label">上映日期:</label>
									<input class="form-control"  name="relased" id="f_date1">
								</div>

								<div class="form-group hidden-md hidden-sm has-feedback">
									<label class="control-label">發行商:</label>
									<input type="TEXT" class="form-control" name="distributed" size="45" value="<%=(movieVO == null) ? "發行商" : movieVO.getDistributed()%>" />

								</div>

								<div class="form-group hidden-md hidden-sm has-feedback">
									<label class="control-label">片長:</label>
									<input type="TEXT" class="form-control" name="length" size="45" value="<%=(movieVO == null) ? "1" : movieVO.getLength()%>" />

								</div>

								<div class="form-group hidden-md hidden-sm has-feedback">
									<label class="control-label">語言:</label>
									<input type="TEXT" class="form-control" name="language" size="45" value="<%=(movieVO == null) ? "語言" : movieVO.getLanguage()%>" />

								</div>

								<div class="form-group hidden-md hidden-sm has-feedback">
									<label class="control-label">產地:</label>
									<input type="TEXT" class="form-control" name="madein" size="45" value="<%=(movieVO == null) ? "產地" : movieVO.getMadein()%>" />

								</div>

								<div class="form-group hidden-md hidden-sm has-feedback">
									<label class="control-label">IMDB評分:</label>
									<input type="TEXT" class="form-control" name="imdb" size="45" value="<%=(movieVO == null) ? "0.1" : movieVO.getImdb()%>" />
								</div>

								<div class="form-group hidden-md hidden-sm has-feedback">
									<label class="control-label">爛番茄評分:</label>
									<input type="TEXT" class="form-control" name="tomato" size="45" value="<%=(movieVO == null) ? "評分" : movieVO.getTomato()%>" />

								</div>

								<div class="form-group">
									<label class="control-label">分級制度:</label>
									<select class="form-control" name="rating">
										<option value="普遍級">普遍級
										<option value="保護級">保護級
										<option value="輔12級">輔12級
										<option value="輔15級">輔15級
										<option value="限制級">限制級
									</select>
								</div>

								<div class="form-group hidden-md hidden-sm has-feedback">
									<label class="control-label">預告片:</label>
									<input type="TEXT" class="form-control" name="trailer_url" size="45" value="<%=(movieVO == null) ? "請輸入預告片網址" : movieVO.getTrailer_url()%>" />
								</div>

								<div class="form-group">
									<label class="control-label">簡介:</label>
									<textarea class="form-control" name="brief_intro" rows="22"><%=(movieVO == null) ? "請輸入內容" : movieVO.getBrief_intro()%></textarea>
								</div>

								<div>
									<input type="radio" name="active" value="0" checked="checked">
									<label for="contactChoice1">下線</label>
									<input type="radio" name="active" value="1">
									<label for="contactChoice2">上線</label>
								</div>

								<div class="form-group">
									<label class="control-label">導演:</label>
									<input type="TEXT" class="form-control" name="director" size="45" value="<%=(movieVO == null) ? "導演" : movieVO.getDirector()%>" />

								</div>

								<div class="form-group">
									<label class="control-label">主演:</label>
									<input type="TEXT" class="form-control" name="starring" size="45" value="<%=(movieVO == null) ? "演員" : movieVO.getStarring()%>" />
								</div>

								<input type="hidden" name="action" value="insert">
								<input type="hidden" name="brief_intro">
								<input type="submit" value="送出新增">

							</form>

						</div>
					</div>
				</div>
			</div>

<!-- movie_Add -->

		</div>
	</div>
	<script src="<%=request.getContextPath() + "/js/back_index.js"%>"></script>
</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Date relased = null;
	try {
		relased = movieVO.getRelased();
	} catch (Exception e) {	
		relased = new java.sql.Date(System.currentTimeMillis());
	}
	
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=relased%>',
	    // value:   new Date(),
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
	     // var somedate2 = new Date('2018-10-12');
	     // $('#f_date1').datetimepicker({
	         // beforeShowDay: function(date) {
	       	  // if (  date.getYear() >  somedate2.getYear() || 
			           // (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
			           // (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	             // ) {
	                  // return [false, ""]
	             // }
	             // return [true, ""];
	     // }});

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