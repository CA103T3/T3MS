<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cinema.model.*"%>
<%@ page import="java.util.*"%>


<%					
	CinemaService cinemaservice = new CinemaService();
	List<CinemaVO> list = cinemaservice.getAll();
	pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html lang="en" >

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">

  <link href='https://fonts.googleapis.com/css?family=Jaldi|Roboto+Condensed' rel='stylesheet' type='text/css'>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
  <link rel='stylesheet' href='http://cdnjs.cloudflare.com/ajax/libs/animate.css/3.2.3/animate.min.css'>
  <link rel='stylesheet' href='https://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css'>
  <link rel="stylesheet" href="/T3MS/css/style.css">
<!--   /T3MS/img/bg-01.jpg -->

	<%@ include file="/forestage/template/link.jsp" %>
  <title>Cooperation Studio Introduction</title>
<style>
      /* NOTE: The styles were added inline because Prefixfree needs access to your styles and they must be inlined if they are on local disk! */
.container {
  max-width: 1100px;
  margin: 0 auto;
}

.cards {
  display: -webkit-flex;
  display: flex;
  -webkit-justify-content: center;
  justify-content: center;
  -webkit-flex-wrap: wrap;
  flex-wrap: wrap;
  margin-top: 15px;
  padding: 1.5%;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}

.card {
  position: relative;
  margin-bottom: 20px;
  padding-bottom: 30px;
  background: #fefff9;
  color: #363636;
  text-decoration: none;
  -moz-box-shadow: rgba(0, 0, 0, 0.19) 0 0 8px 0;
  -webkit-box-shadow: rgba(0, 0, 0, 0.19) 0 0 8px 0;
  box-shadow: rgba(0, 0, 0, 0.19) 0 0 8px 0;
  -moz-border-radius: 4px;
  -webkit-border-radius: 4px;
  border-radius: 4px;
}
@media (max-width: 700px) {
  .card {
    width: 100%;
  }
}
@media (min-width: 700px) {
  .card {
    max-width: 320px;
    margin-right: 20px;
    margin-bottom: 20px;
  }
  .card:nth-child(even) {
    margin-right: 0;
  }"WebContent/css/addCinema.css"
}
@media (min-width: 980px) {
  .card:nth-child(even) {
    margin-right: 20px;
  }
  .card:nth-child(3n) {
    margin-right: 0;
  }
}
.card span {
  display: block;
}
.card .card-summary {
  padding: 5% 5% 3% 5%;
}
.card .card-header {
  position: relative;
  height: 175px;
  overflow: hidden;
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
  background-color: rgba(255, 255, 255, 0.15);
  background-blend-mode: overlay;
  -moz-border-radius: 4px 4px 0 0;
  -webkit-border-radius: 4px;
  border-radius: 4px 4px 0 0;
}
.card .card-header:hover, .card .card-header:focus {
  background-color: rgba(255, 255, 255, 0);
}
.card .card-title {
  background: rgba(157, 187, 63, 0.85);
  padding: 3.5% 0 2.5% 0;
  color: white;
  font-family: 'Roboto Condensed', sans-serif;
  text-transform: uppercase;
  position: absolute;
  bottom: 0;
  width: 100%;
}
.card .card-title h3 {
  font-size: 1.2em;
  line-height: 1.2;
  padding: 0 3.5%;
  margin: 0;
}
.card .card-meta {
  max-height: 0;
  overflow: hidden;
  color: #666;
  font-size: .78em;
  text-transform: uppercase;
  position: absolute;
  bottom: 5%;
  padding: 0 5%;
  -moz-transition-property: max-height;
  -o-transition-property: max-height;
  -webkit-transition-property: max-height;
  transition-property: max-height;
  -moz-transition-duration: 0.4s;
  -o-transition-duration: 0.4s;
  -webkit-transition-duration: 0.4s;
  transition-duration: 0.4s;
  -moz-transition-timing-function: ease-in-out;
  -o-transition-timing-function: ease-in-out;
  -webkit-transition-timing-function: ease-in-out;
  transition-timing-function: ease-in-out;
}
.card:hover, .card:focus {
  background: white;
  -moz-box-shadow: rgba(0, 0, 0, 0.45) 0px 0px 20px 0px;
  -webkit-box-shadow: rgba(0, 0, 0, 0.45) 0px 0px 20px 0px;
  box-shadow: rgba(0, 0, 0, 0.45) 0px 0px 20px 0px;
}
.card:hover .card-title, .card:focus .card-title {
  background: rgba(157, 187, 63, 0.95);
}
.card:hover .card-meta, .card:focus .card-meta {
  max-height: 1em;
}

