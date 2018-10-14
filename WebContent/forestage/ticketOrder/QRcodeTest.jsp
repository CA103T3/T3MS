<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
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
</style>

</head>
<body class="body-template">
	<%@ include file="/forestage/ticketOrder/genQRcode.jsp" %>
	<div class="container" style="color: #ffffff; font-size: 20px;">
		<!-- ==========================start============================= -->

		<%
			//設定 QRCode 圖片要顯示的文字內容
			String uuid = "77B48BFC6B578B48E05011AC02002272";
			//用不會重複的 UUID 當作圖片檔名
			String TempFileName = java.util.UUID.randomUUID().toString() + ".jpg";
			//指定圖片存檔路徑， request.getServletContext().getRealPath 是伺服器端網站架設的最上層路徑
			String TempFilePath = request.getServletContext().getRealPath("/img/QRcode/");
			
			out.println(TempFilePath);
			//在 JSP 伺服器端產生一個長寬都是140的圖檔, genQRCode 函式內容在本文後面
			String TempResult = genQRCode(140, 140, uuid, TempFilePath + TempFileName);
			if (TempResult.equals("")) {//成功
				out.print("<img src='"+ request.getContextPath()+"/img/QRcode/" + TempFileName + "'>");//將 QRCode 秀在網頁上
			} else {//失敗
				out.print(TempResult);//顯示錯誤訊息
			}
		%>

		<!-- ==========================End============================= -->
	</div>
<%-- 	<%@ include file="/forestage/template/footer.jsp"%> --%>
	<script src="<%=request.getContextPath()%>/js/template.js"></script>
	<script>
		$(document).ready(function() {
			$("li:contains('合作影城')").addClass("custom-active");
		});
	</script>
</body>
</html>