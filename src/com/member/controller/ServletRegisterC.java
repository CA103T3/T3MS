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
import javax.servlet.http.HttpSession;

import com.member.model.MemService;
import com.member.model.MemVO;

import com.member.controller.MailService;


@WebServlet("/ServletRegisterC")
public class ServletRegisterC extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);	
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		
	
		
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
				errorMsgs.put("paw","只能是英文、數字和_,長度2~10");
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
	        String phonereg = "^[(0-9_)]{10}$";
	        System.out.println(phone);
	        if (phone == null || phone.trim().length() == 0) {
				errorMsgs.put("phone","電話請勿空白");
			} else if(!phone.trim().matches(phonereg)) { 
				errorMsgs.put("phone","請輸入正確手機號碼");
            }
	        String IDNUM = req.getParameter("IDNUM");
	        Integer gender = Integer.parseInt(req.getParameter("gender"));
	        String ad = req.getParameter("county");
	        System.out.println("ad="+ad);
	        if("".equals(ad.trim())) {
	        	errorMsgs.put("ad","請選擇縣市");
	        }
	        String dr = req.getParameter("district");
	        System.out.println("dr="+dr);
	        Integer locno=null;
	        if("".equals(dr.trim())) {
	        	errorMsgs.put("dr","請選擇地區");
	        }else {
	        	locno = Integer.parseInt(req.getParameter("zipcode"));
	        }
	        System.out.println(locno);
			String address = req.getParameter("address");
	        if (address == null || address.trim().length() == 0) {
				errorMsgs.put("address","請輸入通訊地址");
	        }
			String addr = ad+dr+address;
			
			MemService memSrc = new MemService();
			if(!memSrc.checkduplicated(email)) {        //檢查重複註冊
				errorMsgs.put("email","EMAIL已被使用");
				System.out.println("帳號重複了");
			}
			
			
			
			MemVO memVO = new MemVO();
			memVO.setEmail(email);
			memVO.setPaw(paw);
			memVO.setLastname(lastname);
			memVO.setFirstname(firstname);
			memVO.setBirthday(birthday);
			memVO.setPhone(phone);
			memVO.setIDNUM(IDNUM);
			memVO.setGender(gender);
			
			
			System.out.println(errorMsgs);
			
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memVO", memVO); 
				
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forestage/member/registerf.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************2.開始新增資料***************************************/
			memSrc = new MemService();
			memVO = memSrc.addmem(email, paw, lastname, firstname, birthday, phone, IDNUM, gender , addr, locno);
			
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
		
			
	        HttpSession session = req.getSession();
	        memVO = new MemVO();
            
            memVO = memSrc.getMemVO(email);
            session.setAttribute("memVO",memVO);
            
            String memno = memVO.getmemno();
            String lname = memVO.getLastname();
            String fname = memVO.getFirstname();
            String name = lname+fname;
            req.setAttribute("memno", memno);
            req.setAttribute("email", email);
			
			
            
            MailService ms = new MailService();
            ms.setmail(memno, name, email);
            System.out.println("--------------------------------------------------");
        	String url ="/forestage/member/waitformail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
//			res.sendRedirect(req.getContextPath()+"/forestage/member/waitformail.jsp");
			
            return;
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
