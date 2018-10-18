package com.theater.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
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

import com.theater.model.TheaterService;
import com.theater.model.TheaterVO;

/**
 * Servlet implementation class TheaterServlet
 */
//@WebServlet("/theater/TheaterServlet")
public class TheaterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TheaterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * get seats from parameters
     *
     * @param  HttpServletRequest req
     * @param  int rows  // real rows
     * @param  int cols  // real columns
     * @param  JSONObject json
     * @return Integer seats
     */
    public Integer getSeats(HttpServletRequest req, int rows, int cols, JSONObject json) {
        ArrayList<Integer> row_non_seat = new ArrayList<Integer>();
        ArrayList<Integer> col_non_seat = new ArrayList<Integer>();
        int row_minus = 0, col_minus = 0; //skip sequence
        Integer seats = 0;

        // find out non-seat of row
        //for (int i = 1; i <= t_rows; i++) {
        for (int i = 1; i <= rows; i++) {
            int count = 0;
            //for (int j = 1; j <= t_columns; j++) {
            for (int j = 1; j <= cols; j++) {
                String param = "input_" + i + "_" + j;
                Integer v = Integer.parseInt(req.getParameter(param));
                if(v == 0) count++;
            }
            //set flag if all are non-seat
            //if(count == t_columns) {
            if(count == cols) {
                row_non_seat.add(1);
            } else {
                row_non_seat.add(0);
            }
        }

        System.out.println(row_non_seat.toString());

        // find out non-seat of column
        //for (int i = 1; i <= t_columns; i++) {
        for (int i = 1; i <= cols; i++) {
            int count = 0;
            //for (int j = 1; j <= t_rows; j++) {
            for (int j = 1; j <= rows; j++) {
                String param = "input_" + j + "_" + i; // j - row , i - column
                Integer v = Integer.parseInt(req.getParameter(param));
                if(v == 0) count++;
            }
            //set flag if all are non-seat
            //if(count == t_rows) {
            if(count == rows) {
                col_non_seat.add(1);
            } else {
                col_non_seat.add(0);
            }
        }
        System.out.println(col_non_seat.toString());

//        for (int i = 1; i <= t_rows; i++) {
//            for (int j = 1; j <= t_columns; j++) {
//                String param = "input_" + i + "_" + j;
//                String key = i + "_" + j;
//                String v = req.getParameter(param);
//                //System.out.println(key + " : " + v);
//                String seat;
//                if (!("0".equals(v))) {
//                    char row = (char)(64 + i);
//                    seat = row + "_" + j;
//                } else {
//                    seat = "x";
//                }
//                System.out.println(seat + " : " + v);
//                String[] values = {seat, v};
//                ArrayList<String> list  = new ArrayList<String>(Arrays.asList(values));
//                json.put(key, list);
//            }
//        }
//        System.out.println(json.length());
//        System.out.println(json.toString());

        //for (int i = 1; i <= t_rows; i++) {
        for (int i = 1; i <= rows; i++) {
            row_minus += row_non_seat.get(i - 1);
            col_minus = 0; //initial value of each column loop
            //for (int j = 1; j <= t_columns; j++) {
            for (int j = 1; j <= cols; j++) {
                col_minus += col_non_seat.get(j - 1);
                String param = "input_" + i + "_" + j;
                String key = i + "_" + j;
                String v = req.getParameter(param);
                //System.out.println(key + " : " + v);
                String seat;
                char row = (char)(64 + i - row_minus); //decrease non-seat

                if (!("0".equals(v))) {
                    int col = j - col_minus; //decrease non-seat
                    seat = row + "_" + col;
                    seats++;
                } else {
                    seat = "x";
                    if(col_non_seat.get(j - 1) == 0)
                        col_minus++;
                }
                System.out.println(seat + " : " + v);
                String[] values = {seat, v};
                ArrayList<String> list  = new ArrayList<String>(Arrays.asList(values));
                json.put(key, list);
            }
        }

        return seats;
    }

    /**
     * get real rows and columns from parameters and count parameters
     *
     * @param  HttpServletRequest req
     * @param  Set<String> set
     * @param  List<String> errorMsgs
     * @return int[] {rows, cols, paramCount}
     */
    //public int[] getRowsColsParamCount(HttpServletRequest req, Set<String> set) {
    public int[] getRowsColsParamCount(HttpServletRequest req, Set<String> set, List<String> errorMsgs) {
        Enumeration<String> enu = req.getParameterNames();
        int paramCount = 0;
        int rows = 0, cols = 0;
        int size = set.size();
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            //String[] values = req.getParameterValues(name);
            set.add(name);
            if (size != set.size()) {
                size++;
                try {
                    String[] str = name.split("_");
                    rows = Integer.valueOf(str[1]) > rows ? Integer.valueOf(str[1]) : rows;
                    System.out.println("rows: " + rows + " ,str[1]: " + str[1]);
                    cols = Integer.valueOf(str[2]) > cols ? Integer.valueOf(str[2]) : cols;
                    System.out.println("cols: " + cols + " ,str[2]: " + str[2]);
                } catch(Exception e) {
                    errorMsgs.add("參數 : " + name + ", " + e.getClass().getSimpleName() + " : " + e.getMessage());
                    int[] ret = {0, 0, 0};
                    return ret;
                }
            }
//            if(values.length > 0) {
//                out.println(name + " :");
//                for(int i = 0; i < values.length; i++) {
//                    out.println(i + " : " + values[i]);
//                }
//            }
            paramCount++;
            //System.out.println(paramCount + " : " +name);
        }
        System.out.println("rows : " + rows); //real rows
        System.out.println("cols : " + cols); //real columns
        int[] ret = {rows, cols, paramCount};
        return ret;
    }

    /**
     * @see HttpServlet#doGet(HttpServletreq req, HttpServletres res)
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    /**
     * @see HttpServlet#doPost(HttpServletreq req, HttpServletres res)
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

            //Enumeration<String> enu = req.getParameterNames();
            int paramCount = 0;
            Set<String> set = new HashSet<String>();
            set.add("cinema_no");
            set.add("theater_name");
            set.add("equipment");
            set.add("t_rows");
            set.add("t_columns");
            set.add("action");
            int exclusiveParams = set.size();
            int rows = 0, cols = 0;

            //int[] ret = getRowsColsParamCount(req, set);
            int[] ret = getRowsColsParamCount(req, set, errorMsgs);
            rows = ret[0];
            cols = ret[1];
            paramCount = ret[2];

            try {
                /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
                String theater_name = req.getParameter("theater_name").trim();
                String theater_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9-_\\s)]{1,12}$";
                System.out.println("theater_name : " + theater_name);
                System.out.println("theater_name length: " + theater_name.trim().length());
                if (theater_name == null || theater_name.trim().length() == 0) {
                    errorMsgs.add("影廳名稱: 請勿空白");
//                    System.out.println("影廳名稱: 請勿空白");
                } else if(!theater_name.trim().matches(theater_nameReg)) { //以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("影廳名稱: 只能是中、英文字母、數字和-_, 且長度必需在1到12之間");
                }

                String cinema_no = req.getParameter("cinema_no").trim();
                if (cinema_no == null || cinema_no.trim().length() == 0) {
                    errorMsgs.add("影城編號請勿空白");
                }

                String equipment = req.getParameter("equipment").trim();
                if (equipment == null || equipment.trim().length() == 0) {
                    errorMsgs.add("影廳設備請勿空白");
                }

                Integer t_rows = null;
                try {
                    t_rows = new Integer(req.getParameter("t_rows").trim());
                } catch (NumberFormatException e) {
                    t_rows = 5;
                    errorMsgs.add("排數請填數字");
                }

                Integer t_columns = null;
                try {
                    t_columns = new Integer(req.getParameter("t_columns").trim());
                } catch (NumberFormatException e) {
                    t_columns = 5;
                    errorMsgs.add("行數請填數字");
                }

                JSONObject json = new JSONObject();
                Integer seats = getSeats(req, rows , cols ,json);

                System.out.println("seats : " + seats);
                System.out.println("json.length(): " + json.length());
                System.out.println(t_rows * t_columns);
                System.out.println(json.toString());

                //do not count cinema_no, theater_name, equipment, t_rows, t_columns, action
                if((paramCount - exclusiveParams) != (t_rows * t_columns)) {
                    errorMsgs.add("排數, 行數與產生的座位數不相符");
                }

                //StringReader seat_model = new StringReader(json.toString());
                String seat_model = json.toString();

                TheaterVO theaterVO = new TheaterVO();
                theaterVO.setTheater_name(theater_name);
                theaterVO.setCinema_no(cinema_no);
                theaterVO.setT_rows(t_rows);
                theaterVO.setT_columns(t_columns);
                theaterVO.setSeat_model(seat_model);
                theaterVO.setSeats(seats);
                theaterVO.setEquipment(equipment);
//                System.out.println("before errorMsgs.size() : " + errorMsgs.size());
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
//                    System.out.println("errorMsgs.size() : " + errorMsgs.size());
                    req.setAttribute("theaterVO", theaterVO); // 含有輸入格式錯誤的theaterVO物件,也存入req
                    req.setAttribute("rows", rows);
                    req.setAttribute("cols", cols);
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/backstage/theater/addTheater.jsp");
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                /***************************2.開始新增資料***************************************/
                TheaterService tSvc = new TheaterService();
                String theater_no = tSvc.addTheater(cinema_no, theater_name,
                        t_rows, t_columns, seat_model, seats, equipment);

                /***************************3.新增完成,準備轉交(Send the Success view)***********/
                //String url = "/backstage/theater/listAllTheater.jsp" + "?cinema_no=" + cinema_no;
                String url = "/backstage/theater/listAllTheater.jsp";
                //System.out.println("url: " + url);
                RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllTheater.jsp
                successView.forward(req, res);

                /***************************其他可能的錯誤處理**********************************/
            } catch (Exception e) {
                if(e.getMessage() != null) {
                    errorMsgs.add("其他錯誤: " + e.getMessage().replaceAll("\r|\n", " "));
                } else {
                    errorMsgs.add("其他錯誤");
                }
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/backstage/theater/addTheater.jsp");
                failureView.forward(req, res);
                e.printStackTrace();
            }
        }

        if ("update".equals(action)) { // from updateTheater.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            //Enumeration<String> enu = req.getParameterNames();
            int paramCount = 0;
            Set<String> set = new HashSet<String>();
            set.add("cinema_no");
            set.add("theater_name");
            set.add("equipment");
            set.add("t_rows");
            set.add("t_columns");
            set.add("action");
            set.add("theater_no"); //different from "insert" of action
            set.add("requestURL"); //different from "insert" of action
            set.add("whichRecordIndex"); //different from "insert" of action
            int exclusiveParams = set.size();
            int rows = 0, cols = 0;

            //int[] ret = getRowsColsParamCount(req, set);
            int[] ret = getRowsColsParamCount(req, set, errorMsgs);
            rows = ret[0];
            cols = ret[1];
            paramCount = ret[2];

            try {
                /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
                String theater_name = req.getParameter("theater_name").trim();
                String theater_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9-_\\s)]{1,12}$";
                System.out.println("theater_name : " + theater_name);
                System.out.println("theater_name length: " + theater_name.trim().length());
                if (theater_name == null || theater_name.trim().length() == 0) {
                    errorMsgs.add("影廳名稱: 請勿空白");
//                    System.out.println("影廳名稱: 請勿空白");
                } else if(!theater_name.trim().matches(theater_nameReg)) { //以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("影廳名稱: 只能是中、英文字母、數字和-_, 且長度必需在1到12之間");
                }

                String theater_no = req.getParameter("theater_no").trim();
                if (theater_no == null || theater_no.trim().length() == 0) {
                    errorMsgs.add("影廳編號請勿空白");
                }

                String cinema_no = req.getParameter("cinema_no").trim();
                if (cinema_no == null || cinema_no.trim().length() == 0) {
                    errorMsgs.add("影城編號請勿空白");
                }

                String equipment = req.getParameter("equipment").trim();
                if (equipment == null || equipment.trim().length() == 0) {
                    errorMsgs.add("影廳設備請勿空白");
                }

                Integer t_rows = null;
                try {
                    t_rows = new Integer(req.getParameter("t_rows").trim());
                } catch (NumberFormatException e) {
                    t_rows = 5;
                    errorMsgs.add("排數請填數字");
                }

                Integer t_columns = null;
                try {
                    t_columns = new Integer(req.getParameter("t_columns").trim());
                } catch (NumberFormatException e) {
                    t_columns = 5;
                    errorMsgs.add("行數請填數字");
                }

                JSONObject json = new JSONObject();
                Integer seats = getSeats(req, rows , cols ,json);

                System.out.println("seats : " + seats);
                System.out.println("json.length(): " + json.length());
                System.out.println(t_rows * t_columns);
                System.out.println(json.toString());

                //do not count cinema_no, theater_name, equipment, t_rows, t_columns, action
                if((paramCount - exclusiveParams) != (t_rows * t_columns)) {
                    errorMsgs.add("排數, 行數與產生的座位數不相符");
                }

                //StringReader seat_model = new StringReader(json.toString());
                String seat_model = json.toString();

                TheaterVO theaterVO = new TheaterVO();
                theaterVO.setTheater_name(theater_name);
                theaterVO.setTheater_no(theater_no);  //different from "insert" of action
                theaterVO.setCinema_no(cinema_no);
                theaterVO.setT_rows(t_rows);
                theaterVO.setT_columns(t_columns);
                theaterVO.setSeat_model(seat_model);
                theaterVO.setSeats(seats);
                theaterVO.setEquipment(equipment);
