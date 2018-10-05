package com.movie.model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinema.model.CinemaService;

/**
 * Servlet implementation class MovieServiceTest
 */
@WebServlet("/MovieServiceTest")
public class MovieServiceTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MovieService mSvc;
    PrintWriter out;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieServiceTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");

        out = response.getWriter();
        out.append("Served at: ").append(request.getContextPath()).append("\n");
        mSvc = new MovieService();
        String movie_name = "鬼修女";
        testGetOneMovieByMovieName(movie_name);
	}

    public void testGetOneMovieByMovieName(String movie_name) {
        MovieVO movieVO = mSvc.getOneMovieByMovieName(movie_name);
        out.println("Movie_no : " + movieVO.getMovie_no());
        out.println("Movie_type : " + movieVO.getMovie_type());
        out.println("Movie_name : " + movieVO.getMovie_name());
    }

}
