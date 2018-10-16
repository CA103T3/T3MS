package com.movie.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import com.movie.model.*;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MovieServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//------------------------------Search----------------------------------------------------------	
				
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("movie_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入電影編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backstage/movie/movie_List.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
							
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backstage/movie/movie_List.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MovieService movieSvc = new MovieService();
				MovieVO movieVO = movieSvc.getOneMovie(str);
				
				if (movieVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backstage/movie/movie_List.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("movieVO", movieVO); // 資料庫取出的movieVO物件,存入req
				String url = "/forestage/movie_moment/moment_One.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 movie_One.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backstage/movie/movie_List.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//----------------------------Delete--------------------------------------------------------------------
		
		if ("delete".equals(action)) { // 來自movie_List.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 為【/backstage/movie/movie_List.jsp.do】

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String movie_no = req.getParameter("movie_no").trim();

				/*************************** 2.開始刪除資料 ***************************************/
				MovieService movieSvc = new MovieService();
				movieSvc.deleteMovie(movie_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/

				String url = "/backstage/movie/movie_List.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
//----------------------------Update--------------------------------------------------------------------		

		if ("getOne_For_Update".equals(action)) { // 來自movie_List.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String movie_no = req.getParameter("movie_no").trim();

				/*************************** 2.開始查詢資料 ****************************************/
				MovieService movieSvc = new MovieService();
				MovieVO movieVO = movieSvc.getOneMovie(movie_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("movieVO", movieVO); // 資料庫取出的empVO物件,存入req
				String url = "/backstage/movie/movie_Update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交movie_Update.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backstage/movie/movie_List.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自movie_Update.jsp的請求
			
		
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String movie_no = req.getParameter("movie_no").trim();

				String movie_type = req.getParameter("movie_type").trim();
				if (movie_type == null || movie_type.trim().length() == 0) {
					errorMsgs.add("電影類別請勿空白");
				}

				String movie_name = req.getParameter("movie_name");
				if (movie_name == null || movie_name.trim().length() == 0) {
					errorMsgs.add("電影中文名稱: 請勿空白");
				}

				String eng_name = req.getParameter("eng_name");
				String eng_name_enReg = "^[\u0020-\u007E]*$";
				if (eng_name == null || eng_name.trim().length() == 0) {
					errorMsgs.add("電影英文名稱: 請勿空白");
				} else if (!eng_name.trim().matches(eng_name_enReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影英文名稱: 只能是英文");
				}
				
				
				Part part=req.getPart("movie_pic");
				String type=part.getContentType();
				String ex = part.getContentType().substring(0,type.indexOf("/"));
			
				MovieService movieSvc = new MovieService();
				byte[] movie_pic = movieSvc.getOneMovie(movie_no).getMovie_pic();
				if (part.getSize() != 0 &&  ex.equals("image") ) {
					InputStream in = part.getInputStream();
					movie_pic = new byte[in.available()];
					in.read(movie_pic);
					in.close();
				}else {
					errorMsgs.add("說好的正確檔案呢");
				}
				
				
				java.sql.Date relased = null;
				try {
					relased = java.sql.Date.valueOf(req.getParameter("relased").trim());
				} catch (IllegalArgumentException e) {
					relased = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String distributed = req.getParameter("distributed").trim();
				if (distributed == null || distributed.trim().length() == 0) {
					errorMsgs.add("發行公司請勿空白");
				}

				Integer length = null;
				try {
					length = new Integer(req.getParameter("length").trim());
					if (length < 1) {
						errorMsgs.add("片長不能小於或等於0");
					}
				} catch (NumberFormatException e) {
					length = 0;
					errorMsgs.add("片長請填數字.");
				}

				String language = req.getParameter("language").trim();
				if (language == null || language.trim().length() == 0) {
					errorMsgs.add("電影語言請勿空白");
				}

				String madein = req.getParameter("madein").trim();
				if (madein == null || madein.trim().length() == 0) {
					errorMsgs.add("產地請勿空白");
				}

				Double imdb = null;
				try {
					imdb = new Double(req.getParameter("imdb").trim());
					if (imdb < 0.0 ) {
						errorMsgs.add("評分不能小於或等於0.0");
					}else if (imdb > 10.0 ){
						errorMsgs.add("評分不能大於10.0");
					
					}
				} catch (NumberFormatException e) {
					imdb = 0.0;
					errorMsgs.add("評分請填數字.");
				}

				String tomato = req.getParameter("tomato").trim();
				if (tomato == null || tomato.trim().length() == 0) {
					errorMsgs.add("爛番茄評分請勿空白");
				}

				String rating = req.getParameter("rating").trim();
				if (rating == null || rating.trim().length() == 0) {
					errorMsgs.add("電影分級請勿空白");
				}

				String trailer_url = req.getParameter("trailer_url").trim();
				if (trailer_url == null || trailer_url.trim().length() == 0) {
					errorMsgs.add("預告片請勿空白");
				}

				String brief_intro = req.getParameter("brief_intro").trim();
				if (brief_intro == null || brief_intro.trim().length() == 0) {
					errorMsgs.add("劇情簡介請勿空白");
				}

				Integer active = new Integer(req.getParameter("active"));

				String director = req.getParameter("director").trim();
				if (director == null || director.trim().length() == 0) {
					errorMsgs.add("導演請勿空白");
				}

				String starring = req.getParameter("starring").trim();
				if (starring == null || starring.trim().length() == 0) {
					errorMsgs.add("演員請勿空白");
				}

				MovieVO movieVO = new MovieVO();
				movieVO.setMovie_no(movie_no);
				movieVO.setMovie_type(movie_type);
				movieVO.setMovie_name(movie_name);
				movieVO.setEng_name(eng_name);
				movieVO.setMovie_pic(movie_pic);
				movieVO.setRelased(relased);
				movieVO.setDistributed(distributed);
				movieVO.setLength(length);
				movieVO.setLanguage(language);
				movieVO.setMadein(madein);
				movieVO.setImdb(imdb);
				movieVO.setTomato(tomato);
				movieVO.setRating(rating);
				movieVO.setTrailer_url(trailer_url);
				movieVO.setBrief_intro(brief_intro);
				movieVO.setActive(active);
				movieVO.setDirector(director);
				movieVO.setStarring(starring);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("movieVO", movieVO); // 含有輸入格式錯誤的movieVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/backstage/movie/movie_Update.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				movieSvc = new MovieService();
				movieVO = movieSvc.updateMovie(movie_no, movie_type, movie_name, eng_name, movie_pic, relased,
						distributed, length, language, madein, imdb, tomato, rating, trailer_url, brief_intro, active,
						director, starring);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("movieVO", movieVO); // 資料庫update成功後,正確的的movieVO物件,存入req
				String url = "/backstage/movie/movie_List.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backstage/movie/movie_Update.jsp");
				failureView.forward(req, res);
			}
		}
		
//----------------------------Insert--------------------------------------------------------------------		

		if ("insert".equals(action)) { // 來自addMovie.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String movie_type = req.getParameter("movie_type").trim();
				if (movie_type == null || movie_type.trim().length() == 0) {
					errorMsgs.add("電影類別請勿空白");
				}
				
				String movie_name = req.getParameter("movie_name");
				if (movie_name == null || movie_name.trim().length() == 0) {
					errorMsgs.add("電影中文名稱: 請勿空白");
				}

				String eng_name = req.getParameter("eng_name");
				String eng_name_enReg = "^[\u0020-\u007E]*$";
				if (eng_name == null || eng_name.trim().length() == 0) {
					errorMsgs.add("電影英文名稱: 請勿空白");
				} else if (!eng_name.trim().matches(eng_name_enReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影英文名稱: 只能是英文");
				}
				
				
				InputStream in = req.getPart("movie_pic").getInputStream();
				byte[] movie_pic = new byte[in.available()];
				in.read(movie_pic);
				in.close();
				
				
				
				java.sql.Date relased = null;
				try {
					relased = java.sql.Date.valueOf(req.getParameter("relased").trim());
				} catch (IllegalArgumentException e) {
					relased = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String distributed = req.getParameter("distributed").trim();
				if (distributed == null || distributed.trim().length() == 0) {
					errorMsgs.add("發行公司請勿空白");
				}

				Integer length = null;
				try {
					length = new Integer(req.getParameter("length").trim());
					if (length < 1) {
						errorMsgs.add("片長不能小於或等於0");
					}
				} catch (NumberFormatException e) {
					length = 0;
					errorMsgs.add("片長請填數字.");
				}

				String language = req.getParameter("language").trim();
				if (language == null || language.trim().length() == 0) {
					errorMsgs.add("電影語言請勿空白");
				}

				String madein = req.getParameter("madein").trim();
				if (madein == null || madein.trim().length() == 0) {
					errorMsgs.add("產地請勿空白");
				}

				Double imdb = null;
				try {
					imdb = new Double(req.getParameter("imdb").trim());
					if (imdb < 0.0 ) {
						errorMsgs.add("片長不能小於或等於0.0");
					}
					else if (imdb > 10.0 ){
					errorMsgs.add("評分不能大於10.0");}
				} catch (NumberFormatException e) {
					imdb = 0.0;
					errorMsgs.add("評分請填數字.");
				}

				String tomato = req.getParameter("tomato").trim();
				if (tomato == null || tomato.trim().length() == 0) {
					errorMsgs.add("爛番茄評分請勿空白");
				}

				String rating = req.getParameter("rating").trim();
				if (rating == null || rating.trim().length() == 0) {
					errorMsgs.add("電影分級請勿空白");
				}

				String trailer_url = req.getParameter("trailer_url").trim();
				if (trailer_url == null || trailer_url.trim().length() == 0) {
					errorMsgs.add("預告片請勿空白");
				}

				String brief_intro = req.getParameter("brief_intro").trim();
				if (brief_intro == null || brief_intro.trim().length() == 0) {
					errorMsgs.add("劇情簡介請勿空白");
				}

				Integer active = new Integer(req.getParameter("active"));

				String director = req.getParameter("director").trim();
				if (director == null || director.trim().length() == 0) {
					errorMsgs.add("導演請勿空白");
				}

				String starring = req.getParameter("starring").trim();
				if (starring == null || starring.trim().length() == 0) {
					errorMsgs.add("演員請勿空白");
				}

				MovieVO movieVO = new MovieVO();

				movieVO.setMovie_type(movie_type);
				movieVO.setMovie_name(movie_name);
				movieVO.setEng_name(eng_name);
				movieVO.setMovie_pic(movie_pic);
				movieVO.setRelased(relased);
				movieVO.setDistributed(distributed);
				movieVO.setLength(length);
				movieVO.setLanguage(language);
				movieVO.setMadein(madein);
				movieVO.setImdb(imdb);
				movieVO.setTomato(tomato);
				movieVO.setRating(rating);
				movieVO.setTrailer_url(trailer_url);
				movieVO.setBrief_intro(brief_intro);
				movieVO.setActive(active);
				movieVO.setDirector(director);
				movieVO.setStarring(starring);

				MovieService movieSvc = new MovieService();

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("movieVO", movieVO); // 含有輸入格式錯誤的movieVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/backstage/movie/movie_Add.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/

				movieVO = movieSvc.addMovie(movie_type, movie_name, eng_name, movie_pic, relased, distributed, length,
						language, madein, imdb, tomato, rating, trailer_url, brief_intro, active, director, starring);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/backstage/movie/movie_List.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMovie.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backstage/movie/movie_Add.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		//------------------------------Search----------------------------------------------------------	
		
				if ("getMovie_Name".equals(action)) { // 來自select_page.jsp的請求

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					String requestURL = req.getParameter("requestURL");

					try {
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						String str = req.getParameter("movie_name").trim();

						if (str == null || (str.trim()).length() == 0) {
							errorMsgs.add("請輸入電影名稱");
						}
				

						/***************************2.開始查詢資料*****************************************/
						MovieService movieSvc = new MovieService();
						Set<MovieVO> set = movieSvc.getsrMovieName(str);
						System.out.println(set.size());
						if(set.size() == 0) {
							errorMsgs.add("查無資料");
							RequestDispatcher failureView = req
									.getRequestDispatcher(requestURL);
							failureView.forward(req, res);
							return;//程式中斷
						}
						
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher(requestURL);
							failureView.forward(req, res);
							return;//程式中斷
						}
						
						/***************************3.查詢完成,準備轉交(Send the Success view)*************/
						req.setAttribute("movie_search", set); // 資料庫取出的movieVO物件,存入req
						String url = "/forestage/search_page/search_page.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 movie_One.jsp
						successView.forward(req, res);
						
						/***************************其他可能的錯誤處理*************************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得資料:" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/forestage/search_page/search_page.jsp");
						failureView.forward(req, res);
					}
				}

	}

	private Object getFileNameFromPart(Part part) {
		// TODO Auto-generated method stub
		return null;
	}
}
