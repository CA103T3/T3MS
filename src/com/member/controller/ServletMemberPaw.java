package com.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemService;

@WebServlet("/ServletMemberPaw")
public class ServletMemberPaw extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
	if("forgot".equals(action)) {
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
		
		String email = req.getParameter("email");
		String idnum = req.getParameter("idnum");
		
		if(email == null || email.trim().length() == 0) {
			errorMsgs.put("email","請確認");
		}else if(!email.contains("@")){
			errorMsgs.put("email","請輸入有效的Email格式");
		}
		if(idnum == null || idnum.trim().length() == 0) {
			errorMsgs.put("idnum","請確認");
		}
	
		if (!errorMsgs.isEmpty()) {
	
			RequestDispatcher failureView = req
					.getRequestDispatcher("/forestage/member/forgotpaw.jsp");
			failureView.forward(req, res);
			return;
		}
		
		
		//=============================================================
		
		MemService memSrc = new MemService();
		
		if(memSrc.forgotpaw(email, idnum)) {
			
			req.setAttribute("email", email);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/forestage/member/forgotchangpaw.jsp");
			dispatcher.forward(req, res);
		}else{
			errorMsgs.put("big","帳號與身分證不符");
			RequestDispatcher failureView = req
					.getRequestDispatcher("/forestage/member/forgotpaw.jsp");
			failureView.forward(req, res);
//			System.out.println("帳號身分證配對錯誤");
			return;
		}
				
	//==============================================================		
	} catch (RuntimeException e) {
		errorMsgs.put("Exception",e.getMessage());
		System.out.println("下面");
		RequestDispatcher failureView = req
				.getRequestDispatcher("/forestage/member/forgotpaw.jsp");
		failureView.forward(req, res);
	}
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	
	
	if ("change".equals(action)) {
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
		
		String email = req.getParameter("email");
		String paw = req.getParameter("paw");
		String paw2 = req.getParameter("paw2");
		
//		System.out.println(email+paw);
		String pawReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
		if (paw == null || paw.trim().length() == 0) {
			errorMsgs.put("paw","請確認");
		} else if(!paw.trim().matches(pawReg)) { 
			errorMsgs.put("paw","只能是英文、數字和_,長度2~10");
        }
		if(!paw.equals(paw2)) {
			errorMsgs.put("paw2","請確認密碼");
		}
		
//		System.out.println(errorMsgs);
		if (!errorMsgs.isEmpty()) {	
			RequestDispatcher failureView = req
					.getRequestDispatcher("/forestage/member/changpaw.jsp");
			failureView.forward(req, res);
			return;
		}
		
		
		//=============================================================
		
		MemService memSrc = new MemService();
		
		memSrc.changepaw(paw, email);
		res.sendRedirect(req.getContextPath()+"/forestage/member/changfin.jsp");
		
		//===============================================================
		
		
	} catch (RuntimeException e) {
		errorMsgs.put("Exception",e.getMessage());
		System.out.println("下面");
		RequestDispatcher failureView = req
				.getRequestDispatcher("/forestage/member/changpaw.jsp");
		failureView.forward(req, res);
	}
		
	}

	
	////////////////////////////////////////////////////////////////////////
	
	if ("forgotchange".equals(action)) {
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
		
		String email = req.getParameter("email");
		String paw = req.getParameter("paw");
		String paw2 = req.getParameter("paw2");
		
//		System.out.println(email+paw);
		String pawReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
		if (paw == null || paw.trim().length() == 0) {
			errorMsgs.put("paw","請確認");
		} else if(!paw.trim().matches(pawReg)) { 
			errorMsgs.put("paw","只能是英文、數字和_,長度2~10");
        }
		if(!paw.equals(paw2)) {
			errorMsgs.put("paw2","請確認密碼");
		}
		
//		System.out.println(errorMsgs);
		if (!errorMsgs.isEmpty()) {	
			RequestDispatcher failureView = req
					.getRequestDispatcher("/forestage/member/forgotchangpaw.jsp");
			failureView.forward(req, res);
			return;
		}
		
		
		//=============================================================
		
		MemService memSrc = new MemService();
		
		memSrc.changepaw(paw, email);
		
		res.sendRedirect(req.getContextPath()+"/forestage/member/loginf.jsp");
		
		//===============================================================
		
		
	} catch (RuntimeException e) {
		errorMsgs.put("Exception",e.getMessage());
		System.out.println("下面");
		RequestDispatcher failureView = req
				.getRequestDispatcher("/forestage/member/forgotchangpaw.jsp");
		failureView.forward(req, res);
		
	}
		
	}
	
	
	
	
	
}	
}
