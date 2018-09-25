package com.cinema.controller;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.cinema.model.CinemaVO;
import com.theater.model.TheaterService;
import com.theater.model.TheaterVO;

/**
 * Servlet implementation class CinemaServlet
 */
@WebServlet("/cinema/cinema.do")
public class CinemaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CinemaServlet() {
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
        if ("insert".equals(action)) { // from addTheater.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
                String cinema_name = req.getParameter("cinema_name").trim();
                String cinema_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,12}$";
                System.out.println("cinema_name : " + cinema_name);
                System.out.println("cinema_name length: " + cinema_name.trim().length());
                if (cinema_name == null || cinema_name.trim().length() == 0) {
                    errorMsgs.add("影城名稱: 請勿空白");
//                    System.out.println("影城名稱: 請勿空白");
                } else if(!cinema_name.trim().matches(cinema_nameReg)) { //以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("影城名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到12之間");
                }

                String cinema_engname = req.getParameter("cinema_engname").trim();
                String cinema_engnameReg = "^[(a-zA-Z\\s)]{1,20}$";
                System.out.println("cinema_engname : " + cinema_engname);
                System.out.println("cinema_engname length: " + cinema_engname.trim().length());
                if (cinema_engname == null || cinema_engname.trim().length() == 0) {
                    errorMsgs.add("影城英文名稱: 請勿空白");
                } else if(!cinema_engname.trim().matches(cinema_engnameReg)) { //以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("影城英文名稱: 只能是英文字母和空白字元, 且長度必需在1到20之間");
                }

                String cinema_address = req.getParameter("cinema_address").trim();
                String cinema_addressReg = "^[(\u4e00-\u9fa5)(0-9)]{3,30}$";
                System.out.println("cinema_address : " + cinema_address);
                System.out.println("cinema_address length: " + cinema_address.trim().length());
                if (cinema_address == null || cinema_address.trim().length() == 0) {
                    errorMsgs.add("影城地址: 請勿空白");
                } else if(!cinema_address.trim().matches(cinema_addressReg)) { //以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("影城地址: 只能是中文和數字, 且長度必需在3到30之間");
                }

                String cinema_tel = req.getParameter("cinema_tel").trim();
                String cinema_telReg = "^[(\\d-())]{9,20}$";
                System.out.println("cinema_tel : " + cinema_address);
                System.out.println("cinema_tel length: " + cinema_tel.trim().length());
                if (cinema_tel == null || cinema_tel.trim().length() == 0) {
                    errorMsgs.add("服務專線: 請勿空白");
                } else if(!cinema_tel.trim().matches(cinema_telReg)) { //以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("服務專線: 只能是數字和-(), 且長度必需在9到20之間");
                }

                String photo_title = req.getParameter("photo_title").trim();
                String photo_titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_-)]{1,15}$";
                System.out.println("photo_title : " + photo_title);
                System.out.println("photo_title length: " + photo_title.trim().length());
                if (photo_title == null || photo_title.trim().length() == 0) {
                    errorMsgs.add("圖片名稱: 請勿空白");
                } else if(!photo_title.trim().matches(photo_titleReg)) { //以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("圖片名稱: 只能是中、英文字母、數字和_-, 且長度必需在1到15之間");
                }

//                Integer t_rows = null;
//                try {
//                    t_rows = new Integer(req.getParameter("t_rows").trim());
//                } catch (NumberFormatException e) {
//                    t_rows = 5;
//                    errorMsgs.add("排數請填數字");
//                }
//
//                Integer t_columns = null;
//                try {
//                    t_columns = new Integer(req.getParameter("t_columns").trim());
//                } catch (NumberFormatException e) {
//                    t_columns = 5;
//                    errorMsgs.add("行數請填數字");
//                }

                CinemaVO cinemaVO = new CinemaVO();
                cinemaVO.setCinema_name(cinema_name);
                cinemaVO.setCinema_engname(cinema_engname);
                cinemaVO.setCinema_address(cinema_address);
                cinemaVO.setCinema_tel(cinema_tel);
                cinemaVO.setPhoto_title(photo_title);

//                cinemaVO.setIntroduction(introduction);
//                cinemaVO.setTraffic(traffic);
//                cinemaVO.setPhoto_path(photo_path);
//                cinemaVO.setActive(active);
//                cinemaVO.setState(state);

//                System.out.println("before errorMsgs.size() : " + errorMsgs.size());
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
//                    System.out.println("errorMsgs.size() : " + errorMsgs.size());
                    req.setAttribute("cinemaVO", cinemaVO); // 含有輸入格式錯誤的cinemaVO物件,也存入req
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/backstage/cinema/addCinema.jsp");
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                /***************************2.開始新增資料***************************************/
//                CinemaService cSvc = new CinemaService();
//                String cinema_no = tSvc.addCinema(cinema_name, cinema_engname, cinema_address, cinema_tel, 
//                        introduction, traffic, photo_title, photo_path, active, state);

                /***************************3.新增完成,準備轉交(Send the Success view)***********/
//                String url = "/backstage/theater/listAllTheater.jsp" + "?cinema_no=" + cinema_no;
//                System.out.println("url: " + url);
//                RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllTheater.jsp
//                successView.forward(req, res);

                /***************************其他可能的錯誤處理**********************************/
            } catch (Exception e) {
                errorMsgs.add(e.getMessage().replaceAll("\r|\n", ""));
                System.out.println("其他可能的錯誤處理");
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/backstage/cinema/addCinema.jsp");
                failureView.forward(req, res);
                e.printStackTrace();
            }
        }
	}

}