//                System.out.println("before errorMsgs.size() : " + errorMsgs.size());
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
//                    System.out.println("errorMsgs.size() : " + errorMsgs.size());
                    req.setAttribute("theaterVO", theaterVO); // 含有輸入格式錯誤的theaterVO物件,也存入req
                    req.setAttribute("rows", rows);
                    req.setAttribute("cols", cols);
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/backstage/theater/updateTheater.jsp");
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                /***************************2.開始修改資料*****************************************/
                TheaterService tSvc = new TheaterService();
                tSvc.updateTheater(theater_no, cinema_no, theater_name,
                        t_rows, t_columns, seat_model, seats, equipment);

                /***************************3.修改完成,準備轉交(Send the Success view)***********/
//                String url = "/backstage/theater/listAllTheater.jsp" + "?cinema_no=" + cinema_no;
//                System.out.println("url: " + url);
                String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑
                String url = requestURL;
                RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
                successView.forward(req, res);

                /***************************其他可能的錯誤處理**********************************/
            } catch (Exception e) {
                if(e.getMessage() != null) {
                    errorMsgs.add("修改資料失敗: " + e.getMessage().replaceAll("\r|\n", " "));
                } else {
                    errorMsgs.add("修改資料失敗");
                }
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/backstage/theater/updateTheater.jsp");
                failureView.forward(req, res);
            }
        }

        if ("delete".equals(action)) { // from listAllTheater.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑

            try {
                /***************************1.接收請求參數***************************************/
               String theater_no = req.getParameter("theater_no");

                /***************************2.開始刪除資料***************************************/
               TheaterService tSvc = new TheaterService();
//               TheaterVO theaterVO = tSvc.getOneTheater(theater_no);
               tSvc.deleteTheater(theater_no);

                /***************************3.刪除完成,準備轉交(Send the Success view)***********/
               // DeptService deptSvc = new DeptService();
               // if(requestURL.equals("/dept/listEmps_ByDeptno.jsp") || requestURL.equals("/dept/listAllDept.jsp"))
                   // req.setAttribute("listEmps_ByDeptno",deptSvc.getEmpsByDeptno(empVO.getDeptno())); // 資料庫取出的list物件,存入request

               String url = requestURL;
               System.out.println("requestURL: " + url);
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

        if ("view".equals(action)) { // from listAllTheater.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);
            String requestURL = req.getParameter("requestURL");

            try {
                /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
                String theater_no = req.getParameter("theater_no");
                if (theater_no == null || (theater_no.trim()).length() == 0) {
                    errorMsgs.add("請輸入影廳編號");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher(requestURL);
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                /***************************2.開始查詢資料*****************************************/
                TheaterService tSvc = new TheaterService();
                TheaterVO theaterVO = tSvc.getOneTheater(theater_no);
                if (theaterVO == null) {
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
                req.setAttribute("theaterVO", theaterVO); // 資料庫取出的theaterVO物件,存入req
                String url = "/backstage/theater/listOneTheater.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneTheater.jsp
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

        if ("toUpdatePage".equals(action)) { // from listAllTheater.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑

            try {
                /***************************1.接收請求參數****************************************/
                String theater_no = req.getParameter("theater_no");
                if (theater_no == null || (theater_no.trim()).length() == 0) {
                    errorMsgs.add("請輸入影廳編號");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher(requestURL);
                    failureView.forward(req, res);
                    return;//程式中斷
                }
                /***************************2.開始查詢資料****************************************/
                TheaterService tSvc = new TheaterService();
                TheaterVO theaterVO = tSvc.getOneTheater(theater_no);
                if (theaterVO == null) {
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
                req.setAttribute("theaterVO", theaterVO); // 資料庫取出的theaterVO物件,存入req
                String url = "/backstage/theater/updateTheater.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交updateTheater.jsp
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
