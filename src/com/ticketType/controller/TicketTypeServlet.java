package com.ticketType.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.cinema.model.CinemaService;
import com.cinema.model.CinemaVO;
import com.common.util.ImageUtil;
import com.common.util.UUIDGenerator;
import com.session.model.SessionService;
import com.session.model.SessionVO;
import com.theater.model.TheaterService;
import com.theater.model.TheaterVO;
import com.ticketType.model.TypeService;
import com.ticketType.model.TypeVO;

/**
 * Servlet implementation class ticketType
 */
//@WebServlet("/ticketType/ticketType.do")
public class TicketTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html; charset=utf-8");
        //PrintWriter out = res.getWriter();

        String action = req.getParameter("action");
        if ("insert".equals(action)) { // from addTicketType.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
                String cinema_no = req.getParameter("cinema_no");
                if (cinema_no == null || cinema_no.trim().length() == 0) {
                    errorMsgs.add("無影城編號資料");
                }

                String theater_no = req.getParameter("theater_no");
                if (theater_no == null || theater_no.trim().length() == 0) {
                    errorMsgs.add("影廳名稱請勿空白");
                }

                String identify = req.getParameter("identify");
                String identifyReg = "^[(A-Z)]{5,13}$";
                if (identify == null || identify.trim().length() == 0) {
                    errorMsgs.add("購票身分: 請勿空白");
                } else if(!identify.trim().matches(identifyReg)) { //以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("購票身分: 請選擇正確購票身分");
                }

                String time = req.getParameter("time");
                String timeReg = "^[(A-Z)]{6,7}$";
                if (time == null || time.trim().length() == 0) {
                    errorMsgs.add("放映時段: 請勿空白");
                } else if(!time.trim().matches(timeReg)) { //以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("放映時段: 請選擇正確放映時段");
                }

                String priceStr = req.getParameter("price");
                String priceStrReg = "^[(0-9)]{1,4}$";
                Integer price = null;
                if (priceStr == null || priceStr.trim().length() == 0) {
                    errorMsgs.add("票價: 請勿空白");
                } else if(!priceStr.trim().matches(priceStrReg)) { //以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("票價: 請確認數字及長度");
                } else {
                    try {
                        price = new Integer(priceStr);
                        if(price.intValue() < 100 || price.intValue() > 1000) {
                            errorMsgs.add("票價: 請輸入正確金額");
                        }
                    } catch(NumberFormatException e) {
                        errorMsgs.add("票價: 請輸入正確數字格式");
                    }
                }

                TypeVO typeVO = new TypeVO();
                typeVO.setTheater_no(theater_no);
                typeVO.setIdentify(identify);
                typeVO.setTime(time);
                typeVO.setPrice(price);

//                System.out.println("before errorMsgs.size() : " + errorMsgs.size());
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
//                    System.out.println("errorMsgs.size() : " + errorMsgs.size());
                    req.setAttribute("typeVO", typeVO); // 含有輸入格式錯誤的typeVO物件,也存入req
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/backstage/ticketType/addTicketType.jsp");
                            //.getRequestDispatcher("/backstage/ticketType/addTicketType.jsp?cinema_no=" + cinema_no);
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                /***************************2.開始新增資料***************************************/
                TypeService tSvc = new TypeService();
                String type_no = tSvc.addType(theater_no, identify, time, price);

                /***************************3.新增完成,準備轉交(Send the Success view)***********/
                String url = "/backstage/ticketType/listAllTicketType.jsp?cinema_no=" + cinema_no;
                System.out.println("url: " + url);
                RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllCinema.jsp
                successView.forward(req, res);

                /***************************其他可能的錯誤處理**********************************/
            } catch (Exception e) {
                if(e.getMessage() != null) {
                    errorMsgs.add("其他錯誤: " + e.getMessage().replaceAll("\r|\n", " "));
                } else {
                    errorMsgs.add("其他錯誤");
                }
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/backstage/ticketType/addTicketType.jsp");
                        //.getRequestDispatcher("/backstage/ticketType/addTicketType.jsp?cinema_no=" + cinema_no);
                failureView.forward(req, res);
                e.printStackTrace();
            }
        }

        if ("update".equals(action)) { // from updateTicketType.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
                String cinema_no = req.getParameter("cinema_no");
                if (cinema_no == null || cinema_no.trim().length() == 0) {
                    errorMsgs.add("無影城編號資料");
                }

                String type_no = req.getParameter("type_no");
                if (type_no == null || type_no.trim().length() == 0) {
                    errorMsgs.add("無票種票價編號資料");
                }

                String theater_no = req.getParameter("theater_no");
                if (theater_no == null || theater_no.trim().length() == 0) {
                    errorMsgs.add("影廳名稱請勿空白");
                }
                TheaterService tSvc = new TheaterService();
                TheaterVO theaterVO = tSvc.getOneTheater(theater_no);
                if(theaterVO == null) {
                    errorMsgs.add("查無影廳資料,請查新操作");
                    String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑
                    String url = requestURL;
                    RequestDispatcher successView = req.getRequestDispatcher(url); // 查無影廳資料 轉交回送出修改的來源網頁
                    successView.forward(req, res);
                    return;
                }

                String identify = req.getParameter("identify");
                String identifyReg = "^[(A-Z)]{5,13}$";
                if (identify == null || identify.trim().length() == 0) {
                    errorMsgs.add("購票身分: 請勿空白");
                } else if(!identify.trim().matches(identifyReg)) { //以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("購票身分: 請選擇正確購票身分");
                }

                String time = req.getParameter("time");
                String timeReg = "^[(A-Z)]{6,7}$";
                if (time == null || time.trim().length() == 0) {
                    errorMsgs.add("放映時段: 請勿空白");
                } else if(!time.trim().matches(timeReg)) { //以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("放映時段: 請選擇正確放映時段");
                }

                String priceStr = req.getParameter("price");
                String priceStrReg = "^[(0-9)]{1,4}$";
                Integer price = null;
                if (priceStr == null || priceStr.trim().length() == 0) {
                    errorMsgs.add("票價: 請勿空白");
                } else if(!priceStr.trim().matches(priceStrReg)) { //以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("票價: 請確認數字及長度");
                } else {
                    try {
                        price = new Integer(priceStr);
                        if(price.intValue() < 100 || price.intValue() > 1000) {
                            errorMsgs.add("票價: 請輸入正確金額");
                        }
                    } catch(NumberFormatException e) {
                        errorMsgs.add("票價: 請輸入正確數字格式");
                    }
                }

                TypeVO typeVO = new TypeVO();
                typeVO.setType_no(type_no);
                typeVO.setTheater_no(theater_no);
                typeVO.setIdentify(identify);
                typeVO.setTime(time);
                typeVO.setPrice(price);

//                System.out.println("before errorMsgs.size() : " + errorMsgs.size());
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
//                    System.out.println("errorMsgs.size() : " + errorMsgs.size());
                    req.setAttribute("typeVO", typeVO); // 含有輸入格式錯誤的typeVO物件,也存入req
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/backstage/ticketType/updateTicketType.jsp");
                            //.getRequestDispatcher("/backstage/ticketType/addTicketType.jsp?cinema_no=" + cinema_no);
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                /***************************2.開始新增資料***************************************/
                TypeService typeSvc = new TypeService();
                typeSvc.updateType(type_no, theater_no, identify, time, price);

                /***************************3.新增完成,準備轉交(Send the Success view)***********/
                String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑
                String url = requestURL;
                RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交回送出修改的來源網頁
                successView.forward(req, res);

                /***************************其他可能的錯誤處理**********************************/
            } catch (Exception e) {
                if(e.getMessage() != null) {
                    errorMsgs.add("其他錯誤: " + e.getMessage().replaceAll("\r|\n", " "));
                } else {
                    errorMsgs.add("其他錯誤");
                }
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/backstage/ticketType/updateTicketType.jsp");
                        //.getRequestDispatcher("/backstage/ticketType/addTicketType.jsp?cinema_no=" + cinema_no);
                failureView.forward(req, res);
                e.printStackTrace();
            }
        }

        if ("delete".equals(action)) { // from listAllTicketType.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑

            try {
                /***************************1.接收請求參數***************************************/
               String type_no = req.getParameter("type_no");

                /***************************2.開始刪除資料***************************************/
               TypeService tSvc = new TypeService();
               tSvc.deleteType(type_no);

                /***************************3.刪除完成,準備轉交(Send the Success view)***********/
               // DeptService deptSvc = new DeptService();
               // if(requestURL.equals("/dept/listEmps_ByDeptno.jsp") || requestURL.equals("/dept/listAllDept.jsp"))
                   // req.setAttribute("listEmps_ByDeptno",deptSvc.getEmpsByDeptno(empVO.getDeptno())); // 資料庫取出的list物件,存入request

               String url = requestURL;
               //System.out.println("requestURL: " + url);
               RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
               successView.forward(req, res);

                /***************************其他可能的錯誤處理**********************************/
            } catch (Exception e) {
                if(e.getMessage() != null) {
                    errorMsgs.add("刪除資料失敗: " + e.getMessage().replaceAll("\r|\n", " "));
                } else {
                    errorMsgs.add("刪除資料失敗");
                }
                RequestDispatcher failureView = req
                        .getRequestDispatcher(requestURL);
                failureView.forward(req, res);
            }
        }

        if ("view".equals(action)) { // from listAllTicketType.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);
            String requestURL = req.getParameter("requestURL");

            try {
                /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
                String type_no = req.getParameter("type_no");
                if (type_no == null || (type_no.trim()).length() == 0) {
                    errorMsgs.add("缺少票種票價編號資料");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher(requestURL);
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                /***************************2.開始查詢資料*****************************************/
                TypeService tSvc = new TypeService();
                TypeVO typeVO = tSvc.getOneTypeJoinTheater(type_no);
                if (typeVO == null) {
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
                req.setAttribute("typeVO", typeVO); // 資料庫取出的typeVO物件,存入req
                String url = "/backstage/ticketType/listOneTicketType.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneSession.jsp
                successView.forward(req, res);

                /***************************其他可能的錯誤處理*************************************/
            } catch (Exception e) {
                if(e.getMessage() != null) {
                    errorMsgs.add("無法取得資料: " + e.getMessage().replaceAll("\r|\n", " "));
                } else {
                    errorMsgs.add("無法取得資料");
                }
                RequestDispatcher failureView = req
                        .getRequestDispatcher("requestURL");
                failureView.forward(req, res);
            }
        }

        if ("toUpdatePage".equals(action)) { // from listAllTicketType.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑

            try {
                /***************************1.接收請求參數****************************************/
                String type_no = req.getParameter("type_no");
                if (type_no == null || (type_no.trim()).length() == 0) {
                    errorMsgs.add("無票種票價編號");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher(requestURL);
                    failureView.forward(req, res);
                    return;//程式中斷
                }
                /***************************2.開始查詢資料****************************************/
                TypeService tSvc = new TypeService();
                TypeVO typeVO = tSvc.getOneTypeJoinTheater(type_no);
                if (typeVO == null) {
                    errorMsgs.add("查無資料");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher(requestURL);
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                /***************************3.查詢完成,準備轉交(Send the Success view)************/
                req.setAttribute("typeVO", typeVO); // 資料庫取出的typeVO物件,存入req
                String url = "/backstage/ticketType/updateTicketType.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交updateSession.jsp
                successView.forward(req, res);

                /***************************其他可能的錯誤處理************************************/
            } catch (Exception e) {
                if(e.getMessage() != null) {
                    errorMsgs.add("修改資料取出時失敗: " + e.getMessage().replaceAll("\r|\n", " "));
                } else {
                    errorMsgs.add("修改資料取出時失敗");
                }
                RequestDispatcher failureView = req
                        .getRequestDispatcher(requestURL);
                failureView.forward(req, res);
            }
        }
	}

}
