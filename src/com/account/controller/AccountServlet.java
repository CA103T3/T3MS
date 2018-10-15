package com.account.controller;

import java.io.*;
import java.text.ParseException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.account.model.*;


public class AccountServlet extends HttpServlet{
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

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("bs_acc_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入帳號編號");
				}	
				String strpsw = req.getParameter("bs_acc_psw");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入密碼");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backstage/account_backstage/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String bs_acc_no = null;
				try {
					bs_acc_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("帳號編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backstage/account_backstage/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AccountService account_BackstageService = new AccountService();
				AccountVO accountVO = account_BackstageService.getVO(bs_acc_no);
				if(!strpsw.equals(accountVO.getBs_acc_psw())) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backstage/account_backstage/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("accountVO", accountVO); // 資料庫取出的empVO物件,存入req
				String url = "/backstage/account_backstage/listOneAccount_Backstage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backstage/account_backstage/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				/***************************1.接收請求參數****************************************/
				String bs_acc_no = new String(req.getParameter("bs_acc_no"));
				
				/***************************2.開始查詢資料****************************************/
				AccountService account_BackstageService = new AccountService();
				AccountVO accountVO = account_BackstageService.getVO(bs_acc_no);
					
			
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("accountVO", accountVO);         // 資料庫取出的empVO物件,存入req
				System.out.println("getLast_online_time=" + accountVO.getLast_online_time());
				String url = "/backstage/account_backstage/update_account_backstage_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/backstage/account_backstage/listAllAccount_Backstage.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String bs_acc_no = new String(req.getParameter("bs_acc_no").trim());
				
				String bs_acc_name = req.getParameter("bs_acc_name");
				String bs_acc_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (bs_acc_name == null || bs_acc_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!bs_acc_name.trim().matches(bs_acc_nameReg)) {//以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String bs_acc_psw = req.getParameter("bs_acc_psw");
				String bs_acc_pswReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (bs_acc_psw == null || bs_acc_psw.trim().length() == 0) {
					errorMsgs.add("帳號密碼: 請勿空白");
				} else if(!bs_acc_psw.trim().matches(bs_acc_pswReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("帳號密碼: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String role_no = req.getParameter("role_no").trim();
				if (role_no == null || role_no.trim().length() == 0) {
					errorMsgs.add("角色編號請勿空白");
				}
				
				java.sql.Timestamp last_online_time = null;
				try {
					last_online_time = java.sql.Timestamp.valueOf(req.getParameter("last_online_time").trim());
				} catch (IllegalArgumentException e) {
					last_online_time=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("上次在線時間請輸入日期!");
				}
				
				String cinema_no = null;
				try {
					cinema_no = new String(req.getParameter("cinema_no").trim());
				} catch (NumberFormatException e) {
					cinema_no = "0";
					errorMsgs.add("影城編號請填數字.");
				}
				
				String email = null;
				try {
					email = new String(req.getParameter("email").trim());
				} catch (NumberFormatException e) {
					email = "0";
					errorMsgs.add("電子郵件請填數字.");
				}
				String tel = null;
				try {
					tel = new String(req.getParameter("tel").trim());
				} catch (NumberFormatException e) {
					tel = "0";
					errorMsgs.add("電話號碼請填數字.");
				}
				
				Integer state = new Integer(req.getParameter("state").trim());

				AccountVO accountVO = new AccountVO();
				accountVO.setBs_acc_no(bs_acc_no);
				accountVO.setBs_acc_name(bs_acc_name);
				accountVO.setBs_acc_name(bs_acc_psw);
				accountVO.setRole_no(role_no);
				accountVO.setLast_online_time(last_online_time);
				accountVO.setCinema_no(cinema_no);
				accountVO.setEmail(email);
				accountVO.setTel(tel);
				accountVO.setState(state);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("accountVO", accountVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backstage/account_backstage/update_account_backstage_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				AccountService account_BackstageService = new AccountService();
			
//				accountVO = account_BackstageService.updateaccount_Backstage(bs_acc_no,bs_acc_name,role_no
//						,cinema_no,bs_acc_psw,email,tel,last_online_time,state);
				
				
				
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("accountVO", accountVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backstage/account_backstage/listAllAccount_Backstage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backstage/account_backstage/update_account_backstage_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String bs_acc_name = req.getParameter("bs_acc_name");
				System.out.println(bs_acc_name);
				String bs_acc_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (bs_acc_name == null || bs_acc_name.trim().length() == 0) {
					errorMsgs.add("帳號名稱: 請勿空白");
				} else if(!bs_acc_name.trim().matches(bs_acc_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("帳號名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				
				String role_no = req.getParameter("role_no").trim();
				System.out.println(role_no);
				if (role_no == null || role_no.trim().length() == 0) {
					errorMsgs.add("請選擇角色");
				}
				
								
				String cinema_no = req.getParameter("cinema_no");
				
				System.out.println("c="+cinema_no);

				
				String bs_acc_psw = req.getParameter("bs_acc_psw");
				System.out.println(bs_acc_psw);
				String bs_acc_pswReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (bs_acc_psw == null || bs_acc_psw.trim().length() == 0) {
					errorMsgs.add("帳號密碼: 請勿空白");
				} else if(!bs_acc_psw.trim().matches(bs_acc_pswReg)) {//以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("帳號密碼: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String email = req.getParameter("email");

				String tel = req.getParameter("tel");
				
				
				java.sql.Timestamp last_online_time = null;
//				try {
//					java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
//					java.util.Date du = df.parse(req.getParameter("last_online_time").trim());
//					last_online_time = new java.sql.Timestamp(du.getTime());
//				} catch (IllegalArgumentException e) {
//					errorMsgs.add("上次在線時間請輸入日期!");
//				} 
				System.out.println("last_online_time="+last_online_time);
								
				
				AccountVO accountVO = new AccountVO();
				accountVO.setBs_acc_name(bs_acc_name);
				accountVO.setRole_no(role_no);
				accountVO.setCinema_no(cinema_no);
				accountVO.setBs_acc_psw(bs_acc_psw);
				accountVO.setEmail(email);
				accountVO.setTel(tel);
				accountVO.setLast_online_time(last_online_time);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("accountVO", accountVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backstage/staff/backstage_insert.jsp");
					failureView.forward(req, res);
					return;
				}
		
				/***************************2.開始新增資料***************************************/
				AccountService account_BackstageService = new AccountService();
				account_BackstageService.addAccount_Backstage(bs_acc_name,role_no
						,cinema_no,bs_acc_psw,email,tel,last_online_time);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
			    
				String url = "/backstage/staff/backstage_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/backstage/staff/backstage_insert.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String bs_acc_no = new String(req.getParameter("bs_acc_no"));
				
				/***************************2.開始刪除資料***************************************/
				AccountService account_BackstageService = new AccountService();
				account_BackstageService.deleteAccount_Backstage(bs_acc_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/backstage/account_backstage/listAllAccount_Backstage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backstage/account_backstage/listAllAccount_Backstage.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("login".equals(action)) {
			boolean b=false;
	        AccountService aSrc= new AccountService();
	        HttpSession session = req.getSession();
	        
	        String bs_acc_name=req.getParameter("bs_acc_name");
	        System.out.println(bs_acc_name);
	        String bs_acc_psw=req.getParameter("bs_acc_psw");
	        System.out.println(bs_acc_psw);
	        
	        java.sql.Timestamp last_online_time = null;
	        System.out.println(req.getParameter("last_online_time"));
	        try {
				java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
				java.util.Date du = df.parse(req.getParameter("last_online_time").trim());
				last_online_time = new java.sql.Timestamp(du.getTime());
			} catch (Exception e) {
				e.printStackTrace();
			} 
			System.out.println("last_online_time="+last_online_time);
	        
	        
	        
	        String result = "";
	 
	        b=aSrc.login(bs_acc_name, bs_acc_psw);
	        PrintWriter out = res.getWriter();
	        if(b){  
	            AccountVO aVO = new AccountVO();
	            result = "success";
	            aVO = aSrc.getVO(bs_acc_name);
	            aSrc.logintime(bs_acc_name);
	            session.setAttribute("aVO",aVO);
	            

	            res.sendRedirect(req.getContextPath()+"/backstage/backstage_index.jsp");
//	            Cookie username= new Cookie("email",email);
	//   
//	            username.setPath("/");
//	            username.setMaxAge(60*60);
//	            response.addCookie(username);

	        }
	        else { 
	            result = "fail";
	            
//	            out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
//	            out.println("<BODY>你的帳號 , 密碼無效!<BR>");
//	            out.println("請按此重新登入 <A HREF="+request.getContextPath()+"/forestage/member/loginf.jsp>重新登入</A>");
//	            out.println("</BODY></HTML>");

	            RequestDispatcher rpt = req.getRequestDispatcher("/backstage/staff/backstage_login.jsp");
	            rpt.forward(req, res);
	        }
	        
	        out.flush();
	        out.close();
	        System.out.println(result);
	    
		}
		
		
		
	}

}
