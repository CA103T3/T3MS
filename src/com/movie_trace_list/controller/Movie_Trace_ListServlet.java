package com.movie_trace_list.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.movie_trace_list.model.*;

public class Movie_Trace_ListServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("men_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}	
				String strpsw = req.getParameter("movie_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入電影編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backstage/cinema/addCinema.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String men_no = null;
				try {
					men_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("帳號編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backstage/cinema/addCinema.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Movie_Trace_ListService movie_Trace_listService = new Movie_Trace_ListService();
				Movie_Trace_ListVO movie_Trace_listVO = movie_Trace_listService.getOneMovie_Trace_ListVO(men_no);
				if(!strpsw.equals(movie_Trace_listVO.getMovie_no())) {
					errorMsgs.add("查無資料");
				}
//				if (account_BackstageVO == null) {
//					errorMsgs.add("查無資料");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backstage/cinema/addCinema.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("movie_Trace_listVO", movie_Trace_listVO); // 資料庫取出的empVO物件,存入req
				String url = "/backstage/cinema/listOneCinema.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backstage/cinema/listOneCinema.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String mem_no = new String(req.getParameter("mem_no"));
				
				/***************************2.開始查詢資料****************************************/
				Movie_Trace_ListService movie_Trace_listService = new Movie_Trace_ListService();
				Movie_Trace_ListVO movie_Trace_listServiceVO = movie_Trace_listService.getOneMovie_Trace_ListVO(mem_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("movie_Trace_listServiceVO",movie_Trace_listServiceVO);         // 資料庫取出的empVO物件,存入req
				String url = "/backstage/cinema/listAllCinema.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/account_backstage/listAllAccount_Backstage.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String mem_no = new String(req.getParameter("mem_no").trim());
				
				String mem_no = req.getParameter("mem_no");
				String mem_noReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_no == null || mem_no.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!mem_no.trim().matches(mem_noReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String movie_no = req.getParameter("movie_no");
				String movie_noReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (movie_no == null || movie_no.trim().length() == 0) {
					errorMsgs.add("帳號密碼: 請勿空白");
				} else if(!movie_no.trim().matches(movie_noReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("帳號密碼: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
//				String role_no = req.getParameter("role_no").trim();
//				if (role_no == null || role_no.trim().length() == 0) {
//					errorMsgs.add("角色編號請勿空白");
//				}
//				
//				java.sql.Timestamp last_online_time = null;
//				try {
//					last_online_time = java.sql.Timestamp.valueOf(req.getParameter("last_online_time").trim());
//				} catch (IllegalArgumentException e) {
//					last_online_time=new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("上次在線時間請輸入日期!");
//				}
//				
//				String cinema_no = null;
//				try {
//					cinema_no = new String(req.getParameter("cinema_no").trim());
//				} catch (NumberFormatException e) {
//					cinema_no = "0";
//					errorMsgs.add("影城編號請填數字.");
//				}
//				
//				String email = null;
//				try {
//					email = new String(req.getParameter("email").trim());
//				} catch (NumberFormatException e) {
//					email = "0";
//					errorMsgs.add("電子郵件請填數字.");
//				}
//				String tel = null;
//				try {
//					tel = new String(req.getParameter("tel").trim());
//				} catch (NumberFormatException e) {
//					tel = "0";
//					errorMsgs.add("電話號碼請填數字.");
//				}
				
//				Integer state = new Integer(req.getParameter("state").trim());

				Movie_Trace_ListVO movie_Trace_listVO = new Movie_Trace_ListVO();
				movie_Trace_listVO.setMem_no(mem_no);
				movie_Trace_listVO.setMovie_no(movie_no);
//				account_BackstageVO.setBs_acc_name(bs_acc_psw);
//				account_BackstageVO.setRole_no(role_no);
//				account_BackstageVO.setLast_online_time(last_online_time);
//				account_BackstageVO.setCinema_no(cinema_no);
//				account_BackstageVO.setEmail(email);
//				account_BackstageVO.setTel(tel);
//				account_BackstageVO.setState(state);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("movie_Trace_listVO", movie_Trace_listVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backstage/cinema/updateCinema.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Movie_Trace_ListService movie_Trace_listService = new Movie_Trace_ListService();
			
				movie_Trace_listVO = movie_Trace_listService.updateMovie_Trace_List(mem_no,movie_no);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("movie_Trace_listVO", movie_Trace_listVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backstage/cinema/updateCinema.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backstage/cinema/updateCinema.jsp");
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
				System.out.println(req.getParameter("movie_no"));
				String movie_noReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (movie_no == null || movie_no.trim().length() == 0) {
					errorMsgs.add("帳號名稱: 請勿空白");
				} else if(!movie_no.trim().matches(movie_noReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("帳號名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				
				
//				String role_no = req.getParameter("role_no").trim();
//				System.out.println(req.getParameter("role_no"));
//				if (role_no == null || role_no.trim().length() == 0) {
//					errorMsgs.add("角色編號請勿空白");
//				}
//				
//								
//				String cinema_no = null;
//				try {
//					cinema_no = new String(req.getParameter("cinema_no").trim());
//					System.out.println(req.getParameter("cinema_no"));
//				} catch (NumberFormatException e) {
//					cinema_no = "0";
//					errorMsgs.add("影城編號請填數字.");
//				}
//				
//				String bs_acc_psw = req.getParameter("bs_acc_psw");
//				System.out.println(req.getParameter("bs_acc_psw"));
//				String bs_acc_pswReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (bs_acc_psw == null || bs_acc_psw.trim().length() == 0) {
//					errorMsgs.add("帳號密碼: 請勿空白");
//				} else if(!bs_acc_psw.trim().matches(bs_acc_pswReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("帳號密碼: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//				}
//				
//				String email = null;
//				try {
//					email = new String(req.getParameter("email").trim());
//					System.out.println(req.getParameter("email"));
//				} catch (NumberFormatException e) {
//					email = "0";
//					errorMsgs.add("電子郵件請勿空白.");
//				}
//				String tel = null;
//				try {
//					tel = new String(req.getParameter("tel").trim());
//					System.out.println(req.getParameter("tel"));
//				} catch (NumberFormatException e) {
//					tel = "0";
//					errorMsgs.add("電話號碼請填數字.");
//				}
//				
//				java.sql.Timestamp last_online_time = null;//????????????
//				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxlast_online_time="+req.getParameter("last_online_time"));
//				try {
//					java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
//					java.util.Date du = df.parse(req.getParameter("last_online_time").trim());
//					last_online_time = new java.sql.Timestamp(du.getTime());
//				} catch (IllegalArgumentException e) {
//					last_online_time=new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("上次在線時間請輸入日期!");
//				}
//				
//				Integer state = new Integer(req.getParameter("state").trim());
//				System.out.println(req.getParameter("state"));
				Movie_Trace_ListVO movie_Trace_listVO = new Movie_Trace_ListVO();
//				movie_Trace_listVO.setMem_no(mem_no);
				movie_Trace_listVO.setMovie_no(movie_no);
//				account_BackstageVO.setCinema_no(cinema_no);
//				account_BackstageVO.setBs_acc_psw(bs_acc_psw);
//				account_BackstageVO.setEmail(email);
//				account_BackstageVO.setTel(tel);
//				account_BackstageVO.setLast_online_time(last_online_time);
//				account_BackstageVO.setState(state);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("movie_Trace_listVO", movie_Trace_listVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backstage/cinema/addCinema.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Movie_Trace_ListService movie_Trace_listService = new Movie_Trace_ListService();
				movie_Trace_listVO = movie_Trace_listService.addMovie_Trace_List(movie_no);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backstage/cinema/listAllCinema.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backstage/cinema/addCinema.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				Integer empno = new Integer(req.getParameter("empno"));
//				
//				/***************************2.開始刪除資料***************************************/
//				EmpService empSvc = new EmpService();
//				empSvc.deleteEmp(empno);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/listAllEmp.jsp");
//				failureView.forward(req, res);
//			}
//		}
	}

}