img {
  max-width: 100%;
}

body {
  background: #f0f0f0;
  font-size: 17px;
  line-height: 1.4;
  font-family: 'Jaldi', sans-serif;
}

* {
  -moz-transition-property: background-color, border-color, box-shadow, color, opacity, text-shadow, -moz-transform;
  -o-transition-property: background-color, border-color, box-shadow, color, opacity, text-shadow, -o-transform;
  -webkit-transition-property: background-color, border-color, box-shadow, color, opacity, text-shadow, -webkit-transform;
  transition-property: background-color, border-color, box-shadow, color, opacity, text-shadow, transform;
  -moz-transition-duration: 0.2s;
  -o-transition-duration: 0.2s;
  -webkit-transition-duration: 0.2s;
  transition-duration: 0.2s;
  -moz-transition-timing-function: linear;
  -o-transition-timing-function: linear;
  -webkit-transition-timing-function: linear;
  transition-timing-function: linear;
}

    </style>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>

</head>

<body class="body-template">
	<%@ include file="/forestage/template/header.jsp" %>
 <!-- Preloader with Bootstrap Progress Bar -->
<div class='loader'>
  <div class='loader-container'>
    <h3><b>合作影城介紹</b></h3>
    <div class='progress progress-striped active'>
      <div class='progress-bar progress-bar-color' id='bar' role='progressbar' style='width: 0%;'></div>
    </div>
  </div>
</div>
<!-- Landing Page -->
<div class="container-full">

      <div class="row">
       
        <div class="col-lg-12 text-center v-center">
          <br><br>
          <h1>歡迎</h1>
          <!-- <p>You just see a progress bar</p> -->
          <h1>洽談合作方案</h1>         
          <br><br><br>
          <br><br><br>
        </div>
        
      </div> <!-- /row -->
  <div>
  <!-- <img src="http://p1.pichost.me/i/9/1320769.jpg"/> -->
  </div>
        </div>
  
    <br><br><br><br><br>

</div>

 <div class="container">
 	
        <div class="col-12">
          <h1 class="">合作影城介紹</h1>
        </div>
      
 </div>
  <div class="container">
	<div class="cards">
<!-- 		<a class="card" href="#"> -->
		<a class="card" href="https://www.vscinemas.com.tw/theater/detail.aspx?id=1" >
			<span class="card-header" style="background-image: url(/T3MS/<%= list.get(0).getPhoto_path()%>);">
				<span class="card-title">
					<h3><%= list.get(0).getCinema_name()%></h3>
