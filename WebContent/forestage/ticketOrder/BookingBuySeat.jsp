<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5" import="java.util.*"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<%@ include file="/forestage/template/link.jsp"%>
<title>M&amp;S</title>
<style>
	td{
		vertical-align: middle;
		text-align: center;
	}
</style>
</head>
<body class="body-template">
	<%@ include file="/forestage/template/header.jsp"%>
			<%
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=utf-8");
				String movie_name =request.getParameter("movie_name").trim();
				String theater_name = request.getParameter("theater_name").trim(); 
				String tickettype_no = request.getParameter("type_no").trim();
				String session_no = request.getParameter("session_no").trim();
				String mem_no = "M0001";
// 				String mem_no = session.getAttribute("mem_no").toString();
				Integer price = Integer.valueOf(request.getParameter("price"));
				String[] seats = request.getParameterValues("seats");
				int ticket = seats.length;
			%>
	<div class="container" style="color: #000; font-size: 20px;">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12">
			<form method="POST" action="<%=request.getContextPath()%>/ticketOrder/ticketOrder.do">
			
			<div class="panel panel-default">
				  <div class="panel-heading text-center">購票資訊</div>
				  <table class="table">
				    <tr>
				    	<td>電影名稱</td>
				    	<td>影廳</td>
				    	<td>票數</td>
				    	<td>金額</td>
				    </tr>
				    <tr>
					    <td><%=movie_name %></td>
					    <td><%=theater_name %></td>
				    	<td><%=ticket %></td>
				    	<td>＄<%=ticket*price%></td>
				    </tr>
				  </table>
			</div>
			
			<div class="panel panel-default">
				  <div class="panel-heading text-center">座位資訊</div>
				  <table class="table">
				    <tr>
					    <td>
							<%  
								String seat = null;
								for(int i=0; i < ticket; i++ ){
									seat = seats[i];
									out.print(seat);
									if(!(i==ticket-1)){
										out.print("、");
									}
								}
// 								for (String seat : seats)
// 		    					out.println(seat+"、"); 		
		    				%>
						</td>
				    </tr>
				  </table>
			</div>
                
                <div class="panel panel-default">
				  <div class="panel-heading">
				    <h3 class="panel-title text-center title-bold" style="font-size:20px">請輸入信用卡資訊</h3>
				  </div>
				  <div class="panel-body">
				    
				<div class="col-sm-8 col-sm-offset-4">
					<div class="row">
						<div class="card form-group">						
							<div class="form-inline">
								<label for="card">卡號：</label>
								<input type="text" id="card1" name="card1" size="4" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'')" class="form-control">-
								<input type="text" id="card2" name="card2" size="4" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'')" class="form-control">-
								<input type="text" id="card3" name="card3" size="4" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'')" class="form-control">-
								<input type="text" id="card4" name="card4" size="4" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'')" class="form-control">
							</div>
							<br>
							<div class="form-inline">
								<label for="card">卡片檢核碼：</label>
								<input type="text" name="auth_key" size="2" maxlength="3" name="card" onkeyup="value=value.replace(/[^\d]/g,'')" class="form-control">
								<br><br>
								<label for="card">到期日：</label>
								<input type="text" name="mm"  size="2" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'')" class="form-control">月
								<input type="text" name="yy" size="2"  maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'')" class="form-control">年

						</div>
						</div>
					</div>
				</div>
			</div>
		</div>
                <input type="hidden" name="action" value="insert" />
                <input type="hidden" name="amount" value="<%=ticket*price%>"/>
                <input type="hidden" name="tickettype_no" value="<%=tickettype_no %>" />
                <input type="hidden" name="mem_no" value="<%=mem_no %>" />
                <input type="hidden" name="session_no" value="<%=session_no %>" />
                <button type="submit" class="btn btn-primary" id="save">確定</button>
			</form>
		</div>
	</div>
</div>
	<%@ include file="/forestage/template/footer.jsp"%>
<script type="text/javascript">
	$(function(){
	    $("#card1").focus();
	})
	$("input[name^='card']").each(function(){
   		 $(this).keyup(function(e){
        	 if($(this).val().length < 1){
     	        $(this).prev().focus();
	         }else {
	            if($(this).val().length >= 4){
	                $(this).next().focus();
	            }
	         }
        });
	});
</script>
	<script src="<%=request.getContextPath()%>/js/template.js"></script>
<script>
		$(document).ready(function() {
			$("li:contains('合作影城')").addClass("custom-active");
		});
</script>
</body>
</html>