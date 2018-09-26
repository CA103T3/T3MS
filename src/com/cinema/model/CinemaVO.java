package com.cinema.model;

public class CinemaVO implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1677349311558107695L;
    private String cinema_no;
    private String cinema_name;
    private String cinema_engname;
    private String cinema_address;
    private String cinema_tel;
    private String introduction;
    private String traffic;
    private String photo_title;
    private String photo_path;
    private String photo_small;
    private Integer active;
    private Integer state;

    public String getCinema_no() {
        return cinema_no;
    }
    public void setCinema_no(String cinema_no) {
        this.cinema_no = cinema_no;
    }
    public String getCinema_name() {
        return cinema_name;
    }
    public void setCinema_name(String cinema_name) {
        this.cinema_name = cinema_name;
    }
    public String getCinema_engname() {
        return cinema_engname;
    }
    public void setCinema_engname(String cinema_engname) {
        this.cinema_engname = cinema_engname;
    }
    public String getCinema_address() {
        return cinema_address;
    }
    public void setCinema_address(String cinema_address) {
        this.cinema_address = cinema_address;
    }
    public String getCinema_tel() {
        return cinema_tel;
    }
    public void setCinema_tel(String cinema_tel) {
        this.cinema_tel = cinema_tel;
    }
    public String getIntroduction() {
        return introduction;
    }
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public String getTraffic() {
        return traffic;
    }
    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }
    public String getPhoto_title() {
        return photo_title;
    }
    public void setPhoto_title(String photo_title) {
        this.photo_title = photo_title;
    }
    public String getPhoto_path() {
        return photo_path;
    }
    public void setPhoto_path(String photo_path) {
        this.photo_path = photo_path;
    }
    public String getPhoto_small() {
        return photo_small;
    }
    public void setPhoto_small(String photo_small) {
        this.photo_small = photo_small;
    }
    public Integer getActive() {
        return active;
    }
    public void setActive(Integer active) {
        this.active = active;
    }
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }

    
    
}
