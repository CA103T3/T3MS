package com.ticketorder.model;

import java.sql.Connection;
import java.util.List;

public class Ticket_OrderService {
	private Ticket_OrderDAO_Interface dao;

	public Ticket_OrderService() {
		dao = new Ticket_OrderJNDIDAO();
	}

	public String insert(Ticket_OrderVO ticket_OrderVO) {
		return dao.insert(ticket_OrderVO);
	}

	public void update(String order_no, Integer amount, Integer order_state, Integer payment_state,
			Integer exchange_state, Connection conn) {
		dao.update(order_no, amount, order_state, payment_state, exchange_state, conn);
	}
	
	public void updateAmount(String order_no,Integer amount,Connection conn) {
		dao.updateAmount(order_no, amount, conn);
	}

	public void delete(Ticket_OrderVO ticket_OrderVO) {
		dao.delete(ticket_OrderVO);
	}

	public Ticket_OrderVO findByPrimaryKey(String order_no) {
		return dao.findByPrimaryKey(order_no);
	}

	public List<Ticket_OrderVO> findAllOrdersByMember(String mem_no) {
		return dao.findAllOrdersByMember(mem_no);
	}

}
