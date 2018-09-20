<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
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
                            <a href="listAnnouncement.jsp">
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