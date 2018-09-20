<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.announcement.model.*"%>

<%
	AnnouncementVO annVO = (AnnouncementVO) request.getAttribute("annVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>���u��ƭק� - update_emp_input.jsp</title>

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

	<h3>��ƭק�:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backstage/announcement/ann.do" name="form1">
		<table>
			<tr>
				<td>���i���e:</td>
				<td>
					<textarea class="form-control" name="deblock_udid" rows="16" style="resize: none">
					<%=annVO.getAnc_con()%>
					</textarea>
				</td>
			</tr>
			<tr>
				<td>
					���i���A:
					<font color=red>
						<b>*</b>
					</font>
				</td>
				<td>
					<c:choose>
						<c:when test="<%=annVO.getActive() == 0%>">
							<label>
								<input name="active" type="radio" value="0" checked="checked">
								�U�u
							</label>
							<label>
								<input name="active" type="radio" value="1">
								�W�u
							</label>
						</c:when>
						<c:otherwise>
							<label>
								<input name="active" type="radio" value="0">
								�U�u
							</label>
							<label>
								<input name="active" type="radio" value="1" checked="checked">
								�W�u
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
		<!--��e�X�ק諸�ӷ��������|,�qrequest���X��,�A�e��Controller�ǳ���椧��-->
		<input type="hidden" name="whichPage" value="<%=request.getAttribute("whichPage")%>">
		<!--�u�Ω�:istAllEmp.jsp-->
		<input type="hidden" name="radioStatus" value=radioValue>
		<input type="submit" value="�e�X�ק�">
	</FORM>

	<br>
	�e�X�ק諸�ӷ��������|:
	<br>
	<b>
		<font color=blue>request.getAttribute("requestURL"):</font>
		<%=request.getAttribute("requestURL")%><br>
		<font color=blue>request.getAttribute("whichPage"): </font>
		<%=request.getAttribute("whichPage")%>
		(���d�ҥثe�u�Ω�:istAllEmp.jsp))
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