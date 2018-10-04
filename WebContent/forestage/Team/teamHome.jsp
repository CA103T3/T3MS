<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<!doctype html>
<html>
<head>



<style>
.fo {
	margin-top: 20px;
}

.card {
	margin-top: 1%;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
	max-width: 300px;
	margin: auto;
	text-align: center;
	font-family: arial;
	background-color: lightgreen;
	color: white;
	margin-top: 20px;
}

.card button {
	border: none;
	outline: 0;
	padding: 12px;
	color: white;
	background-color: #777;
	text-align: center;
	cursor: pointer;
	width: 100%;
	font-size: 18px;
}

.card button:hover {
	opacity: 0.7;
}

body {
	font-family: "Lato", sans-serif;
	font-family: Arial, Helvetica, sans-serif;
	background-color: lightyellow !important;
}



.image-container {
	background-image: url("/T3MS/img/chu.jpg");
	background-size: cover;
	position: relative;
	height: 300px;
}

.text {
	background-color: white;
	color: black;
	font-size: 7vw;
	font-weight: bold;
	margin: 0 auto;
	padding: 10px;
	width: 30%;
	text-align: center;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	mix-blend-mode: screen;
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
	<%@ include file="/forestage/template/header_no_bar.jsp"%>














	<div class="image-container">
		<div class="text">都 不 揪</div>
	</div>












	<div class="py-4 bg-light">
		<div class="container">
			<div class="row">



				<div class="col-md-3">




					<div class="fo">






						<form class="filter-list" style="">
							<div class="form-group">
								<label for="formControlsSelect1" class="control-label"> <span>日期</span>
								</label> <br>


								<!--要選取日期的元素-->
								<input class="default form-control" type="text" size="auto" />
								<!--要選取日期的元素-->

								<!--執行 Date Range Picker-->
								<script>
									$('input.default')
											.daterangepicker(
													{
														timePicker : true,
														startDate : moment()
																.startOf('hour'),
														endDate : moment()
																.startOf('hour')
																.add(32, 'hour'),
														locale : {
															format : 'M/DD hh:mm A'
														}
													});
								</script>
								<!--執行 Date Range Picker-->



							</div>

							<div class="form-group">
								<label class="control-label"><span>城市</span></label> <select placeholder="select" id="countryId" class="form-control">
									<option value=""><span>隨便</span></option>
									<option value="1">臺北</option>
									<option value="2">新北</option>
									<option value="3">基隆</option>
									<option value="4">桃園</option>
									<option value="5">新竹</option>
									<option value="6">苗栗</option>
									<option value="7">臺中</option>
									<option value="8">彰化</option>
									<option value="9">南投</option>
									<option value="10">雲林</option>
									<option value="11">嘉義</option>
									<option value="12">臺南</option>
									<option value="13">高雄</option>
									<option value="14">屏東</option>
									<option value="15">宜蘭</option>
									<option value="16">花蓮</option>
									<option value="17">臺東</option>
									<option value="18">澎湖</option>
									<option value="19">金門</option>
									<option value="20">連江</option>
								</select>
							</div>

							<div class="form-group">
								<label class="control-label"><span>所在地</span></label><select placeholder="select" id="cityId" class="form-control">
									<option value=""><span>隨便</span></option>
									<option value="1">臺北</option>
									<option value="2">新北</option>
									<option value="3">基隆</option>
									<option value="4">桃園</option>
									<option value="5">新竹</option>
									<option value="6">苗栗</option>
									<option value="7">臺中</option>
									<option value="8">彰化</option>
									<option value="9">南投</option>
									<option value="10">雲林</option>
									<option value="11">嘉義</option>
									<option value="12">臺南</option>
									<option value="13">高雄</option>
									<option value="14">屏東</option>
									<option value="15">宜蘭</option>
									<option value="16">花蓮</option>
									<option value="17">臺東</option>
									<option value="18">澎湖</option>
									<option value="19">金門</option>
									<option value="20">連江</option>
								</select>
							</div>
							<div class="form-group">

								<label class="control-label"> <span>電影</span>
								</label> <select placeholder="select" id="tagId" class="form-control">
									<option value=""><span>隨便</span></option>
									<option value="1">輕鬆聊</option>
									<option value="2">浪漫約會</option>
									<option value="3">商務飯局</option>
									<option value="4">馬上出發</option>
									<option value="5">人生夢想</option>
									<option value="6">創業</option>
									<option value="7">感情</option>
									<option value="8">進修</option>
									<option value="9">哲學思辨</option>
									<option value="10">寵物</option>
									<option value="11">科技</option>
									<option value="12">冒險</option>
									<option value="13">語言</option>
									<option value="14">行銷</option>
									<option value="15">星座</option>
									<option value="16">戶外活動</option>
									<option value="17">旅行</option>
									<option value="18">電影電視</option>
									<option value="19">音樂</option>
									<option value="20">攝影</option>
									<option value="21">藝術設計</option>
									<option value="22">戲劇舞蹈</option>
									<option value="23">文學閱讀</option>
									<option value="24">球類運動</option>
									<option value="25">運動健身</option>
									<option value="26">流行時尚</option>
									<option value="27">美食</option>
									<option value="28">品酒小酌</option>
									<option value="29">烹飪</option>
									<option value="30">電玩遊戲</option>
									<option value="31">玩具</option>
									<option value="32">車輛機械</option>
									<option value="33">程式設計</option>
									<option value="34">園藝植栽</option>
									<option value="35">理財金融</option>
									<option value="36">政治時事</option>
									<option value="37">娛樂八卦</option>
								</select>
							</div>
					</div>



					<hr>

					<div class="form-group">
						<label class="control-label"> <span>關鍵字搜尋</span>
						</label> <input type="text" placeholder="搜尋聚會名稱、好友暱稱" id="keywords" class="form-control">
					</div>

					<div class="form-group">
						<button type="button" class="btn-yellow btn btn-default btn-block">
							<span class="middle icon icon-search"></span> <span class="middle"><span>搜尋</span></span>
						</button>
						<button type="button" class="btn-grey btn btn-default btn-block">
							<span>重置</span>
						</button>
					</div>

					</form>



















				</div>

				<div class="col-md-9">
					<div class="row">


						<div class="col-md-4 p-3">
							<div class="card">
								<img src="https://pingendo.com/assets/photos/wireframe/photo-1.jpg" style="width: 100%">
								<h1>name</h1>
								<p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
								<p>
									<button>join</button>
								</p>
								<small class="text-muted">9 mins</small>
							</div>
						</div>
						<div class="col-md-4 p-3">
							<div class="card">
								<img src="https://pingendo.com/assets/photos/wireframe/photo-1.jpg" style="width: 100%">
								<h1>《電影》標題</h1>
								<p class="card-text">目前人數：</p>
								<p class="card-text">集合地點：ＸＸＸＸＸＸＸＸＸＸ</p>
								<p class="card-text">集合時間：ＸＸＸＸＸＸＸＸＸＸ</p>
								
								
								<small class="text-muted">截止時間</small>
							</div>
						</div>

						<div class="col-md-4 p-3">
							<div class="card">
								<img src="https://pingendo.com/assets/photos/wireframe/photo-1.jpg" style="width: 100%">
								<h1>name</h1>
								<p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
								<p>
									<button>join</button>
								</p>
								<small class="text-muted">9 mins</small>
							</div>
						</div>

						<div class="col-md-4 p-3">
							<div class="card">
								<img src="https://pingendo.com/assets/photos/wireframe/photo-1.jpg" style="width: 100%">
								<h1>name</h1>
								<p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
								<p>
									<button>join</button>
								</p>
								<small class="text-muted">9 mins</small>
							</div>
						</div>

						<div class="col-md-4 p-3">
							<div class="card">
								<img src="https://pingendo.com/assets/photos/wireframe/photo-1.jpg" style="width: 100%">
								<h1>name</h1>
								<p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
								<p>
									<button>join</button>
								</p>
								<small class="text-muted">9 mins</small>
							</div>
						</div>

						<div class="col-md-4 p-3">
							<div class="card">
								<img src="https://pingendo.com/assets/photos/wireframe/photo-1.jpg" style="width: 100%">
								<h1>name</h1>
								<p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
								<p>
									<button>join</button>
								</p>
								<small class="text-muted">9 mins</small>
							</div>
						</div>

						<div class="col-md-4 p-3">
							<div class="card">
								<img src="https://pingendo.com/assets/photos/wireframe/photo-1.jpg" style="width: 100%">
								<h1>name</h1>
								<p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
								<p>
									<button>join</button>
								</p>
								<small class="text-muted">9 mins</small>
							</div>
						</div>

						<div class="col-md-4 p-3">
							<div class="card">
								<img src="https://pingendo.com/assets/photos/wireframe/photo-1.jpg" style="width: 100%">
								<h1>name</h1>
								<p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
								<p>
									<button>join</button>
								</p>
								<small class="text-muted">9 mins</small>
							</div>
						</div>

						<div class="col-md-4 p-3">
							<div class="card">
								<img src="https://pingendo.com/assets/photos/wireframe/photo-1.jpg" style="width: 100%">
								<h1>name</h1>
								<p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
								<p>
									<button>join</button>
								</p>
								<small class="text-muted">9 mins</small>
							</div>
						</div>



					</div>
				</div>
			</div>
		</div>
	</div>



	<div class="p-0">
		<div class="container text-center">
			<div class="row">
				<div class="col-md-12">
					<div class=" ">
						<ul class="pagination">
							<li class="page-item"><a class="page-link" href="#"> <span>«</span> <span class="sr-only">Previous</span>
							</a></li>
							<li class="page-item"><a class="page-link" href="#">1</a></li>
							<li class="page-item"><a class="page-link" href="#">2</a></li>
							<li class="page-item"><a class="page-link" href="#">3</a></li>
							<li class="page-item"><a class="page-link" href="#">4</a></li>
							<li class="page-item"><a class="page-link" href="#"> <span>»</span> <span class="sr-only">Next</span>
							</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>




	<footer>
		<div class="container ">
			<p class="text-right">
				<a href="#">Back to top</a>
			</p>
			<p class="text-center">
				Album template is based on Bootstrap © examples.&nbsp; <br>New to Bootstrap? <a href="#">Visit the homepage</a> or read our <a href="#">getting started guide</a>.
			</p>
		</div>



	</footer>










	<%@ include file="/forestage/template/footer.jsp"%>

	<script src="/T3MS/js/template.js"></script>
	<script>
		$(document).ready(function() {
			$("li:contains('合作影城')").addClass("custom-active");
		});
	</script>
</body>
</html>