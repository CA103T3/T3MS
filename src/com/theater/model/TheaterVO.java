package com.theater.model;

import java.io.Reader;

public class TheaterVO implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 721089029823905838L;
    private String theater_no;
    private String cinema_no;
    private String theater_name;
    private Integer t_rows;
    private Integer t_columns;
    private String seat_model;
    private Integer seats;
    private String equipment;
    
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
    public String getTheater_name() {
        return theater_name;
    }
    public void setTheater_name(String theater_name) {
        this.theater_name = theater_name;
    }
    public Integer getT_rows() {
        return t_rows;
    }
    public void setT_rows(Integer t_rows) {
        this.t_rows = t_rows;
    }
    public Integer getT_columns() {
        return t_columns;
    }
    public void setT_columns(Integer t_columns) {
        this.t_columns = t_columns;
    }
    public String getSeat_model() {
        return seat_model;
    }
    public void setSeat_model(String seat_model) {
        this.seat_model = seat_model;
    }
    public Integer getSeats() {
        return seats;
    }
    public void setSeats(Integer seats) {
        this.seats = seats;
    }
    public String getEquipment() {
        return equipment;
    }
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
    
}
