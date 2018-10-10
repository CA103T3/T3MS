<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.account_backstage.model.*"%>
<%@ page import="com.role_permission_bs.model.*"%>
<%@ page import="java.util.*"%>

<%
	Account_BackstageVO account_BackstageVO = (Account_BackstageVO) request.getAttribute("account_BackstageVO");
// 	pageContext.setAttribute("account_BackstageVO", account_BackstageVO);
	Role_Permission_BsVO role_Permission_BsVO = (Role_Permission_BsVO) request.getAttribute("role_Permission_BsVO");
// 	pageContext.setAttribute("Role_Permission_BsVO", role_Permission_bsVO);

	Role_Permission_BsService role_Permission_bsService = new Role_Permission_BsService();
	List<Role_Permission_BsVO> list=role_Permission_bsService.getAll2();
	pageContext.setAttribute("list",list);
	
	

	List list2 = (List)request.getAttribute("list");
	 
	 


	
	
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


<title>���u��Ʒs�W -update_account_backstage_input.jsp</title>

</head>
<body bgcolor='white'>

<center>
		 <h3>���u��ƭק� - update_account_backstage_input.jsp</h3></td><td>
		 <h4><a href="select_page.jsp">�^����</a></h4>
	
<div class="w3-container">
	<div class="w3-card-4" style="width:40%;">
	 	<header class="w3-container w3-blue">
			<h4>��ƭק�</h4>
		</header>
		
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backstage/account_backstage/account_backstageServlet.do" name="form1">
<div class="w3-container" style="background-color:#f0ffff">
<table>

	<tr>
		<td>�b���s��:<font color=red><b>*</b></font></td>
		<td><%=account_BackstageVO.getBs_acc_no()%></td>
	</tr>
	
	<tr>
		<td>�b���W��:</td>
		<td><input type="TEXT" name="bs_acc_name" size="45" value="<%=account_BackstageVO.getBs_acc_name()%>" /></td>
		
	</tr>
	
<!-- 	<tr> -->
<!-- 		<td>����s��:</td> -->
<%-- 		<td><input type="TEXT" name="role_no" size="45" value="<%=account_BackstageVO.getRole_no()%>" /></td> --%>
		
<!-- 	</tr> -->
	
	<tr>
		<td>�v���s��:</td>
		<td><input type="TEXT" name="cinema_no" size="45"	value="<%=account_BackstageVO.getCinema_no()%>" /></td>
		
	</tr>
	<tr>
		<td>�b���K�X:</td>
		<td><input type="TEXT" name="bs_acc_psw" size="45"	value="<%=account_BackstageVO.getBs_acc_psw()%>" /></td>
		
	</tr>
	<tr>
		<td>�q�l�l��:</td>
		<td><input type="TEXT" name="email" size="45"	value="<%=account_BackstageVO.getEmail()%>" /></td>
		
	</tr>
	<tr><!--�h�X��-->
		<td>�p���q��:</td>
		<td><input type="TEXT" name="tel" size="45"	value="<%=account_BackstageVO.getTel()%> " /></td>
		
	</tr>
	<tr><!--�h�X��-->
		<td>�W���b�u�ɶ�:<font color=red><b>*</b></font></td>
		<td><input name="last_online_time" id="f_date1" type="text" size="45" readonly="readonly" style="background-color:pink;"></td>
<%-- 		<td><%=account_BackstageVO.getLast_online_time()%></td> ??????�����~����--%>
	</tr>
	<tr><!--�h�X��-->
		<td>�b�����A:</td>
		<td><input type="TEXT" name="state" size="45"	value="<%=account_BackstageVO.getState()%>" /></td>
		
	</tr>
	
	<tr><!--�h�X��  -->
		<td>�����v��:</td>
		<td><input type="checkbox" name="PERMISSION_NO" size="45" class="1" />���|�޲z

