<%@page import="com.ticketType.model.TypeVO"%>
<%@page import="com.ticketType.model.TypeService"%>
<%@page import="com.movie.model.MovieVO"%>
<%@page import="com.movie.model.MovieService"%>
<%@page import="com.member.model.MemService"%>
<%@page import="com.member.model.MemVO"%>
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
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				
				String movie_no = request.getParameter("movie_no").trim(); //�q�v�s��
				String movie_name =request.getParameter("movie_name").trim(); //�q�v�W��
				String theater_name = request.getParameter("theater_name").trim(); //�v�U�W��
				String tickettype_no = request.getParameter("type_no").trim(); //���ؽs��
				String session_no = request.getParameter("session_no").trim(); //�����s��
				String mem_no = request.getParameter("mem_no").trim(); //�|���s��
				
				MemVO memVO = new MemService().getMemVOByNO(mem_no);
				String mem_email = memVO.getEmail();  //�|���H�c
				String mem_LastName = memVO.getLastname();
				String mem_FirstName = memVO.getFirstname();
				StringBuffer mem_FullName = new StringBuffer();
				mem_FullName.append(mem_FirstName).append(mem_LastName); //�|���W��
				
				Integer price = Integer.valueOf(request.getParameter("price"));
				String[] seats = request.getParameterValues("seats");
				int ticket = seats.length;
			%>
		
	<div class="container" style="color: #000; font-size: 25px;">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12">
			<form method="POST" action="<%=request.getContextPath()%>/ticketOrder/ticketOrder.do">
			
			<div class="panel panel-default">
				  <div class="panel-heading text-center">�ʲ���T</div>
				  <table class="table">
				    <tr>
				    	<td>���W�G<%=movie_name %></td>
				    	<td>�v�U</td>
				    	<td>����</td>
				    	<td>���</td>
				    	<td>���B</td>
				    </tr>
				    <tr>
					    <td>
					    <img style="width:250px; height:320px" id="movie_pic" src="<%=request.getContextPath() %>/DBGifReader?movie_no=<%=movie_no%>">
					    </td>
					    <td><%=theater_name %></td>
				    	<td><%=ticket %></td>
				    	<td>�C<%=price%></td>
				    	<td>�C<%=ticket*price%></td>
				    </tr>
				  </table>
			</div>
			
			<div class="panel panel-default">
				  <div class="panel-heading text-center">�y���T</div>
				  <table class="table">
				    <tr>
					    <td>
					   	    <%  
								String seat = null;
					   	    	StringBuffer seatAll = new StringBuffer();
					   	    	StringBuffer seatChinese = new StringBuffer();
								for(int i=0; i < ticket; i++ ){
									seat = seats[i];
									String[] seatArr = seat.split("_");
									String temp = (char)(Integer.parseInt(seatArr[0]) + 64) +"��"+ seatArr[1] + "��";
									out.print(temp);
									seatChinese.append(temp+"@");  //�x�s��ܪ�����y��
									seatAll.append(seat+"@");
									if(!(i==ticket-1)){
										out.print("�B");
									}
								}
		    				%>
						<input type="hidden" name="seatTD" value="<%=seatAll %>" />	
						</td>
				    </tr>
				  </table>
			</div>
                
                <div class="panel panel-default">
				  <div class="panel-heading">
				    <h3 class="panel-title text-center title-bold" style="font-size:25px">�п�J�H�Υd��T</h3>
				  </div>
				  <div class="panel-body">
				    
				<div class="col-sm-8 col-sm-offset-4">
					<div class="row">
						<div class="card form-group">						
							<div class="form-inline">
								<label for="card">�d���G</label>
								<input type="text" id="card1" name="card1" size="4" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'')" class="form-control">-
								<input type="text" id="card2" name="card2" size="4" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'')" class="form-control">-
								<input type="text" id="card3" name="card3" size="4" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'')" class="form-control">-
								<input type="text" id="card4" name="card4" size="4" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'')" class="form-control">
							</div>
							<br>
							<div class="form-inline">
								<label for="card">�d���ˮֽX�G</label>
								<input type="text" name="auth_key" size="2" maxlength="3" name="card" onkeyup="value=value.replace(/[^\d]/g,'')" class="form-control">
								<br><br>
								<label for="card">�����G</label>
								<input type="text" name="mm"  size="2" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'')" class="form-control">��
								<input type="text" name="yy" size="2"  maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'')" class="form-control">�~

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
               	<input type="hidden" name="mem_FullName" value="<%=mem_FullName %>" />
               	<input type="hidden" name="mem_email" value="<%=mem_email %>" />
               	<div class="panel panel-default">
				  <div class="panel-heading text-center">�I�ڪ��B�G�C<%=ticket*price%></div>
                	<button type="submit" class="btn btn-lg btn-primary btn-block" id="save">�T�w</button>
                </div>
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
			$("li:contains('�X�@�v��')").addClass("custom-active");
		});
</script>
</body>
</html>