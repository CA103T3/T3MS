<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cinema.model.*"%>

<%
  CinemaVO cinemaVO = (CinemaVO) request.getAttribute("cinemaVO");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>M&amp;S</title>
    <%@ include file="/backstage/template/link.jsp" %>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/addCinema.css">
    <style type="text/css">
    </style>
</head>

<body class="fs16">
    <%@ include file="/backstage/template/header.jsp" %>
    <div id="wrapper" class="mt50">
        <%@ include file="/backstage/template/sidebar.jsp" %>
        <div class="flex-column" id="page-content-wrapper">
            <div class="container">
                <h3 class="page-header">
                    <label>修改影城&nbsp;&nbsp;</label>
                    <form id="fm-back" method="post" style="display:inline" action="<%=request.getContextPath()%>/backstage/cinema/listAllCinema.jsp">
                        <button type="submit" id="back-btn" class="btn btn-default fs16 " >
                            <i class="fa fa-undo" aria-hidden="true"></i>&nbsp;返回
                        </button>
                        <!-- <input type="hidden" name="cinema_no" value="${cinemaVO.cinema_no}"> -->
                        <!-- <input type="hidden" name="requestURL" value="<%=request.getServletPath()+"?"+request.getQueryString()%>"> -->
                        <input type="hidden" name="whichRecordIndex" value="${param.whichRecordIndex}">
                        <!-- <input type="hidden" name="action" value="view"> -->
                    </form>
                </h3>
                <form class="form-horizontal" method="post" action="<%=request.getContextPath()%>/cinema/cinema.do" enctype="multipart/form-data">
                    <div class="form-group">
                      <label class="col-md-5 control-label">影城名稱</label>
                      <div class="col-md-3">
                        <input class="form-control" id="" type="text" name="cinema_name" value="<%= (cinemaVO==null) ? "" : cinemaVO.getCinema_name() %>" >
                      </div>
                      <div class="col-md-4">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">影城英文名稱</label>
                      <div class="col-md-3">
                        <input class="form-control" id="" type="text" name="cinema_engname" value="<%= (cinemaVO==null) ? "" : cinemaVO.getCinema_engname() %>" >
                      </div>
                      <div class="col-md-4">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">影城地址</label>
                      <div class="col-md-3">
                        <input class="form-control" id="" type="text" name="cinema_address" value="<%= (cinemaVO==null) ? "" : cinemaVO.getCinema_address() %>" >
                      </div>
                      <div class="col-md-4">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">服務專線</label>
                      <div class="col-md-3">
                        <input class="form-control" id="" type="text" name="cinema_tel" value="<%= (cinemaVO==null) ? "" : cinemaVO.getCinema_tel() %>" >
                      </div>
                      <div class="col-md-4">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">圖片名稱</label>
                      <div class="col-md-3">
                        <input class="form-control" id="" type="text" name="photo_title" value="<%= (cinemaVO==null) ? "" : cinemaVO.getPhoto_title() %>" >
                      </div>
                      <div class="col-md-4">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">圖片</label>
                      <div class="col-md-3" id="drop-container">
                        <input class="" id="inputFile" type="file" data-img="dpimg" name="photo_path" value="" >
                      </div>
                      <div class="col-md-4">
                        <label class="control-label">可拖曳圖片到左方區塊</label>
                      </div>
                    </div>
                    <div class="row container text-center" id="img_div">
                      <img src='<%= (cinemaVO==null) ? "" : cinemaVO.getPhoto_small() %>' alt='<%= (cinemaVO==null) ? "" : cinemaVO.getPhoto_title() %>' style="max-height: 100%;">
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">上線</label>
                      <div class="col-md-3">
                        <input type="checkbox" name="active" class="toggleswitch" <%= (cinemaVO==null) ? "" : ((cinemaVO.getActive()==1) ? "checked" : "") %> >
                      </div>
                      <div class="col-md-4">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-5 control-label">合作狀態</label>
                      <div class="col-md-3">
                        <input type="checkbox" name="state" class="toggleswitch" <%= (cinemaVO==null) ? "" : ((cinemaVO.getState()==1) ? "checked" : "") %>>
                      </div>
                      <div class="col-md-4">
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="traffic">交通資訊</label>
                      <textarea class="form-control" id="traffic" name="traffic" rows="6"><%= (cinemaVO==null) ? "" : cinemaVO.getTraffic() %></textarea>
                    </div>
                    <div class="form-group">
                      <label for="introduction">影城介紹</label>
                      <textarea class="form-control" id="introduction" name="introduction" rows="10"><%= (cinemaVO==null) ? "" : cinemaVO.getIntroduction() %></textarea>
                    </div>
                    <div class="form-group text-center">
                      <input type="hidden" name="action" value="update">
                      <input type="hidden" name="cinema_no" value="${cinemaVO.cinema_no}">
                      <input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
                      <input type="hidden" name="whichRecordIndex" value="${param.whichRecordIndex}">
                      <button class="btn btn-primary fs16" id="smtbtn" type="submit" ><i class="fa fa-paper-plane" aria-hidden="true"></i>&nbsp;送出</button>
                    </div>
                </form>

            </div>
        </div>
    </div>

    <script src="<%=request.getContextPath()+"/js/back_index.js"%>"></script>
    <script type="text/javascript">
      function preview_image(e) {
          /*
          //http://talkerscode.com/webtricks/preview-image-before-upload-using-javascript.php
          var reader = new FileReader();
          reader.onload = function() {
              var output = document.getElementById('output_image');
              output.src = reader.result;
          }
          reader.readAsDataURL(event.target.files[0]);
          */
          console.log("preview_image");

          $("#img_div").empty();

          //https://stackoverflow.com/questions/7394750/adding-event-as-parameter-within-function-using-addeventlistener-doesnt-work
          if (!e) // i.e. the argument is undefined or null
              e = window.event;

          // cross browser
          var obj = e.target ? e.target : event.srcElement;
          //console.log(e);
          //console.log(e.target);
          //console.log(obj);
          //console.log(event.srcElement);
          //console.log(event.srcElement.id);
          //console.log(obj.id);
          img_id = obj.getAttribute("data-img");
          img = document.getElementById(img_id);
          var found;
          if (img) {
              console.log("found");
              found = true;
          } else {
              console.log("not found");
              var img = document.createElement("img");
              img.setAttribute("style", "max-height: 100%;");
              img.id = obj.getAttribute("data-img");
              found = false;
          }
          /*
          var reader = new FileReader();
          reader.onload = function() {
              img.src = reader.result;
              img.id = event.target.getAttribute("data-index");
              console.log(img.id);
          }
          reader.readAsDataURL(event.target.files[0]);

          if(!found) {
              var idiv = document.getElementById("img_div");
              idiv.appendChild(img);
              console.log("appendChild");
          }
          */
          var ver = getInternetExplorerVersion();
          if (ver > -1 && ver <= 9) {
              //img.src = obj.files[0];
              img.src = obj.value;
              console.log("old : " + ver);
          } else {
              console.log("new : " + ver);
              var reader = new FileReader();
              //https://developer.mozilla.org/zh-TW/docs/Web/API/FileReader
              //FileReader.onload
              //load 事件處理器，於讀取完成時觸發。
              reader.onload = function() {
                  //https://developer.mozilla.org/zh-TW/docs/Web/API/FileReader
                  //FileReader.result Read only
                  //讀入的資料內容。只有在讀取完成之後此屬性才有效，而資料的格式則取決於是由哪一個方法進行讀取。
                  img.src = reader.result;
                  img.id = obj.getAttribute("data-img");
                  console.log(img.id);
              }
              console.log("obj.files[0].name: " + obj.files[0].name);
              let filename = obj.files[0].name;
              //console.log("obj.get(0).files: " + obj.get(0).files);
              // Read in the image file as a data URL.
              //reader.readAsDataURL(obj.files[0]);

              //https://developer.mozilla.org/zh-TW/docs/Web/API/File/Using_files_from_web_applications
              let imageType = /image.*/;
              if (obj.files[0].type.match(imageType)) {
              //if (obj.get(0).files.type.match(imageType)) {
                  reader.readAsDataURL(obj.files[0]);
                  $("#img_div").css("display", "block");
              } else {

                  console.log(filename, " not image file");
                  //alert("not image file");
                  $.alert({
                      title: '請選擇圖檔',
                      content: filename + ' 非圖檔!',
                  });
                  $("#img_div").empty();
                  $("#img_div").css("display", "none");
                  //$("#inputFile").val("");
                  setTimeout(function(){ $("#inputFile").val(""); }, 100);

              }
          }

          if (!found) {
              var idiv = document.getElementById("img_div");
              idiv.appendChild(img);
              console.log("appendChild");
          }
      }

      //https://msdn.microsoft.com/en-us/library/cc817582.aspx
      function getInternetExplorerVersion()
      // Returns the version of Windows Internet Explorer or a -1
      // (indicating the use of another browser).
      {
          var rv = -1; // Return value assumes failure.
          if (navigator.appName == 'Microsoft Internet Explorer') {
              var ua = navigator.userAgent;
              var re = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
              if (re.exec(ua) != null) {
                  rv = parseFloat(RegExp.$1);
                  console.log(RegExp.$1);
              }
          }
          return rv;
      }

      $(document).ready(function(){
        //show errorMsgs
        <c:if test="${not empty errorMsgs}">
          <c:forEach var="message" items="${errorMsgs}">
            toastr.error("${message}");
          </c:forEach>
        </c:if>

        let fileInput = document.getElementById("inputFile");
        fileInput.addEventListener("change", function (event) {preview_image(event);}, false);

        //https://stackoverflow.com/questions/47515232/how-to-set-file-input-value-when-dropping-file-on-page
        //https://developer.mozilla.org/zh-TW/docs/Web/API/File/Using_files_from_web_applications
        //https://phppot.com/jquery/responsive-image-upload-using-jquery-drag-and-drop/
        //let target = document.documentElement;
        let target = document.getElementById("drop-container");
        let body = document.body;

        target.addEventListener('dragenter', function(e) {
            e.stopPropagation();
            e.preventDefault();
            console.log("dragenter");
        });

        target.addEventListener('dragover', function(e) {
            e.stopPropagation();
            e.preventDefault();
            console.log("dragover");
        });

        target.addEventListener('drop', function(e) {
            e.stopPropagation();
            e.preventDefault();
            console.log("drop");
            fileInput.files = e.dataTransfer.files;

            //https://stackoverflow.com/questions/9847580/how-to-detect-safari-chrome-ie-firefox-and-opera-browser/9851769#
            // Chrome 1+
            let isChrome = !!window.chrome && !!window.chrome.webstore;
            // Opera 8.0+
            let isOpera = (!!window.opr && !!opr.addons) || !!window.opera || navigator.userAgent.indexOf(' OPR/') >= 0;

            if(!isChrome && !isOpera) {
                //not work
                //$("#inputFile").change();

                //https://stackoverflow.com/questions/2856513/how-can-i-trigger-an-onchange-event-manually
                //let element = document.getElementById("inputFile");
                if ("createEvent" in document) {
                    var evt = document.createEvent("HTMLEvents");
                    evt.initEvent("change", false, true);
                    //fileInput.dispatchEvent(evt);
                    setTimeout(function(){ fileInput.dispatchEvent(evt); }, 100);
                    console.log("fileInput.dispatchEvent");
                } else {
                    fileInput.fireEvent("onchange");
                    console.log("fileInput.fireEvent");
                }
            }
        });

        $('.toggleswitch').bootstrapToggle({
            on: '是',
            off: '否'
        });

        //display #img_div
        $("#img_div").css("display", "block");
      });
    </script>
</body>
</html>