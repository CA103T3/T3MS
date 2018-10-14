package com.ticketorder.model;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

public interface Ticket_OrderDAO_Interface {

	public String insert(Ticket_OrderVO ticket_OrderVO);

	public String insert_con(Ticket_OrderVO ticket_OrderVO, HashMap<String, String> seat_ticketType, String session_no);

	public void update(String order_no, Integer amount, Integer order_state, Integer payment_state,
			Integer exchange_state, Connection conn);

	public void updateAmount(String uuid, Integer amount, Connection conn);

	public List<String> search_TicketDetail(String uuid);

	public void delete(Ticket_OrderVO ticket_OrderVO);

	public Ticket_OrderVO findByPrimaryKey(String order_no);
	
	public Ticket_OrderVO find_oneOrder_by_uuid(String uuid);

	public List<Ticket_OrderVO> findAllOrdersByMember(String mem_no);

}
