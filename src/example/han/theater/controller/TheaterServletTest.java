package example.han.theater.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.theater.model.TheaterVO;

/**
 * Servlet implementation class TheaterServlet
 */
@WebServlet("/theater/TheaterServletTest")
public class TheaterServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TheaterServletTest() {
        super();
        // TODO Auto-generated constructor stub
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
		PrintWriter out = res.getWriter();
//        Enumeration enu = req.getParameterNames();
//        while (enu.hasMoreElements()) {
//            String name = (String) enu.nextElement();
//            String[] values = req.getParameterValues(name);
//
//            if(values.length > 0) {
//                out.println(name + " :");
//                for(int i = 0; i < values.length; i++) {
//                    out.println(i + " : " + values[i]);
//                }
//            }
//        }
		String action = req.getParameter("action");
		
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
            
            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
                String theater_name = req.getParameter("theater_name");
                String theater_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,12}$";
                if (theater_name == null || theater_name.trim().length() == 0) {
                    errorMsgs.add("影廳名稱: 請勿空白");
//                    System.out.println("影廳名稱: 請勿空白");
                } else if(!theater_name.trim().matches(theater_nameReg)) { //以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("影廳名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到12之間");
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
                    errorMsgs.add("排數請填數字.");
                }
                
                Integer t_columns = null;
                try {
                    t_columns = new Integer(req.getParameter("t_columns").trim());
                } catch (NumberFormatException e) {
                    t_columns = 5;
                    errorMsgs.add("行數請填數字.");
                }

                TheaterVO theaterVO = new TheaterVO();
                theaterVO.setTheater_name(theater_name);
                theaterVO.setCinema_no(cinema_no);
                theaterVO.setT_rows(t_rows);
                theaterVO.setT_columns(t_columns);
                theaterVO.setEquipment(equipment);
//                System.out.println("before errorMsgs.size() : " + errorMsgs.size());
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
//                    System.out.println("errorMsgs.size() : " + errorMsgs.size());
                    req.setAttribute("theaterVO", theaterVO); // 含有輸入格式錯誤的empVO物件,也存入req
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/example/han/theater/addTheater.jsp");
                    failureView.forward(req, res);
                    return;
                }
                
                /***************************2.開始新增資料***************************************/
//                TheaterService tSvc = new TheaterService();
//                String theater_no = tSvc.addTheater(cinema_no, theater_name,
//                        t_rows, t_columns, seat_model, seats, equipment);
                
                /***************************3.新增完成,準備轉交(Send the Success view)***********/
//                String url = "/emp/listAllEmp.jsp";
//                RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//                successView.forward(req, res);
                
                /***************************其他可能的錯誤處理**********************************/
            } catch (Exception e) {
                errorMsgs.add(e.getMessage());
                System.out.println("其他可能的錯誤處理");
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/example/han/theater/addTheater.jsp");
                failureView.forward(req, res);
            }
        }
		
	}

}
