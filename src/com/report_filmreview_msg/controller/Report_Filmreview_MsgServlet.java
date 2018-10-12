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
import com.member.model.MemService;
import com.report_filmreview_msg.model.Report_Filmreview_MsgService;
import com.report_filmreview_msg.model.Report_Filmreview_MsgVO;

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

			System.out.println("=====================87========================");

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
							
				String fr_no = req.getParameter("fr_no");
				System.out.println(fr_no);
				String frm_no = req.getParameter("frm_no");
				System.out.println(frm_no);
				String content = req.getParameter("content");
				System.out.println(content);
				String mem_no = req.getParameter("mem_no");
				System.out.println(mem_no);
				
				Report_Filmreview_MsgVO rmsgVO = new Report_Filmreview_MsgVO();
				
				rmsgVO.setFrm_no(frm_no);
				rmsgVO.setContent(content);
				rmsgVO.setMem_no(mem_no);
				
				/***************************2.開始新增資料***************************************/
				Report_Filmreview_MsgService rfvmSvc = new Report_Filmreview_MsgService();
				rmsgVO = rfvmSvc.insert(frm_no, mem_no, content);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				String url = "/forestage/filmreview/fv.jsp?fr_no="+fr_no;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				System.out.println("ERRRRRRRRRRRRRROR");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forestage/filmreview/fv.jsp");
				failureView.forward(req, res);
			}
		}
	 
	 
	 if ("cancel".equals(action)) { // 來自addEmp.jsp的請求  

			System.out.println("=====================88========================");

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
							
				String rfm_no = req.getParameter("rfm_no");
				System.out.println(rfm_no);
				
				

				
				/***************************2.開始新增資料***************************************/
				Report_Filmreview_MsgService rfvmSvc = new Report_Filmreview_MsgService();
				rfvmSvc.delete(rfm_no);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				String url = "/T3MS/backstage/reportMsg/reportMsg_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				System.out.println("ERRRRRRRRRRRRRROR");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backstage/reportMsg/reportMsg_list.jsp");
				failureView.forward(req, res);
			}
		}
	 
	 
	 
	 
	 if ("delete".equals(action)) { // 來自addEmp.jsp的請求  

			System.out.println("=====================89========================");

//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
							
				String rfm_no = req.getParameter("rfm_no");
				System.out.println(rfm_no);
				Report_Filmreview_MsgService rfvmSvc = new Report_Filmreview_MsgService();
				Report_Filmreview_MsgVO rmsgVO = rfvmSvc.getAllByRfmNo(rfm_no);
				

				
				/***************************2.開始新增資料***************************************/
				Filmreview_MsgService fvmSvc = new Filmreview_MsgService();
				Filmreview_MsgVO fmVO =fvmSvc.getVO(rmsgVO.getFrm_no());
			
				
				MemService memSrc = new MemService();
		
				memSrc.foul(fmVO.getMem_no());
				fvmSvc.delete(rmsgVO.getFrm_no());
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				String url = "/backstage/reportMsg/reportMsg_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				System.out.println("ERRRRRRRRRRRRRROR");
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/backstage/reportMsg/reportMsg_list.jsp");
//				failureView.forward(req, res);
//			}
		}	 
	 
	 
	 
	 
	 
	 
	 
	 
}
}
