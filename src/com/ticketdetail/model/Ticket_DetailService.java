package com.ticketdetail.model;

import java.sql.Connection;
import java.util.List;

public class Ticket_DetailService {
	private Ticket_DetailDAO_Interface dao;

	public Ticket_DetailService() {
		dao = new Ticket_DetailJNDIDAO();
	}

	public String insert(Ticket_DetailVO ticket_DetailVO) {
		return dao.insert(ticket_DetailVO);
	}

	public Integer deleteOneTicketDetail(String ticket_detail_no) {
		return dao.deleteOneTicketDetail(ticket_detail_no);
	}

	public void deleteOneTicketOrder(String uuid, Connection conn) {
		dao.deleteOneTicketOrder(uuid, conn);
	}

	public Ticket_DetailVO findOneTicketDetail(String ticket_detail_no) {
		return dao.findOneTicketDetail(ticket_detail_no);
	}

	public List<Ticket_DetailVO> find_ticketDetail_list(String order_no) {
		return dao.find_ticketDetail_list(order_no);
	}

	public void insertConn(Ticket_DetailVO ticket_DetailVO, Connection conn) {
		dao.insertConn(ticket_DetailVO, conn);
	}

	public List<Ticket_DetailVO> findBySession(String session_no) {
		return dao.findBySession(session_no);
	}

	public Ticket_DetailVO find_TicketDetail_By_OrderNo(String order_no) {
		return dao.find_TicketDetail_By_OrderNo(order_no);
	}

	public void delete_UpdateTicketOrder_UpdateMovieSession(String ticket_detail_no, String order_no, Integer amount,
			String session_no, String seat_table) {
		dao.delete_UpdateTicketOrder_UpdateMovieSession(ticket_detail_no, order_no, amount, session_no, seat_table);
	}

	public void delete_one_detail_update_seat(String uuid, String seat, Integer amount, String session_no,
			String seat_table) {
		dao.delete_one_detail_update_seat(uuid, seat, amount, session_no, seat_table);
	}

}