<%-- 			 value="<c:if test="(list.get(0).getPermission_no()==1)">  --%>
<!-- 			 		checked -->
<%-- 			 		</c:if>" --%>
<!-- 			 		/>���|�޲z -->
			 
		<input type="checkbox" name="PERMISSION_NO" size="45"
<%-- 			 value="<%= (role_Permission_BsVO.getPermission_no()==null)? "2" : "checked"%>" />�|���b���޲z --%>
			 value="${role_Permission_BsVO.permission_no}" ${(role_Permission_BsVO.permission_no==2)?'checked':'' } />�|���b���޲z
			 
		<input type="checkbox" name="PERMISSION_NO" size="45"
			 value="<%= (role_Permission_BsVO==null)? "3" : role_Permission_BsVO.getPermission_no()%>" />��x�b���޲z
			 
		<input type="checkbox" name="PERMISSION_NO" size="45"
			 value="<%= (role_Permission_BsVO==null)? "4" : role_Permission_BsVO.getPermission_no()%>" />��x����޲z
		<br>	 
		<input type="checkbox" name="PERMISSION_NO" size="45"
			 value="<%= (role_Permission_BsVO==null)? "5" : role_Permission_BsVO.getPermission_no()%>" />�q�v�|�޲z
			 
		<input type="checkbox" name="PERMISSION_NO" size="45"
			 value="<%= (role_Permission_BsVO==null)? "6" : role_Permission_BsVO.getPermission_no()%>" />�q�v��T�޲z
			 
		<input type="checkbox" name="PERMISSION_NO" size="45"
			 value="<%= (role_Permission_BsVO==null)? "7" : role_Permission_BsVO.getPermission_no()%>" />�q���t�κ޲z
			 
		<input type="checkbox" name="PERMISSION_NO" size="45"
			 value="<%= (role_Permission_BsVO==null)? "8" : role_Permission_BsVO.getPermission_no()%>" />���ʤ��i�޲z
		<br>	 
		<input type="checkbox" name="PERMISSION_NO" size="45"
			 value="<%= (role_Permission_BsVO==null)? "9" : role_Permission_BsVO.getPermission_no()%>" />�ȪA�t�κ޲z</td>
	</tr>

<%-- 	<jsp:useBean id="backstage_RoleService" scope="page" class="com.backstage_role.model.Backstage_RoleService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>����:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="backstage_RoleVO" items="${backstage_RoleService.all}"> --%>
<%-- 				<option value="${backstage_RoleVO.role_no}" ${(account_BackstageVO.bs_acc_no==backstage_RoleVO.role_no)? 'selected':'' } >${backstage_RoleVO.role_no} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select> -->
<!-- 		</td> -->
<!-- 	</tr> -->

</table>
		</div>
<script>
var checkboxs= document.getElementsByName("PERMISSION_NO");





// for(int i=0;i<list.size();i++){
// var num=(list.get(i));
// 		if(num-1==checkboxs){
// 			checked=true;
// 		}
// }


	


</script>
		<footer class="w3-container w3-blue">
		      <h5>�p������ðݡA�w��P�ڭ̳sô  24�p�ɪA�ȱM�u 0800-30-1313 02-2182-1313</h5>
		    </footer>
		</div>
	</div>
</div>

<br>
<input type="hidden" name="action" value="insert">
<input type="hidden" name="bs_acc_no" value="<%=account_BackstageVO.getBs_acc_no()%>">
<input type="submit" value="�e�X�ק�" class="btn btn-success">
<input type="hidden" name="role_no" value="R01" />
</center>
</FORM>

</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<% 
  java.sql.Timestamp last_online_time = null;
  try {
	  last_online_time = account_BackstageVO.getLast_online_time();
   } catch (Exception e) {
	   last_online_time = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>
<%=last_online_time%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.datetimepicker.full.js"></script>

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
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=last_online_time%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
        
        
   
        // ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

        //      1.�H�U���Y�@�Ѥ��e������L�k���
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

        
        //      2.�H�U���Y�@�Ѥ��᪺����L�k���
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


        //      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
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