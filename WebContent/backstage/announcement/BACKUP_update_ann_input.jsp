<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.announcement.model.*"%>

<%
	AnnouncementVO annVO = (AnnouncementVO) request.getAttribute("annVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料修改 - update_emp_input.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
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

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backstage/announcement/ann.do" name="form1">
		<table>
			<tr>
				<td>公告內容:</td>
				<td>
					<textarea class="form-control" name="deblock_udid" rows="16" style="resize: none">
					<%=annVO.getAnc_con()%>
					</textarea>
				</td>
			</tr>
			<tr>
				<td>
					公告狀態:
					<font color=red>
						<b>*</b>
					</font>
				</td>
				<td>
					<c:choose>
						<c:when test="<%=annVO.getActive() == 0%>">
							<label>
								<input name="active" type="radio" value="0" checked="checked">
								下線
							</label>
							<label>
								<input name="active" type="radio" value="1">
								上線
							</label>
						</c:when>
						<c:otherwise>
							<label>
								<input name="active" type="radio" value="0">
								下線
							</label>
							<label>
								<input name="active" type="radio" value="1" checked="checked">
								上線
							</label>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>

		</table>
		<br>
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="anc_no" value="<%=annVO.getAnc_no()%>">
		<input type="hidden" name="anc_con" value="<%=annVO.getAnc_con()%>">
		<input type="hidden" name="active" value="<%=annVO.getActive()%>">
		<input type="hidden" name="requestURL" value="<%=request.getAttribute("requestURL")%>">
		<!--原送出修改的來源網頁路徑,從request取出後,再送給Controller準備轉交之用-->
		<input type="hidden" name="whichPage" value="<%=request.getAttribute("whichPage")%>">
		<!--只用於:istAllEmp.jsp-->
		<input type="hidden" name="radioStatus" value=radioValue>
		<input type="submit" value="送出修改">
	</FORM>

	<br>
	送出修改的來源網頁路徑:
	<br>
	<b>
		<font color=blue>request.getAttribute("requestURL"):</font>
		<%=request.getAttribute("requestURL")%><br>
		<font color=blue>request.getAttribute("whichPage"): </font>
		<%=request.getAttribute("whichPage")%>
		(此範例目前只用於:istAllEmp.jsp))
	</b>
</body>

<script>
<%-- $('input[name="active"]')[<%=annVO.getActive()%>].checked=true;
var radioValue = $('input[name="active"]:checked').val(); --%>

/* return radio choose is value */
/*  var amount = getRadioButtionChecked("radio");
 console.log("amount=="+amount); */
 
/* function getRadioButtonCheckedValue(tagNameAttr){
	var radio_tag=document.getElementsByName(tagNameAttr);
	for(var i=0;i<radio_tag.length;i++){
		if(radio_tag[i].checked){
			var checkvalue=radio_tag[i].value;
			return checkvalue;
		}
	}
} */
</script>

</html>