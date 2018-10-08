package com.report_filmreview_msg.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.filmreview_msg.model.Filmreview_MsgService;
import com.filmreview_msg.model.Filmreview_MsgVO;

public class Report_Filmreview_MsgServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
	
	 if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("=============================================");

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			
				
				
				
				String frm_no = req.getParameter("frm_no");
				System.out.println(frm_no);
				
				String content = req.getParameter("content").trim();
				System.out.println(content);
				String mem_no = req.getParameter("mem_no").trim();
				System.out.println(mem_no);
				Filmreview_MsgVO filmreview_msgVO = new Filmreview_MsgVO();
				
				filmreview_msgVO.setFr_no(frm_no);
				filmreview_msgVO.setMem_no(mem_no);	
				filmreview_msgVO.setContent(content);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("filmreview_msgVO", filmreview_msgVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forestage/filmreview/fv.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Filmreview_MsgService fvmSvc = new Filmreview_MsgService();
				filmreview_msgVO = fvmSvc.insertFilmrevew_Msg(frm_no, mem_no, content);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				String urll = "/forestage/filmreview/fv.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(urll); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forestage/filmreview/fv.jsp");
				failureView.forward(req, res);
			}
		}
}
}
