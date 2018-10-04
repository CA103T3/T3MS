package com.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemVO;

@WebServlet("/MailAgain")
public class MailAgain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		 HttpSession session = req.getSession();
		
		 MemVO memVO=(MemVO) session.getAttribute("memVO");
		 String email = memVO.getEmail();
		 String memno = memVO.getmemno();
         String lname = memVO.getLastname();
         String fname = memVO.getFirstname();
         String name = lname+fname;
         req.setAttribute("memno", memno);
         req.setAttribute("email", email);
		 req.setAttribute("vested", 1);
         
         MailService ms = new MailService();
         ms.setmail(memno, name, email);
         String url ="/forestage/member/waitformail.jsp";
         RequestDispatcher successView = req.getRequestDispatcher(url); 
         successView.forward(req, res);
	}

}
