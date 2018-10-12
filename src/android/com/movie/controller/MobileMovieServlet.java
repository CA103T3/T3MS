package android.com.movie.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.util.ImageUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.movie.model.MovieService;
import com.movie.model.MovieVO;


/**
 * Servlet implementation class MobileMovieServlet
 */
@WebServlet("/MobileMovieServlet")
public class MobileMovieServlet extends HttpServlet {
	
    private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MobileMovieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ServletContext context = getServletContext();
		
		MovieService movieService = new MovieService();
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while((line = br.readLine()) != null)
		{
			jsonIn.append(line);
		}
		System.out.println("input = "+jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		
		String action = jsonObject.get("action").getAsString();
		
		if ("getAll".equals(action))
		{
			List<MovieVO> movieList =  movieService.getAll();
			for (MovieVO movieVO : movieList) {
				byte[] shrinImg = ImageUtil.shrink(movieVO.getMovie_pic(),200);
				movieVO.setMovie_pic(shrinImg); 
			}
			writeText(res, gson.toJson(movieList));
		}
		else if ("findByMovieType".equals(action))
		{
			
		}
		else if ("getImg".equals(action))
		{
			
		}
		else {
			writeText(res, " no matched action! ");
		}
	}
	
	private void writeText(HttpServletResponse res, String outText) throws IOException
	{		
		res.setContentType(CONTENT_TYPE);

		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		
		System.out.println("outText = " + outText);
	}

}
