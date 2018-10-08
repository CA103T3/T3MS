package com.ticketorder.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.print.attribute.standard.RequestingUserName;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.movie.model.MovieService;
import com.session.model.SessionVO;
import com.ticketdetail.model.Ticket_DetailService;
import com.ticketdetail.model.Ticket_DetailVO;
import com.ticketorder.model.Ticket_OrderService;
import com.ticketorder.model.Ticket_OrderVO;

import oracle.net.aso.b;

public class Ticket_OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		response.setContentType("text/html;charset=utf-8");

		if ("selectMovie".equals(action)) {
			String movie_no = request.getParameter("movie_no").trim();
			if (movie_no == null || movie_no.length() == 0) {
				response.sendRedirect(request.getContextPath() + "/forestage/movie_moment/moment_Home.jsp");
				return;
			}

			MovieService moiveSvc = new MovieService();
			List<SessionVO> sessionVOs = null;
		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<>();
			request.setAttribute("errorMsgs", errorMsgs);

			String mem_no = request.getParameter("mem_no"); // 假設會員編號
			String meal_no = "ME0001"; // 假設餐點編號
			String session_no = request.getParameter("session_no"); // 假設場次編號
			String tickettype_no = request.getParameter("tickettype_no");
			Integer order_state = 1; // 訂單狀態成立
			Integer payment_state = 1; //付款狀態成立
			Integer exchange_state = 1; //兌換餐點成功

			try {
				String card1 = request.getParameter("card1").trim();
				if (card1 == null || card1.length() == 0) {
					errorMsgs.add("請輸入卡號");
				}

				String card2 = request.getParameter("card2").trim();
				if (card2 == null || card2.length() == 0) {
					errorMsgs.add("請輸入卡號");
				}
				String card3 = request.getParameter("card3").trim();
				if (card3 == null || card3.length() == 0) {
					errorMsgs.add("請輸入卡號");
				}
				String card4 = request.getParameter("card4").trim();
				if (card4 == null || card4.length() == 0) {
					errorMsgs.add("請輸入卡號");
				}
				StringBuffer credit_card = new StringBuffer();
				credit_card.append(card1).append(card2).append(card3).append(card4);

				String auth_key = request.getParameter("auth_key").trim();
				if (auth_key == null || auth_key.length() == 0) {
					errorMsgs.add("請輸入檢核碼");
				}
				String mm = request.getParameter("mm").trim();
				if (mm == null || mm.length() == 0) {
					errorMsgs.add("請輸入月份");
				}
				String yy = request.getParameter("yy").trim();
				if (yy == null || yy.length() == 0) {
					errorMsgs.add("請輸入年份");
				}
				StringBuffer deadline = new StringBuffer();
				deadline.append(mm).append(yy);

				if (!errorMsgs.isEmpty()) {
					request.getRequestDispatcher("/forestage/ticketOrder/BookingSeat.jsp").forward(request, response);
				}
				String amount = request.getParameter("amount").trim();

				Ticket_OrderService ticketSvc = new Ticket_OrderService();
				Ticket_OrderVO orderVO = new Ticket_OrderVO();
				orderVO.setMem_no(mem_no);
				orderVO.setMeal_no(meal_no);
				orderVO.setAmount(amount);
				orderVO.setOrder_state(order_state);
				orderVO.setPayment_state(payment_state);
				orderVO.setExchange_state(exchange_state);
				orderVO.setCredit_card(credit_card.toString());
				orderVO.setDeadline(deadline.toString());
				orderVO.setAuth_key(auth_key);

				String order_no = ticketSvc.insert(orderVO);
				System.out.println(order_no);
				Ticket_DetailService detailSvc = new Ticket_DetailService();
				Ticket_DetailVO detailVO = new Ticket_DetailVO();
				System.out.println(session_no);
				System.out.println(tickettype_no);
				
				detailVO.setOrder_no(order_no);
				detailVO.setSession_no(session_no);
				detailVO.setTicketType_no(tickettype_no);
				detailVO.setSeat("3ddd3");
				
				String ticket_detail_no = detailSvc.insert(detailVO);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace(System.err);
			}

		}
	}

}
