<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<title>MOVIES : Home</title>

<style>
/*   table#table-1 { */
/* 	width: 450px; */
/*  	background-color: #CCCCFF;  */
/* 	margin-top: 5px; */
/* 	margin-bottom: 10px; */
/*     border: 3px ridge Gray; */
/*     height: 80px; */
/*     text-align: center; */
/*   } */
/*   table#table-1 h4 { */
/*     color: red; */
/*     display: block; */
/*     margin-bottom: 1px; */
/*   } */
/*   h4 { */
/*     color: blue; */
/*     display: inline; */
/*   } */
  
  .red:focus{
		outline-color: pink;
	}
</style>

</head>
<body bgcolor='white'>
<center>
<!-- <table id="table-1"> -->
<!--    <tr><td><h3>IBM Account_Backstage: Home</h3><h4>( MVC )</h4></td></tr> -->
<!-- </table> -->

<!-- <p>This is the Home page for IBM Account_Backstage: Home</p> -->

<table id="table-1">
	<tr><td>
		 
<!-- 		 <h4><a href="select_page.jsp"><img src="images/M_S-01-4.jpg" width="100" height="32" border="0"></a></h4> -->
		 <h4><a href="select_page.jsp"><img src="<%= request.getContextPath() %>/img/M_S-01-4.jpg" width="100" height="32" border="0"></a></h4>
	</td></tr>
</table>


	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	
</c:if>


    
  
 
  
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backstage/account_backstage/account_backstageServlet.do" >
    <div class="container-fluid">
    <div class="row">
     	<b>輸入帳號編號:</b><br>
        <input type="text" name="bs_acc_no" class="red">
<!--       第一組: {key:value,key=name="bs_acc_no":使用者輸入的帳號編號} -->
    </div>
    
    
    <div class="row">
        <b>輸入帳號密碼:</b><br>
        <input type="password" name="bs_acc_psw" class="red">
<!--       第二組:{key:value,key=name="bs_acc_psw":使用者輸入的密碼} -->
        <input type="hidden" name="action" value="getOne_For_Display">
<!--       第三組:{key:value,key=name="action":value=value="getOne_For_Display"} 總共有三組-->
	</div>
    	<br>
     	<input type="submit" value="送出" class="btn btn-primary">
     	<input type="reset" class="btn btn-danger">	
     
     </div>
    </FORM>
  


<%--   <jsp:useBean id="account_BackstageService" scope="page" class="com.account_backstage.model.Account_BackstageService" /> --%>
   
  
<!--      <FORM METHOD="post" ACTION="account_backstageServlet.do" > -->
<!--        <b>選擇帳號編號:</b> -->
<!--        <select size="1" name="bs_acc_no"> -->
<%--          <c:forEach var="account_BackstageVO" items="${account_BackstageService.all}" >  --%>
<%--           <option value="${account_BackstageVO.bs_acc_no}">${account_BackstageVO.bs_acc_no} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--     </FORM> -->
  
  
  
<!--      <FORM METHOD="post" ACTION="account_backstageServlet.do" > -->
<!--        <b>選擇員工姓名:</b> -->
<!--        <select size="1" name="bs_acc_no"> -->
<%--          <c:forEach var="account_BackstageVO" items="${account_BackstageService.all}" >  --%>
<%--           <option value="${account_BackstageVO.bs_acc_no}">${account_BackstageVO.bs_acc_name} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--      </FORM> -->
  	
  	<h3>資料查詢</h3>

	<a href='listAllAccount_Backstage.jsp'>List</a> all listAllAccount_Backstage.

	<h3>歡迎加入</h3>

  	<a href='addaccount_backstage.jsp'>註冊</a> a new addaccount_backstage.

</center>
</body>
</html>