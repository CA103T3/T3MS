<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.movie.model.*"%>
        <!-- search bar > -->
        <div class="container-fluid">
            <div class="col-md-4 col-md-offset-4 search-bar-margin">
                <form METHOD="post" ACTION="<%=request.getContextPath()%>/movie/movie.do">
                    <div class="input-group">
                        <input type="text" class="form-control input-lg text-center font-custom-large" name="movie_name" placeholder="搜尋電影時刻" aria-label="搜尋電影時刻">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="submit">
                              <i class="fa fa-search fa-2x" aria-label="搜尋電影時刻"></i>
                            </button>
                        </span>
                        <input type="hidden" name="requestURL"  value="<%=request.getServletPath()%>">
                        <input type="hidden" name="action" value="getMovie_Name">
                    </div>
                </form>
            </div>
        </div>