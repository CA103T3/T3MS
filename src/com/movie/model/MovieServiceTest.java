package com.movie.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
        testAddMovieReturnMovieNo();
	}

    public void testGetOneMovieByMovieName(String movie_name) {
        MovieVO movieVO = mSvc.getOneMovieByMovieName(movie_name);
        out.println("Movie_no : " + movieVO.getMovie_no());
        out.println("Movie_type : " + movieVO.getMovie_type());
        out.println("Movie_name : " + movieVO.getMovie_name());
    }

    public void testAddMovieReturnMovieNo() {
        // ----------------INSERT------------------
        try {
            String realPath = getServletContext().getRealPath("/img/Test_UP_IMG/04.jpeg");
            byte[] movie_pic = getPictureByteArray(realPath);
            String movie_type = "恐怖片";
            String movie_name = "鬼修女";
            String eng_name = "ShowGirl";
            java.sql.Date relased = java.sql.Date.valueOf("2018-09-11");
            String distributed = "華納";
            Integer length = 120;
            String language = "English";
            String madein = "USA";
            Double imdb = 8.7;
            String tomato = "69%";
            String rating = "限制級";
            String trailer_url = "www.yahoo.com.tw";
            String brief_intro =
                    "在《鬼修女》中，破紀錄賣座恐怖片《厲陰宅》及《厲陰宅2》的導演溫子仁，著手探索另一個駭人的黑暗角落。這部新片由柯林哈迪（《陰林》）執導，溫子仁及《厲陰宅》全系列製片人彼得沙佛朗製作。\r\n"
                            + "在羅馬尼亞一間與世隔絕的修道院中，有一個年輕的修女自殺了，梵蒂岡派出一名藏有悲慘過去的神父和一名剛入教的見習修女來調查。他們一起揭開這個教團的邪惡祕密，卻遭遇到一股邪惡的力量，也就是《厲陰宅2》中嚇壞觀眾的那個鬼修女，使修道院變成活人與受詛咒者之間的可怕戰場。他們不只可能會喪命，還可能會失去原本的信仰和自己的靈魂。";
            Integer active = 1;
            String director = "柯林·哈迪";
            String starring = "泰莎・法蜜嘉";

            String movie_no = mSvc.addMovieReturnMovieNo(movie_type, movie_name, eng_name, movie_pic, relased, distributed, length,
                    language, madein, imdb, tomato, rating, trailer_url, brief_intro, active, director, starring);
            out.println(movie_no + " 新增成功");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 使用byte[]方式
    public static byte[] getPictureByteArray(String path) throws IOException {
        File file = new File(path);
        System.out.println(path);
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int i;
        while ((i = fis.read(buffer)) != -1) {
            baos.write(buffer, 0, i);
        }
        baos.close();
        fis.close();

        return baos.toByteArray();
    }
}
