package com.filmreview.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.filmreview.model.FilmreviewService;
import com.filmreview.model.FilmreviewVO;

public class FilmreviewServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String fr_no = new String(req.getParameter("fr_no"));
				
				/***************************2.開始查詢資料****************************************/
				FilmreviewService fvSvc = new FilmreviewService();
				FilmreviewVO filmreviewVO = fvSvc.findByPrimaryKey(fr_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("filmreviewVO", filmreviewVO);         // 資料庫取出的VO物件,存入req
				String url = "/forestage/filmreview/fv_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forestage/filmreview/fv_home.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				
				
				String movie_no = req.getParameter("movie_no");
				
				
		
				if (movie_no == null || movie_no.trim().length() == 0) {
					errorMsgs.add("電影編號: 請勿空白");
				} else if(!movie_no.trim().matches(movie_no)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影編號: 只能是中、英文字母、數字和_");
	            }else if(movie_no.length()>10) {
					errorMsgs.add("會員編號長度必需在1到10之間");
					}
				
		
				String title = req.getParameter("title").trim();
				if (title == null || title.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				
				
				
				String content = req.getParameter("content");
				if (content == null || content.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				
				
				String mem_no = req.getParameter("mem_no");
				if (mem_no == null || mem_no.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}else if(mem_no.length()>10) {
					errorMsgs.add("會員編號長度請勿超過10");
					}
				
				String author = req.getParameter("author");
				if (author == null || author.trim().length() == 0) {
					errorMsgs.add("作者請勿空白");
				}
				
				String url = req.getParameter("url");
				if (url == null || url.trim().length() == 0) {
					errorMsgs.add("網址請勿空白");
				}
				String source = req.getParameter("source");
				if (source == null || source.trim().length() == 0) {
					errorMsgs.add("來源請勿空白");
				}
				
				
				Double evaluation = null;
				try {
					evaluation = new Double(req.getParameter("evaluation").trim());
					if(evaluation>5.0||evaluation<0.0) {
						errorMsgs.add("評分請在0.0~5.0內.");
					}
					
				} catch (NumberFormatException e) {
					evaluation = 0.0;
					errorMsgs.add("評分請填數字.");
				}

				FilmreviewVO FilmreviewVO = new FilmreviewVO();
				FilmreviewVO.setMovie_no(movie_no);
				FilmreviewVO.setTitle(title);
				FilmreviewVO.setEvaluation(evaluation);
				FilmreviewVO.setContent(content);
				FilmreviewVO.setMem_no(mem_no);
				FilmreviewVO.setAuthor(author);
				FilmreviewVO.setUrl(url);
				FilmreviewVO.setSource(source);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("errorMsgs", errorMsgs); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/forestage/filmreview/fv_writing.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				FilmreviewService fvSvc = new FilmreviewService();
				FilmreviewVO = fvSvc.insertFilmrevew(movie_no ,content, evaluation , title , source , url , mem_no , author );
				
				/*************************3.新增完成,準備轉交(Send the Success view)***********/
				String urll = "/forestage/filmreview/fv_home.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(urll); // 新增成功後轉交
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forestage/filmreview/fv_writing.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
	}
}
