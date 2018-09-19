<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">

<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
<style>
.middle {
    .parent {
    display: table;
    table-layout: fixed;
}

.child {
    display:table-cell;
    vertical-align:middle;
    text-align:center;
}
}
</style>
</head>
<body>
<div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-right">
            <a href="fv_home.jsp" class="btn btn-primary">瀏覽影評</a>
          </div>
        </div>
      </div>
    </div>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <h1 class="text-center">搜尋</h1>
          </div>
        </div>
        <div class="row">
          <div class="col-md-offset-3 col-md-6">
            <form role="form">
              <div class="form-group">
                <div class="input-group">
                  <input type="text" class="form-control" placeholder="請輸入電影名稱">
                  <span class="input-group-btn">
                    <a class="btn btn-primary" type="submit">Go</a>
                  </span>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <a href="fv_writing.jsp" class="btn btn-block btn-lg btn-primary">寫影評</a>
          </div>
        </div>
      </div>
    </div>
    <div class="section">
      <div class="container">
        <div class="row">
          
          <div class="col-md-6">
            <p>標題<br><br></p>
          </div>
          <div class="col-md-1">
            <h3 class="text-center">日期</h3>
          </div>
          <div class="col-md-2">
            <h3 class="text-center">電影名稱</h3>
          </div>
          <div class="col-md-1">
            <h3 class="text-center">評分</h3>
          </div>
          <div class="col-md-1 text-center ">
            <a class="btn btn-primary " data-toggle="button">修改</a>
          </div>
          <div class="col-md-1 text-center">
            <a class="btn btn-primary">刪除</a>
          </div>
        </div>
      </div>
    </div><div class="section">
      <div class="container">
        <div class="row">
          
          <div class="col-md-6">
            <p>標題<br><br></p>
          </div>
          <div class="col-md-1">
            <h3 class="text-center">日期</h3>
          </div>
          <div class="col-md-2">
            <h3 class="text-center">電影名稱</h3>
          </div>
          <div class="col-md-1">
            <h3 class="text-center">評分</h3>
          </div>
          <div class="col-md-1 text-center">
            <a class="btn btn-primary" data-toggle="button">修改</a>
          </div>
          <div class="col-md-1 text-center">
            <a class="btn btn-primary">刪除</a>
          </div>
        </div>
      </div>
    </div>
    
    
    
    
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-center">
            <ul class="pagination pagination-sm">
              <li>
                <a href="#">Prev</a>
              </li>
              <li>
                <a href="#">1</a>
              </li>
              <li>
                <a href="#">2</a>
              </li>
              <li>
                <a href="#">3</a>
              </li>
              <li>
                <a href="#">4</a>
              </li>
              <li>
                <a href="#">5</a>
              </li>
              <li>
                <a href="#">Next</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  

</body>
</html>