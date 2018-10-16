package com.boxoffice.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class BoxOfficeServiceTest
 */
@WebServlet("/BoxOfficeServiceTest")
public class BoxOfficeServiceTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BoxOfficeService bSvc;
    PrintWriter out;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoxOfficeServiceTest() {
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
        bSvc = new BoxOfficeService();
        String ranking_no = testAddBoxOffice();
        testGetOneBoxOffice(ranking_no);
        ranking_no = testAddBoxOffice();
        testGetOneBoxOffice(ranking_no);
        testUpdatBoxOffice(ranking_no);
        testGetOneBoxOffice(ranking_no);
        ranking_no = testAddBoxOffice();
        testGetOneBoxOffice(ranking_no);
        testGetAll();
        testDeleteBoxOffice(ranking_no);
        testGetAll();
        Integer loc = 1;
        testGetLatestTenByLoc(loc);
	}

    private void testGetLatestTenByLoc(Integer loc) {
        List<BoxOfficeVO> list = bSvc.getLatestTenByLoc(loc);
        out.println(list.size());
        for(BoxOfficeVO vo : list) {
            out.println(vo.getRanking_no());
            out.println(vo.getMovie_no());

            out.println("Statistics date : " + vo.getStatistics());
            java.sql.Date sqlDate = vo.getStatistics();
            java.util.Date utilDate = new java.util.Date();
            utilDate.setTime(sqlDate.getTime());
            out.println("utilDate : " + utilDate);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(utilDate);
            out.println("converted java.util.Date to String : " + strDate);

            out.println("vo.getMoviename() : " + vo.getMoviename());
            out.println("vo.getMovieVO().getMovie_name() : " + vo.getMovieVO().getMovie_name());
        }
    }

    public String testAddBoxOffice() {
        String movie_no = "MV0001";
        String moviename = "猛毒";
        String date = "2018-10-01";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = null;
        try {
            utilDate = dateFormat.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        java.sql.Date statistics = new java.sql.Date(utilDate.getTime());
        Integer rank = 1;
        Integer loc = 0;
        String ranking_no = bSvc.addBoxOffice(movie_no, moviename, statistics, rank, loc);
        return ranking_no;
    }

    public void testGetOneBoxOffice(String ranking_no) {
        BoxOfficeVO boVO = bSvc.getOneBoxOffice(ranking_no);
        out.println("Ranking_no : " + boVO.getRanking_no());
        out.println("Movie_no : " + boVO.getMovie_no());
        out.println("Moviename : " + boVO.getMoviename());
        out.println("Statistics date : " + boVO.getStatistics());
        java.sql.Date sqlDate = boVO.getStatistics();
        java.util.Date utilDate = new java.util.Date();
        utilDate.setTime(sqlDate.getTime());
        out.println("utilDate : " + utilDate);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(utilDate);
        out.println("converted java.util.Date to String : " + strDate);
        out.println("Rank : " + boVO.getRank());
        out.println("Loc : " + boVO.getLoc());

        //oracle sql
        //select TO_CHAR(STATISTICS, 'YYYY-MM-DD') from BOX_OFFICE_CHARTS;
        //'2018-10-01'
    }

    public void testUpdatBoxOffice(String ranking_no) {
        String movie_no = "MV0002";
        String moviename = "一個巨星的誕生";
        String date = "2019-11-11";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = null;
        try {
            utilDate = dateFormat.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        java.sql.Date statistics = new java.sql.Date(utilDate.getTime());
        Integer rank = 2;
        Integer loc = 1;
        bSvc.updateBoxOffice(ranking_no, movie_no, moviename, statistics, rank, loc);
    }

    public void testGetAll() {
        List<BoxOfficeVO> list = bSvc.getAll();
        out.println(list.size());
        for(BoxOfficeVO vo : list) {
            out.println(vo.getRanking_no());
            out.println(vo.getMoviename());
        }
    }

    public void testDeleteBoxOffice(String ranking_no) {
        bSvc.deleteBoxOffice(ranking_no);
        out.println("delete session_no : " + ranking_no);
    }
}
