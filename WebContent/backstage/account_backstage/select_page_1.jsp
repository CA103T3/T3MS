<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<title>IBM Account_Backstage: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>
<center>
<table id="table-1">
   <tr><td><h3>IBM Account_Backstage: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Account_Backstage: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	
</c:if>


  <a href='listAllAccount_Backstage.jsp'>List</a> all Account_Backstage.  <br><br>
  
  
  
    <FORM METHOD="post" ACTION="account_backstageServlet.do" >
     <form class="form-horizontal">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
    <div class="col-sm-2">
      <input type="text" name="bs_acc_no" class="form-control" id="inputEmail3" >
      <input type="hidden" name="action" value="getOne_For_Display">
      
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
    <div class="col-sm-2">
      <input type="password" name="bs_acc_psw" class="form-control" id="inputPassword3" >
      <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </div>
  </div>
  <!-- <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox"> Remember me
        </label>
      </div>
    </div>
  </div> -->
  <!-- <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Sign in</button>
    </div>
  </div> -->
</form>
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
  



<h3>員工管理</h3>


  <a href='addaccount_backstage.jsp'>Add</a> a new addaccount_backstage.


</body>
</html>