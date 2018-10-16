package com.activity.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;

@MultipartConfig
public class ActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getParameter("action");

		if ("view".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			request.setAttribute("errorMsgs", errorMsgs);
			// String activity_no = request.getParameter("activity_no");
			// System.out.println(activity_no);
			

		}
		
		if ("getOne_For_Delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL").trim();
			System.out.println("requestURL="+requestURL);
			String anc_no = request.getParameter("activity_no").trim();
			try {
				boolean deleteAnn = true;
				request.setAttribute("deleteAnn", deleteAnn);
				request.setAttribute("activity_no", anc_no);
				request.getRequestDispatcher(requestURL).forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("modify error" + e.getMessage());
				request.getRequestDispatcher(requestURL).forward(request, response);
			}
			
		}
		
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				String activity_no = request.getParameter("activity_no").trim();
				ActivityService activityService = new ActivityService();
				activityService.deleteAct(activity_no);
				String url = "/backstage/activity/listActivity.jsp";
				request.getRequestDispatcher(url).forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("delete" + e.getMessage());
			}
		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			request.setAttribute("errorMsgs", errorMsgs);
			String url = "/backstage/activity/listActivity.jsp";
			String backstage_no = request.getParameter("backstage_no");
			try {
				String activity_name = request.getParameter("activity_name").trim();
				if (activity_name == null || activity_name.trim().length() == 0) {
					errorMsgs.add("活動名稱請勿空白");
				}

				String activity_desc = request.getParameter("activity_desc").trim();
				if (activity_desc == null || activity_desc.trim().length() == 0) {
					errorMsgs.add("活動描述請勿空白");
				}

				InputStream in = request.getPart("img_pic").getInputStream();
				if (in == null || in.available() == 0) {
					errorMsgs.add("請上傳圖片");
				}
				byte[] img_pic = new byte[in.available()];
				in.read(img_pic);
				in.close();

				String activity_url = request.getParameter("activity_url").trim();
				if (activity_url == null || activity_url.trim().length() == 0) {
					errorMsgs.add("參考連結請勿空白");
				}

				Integer active = new Integer(request.getParameter("active"));

				ActivityVO activityVO = new ActivityVO();

				activityVO.setActivity_name(activity_name);
				activityVO.setActivity_desc(activity_desc);
				activityVO.setBackstage_no(backstage_no);
				activityVO.setActive(active);
				activityVO.setImg_pic(img_pic);
				activityVO.setActivity_url(activity_url);

				ActivityService activitySvc = new ActivityService();

				if (!errorMsgs.isEmpty()) {

					boolean openupdatereplyform = true; // 再次打開燈箱
					request.setAttribute("openupdatereplyform", openupdatereplyform);
					request.setAttribute("actVO", activityVO);
					RequestDispatcher errorView = request.getRequestDispatcher(url);
					errorView.forward(request, response);
					return;
				}

				activityVO = activitySvc.addAct(activity_name, activity_desc, backstage_no, active, img_pic,
						activity_url);
				// String openupdatereplyform =null;
				// request.setAttribute("openupdatereplyform", openupdatereplyform);
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				boolean openupdatereplyform = true; // 再次打開燈箱
				request.setAttribute("openupdatereplyform", openupdatereplyform);
				RequestDispatcher errorView = request.getRequestDispatcher(url);
				errorView.forward(request, response);
			}
		}

	}

}
