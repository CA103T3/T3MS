<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
    <!-- header -->
    <nav class="navbar navbar-default navbar-fixed-top mb0 brs-none">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="/T3MS/backstage/backstage_index.jsp" class="navbar-brand">
                    <img id="backimg" src="<%=request.getContextPath()+"/img/LOGO3_SM.png"%>" alt="logo">
                </a>
                <a href="#" class="btn menu-toggle mt4"><i class="fa fa-bars fa-2x" aria-hidden="true"></i></a>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a aria-expanded="false" aria-haspopup="true" class="dropdown-toggle" role="button" data-toggle="dropdown">
                        ${aVO.bs_acc_name}
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
                    <a href="<%=request.getContextPath()%>/account/logout.do">
                        <span class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;退出</a>
                </li>
            </ul>
        </div>
    </nav>
    <!-- header -->