package android.com.login.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.member.model.MemDAO_interface;
import com.member.model.MemJDBCDAO;
import com.member.model.MemJNDIDAO;

//import idv.david.member.model.Member;
//import idv.david.member.model.MemberDAO;
//import idv.david.member.model.MemberDAOImpl;

/**
 * Servlet implementation class MobileLoginServlet
 */
@WebServlet("/MobileLoginServlet")
public class MobileLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MobileLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // TODO Auto-generated method stub
//        response.getWriter().append("Served at: ").append(request.getContextPath());
        
        doPost(req, res);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
        req.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        BufferedReader br = req.getReader();
        StringBuilder jsonIn = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            jsonIn.append(line);
        }
        System.out.println("input: " + jsonIn);
        MemDAO_interface memberDao = new MemJNDIDAO();
        JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
        
        String action = jsonObject.get("action").getAsString();

        System.out.println("action = "+ action);
        System.out.println("jsonObj = "+ jsonObject);

        
        if (action.equals("isMember")) {
            String userId = jsonObject.get("userId").getAsString();
            String password = jsonObject.get("password").getAsString();
            writeText(res,    String.valueOf(memberDao.isuserlogin(userId, password)));
        }
        
//        else if (action.equals("isUserIdExist")) {
//            String userId = jsonObject.get("userId").getAsString();
//            writeText(res, String.valueOf(memberDao.isUserIdExist(userId)));
//        }
        
        
//        } else if (action.equals("add")) {
//            Member member = gson.fromJson(jsonObject.get("member").getAsString(), Member.class);
//            writeText(res, String.valueOf(memberDao.add(member)));
//        } else if (action.equals("findById")) {
//            String userId = jsonObject.get("userId").getAsString();
//            Member member = memberDao.findById(userId);
//            writeText(res, member == null ? "" : gson.toJson(member));
//        } else if (action.equals("update")) {
//            Member member = gson.fromJson(jsonObject.get("member").getAsString(), Member.class);
//            writeText(res, String.valueOf(memberDao.update(member)));
//        }
    }
        
        
            
    private void writeText(HttpServletResponse res, String outText) throws IOException {
        res.setContentType(CONTENT_TYPE);
        PrintWriter out = res.getWriter();
        out.print(outText);
        out.close();
        System.out.println("outText: " + outText);

    }

}