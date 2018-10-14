package com.ticketorder.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.movie.model.MovieService;
import com.session.model.SessionService;
import com.session.model.SessionVO;
import com.sun.javafx.collections.MappingChange.Map;
import com.ticketdetail.model.Ticket_DetailService;
import com.ticketdetail.model.Ticket_DetailVO;
import com.ticketorder.model.Ticket_OrderService;
import com.ticketorder.model.Ticket_OrderVO;

import oracle.net.aso.a;
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
		response.setContentType("text/html;charset=utf-8");
		String action = request.getParameter("action");

		if ("selectMovie".equals(action)) {
			String movie_no = request.getParameter("movie_no").trim();
			if (movie_no == null || movie_no.length() == 0) {
				response.sendRedirect(request.getContextPath() + "/forestage/movie_moment/moment_Home.jsp");
				return;
			}
		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<>();
			request.setAttribute("errorMsgs", errorMsgs);
			MailService mailService = new MailService();
			String mem_no = request.getParameter("mem_no").trim(); // 會員編號
			String meal_no = "ME0001"; // 假設餐點編號
			String session_no = request.getParameter("session_no").trim(); // 場次編號
			String tickettype_no = request.getParameter("tickettype_no").trim(); // 票種編號
			String mem_email = request.getParameter("mem_email").trim(); // 會員信箱
			String mem_FullName = request.getParameter("mem_FullName").trim(); // 會員姓名
			System.out.println("name " + mem_FullName);
			String bookingSeats = request.getParameter("seatTD").trim(); // 已訂購的座位
			String TempFileName = request.getParameter("TempFileName").trim(); // QRcode 檔案名稱
			String TempFilePath = request.getParameter("TempFilePath").trim(); // QRcode 儲存路徑
			System.out.println("=TempFileName=" + TempFileName);
			System.out.println("=TempFilePath=" + TempFilePath);

			System.out.println();

			System.out.println(bookingSeats);
			String[] bookingSeatArr = bookingSeats.split("@");

			HashMap<String, String> seat_ticketType = new HashMap<String, String>();
			for (int i = 0; i < bookingSeatArr.length; i++) {
				seat_ticketType.put(bookingSeatArr[i], tickettype_no);
			}

			Integer order_state = 1; // 訂單狀態成立
			Integer payment_state = 1; // 付款狀態成立
			Integer exchange_state = 0; // 餐點未兌換

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
					return;
				}
				String amount = request.getParameter("amount").trim();

				// 一張訂單會多張明細
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

				String order_no = ticketSvc.insert_con(orderVO, seat_ticketType, session_no);
				System.out.println("order_no=" + order_no);
				System.out.println("session_no=" + session_no);
				System.out.println("tickettype_no=" + tickettype_no);

				// 新增完後一筆訂單取出訂單編號，再取出uuid產生QRcode
				Ticket_OrderVO orderVO2 = ticketSvc.findByPrimaryKey(order_no);
				String uuidOK = orderVO2.getUuid();
				String successView = "/forestage/ticketOrder/BookingSuccess.jsp?uuid=" + uuidOK + "&bookingSeats="
						+ bookingSeats + "&session_no=" + session_no;
				String imgpath = TempFilePath + TempFileName;
				genQRcodegood genQRcod = new genQRcodegood();
				String send = genQRcod.genQRCode(140, 140, uuidOK, imgpath);

				if (send == "OK") {
					System.out.println("QRcode存檔成功");
				} else {
					System.out.println("QRcode存檔失敗");
				}

				System.out.println("imgpath=" + imgpath);
				mailService.sendMail(mem_email, "M&S電影平台 購票通知", imgpath, mem_FullName);
				request.getRequestDispatcher(successView).forward(request, response);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace(System.err);
			}
		}

		if ("search_ticketDetail_seats".equals(action)) {
			String uuid = request.getParameter("uuid").trim();

			Ticket_OrderService orderSvc = new Ticket_OrderService();
			List<String> seats = orderSvc.search_TicketDetail(uuid);
			System.out.println(seats);
			Iterator<String> is = seats.iterator();
			StringBuffer sb = new StringBuffer();
			while (is.hasNext()) {
				sb.append("seat=" + is.next() + "&");
			}
			System.out.println(sb);
			String refundView = "/forestage/ticketOrder/BookingRefund.jsp?" + sb;
			request.getRequestDispatcher(refundView).forward(request, response);
		}

		if ("del_ticket_open_seat".equals(action)) {
			System.out.println("=========Delete_Start===========");
			String uuid = request.getParameter("uuid").trim();
			System.out.println("UUID=" + uuid);
			String a_seat = request.getParameter("a_seat").trim(); // 要退票的座位
			Integer price = Integer.valueOf(request.getParameter("price"));
			Integer amount = Integer.valueOf(request.getParameter("amount"));
			Integer update_amount = amount - price; // 總價-單價

			Ticket_OrderService ticket_OrderSvc = new Ticket_OrderService();
			Ticket_OrderVO ticket_OrderVO = ticket_OrderSvc.find_oneOrder_by_uuid(uuid);
			String order_no = ticket_OrderVO.getOrder_no(); // 訂單編號
			System.out.println("order_no=" + order_no);

			Ticket_DetailService tDetailSvc = new Ticket_DetailService();
			Ticket_DetailVO ticket_DetailVO = tDetailSvc.find_TicketDetail_By_OrderNo(order_no);
			String session_no = ticket_DetailVO.getSession_no(); // 取得該場次編號
			System.out.println("session_no=" + session_no);

			SessionService sessionSvc = new SessionService();
			SessionVO sessionVO = sessionSvc.getOneSession(session_no);
			String seat_table = sessionVO.getSeat_table(); // 取得該場次座位資訊
			System.out.println("==before==seat_table=" + seat_table);

			// 更改座位資訊，轉成JSONobject，取出key，因value是JSONarray所以再另外宣告來接值
			JSONObject jsonObject = new JSONObject(seat_table);
			JSONArray jsonArray = null;
			Iterator<String> iterator = jsonObject.keys();
			while (iterator.hasNext()) {
				String key = iterator.next();
				if (key.equals(a_seat)) {
					jsonArray = jsonObject.getJSONArray(key);
					System.out.println("===before===" + jsonArray);
					jsonArray.put(1, "2");
					System.out.println("===after===" + jsonArray);
				}
			}
			seat_table = jsonObject.toString();
			System.out.println("==after==seat_table" + seat_table);
			// 刪除訂單中的一筆座位，再修改訂單總金額，再修改該場次座位，再檢查訂單是否還有明細，若無則刪除訂單
			tDetailSvc.delete_one_detail_update_seat(uuid, a_seat, update_amount, session_no, seat_table);
		}
	}
}
