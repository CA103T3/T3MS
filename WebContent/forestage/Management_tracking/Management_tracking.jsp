<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
    <!-- Bootstrap CSS -->
<!--       <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous"> -->
<!--       <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">  -->
      <link rel='stylesheet' href='https://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css'>
      <meta http-equiv="X-UA-Compatible" content="IE=edge">

      <link rel="stylesheet" href="/css/base.css">
    <%@ include file="/forestage/template/link.jsp" %>
    <title>Management tracking movie</title>
  </head>
  
  <body onload="test()" class="body-template">
    <!-- <div class="container">
    	<div class="row">
    		<div class="col-12 col-md-12">
    		<img src="http://fakeimg.pl/3000x500/00CED1/FFF/?text=img+placeholder" class="img-fluid">
			</div>
		
			<div class="col-12 col-md-4">
    		<img src="http://fakeimg.pl/1200x1000/00CED1/FFF/?text=img+placeholder" class="img-fluid img-thumbnail" >
			</div>
		</div>
    		
    </div> -->
    <%@ include file="/forestage/template/header.jsp" %>
<div class="container">
  <div class="row">
    
  </div>
</div>
	<!-- <nav class="navbar navbar-expand-md bg-primary navbar-dark">
	    <div class="container">
	      <a class="navbar-brand" href="#">Navbar</a>
	      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbar2SupportedContent">
	        <span class="navbar-toggler-icon"></span>
	      </button>
	      <div class="collapse navbar-collapse text-center justify-content-end" id="navbar2SupportedContent">
	        <ul class="navbar-nav">
	          <li class="nav-item">
	            <a class="nav-link" href="#">
	              <i class="fa d-inline fa-lg fa-bookmark-o"></i> Bookmarks</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link" href="#">
	              <i class="fa d-inline fa-lg fas fa-heart"></i> Contacts</a>
	          </li>
	        </ul>
	        <a class="btn navbar-btn btn-primary ml-2 text-white">
	          	<i class="fa d-inline fa-lg far fa-user-circle"></i> Sign in</a>
	      </div>

	    </div>

	</nav> -->
	

   <div class="py-5 text-center bg-secondary" >
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="row">
            <div class="col-md-12">
              <h1 class="text-light">Article header</h1>
              <p class="text-light"><span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quas dignissimos, aut dolorum aliquam minus rerum sint repellat? Similique reiciendis laboriosam veritatis dignissimos reprehenderit deleniti molestiae suscipit, quis officiis! Repudiandae, dolores.</span>
              <span>In quidem aliquid, iusto deserunt explicabo suscipit quo doloribus, dolor esse a aspernatur neque quasi provident temporibus ipsa tempora eos. Perferendis culpa autem sunt, harum maxime sint alias dolores vel.</span>
              <span>Incidunt magni fuga, eveniet libero, natus recusandae, alias, ea iusto harum odio nobis voluptatibus! Repudiandae ea dignissimos minus sequi voluptate corrupti fuga illum? Alias doloremque, qui sequi blanditiis odio! Odio.</span>
              <span>Dolore, vel eius repellat soluta, veritatis molestiae odit vero incidunt esse consequatur iure doloribus deleniti reprehenderit ducimus vitae provident temporibus tenetur impedit repudiandae aliquid perspiciatis optio et sapiente dolores. Aliquam.</span>
              <span>Error soluta quaerat delectus rerum similique, impedit distinctio a qui deleniti deserunt cupiditate, vel explicabo tempore dolor velit, officia architecto tenetur laborum nobis enim autem animi, ipsam harum unde assumenda!</span></p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="py-4">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h1 class="">追蹤電影</h1>
        </div>
      </div>
    </div>
  </div>


    <div class="py-1" >
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <table class="table">
            <thead>
              <tr>
                <th>#</th>
                <th>電影名稱</th>
                <th>上映日期</th>
                <th>片長時間</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td	>1</td>
                <td id="sok"><div><img src="img/images1.jpg"></div>Mark
                <table id ="czy">
                    <tr class="starRow">
                        <td class="star">★</td><td class="star">★</td><td class="star">★</td><td class="star">★</td><td class="star">★</td>
                    </tr>
                </table><input type="button" class="btnScore" value="打分"><span></span>分</td>
                <td>Otto</td>
                <td>PS</td>
              </tr>
              
              <tr>
                <td>2</td>
                <td><div><img src="img/images2.jpg"></div>Jacob
                <table id ="czy">
                    <tr class="starRow">
                        <td class="star">★</td><td class="star">★</td><td class="star">★</td><td class="star">★</td><td class="star">★</td>
                    </tr>
                </table><input type="button" class="btnScore" value="打分"><span> </span>分</td>
                <td>Thornton</td>
                <td>PS</td>
              </tr>
              
              <tr>
                <td>3</td>
                <td><div><img src="img/images3.jpg"></div>Larry
                <table id ="czy">
                    <tr class="starRow">
                        <td class="star">★</td><td class="star">★</td><td class="star">★</td><td class="star">★</td><td class="star">★</td>
                    </tr>
                </table><input type="button" class="btnScore" value="打分"><span></span>分</td>
                <td>the Bird</td>
                <td>PS</td>
              
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
<script>
    trs=document.getElementsByClassName("starRow");
    for(let i=0;i<trs.length;i++){
        trs[i].onmouseover=function(e){
            if( e.target.nodeName == "TD"){
                var tr = e.target.parentNode;
                var tds = tr.getElementsByTagName("td");
                for(let j=0;j<tds.length;j++){
                    if(tds[j]==e.target)
                    {
                        for( let k=0; k<=j; k++){
                          tds[k].style.color = "red";
                        }
                        
                        tr.dataset.score = j+1; 
                    }else{
                        tds[j].style.color = "black";
                    }
                }
                //选中的设置成红色 没选中的设置成黑色
             }
        };

        
    }

//............
  var  btnScores=document.getElementsByClassName("btnScore");
  for(let i=0;i<btnScores.length;i++){
    btnScores[i].onclick=function(e){
        let tr = e.target.previousSibling.getElementsByTagName("tr")[0];
        e.target.nextSibling.innerHTML = tr.dataset.score
    }
  }
</script>	

<div align="center">
			<nav aria-label="Page navigation example">
			  <ul class="pagination justify-content-center">
			    <li class="page-item">
			      <a class="page-link" href="#" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			        <span class="sr-only">Previous</span>
			      </a>
			    </li>
			    <li class="page-item"><a class="page-link" href="?pageNo=1">1</a></li>
			    <li class="page-item"><a class="page-link" href="?pageNo=2">2</a></li>
			    <li class="page-item"><a class="page-link" href="?pageNo=3">3</a></li>
			    <li class="page-item">
			      <a class="page-link" href="#" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			        <span class="sr-only">Next</span>
			      </a>
			    </li>
			  </ul>
			</nav>
</div >			

 
	
			


   <!--  <style type="text/css">
#P1{
    position:absolute;
    top:0px;
    left:0px;
    z-index:-1;
}
#P2{
    position:absolute;
    top:20px;
    left:20px;
    z-index:0;
}
#P3{
    position:absolute;
    top:40px;
    left:40px;
    z-index:1;
}
</style>
<div style="position:relative;">
<img src="第一張圖片" id="P1">
<img src="第二張圖片" id="P2">
<img src="第三張圖片" id="P3">
</div> -->

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->

    <%@ include file="/forestage/template/footer.jsp" %>
    <script src="<%=request.getContextPath()%>/js/template.js"></script>
        <script>
        $(document).ready(function(){
            $("li:contains('合作影城')").addClass("custom-active");
        });
        </script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  </body>
</html>