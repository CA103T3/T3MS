<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ page import="com.account.model.*"%>
<%@ page import="com.permission.model.*"%>



<%
PermissionService ppSvc = new PermissionService();
AccountVO aVO0 = (AccountVO)session.getAttribute("aVO");
List<String> sidelist = ppSvc.getOnesP(aVO0.getRole_no());

%>
        <!-- sidebar -->
        <div class="navbar sidebar-hidden collapse in" id="sidebar-wrapper">
            <ul class="nav sidebar">
                <!-- 檢舉管理 側邊折疊 -->
                <c:if test='<%=sidelist.contains("N001")||sidelist.contains("N002") %>'>   
                <li>
                    <a href="#sub1" data-toggle="collapse">檢舉管理
                        <span class="glyphicon glyphicon-chevron-right pull-right"></span>
                    </a>
                    <ul id="sub1" class="nav collapse">
                    	<c:if test='<%=sidelist.contains("N002") %>'>   
                        <li>
                            <a href="<%=request.getContextPath()%>/backstage/reportMsg/reportMsg_list.jsp">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;影評留言管理</a>
                        </li>
                        </c:if>
                    </ul>
                </li>
                </c:if>
                <!-- 檢舉管理 側邊折疊 -->

                <!-- 會員帳號管理 側邊折疊 -->
                <c:if test='<%=sidelist.contains("N003")||sidelist.contains("N004")||sidelist.contains("N005") %>'> 
                <li>
                    <a href="#sub2" data-toggle="collapse">會員帳號管理
                        <span class="glyphicon glyphicon-chevron-right pull-right"></span>
                    </a>
                    <ul id="sub2" class="nav collapse">
                    	<c:if test='<%=sidelist.contains("N004") %>'> 
                        <li>
                            <a href="<%=request.getContextPath()%>/backstage/member/becomeFilmCriticism.jsp">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;影評資格審核</a>
                        </li>
                        </c:if>
                        <c:if test='<%=sidelist.contains("N005") %>'> 
                        <li>
                            <a href="<%=request.getContextPath()%>/backstage/member/listAllMember.jsp">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;查詢會員資料</a>
                        </li>
                        </c:if>
                    </ul>
                </li>
                </c:if>
                <!-- 會員帳號管理 側邊折疊 -->

                <!-- 後台帳號管理 側邊折疊 -->
                <c:if test='<%=sidelist.contains("N006")||sidelist.contains("N007")||sidelist.contains("N008")||sidelist.contains("N009")||sidelist.contains("N010") %>'>             
                <li>
                    <a href="#sub3" data-toggle="collapse">後台帳號管理
                        <span class="glyphicon glyphicon-chevron-right pull-right"></span>
                    </a>
                    <ul id="sub3" class="nav collapse">
                    	<c:if test='<%=sidelist.contains("N007") %>'>
                        <li>
                            <a href="<%=request.getContextPath()%>/backstage/staff/backstage_insert.jsp">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;新增帳號</a>
                        </li>
                        </c:if>
                        <c:if test='<%=sidelist.contains("N008") %>'>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;修改帳號</a>
                        </li>
                        </c:if>
                        <c:if test='<%=sidelist.contains("N009") %>'>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;刪除帳號</a>
                        </li>
                        </c:if>
                        <c:if test='<%=sidelist.contains("N010") %>'>
                        <li>
                            <a href="<%=request.getContextPath()%>/backstage/staff/backstage_listAll.jsp">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;查詢帳號</a>
                        </li>
                        </c:if>
                    </ul>
                </li>
            
                </c:if>
                <!-- 後台帳號管理 側邊折疊 -->
				
                <!-- 後台角色管理 側邊折疊 -->
                <c:if test='<%=sidelist.contains("N011")||sidelist.contains("N012")||sidelist.contains("N013")||sidelist.contains("N014") %>'>          
                <li>
                    <a href="#sub4" data-toggle="collapse">後台角色管理
                        <span class="glyphicon glyphicon-chevron-right pull-right"></span>
                    </a>
                    <ul id="sub4" class="nav collapse">
                    	<c:if test='<%=sidelist.contains("N012") %>'>
                        <li>
                            <a href="<%=request.getContextPath()%>/backstage/role/role_insert.jsp">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;新增角色/權限</a>
                        </li>
                        </c:if>
                        <c:if test='<%=sidelist.contains("N013") %>'>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;修改角色/權限</a>
                        </li>
                        </c:if>
                        <c:if test='<%=sidelist.contains("N014") %>'>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;刪除角色/權限</a>
                        </li>
                        </c:if>
                    </ul>
                </li>
                </c:if>
                <!-- 後台角色管理 側邊折疊 -->

                <!-- 電影院管理 側邊折疊 -->
                <c:if test='<%=sidelist.contains("N015")||sidelist.contains("N016")||sidelist.contains("N017")||sidelist.contains("N018")||sidelist.contains("N019") %>'> 
                <li>
                    <a href="#sub5" data-toggle="collapse">電影院管理
                        <span class="glyphicon glyphicon-chevron-right pull-right"></span>
                    </a>
                    <ul id="sub5" class="nav collapse">
                    	<c:if test='<%=sidelist.contains("N016") %>'>
                        <li>
                            <a href="<%=request.getContextPath()%>/backstage/cinema/listAllCinema.jsp">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;影城</a>
                        </li>
                        </c:if>
                        <c:if test='<%=sidelist.contains("N017") %>'>
                        <li>
                            <a href="<%=request.getContextPath()%>/backstage/theater/listAllTheater.jsp?cinema_no=C001">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;影廳</a>
                        </li>
                        </c:if>
                        <c:if test='<%=sidelist.contains("N018") %>'>
                        <li>
                            <a href="<%=request.getContextPath()%>/backstage/session/listAllSession.jsp?cinema_no=C001">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;電影場次</a>
                        </li>
                        </c:if>
                        <c:if test='<%=sidelist.contains("N019") %>'>
                        <li>
                            <a href="<%=request.getContextPath()%>/backstage/ticketType/listAllTicketType.jsp?cinema_no=C001">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;票種/票價</a>
                        </li>
                        </c:if>
                    </ul>
                </li>
                </c:if>
                <!-- 電影院管理 側邊折疊 -->

                <!-- 電影資訊管理 側邊折疊 -->
                <c:if test='<%=sidelist.contains("N020")||sidelist.contains("N021")||sidelist.contains("N022")||sidelist.contains("N023")||sidelist.contains("N024") %>'> 
                <li>
                    <a href="#sub6" data-toggle="collapse">電影資訊管理
                        <span class="glyphicon glyphicon-chevron-right pull-right"></span>
                    </a>
                    <ul id="sub6" class="nav collapse">
                    	<c:if test='<%=sidelist.contains("N021") %>'>
                        <li>
                            <a href="<%=request.getContextPath()%>/backstage/movie/movie_List.jsp">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;電影管理</a>
                        </li>
                        </c:if>
                        <c:if test='<%=sidelist.contains("N022") %>'>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;票房排行榜管理</a>
                        </li>
                        </c:if>
                        <c:if test='<%=sidelist.contains("N023") %>'>
                        <li>
                            <a href="<%=request.getContextPath()%>/backstage/filmreview/fv_b.jsp">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;電影影評管理</a>
                        </li>
                        </c:if>
                        <c:if test='<%=sidelist.contains("N024") %>'>
                        <li>
                            <a href="<%=request.getContextPath()%>/backstage/movie_introduce/introduce_List1.jsp">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;電影情報管理</a>
                        </li>
                        </c:if>
                    </ul>
                </li>
                </c:if>
                <!-- 電影資訊管理 側邊折疊 -->

                <!-- 訂票系統管理 側邊折疊 -->
                <c:if test='<%=sidelist.contains("N025")||sidelist.contains("N026")||sidelist.contains("N027") %>'>
                <li>
                    <a href="#sub7" data-toggle="collapse">訂票系統管理
                        <span class="glyphicon glyphicon-chevron-right pull-right"></span>
                    </a>
                    <ul id="sub7" class="nav collapse">
                    	<c:if test='<%=sidelist.contains("N026") %>'>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;查詢場次訂票狀況</a>
                        </li>
                        </c:if>
                        <c:if test='<%=sidelist.contains("N027") %>'>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;查詢退票記錄</a>
                        </li>
                        </c:if>
                    </ul>
                </li>
                </c:if>
                <!-- 訂票系統管理 側邊折疊 -->

                <!-- 活動公告管理 側邊折疊 -->
                <c:if test='<%=sidelist.contains("N028")||sidelist.contains("N029")||sidelist.contains("N030") %>'>
                <li>
                    <a href="#sub8" data-toggle="collapse">活動公告管理
                        <span class="glyphicon glyphicon-chevron-right pull-right"></span>
                    </a>
                    <ul id="sub8" class="nav collapse">
                    	<c:if test='<%=sidelist.contains("N029") %>'>
                        <li>
                            <a href="<%=request.getContextPath()%>/backstage/announcement/listAnnouncement.jsp">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;公告管理</a>
                        </li>
                        </c:if>
                        <c:if test='<%=sidelist.contains("N030") %>'>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;活動管理</a>
                        </li>
                        </c:if>
                    </ul>
                </li>
                </c:if>
                <!-- 活動公告管理 側邊折疊 -->

                <!-- 客服系統管理 側邊折疊 -->
                <c:if test='<%=sidelist.contains("N031")||sidelist.contains("N032") %>'>
                <li>
                    <a href="#sub9" data-toggle="collapse">客服系統管理
                        <span class="glyphicon glyphicon-chevron-right pull-right"></span>
                    </a>
                    <ul id="sub9" class="nav collapse">
                    	<c:if test='<%=sidelist.contains("N032") %>'>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-list-alt"></span>&nbsp;即時客服聊天室</a>
                        </li>
                        </c:if>
                    </ul>
                </li>
                </c:if>
                <!-- 客服系統管理 側邊折疊 -->
            </ul>
            
        </div>
        <!-- sidebar -->