package com.ticketorder.model;

import java.sql.Timestamp;

public class Ticket_Refund_tempVO {

	private String movie_name;
	private String movie_no;
	private String uuid;
	private Timestamp session_time;
	private String seat;
	private Integer price;
	private Integer amount;
	/**
	 * @return the movie_name
	 */
	public String getMovie_name() {
		return movie_name;
	}
	/**
	 * @param movie_name the movie_name to set
	 */
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	/**
	 * @return the movie_no
	 */
	public String getMovie_no() {
		return movie_no;
	}
	/**
	 * @param movie_no the movie_no to set
	 */
	public void setMovie_no(String movie_no) {
		this.movie_no = movie_no;
	}
	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}
	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * @return the session_time
	 */
	public Timestamp getSession_time() {
		return session_time;
	}
	/**
	 * @param session_time the session_time to set
	 */
	public void setSession_time(Timestamp session_time) {
		this.session_time = session_time;
	}
	/**
	 * @return the seat
	 */
	public String getSeat() {
		return seat;
	}
	/**
	 * @param seat the seat to set
	 */
	public void setSeat(String seat) {
		this.seat = seat;
	}
	/**
	 * @return the price
	 */
	public Integer getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
	/**
	 * @return the amount
	 */
	public Integer getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
