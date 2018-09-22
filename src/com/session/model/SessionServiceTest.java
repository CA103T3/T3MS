package com.session.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class SessionServiceTest
 */
@WebServlet("/SessionServiceTest")
public class SessionServiceTest extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SessionService sSvc;
    PrintWriter out;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionServiceTest() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");

        out = response.getWriter();
        out.append("Served at: ").append(request.getContextPath()).append("\n");
        sSvc = new SessionService();
        String session_no = testAddSession();
    }
    
    //INSERT INTO MOVIE (movie_no) values ('MV0001');
    public String testAddSession() {
        String theater_no = "T00001";
        String movie_no = "MV0001";
        //SimpleDateFormat parse java.util.Date -> getTime() -> java.sql.Timestamp
        String date = "2018-9-31 14:30:05";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date utilDate = null;
        try {
            utilDate = dateFormat.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Timestamp session_time = new Timestamp(utilDate.getTime());
        
        String seat_table = "This is the CLOB";

        String session_no = sSvc.addSession(theater_no, movie_no, session_time, seat_table);

        out.println("Add session_no : " + session_no);
        return session_no;
    }
}
