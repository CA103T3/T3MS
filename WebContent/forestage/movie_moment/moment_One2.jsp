<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.movie.model.*"%>


<%
	MovieService movieSvc = new MovieService();
    MovieVO movieVO = movieSvc.getOneMovie(request.getQueryString());
	pageContext.setAttribute("movieVO", movieVO);
%>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <!--star-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/m_Moment.css">
        <link href="<%=request.getContextPath()%>/css/introduceM.css" rel="stylesheet" type="text/css">
        <%@ include file="/forestage/template/link.jsp" %>
        <title>M&amp;S</title>
        <style>
			body{background-color:#fff !important}
			.container-fluid{background-color:#404040;height: 100px;} 
			.text-warning {color:#ffe661;}
			#btnbtn{
				position:absolute;top:230px;left:918px;z-index:999;
			}
        </style>
        
    </head>
    <body class="body-template">
        <%@ include file="/forestage/template/header_no_bar.jsp" %>
        
        
        
        <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#hanhan" id="btnbtn">播放</button>
 <!-- ==============================================新增跳出的燈箱============================================== -->       
 <div class="modal fade" id="hanhan" tabindex="-1" role="dialog" aria-labelledby="hanhan" aria-hidden="true">
							<form action="<%=request.getContextPath()%>/backstage/announcement/ann.do" method="POST">
								<div class="modal-dialog modal-md">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											<h3 class="modal-title" id="myModalLabel">預告片</h3>
										</div>

										<div class="modal-body">
											<div class="embed-responsive embed-responsive-16by9">
                                                <iframe class="embed-responsive-item" src="${movieVO.trailer_url}"></iframe>
</div>
										</div>

										<div class="modal-footer">
											  
      
        <button type="button" class="btn btn-danger" data-dismiss="modal">關閉</button>
     
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			
				<!-- ==============================================新增跳出的燈箱============================================== -->       
        
        
        
        
        
        
        
        
<!-- ----------------moment One ----------------->


<div class="youtudem">
                    <iframe  width="100%" height="315" src="${movieVO.trailer_url}" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
                  </div>

                  
         
          <div class="section">
            <div class="container">
              <div class="row">
                <div class="col-md-6">

                  <div class="col-xs-12 col-sm-6">
                    <img src="<%=request.getContextPath() %>/DBGifReader?movie_no=${movieVO.movie_no}" class="img-responsive img-rounded" id="imgm">                    
                  </div>  

                  <div class="col-xs-12 col-sm-6">
                  
                    <h2 class="text-left" id="tittle">${movieVO.movie_name}</h2>
                    <p class="text-left">${movieVO.eng_name}</p>
                    <div class="label label-default">${movieVO.rating}</div>
                    <div class="time2">上映日期 : ${movieVO.relased}</div>
                    <div class="time2">片長 : ${movieVO.length}分鐘</div>
                    <div class="sor">MS評分:</div>
                  <span class="fa fa-star ${(movieVO.imdb >=2.0)? 'text-warning':''}"></span>
		          <span class="fa fa-star ${(movieVO.imdb >=4.0)? 'text-warning':''}"></span>
		          <span class="fa fa-star ${(movieVO.imdb >=6.0)? 'text-warning':''}"></span>
		          <span class="fa fa-star ${(movieVO.imdb >=8.0)? 'text-warning':''}"></span>
		          <span class="fa fa-star ${(movieVO.imdb >=10.0)? 'text-warning':''}"></span>
                    <div class="sor">IMDB:</div>
                    <div class="sor1">${movieVO.imdb}</div>
                    <div class="sor">爛番茄:</div>
                    <div class="sor1">${movieVO.tomato}</div>
                  </div>

                </div>                             
              </div>
            </div>
          </div>


  

 <!-- movie Moment   -->       
    <div class="section">
      <div class="container">
            
          
          
          <div class="tab">
            <button class="tablinks" onclick="openCity(event, 'Moment')" >電影時刻</button>
            <button class="tablinks" onclick="openCity(event, 'Introd')" id="defaultOpen">電影簡介</button> 
          </div>
          
          <div id="Moment" class="tabcontent">
          
                  <div class="container">
                          <div class="col-md-6">
                           
                            <form>
                              <div class="form-group">
                                <label for="sel1">選擇電影日期:</label>
                                <select class="form-control" id="sel1">
                                  <option>10/06</option>
                                  <option>10/07</option>
                                  <option>10/08</option>
                                  <option>10/09</option>
                                  <option>10/10</option>
                                </select>
                                <br>
                              </div>
                            </form>
                          </div>
                          </div>
              
                  
                  <div class="panel panel-primary">
          
                    <div class="panel-heading">
                      <h3 class="panel-title">板橋大園百威秀影城</h3>
          
                    
                    </div>
                    
                    <div class="panel-body">
                      <div class="row">
                      <div class="col-md-3" ><div id="ctype"><h4>數位</h4></div> </div>
                      <div class="col-md-9">
                    
                    <div class="row">
          
                      <div class="col-md-2 text-center">
                        <div class="sisson">      
                          <div id="time">08:20</div>
                         </div>
                      </div> 
          
                      <div class="col-md-2 text-center">
                          <div class="sisson">      
                            <div id="time">09:20</div>
                           </div>
                      </div> 
          
                      <div class="col-md-2 text-center">
                          <div class="sisson">      
                            <div id="time">10:20</div>
                          </div>
                      </div> 
          
                      <div class="col-md-2 text-center">
                          <div class="sisson">      
                            <div id="time">12:20</div>
                          </div>
                      </div> 
          
                      <div class="col-md-2 text-center">
                          <div class="sisson">      
                            <div id="time">12:20</div>
                          </div>
                      </div> 
          
                      <div class="col-md-2 text-center">
                          <div class="sisson">      
                            <div id="time">12:20</div>
                          </div>
                      </div> 
          
                      <div class="col-md-2 text-center">
                          <div class="sisson">      
                            <div id="time">12:20</div>
                          </div>
                      </div> 
          
          
          
          
                    </div>
          
          
                    </div>
          
                    
                      
                    </div>
                </div>
          
                  </div>
          
                  <div class="panel panel-primary">
          
                    <div class="panel-heading">
                      <h3 class="panel-title">台中老虎城威秀影城</h3>
          
                    
                    </div>
                    
                    <div class="panel-body">
                      <div class="row">
                      <div class="col-md-3" ><div id="ctype"><h4>數位</h4></div> </div>
                      <div class="col-md-9">
                    
                    <div class="row">
          
                      <div class="col-md-2 text-center">
                        <div class="sisson">      
                          <div id="time">08:20</div>
                         </div>
                      </div> 
          
                      <div class="col-md-2 text-center">
                          <div class="sisson">      
                            <div id="time">09:20</div>
                           </div>
                      </div> 
          
                      <div class="col-md-2 text-center">
                          <div class="sisson">      
                            <div id="time">10:20</div>
                          </div>
                      </div> 
          
                      <div class="col-md-2 text-center">
                          <div class="sisson">      
                            <div id="time">12:20</div>
                          </div>
                      </div> 
          
                      <div class="col-md-2 text-center">
                          <div class="sisson">      
                            <div id="time">12:20</div>
                          </div>
                      </div> 
          
                      <div class="col-md-2 text-center">
                          <div class="sisson">      
                            <div id="time">12:20</div>
                          </div>
                      </div> 
          
                      <div class="col-md-2 text-center">
                          <div class="sisson">      
                            <div id="time">12:20</div>
                          </div>
                      </div> 
          
          
          
          
                    </div>
          
          
                    </div>
          
                    
                      
                    </div>
                </div>
          
                  </div>
          
          
          </div>
          
          
          
          <!-- movie_Introd   -->
          <div id="Introd" class="tabcontent">
            <div style="display:inline;"><strong>導演:</strong></div><span style="display:inline;">${movieVO.director}</span>
            <div style="display:inline;"><strong>演員:</strong></div><span style="display:inline;">${movieVO.starring}</span>
            <p>${movieVO.brief_intro}</p>
          </div>     
         


      </div>
   </div>

















       
<!-- ----------------moment One ----------------->  
        <%@ include file="/forestage/template/footer.jsp" %>

        <script src="<%=request.getContextPath()%>/js/template.js"></script>
        <script>
        $(document).ready(function(){
            $("li:contains('電影資訊')").addClass("custom-active");
        });
        </script>
        
         <script>
        function openCity(evt, cityName) {
            var i, tabcontent, tablinks;
            tabcontent = document.getElementsByClassName("tabcontent");
            for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].style.display = "none";
            }
            tablinks = document.getElementsByClassName("tablinks");
            for (i = 0; i < tablinks.length; i++) {
                tablinks[i].className = tablinks[i].className.replace(" active", "");
            }
            document.getElementById(cityName).style.display = "block";
            evt.currentTarget.className += " active";
        }
        // Get the element with id="defaultOpen" and click on it
        document.getElementById("defaultOpen").click();
      </script>

<script>
    export default {
      data () {
        return {
          selected: null,
          options: [
            { value: 'A', text: 'Option A (from options prop)' },            
          ]
        }
      }
    }
    </script>
    </body>
</html>