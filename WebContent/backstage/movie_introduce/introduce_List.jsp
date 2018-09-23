<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>M&amp;S</title>
<%@ include file="/backstage/template/link.jsp"%>

    <!--DateTable css-->    
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">
   
<style type="text/css">

#btn6 {
    background-color: #00FFFF; /* Green */
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
    position: absolute;
    top: 45px;
    right: 15px;

}


#btn6:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);
}

</style>
</head>

<body class="fs16">
	<%@ include file="/backstage/template/header.jsp"%>
	<div id="wrapper" class="mt50">
		<%@ include file="/backstage/template/sidebar.jsp"%>
		<div class="flex-column" id="page-content-wrapper">

			<!-- introduce_List -->
			
				<div class="section">
				<div class="container">
					<div class="row">
						<div class=" col-md-12 text-left">
							<div class="page-header">
								<h1>
									電影介紹管理 <font color="#444444"> <span
										style="font-size: 23.4px; line-height: 23.4px;">
										Movie Introduction	&nbsp;</span>											
												<a href="movie_Add.jsp" target="_blank">
												<button id="btn6">新增電影</button>
												</a>
									</font>
								</h1>
							</div>
						</div>
					</div>
					
				</div>
			</div>

			<div class="container">
				<div class="row">
					<div class="col-10">
						<table id="example" class="table table-striped table-bordered"
							style="width: 100%">
							<thead>
								<tr>
									<th>介紹編號:</th>
									<th>電影編號:</th>
									<th>網址:</th>
									<th>是否上線</th>
									<th>查詢:</th>
									<th>修改:</th>
									<th>刪除:</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>MID0001</td>
									<td>M0001</td>
									<td>WWW.EZ.COM</td>
									<td>1</td>
									<td><button type="button" class="btn btn-info">查詢</button></td>
									<td><button type="button" class="btn btn-success">修改</button></td>
									<td><button type="button" class="btn btn-danger">刪除</button></td>
								</tr>
						</table>
					</div>
				</div>
			</div>

			<!-- introduce_List -->
		</div>
	</div>
	
<!-- script -->	
<script src="<%=request.getContextPath() + "/js/back_index.js"%>"></script>

<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
<script>
    $(document).ready(function() {
    $('#example').DataTable();
} );
</script>
 <script type="text/javascript">
    $(document).ready(function() {
        $('#theaters').DataTable({
          "language": {
            "url": "<%=request.getContextPath()%>/resources/Chinese-traditional.json"
          }
        });
    } );
    </script>
<!-- script -->	
</body>

</html>