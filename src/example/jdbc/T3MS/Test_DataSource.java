package example.jdbc.T3MS;

/** 自行取得DataSource的 servlet
 
 1.需配合 web.xml 如下:
    <resource-ref>
      <description>DB Connection</description>
      <res-ref-name>jdbc/TestDB</res-ref-name>
      <res-type>javax.sql.DataSource</res-type>
      <res-auth>Container</res-auth>
    </resource-ref>
 2.需配合 server.xml
    -參考: http://localhost:8080/index.jsp 首頁
             之 Tomcat Documentation 之 JNDI DataSource HOW-TO 的說明
    -注意: 隨 servlet container 版本寫法會不同              
 */
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import javax.naming.*;
import javax.sql.*;
import java.sql.*;

@WebServlet("/Test_DataSource")
public class Test_DataSource extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -7163332003172065793L;

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = res.getWriter();

        try {
            Context ctx = new javax.naming.InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/T3MS");
            if (ds != null) {
                Connection conn = ds.getConnection();

                if (conn != null) {
                    out.println("Got Connection: " + conn.toString());
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from cinema");
                    while (rs.next()) {
                        out.println("cinema_no = " + rs.getString(1));
                    }
                    conn.close();
                }
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}