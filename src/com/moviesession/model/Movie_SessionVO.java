package com.moviesession.model;

import java.io.Serializable;

public class Movie_SessionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String movie_session_no;
	private String theater_no;
	private String movie_no;
	private String seat_table;

	public String getMovie_session_no() {
		return movie_session_no;
	}

	public void setMovie_session_no(String movie_session_no) {
		this.movie_session_no = movie_session_no;
	}

	public String getTheater_no() {
		return theater_no;
	}

	public void setTheater_no(String theater_no) {
		this.theater_no = theater_no;
	}

	public String getMovie_no() {
		return movie_no;
	}

	public void setMovie_no(String movie_no) {
		this.movie_no = movie_no;
	}

	public String getSeat_table() {
		return seat_table;
	}

	public void setSeat_table(String seat_table) {
		this.seat_table = seat_table;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}