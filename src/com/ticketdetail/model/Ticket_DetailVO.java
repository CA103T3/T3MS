package com.ticketdetail.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Ticket_DetailVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ticket_detail_no;
	private String order_no;
	private String session_no;
	private String ticketType_no;
	private String seat;
	private Timestamp created_at;
	private Timestamp updated_at;

	public String getTicket_detail_no() {
		return ticket_detail_no;
	}

	public void setTicket_detail_no(String ticket_detail_no) {
		this.ticket_detail_no = ticket_detail_no;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getSession_no() {
		return session_no;
	}

	public void setSession_no(String session_no) {
		this.session_no = session_no;
	}

	public String getTicketType_no() {
		return ticketType_no;
	}

	public void setTicketType_no(String ticketType_no) {
		this.ticketType_no = ticketType_no;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
