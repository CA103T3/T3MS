package com.activity.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig
public class ActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action =request.getParameter("action");
		
		if ("view".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			request.setAttribute("errorMsgs", errorMsgs);
			String activity_no=request.getParameter("activity_no");
			System.out.println(activity_no);
			
		}
		
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			request.setAttribute("errorMsgs", errorMsgs);
			
		}
	}

}
