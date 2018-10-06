<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>M&amp;S</title>

    <!-- include libraries(jQuery, bootstrap) -->
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()+"/css/back_base.css"%>">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">

    </style>
    
</head>

<body class="fs16">
    <!-- header -->
    <nav class="navbar navbar-default navbar-fixed-top mb0 brs-none">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="#" class="navbar-brand">
                    <img id="backimg" src="<%=request.getContextPath()+"/img/LOGO3_SM.png"%>" alt="logo">
                </a>
                <a href="#" class="btn menu-toggle mt4"><i class="fa fa-bars fa-2x" aria-hidden="true"></i></a>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a aria-expanded="false" aria-haspopup="true" class="dropdown-toggle" role="button" data-toggle="dropdown">
                        Admin
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-screenshot"></span>&nbsp;&nbsp;前台首頁</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;個人主頁</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-cog"></span>&nbsp;&nbsp;個人設定</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-credit-card"></span>&nbsp;&nbsp;帳號中心</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-heart"></span>&nbsp;&nbsp;我的收藏</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#">
                        <span class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;退出</a>
                </li>
            </ul>
        </div>
    </nav>
    <!-- header -->

    <div id="wrapper" class="mt50">
        <!-- sidebar -->
        <div class="navbar sidebar-hidden collapse in" id="sidebar-wrapper">
            <ul class="nav sidebar">
                <!-- 檢舉管理 側邊折疊 -->
                <li>
                    <a href="#sub1" data-toggle="collapse">檢舉管理
                        <span class="glyphicon glyphicon-chevron-right pull-right"></span>
                    </a>
                    <ul id="sub1" class="nav collapse">
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;影評留言管理</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;社群照片管理</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;社群留言管理</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;揪團成員管理</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;揪團留言管理</a>
                        </li>
                    </ul>
                </li>
                <!-- 檢舉管理 側邊折疊 -->

                <!-- 會員帳號管理 側邊折疊 -->
                <li>
                    <a href="#sub2" data-toggle="collapse">會員帳號管理
                        <span class="glyphicon glyphicon-chevron-right pull-right"></span>
                    </a>
                    <ul id="sub2" class="nav collapse">
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;影評資格審核</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;查詢會員資料</a>
                        </li>
                    </ul>
                </li>
                <!-- 會員帳號管理 側邊折疊 -->

                <!-- 後台帳號管理 側邊折疊 -->
                <li>
                    <a href="#sub3" data-toggle="collapse">後台帳號管理
                        <span class="glyphicon glyphicon-chevron-right pull-right"></span>
                    </a>
                    <ul id="sub3" class="nav collapse">
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;新增帳號</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;修改帳號</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;刪除帳號</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;查詢帳號</a>
                        </li>
                    </ul>
                </li>
                <!-- 後台帳號管理 側邊折疊 -->

                <!-- 後台角色管理 側邊折疊 -->
                <li>
                    <a href="#sub4" data-toggle="collapse">後台角色管理
                        <span class="glyphicon glyphicon-chevron-right pull-right"></span>
                    </a>
                    <ul id="sub4" class="nav collapse">
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;新增角色/權限</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;修改角色/權限</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;刪除角色/權限</a>
                        </li>
                    </ul>
                </li>
                <!-- 後台角色管理 側邊折疊 -->

                <!-- 電影院管理 側邊折疊 -->
                <li>
                    <a href="#sub5" data-toggle="collapse">電影院管理
                        <span class="glyphicon glyphicon-chevron-right pull-right"></span>
                    </a>
                    <ul id="sub5" class="nav collapse">
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;影城</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;影廳</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;電影場次</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;票種/票價</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;餐點</a>
                        </li>
                    </ul>
                </li>
                <!-- 電影院管理 側邊折疊 -->

                <!-- 電影資訊管理 側邊折疊 -->
                <li>
                    <a href="#sub6" data-toggle="collapse">電影資訊管理
                        <span class="glyphicon glyphicon-chevron-right pull-right"></span>
                    </a>
                    <ul id="sub6" class="nav collapse">
                        <li>
                            <a href="<%=request.getContextPath()%>/backstage/movie/movie_List.jsp">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;電影管理</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;票房排行榜管理</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;電影影評管理</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/backstage/movie_introduce/introduce_List1.jsp">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;電影情報管理</a>
                        </li>
                    </ul>
                </li>
                <!-- 電影資訊管理 側邊折疊 -->

                <!-- 訂票系統管理 側邊折疊 -->
                <li>
                    <a href="#sub7" data-toggle="collapse">訂票系統管理
                        <span class="glyphicon glyphicon-chevron-right pull-right"></span>
                    </a>
                    <ul id="sub7" class="nav collapse">
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;查詢場次訂票狀況</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;查詢退票記錄</a>
                        </li>
                    </ul>
                </li>
                <!-- 訂票系統管理 側邊折疊 -->

                <!-- 活動公告管理 側邊折疊 -->
                <li>
                    <a href="#sub8" data-toggle="collapse">活動公告管理
                        <span class="glyphicon glyphicon-chevron-right pull-right"></span>
                    </a>
                    <ul id="sub8" class="nav collapse">
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;公告管理</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;活動管理</a>
                        </li>
                    </ul>
                </li>
                <!-- 活動公告管理 側邊折疊 -->

                <!-- 客服系統管理 側邊折疊 -->
                <li>
                    <a href="#sub9" data-toggle="collapse">客服系統管理
                        <span class="glyphicon glyphicon-chevron-right pull-right"></span>
                    </a>
                    <ul id="sub9" class="nav collapse">
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;即時客服聊天室</a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;歷史訊息</a>
                        </li>
                    </ul>
                </li>
                <!-- 客服系統管理 側邊折疊 -->
            </ul>
        </div>
        <!-- sidebar -->

        <div class="flex-column" id="page-content-wrapper">
            <div class="container-fluid mt20">
                <p>本片改編自韓國漫畫家周浩旻同名漫畫，耗時6年籌備，上下集共斥資400億韓元（約12億元台幣），上集《與神同行》以近4.9億元勇奪台灣影史韓片票房冠軍。本集故事則是著重在三名陰間使者江林、解怨脈和德春千年前的前世糾葛，並加入「成造神」馬東石新角色，以詼諧角度描述了韓國社會的生活現況，片尾還揭開了閻羅王的真面目。下集一開始，陰間使者江林（河正宇飾演）打算幫秀鴻（金東昱飾演）洗刷冤屈，並將秀鴻引渡到陰間進行審判，讓他有轉世機會。不過陰間嚴禁身為冤鬼的秀鴻進入，江林試圖說服閻羅王（李政宰飾演）放行，因為他知道秀鴻的死亡真相，並肯定他能投胎轉世，成為他與其他兩位陰間使者的第49位「貴人」。此時，閻羅王開出兩大條件：一是在秀鴻的審判結束前，陰間使者必須帶回一個老人的靈魂，因為這名老人「許春三」陽壽已盡，卻因為家神-「成造神」（馬東石飾演）的守護而一直活在人間。二是如果秀鴻在七場審判中的任何一場審判被判有罪，三位陰間使者就必須放棄自己轉世的機會，並且永世不得超生…。為了完成閻羅王開出的條件，解怨脈（朱智勳飾演）與德春（金香起飾演）開始跟「成造神」進行決鬥。同時，江林也力勸元東延（都敬秀飾演）和朴中尉說出秀鴻真正死亡的真相。就在陰間使者們試圖完成任務的同時，千年前，他們生前的悲劇也被揭開。原來成造神當初是1千年前帶走解怨脈和李德春的陰間使者，解怨脈是千年前的高麗時期武士「白狼」，李德春則是照顧失去父母的女真族少女，兩人之間的相處情誼真誠動容。至於唯一擁有前世記憶的江林成為陰間使者的秘密也將公開....導演金容華表示《與神同行：最終審判》可說是《與神同行》系列的出發點，為了串聯兩集故事，並更有效地運用電影背景，因此選擇「兩集同時拍攝」的艱鉅挑戰。尤其第一集必須奠定貫穿劇情的世界觀，並組織角色們的獨特風格，才能讓觀眾在觀賞第二集時，能更輕易地接受劇情想要傳達的訊息。因此，《與神同行：最終審判》將可看到更細緻的情感密度，以及更深入的劇情敘述。在第一集所埋下的許多故事線索，都將在第二集如拼圖般拼湊完成。…………（影片資訊由【開眼電影網】提供）</p>
            </div>
        </div>
    </div>
    <script src="<%=request.getContextPath()+"/js/back_index.js"%>"></script>
</body>
</html>