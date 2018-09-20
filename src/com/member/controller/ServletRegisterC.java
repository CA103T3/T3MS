package com.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemService;
import com.member.model.MemVO;


@WebServlet("/ServletRegisterC")
public class ServletRegisterC extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);	
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		
		System.out.println(req.getServletPath());
		
		try {
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			String email = req.getParameter("email");
			
			if(email == null || email.trim().length() == 0) {
				errorMsgs.put("email","帳號請勿空白");
			}else if(!email.contains("@")){
				errorMsgs.put("email","請輸入有效的Email格式");
			}
			
			
			String pawReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			String paw=req.getParameter("paw");
			if (paw == null || paw.trim().length() == 0) {
				errorMsgs.put("paw","密碼請勿空白");
			} else if(!paw.trim().matches(pawReg)) { 
				errorMsgs.put("paw","只能是英文、數字和_ , 長度2~10");
            }
			
			String paw2=req.getParameter("paw2");
			if(!paw.equals(paw2)) {
				errorMsgs.put("paw2","請確認密碼");
			}
			System.out.println(paw + paw2);
			String lastname=req.getParameter("Lastname");
	        String firstname=req.getParameter("Firstname");
	        String birthday =req.getParameter("birthday");
	        String phone = req.getParameter("phone");
	        String IDNUM = req.getParameter("IDNUM");
	        Integer gender = Integer.parseInt(req.getParameter("gender"));
	        System.out.println(gender);
			
			
			
			MemVO memVO = new MemVO();
			memVO.setEmail(email);
			memVO.setPaw(paw);
			memVO.setLastname(lastname);
			memVO.setFirstname(firstname);
			memVO.setBirthday(birthday);
			memVO.setPhone(phone);
			memVO.setIDNUM(IDNUM);
			memVO.setGender(gender);
			System.out.println(memVO.getEmail());
			System.out.println(memVO.getBirthday());
			System.out.println(errorMsgs);
			
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memVO", memVO); 
				
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forestage/member/registerf.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************2.開始新增資料***************************************/
			MemService memSvc = new MemService();
			if(memSvc.checkduplicated(email)) {        //檢查重複註冊
			memVO = memSvc.addmem(email, paw, lastname,  firstname,  birthday,  phone,  IDNUM,  gender);
			}else {
				errorMsgs.put("email","EMAIL已被使用");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forestage/member/registerf.jsp");
				failureView.forward(req, res);
				System.out.println("帳號重複了");
				return;
			}
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url ="/forestage/member/loginf.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);				
			
			/***************************其他可能的錯誤處理**********************************/
		
		} catch (RuntimeException e) {
			errorMsgs.put("Exception",e.getMessage());
			System.out.println("下面");
			RequestDispatcher failureView = req
					.getRequestDispatcher("/forestage/member/registerf.jsp");
			failureView.forward(req, res);
		}
		
		
		
	}

}
