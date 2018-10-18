<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.movie.model.*"%>
<%@ page import="com.movie_introduce.model.*"%>
<%@ page import="com.filmreview.model.*"%>
<%@ page import="com.boxoffice.model.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.announcement.model.*"%>
<%
    MovieService mSvc = new MovieService();
    List<MovieVO> mList = mSvc.getNow();
    pageContext.setAttribute("mList",mList);
    Movie_IntroduceService introduceSvc = new Movie_IntroduceService();
    List<Movie_IntroduceVO> introList = introduceSvc.getAll();
    pageContext.setAttribute("introList",introList);
    FilmreviewDAO fvSvc = new FilmreviewDAO();
    List<FilmreviewVO> frList = fvSvc.getAll();
    pageContext.setAttribute("frList", frList);

    BoxOfficeService bSvc = new BoxOfficeService();
    Integer loc_tw = 0;
    Integer loc_us = 1;
    List<BoxOfficeVO> list_tw = bSvc.getLatestTenByLoc(loc_tw);
    pageContext.setAttribute("list_tw", list_tw);
    java.sql.Date stat_tw = list_tw.get(0).getStatistics();
    pageContext.setAttribute("stat_tw", stat_tw);

    List<BoxOfficeVO> list_us = bSvc.getLatestTenByLoc(loc_us);
    pageContext.setAttribute("list_us", list_us);
    java.sql.Date stat_us = list_us.get(0).getStatistics();
    pageContext.setAttribute("stat_us", stat_us);

    ActivityService actSvc = new ActivityService();
    List<ActivityVO> actList = actSvc.getAll();
    pageContext.setAttribute("actList", actList);

    AnnouncementService annSvc = new AnnouncementService();
    List<AnnouncementVO> annList = annSvc.getAll();
    pageContext.setAttribute("annList", annList);
%>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <%@ include file="/forestage/template/link.jsp" %>
        <link rel="stylesheet" href="/T3MS/css/scroller.css">
        <title>M&amp;S</title>
        <style>

        </style>

    </head>
    <body class="body-custom">
        <div id="lightBox">
            <div id="announce" class="announce-div">
                <div class="pos-relative ">
                    <span class="close-item">
                    <i class="fa fa-times-circle fa-2x" aria-hidden="true"></i>
                    </span>
                    <p class="announcement t-custom">公告!</p>
                    <c:forEach var="annVO" items="${annList}" varStatus="s" begin="<%=0%>" end="<%=annList.size()-1%>">
                        <p class="announcement anc-con">${annVO.anc_con}</p><br />
                    </c:forEach>
                </div>
            </div>
        </div>

        <%@ include file="/forestage/template/header.jsp" %>

        <!-- scroller -->
        <div class="container-fluid slide">
            <div id="scroller" class="scroller-custom"> <!-- margin 0 auto 置中 -->
                <div class="innerScrollArea">
                    <ul>
                        <c:forEach var="movieVO" items="${mList}" varStatus="s" begin="<%=0%>" end="<%=mList.size()-1%>">
                            <li style="left: 0px;" ><a href="<%=request.getContextPath()%>/forestage/movie_moment/moment_One2.jsp?${movieVO.movie_no}"><img src="<%=request.getContextPath() %>/DBGifReader?movie_no=${movieVO.movie_no}" width="180" height="260"  alt="${movieVO.movie_name}" title="${movieVO.movie_name}"/></a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <!-- scroller -->

        <div class="container-fluid">
            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <c:forEach var="actVO" items="${actList}" varStatus="s" begin="<%=0%>" end="<%=actList.size()-1%>">
                        <li data-target="#myCarousel" data-slide-to="${s.index}" class='${(s.index==0) ? "active" : "" }'></li>
<!--                         <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li> -->
                    </c:forEach>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner">
                    <c:forEach var="actVO" items="${actList}" varStatus="s" begin="<%=0%>" end="<%=actList.size()-1%>">
                        <div class='item ${(s.index==0) ? "active" : "" }'>
                            <a href="${actVO.activity_url}" target="_blank">
                                <img class="img-custom" src="<%=request.getContextPath() %>/DBGifReaderAct?activity_no=${actVO.activity_no}" alt="${actVO.activity_name}">
                                <div class="carousel-caption">
                                  <h3>${actVO.activity_name}</h3>
                                  <p class="font-custom act-desc">${actVO.activity_desc}</p>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
