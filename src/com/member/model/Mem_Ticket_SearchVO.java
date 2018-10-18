package com.member.model;

import java.sql.Timestamp;

public class Mem_Ticket_SearchVO implements Comparable<Mem_Ticket_SearchVO> {
	private String movie_name;
	private String movie_no;
	private String uuid;
	private String session_no;
	private Timestamp session_time;
	private Integer price;
	private Integer amount;

	public String getMovie_name() {
		return movie_name;
	}

	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}

	public String getMovie_no() {
		return movie_no;
	}

	public void setMovie_no(String movie_no) {
		this.movie_no = movie_no;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setSession_no(String session_no) {
		this.session_no = session_no;
	}

	public String getSession_no() {
		return session_no;
	}

	public Timestamp getSession_time() {
		return session_time;
	}

	public void setSession_time(Timestamp session_time) {
		this.session_time = session_time;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public int compareTo(Mem_Ticket_SearchVO o) {
		System.out.println("mem_ticket_searchVO.session=" + o.session_no);
		if (o.session_no != null) {
			if (this.session_no.equals(o.session_no)) {
				return 0;
			}
		}
		return 1;
	}
}
