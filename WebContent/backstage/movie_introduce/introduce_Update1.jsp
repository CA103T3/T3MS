<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.movie_introduce.model.*"%>
<%@ page import="com.movie.model.*"%>

<%
  Movie_IntroduceVO movie_introduceVO = (Movie_IntroduceVO) request.getAttribute("movie_introduceVO"); //Movie_IntroduceServlet.java (Concroller) 存入req的movie_introduceVO物件 (包括幫忙取出的movie_introduceVO, 也包括輸入資料錯誤時的movie_introduceVO物件)
  MovieService mSvc = new MovieService();
  List<MovieVO> mList = mSvc.getAll();
  pageContext.setAttribute("mList",mList);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>M&amp;S</title>
<%@ include file="/backstage/template/link.jsp"%>

<!-- movie_back_movie CSS -->
<!-- <link href="<%=request.getContextPath()%>/css/movie_back_movie.css" rel="stylesheet" type="text/css"> -->
<!-- summernote CSS -->
<!-- <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css" rel="stylesheet"> -->
<!-- <link href="<%=request.getContextPath()%>/css/summernote.css" rel="stylesheet"> -->
<style type="text/css">

textarea{
resize: none;
}
#upbtn{
margin-top: 10px;
}
</style>
</head>

<body class="fs16" ata-spy="scroll">
    <%@ include file="/backstage/template/header.jsp"%>
    <div id="wrapper" class="mt50">
        <%@ include file="/backstage/template/sidebar.jsp"%>
        <div class="flex-column" id="page-content-wrapper">



            <!-- movie_introduce_Update -->

                <div class="section">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-10">
                                <div class="page-header text-warning">
                                    <h1>電影情報修改
                                         <font color="#777777"> 
                                            <span style="font-size: 23.4px; line-height: 23.4px;">Introduce Update</span>                                               
                                        </font>
                                    </h1>
                                </div>

                                <%-- 錯誤表列 --%>
                                <c:if test="${not empty errorMsgs}">
                                    <font style="color: red">請修正以下錯誤:</font>
                                    <ul>
                                        <c:forEach var="message" items="${errorMsgs}">
                                            <li style="color: red">${message}</li>
                                        </c:forEach>
                                    </ul>
                                </c:if>


                                <form METHOD="post" ACTION="<%=request.getContextPath()%>/backstage/movie_introduce/movie_introduce.do" name="form1" enctype="multipart/form-data">
                                    <div class="form-group has-error">
                                        <label class="control-label">電影情報編號:*</label>
                                        <div>${param.introd_no}</div>
                                    </div>
                                                                        
<!--                                    <div class="form-group"> -->
<!--                                        <label class="control-label">電影編號:</label> <input -->
<%--                                            class="form-control" type="text" name="movie_no" value="<%=movie_introduceVO.getMovie_no()%>"> --%>
<!--                                    </div> -->
                                    
                                    <div class="form-group has-error">
                                        <label class="control-label">電影名稱:*</label>
                                        <select class="form-control" id="movie_no" name="movie_no">
                                        <c:forEach var="movieVO" items="${mList}" varStatus="s" begin="<%=0%>" end="<%=mList.size()%>">
                                             <c:if test="${movieVO.active==1}">
                                                <option value="${movieVO.movie_no}" ${(movieVO.movie_no==movie_introduceVO.movie_no)? 'selected': '' }>${movieVO.movie_name}</option>
                                             </c:if>
                                        </c:forEach>
                                     </select>
                                    </div>
                                                            
                                    <div class="form-group hidden-md hidden-sm has-feedback">
                                        <label class="control-label">來源:</label> <input
                                            class="form-control" type="text" name="source" value="<%=movie_introduceVO.getSource()%>">
                                    </div>
                                    
                                    <div class="form-group hidden-md hidden-sm has-feedback">
                                        <label class="control-label">網址：</label> <input
                                            class="form-control" type="text" name="url" value="<%=movie_introduceVO.getUrl()%>">
                                    </div>
                                    
                                    <div class="form-group hidden-md hidden-sm has-feedback">
                                        <label class="control-label">作者:</label> <input
                                            class="form-control" type="text" name="author" value="<%=movie_introduceVO.getAuthor()%>">
                                    </div>
                                    
                                    <div class="form-group hidden-md hidden-sm has-feedback">
                                        <label class="control-label">標題：</label> <input
                                            class="form-control" type="text" name="title" value="<%=movie_introduceVO.getTitle()%>">
                                    </div>
                                                                        
                                    <div class="form-group">
                                        <label class="control-label">簡介:</label>
                                        <textarea class="form-control" id="summernote" name="content" rows="20" ><%=movie_introduceVO.getContent()%></textarea>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label class="control-label">狀態:</label>
                                            <c:choose>
                                             <c:when test="<%=movie_introduceVO.getActive() == 0%>">
                                              <label><input name="active" type="radio" value="0"
                                               checked="checked">下線</label>
                                              <label><input name="active" type="radio" value="1">上線</label>
                                             </c:when>
                                             <c:otherwise>
                                              <label><input name="active" type="radio" value="0">下線</label>
                                              <label><input name="active" type="radio" value="1"
                                               checked="checked">上線</label>
                                             </c:otherwise>
                                           </c:choose>                                      
                                    </div>
                                    
                                    
                      
                                     <div class="form-group">
                                        <label class="col-md-10 control-label">圖片:</label>
                                     <div class="col-md-3" id="drop-container">
                                        <input class="" id="inputFile" type="file" data-img="dpimg" name="photo_path" value="" >
                                    </div>
                                    <div class="col-md-2">
                                    <label class="control-label">可拖曳圖片到左方區塊</label>
                                    </div>
                                    </div>
                                    <div class="col-md-10" id="img_div">
                                      <img src='<%= (movie_introduceVO==null) ? "" : movie_introduceVO.getPhoto_small() %>' style="max-height: 100%;">
                                    </div>
                                    
                                                                                
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="introd_no"  value="<%=movie_introduceVO.getIntrod_no()%>">
                                <input type="hidden" name="active" value="<%=movie_introduceVO.getActive()%>">
                                <input type="hidden" name="content">
                                
                            
                                <input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
                                <input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--只用於:movie_introduce_List.jsp-->
                                
                                <button id="upbtn" type="submit" class="btn btn-success btn-lg btn-block">送出修改</button>     
                                </form>


                            </div>
                        </div>
                    </div>
                </div>



                <!-- movie_introduce_Update -->
        </div>
    </div>
    
    <script src="<%=request.getContextPath() + "/js/back_index.js"%>"></script>
<!-- summernote JS -->  
<!--   <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script> -->
<script src="<%=request.getContextPath()%>/js/summernote.js"></script>
    
<script>
      $(document).ready(function () {
          $('#summernote').summernote({           
              tabsize: 2,
              height: 600,
              minHeight: 600, // set minimum height of editor
              maxHeight: 600,
              focus: true                  // set focus to editable area after initializing summe
          });
          
      });
 </script>
 
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
</script>
</body>


</html>