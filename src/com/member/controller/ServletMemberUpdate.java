package com.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.member.model.MemService;
import com.member.model.MemVO;

@WebServlet("/ServletMemberUpdate")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ServletMemberUpdate extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		
		HttpSession session = req.getSession();
		MemVO memVO=(MemVO)session.getAttribute("memVO");
		String email=memVO.getEmail();

		System.out.println(email);
		try {
			String lname = req.getParameter("lname");
			
			String fname = req.getParameter("fname");
			
			String phone = req.getParameter("phone");
			String phonereg = "^[(0-9_)]{10}$";
	        System.out.println(phone);
	        if (phone == null || phone.trim().length() == 0) {
				errorMsgs.put("phone","電話勿空白");
			} else if(!phone.trim().matches(phonereg)) { 
				errorMsgs.put("phone","請確認號碼");
            }
			String addr = req.getParameter("addr");
			
			Integer locno =Integer.parseInt(req.getParameter("locno"));
			
			//圖片
			Part part=req.getPart("memimg");
			String typ=part.getContentType();
			String ex = part.getContentType().substring(0,typ.indexOf("/"));
		
			MemService memSrc= new MemService();
			byte[] memimg = memSrc.getMemVO(email).getMemimg();
			System.out.println("圖="+memimg);
			if (part.getSize() != 0 &&  ex.equals("image") ) {
				InputStream in = part.getInputStream();
				memimg = new byte[in.available()];
				in.read(memimg);
				in.close();
			}else {
			
			}
			

			memVO.setLastname(lname);
			memVO.setFirstname(fname);
			memVO.setPhone(phone);
			memVO.setAddr(addr);
			memVO.setLocno(locno);
			memVO.setMemimg(memimg);
			//修改VO
			
			String memno = memVO.getmemno();
			String idnum = memVO.getIDNUM();
			Integer gender = memVO.getGender();
			Integer status = memVO.getStatus();
			Integer type = memVO.getType();
			String extname = memVO.getExtname();
			String birthday = memVO.getBirthday();
			Integer violation = memVO.getViolation();
			String introduction = memVO.getIntroduction();
			
			
			System.out.println(errorMsgs);
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memVO", memVO);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forestage/member/membercenter.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始修改資料 *****************************************/
			memSrc = new MemService();
			System.out.println(email);
			memVO=memSrc.updatemem(lname, fname, phone, addr, locno, memimg, memno, idnum, gender, status, type, extname, birthday, violation, introduction, email);
			System.out.println(memVO.getFirstname());
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/	
			session.setAttribute("memVO", memVO);
			res.sendRedirect(req.getContextPath()+"/index.jsp");
//			RequestDispatcher successView = req.getRequestDispatcher("/forestage/member/successin.jsp");   // 修改成功後,轉交回送出修改的來源網頁
//			successView.forward(req, res);
			
			
		} catch (RuntimeException e) {
			errorMsgs.put("Exception",e.getMessage());
			System.out.println("幹幹幹");
			RequestDispatcher failureView = req
					.getRequestDispatcher("/forestage/member/membercenter.jsp");
			failureView.forward(req, res);
		}

	}

}
