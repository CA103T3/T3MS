package com.session.controlller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.session.model.SessionService;
import com.theater.model.TheaterService;
import com.theater.model.TheaterVO;

/**
 * Servlet implementation class SessionServlet
 */
@WebServlet("/session/session.do")
public class SessionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionServlet() {
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

        if ("delete".equals(action)) { // from listAllSession.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑

            try {
                /***************************1.接收請求參數***************************************/
               String session_no = req.getParameter("session_no");

                /***************************2.開始刪除資料***************************************/
               SessionService sSvc = new SessionService();
               sSvc.deleteSession(session_no);

                /***************************3.刪除完成,準備轉交(Send the Success view)***********/
               String url = requestURL;
               System.out.println("requestURL: " + url);
               RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
               successView.forward(req, res);

                /***************************其他可能的錯誤處理**********************************/
            } catch (Exception e) {
                errorMsgs.add("刪除資料失敗: "+e.getMessage().replaceAll("\r|\n", ""));
                RequestDispatcher failureView = req
                        .getRequestDispatcher(requestURL);
                failureView.forward(req, res);
            }
        }

        if ("get_seat_model".equals(action)) { // from addSession.jsp ajax

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
//            req.setAttribute("errorMsgs", errorMsgs);

//            String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑
            req.setCharacterEncoding("utf-8");
            res.setContentType("text/plain; charset=utf-8");
            PrintWriter out = res.getWriter();
            JSONObject json = new JSONObject();

            try {
                /***************************1.接收請求參數***************************************/
               String theater_no = req.getParameter("theater_no");

                /***************************2.查詢資料***************************************/
               TheaterService tSvc = new TheaterService();
               TheaterVO theaterVO = tSvc.getOneTheater(theater_no);

               if(theaterVO == null) {
                   json.put("status", "no data found");
                   out.print(json);
                   return;
               } else {
                   Integer t_rows = theaterVO.getT_rows();
                   json.put("t_rows", t_rows);
                   Integer t_columns = theaterVO.getT_columns();
                   json.put("t_columns", t_columns);

                   Reader model = theaterVO.getSeat_model();
                   char[] arr = new char[8 * 1024];
                   StringBuilder buffer = new StringBuilder();
                   int numCharsRead;
                   while ((numCharsRead = model.read(arr, 0, arr.length)) != -1) {
                       buffer.append(arr, 0, numCharsRead);
                   }
                   model.close();
                   String str = buffer.toString();
                   //System.out.println("buffer.toString() : " + str);
                   //json.put("seat_model", str); //{"t_rows":9,"seat_model":"{\"4_11\":[\"D_11\",\"2\"],\"4_10\":[\"D_10\",\"2\"]...
                   json.put("seat_model", new JSONObject(str)); //{"t_rows":5,"seat_model":{"1_1":["x","0"],"1_2":["A_1","2"],"2_1":["B_1","2"],"1_3":["A_2","2"]...

               }
                /***************************3.完成,回傳json***********/
               json.put("status", "done");
               //System.out.println("json.toString() : " + json.toString());
               out.print(json);

                /***************************其他可能的錯誤處理**********************************/
            } catch (Exception e) {
                errorMsgs.add("查詢資料失敗: "+e.getMessage().replaceAll("\r|\n", ""));
//                RequestDispatcher failureView = req
//                        .getRequestDispatcher(requestURL);
//                failureView.forward(req, res);
                json.put("status", "error");
                json.put("msg", errorMsgs);
                return;
            }
        }

    }

}
