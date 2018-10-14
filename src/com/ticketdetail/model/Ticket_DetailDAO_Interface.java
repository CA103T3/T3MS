package com.ticketdetail.model;

import java.sql.Connection;
import java.util.List;

public interface Ticket_DetailDAO_Interface {
	public String insert(Ticket_DetailVO ticket_DetailVO);

	public Integer deleteOneTicketDetail(String ticket_detail_no);

	public void deleteOneTicketOrder(String uuid, Connection conn);

	public Ticket_DetailVO findOneTicketDetail(String ticket_detail_no);

	public List<Ticket_DetailVO> find_ticketDetail_list(String order_no);

	public void insertConn(Ticket_DetailVO ticket_DetailVO, Connection conn);

	public List<Ticket_DetailVO> findBySession(String session_no);
	
	public Ticket_DetailVO find_TicketDetail_By_OrderNo(String order_no);

	public void delete_UpdateTicketOrder_UpdateMovieSession(String ticket_detail_no, String order_no, Integer amount,
			String session_no, String seat_table);

	// 先刪除訂單中的一筆座位，再修改訂單總金額，再修改該場次座位，再檢查訂單是否還有明細，若無則刪除訂單
	public void delete_one_detail_update_seat(String uuid, String seat, Integer amount, String session_no,
			String seat_table);

}
