<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
        <nav class="navbar navbar-inverse navbar-fixed-top navbar-custom">
            <div class="container">
                <div class="navbar-header">
                  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                  </button>
                  <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp"><img src="<%=request.getContextPath()%>/img/M&S-05Z.png"></a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                  <ul class="nav navbar-nav">
                    <li class=""><a href="#">線上訂票</a></li>
                    <li><a href="#">合作影城</a></li>
                    <li class="dropdown">
                      <a class="dropdown-toggle" data-toggle="dropdown" href="#">電影資訊<span class="caret"></span></a>
                      <ul class="dropdown-menu font-custom">
                        <li><a href="<%=request.getContextPath()%>/forestage/movie_moment/moment_Home.jsp">電影時刻</a></li>
                        <li><a href="<%=request.getContextPath()%>/forestage/movie_introduce/List_introduce.jsp">電影情報</a></li>
                        <li><a href="<%=request.getContextPath()%>/forestage/filmreview/fv_home.jsp">電影影評</a></li>
                      </ul>
                    </li>
                    <li><a href="#">社群平台</a></li>
<!--                     <li><a href="#">會員專區</a></li> -->
                    <li><a href="#">揪團看電影</a></li>
                    <li class="dropdown">
                      <a class="dropdown-toggle" data-toggle="dropdown" href="#">客服中心<span class="caret"></span></a>
                      <ul class="dropdown-menu font-custom">
                        <li><a href="#">FAQ</a></li>
                        <li><a href="#">線上客服</a></li>
                        <li><a href="#">聯絡我們</a></li>
                      </ul>
                    </li>
                  </ul>
                  <c:if test="${memVO==null}">
                  <ul class="nav navbar-nav navbar-right">               
	                  <li><a href="registerf.jsp"><span class="glyphicon glyphicon-user"></span>&nbsp;註冊</a></li>
	                  <li><a href="/T3MS/forestage/member/loginf.jsp"><span class="glyphicon glyphicon-log-in"></span>&nbsp;登入</a></li>
                  </ul>
                  </c:if>
                 
 					<c:if test="${memVO!=null}">
 					<ul class="nav navbar-nav navbar-right">
 						<li><img style="border: 1px solid black;width:44px;height:42px;border-radius:50%;padding-top:5px;" src="<%=request.getContextPath() %>/DBGifReaderMem?memno=${memVO.memno}">
                    	<li><a href="/T3MS/forestage/member/membercenter.jsp">${memVO.lastname}${memVO.firstname}</a></li>
                    </ul>
                    </c:if>
                </div>
            </div>
        </nav>