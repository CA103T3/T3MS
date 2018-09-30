package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemService;
import com.member.model.MemVO;

import example.MailService;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ServletLoginC extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean b=false;
        MemService memSrc= new MemService();
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        String email=request.getParameter("email");
        String paw=request.getParameter("paw");
        String result = "";
 
        b=memSrc.allowuser(email, paw);
        PrintWriter out = response.getWriter();
        if(b){  
            MemVO memVO = new MemVO();
            result = "success";
            memVO = memSrc.getMemVO(email);
            session.setAttribute("memVO",memVO);
            
            String memno = memVO.getmemno();
            String lname = memVO.getLastname();
            String fname = memVO.getFirstname();
            String name = lname+fname;
            request.setAttribute("memno", memno);
            request.setAttribute("email", email);
            session.setAttribute("email", email);
           
//            Cookie username= new Cookie("email",email);
//   
//            username.setPath("/");
//            username.setMaxAge(60*60);
//            response.addCookie(username);
            
            if(memVO.getStatus()==0) {
	            response.sendRedirect(request.getContextPath()+"/forestage/member/waitformail.jsp");
	            
	            MailService ms = new MailService();
	            ms.setmail(memno, name, email);
	            
            }else if(memVO.getStatus()==1) {
               	response.sendRedirect(request.getContextPath()+"/forestage/member/successin.jsp");
       
            }     
        }
        else { 
            result = "fail";
            out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
            out.println("<BODY>你的帳號 , 密碼無效!<BR>");
            out.println("請按此重新登入 <A HREF="+request.getContextPath()+"/forestage/member/loginf.jsp>重新登入</A>");
            out.println("</BODY></HTML>");
        }
        out.write(result);
        out.flush();
        out.close();
        System.out.println(result);
    
	}
		
	}


