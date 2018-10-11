<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/util.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/login.css">
	
<style>
  #myBtn {
      width: 300px;
      padding: 10px;
      font-size:20px;
      position: absolute;
      margin: 0 auto;
      right: 0;
      left: 0;
      bottom: 50px;
      z-index: 9999;
  }
</style>
<title>waitformail</title>
</head>
<body>

<div class="limiter">
	<div class="container-login100" style="background-image: url('/T3MS/img/bg-01.jpg');">
		<div class="wrap-login100 p-t-30 p-b-50">
			<form class="login100-form validate-form p-b-10 p-t-5" action="<%=request.getContextPath()%>/member/check.do"  method="post">
				<div class="wrap-input100 validate-input">
						<input class="input100" type="text" name="code" placeholder="請輸入驗證碼">
						<span class="focus-input100" data-placeholder="&#xe88a;"></span>	
				</div>
				<div class="container-login100-form-btn m-t-32">
						<button class="login100-form-btn">認證</button>
				</div>
			</form>
			<div class="container" style="align-content: right">
					<div class="align-items-end">
					<a style="color:deeppink;" href="<%=request.getContextPath()%>/member/mailagain.do">重新寄送</a>
					</div>
			</div>
		</div>
	</div>
</div>



<%if(request.getAttribute("vested")==null){ %>

  

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">您已完成註冊!</h4>
        </div>
        <div class="modal-body">
          <p><strong></strong>驗證碼已發送至您的信箱</p>
        </div>
      </div>
      
    </div>
  </div>


<script>
$(document).ready(function(){
    $("#myModal").modal("show");
    
    $("#myBtn").click(function(){
        $("#myModal").modal("hide");
    });
        $("#myModal").on('hide.bs.modal', function () {
            
    });
});
</script>
<%} %>
</body>
</html>