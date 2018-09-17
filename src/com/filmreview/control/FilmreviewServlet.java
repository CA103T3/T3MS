package com.filmreview.control;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.filmreview.model.FilmreviewService;
import com.filmreview.model.FilmreviewVO;

public class FilmreviewServlet extends HttpServlet {

	
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

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String movie_no = req.getParameter("movie_no");
				String movie_noReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
				if (movie_no == null || movie_no.trim().length() == 0) {
					errorMsgs.add("電影名稱: 請勿空白");
				} else if(!movie_no.trim().matches(movie_no)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到10之間");
	            }
				
				String title = req.getParameter("job").trim();
				if (title == null || title.trim().length() == 0) {
					errorMsgs.add("職位請勿空白");
				}
				
				
				
				Double evaluation = null;
				try {
					evaluation = new Double(req.getParameter("evaluation").trim());
				} catch (NumberFormatException e) {
					evaluation = 0.0;
					errorMsgs.add("評分請填數字.");
				}
				
				String CONTENT = null;
				if (CONTENT == null || CONTENT.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				
				Integer filmreviewno = new Integer(req.getParameter("filmreviewno").trim());

				EmpVO empVO = new EmpVO();
				empVO.setEname(ename);
				empVO.setJob(job);
				empVO.setHiredate(hiredate);
				empVO.setSal(sal);
				empVO.setComm(comm);
				empVO.setDeptno(filmreviewno);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				FilmreviewService empSvc = new FilmreviewService();
				empVO = empSvc.addEmp(ename, job, hiredate, sal, comm, deptno);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/T3MS/example/ten/filmreview/fv_writing.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
	}
}
