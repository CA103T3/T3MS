package com.announcement.controllor;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.announcement.model.AnnouncementJNDIDAO;
import com.announcement.model.AnnouncementService;
import com.announcement.model.AnnouncementVO;

@WebServlet("/announcementServlet")
public class AnnouncementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			request.setAttribute("errorMsgs", errorMsgs);
			String backstage_no = "B001"; // 假設後台人員編號

			try {
				String anc_con = request.getParameter("anc_con").trim();
				String finalPage = request.getParameter("finalPage").trim();
				String whichPage = request.getParameter("whichPage").trim();
				String url = "/backstage/announcement/listAnnouncement.jsp?whichPage=" + whichPage;
				if (anc_con == null || anc_con.trim().length() == 0) {
					errorMsgs.add("錯誤：公告內容請勿空白！");
				}

				if (anc_con.length() > 1000) {
					errorMsgs.add("錯誤：公告內容超過長度！");
				}

				if (!errorMsgs.isEmpty()) {
					boolean openupdatereplyform = true; // 再次打開燈箱
					request.setAttribute("openupdatereplyform", openupdatereplyform);
					request.getRequestDispatcher(url).forward(request, response);
					return;
				}
				url = "/backstage/announcement/listAnnouncement.jsp?whichPage=" + finalPage;
				AnnouncementService annSvc = new AnnouncementService();
				annSvc.addAnn(anc_con, backstage_no); //新增一筆公告
				request.getRequestDispatcher(url).forward(request, response);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				boolean openupdatereplyform = true;
				request.setAttribute("openupdatereplyform", openupdatereplyform);
				request.getRequestDispatcher("/backstage/announcement/listAnnouncement.jsp").forward(request, response);
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");

			try {
				String anc_no = request.getParameter("anc_no").trim();
				String anc_con = request.getParameter("anc_con").trim();
				Integer active = new Integer(request.getParameter("active").trim());

				AnnouncementVO annVO = new AnnouncementVO();
				annVO.setAnc_no(anc_no);
				annVO.setAnc_con(anc_con);
				annVO.setActive(active);

				if (anc_con == null || anc_con.trim().length() == 0) {
					errorMsgs.add("錯誤：公告內容請勿空白！");
				}

				if (anc_con.length() > 1000) {
					errorMsgs.add("錯誤：公告內容超過長度！");
				}

				if (!errorMsgs.isEmpty()) {
					request.setAttribute("annVO", annVO);
					boolean openupdatereplyform2 = true; // 再次打開燈箱
					request.setAttribute("openupdatereplyform2", openupdatereplyform2);
					request.getRequestDispatcher(requestURL).forward(request, response);
					return;
				}

				AnnouncementService annSvc = new AnnouncementService();
				annVO = annSvc.updateAnn(anc_no, anc_con, active); // 修改一筆公告
				request.getRequestDispatcher(requestURL).forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("modify error" + e.getMessage());
				boolean openupdatereplyform2 = true;
				request.setAttribute("openupdatereplyform2", openupdatereplyform2);
				request.getRequestDispatcher(requestURL).forward(request, response);
			}
		}

		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				String anc_no = request.getParameter("anc_no").trim();
				AnnouncementService annSvc = new AnnouncementService();
				annSvc.delete(anc_no); //刪除一筆公告
				String url = "/backstage/announcement/listAnnouncement.jsp";
				request.getRequestDispatcher(url).forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("delete" + e.getMessage());
			}
		}

		if ("getOne_bootstrap".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				String anc_no = request.getParameter("anc_no").trim();
				String requestURL = request.getParameter("requestURL").trim();
				Integer active = new Integer(request.getParameter("active").trim());

				AnnouncementJNDIDAO dao = new AnnouncementJNDIDAO();
				AnnouncementVO annVO = dao.findByPrimaryKey(anc_no);

				request.setAttribute("annVO", annVO);
				request.setAttribute("active", active);

				// Bootstrap_modal
				boolean openupdatereplyform2 = true;
				request.setAttribute("openupdatereplyform2", openupdatereplyform2);
				request.getRequestDispatcher(requestURL).forward(request, response);
				return;

			} catch (Exception e) {
				errorMsgs.add("bootstrap error:" + e.getMessage());
			}
		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");

			try {
				String anc_no = request.getParameter("anc_no").trim();
				String url = "/backstage/announcement/update_ann_input.jsp";
				AnnouncementService annSvc = new AnnouncementService();
				AnnouncementVO annVO = annSvc.getOneAnn(anc_no);
				request.setAttribute("annVO", annVO);
				request.getRequestDispatcher(url).forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("modify error" + e.getMessage());
				request.getRequestDispatcher(requestURL).forward(request, response);
			}
		}
	}
}
