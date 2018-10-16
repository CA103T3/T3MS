package com.movie.model;

import java.sql.Timestamp;

public class Movie_Session_Temp_VO {

	private String session_no;
	private String theater_no;
	private String cinema_no;
	private String movie_no;
	private String cinema_name;
	private String equipment;
	private Timestamp session_time;

	public String getSession_no() {
		return session_no;
	}

	public void setSession_no(String session_no) {
		this.session_no = session_no;
	}

	public String getTheater_no() {
		return theater_no;
	}

	public void setTheater_no(String theater_no) {
		this.theater_no = theater_no;
	}

	public String getCinema_no() {
		return cinema_no;
	}

	public void setCinema_no(String cinema_no) {
		this.cinema_no = cinema_no;
	}

	public String getMovie_no() {
		return movie_no;
	}

	public void setMovie_no(String movie_no) {
		this.movie_no = movie_no;
	}

	public String getCinema_name() {
		return cinema_name;
	}

	public void setCinema_name(String cinema_name) {
		this.cinema_name = cinema_name;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public Timestamp getSession_time() {
		return session_time;
	}

	public void setSession_time(Timestamp session_time) {
		this.session_time = session_time;
	}

}
