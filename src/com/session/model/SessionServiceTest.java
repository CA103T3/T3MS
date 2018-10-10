package com.session.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.theater.model.*;



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
        testGetOneSession(session_no);

        session_no = testAddSession();
        testGetOneSession(session_no);
        testUpdateSession(session_no);
        testGetOneSession(session_no);
        testGetOneofJoinTheaterMovieWhereSessionNo(session_no);

        session_no = testAddSession();
        testGetAll();
        testDeleteSession(session_no);
        testGetAll();
        String theater_no = "T00001";
        testGetAllofTheater(theater_no);
        String cinema_no = "C001";
        testGetAllofJoinTheaterMovieWhereTheaterNoCinema(cinema_no);
        testGetAllBySessionTimeMovieNo();

    }

    //INSERT INTO MOVIE (movie_no) values ('MV0001');
    public String testAddSession() throws IOException {
        String theater_no = "T00001";
        String movie_no = "MV0001";
        //SimpleDateFormat parse java.util.Date -> getTime() -> java.sql.Timestamp
        String date = "2018-9-30 14:30:05";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
        Date utilDate = null;
        try {
            utilDate = dateFormat.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Timestamp session_time = new Timestamp(utilDate.getTime());

        TheaterService tSvc = new TheaterService();
        TheaterVO tVO = tSvc.getOneTheater(theater_no);
        String seat_table = null;
        if(tVO != null) {
//            Reader model = tVO.getSeat_model();
//            char[] arr = new char[8 * 1024];
//            StringBuilder buffer = new StringBuilder();
//            int numCharsRead;
//            while ((numCharsRead = model.read(arr, 0, arr.length)) != -1) {
//                buffer.append(arr, 0, numCharsRead);
//            }
//
//            model.close();
//            seat_table = buffer.toString();
            seat_table = tVO.getSeat_model();
        }

        String session_no = sSvc.addSession(theater_no, movie_no, session_time, seat_table);

        out.println("Add session_no : " + session_no);
        out.println("    utilDate : " + utilDate);
        return session_no;
    }

    public void testGetOneSession(String session_no) {
        SessionVO sessionVO = sSvc.getOneSession(session_no);
        out.println("Session_no : " + sessionVO.getSession_no());
        out.println("Theater_no : " + sessionVO.getTheater_no());
        out.println("Movie_no : " + sessionVO.getMovie_no());
        out.println("Session_time : " + sessionVO.getSession_time());
        Timestamp session_time = sessionVO.getSession_time();
        Date date = new Date(session_time.getTime());
        out.println("Timestamp convert to java.util.Date : " + date);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = dateFormat.format(date);
        out.println("converted java.util.Date to String : " + strDate);
        out.println("Seat_tabl : " + sessionVO.getSeat_table());

        //oracle sql
        //select to_char(SESSION_TIME, 'YYYY-MM-DD HH24:MI:SS TZR') from MOVIE_SESSION;
        //'2018-09-30 14:30:05 ASIA/TAIPEI'
    }

    //INSERT INTO MOVIE (movie_no) values ('MV0002');
    public void testUpdateSession(String session_no) throws IOException {
        String theater_no = "T00002";
        String movie_no = "MV0002";
        //SimpleDateFormat parse java.util.Date -> getTime() -> java.sql.Timestamp
        String date = "2018-10-15 09:00:30";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
        Date utilDate = null;
        try {
            utilDate = dateFormat.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Timestamp session_time = new Timestamp(utilDate.getTime());

        TheaterService tSvc = new TheaterService();
        TheaterVO tVO = tSvc.getOneTheater(theater_no);
        String seat_table = null;
        if(tVO != null) {
//            Reader model = tVO.getSeat_model();
//            char[] arr = new char[8 * 1024];
//            StringBuilder buffer = new StringBuilder();
//            int numCharsRead;
//            while ((numCharsRead = model.read(arr, 0, arr.length)) != -1) {
//                buffer.append(arr, 0, numCharsRead);
//            }
//
//            model.close();
//            seat_table = buffer.toString();
            seat_table = tVO.getSeat_model();
        }

        sSvc.updateSession(session_no, theater_no, movie_no, session_time, seat_table);
    }

    public void testGetAll() {
        List<SessionVO> list = sSvc.getAll();
        out.println(list.size());
        for(SessionVO vo : list) {
            out.println(vo.getSession_no());
        }
    }

    public void testGetAllofTheater(String theater_no) {
        List<SessionVO> list = sSvc.getAllofTheater(theater_no);
        out.println(list.size());
        for(SessionVO vo : list) {
            out.println(vo.getSession_no());
        }
    }

    public void testGetAllBySessionTimeMovieNo() {
        String movie_no = "MV0001";
        String sessionTime = "2018-9-30 14:30:05";
        List<SessionVO> list = sSvc.getAllBySessionTimeMovieNo(sessionTime, movie_no);
        out.println("testGetAllBySessionTimeMovieNo - size : " + list.size());
        for(SessionVO vo : list) {
            out.println("Session_no : " + vo.getSession_no());
            out.println("Theater_no : " + vo.getTheater_no());
            out.println("Movie_no : " + vo.getMovie_no());
            out.println("Session_time : " + vo.getSession_time());
            Timestamp session_time = vo.getSession_time();
            Date date = new Date(session_time.getTime());
            out.println("Timestamp convert to java.util.Date : " + date);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = dateFormat.format(date);
            out.println("converted java.util.Date to String : " + strDate);
        }
    }

    public void testGetAllofJoinTheaterMovieWhereTheaterNoCinema(String cinema_no) {
        List<SessionVO> list = sSvc.getAllofJoinTheaterMovieWhereTheaterNoCinema(cinema_no);
        out.println(list.size());
        for(SessionVO sessionVO : list) {
            out.println(sessionVO.getSession_no());
            out.println("theater_name : " + sessionVO.getTheaterVO().getTheater_name());
            out.println("movie_name : " + sessionVO.getMovieVO().getMovie_name());
        }
    }

    public void testGetOneofJoinTheaterMovieWhereSessionNo(String session_no) {
        SessionVO sessionVO = sSvc.getOneofJoinTheaterMovieWhereSessionNo(session_no);
        out.println("Session_no : " + sessionVO.getSession_no());
        out.println("Theater_no : " + sessionVO.getTheater_no());
        out.println("Movie_no : " + sessionVO.getMovie_no());
        out.println("Session_time : " + sessionVO.getSession_time());
        Timestamp session_time = sessionVO.getSession_time();
        Date date = new Date(session_time.getTime());
        out.println("Timestamp convert to java.util.Date : " + date);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = dateFormat.format(date);
        out.println("converted java.util.Date to String : " + strDate);
        out.println("Seat_tabl : " + sessionVO.getSeat_table());
        out.println("theater_name : " + sessionVO.getTheaterVO().getTheater_name());
        out.println("movie_name : " + sessionVO.getMovieVO().getMovie_name());
    }

    public void testDeleteSession(String session_no) {
        sSvc.deleteSession(session_no);
        out.println("delete session_no : " + session_no);
    }
}
