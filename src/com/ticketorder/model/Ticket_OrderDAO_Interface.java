package com.ticketorder.model;


import java.sql.Connection;
import java.util.List;

public interface Ticket_OrderDAO_Interface {

	public String insert(Ticket_OrderVO ticket_OrderVO);

	public void update(String order_no, Integer amount, Integer order_state, Integer payment_state,
			Integer exchange_state, Connection conn);

	public void updateAmount(String order_no,Integer amount,Connection conn);
	
	public void delete(Ticket_OrderVO ticket_OrderVO);

	public Ticket_OrderVO findByPrimaryKey(String order_no);

	public List<Ticket_OrderVO> findAllOrdersByMember(String mem_no);

}
