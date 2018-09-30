package example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemService;
import com.member.model.MemVO;

import redis.clients.jedis.Jedis;

@WebServlet("/MemRegistermailCheck")
public class MemRegistermailCheck extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");
		
		MemVO memVO=(MemVO) session.getAttribute("memVO");
		System.out.println(memVO.getEmail());
		String str = req.getParameter("code");
		
		String tempAuth = jedis.get(memVO.getMemno());
		if (tempAuth == null) {
			System.out.println("連結已逾時，請重新申請");
			out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
	         out.println("<BODY>連結已逾時，請重新申請<BR>");
	         out.println("<h1 style='color:red;align:center;'>失敗!</h1>");
	         out.println("</BODY></HTML>");
		} else if (str.equals(tempAuth)){
			System.out.println("驗證成功!");
			System.out.println(memVO.getEmail());
			memVO.setStatus(1);
			MemService memSrc = new MemService();
			memSrc.passemail(memVO.getEmail());
			res.sendRedirect(req.getContextPath()+"/forestage/member/successin.jsp");
		} else {
			System.out.println("驗證有誤，請重新申請");
			 out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
	         out.println("<BODY>驗證碼錯誤!<BR>");
	         out.println("<h1 style='color:red;align:center;'>失敗!</h1>");
	         out.println("</BODY></HTML>");
		}

		
		jedis.close();
		
	}

}