<!--                     <div class="item">
                        <img class="img-custom" src="/T3MS/img/homepage_20180801002.jpg" alt="《全台七夕活動》">
                        <div class="carousel-caption">
                          <h3>《全台七夕活動》</h3>
                          <p class="font-custom">全台七夕活動 ❤❤萌月老現身 幸福來敲門❤❤ 大獎帶回家</p>
                        </div>
                    </div>

                    <div class="item">
                        <img class="img-custom" src="/T3MS/img/ny.jpg" alt="New york">
                        <div class="carousel-caption">
                          <h3>New York</h3>
                          <p class="font-custom">We love the Big Apple!</p>
                        </div>
                    </div> -->
                </div>

                <!-- Left and right controls -->

                <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                    <span class="sr-only">Next</span>
                </a>

            </div>
        </div>
        <%@ include file="/forestage/template/search_bar.jsp" %>
        <!-- <div class="container-fluid" > -->
        <div class="container slideanim" >
            <div class="col-md-8 ">
                <!-- horizontal line with words -->
                <div class="col-md-4 margin-top-ten">
                    <hr class="horizontal-line">
                </div>
                <div class="col-md-4 ">
                    <h3 class="title-custom"><span>電影情報</span></h3>
                </div>
                <div class="col-md-4 margin-top-ten">
                    <hr class="horizontal-line">
                </div>

                <c:forEach var="movie_introduceVO" items="${introList}" varStatus="s" begin="<%=0%>" end="<%=4%>">
                <article>
                    <div class="col-md-12 text-center">
                        <header><h3 class="hover-custom"><a class="t-custom" href="<%=request.getContextPath()%>/forestage/movie_introduce/One_introduce.jsp?${movie_introduceVO.introd_no}" >${movie_introduceVO.title}</a></h3></header>
                    </div>
                    <div class="row">
                        <div class="col-md-4 ">
                            <a href="<%=request.getContextPath()%>/forestage/movie_introduce/One_introduce.jsp?${movie_introduceVO.introd_no}"><img class="img-custom-small" src="${movie_introduceVO.photo_small}" alt="${movie_introduceVO.title}"></a>
                        </div>
                        <div class="col-md-8 p-custom intro-content">
                            <p class="">${movie_introduceVO.content}</p>
                        </div>
                        <c:if test="${s.index == 4}" >
                            <div class="text-center col-md-12"><a href="<%=request.getContextPath()%>/forestage/movie_introduce/List_introduce.jsp"><button type="button" class="btn btn-success btn-lg">更多情報</button></a></div>
                        </c:if>
                    </div>
                </article>
                </c:forEach>

                <!-- horizontal line with words -->
                <div class="row" style="margin-top: 30px">
                    <div class="col-md-4 margin-top-ten">
                        <hr class="horizontal-line">
                    </div>
                    <div class="col-md-4 ">
                        <h3 class="title-custom"><span>影評文章</span></h3>
                    </div>
                    <div class="col-md-4 margin-top-ten">
                        <hr class="horizontal-line">
                    </div>
                </div>

                <c:forEach var="filmreviewVO" items="${frList}" varStatus="s" begin="<%=0%>" end="<%=4%>">
                <article>
                    <div class="col-md-12 text-center">
                        <header><h3 class="hover-custom"><a class="t-custom" href="<%=request.getContextPath()%>/forestage/filmreview/fv.jsp?fr_no=${filmreviewVO.fr_no}">${filmreviewVO.title}</a></h3></header>
                    </div>
                    <div class="row">
                        <div class="col-md-4 ">
                            <a href="<%=request.getContextPath()%>/forestage/filmreview/fv.jsp?fr_no=${filmreviewVO.fr_no}"><img class="img-custom-small" src="<%=request.getContextPath() %>/DBGifReader?movie_no=${filmreviewVO.movie_no}" alt="${filmreviewVO.title}"></a>
                        </div>
                        <div class="col-md-8 p-custom intro-content">
                            <p class="">${filmreviewVO.content}</p>
                        </div>
                        <c:if test="${s.index == 4}" >
                            <div class="text-center col-md-12"><a href="<%=request.getContextPath()%>/forestage/filmreview/fv_home.jsp"><button type="button" class="btn btn-success btn-lg">更多影評</button></a></div>
                        </c:if>
                    </div>
                </article>
                </c:forEach>

            </div>

            <!-- rank list -->
            <div class="col-md-4 movie_rank">
                <div class="panel panel-info panel-custom">
                    <div class="panel-heading text-center">全美週末票房排行榜<br>${stat_us}</div>
                    <div class="panel-body">
                        <div class="rank_list">
                            <ul class="hover-custom">
                            <c:forEach var="boVO" items="${list_us}" varStatus="s" begin="<%=0%>" end="<%=list_us.size()-1%>">
                                <li><a href='<c:if test="${!empty boVO.movie_no}"><%=request.getContextPath()%>/forestage/movie_moment/moment_One2.jsp?${boVO.movie_no}</c:if><c:if test="${empty boVO.movie_no}">#</c:if>'>${(empty boVO.movie_no) ? boVO.moviename : boVO.movieVO.movie_name}</a></li>
                            </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="panel panel-info panel-custom">
                    <div class="panel-heading text-center">台北週末票房排行榜<br>${stat_tw}</div>
                    <div class="panel-body">
                        <div class="rank_list">
                            <ul class="hover-custom">
                            <c:forEach var="boVO" items="${list_tw}" varStatus="s" begin="<%=0%>" end="<%=list_tw.size()-1%>">
                                <li><a href='<c:if test="${!empty boVO.movie_no}"><%=request.getContextPath()%>/forestage/movie_moment/moment_One2.jsp?${boVO.movie_no}</c:if><c:if test="${empty boVO.movie_no}">#</c:if>'>${(empty boVO.movie_no) ? boVO.moviename : boVO.movieVO.movie_name}</a></li>
                            </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- </div> -->

        <%@ include file="/forestage/template/footer.jsp" %>

        <script src="/T3MS/js/index.js"></script>
    </body>
</html>