<%-- 					<h3>${list.get(0).cinema_name}</h3> --%>
				</span>
			</span>
			<span class="card-summary">
				<%= list.get(0).getIntroduction()%>
			</span>	
			<span class="card-meta">
				<%= list.get(0).getCinema_tel()%>
			</span>
		
		</a>

		<a class="card" href="http://www.skcinemas.com/Theater.aspx?TID=9011">
			<span class="card-header" style="background-image: url(/T3MS/<%= list.get(1).getPhoto_path()%>);">
				<span class="card-title">
					<h3><%= list.get(1).getCinema_name()%></h3>
				</span>
			</span>
			<span class="card-summary">
				<%= list.get(1).getIntroduction()%>
			</span>
			<span class="card-meta">
				<%= list.get(1).getCinema_tel()%>
			</span>
		</a>
		
		<a class="card" href="https://www.in89.com.tw/show.php?id=10">
			<span class="card-header" style="background-image: url(/T3MS/<%= list.get(2).getPhoto_path()%>)">
				<span class="card-title">
					<h3><%= list.get(2).getCinema_name()%></h3>
				</span>
			</span>
			<span class="card-summary">
				<%= list.get(2).getIntroduction()%>
			</span>
			<span class="card-meta">
				<%= list.get(2).getCinema_tel()%>
			</span>
		</a>

		<a class="card" href="http://wonderful.movie.com.tw/">
			<span class="card-header" style="background-image: url(/T3MS/<%= list.get(3).getPhoto_path()%>);">
				<span class="card-title">
					<h3><%= list.get(3).getCinema_name()%></h3>
				</span>
			</span>
			<span class="card-summary">
				<%= list.get(3).getIntroduction()%>
			</span>
			<span class="card-meta">
				<%= list.get(3).getCinema_tel()%>
			</span>
		</a>
		
		<a class="card" href="http://ptcinema.movie.com.tw/time">
			<span class="card-header" style="background-image: url(/T3MS/<%= list.get(4).getPhoto_path()%>);">
				<span class="card-title">
					<h3><%= list.get(4).getCinema_name()%></h3>
				</span>
			</span>
			<span class="card-summary">
				<%= list.get(4).getIntroduction()%>
			</span>
			<span class="card-meta">
				<%= list.get(4).getCinema_tel()%>
			</span>
		</a>
		
		<a class="card" href="http://chungli2.iiiedu.org.tw/chungli/">
			<span class="card-header" style="background-image: url(/T3MS/<%= list.get(5).getPhoto_path()%>);">
				<span class="card-title">
					<h3><%= list.get(5).getCinema_name()%></h3>
				</span>
			</span>
			<span class="card-summary">
				<%= list.get(5).getIntroduction()%>
			</span>
			<span class="card-meta">
				<%= list.get(5).getCinema_tel()%>
			</span>
		</a>

		<!-- <a class="card" href="#">
			<span class="card-header" style="background-image: url(http://placeimg.com/400/250/animals);">
				<span class="card-title">
					<h3>This is a title for a card</h3>
				</span>
			</span>
			<span class="card-summary">
				A summary will also be present. Usually two to three brief sentences about the content on the detail page.
			</span>
			<span class="card-meta">
				Published: June 18th, 2015
			</span>
		</a>

		<a class="card" href="#">
			<span class="card-header" style="background-image: url(http://placeimg.com/600/600/people);">
				<span class="card-title">
					<h3>This is a title for a card</h3>
				</span>
			</span>
			<span class="card-summary">
				Each card is created from an &lt;a&gt; tag so the whole card is linked.
			</span>
			<span class="card-meta">
				Published: June 18th, 2015
			</span>
		</a>

		<a class="card" href="#">
			<span class="card-header" style="background-image: url(http://placeimg.com/400/400/tech);">
				<span class="card-title">
					<h3>This is a title for a card</h3>
				</span>
			</span>
			<span class="card-summary">
				Using Flexbox is such a an easy, well supported way for grid-style content, such as cards. The cards height will expand to match the longest item.
			</span>
			<span class="card-meta">
				Published: June 18th, 2015
			</span>
		</a> -->
	</div>
</div>
  
 <%@ include file="/forestage/template/footer.jsp" %>
  	<script src="<%=request.getContextPath()%>/js/template.js"></script>
	<script>
        $(document).ready(function(){
            $("li:contains('合作影城')").addClass("custom-active");
        });
        </script>

  	<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
  
    <script  src="/T3MS/js/index_1.js"></script>
  

</body>

</html>
