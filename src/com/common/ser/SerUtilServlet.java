package com.common.ser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SerUtilServlet
 */
@WebServlet("/SerUtilServlet")
public class SerUtilServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    PrintWriter out;
    private String[] voList = {"MovieVO", "CinemaVO", "TheaterVO", "MemberVO", "FilmreviewVO", "Movie_IntroduceVO",
    		"SessionVO", "TypeVO","NavVO","RoleVO","PermissionVO","AccountVO"};
    private String packagePath = "com.common.ser.";
    private SerUtil su;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SerUtilServlet() {
        super();
        su = new SerUtil();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse res)
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/plain; charset=UTF-8");

        out = res.getWriter();
        out.append("Served at: ").append(req.getContextPath()).append("\n");

        Enumeration enu = req.getParameterNames();
        if (enu.hasMoreElements()) {
            out.println("enu.hasMoreElements ");
            String action = req.getParameter("action");
            if (action == null || action.trim().length() == 0) {
                out.println("no parameter action");
                return;
            }
            out.println("action : " + action);
            String voName = req.getParameter("vo");
            if (voName == null || voName.trim().length() == 0) {
                out.println("no parameter vo");
                return;
            }
            out.println("voName : " + voName);
            SerStrategy_interface si = getSerStrategy(voName);
            if(si == null) {
                out.println("can not find " + voName + "Ser");
                return;
            }
            su.setStrategy(si);
            String realPath = getServletContext().getRealPath("/");
            su.setServletContextRealPath(realPath);

            String no = req.getParameter("no");
            out.println("no : " + no);
            try {
                executeAction(action, no);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            //no parameters, run default
            //out.println("before importAll : ");
            try {
                importAll();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void executeAction(String action, String no) throws Exception {
        if("add".equals(action)) {
            if (no == null || no.trim().length() == 0) {
                su.importAll();
            } else {
                su.importOne(action, no);
            }
        } else if ("update".equals(action)) {
            if (no == null || no.trim().length() == 0) {
                su.importUpdateAll();
            } else {
                su.importOne(action, no);
            }
        } else if ("export".equals(action)) {
            if (no == null || no.trim().length() == 0) {
                su.export(su.getAllVO());
            } else {
                su.export(su.findVOByNo(no));
            }
        }
    }

    public void importAll() throws Exception {
        out.println("import all ser");
        for(int i = 0; i < voList.length; i++) {
            String voName = voList[i];
            SerStrategy_interface si = getSerStrategy(voName);
            if(si == null) {
                out.println("can not find " + voName + "Ser");
                return;
            }
            su.setStrategy(si);
            String realPath = getServletContext().getRealPath("/");
            su.setServletContextRealPath(realPath);
            su.importAll();
        }
    }

    public SerStrategy_interface getSerStrategy(String voName) {
        SerStrategy_interface si = null;
        try {
            si = (SerStrategy_interface) Class.forName(packagePath + voName + "Ser").newInstance();
            out.println(packagePath + voName + "Ser");
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return si;
    }

}
