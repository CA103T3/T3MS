package com.session.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import org.json.JSONObject;

import com.session.model.SessionService;
import com.session.model.SessionVO;
import com.theater.model.TheaterService;
import com.theater.model.TheaterVO;
import com.movie.model.MovieService;
import com.movie.model.MovieVO;
import com.cinema.model.CinemaService;
import com.cinema.model.CinemaVO;

/**
 * Servlet implementation class MomentServlet
 */
@WebServlet("/MomentServlet")
public class MomentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MomentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse res)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");

		String action = req.getParameter("action");
		
	//-----------------------------------今天------------------------------------------	
		if ("selectA".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.****************************************/
	
				String movie_no = req.getParameter("movie_no");

				/*************************** 2.****************************************/


	SessionService sSvc = new SessionService();
	List<SessionVO> sessionVOList = sSvc.getNowMoment(movie_no);
	
	req.setAttribute("selectA", sessionVOList); //Today
	
				/*************************** 3.(Send the Success view) ************/
		String url = null;
				if ("selectA".equals(action))
					url = "/forestage/movie_moment/moment_One.jsp?"+movie_no;              
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************  ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
//------------------------------明天---------------------------------------------------		
		
		if ("selectB".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.****************************************/
	
				String movie_no = req.getParameter("movie_no");

				/*************************** 2.****************************************/

				
				SessionService tmrSvc = new SessionService();
				List<SessionVO> sessionVOList1 = tmrSvc.getTmrMoment(movie_no);
				
				req.setAttribute("selectB", sessionVOList1); //  TMR  

	
				/*************************** 3.(Send the Success view) ************/
		
				String url = null;
				if ("selectB".equals(action))
					url = "/forestage/movie_moment/moment_One.jsp?"+movie_no;              
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************  ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
//------------------------------後天---------------------------------------------------		
		
		if ("selectC".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.****************************************/
				
				String movie_no = req.getParameter("movie_no");
				
				/*************************** 2.****************************************/
				
				
				SessionService tmrSvc = new SessionService();
				List<SessionVO> sessionVOList1 = tmrSvc.getCMoment(movie_no);
				
				req.setAttribute("selectC", sessionVOList1); //  TMR  
				
				
				/*************************** 3.(Send the Success view) ************/
				
				String url = null;
				if ("selectC".equals(action))
					url = "/forestage/movie_moment/moment_One.jsp?"+movie_no;              
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************  ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
//------------------------------明天---------------------------------------------------		
		
		if ("selectD".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.****************************************/
				
				String movie_no = req.getParameter("movie_no");
				
				/*************************** 2.****************************************/
				
				
				SessionService tmrSvc = new SessionService();
				List<SessionVO> sessionVOList1 = tmrSvc.getDMoment(movie_no);
				
				req.setAttribute("selectD", sessionVOList1); //  TMR  
				
				
				/*************************** 3.(Send the Success view) ************/
				
				String url = null;
				if ("selectD".equals(action))
					url = "/forestage/movie_moment/moment_One.jsp?"+movie_no;              
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************  ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
//------------------------------明天---------------------------------------------------		
		
		if ("selectE".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.****************************************/
				
				String movie_no = req.getParameter("movie_no");
				
				/*************************** 2.****************************************/
				
				
				SessionService tmrSvc = new SessionService();
				List<SessionVO> sessionVOList1 = tmrSvc.getEMoment(movie_no);
				
				req.setAttribute("selectE", sessionVOList1); //  TMR  
				
				
				/*************************** 3.(Send the Success view) ************/
				
				String url = null;
				if ("selectE".equals(action))
					url = "/forestage/movie_moment/moment_One.jsp?"+movie_no;              
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************  ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
//------------------------------明天---------------------------------------------------		
		
		if ("selectF".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.****************************************/
				
				String movie_no = req.getParameter("movie_no");
				
				/*************************** 2.****************************************/
				
				
				SessionService tmrSvc = new SessionService();
				List<SessionVO> sessionVOList1 = tmrSvc.getFMoment(movie_no);
				
				req.setAttribute("selectF", sessionVOList1); //  TMR  
				
				
				/*************************** 3.(Send the Success view) ************/
				
				String url = null;
				if ("selectF".equals(action))
					url = "/forestage/movie_moment/moment_One.jsp?"+movie_no;              
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************  ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
//------------------------------7天---------------------------------------------------		
		
		if ("selectG".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.****************************************/
				
				String movie_no = req.getParameter("movie_no");
				
				/*************************** 2.****************************************/
				
				
				SessionService tmrSvc = new SessionService();
				List<SessionVO> sessionVOList1 = tmrSvc.getGMoment(movie_no);
				
				req.setAttribute("selectG", sessionVOList1); //  TMR  
				
				
				/*************************** 3.(Send the Success view) ************/
				
				String url = null;
				if ("selectG".equals(action))
					url = "/forestage/movie_moment/moment_One.jsp?"+movie_no;              
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************  ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//==================================AJAX========================================		

		if ("get_moment".equals(action)) { // from moment_One.jsp ajax

			System.out.println("==============");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
//	            req.setAttribute("errorMsgs", errorMsgs);

//	            String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑
			req.setCharacterEncoding("utf-8");
			res.setContentType("text/plain; charset=utf-8");
			PrintWriter out = res.getWriter();
			JSONObject json = new JSONObject();

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String movie_no = req.getParameter("movie_no");

				/*************************** 2.查詢資料 ***************************************/
				SessionService sSvc = new SessionService();
				List<SessionVO> sessionVOList = sSvc.getNowMoment(movie_no);

				
				
				
				    for (SessionVO sessionVO : sessionVOList) {
					String cinName = sessionVO.getCinemaVO().getCinema_name();
					String eqMent = sessionVO.getTheaterVO().getEquipment();
					Timestamp sTime = sessionVO.getSession_time();
										
					System.out.println(cinName);
					json.put("cinName", cinName);
					json.put("eqMent", eqMent);
					json.put("sTime", sTime);
				}

//	               if(sessionVOList == null) {
//	                   json.put("status", "no data found");
//	                   out.print(json);
//	                   return;
//	               } else {
//	            	   
//	                   String cname = sessionVOList.getCinemaVO().getCinema_name();
//	                   json.put("cname", cname);
//	                   
//	                   System.out.println("cname="+cname);
//	                  
//                
//	               }
				/*************************** 3.完成,回傳json ***********/
				json.put("status", "done");
				 System.out.println("json.toString() : " + json.toString());
				out.print(json);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				if (e.getMessage() != null) {
					errorMsgs.add("查詢資料失敗: " + e.getMessage().replaceAll("\r|\n", " "));
				} else {
					errorMsgs.add("查詢資料失敗");
				}
//	                RequestDispatcher failureView = req
//	                        .getRequestDispatcher(requestURL);
//	                failureView.forward(req, res);
				json.put("status", "error");
				json.put("msg", errorMsgs);
				return;
			}
		}

	}

}
