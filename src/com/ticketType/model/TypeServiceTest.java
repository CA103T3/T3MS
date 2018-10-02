package com.ticketType.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class TypeServiceTest
 */
@WebServlet("/TypeServiceTest")
public class TypeServiceTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TypeService tSvc;
    PrintWriter out;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TypeServiceTest() {
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
        tSvc = new TypeService();
        String type_no = testAddType();
        testGetOneType(type_no);
        type_no = testAddType();
        testGetOneType(type_no);
        testUpdateType(type_no);
        testGetOneType(type_no);
        type_no = testAddType();
        testGetAll();
        testDeleteTheater(type_no);
        testGetAll();
        String cinema_no = "C001";
        testGetAllofCinema(cinema_no);
	}

    public String testAddType() {
        String theater_no = "T00001";
        String identify = "ADULT";
        // String equipment = "數位";
        String time = "NORMAL";
        Integer price = 300;

        // String type_no = tSvc.addType(theater_no, identify, equipment, time, price);
        String type_no = tSvc.addType(theater_no, identify, time, price);

        out.println("Add type_no : " + type_no);
        return type_no;
    }

    public void testGetOneType(String type_no) {
        TypeVO typeVO = tSvc.getOneType(type_no);
        out.println("type_no : " + typeVO.getType_no());
        out.println("theater_no : " + typeVO.getTheater_no());
        out.println("identify : " + typeVO.getIdentify());
        // out.println("equipment : " + typeVO.getEquipment());
        out.println("time : " + typeVO.getTime());
        out.println("price : " + typeVO.getPrice());
    }

    public void testUpdateType(String type_no) {
        String theater_no = "T00002";
        String identify = "COMPLIMENTARY";
        // String equipment = "GC 3D DIG";
        String time = "MAITNEE";
        Integer price = 250;

        // tSvc.updateType(type_no, theater_no, identify, equipment, time, price);
        tSvc.updateType(type_no, theater_no, identify, time, price);
        out.println("testUpdateType type_no : " + type_no);
    }

    public void testGetAll() {
        List<TypeVO> list = tSvc.getAll();
        out.println(list.size());
        for(TypeVO vo : list) {
            out.println(vo.getType_no());
        }
    }

    public void testGetAllofCinema(String cinema_no) {
        List<TypeVO> list = tSvc.getAllofCinema(cinema_no);
        out.println("GetAllofCinema: " + list.size());
        for(TypeVO vo : list) {
            out.println(vo.getType_no());
            out.println("theater_name : " + vo.getTheaterVO().getTheater_name());
        }
    }

    public void testDeleteTheater(String type_no) {
        tSvc.deleteType(type_no);
        out.println("delete type_no : " + type_no);
    }
}
