package com.filmreview.controller;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.json.JSONObject;

import com.filmreview.model.FilmreviewDAO;
import com.filmreview.model.FilmreviewService;
import com.filmreview.model.FilmreviewVO;
import com.theater.model.TheaterService;
import com.theater.model.TheaterVO;

public class FilmreviewServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("movie_name");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("查無資料");
				}
// 				Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forestage/filmreview/fv_home.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				FilmreviewService fvSvc = new FilmreviewService();
				Set<FilmreviewVO> set = fvSvc.getAllByMemNo(str);
				
				if(set.size() == 0) {
					errorMsgs.add("查無資料");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forestage/filmreview/fv_home.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forestage/filmreview/fv_home.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
//				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("fv_search", set); // 資料庫取出的VO物件,存入req
				String urll = "/forestage/filmreview/fv_search.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(urll); // 成功轉交jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forestage/filmreview/fv_home.jsp");
				failureView.forward(req, res);
			}
		}
		//-----------------------------------------------------------------------------------------------------
		
		
		if ("toUpdatePage".equals(action)) { // from listAllTheater.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑

            try {
                /***************************1.接收請求參數****************************************/
                String fr_no = req.getParameter("fr_no");
                if (fr_no == null || (fr_no.trim()).length() == 0) {
                    errorMsgs.add("請輸入影評編號");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher(requestURL);
                    failureView.forward(req, res);
                    return;//程式中斷
                }
                /***************************2.開始查詢資料****************************************/
                FilmreviewService tSvc = new FilmreviewService();
                FilmreviewVO filmreviewVO = tSvc.findByPrimaryKey(fr_no);
                if (filmreviewVO == null) {
                    errorMsgs.add("查無資料");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher(requestURL);
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                /***************************3.查詢完成,準備轉交(Send the Success view)************/
                req.setAttribute("filmreviewVO", filmreviewVO); // 資料庫取出的theaterVO物件,存入req
                String url = "/backstage/filmreview/fv_update.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交updateTheater.jsp
                successView.forward(req, res);

                /***************************其他可能的錯誤處理************************************/
            } catch (Exception e) {
                if(e.getMessage() != null) {
                    errorMsgs.add("修改資料取出時失敗: " + e.getMessage().replaceAll("\r|\n", " "));
                } else {
                    errorMsgs.add("修改資料取出時失敗");
                }
                RequestDispatcher failureView = req
                        .getRequestDispatcher(requestURL);
                failureView.forward(req, res);
            }
        }
		
		
		//-----------------------------------------------------------------------------------------------------

		 if ("update".equals(action)) { // from updateTheater.jsp

	            List<String> errorMsgs = new LinkedList<String>();
	            // Store this set in the request scope, in case we need to
	            // send the ErrorPage view.
	            req.setAttribute("errorMsgs", errorMsgs);

	           

	            try {
	                /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
	            	String fr_no = req.getParameter("fr_no");
	            	String movie_no = req.getParameter("movie_no");
					
					if (movie_no == null || movie_no.trim().length() == 0) {
						errorMsgs.add("電影編號: 請勿空白");
					} else if(!movie_no.trim().matches(movie_no)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("電影編號: 只能是中、英文字母、數字和_");
		            }else if(movie_no.length()>10) {
						errorMsgs.add("會員編號長度必需在1到10之間");
						}
					String title = req.getParameter("title").trim();
					if (title == null || title.trim().length() == 0) {
						errorMsgs.add("標題請勿空白");
					}
					
					String content = req.getParameter("content");
					if (content == null || content.trim().length() == 0) {
						errorMsgs.add("內容請勿空白");
					}
					
					String mem_no = req.getParameter("mem_no");
						
					String author = req.getParameter("author");
					if (author == null || author.trim().length() == 0) {
						errorMsgs.add("作者請勿空白");
					}
					String url = req.getParameter("url");
					if (url == null || url.trim().length() == 0) {
						errorMsgs.add("網址請勿空白");
					}
					String source = req.getParameter("source");
					if (source == null || source.trim().length() == 0) {
						errorMsgs.add("來源請勿空白");
					}
					
					Double evaluation = null;
					try {
						evaluation = new Double(req.getParameter("evaluation").trim());
						if(evaluation>5.0||evaluation<0.0) {
							errorMsgs.add("評分請在0.0~5.0內.");
						}
						
					} catch (NumberFormatException e) {
						evaluation = 0.0;
						errorMsgs.add("評分請填數字");
					}

					

					if (!errorMsgs.isEmpty()) {
						req.setAttribute("errorMsgs", errorMsgs); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/backstage/filmreview/fv_update.jsp?fr_no="+fr_no);
						failureView.forward(req, res);
						return;
					}

	                /***************************2.開始修改資料*****************************************/
	                FilmreviewService fvSvc = new FilmreviewService();
	                fvSvc.updateFilmrevew(movie_no, content, evaluation, title, url, source, mem_no, author,fr_no);

	                /***************************3.修改完成,準備轉交(Send the Success view)***********/
//	                String url = "/backstage/theater/listAllTheater.jsp" + "?cinema_no=" + cinema_no;
//	                System.out.println("url: " + url);
	                String urll = "/backstage/filmreview/fv_b.jsp";
	              
	                RequestDispatcher successView = req.getRequestDispatcher(urll);   // 修改成功後,轉交回送出修改的來源網頁
	                successView.forward(req, res);

	                /***************************其他可能的錯誤處理**********************************/
	            } catch (Exception e) {
	                if(e.getMessage() != null) {
	                    errorMsgs.add("修改資料失敗: " + e.getMessage().replaceAll("\r|\n", " "));
	                } else {
	                    errorMsgs.add("修改資料失敗");
	                }
	                RequestDispatcher failureView = req
	                        .getRequestDispatcher("/backstage/filmreview/fv_b.jsp");
	                failureView.forward(req, res);
	            }
	        }
		
		//-----------------------------------------------------------------------------------------------------
		
		if ("getOne".equals(action)) { // 來自listAllEmp.jsp的請求

			 List<String> errorMsgs = new LinkedList<String>();
	            // Store this set in the request scope, in case we need to
	            // send the ErrorPage view.
	            req.setAttribute("errorMsgs", errorMsgs);
	            String requestURL = req.getParameter("requestURL");

	            try {
	                /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
	                String fr_no = req.getParameter("fr_no");
	                if (fr_no == null || (fr_no.trim()).length() == 0) {
	                    errorMsgs.add("請輸入影評編號");
	                }
	                // Send the use back to the form, if there were errors
	                if (!errorMsgs.isEmpty()) {
	                    RequestDispatcher failureView = req
	                            .getRequestDispatcher(requestURL);
	                    failureView.forward(req, res);
	                    return;//程式中斷
	                }

	                /***************************2.開始查詢資料*****************************************/
	                FilmreviewService tSvc = new FilmreviewService();
	                FilmreviewVO filmreviewVO = tSvc.findByPrimaryKey(fr_no);
	                if (filmreviewVO == null) {
	                    errorMsgs.add("查無資料");
	                }
	                // Send the use back to the form, if there were errors
	                if (!errorMsgs.isEmpty()) {
	                    RequestDispatcher failureView = req
	                            .getRequestDispatcher(requestURL);
	                    failureView.forward(req, res);
	                    return;//程式中斷
	                }

	                /***************************3.查詢完成,準備轉交(Send the Success view)*************/
	                req.setAttribute("filmreviewVO", filmreviewVO); // 資料庫取出的theaterVO物件,存入req
	                String urll = "/backstage/filmreview/fv_one.jsp";
	                RequestDispatcher successView = req.getRequestDispatcher(urll); // 成功轉交listOneTheater.jsp
	                successView.forward(req, res);

	                /***************************其他可能的錯誤處理*************************************/
	            } catch (Exception e) {
	                if(e.getMessage() != null) {
	                    errorMsgs.add("無法取得資料: " + e.getMessage().replaceAll("\r|\n", " "));
	                } else {
	                    errorMsgs.add("無法取得資料");
	                }
	                RequestDispatcher failureView = req
	                        .getRequestDispatcher("requestURL");
	                failureView.forward(req, res);
	            }
		}
		
		
		
		
		//-------------------------------------------------------------------------------------------------------
		if ("insertb".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				
				
				String movie_no = req.getParameter("movie_no");
				
				if (movie_no == null || movie_no.trim().length() == 0) {
					errorMsgs.add("電影編號: 請勿空白");
				} else if(!movie_no.trim().matches(movie_no)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影編號: 只能是中、英文字母、數字和_");
	            }else if(movie_no.length()>10) {
					errorMsgs.add("會員編號長度必需在1到10之間");
					}
				String title = req.getParameter("title").trim();
				if (title == null || title.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				
				String content = req.getParameter("content");
				if (content == null || content.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				
				String mem_no = req.getParameter("mem_no");
					
				String author = req.getParameter("author");
				if (author == null || author.trim().length() == 0) {
					errorMsgs.add("作者請勿空白");
				}
				String url = req.getParameter("url");
				if (url == null || url.trim().length() == 0) {
					errorMsgs.add("網址請勿空白");
				}
				String source = req.getParameter("source");
				if (source == null || source.trim().length() == 0) {
					errorMsgs.add("來源請勿空白");
				}
				
				Double evaluation = null;
				try {
					evaluation = new Double(req.getParameter("evaluation").trim());
					if(evaluation>5.0||evaluation<0.0) {
						errorMsgs.add("評分請在0.0~5.0內.");
					}
					
				} catch (NumberFormatException e) {
					evaluation = 0.0;
					errorMsgs.add("評分請填數字");
				}

				FilmreviewVO FilmreviewVO = new FilmreviewVO();
				FilmreviewVO.setMovie_no(movie_no);
				FilmreviewVO.setTitle(title);
				FilmreviewVO.setEvaluation(evaluation);
				FilmreviewVO.setContent(content);
				FilmreviewVO.setMem_no(mem_no);
				FilmreviewVO.setAuthor(author);
				FilmreviewVO.setUrl(url);
				FilmreviewVO.setSource(source);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("errorMsgs", errorMsgs); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/backstage/filmreview/fv_add.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				FilmreviewService fvSvc = new FilmreviewService();
				FilmreviewVO = fvSvc.insertFilmrevew(movie_no ,content, evaluation , title , source , url , mem_no , author );
				
				/*************************3.新增完成,準備轉交(Send the Success view)***********/
				String urll = "/backstage/filmreview/fv_b.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(urll); // 新增成功後轉交
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backstage/filmreview/fv_b.jsp");
				failureView.forward(req, res);
			}
		}
		
		//---------------------------------------------------------------

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				
				
				String movie_no = req.getParameter("movie_no");
				
				
		
				if (movie_no == null || movie_no.trim().length() == 0) {
					errorMsgs.add("電影編號: 請勿空白");
				} else if(!movie_no.trim().matches(movie_no)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影編號: 只能是中、英文字母、數字和_");
	            }else if(movie_no.length()>10) {
					errorMsgs.add("會員編號長度必需在1到10之間");
					}
				
		
				String title = req.getParameter("title").trim();
				if (title == null || title.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				
				
				
				String content = req.getParameter("content");
				if (content == null || content.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				
				
				String mem_no = req.getParameter("mem_no");
				
				
				String author = req.getParameter("author");
			
				
				String url = req.getParameter("url");
				if (url == null || url.trim().length() == 0) {
					errorMsgs.add("網址請勿空白");
				}
				String source = req.getParameter("source");
				if (source == null || source.trim().length() == 0) {
					errorMsgs.add("來源請勿空白");
				}
				
				
				Double evaluation = null;
				try {
					evaluation = new Double(req.getParameter("evaluation").trim());
					if(evaluation>5.0||evaluation<0.0) {
						errorMsgs.add("評分請在0.0~5.0內.");
					}
					
				} catch (NumberFormatException e) {
					evaluation = 0.0;
					errorMsgs.add("評分請填數字.");
				}

				FilmreviewVO FilmreviewVO = new FilmreviewVO();
				FilmreviewVO.setMovie_no(movie_no);
				FilmreviewVO.setTitle(title);
				FilmreviewVO.setEvaluation(evaluation);
				FilmreviewVO.setContent(content);
				FilmreviewVO.setMem_no(mem_no);
				FilmreviewVO.setAuthor(author);
				FilmreviewVO.setUrl(url);
				FilmreviewVO.setSource(source);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("errorMsgs", errorMsgs); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/forestage/filmreview/fv_writing.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				FilmreviewService fvSvc = new FilmreviewService();
				FilmreviewVO = fvSvc.insertFilmrevew(movie_no ,content, evaluation , title , source , url , mem_no , author );
				
				/*************************3.新增完成,準備轉交(Send the Success view)***********/
				String urll = "/forestage/filmreview/fv_home.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(urll); // 新增成功後轉交
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forestage/filmreview/fv_writing.jsp");
				failureView.forward(req, res);
			}
		}
		
		//---------------------------------------------------------------
		
		
		if ("getOne_From06".equals(action)) {

			try {
				// Retrieve form parameters.
				String fr_no =req.getParameter("fr_no");

				FilmreviewDAO dao = new FilmreviewDAO();
				FilmreviewVO fvVO =  dao.findByPrimaryKey(fr_no);

				req.setAttribute("fvVO", fvVO); // 資料庫取出的empVO物件,存入req
				
				//Bootstrap_modal
				boolean openModal=true;
				req.setAttribute("openModal",openModal );
				
				// 取出的empVO送給listOneEmp.jsp
				RequestDispatcher successView = req
						.getRequestDispatcher("/backstage/filmreview/fv_dele.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		//--------------------------------------------------------------------------------------------
		if ("delete".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】 或 【 /emp/listEmps_ByCompositeQuery.jsp】

			try {
				/***************************1.接收請求參數***************************************/
				String fr_no = req.getParameter("fr_no");
				
				/***************************2.開始刪除資料***************************************/
				FilmreviewService fvSvc = new FilmreviewService();
				fvSvc.delete(fr_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
//				DeptService deptSvc = new DeptService();
//				if(requestURL.equals("/filmreview/listAllDept.jsp"))
//					req.setAttribute("listEmps_ByDeptno",fvSvc.findByPrimaryKey(fvVO.getFr_no())); // 資料庫取出的list物件,存入request
//				
//				if(requestURL.equals("/emp/listEmps_ByCompositeQuery.jsp")){
//					HttpSession session = req.getSession();
//					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
//					List<EmpVO> list  = empSvc.getAll(map);
//					req.setAttribute("listEmps_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request
//				}
				
				 String url = requestURL;
	               System.out.println("requestURL: " + url);
	               RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
	               successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		
		//--------------------------------------------------------------------------------------------------------
		
		//-----------------------------------------------------------------------------------------------------
		
		
				if ("toUpdate".equals(action)) { // from listAllTheater.jsp

		            List<String> errorMsgs = new LinkedList<String>();
		            // Store this set in the request scope, in case we need to
		            // send the ErrorPage view.
		            req.setAttribute("errorMsgs", errorMsgs);

		            String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑

		            try {
		                /***************************1.接收請求參數****************************************/
		                String fr_no = req.getParameter("fr_no");
		                if (fr_no == null || (fr_no.trim()).length() == 0) {
		                    errorMsgs.add("請輸入影評編號");
		                }
		                // Send the use back to the form, if there were errors
		                if (!errorMsgs.isEmpty()) {
		                    RequestDispatcher failureView = req
		                            .getRequestDispatcher(requestURL);
		                    failureView.forward(req, res);
		                    return;//程式中斷
		                }
		                /***************************2.開始查詢資料****************************************/
		                FilmreviewService tSvc = new FilmreviewService();
		                FilmreviewVO filmreviewVO = tSvc.findByPrimaryKey(fr_no);
		                if (filmreviewVO == null) {
		                    errorMsgs.add("查無資料");
		                }
		                // Send the use back to the form, if there were errors
		                if (!errorMsgs.isEmpty()) {
		                    RequestDispatcher failureView = req
		                            .getRequestDispatcher(requestURL);
		                    failureView.forward(req, res);
		                    return;//程式中斷
		                }

		                /***************************3.查詢完成,準備轉交(Send the Success view)************/
		                req.setAttribute("filmreviewVO", filmreviewVO); // 資料庫取出的theaterVO物件,存入req
		                String url = "/forestage/filmreview/fr_update.jsp";
		                RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交updateTheater.jsp
		                successView.forward(req, res);

		                /***************************其他可能的錯誤處理************************************/
		            } catch (Exception e) {
		                if(e.getMessage() != null) {
		                    errorMsgs.add("修改資料取出時失敗: " + e.getMessage().replaceAll("\r|\n", " "));
		                } else {
		                    errorMsgs.add("修改資料取出時失敗");
		                }
		                RequestDispatcher failureView = req
		                        .getRequestDispatcher(requestURL);
		                failureView.forward(req, res);
		            }
		        }
				
				
				//-----------------------------------------------------------------------------------------------------

				 if ("updatepage".equals(action)) { // from updateTheater.jsp

			            List<String> errorMsgs = new LinkedList<String>();
			            // Store this set in the request scope, in case we need to
			            // send the ErrorPage view.
			            req.setAttribute("errorMsgs", errorMsgs);

			           
			            
			            try {
			                /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			            	String fr_no = req.getParameter("fr_no");
			            	String movie_no = req.getParameter("movie_no");
			            	String mem_no = req.getParameter("mem_no");
							
							if (movie_no == null || movie_no.trim().length() == 0) {
								errorMsgs.add("電影編號: 請勿空白");
							} else if(!movie_no.trim().matches(movie_no)) { //以下練習正則(規)表示式(regular-expression)
								errorMsgs.add("電影編號: 只能是中、英文字母、數字和_");
				            }else if(movie_no.length()>10) {
								errorMsgs.add("會員編號長度必需在1到10之間");
								}
							String title = req.getParameter("title").trim();
							if (title == null || title.trim().length() == 0) {
								errorMsgs.add("標題請勿空白");
							}
							
							String content = req.getParameter("content");
							if (content == null || content.trim().length() == 0) {
								errorMsgs.add("內容請勿空白");
							}
							
								
							String author = req.getParameter("author");
							
							String url = req.getParameter("url");
							if (url == null || url.trim().length() == 0) {
								errorMsgs.add("網址請勿空白");
							}
							String source = req.getParameter("source");
							if (source == null || source.trim().length() == 0) {
								errorMsgs.add("來源請勿空白");
							}
							
							Double evaluation = null;
							try {
								evaluation = new Double(req.getParameter("evaluation").trim());
								if(evaluation>5.0||evaluation<0.0) {
									errorMsgs.add("評分請在0.0~5.0內.");
								}
								
							} catch (NumberFormatException e) {
								evaluation = 0.0;
								errorMsgs.add("評分請填數字");
							}

							

							if (!errorMsgs.isEmpty()) {
								req.setAttribute("errorMsgs", errorMsgs); // 含有輸入格式錯誤的empVO物件,也存入req
								RequestDispatcher failureView = req.getRequestDispatcher("/forestage/filmreview/fr_update.jsp?fr_no="+fr_no);
								failureView.forward(req, res);
								return;
							}

			                /***************************2.開始修改資料*****************************************/
			                FilmreviewService fvSvc = new FilmreviewService();
			                fvSvc.updateFilmrevew(movie_no, content, evaluation, title, url, source, mem_no, author,fr_no);
System.out.println(fr_no);
			                /***************************3.修改完成,準備轉交(Send the Success view)***********/
//			               
			                String urll = "/forestage/filmreview/fv_list.jsp?mem_no="+mem_no;
			              
			                RequestDispatcher successView = req.getRequestDispatcher(urll);   // 修改成功後,轉交回送出修改的來源網頁
			                successView.forward(req, res);

			                /***************************其他可能的錯誤處理**********************************/
			            } catch (Exception e) {
			                if(e.getMessage() != null) {
			                    errorMsgs.add("修改資料失敗: " + e.getMessage().replaceAll("\r|\n", " "));
			                } else {
			                    errorMsgs.add("修改資料失敗");
			                }
			                RequestDispatcher failureView = req
			                        .getRequestDispatcher("/forestage/filmreview/fv_home.jsp");
			                failureView.forward(req, res);
			            }
			        }
				
				//-----------------------------------------------------------------------------------------------------
				
	}
	
	
	
	
}
