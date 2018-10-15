package com.ticketorder.model;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.sun.javafx.collections.MappingChange.Map;
import com.ticketdetail.model.Ticket_DetailVO;

public class Ticket_OrderService {
	private Ticket_OrderDAO_Interface dao;

	public Ticket_OrderService() {
		dao = new Ticket_OrderJNDIDAO();
	}

	public String insert(Ticket_OrderVO ticket_OrderVO) {
		return dao.insert(ticket_OrderVO);
	}

	public String insert_con(Ticket_OrderVO ticket_OrderVO, HashMap<String, String> seat_ticketType,
			String session_no) {
		return dao.insert_con(ticket_OrderVO, seat_ticketType, session_no);
	}

	public List<String> search_TicketDetail(String uuid) {
		return dao.search_TicketDetail(uuid);
	}

	public void update(String order_no, Integer amount, Integer order_state, Integer payment_state,
			Integer exchange_state, Connection conn) {
		dao.update(order_no, amount, order_state, payment_state, exchange_state, conn);
	}

	public void updateAmount(String order_no, Integer amount, Connection conn) {
		dao.updateAmount(order_no, amount, conn);
	}

	public void delete(Ticket_OrderVO ticket_OrderVO) {
		dao.delete(ticket_OrderVO);
	}

	public Ticket_OrderVO findByPrimaryKey(String order_no) {
		return dao.findByPrimaryKey(order_no);
	}

	public Ticket_OrderVO find_oneOrder_by_uuid(String uuid) {
		return dao.find_oneOrder_by_uuid(uuid);
	}

	public List<Ticket_OrderVO> findAllOrdersByMember(String mem_no) {
		return dao.findAllOrdersByMember(mem_no);
	}

	public List<Ticket_Refund_tempVO> find_Order_Movie_By_orderNo(String order_no) {
		return dao.find_Order_Movie_By_orderNo(order_no);
	}
}
