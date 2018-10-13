package com.member.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.*;


@WebServlet("/ServletMemberBackstageController")
public class ServletMemberBackstage extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
        res.setContentType("text/html; charset=utf-8");
        //PrintWriter out = res.getWriter();
        HttpSession session = req.getSession();
        String action = req.getParameter("action");
			
		
		if ("view".equals(action)) { // from listAllMember.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);
            String requestURL = req.getParameter("requestURL");

            try {
                /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
                String memno = req.getParameter("memno");
                if (memno == null || (memno.trim()).length() == 0) {
                    errorMsgs.add("請輸入會員編號");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher(requestURL);
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                /***************************2.開始查詢資料*****************************************/
                MemService memSrc = new MemService();
                MemVO memVO = memSrc.getMemVOByNO(memno);
                if (memVO == null) {
                    errorMsgs.add("查無資料");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher(requestURL);
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                /***************************3.查詢完成,準備轉交(Send the Success view)*************/
                req.setAttribute("memVO", memVO); // 資料庫取出的theaterVO物件,存入req
                String url = "/backstage/member/listOneMember.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneTheater.jsp
                successView.forward(req, res);

                /***************************其他可能的錯誤處理*************************************/
            } catch (Exception e) {
                errorMsgs.add("無法取得資料:" + e.getMessage().replaceAll("\r|\n", ""));
                RequestDispatcher failureView = req
                        .getRequestDispatcher("requestURL");
                failureView.forward(req, res);
            }
        }
		
		
		if ("ban".equals(action)) { // from listAllMember.jsp

            List<String> errorMsgs = new LinkedList<String>();
            
            req.setAttribute("errorMsgs", errorMsgs);
            String requestURL = req.getParameter("requestURL");
            System.out.println(requestURL);

            try {
                /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
                String memno = req.getParameter("memno");
                System.out.println(memno);
                if (memno == null || (memno.trim()).length() == 0) {
                    errorMsgs.add("請輸入會員編號");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher(requestURL);
                    failureView.forward(req, res);
                    return;//程式中斷
                }
                
                /***************************2.開始查詢資料*****************************************/
                MemService memSrc = new MemService();
                memSrc.ban(memno);
                System.out.println(errorMsgs);
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher(requestURL);
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                /***************************3.查詢完成,準備轉交(Send the Success view)*************/
//                req.setAttribute("memVO", memVO); // 資料庫取出的theaterVO物件,存入req
                String url = "/backstage/member/listAllMember.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listALLmember.jsp
                successView.forward(req, res);

                /***************************其他可能的錯誤處理*************************************/
            } catch (Exception e) {
                errorMsgs.add("無法取得資料:" + e.getMessage().replaceAll("\r|\n", ""));
                RequestDispatcher failureView = req
                        .getRequestDispatcher(requestURL);
                failureView.forward(req, res);
            }
        }
		
		
		if ("unban".equals(action)) { // from listAllMember.jsp

            List<String> errorMsgs = new LinkedList<String>();
            
            req.setAttribute("errorMsgs", errorMsgs);
            String requestURL = req.getParameter("requestURL");
            System.out.println(requestURL);

            try {
                /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
                String memno = req.getParameter("memno");
                System.out.println(memno);
                if (memno == null || (memno.trim()).length() == 0) {
                    errorMsgs.add("請輸入會員編號");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher(requestURL);
                    failureView.forward(req, res);
                    return;//程式中斷
                }
                
                /***************************2.開始查詢資料*****************************************/
                MemService memSrc = new MemService();
                memSrc.unban(memno);
                System.out.println(errorMsgs);
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher(requestURL);
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                /***************************3.查詢完成,準備轉交(Send the Success view)*************/
//                req.setAttribute("memVO", memVO); // 資料庫取出的theaterVO物件,存入req
                String url = "/backstage/member/listAllMember.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listALLmember.jsp
                successView.forward(req, res);

                /***************************其他可能的錯誤處理*************************************/
            } catch (Exception e) {
                errorMsgs.add("無法取得資料:" + e.getMessage().replaceAll("\r|\n", ""));
                RequestDispatcher failureView = req
                        .getRequestDispatcher(requestURL);
                failureView.forward(req, res);
            }
        }
		
		//影評
		if ("wnatbeFC".equals(action)) { // from listAllMember.jsp

            List<String> errorMsgs = new LinkedList<String>();
            
            req.setAttribute("errorMsgs", errorMsgs);
            

            try {
                /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
                String memno = req.getParameter("memno");
                System.out.println(memno);
                if (memno == null || (memno.trim()).length() == 0) {
                    errorMsgs.add("請輸入會員編號");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/forestage/filmreview/fv_home.jsp");
                    failureView.forward(req, res);
                    return;//程式中斷
                }
                
                /***************************2.開始查詢資料*****************************************/
                MemService memSrc = new MemService();
                memSrc.wanttobeFC(memno);
                MemVO memVO = memSrc.getMemVOByNO(memno);
                System.out.println(memVO.getType());
                System.out.println(errorMsgs);
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/forestage/filmreview/fv_home.jsp");
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                /***************************3.查詢完成,準備轉交(Send the Success view)*************/
                req.setAttribute("memVO", memVO); 
                String url = "/forestage/filmreview/fv_home.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); 
                successView.forward(req, res);

                /***************************其他可能的錯誤處理*************************************/
            } catch (Exception e) {
            	e.printStackTrace();
//                errorMsgs.add("無法取得資料:" + e.getMessage().replaceAll("\r|\n", ""));
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/forestage/filmreview/fv_home.jsp");
                failureView.forward(req, res);
            }
        }
		
		if ("becomeFC".equals(action)) { // from listAllMember.jsp

            List<String> errorMsgs = new LinkedList<String>();
            
            req.setAttribute("errorMsgs", errorMsgs);
            String requestURL = req.getParameter("requestURL");
            System.out.println(requestURL);

            try {
                /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
                String memno = req.getParameter("memno");
                System.out.println(memno);
                if (memno == null || (memno.trim()).length() == 0) {
                    errorMsgs.add("請輸入會員編號");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher(requestURL);
                    failureView.forward(req, res);
                    return;//程式中斷
                }
                
                /***************************2.開始查詢資料*****************************************/
                MemService memSrc = new MemService();
                memSrc.becomeFC(memno);
                MemVO memVO = memSrc.getMemVOByNO(memno);
                System.out.println(errorMsgs);
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher(requestURL);
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                /***************************3.查詢完成,準備轉交(Send the Success view)*************/
//                req.setAttribute("memVO", memVO); // 資料庫取出的theaterVO物件,存入req
                String url = "/backstage/member/becomeFilmCriticism.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listALLmember.jsp
                successView.forward(req, res);

                /***************************其他可能的錯誤處理*************************************/
            } catch (Exception e) {
                errorMsgs.add("無法取得資料:" + e.getMessage().replaceAll("\r|\n", ""));
                RequestDispatcher failureView = req
                        .getRequestDispatcher(requestURL);
                failureView.forward(req, res);
            }
        }
	
		
		
	}
	
}
