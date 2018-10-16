<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.movie.model.*" %>


<% 
	MovieService movieservice=new MovieService();
	List<MovieVO> list=movieservice.getAll();
	pageContext.setAttribute("list",list);


%>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <link rel='stylesheet' href='https://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css'>
      <meta http-equiv="X-UA-Compatible" content="IE=edge">

      <link rel="stylesheet" href="/T3MS/css/style_2.css">
    <%@ include file="/forestage/template/link.jsp" %>
    <title>Management tracking movie</title>
  </head>
  
  <body onload="test()" class="body-template">
 
    <%@ include file="/forestage/template/header.jsp" %>
<div class="container">
  <div class="row">
    
  </div>
</div>
	
<%@ include file="/resources/page_code/pagef.file"%>	
<section id="slideshow">
			<div class="entire-content">
				<div class="content-carrousel">
					<figure class="shadow"><img src="https://images.pexels.com/photos/758733/pexels-photo-758733.jpeg?w=940&h=650&auto=compress&cs=tinysrgb"/></figure>
					<figure class="shadow"><img src="https://images.pexels.com/photos/21261/pexels-photo.jpg?w=940&h=650&auto=compress&cs=tinysrgb"/></figure>
					<figure class="shadow"><img src="https://images.pexels.com/photos/567973/pexels-photo-567973.jpeg?w=940&h=650&auto=compress&cs=tinysrgb"/></figure>
					<figure class="shadow"><img src="https://images.pexels.com/photos/776653/pexels-photo-776653.jpeg?w=940&h=650&auto=compress&cs=tinysrgb"/></figure>
					<figure class="shadow"><img src="https://images.pexels.com/photos/54630/japanese-cherry-trees-flowers-spring-japanese-flowering-cherry-54630.jpeg?w=940&h=650&auto=compress&cs=tinysrgb"/></figure>
					<figure class="shadow"><img src="https://images.pexels.com/photos/131046/pexels-photo-131046.jpeg?w=940&h=650&auto=compress&cs=tinysrgb"/></figure>
					<figure class="shadow"><img src="https://images.pexels.com/photos/302515/pexels-photo-302515.jpeg?w=940&h=650&auto=compress&cs=tinysrgb"/></figure>
					<figure class="shadow"><img src="https://images.pexels.com/photos/301682/pexels-photo-301682.jpeg?w=940&h=650&auto=compress&cs=tinysrgb"/></figure>
					<figure class="shadow"><img src="https://images.pexels.com/photos/933054/pexels-photo-933054.jpeg?w=940&h=650&auto=compress&cs=tinysrgb"/></figure>
		</div>
	</div>
</section>

   <div class="py-5 text-center bg-secondary" >
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="row">
            <div class="col-md-12">
              <h1 class="text-light" style="color:red">追蹤電影</h1>
              <p class="text-light"><span style="font-size:20px;">看電影是一種品味  也是一種不可磨滅的習慣 讓電影走入您的生活中 . . .每一天 有電影看真好 !</span>
