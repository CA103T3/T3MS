package example.imageutil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.util.ImageUtil;



/**
 * Servlet implementation class ImageUtilTestServlet
 */
@WebServlet("/ImageUtilTestServlet")
public class ImageUtilTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageUtilTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/plain; charset=UTF-8");

        PrintWriter out = res.getWriter();
		res.getWriter().append("Served at: ").append(req.getContextPath());
		out.println();
		//String filepath = req.getContextPath() + "/img/01.jpg";
		String filepath = "/img/01.jpg";
		String realPath = getServletContext().getRealPath("/img/01.jpg");
        out.println("realPath:" + realPath); //realPath:D:\T3_workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\T3MS\img\01.jpg
        System.out.println("realPath:" + realPath);
//		File f = new File(filepath);
		File f = new File(realPath);
        if (f.exists()) {
            out.println("file exists");
            System.out.println("file exists");
        } else {
            out.println("file doesn't exist: " + filepath);
            System.out.println("file doesn't exist: " + filepath);
        }

        byte[] pic = ImageUtil.getPictureByteArray(realPath);
        int imageSize = 260; //pixel
        pic = ImageUtil.shrink(pic, imageSize);
        String fileStart = realPath.substring(0, realPath.lastIndexOf("."));
        out.println("fileStart : " + fileStart); //fileStart : D:\T3_workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\T3MS\img\01
        String fileExt = realPath.substring(realPath.lastIndexOf("."));
        out.println("fileExt : " + fileExt); //fileExt : .jpg
        String target = fileStart + "-s" + fileExt;
        out.println("target : " + target);

        ImageUtil.outputPicture(pic, target);
	}

	/*
    // 使用byte[]方式
    public static byte[] getPictureByteArray(String path) throws IOException {
        File file = new File(path);
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

    // Handle with byte array data
    public static void outputPicture(byte[] bytes, String path) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(bytes);
        fos.flush();
        fos.close();
    }
    */
}
