package com.ticketdetail.model;

import java.sql.Connection;
import java.util.List;

public interface Ticket_DetailDAO_Interface {
	public String insert(Ticket_DetailVO ticket_DetailVO);

	public Integer deleteOneTicketDetail(String ticket_detail_no);

	public void deleteOneTicketOrder(String order_no, Connection conn);

	public Ticket_DetailVO findOneTicketDetail(String ticket_detail_no);

	public List<Ticket_DetailVO> findOneTicketOrder(String order_no);

	public void insertConn(Ticket_DetailVO ticket_DetailVO, Connection conn);

	public List<Ticket_DetailVO> findBySession(String session_no);

	public void delete_UpdateTicketOrder_UpdateMovieSession(String ticket_detail_no, String order_no, Integer amount,
			String session_no, String seat_table);

}
