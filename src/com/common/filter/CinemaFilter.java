package com.common.filter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.account.model.AccountVO;
import com.role.model.RoleService;
import com.role.model.RoleVO;

/**
 * Servlet Filter implementation class CinemaFilter
 */
@WebFilter("/CinemaFilter")
public class CinemaFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CinemaFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html; charset=utf-8");
        //PrintWriter out = res.getWriter();
        List<String> errorMsgs = new LinkedList<String>();
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        req.setAttribute("errorMsgs", errorMsgs);
        String cinema_no = req.getParameter("cinema_no");
        if(cinema_no == null || cinema_no.trim().length() == 0) {
            errorMsgs.add("影城編號請勿空白");
        }
        System.out.println("CinemaFilter cinema_no : " + cinema_no);
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpSession session = httpRequest.getSession();
        String url = httpRequest.getRequestURI();
        AccountVO aVO = (AccountVO)session.getAttribute("aVO");
        RoleService roleSvc = new RoleService();
        RoleVO roleVO = roleSvc.findbyno(aVO.getRole_no());
        String roleName = roleVO.getBr_name();
        String aVO_cinema_no = aVO.getCinema_no();
        if("Cinema".equals(roleName)) {
            if(!aVO_cinema_no.equals(cinema_no)) {
            	errorMsgs.add("無權限瀏覽此影城資訊");
            }
        } else if("Admin".equals(roleName)) {
        	//do nothing
        } else {
        	errorMsgs.add("無權限瀏覽" + url);
        }

        // Send the use back to the form, if there were errors
        if (!errorMsgs.isEmpty()) {
//            System.out.println("errorMsgs.size() : " + errorMsgs.size());

            RequestDispatcher failureView = req
                    .getRequestDispatcher("/backstage/backstage_index.jsp");
            failureView.forward(req, res);
            return;//程式中斷
        }
        
		// pass the request along the filter chain
		chain.doFilter(req, res);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
