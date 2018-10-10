<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.account_backstage.model.*"%>

<%
	Account_BackstageVO account_BackstageVO = (Account_BackstageVO) request.getAttribute("account_BackstageVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">	
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<style type="text/css">
body {
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	margin-left: 0px;
	height: 700px;
    background-color: red; /* For browsers that do not support gradients */
    background-image: linear-gradient(to top, #afeeee, #e0ffff); /* Standard syntax (must be last) */
}

.contain {
    height: 1000px;
    background-color: red; /* For browsers that do not support gradients */
    background-image: linear-gradient(to top, #afeeee, #e0ffff); /* Standard syntax (must be last) */
}
</style>


<title>員工資料新增 - addaccount_backstage.jsp</title>

</head>
<body bgcolor='white'>

<center>
		 <h3>員工資料新增 - addaccount_Backstage.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/select_page.jsp">回首頁</a></h4>

<div class="w3-container">
	<div class="w3-card-4" style="width:40%;">
	 	<header class="w3-container w3-blue">
	      <h4>資料新增</h4>
	    </header>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="account_backstageServlet.do" name="form1">
 <div class="w3-container" style="background-color:#f0ffff">
 
<table>

	<tr>
		<td>帳號名稱:</td>
		<td><input type="TEXT" name="bs_acc_name" size="45" 
			 value="<%= (account_BackstageVO==null)? "鐘天佑" : account_BackstageVO.getBs_acc_name()%>" /></td>
	</tr>
	
	<tr>
		<td>角色編號:</td>
		<td><input type="TEXT" name="role_no" size="45"
			 value="<%= (account_BackstageVO==null)? "role" : account_BackstageVO.getRole_no()%>" /></td>
	</tr>
	
	<tr>
		<td>影城編號:</td>
		<td><input type="TEXT" name="cinema_no" size="45"
			 value="<%= (account_BackstageVO==null)? "CINEMA_NO" : account_BackstageVO.getCinema_no()%>" /></td>
	</tr>
	<tr>
		<td>帳號密碼:</td>
		<td><input type="TEXT" name="bs_acc_psw" size="45" 
			 value="<%= (account_BackstageVO==null)? "psw" : account_BackstageVO.getBs_acc_psw()%>" /></td>
	</tr>
	<tr>
		<td>電子郵件:</td>
		<td><input type="TEXT" name="email" size="45"
			 value="<%= (account_BackstageVO==null)? "EMAIL" : account_BackstageVO.getEmail()%>" /></td>
	</tr>
	<tr><!--多出來-->
		<td>聯絡電話:</td>
		<td><input type="TEXT" name="tel" size="45"
			 value="<%= (account_BackstageVO==null)? "TEL" : account_BackstageVO.getTel()%>" /></td>
	</tr>
	<tr><!--多出來-->
		<td>上次在線時間:</td>
		<td><input name="last_online_time" id="f_date1" type="text" size="45"></td>
	</tr>
	<tr><!--多出來  -->
		<td>帳號狀態:</td>
		<td><input type="TEXT" name="state" size="45"
			 value="<%= (account_BackstageVO==null)? "1" : account_BackstageVO.getState()%>" /></td>
	</tr>

<%-- 	<jsp:useBean id="backstage_RoleService" scope="page" class="com.backstage_role.model.Backstage_RoleService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="role_no"> -->
<%-- 			<c:forEach var="backstage_RoleVO" items="${backstage_RoleService.all}"> --%>
<%-- 				<option value="${backstage_RoleVO.role_no}" ${(account_BackstageVO.role_no==backstage_RoleVO.role_no)? 'selected':'' } >${backstage_RoleVO.br_name} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->
<%-- 	<jsp:useBean id="backstage_RoleService" scope="page" class="com.backstage_role.model.Backstage_RoleService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>帳號狀態:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="role_no"> -->
<%-- 			<c:forEach var="backstage_RoleVO" items="${backstage_RoleService.all}"> --%>
<%-- 				<option value="${backstage_RoleVO.role_no}" ${(account_BackstageVO.role_no==backstage_RoleVO.role_no)? 'selected':'' } >${backstage_RoleVO.br_name} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>帳號狀態:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="state"> -->
<%-- 			<c:forEach var="backstage_RoleVO" items="${backstage_RoleService.all}"> --%>
<%-- 				<option value="${backstage_RoleVO.role_no}">${backstage_RoleVO.br_name} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select> -->
<!-- 		</td> -->
<!-- 	</tr> -->

</table>	
		</div>
		<footer class="w3-container w3-blue">
		      <h5>如有任何疑問，歡迎與我們連繫  24小時服務專線 0800-30-1313 02-2182-1313</h5>
		    </footer>
		</div>
	</div>
</div>

<br>
<input type="hidden" name="action" value="insert" >
<input type="submit" value="送出新增" class="btn btn-primary">
</center>
</FORM>

</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Timestamp last_online_time = null;
  try {
	  last_online_time = account_BackstageVO.getLast_online_time();
   } catch (Exception e) {
	   last_online_time = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=last_online_time%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>