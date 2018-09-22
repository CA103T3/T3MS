package com.session.model;

import java.sql.Timestamp;

public class SessionVO implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6332296927066906614L;
    private String session_no;
    private String theater_no;
    private String movie_no;
    private Timestamp session_time;
    private String seat_table;

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
    public String getMovie_no() {
        return movie_no;
    }
    public void setMovie_no(String movie_no) {
        this.movie_no = movie_no;
    }
    public Timestamp getSession_time() {
        return session_time;
    }
    public void setSession_time(Timestamp session_time) {
        this.session_time = session_time;
    }
    public String getSeat_table() {
        return seat_table;
    }
    public void setSeat_table(String seat_table) {
        this.seat_table = seat_table;
    }

}
