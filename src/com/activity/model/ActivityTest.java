package com.activity.model;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@MultipartConfig
@WebServlet("/ActivityTest")
public class ActivityTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ActivityService actSvc;
	private Connection conn = null;
	private DataSource ds = null;
	

	@Override
	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/T3MS");
			conn = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(System.err);
			}
		}
	}

	// INSERT INTO ACTIVITY (ACTIVITY_NO,
	// ACTIVITY_NAME,
	// ACTIVITY_DESC,
	// BACKSTAGE_NO,
	// CREATED_AT,
	// UPDATED_AT,
	// ACTIVE,
	// IMG_PATH,
	// ACTIVITY_URL)
	// VALUES
	// ('AC'||LPAD(ACTIVITY_SEQ.NEXTVAL,4,'0'),'test','testact','B01',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1,empty_blob(),'http://sdf');
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("image/jpg;charset=utf-8");
		ServletOutputStream servletOut = response.getOutputStream();

//		PrintWriter out = response.getWriter();

//		out.append("Served at: ").append(request.getContextPath()).append("\n");
		actSvc = new ActivityService();
		String activity_no = "AC0006";
		// addTest(img2);
		try {
			String sql = "select img_path from activity where activity_no='" + activity_no + "'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
//			String path = "/Users/chengmark/Pictures/moon/fishmoon24.jpg";

			while (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("img_path"));
				
				byte[] buf = new byte[in.available()];
				servletOut.write(buf);
				servletOut.close();
				rs.close();
				pstmt.close();
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}

	}

	public static byte[] getPicByteArr(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, 1);
		}
		baos.close();
		fis.close();
		return baos.toByteArray();
	}

	public void getDBPic(String activity_no) {
		try {
			String sql = "select img_path from activity where activity_no='" + activity_no + "'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
//			String path = "/Users/chengmark/Pictures/moon/fishmoon24.jpg";

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("img_path"));
				byte[] buf = new byte[4*1024];
				int len;
				while((len=in.read(buf))!=-1) {
					
				}
//				InputStream fis = new FileInputStream(path);
//				byte[] img2 = new byte[fis.available()];
//				fis.read(img2);
//				servletOut.write(img2);

//				fis.close();
//				servletOut.close();
				rs.close();
				pstmt.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(System.err);
		} catch (IOException e) {
			// TODO: handle exception
			e.getMessage();
		}
	}

	public void addTest(byte[] img_path) {

		String activity_name = "testAct";
		String activity_desc = "testActDesc";
		String backstage_no = "B01";
		Integer active = 1;
		String activity_url = "http://adsaf.asdf.com";

		actSvc.addAct(activity_name, activity_desc, backstage_no, active, img_path, activity_url);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
