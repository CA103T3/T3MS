package example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.json.JSONObject;

/**
 * Servlet implementation class Ajax_doPost
 */
@WebServlet("/Ajax_doPost.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
//上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常

public class Ajax_doPost extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String saveDirectory = "/images_uploaded"; // 上傳檔案的目地目錄;  用 java.io.File 於 ContextPath 之下, 自動建立目地目錄

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ajax_doPost() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

//      response.setContentType("text/html; charset=UTF-8");
        response.setContentType("text/plain; charset=utf-8");
        System.out.println("Ajax_doPost.do");
        PrintWriter out = response.getWriter();
        
        JSONObject json = new JSONObject();
        
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            String[] values = request.getParameterValues(name);

            if(values.length > 0) {
                //out.println(name + " :");
                for(int i = 0; i < values.length; i++) {
                    //out.println(i + " : " + values[i]);
                }
            }
            
            ArrayList<String> list = new ArrayList<String>(Arrays.asList(values));
            json.put(name, list);
        }
        
        String realPath = getServletContext().getRealPath(saveDirectory);
        File fsaveDir = new File(realPath);
        if(!fsaveDir.exists()) {
            fsaveDir.mkdirs();
        }
        
        ArrayList<String> files = new ArrayList<String>();
        Collection<Part> parts = request.getParts();
        for (Part part : parts) { 
            String filename = getFileNameFromPart(part);
            if(filename != null && part.getContentType() != null) {
                File f = new File(fsaveDir, filename);
                // 利用File物件,寫入目地目錄,上傳成功
                try {
                    part.write(f.toString());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                files.add(filename);
            }
        }
        json.put("files", files);
        out.print(json);
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