<!--               <span>In quidem aliquid, iusto deserunt explicabo suscipit quo doloribus, dolor esse a aspernatur neque quasi provident temporibus ipsa tempora eos. Perferendis culpa autem sunt, harum maxime sint alias dolores vel.</span> -->
<!--               <span>Incidunt magni fuga, eveniet libero, natus recusandae, alias, ea iusto harum odio nobis voluptatibus! Repudiandae ea dignissimos minus sequi voluptate corrupti fuga illum? Alias doloremque, qui sequi blanditiis odio! Odio.</span> -->
<!--               <span>Dolore, vel eius repellat soluta, veritatis molestiae odit vero incidunt esse consequatur iure doloribus deleniti reprehenderit ducimus vitae provident temporibus tenetur impedit repudiandae aliquid perspiciatis optio et sapiente dolores. Aliquam.</span> -->
<!--               <span>Error soluta quaerat delectus rerum similique, impedit distinctio a qui deleniti deserunt cupiditate, vel explicabo tempore dolor velit, officia architecto tenetur laborum nobis enim autem animi, ipsam harum unde assumenda!</span></p> -->
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
          <h1 class=""></h1>
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
                <th><font color="red">NO.</font></th>
                <th style="color:red">電影名稱</th>
                <th style="color:red">上映日期</th>
                <th style="color:red">片長時間</th>
                <th style="color:red">語言</th>
                <th style="color:red">IMDB評分</th>
                <th style="color:red">爛番茄評分</th>
                
              </tr>
            </thead>
            <tbody>
              <tr>
                <td><font color="#e6e6e6">1</font></td>
                <td id="sok"><div><img src="/T3MS/<%= list.get(0).getMovie_pic()%>"></div><font color="#e6e6e6"><%= list.get(0).getMovie_name() %></font>
                <table id ="czy">
                    <tr class="starRow">
                        <td class="star">★</td><td class="star">★</td><td class="star">★</td><td class="star">★</td><td class="star">★</td>
                    </tr>
                </table><input type="button" class="btnScore" value="想看度"><span style="color:red;font-weight:bold"></span><font color="#ff6600">分</font></td>
                <td><font color="#e6e6e6"><%= list.get(0).getRelased()%></font></td>
                <td><font color="#e6e6e6"><%= list.get(0).getLength()%></font></td>
                <td><font color="#e6e6e6"><%= list.get(0).getLanguage()%></font></td>
                <td><font color="#e6e6e6"><%= list.get(0).getImdb()%></font></td>
                <td><font color="#e6e6e6"><%= list.get(0).getTomato()%></font></td>
              </tr>
              
              <tr>
                <td><font color="#e6e6e6">2</font></td>
                <td><div><img src="/T3MS/<%= list.get(1).getMovie_pic()%>"></div><font color="#e6e6e6"><%= list.get(1).getMovie_name() %></font>
                <table id ="czy">
                    <tr class="starRow">
                        <td class="star">★</td><td class="star">★</td><td class="star">★</td><td class="star">★</td><td class="star">★</td>
                    </tr>
                </table><input type="button" class="btnScore" value="想看度"><span style="color:red;font-weight:bold"></span><font color="#ff6600">分</font></td>
                <td><font color="#e6e6e6"><%= list.get(1).getRelased()%></font></td>
                <td><font color="#e6e6e6"><%= list.get(1).getLength()%></font></td>
                <td><font color="#e6e6e6"><%= list.get(1).getLanguage()%></font></td>
                <td><font color="#e6e6e6"><%= list.get(1).getImdb()%></font></td>
                <td><font color="#e6e6e6"><%= list.get(1).getTomato()%></font></td>
              </tr>
              
              <tr>
                <td><font color="#e6e6e6">3</font></td>
                <td><div><img src="/T3MS/<%= list.get(2).getMovie_pic()%>"></div><font color="#e6e6e6"><%= list.get(2).getMovie_name() %></font>
                <table id ="czy">
                    <tr class="starRow">
                        <td class="star">★</td><td class="star">★</td><td class="star">★</td><td class="star">★</td><td class="star">★</td>
                    </tr>
                </table><input type="button" class="btnScore" value="想看度"><span style="color:red;font-weight:bold"></span><font color="#ff6600">分</font></td>
                <td><font color="#e6e6e6"><%= list.get(2).getRelased()%></font></td>
                <td><font color="#e6e6e6"><%= list.get(2).getLength()%></font></td>
                <td><font color="#e6e6e6"><%= list.get(2).getLanguage()%></font></td>
                <td><font color="#e6e6e6"><%= list.get(2).getImdb()%></font></td>
                <td><font color="#e6e6e6"><%= list.get(2).getTomato()%></font></td>
              
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
  <%@ include file="/resources/page_code/pageb.file"%>
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

<!-- <div align="center"> -->
<!-- 			<nav aria-label="Page navigation example"> -->
<!-- 			  <ul class="pagination justify-content-center"> -->
<!-- 			    <li class="page-item"> -->
<!-- 			      <a class="page-link" href="#" aria-label="Previous"> -->
<!-- 			        <span aria-hidden="true">&laquo;</span> -->
<!-- 			        <span class="sr-only">Previous</span> -->
<!-- 			      </a> -->
<!-- 			    </li> -->
<!-- 			    <li class="page-item"><a class="page-link" href="?pageNo=1">1</a></li> -->
<!-- 			    <li class="page-item"><a class="page-link" href="?pageNo=2">2</a></li> -->
<!-- 			    <li class="page-item"><a class="page-link" href="?pageNo=3">3</a></li> -->
<!-- 			    <li class="page-item"> -->
<!-- 			      <a class="page-link" href="#" aria-label="Next"> -->
<!-- 			        <span aria-hidden="true">&raquo;</span> -->
<!-- 			        <span class="sr-only">Next</span> -->
<!-- 			      </a> -->
<!-- 			    </li> -->
<!-- 			  </ul> -->
<!-- 			</nav> -->
<!-- </div >			 -->

 
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