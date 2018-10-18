package com.common.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.account.model.*;
import com.nav_backstage.model.NavService;
import com.nav_backstage.model.NavVO;
import com.permission.model.PermissionService;

@WebFilter("/Back_accountFilter")
public class Back_accountFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
    	
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 1. 檢查是否已登入
        AccountVO aVO = (AccountVO) session.getAttribute("aVO");

        // 2. 沒登入=>轉交
        if (aVO == null) {
            request.setAttribute("message", "轉交至登入畫面");
            request.getRequestDispatcher("/backstage/staff/backstage_login.jsp").forward(request, response);
            return;
        }

        // 3. 得到用户想访问的资源
        String url = request.getRequestURI();
        System.out.println(url);
        // 4. 得到访问该资源需要的权限
        NavService nSvc = new NavService();
        NavVO nVO = nSvc.findbyurl(url);
        /*
         * 你要访问的资源，我在权限管理系统里面没有说访问这个资源需要权限，
         * 也即这个资源不需要被权限系统控制，只有被权限系统控制的资源，在数据库里面
         * 才有，如果为null，这个资源不受权限系统控制。
         */
        if (nVO == null) {
            chain.doFilter(req, res);
            return;
        }
        String p = nVO.getListitem_no(); // 得到访问资源需要的权限
        PermissionService pSvc = new PermissionService();
        
        // 5. 判断用户是否有相应权限
        List<String> list = pSvc.getOnesP(aVO.getRole_no()); // 得到用户所有权限
        if (!list.contains(p)) {
            // 6. 没有权限，则提示用户权限不足，联系管理员
            request.setAttribute("nop", url);
            
            
            request.getRequestDispatcher("/backstage/backstage_index.jsp").forward(request, response);
            
            
            out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
            out.println("<BODY><h1>你無權限</h1>");
//            out.println("請按上一頁 <A HREF="+request.getContextPath()+"/backstage/stage/backstge_insert.jsp>按</A>");
            out.println("</BODY></HTML>");
            return;
        }

        // 7. 如果有，则则放行
        chain.doFilter(req, res);

    }

    @Override
    public void destroy() {
        
    }
    

}
