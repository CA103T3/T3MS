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
<%-- 	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/back_img_base.css"> --%>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
     <link rel="stylesheet" href="/T3MS/css/style_2.css">
    <style type="text/css">

    </style>
    
</head>

<body class="fs16" background="/T3MS/img/a1.jpg">
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
                            <a href="<%=request.getContextPath()%>/backstage/account_backstage/addaccount_backstage.jsp">
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
                            <a href="#">
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
                            <a href="#">
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
                            <a href="<%=request.getContextPath()%>/backstage/announcement/listAnnouncement.jsp">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;公告管理</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/backstage/activity/listActivity.jsp">
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
                            <a href="<%=request.getContextPath()%>/backstage/service_BackChat/chatServer.jsp">
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

        <div class="flex-column" id="page-content-wrapper" style="margin-left:150px;">
           <section id="slideshow">
			<div class="entire-content">
				<div class="content-carrousel">
					<figure class="shadow"><img src="/T3MS/img/introduce/0800dab154184f1f998b3285cf44d570-s.jpg"/></figure>
					<figure class="shadow"><img src="/T3MS/img/introduce/0800dab154184f1f998b3285cf44d570-s.jpg"/></figure>
					<figure class="shadow"><img src="/T3MS/img/introduce/0800dab154184f1f998b3285cf44d570-s.jpg"/></figure>
					<figure class="shadow"><img src="/T3MS/img/introduce/0800dab154184f1f998b3285cf44d570-s.jpg"/></figure>
					<figure class="shadow"><img src="/T3MS/img/introduce/0800dab154184f1f998b3285cf44d570-s.jpg"/></figure>
					<figure class="shadow"><img src="/T3MS/img/introduce/0800dab154184f1f998b3285cf44d570-s.jpg"/></figure>
					<figure class="shadow"><img src="/T3MS/img/introduce/0800dab154184f1f998b3285cf44d570-s.jpg"/></figure>
					<figure class="shadow"><img src="/T3MS/img/introduce/0800dab154184f1f998b3285cf44d570-s.jpg"/></figure>
					<figure class="shadow"><img src="/T3MS/img/introduce/0800dab154184f1f998b3285cf44d570-s.jpg"/></figure>
		</div>
	</div>
</section>
       </div>
   </div>
    <script src="<%=request.getContextPath()+"/js/back_index.js"%>"></script>
</body>
</html>