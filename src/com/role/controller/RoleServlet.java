package com.role.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.permission.model.PermissionService;
import com.role.model.RoleService;
import com.role.model.RoleVO;


public class RoleServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		
	
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

				String br_name = req.getParameter("br_name");
				System.out.println("name="+br_name);
				
				String[] listitem_no = req.getParameterValues("listitem_no");
				System.out.println(listitem_no.toString());
				System.out.println("[2]="+listitem_no[2]);

				if (!errorMsgs.isEmpty()) {
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backstage/role/role_insert.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				RoleService rSvc = new RoleService();
				rSvc.addRole(br_name);
				RoleVO roleVO = rSvc.find(br_name);
				System.out.println(listitem_no.length);
				System.out.println("nO="+roleVO.getBr_no());
				PermissionService pSvc = new PermissionService();
				for(int i=0;i<listitem_no.length;i++) {
					pSvc.add(listitem_no[i], roleVO.getBr_no());
				}
			
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backstage/role/role_insert.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/backstage/role/role_insert.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
	}

}
