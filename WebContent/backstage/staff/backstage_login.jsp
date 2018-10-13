<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>M&amp;S</title>
    <%@ include file="/backstage/template/link.jsp" %>
    <style type="text/css">
    
body{
	margin: 0;
	padding: 0;
	font-family: Helvetica,"",Arial, sans-serif; 
	font-size: 16px;
	color:rgba(0, 0, 0, 0.7);
	background: url('../images/ODRAZ80.jpg');
}

.form-group{
	margin-bottom:1.5rem;
    margin-top: 1.5rem;
}

.login_box{
	margin: 100px auto 30px auto;
}

/*.login_left{
	color:#fff;
	background: rgba(0, 0, 0, 0.3);
	border-top-left-radius: .25rem;
	border-bottom-left-radius: .25rem;
	padding: 30px;
	position: relative;
}
.login_left h2{
	font-size: 5vw;
	line-height: 1;
	font-weight: bold;
	margin-bottom: 110px;
}

.memo_font{
	position: absolute;
    bottom: 25px;
    width: 85%;
}*/

.top_bar{
    background-color: #fff;
    padding: 10px;
    /**/
    box-shadow: 0px 0px 5px #2e2e2e; 
    -webkit-box-shadow: 0px 0px 5px #2e2e2e; 
    -moz-box-shadow: 0px 0px 5px #2e2e2e;
}
.top_bar a,.top_bar div{
	display: inline-block;
}

.login_right{
	background: #fff;
	border-radius: .25rem;
	padding: 30px;
	margin: 0 auto;
	max-width: 400px;
	/**/
    box-shadow: 0px 0px 5px #2e2e2e; 
    -webkit-box-shadow: 0px 0px 5px #2e2e2e; 
    -moz-box-shadow: 0px 0px 5px #2e2e2e;
}
.login_right h5{
	text-align: center;
}

.logo{
	max-width: 250px;
    margin: 0 auto 10px auto;
}

.btn_box{
	display: flex;
	justify-content: center;
}
.login_btn{
	border-radius: 2.25rem;
	border-radius: 2.25rem;
    width: 80px;
    margin: 20px 0;
}
.not_rem{
	margin: 10px 0;
}

.bottom_font{
	text-align: center;
	color: #696969;
}

@media screen and (max-width: 812px){
	.login_box{margin: 30px auto 30px auto;}
}
    </style>
</head>

<body class="fs16">
    <%@ include file="/backstage/template/header.jsp" %>
    <div id="wrapper" class="mt50">
        <%@ include file="/backstage/template/sidebar.jsp" %>
        <div class="flex-column" id="page-content-wrapper">
           <div class="row login_box">
			<div class="col-sm login_right">
				<!-- <div class="logo"><img src="./view/images/hitrust_logo.svg" alt=""></div> -->
				<h5>MS後台登入</h5>	
				<form name="form1" method="post" action="window.jsp">
					<input type='hidden' name='sessionid' value='BEEF39E6681B7441035CC47E95C97B9B868E748F'/>
					<div class="form-group">
						<!-- <label for="exampleInputEmail1">Email address</label> -->
						<input type="text" class="form-control"  name="userId" aria-describedby="" placeholder="帳號/Account">
						<input type="hidden" name="fid" value="0"> 
						
					</div>
					<div class="form-group">
					 	<!-- <label for="exampleInputPassword1">Password</label> -->
					 	<input type="password" class="form-control" name='userPwd' placeholder="密碼/Password">
					</div>
					
					<div class="not_rem"><div id="divReCAPTCHA"></div></div>
					<div class="btn_box">
						<button type="button" class="btn btn-primary login_btn" name="login" id="login">登入</button>
					</div>
				</form>
			</div>
		</div>
           
        </div>
    </div>
    <script src="<%=request.getContextPath()+"/js/back_index.js"%>"></script>
</body>
</html>