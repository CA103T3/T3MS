<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">

        <link rel="stylesheet" href="/T3MS/css/scroller.css">
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="/T3MS/css/font-awesome.min.css">
        <link rel="stylesheet" href="/T3MS/css/base.css">
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
                    <p>公告!</p>
                    <p>提醒您：7/11（三）全台影城正常營業，如有於颱風期間7/10（二）相關退票退款事宜請點選首頁颱風公告圖或至ez訂fb粉絲團了解詳情，謝謝。<br>國泰世華紅利訂票系統維護中，暫時無法訂票，造成您的不便敬請見諒，謝謝！<br>配合美麗華大直影城營運公告，6/22(五)起將暫停【美麗華大直】訂票服務，造成您的不便敬請見諒，謝謝！<br>【ez訂絕對不會叫您去操作ATM】近期常有詐騙集團假冒ez訂客服/會計人員/影城工作人員來電（號碼開頭有+或竄改來電號碼顯示），告知您訂單錯誤（設定為「多筆訂單」、「分期付款」等），再冒充金融機構人員要求您至自動櫃員機（ATM）操作以解除錯誤設定，這是詐騙，請小心勿受騙！如有訂單疑問，可撥打ez訂客服專線02-8912-6600，謝謝</p>
                </div>
            </div>
        </div>

        <%@ include file="/forestage/template/header.jsp" %>

        <!-- scroller -->
        <div class="container-fluid slide">
            <div id="scroller" class="scroller-custom"> <!-- margin 0 auto 置中 -->
                <div class="innerScrollArea">
                    <ul>
                        <li style="left: 0px;" ><a href="#"><img src="/T3MS/img/01.jpg" width="180" height="260"  alt="movie-alt" title="movie-title"/></a></li>
                        <li style="left: 180px;" ><a href="#"><img src="/T3MS/img/02.jpg" width="180" height="260"  alt="movie-alt" title="movie-title"/></a></li>
                        <li style="left: 360px;" ><a href="#"><img src="/T3MS/img/03.jpg" width="180" height="260"  alt="movie-alt" title="movie-title"/></a></li>
                        <li style="left: 540px;" ><a href="#"><img src="/T3MS/img/04.jpg" width="180" height="260"  alt="movie-alt" title="movie-title"/></a></li>
                        <li style="left: 720px;" ><a href="#"><img src="/T3MS/img/05.jpg" width="180" height="260"  alt="movie-alt" title="movie-title"/></a></li>
                        <li style="left: 900px;" ><a href="#"><img src="/T3MS/img/06.jpg" width="180" height="260"  alt="movie-alt" title="movie-title"/></a></li>
                        <li style="left: 1080px;" ><a href="#"><img src="/T3MS/img/07.jpg" width="180" height="260"  alt="movie-alt" title="movie-title"/></a></li>
                        <li style="left: 1260px;" ><a href="#"><img src="/T3MS/img/08.jpg" width="180" height="260"  alt="movie-alt" title="movie-title"/></a></li>
                        <li style="left: 1440px;" ><a href="#"><img src="/T3MS/img/09.jpg" width="180" height="260"  alt="movie-alt" title="movie-title"/></a></li>
                        <li style="left: 1620x;" ><a href="#"><img src="/T3MS/img/10.jpg" width="180" height="260"  alt="movie-alt" title="movie-title"/></a></li>
                        <li style="left: 1800px;" ><a href="#"><img src="/T3MS/img/11.jpg" width="180" height="260"  alt="movie-alt" title="movie-title"/></a></li>
                        <li style="left: 1980px;" ><a href="#"><img src="/T3MS/img/12.jpg" width="180" height="260"  alt="movie-alt" title="movie-title"/></a></li>
                        <li style="left: 2160px;" ><a href="#"><img src="/T3MS/img/13.jpg" width="180" height="260"  alt="movie-alt" title="movie-title"/></a></li>
                        <li style="left: 2340px;" ><a href="#"><img src="/T3MS/img/14.jpg" width="180" height="260"  alt="movie-alt" title="movie-title"/></a></li>
                        <li style="left: 2520px;" ><a href="#"><img src="/T3MS/img/15.jpg" width="180" height="260"  alt="movie-alt" title="movie-title"/></a></li>
                        <li style="left: 2700px;" ><a href="#"><img src="/T3MS/img/16.jpg" width="180" height="260"  alt="movie-alt" title="movie-title"/></a></li>
                        <li style="left: 2880px;" ><a href="#"><img src="/T3MS/img/17.jpg" width="180" height="260"  alt="movie-alt" title="movie-title"/></a></li>
                        <li style="left: 3060px;" ><a href="#"><img src="/T3MS/img/18.jpg" width="180" height="260"  alt="movie-alt" title="movie-title"/></a></li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- scroller -->

        <div class="container-fluid">
            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner">
                    <div class="item active">
                        <img class="img-custom" src="/T3MS/img/homepage_20180730001.jpg" alt="iShow儲值金消費多元平台">
                        <div class="carousel-caption">
                          <h3>iShow</h3>
                          <p class="font-custom">iShow儲值金消費多元平台</p>
                        </div>
                    </div>

                    <div class="item">
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
                    </div>
                </div>

                <!-- Left and right controls -->
                <!--
                <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                    <span class="sr-only">Next</span>
                </a>
                -->
            </div>
        </div>
        <!-- search bar > -->
        <div class="container-fluid slideanim">
            <div class="col-md-4 col-md-offset-4 search-bar-margin">
                <div class="input-group">
                    <input type="text" class="form-control input-lg text-center font-custom-large" name="search" placeholder="搜尋電影時刻" aria-label="搜尋電影時刻">
                    <span class="input-group-addon"><a href="#" title="搜尋電影時刻"><i class="fa fa-search fa-2x" aria-label="搜尋電影時刻"></i></a></span>
                </div>
            </div>
        </div>
        <!-- <div class="container-fluid" > -->
        <div class="container slideanim" >
            <div class="col-md-8 ">
                <!-- horizontal line with words -->
                <div class="col-md-4 margin-top-ten">
                    <hr class="horizontal-line">
                </div>
                <div class="col-md-4 ">
                    <h3 class="title-custom"><span>電影介紹</span></h3>
                </div>
                <div class="col-md-4 margin-top-ten">
                    <hr class="horizontal-line">
                </div>

                <article>
                    <div class="col-md-12 text-center">
                        <header><h3 class="hover-custom"><a href="#" >《與神同行：最終審判》揭開主角千年過往！ 感動更勝第一集</a></h3></header>
                    </div>
                    <div class="row">
                        <div class="col-md-4 ">
                            <a href="#"><img class="img-custom" src="/T3MS/img/withgod.jpg" alt=""></a>
                        </div>
                        <div class="col-md-8 ">
                            <p class="p-custom">《與神同行：最終審判》今日（7/6）上午在南韓首爾舉辦製作發表會，導演金榮華率領河正宇、朱智勛、金香起、馬東石、金東旭、李政宰等主要演員一同出席。</p>
                        </div>
                    </div>
                </article>

                <article>
                    <div class="col-md-12 text-center">
                        <header><h3 class="hover-custom"><a href="#">《與神同行：最終審判》揭開主角千年過往！ 感動更勝第一集</a></h3></header>
                    </div>
                    <div class="row">
                        <div class="col-md-4 ">
                            <a href="#"><img class="img-custom" src="/T3MS/img/withgod.jpg" alt=""></a>
                        </div>
                        <div class="col-md-8 ">
                            <p class="p-custom">《與神同行：最終審判》今日（7/6）上午在南韓首爾舉辦製作發表會，導演金榮華率領河正宇、朱智勛、金香起、馬東石、金東旭、李政宰等主要演員一同出席。</p>
                        </div>
                        <div class="text-center"><a href="#"><button type="button" class="btn btn-success btn-lg">更多介紹</button></a></div>
                    </div>
                </article>

                <!-- horizontal line with words -->
                <div class="col-md-4 margin-top-ten">
                    <hr class="horizontal-line">
                </div>
                <div class="col-md-4 ">
                    <h3 class="title-custom"><span>影評文章</span></h3>
                </div>
                <div class="col-md-4 margin-top-ten">
                    <hr class="horizontal-line">
                </div>

                <article>
                    <div class="col-md-12 text-center">
                        <header><h3 class="hover-custom"><a href="#">幽默升級的《蟻人與黃蜂女》一樣有目標一樣道阻且長</a></h3></header>
                    </div>
                    <div class="row">
                        <div class="col-md-4 ">
                            <a href="#"><img class="img-custom" src="/T3MS/img/antman-and-wasp.jpg" alt=""></a>
                        </div>
                        <div class="col-md-8 ">
                            <p class="p-custom">其他超級英雄往往遇到阻礙和反派、衝破心理陰影面積才懂得成長，而蟻人史考特是因為知道自己想要什麼，才跟他有所衝突的反派或阻礙遇上，並試著一一解決，這也是《蟻人》和《蟻人與黃蜂女》充滿活力，並吸引到其他影迷的有趣之處。</p>
                        </div>
                    </div>
                </article>

               <article>
                    <div class="col-md-12 text-center">
                        <header><h3 class="hover-custom"><a href="#">幽默升級的《蟻人與黃蜂女》一樣有目標一樣道阻且長</a></h3></header>
                    </div>
                    <div class="row">
                        <div class="col-md-4 ">
                            <a href="#"><img class="img-custom" src="/T3MS/img/antman-and-wasp.jpg" alt=""></a>
                        </div>
                        <div class="col-md-8 ">
                            <p class="p-custom">其他超級英雄往往遇到阻礙和反派、衝破心理陰影面積才懂得成長，而蟻人史考特是因為知道自己想要什麼，才跟他有所衝突的反派或阻礙遇上，並試著一一解決，這也是《蟻人》和《蟻人與黃蜂女》充滿活力，並吸引到其他影迷的有趣之處。</p>
                            <div class="text-center"><a href="#"><button type="button" class="btn btn-success btn-lg">更多文章</button></a></div>
                        </div>
                    </div>
                </article>

            </div>

            <!-- rank list -->
            <div class="col-md-4 movie_rank">
                <div class="panel panel-info panel-custom">
                    <div class="panel-heading text-center">全美週末票房排行榜<br>2018-07-06</div>
                    <div class="panel-body">
                        <div class="rank_list">
                            <ul class="hover-custom">
                                <li><a href="#">蟻人與黃蜂女</a></li>
                                <li><a href="#">侏羅紀世界：殞落國度</a></li>
                                <li><a href="#">超人特攻隊2</a></li>
                                <li><a href="#">殺戮元年</a></li>
                                <li><a href="#">怒火邊界2：毒刑者</a></li>
                                <li><a href="#">德魯大叔</a></li>
                                <li><a href="#">瞞天過海：八面玲瓏</a></li>
                                <li><a href="#">貼背戰</a></li>
                                <li><a href="#">願與我為鄰？</a></li>
                                <li><a href="#">死侍2</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="panel panel-info panel-custom">
                    <div class="panel-heading text-center">台北週末票房排行榜<br>2018-07-06</div>
                    <div class="panel-body">
                        <div class="rank_list">
                            <ul class="hover-custom">
                                <li><a href="#">蟻人與黃蜂女</a></li>
                                <li><a href="#">侏羅紀世界：殞落國度</a></li>
                                <li><a href="#">超人特攻隊2</a></li>
                                <li><a href="#">殺戮元年</a></li>
                                <li><a href="#">怒火邊界2：毒刑者</a></li>
                                <li><a href="#">德魯大叔</a></li>
                                <li><a href="#">瞞天過海：八面玲瓏</a></li>
                                <li><a href="#">貼背戰</a></li>
                                <li><a href="#">願與我為鄰？</a></li>
                                <li><a href="#">死侍2</a></li>
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