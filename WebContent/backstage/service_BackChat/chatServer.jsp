<%@page import="com.member.model.MemVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>M&amp;S</title>
    <%@ include file="/backstage/template/link.jsp" %>
    <style type="text/css">
    </style>
</head>
 <%
    	String memid = "M0001";
//     	MemVO memVO = (MemVO) session.getAttribute("memVO");
//     	String mem_no = memVO.getmemno();
    	
    %>
<body onload="connect();" class="fs16">
    <%@ include file="/backstage/template/header.jsp" %>
    <div id="wrapper" class="mt50">
        <%@ include file="/backstage/template/sidebar.jsp" %>
        <div class="flex-column" id="page-content-wrapper">
            <div class="container-fluid mt20">
             <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title text-center">Service Chat</h3>
                </div>
                
                <div class="panel-body" id="content"></div>
            </div>
            <hr/>
            <input type="text" class="form-control" placeholder="message" aria-describedby="sizing-addon1" id="msg">
            <hr/>
            <button type="button" class="btn btn-lg btn-success btn-block" onclick="emit()">發送</button>
        </div>

<script type="text/javascript">
    	$(document).ready(function(){
    		$("#msg").focus();
    	});
</script>
 <script type="text/javascript">
		 	var MyPoint = "/FriendWS/Service";
			var host = window.location.host;
			var path = window.location.pathname;
			var webCtx = path.substring(0, path.indexOf('/', 1)); 
			var endPointURL = "ws://" + host + webCtx + MyPoint;
			var webSocket;
			
			function connect() {
				 webSocket = new WebSocket(endPointURL);
				
				 webSocket.onopen = function() {
					 $("#content").append("<kbd>Welcome!</kbd></br>");
				 };
				
			     webSocket.onmessage = function(evt) {
			         var data = JSON.parse(evt.data);
			         console.log("sender="+data.sender);
			         console.log("receiver="+data.receiver);
			         console.log("data.message="+data.message);
			         if(data.sender != undefined){
			        	 $("#content").append("<kbd style='color: #fff;font-size:16px ;margin-top: 10px;'>" + data.sender + " Say: " + data.message + "</kbd></br>");
			         }
			         console.log("DDD");
// 			         /* $("#content").append("<kbd style='color: #" + data.color + ";font-size: " + data.fontSize + ";margin-top: 10px;'>" + data.sender + " Say: " + data.message + "</kbd></br>"); */
			     };
			     
			     
			     webSocket.onclose = function(evt) {
			         $("#content").append("<kbd>" + "Close!" + "</kbd></br>");
			     }
			     webSocket.onerror = function(evt) {
			         $("#content").append("<kbd>" + "ERROR!" + "</kbd></br>");
			     }
			 }
			 document.onkeydown = function(event){
			     var e = event || window.event || arguments.callee.caller.arguments[0];
			     if(e && e.keyCode == 13){
			         emit();
			     }
	 		 }; 	
			
			
		 function emit() {
// 			 var text = $("#msg").val();
		     var text = encodeScript($("#msg").val());
		     var msg = {
		    		 "type":"XhistoryX",
		    		 "sender":"Service", 
		    		 "receiver":"M0001",
		    		 "message":text
		     };
// 		     var msg = {
// 		         "message" : text,
// 		         "color" : "#CECECE",
// 		         "bubbleColor" : "#2E2E2E",
// 		         "fontSize" : "16"
// 		     };
		     msg = JSON.stringify(msg);
		     //向server發送訊息
		     webSocket.send(msg);
		     $("#content").append("<kbd style='color: #" + "CECECE" + ";float:right; font-size: " + 12 + ";'>" + text +  "</kbd><br/>");
		     $("#msg").val("");
		 }
		 
		 function encodeScript(data) {
			    if(null == data || "" == data) {
			        return "";
			    }
			    return data.replace("<", "&lt;").replace(">", "&gt;");
			}
</script>
            </div>
        </div>
   
    <script src="<%=request.getContextPath()+"/js/back_index.js"%>"></script>
</body>
</html>