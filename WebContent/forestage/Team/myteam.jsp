<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<!doctype html>
<html>
<head>



<style>
body {
	
	background-color: lightyellow !important;
}
</style>


<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->


<%@ include file="/forestage/template/link.jsp"%>


<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />






</head>







<body class="body-template">
	<%@ include file="/forestage/template/header.jsp"%>











<div class="section">
      <div class="container">
      <div class="row">
      <div class="col-md-1">
      </div>
      <div class="col-md-11">
        <div class="row">
          <div class="col-md-12">
            <h1 class="text-left">我的揪團</h1>
          </div>
        </div>
       
          <div class="container">
            <div class="row">
              <div class="col-md-2">
                <img src="https://unsplash.imgix.net/photo-1421986527537-888d998adb74?w=1024&amp;q=50&amp;fm=jpg&amp;s=e633562a1da53293c4dc391fd41ce41d"
                class="img-responsive img-thumbnail" style="margin-top: 10px;">
              </div>
              <div class="col-md-10 text-center">
                <h4 class="text-left">《電影》標題</h4>
                <div class="col-md-5 text-right">
                  <p class="text-left">目前人數：</p>
                  <p class="text-left">集合地點：ＸＸＸＸＸＸＸＸＸＸ</p>
                </div>
                <div class="col-md-7 text-left">
                  <p class="text-danger">截止時間：ＸＸＸＸＸＸＸＸＸＸ</p>
                  <p>集合時間：ＸＸＸＸＸＸＸＸＸＸ</p>
                </div>
                <div class="col-md-12 text-left">
                  <a>詳細內容：</a>
                </div>
              </div>
            </div>
          </div><hr><div class="container">
            <div class="row">
              <div class="col-md-2">
                <img src="https://unsplash.imgix.net/photo-1421986527537-888d998adb74?w=1024&amp;q=50&amp;fm=jpg&amp;s=e633562a1da53293c4dc391fd41ce41d"
                class="img-responsive img-thumbnail" style="margin-top: 10px;">
              </div>
              <div class="col-md-10 text-center">
                <h4 class="text-left">《電影》標題</h4>
                <div class="col-md-5 text-right">
                  <p class="text-left">目前人數：</p>
                  <p class="text-left">集合地點：ＸＸＸＸＸＸＸＸＸＸ</p>
                </div>
                <div class="col-md-7 text-left">
                  <p class="text-danger">截止時間：ＸＸＸＸＸＸＸＸＸＸ</p>
                  <p>集合時間：ＸＸＸＸＸＸＸＸＸＸ</p>
                </div>
                <div class="col-md-12 text-left">
                  <p>詳細內容：</p>
                </div>
              </div>
            </div>

          </div><hr>
          <div class="container">
            <div class="row">
              <div class="col-md-2">
                <img src="https://unsplash.imgix.net/photo-1421986527537-888d998adb74?w=1024&amp;q=50&amp;fm=jpg&amp;s=e633562a1da53293c4dc391fd41ce41d"
                class="img-responsive img-thumbnail" style="margin-top: 10px;">
              </div>
              <div class="col-md-10 text-center">
                <h4 class="text-left">《電影》標題</h4>
                <div class="col-md-5 text-right">
                  <p class="text-left">目前人數：</p>
                  <p class="text-left">集合地點：ＸＸＸＸＸＸＸＸＸＸ</p>
                </div>
                <div class="col-md-7 text-left">
                  <p class="text-danger">截止時間：ＸＸＸＸＸＸＸＸＸＸ</p>
                  <p>集合時間：ＸＸＸＸＸＸＸＸＸＸ</p>
                </div>
                <div class="col-md-12 text-left">
                  <p>詳細內容：</p>
                </div>
              </div>
            </div>
          </div>
<hr>
        </div>
        </div>
        </div>
        </div>







	<%@ include file="/forestage/template/footer.jsp"%>

	<script src="/T3MS/js/template.js"></script>
	<script>
		$(document).ready(function() {
			$("li:contains('合作影城')").addClass("custom-active");
		});
	</script>
</body>
</html>