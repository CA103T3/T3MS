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

<body class="fs16">
    <%@ include file="/backstage/template/header.jsp" %>
    <div id="wrapper" class="mt50">
        <%@ include file="/backstage/template/sidebar.jsp" %>
        <div class="flex-column" id="page-content-wrapper">
            <div class="container-fluid mt20">
                <form class="form-horizontal">
                    <div class="form-group">
                      <label class="col-sm-5 control-label">影城</label>
                      <div class="col-sm-3">
                        <input class="form-control" id="" type="text" value="威秀影城" readonly>
                      </div>
                      <div class="col-sm-4">
                      </div> 
                    </div>
                    <div class="form-group">
                      <label class="col-sm-5 control-label">影廳名稱</label>
                      <div class="col-sm-3">
                        <input class="form-control" id="" type="text" value="" >
                      </div>
                      <div class="col-sm-4">
                      </div> 
                    </div>
                    <div class="form-group">
                      <label class="col-sm-5 control-label">影廳設備</label>
                      <div class="col-sm-3">
                        <select class="form-control">
                           <option value="數位">數位</option>
                           <option value="2D">2D</option>
                           <option value="3D">3D</option>
                           <option value="4DX">4DX</option>
                           <option value="4DX 3D">4DX 3D</option>
                           <option value="IMAX">IMAX</option>
                           <option value="IMAX 3D">IMAX 3D</option>
                           <option value="GC DIG">GC DIG</option>
                           <option value="GC 3D DIG">GC 3D DIG</option>
                        </select>
                      </div>
                      <div class="col-sm-4">
                      </div> 
                    </div>
                    <div class="form-group">
                      <label class="col-sm-5 control-label">排數</label>
                      <div class="col-sm-3">
                        <select class="form-control">
                           <option value="5">5</option>
                           <option value="6">6</option>
                           <option value="7">7</option>
                           <option value="8">8</option>
                           <option value="9">9</option>
                           <option value="10">10</option>
                           <option value="11">11</option>
                           <option value="12">12</option>
                           <option value="13">13</option>
                           <option value="14">14</option>
                           <option value="15">15</option>
                           <option value="16">16</option>
                           <option value="17">17</option>
                           <option value="18">18</option>
                           <option value="19">19</option>
                           <option value="20">20</option>
                           <option value="21">21</option>
                           <option value="22">22</option>
                           <option value="23">23</option>
                           <option value="24">24</option>
                           <option value="25">25</option>
                           <option value="26">26</option>
                        </select>
                      </div>
                      <div class="col-sm-4">
                      </div> 
                    </div>
                    <div class="form-group">
                      <label class="col-sm-5 control-label">行數</label>
                      <div class="col-sm-3">
                        <select class="form-control">
                           <option value="5">5</option>
                           <option value="6">6</option>
                           <option value="7">7</option>
                           <option value="8">8</option>
                           <option value="9">9</option>
                           <option value="10">10</option>
                           <option value="11">11</option>
                           <option value="12">12</option>
                           <option value="13">13</option>
                           <option value="14">14</option>
                           <option value="15">15</option>
                           <option value="16">16</option>
                           <option value="17">17</option>
                           <option value="18">18</option>
                           <option value="19">19</option>
                           <option value="20">20</option>
                        </select>
                      </div>
                      <div class="col-sm-4">
                      </div> 
                    </div>
                    <div class="">          
                      <table class="">
                        <thead>
                          <tr>
                            <th>#</th>
                            <th>Firstname</th>
                            <th>Lastname</th>
                            <th>Age</th>
                            <th>City</th>
                            <th>Country</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            <td>1</td>
                            <td>Anna</td>
                            <td>Pitt</td>
                            <td>35</td>
                            <td>New York</td>
                            <td>USA</td>
                          </tr>
                        </tbody>
                      </table>
                    </div>
                    <div class="form-group text-center">
                      <span class="label label-primary">1</span>
                      <button class="btn btn-warning btn-md" id="btn_1_1">1</button>
                      <button class="btn btn-success btn-md" id="btn_1_2">2</button>
                      <button class="btn btn-danger btn-md" id="btn_1_3">3</button>
                      <button class="btn btn-default btn-md" id="btn_1_4">4</button>
                    </div>
                    <div class="form-group text-center">
                      <button class="btn btn-warning btn-md" id="btn_1_1">1</button>
                      <button class="btn btn-success btn-md" id="btn_1_2">2</button>
                      <button class="btn btn-danger btn-md" id="btn_1_3">3</button>
                      <button class="btn btn-default btn-md" id="btn_1_4">4</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="<%=request.getContextPath()+"/js/back_index.js"%>"></script>
</body>
</html>