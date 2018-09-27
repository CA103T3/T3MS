package com.cinema.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

import com.cinema.model.CinemaService;
import com.cinema.model.CinemaVO;
import com.common.util.ImageUtil;
import com.common.util.UUIDGenerator;

/**
 * Servlet implementation class CinemaServlet
 */
//@WebServlet("/cinema/cinema.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
//上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常

public class CinemaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String saveDirectory = "/img/cinema"; // 上傳檔案的目地目錄;  用 java.io.File 於 ContextPath 之下, 自動建立目地目錄
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CinemaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html; charset=utf-8");
        //PrintWriter out = res.getWriter();

        String action = req.getParameter("action");
        if ("insert".equals(action)) { // from addCinema.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
                String cinema_name = req.getParameter("cinema_name").trim();
                String cinema_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{1,12}$";
                System.out.println("cinema_name : " + cinema_name);
                System.out.println("cinema_name length: " + cinema_name.trim().length());
                if (cinema_name == null || cinema_name.trim().length() == 0) {
                    errorMsgs.add("影城名稱: 請勿空白");
//                    System.out.println("影城名稱: 請勿空白");
                } else if(!cinema_name.trim().matches(cinema_nameReg)) { //以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("影城名稱: 只能是中、英文字母和數字, 且長度必需在1到12之間");
                }

                String cinema_engname = req.getParameter("cinema_engname").trim();
                String cinema_engnameReg = "^[(a-zA-Z0-9\\s)]{1,50}$";
                System.out.println("cinema_engname : " + cinema_engname);
                System.out.println("cinema_engname length: " + cinema_engname.trim().length());
                if (cinema_engname == null || cinema_engname.trim().length() == 0) {
                    errorMsgs.add("影城英文名稱: 請勿空白");
                } else if(!cinema_engname.trim().matches(cinema_engnameReg)) { //以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("影城英文名稱: 只能是英文字母、數字和空白字元, 且長度必需在1到50之間");
                }

                String cinema_address = req.getParameter("cinema_address").trim();
                String cinema_addressReg = "^[(\u4e00-\u9fa5)(0-9)]{3,30}$";
                System.out.println("cinema_address : " + cinema_address);
                System.out.println("cinema_address length: " + cinema_address.trim().length());
                if (cinema_address == null || cinema_address.trim().length() == 0) {
                    errorMsgs.add("影城地址: 請勿空白");
                } else if(!cinema_address.trim().matches(cinema_addressReg)) { //以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("影城地址: 只能是中文和數字, 且長度必需在3到30之間");
                }

                String cinema_tel = req.getParameter("cinema_tel").trim();
                String cinema_telReg = "^[(\\d-()\\s)]{9,20}$";
                System.out.println("cinema_tel : " + cinema_address);
                System.out.println("cinema_tel length: " + cinema_tel.trim().length());
                if (cinema_tel == null || cinema_tel.trim().length() == 0) {
                    errorMsgs.add("服務專線: 請勿空白");
                } else if(!cinema_tel.trim().matches(cinema_telReg)) { //以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("服務專線: 只能是數字和-(), 且長度必需在9到20之間");
                }

                String photo_title = req.getParameter("photo_title").trim();
                String photo_titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9-_)]{1,15}$";
                System.out.println("photo_title : " + photo_title);
                System.out.println("photo_title length: " + photo_title.trim().length());
                if (photo_title == null || photo_title.trim().length() == 0) {
                    errorMsgs.add("圖片名稱: 請勿空白");
                } else if(!photo_title.trim().matches(photo_titleReg)) { //以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("圖片名稱: 只能是中、英文字母、數字和-_, 且長度必需在1到15之間");
                }

                Integer active = null;
                String active_str = req.getParameter("active");
                //checkbox "active" - null or on
                if (active_str == null) {
                    System.out.println("active_str null");
                    active = 0;
                //} else if(active_str.length() == 0) {
                    //not enter
                    //System.out.println("active_str length() 0");
                } else if("on".equals(active_str)){
                    System.out.println("active_str on");
                    active = 1;
                } else {
                    errorMsgs.add("上線: 資料不正確");
                }

                Integer state = null;
                String state_str = req.getParameter("state");
                //checkbox "state" - null or on
                if (state_str == null) {
                    System.out.println("state_str null");
                    state = 0;
                //} else if(state_str.length() == 0) {
                    //not enter
                    //System.out.println("state_str length() 0");
                } else if("on".equals(state_str)){
                    System.out.println("state_str on");
                    state = 1;
                } else {
                    errorMsgs.add("合作狀態: 資料不正確");
                }

                String photo_path = null;
                String photo_small = null;
                String realPath = getServletContext().getRealPath(saveDirectory);
                File fsaveDir = new File(realPath);
                if(!fsaveDir.exists()) {
                    fsaveDir.mkdirs();
                }
                //String photo_path_temp = req.getParameter("photo_path"); //file multipart/form-data - not work, get null
                //System.out.println("photo_path_temp: " + photo_path_temp); //null
                Part part = req.getPart("photo_path");
                String filename = getFileNameFromPart(part);
                if(filename != null && part.getContentType() != null) {
                    System.out.println("part.getContentType(): " + part.getContentType()); //part.getContentType(): image/png
                    String type = part.getContentType();
                    CharSequence pattern = "image/";
                    String ext = filename.substring(filename.lastIndexOf("."));
                    System.out.println("extension: " + ext); //extension: .html
                    String uuid = UUIDGenerator.getUUID();
                    System.out.println("uuid: " + uuid);
                    filename = uuid + ext;
                    if(type.contains(pattern)) {
                        File f = new File(fsaveDir, filename);
                        // 利用File物件,寫入目地目錄,上傳成功
                        try {
                            part.write(f.toString());
                            System.out.println("f.toString(): " + f.toString());
                            // f.toString():
                            // D:\T3_workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\T3MS\img\cinema\ny.jpg
                            // http://localhost:8082/T3MS/img/cinema/ny.jpg
                            photo_path = req.getContextPath() + saveDirectory + "/" + filename;
                            System.out.println("photo_path: " + photo_path); //photo_path: /T3MS/img/cinema/8bec6d1b9e1248efaf5af2bf7b283053.jpg
                            System.out.println("req.getContextPath(): " + req.getContextPath()); //req.getContextPath(): /T3MS
                            System.out.println("fsaveDir: " + fsaveDir); //fsaveDir: D:\T3_workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\T3MS\img\cinema
                            System.out.println("saveDirectory: " + saveDirectory); //saveDirectory: /img/cinema
                            System.out.println("filename: " + filename); //filename: 8bec6d1b9e1248efaf5af2bf7b283053.jpg

                            InputStream is = part.getInputStream();
                            byte[] targetArray = new byte[is.available()];
                            is.read(targetArray); //read targetArray.length bytes to targetArray

                            int imageSize = 300; //pixel
                            targetArray = ImageUtil.shrink(targetArray, imageSize);
                            String tempPath = fsaveDir + "/" + filename;
                            String fileStart = tempPath.substring(0, tempPath.lastIndexOf("."));
                            System.out.println("fileStart : " + fileStart); //fileStart : D:\T3_workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\T3MS\img\01
                            String fileExt = tempPath.substring(tempPath.lastIndexOf("."));
                            System.out.println("fileExt : " + fileExt); //fileExt : .jpg
                            String thumbnailPath = fileStart + "-s" + fileExt;
                            System.out.println("thumbnailPath : " + thumbnailPath); //thumbnailPath : D:\T3_workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\T3MS\img\cinema/18b4775986704da0a843e66a167a778c-s.jpg
                            ImageUtil.outputPicture(targetArray, thumbnailPath);
                            photo_small = req.getContextPath() + saveDirectory + thumbnailPath.substring(thumbnailPath.lastIndexOf("/"));
                            System.out.println("photo_small : " + photo_small); //photo_small : /T3MS/img/cinema/18b4775986704da0a843e66a167a778c-s.jpg
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } else {
                        errorMsgs.add("圖片: 請選擇圖檔");
                    }
                } else {
                    errorMsgs.add("圖片: 不可空白");
                }

                String traffic = req.getParameter("traffic");
                if (traffic == null || traffic.trim().length() == 0) {
                    errorMsgs.add("交通資訊: 請勿空白");
                } else if(traffic.length() > 250) {
                    errorMsgs.add("交通資訊: 請勿超過250個字");
                    System.out.println("交通資訊: 長度 " + traffic.length());
                }

                String introduction = req.getParameter("introduction");
                if (introduction == null || introduction.trim().length() == 0) {
                    errorMsgs.add("影城介紹: 請勿空白");
                } else if(introduction.length() > 1300) {
                    errorMsgs.add("影城介紹: 請勿超過1300個字");
                    System.out.println("影城介紹: 長度 " + introduction.length());
                }

                CinemaVO cinemaVO = new CinemaVO();
                cinemaVO.setCinema_name(cinema_name);
                cinemaVO.setCinema_engname(cinema_engname);
                cinemaVO.setCinema_address(cinema_address);
                cinemaVO.setCinema_tel(cinema_tel);
                cinemaVO.setPhoto_title(photo_title);

                cinemaVO.setActive(active);
                cinemaVO.setState(state);
                cinemaVO.setPhoto_path(photo_path);
                cinemaVO.setPhoto_small(photo_small);

                cinemaVO.setIntroduction(introduction);
                cinemaVO.setTraffic(traffic);


//                System.out.println("before errorMsgs.size() : " + errorMsgs.size());
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
//                    System.out.println("errorMsgs.size() : " + errorMsgs.size());
                    req.setAttribute("cinemaVO", cinemaVO); // 含有輸入格式錯誤的cinemaVO物件,也存入req
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/backstage/cinema/addCinema.jsp");
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                /***************************2.開始新增資料***************************************/
                CinemaService cSvc = new CinemaService();
                String cinema_no = cSvc.addCinema(cinema_name, cinema_engname, cinema_address, cinema_tel,
                        introduction, traffic, photo_title, photo_path, photo_small, active, state);

                /***************************3.新增完成,準備轉交(Send the Success view)***********/
                String url = "/backstage/cinema/listAllCinema.jsp";
                System.out.println("url: " + url);
                RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllCinema.jsp
                successView.forward(req, res);

                /***************************其他可能的錯誤處理**********************************/
            } catch (Exception e) {
                errorMsgs.add(e.getMessage().replaceAll("\r|\n", ""));
                System.out.println("其他可能的錯誤處理");
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/backstage/cinema/addCinema.jsp");
                failureView.forward(req, res);
                e.printStackTrace();
            }
        }

        if ("delete".equals(action)) { // from listAllCinema.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑

            try {
                /***************************1.接收請求參數***************************************/
               String cinema_no = req.getParameter("cinema_no");

                /***************************2.開始刪除資料***************************************/
               CinemaService cSvc = new CinemaService();
               cSvc.deleteCinema(cinema_no);

                /***************************3.刪除完成,準備轉交(Send the Success view)***********/
               String url = requestURL;
               System.out.println("requestURL: " + url);
               RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
               successView.forward(req, res);

                /***************************其他可能的錯誤處理**********************************/
            } catch (Exception e) {
                errorMsgs.add("刪除資料失敗: "+e.getMessage().replaceAll("\r|\n", ""));
                RequestDispatcher failureView = req
                        .getRequestDispatcher(requestURL);
                failureView.forward(req, res);
            }
        }

        if ("view".equals(action)) { // from listAllCinema.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);
            String requestURL = req.getParameter("requestURL");

            try {
                /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
                String cinema_no = req.getParameter("cinema_no");
                if (cinema_no == null || (cinema_no.trim()).length() == 0) {
                    errorMsgs.add("請輸入影城編號");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher(requestURL);
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                /***************************2.開始查詢資料*****************************************/
                CinemaService cSvc = new CinemaService();
                CinemaVO cinemaVO = cSvc.getOneCinema(cinema_no);
                if (cinemaVO == null) {
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
                req.setAttribute("cinemaVO", cinemaVO); // 資料庫取出的cinemaVO物件,存入req
                String url = "/backstage/cinema/listOneCinema.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneCinema.jsp
                successView.forward(req, res);

                /***************************其他可能的錯誤處理*************************************/
            } catch (Exception e) {
                errorMsgs.add("無法取得資料:" + e.getMessage().replaceAll("\r|\n", ""));
                RequestDispatcher failureView = req
                        .getRequestDispatcher("requestURL");
                failureView.forward(req, res);
            }
        }
	}

    // 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
    public String getFileNameFromPart(Part part) {
        String header = part.getHeader("content-disposition");
        System.out.println("header=" + header); // 測試用
        String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
        System.out.println("filename=" + filename); // 測試用
        if (filename.length() == 0) {
            return null;
        }
        return filename;
    }

}
