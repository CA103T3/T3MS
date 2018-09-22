package com.theater.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.theater.model.TheaterService;
import com.theater.model.TheaterVO;

/**
 * Servlet implementation class TheaterServiceTest
 */
@WebServlet("/TheaterServiceTest")
public class TheaterServiceTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TheaterService tSvc;
    PrintWriter out;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TheaterServiceTest() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/plain; charset=UTF-8");

        out = response.getWriter();
        out.append("Served at: ").append(request.getContextPath()).append("\n");
        tSvc = new TheaterService();
	    String theater_no = testAddTheater();
	    testGetOneTheater(theater_no);
	    testUpdateTheater(theater_no);
	    testGetOneTheater(theater_no);
	    theater_no = testAddTheater();
        testGetAll();
        testDeleteTheater(theater_no);
        testGetAll();
        String cinema_no = "C001";
        testGetAllofCinema(cinema_no);
	}

//  insert into CINEMA (CINEMA_NO, CINEMA_NAME) values ('C001', '威秀影城');
	public String testAddTheater() throws IOException {
	    String cinema_no = "C001";
        String theater_name = "影廳";
        Integer t_rows = 20;
        Integer t_columns = 15;
        String clobContent = "This is a very very long string\n abc \n";
        StringReader seat_model = new StringReader(clobContent); 
        Integer seats = 250;
        String equipment = "數位";

        //TheaterVO theaterVO = tSvc.addTheater(cinema_no, theater_name, t_rows, t_columns, seat_model, seats, equipment);
        String theater_no = tSvc.addTheater(cinema_no, theater_name, t_rows, t_columns, seat_model, seats, equipment);

        out.println("Add Theater_no : " + theater_no);

//      out.println("Theater_no : " + theaterVO.getTheater_no());
//      out.println("Cinema_no : " + theaterVO.getCinema_no());
//      out.println("Theater_name : " + theaterVO.getTheater_name());
//      out.println("T_rows : " + theaterVO.getT_rows());
//      out.println("T_columns : " + theaterVO.getT_columns());
//      out.println("Seats : " + theaterVO.getSeats());
//      out.println("Equipment : " + theaterVO.getEquipment());

        //can not use seat_model again after inserting to database
        //String seatModel = readString(seat_model);
        //System.out.println("theaterVO.getSeat_model():" + theaterVO.getSeat_model());

        StringReader seatModel = new StringReader(clobContent);
        //https://www.baeldung.com/java-convert-reader-to-string
//      char[] arr = new char[8 * 1024];
//      StringBuilder buffer = new StringBuilder();
//      int numCharsRead;
//      while ((numCharsRead = seatModel.read(arr, 0, arr.length)) != -1) {
//          buffer.append(arr, 0, numCharsRead);
//      }
//      System.out.println("numCharsRead: " + numCharsRead);
//      seatModel.close();
//      String str = buffer.toString();
        String str = readString(seatModel);

        out.println("Seat_model : " + str);
        return theater_no;
	}

//  insert into CINEMA (CINEMA_NO, CINEMA_NAME) values ('C002', '新光影城');
    public void testUpdateTheater(String theater_no) throws IOException {
        //String theater_no = "T00033";
        String cinema_no = "C002";
        String theater_name = "影廳2";
        Integer t_rows = 15;
        Integer t_columns = 30;
        String clobContent = "update This is a very very long string\n abc \n";
        StringReader seat_model = new StringReader(clobContent); 
        Integer seats = 400;
        String equipment = "GC 3D DIG";

        tSvc.updateTheater(theater_no, cinema_no, theater_name, t_rows, t_columns, seat_model, seats, equipment);
        out.println("testUpdateTheater Theater_no : " + theater_no);
    }

    public void testGetOneTheater(String theater_no) throws IOException {
        TheaterVO theaterVO = tSvc.getOneTheater(theater_no);
        out.println("Theater_no : " + theaterVO.getTheater_no());
        out.println("Cinema_no : " + theaterVO.getCinema_no());
        out.println("Theater_name : " + theaterVO.getTheater_name());
        out.println("T_rows : " + theaterVO.getT_rows());
        out.println("T_columns : " + theaterVO.getT_columns());
        String seatModel = readString(theaterVO.getSeat_model());
        out.println("Seat_model : " + seatModel);
        out.println("Seats : " + theaterVO.getSeats());
        out.println("Equipment : " + theaterVO.getEquipment());
    }

    public void testGetAll() {
        List<TheaterVO> list = tSvc.getAll();
        out.println(list.size());
        for(TheaterVO vo : list) {
            out.println(vo.getTheater_no());
        }
    }

    public void testGetAllofCinema(String cinema_no) {
        List<TheaterVO> list = tSvc.getAllofCinema(cinema_no);
        out.println(cinema_no + " list size() : " + list.size());
        for(TheaterVO vo : list) {
            out.println(vo.getTheater_no());
        }
    }

    public void testDeleteTheater(String theater_no) {
        tSvc.deleteTheater(theater_no);
        out.println("delete Theater_no : " + theater_no);
    }

    public String readString(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(reader);
        String str;
//        System.out.println("readString");
        while((str = br.readLine()) != null) {
//            System.out.println("readString : " + str);
            sb.append(str);
            sb.append("\n");
        }
        br.close();

        return sb.toString();
    }
}
