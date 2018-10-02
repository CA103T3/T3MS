package com.ticketType.model;

import com.theater.model.TheaterVO;

public class TypeVO implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6461056948372828442L;
    private String type_no;
    private String theater_no;
    private String identify;
    //private String equipment;
    private String time;
    private Integer price;
    private TheaterVO theaterVO;

    public TheaterVO getTheaterVO() {
        return theaterVO;
    }

    public void setTheaterVO(TheaterVO theaterVO) {
        this.theaterVO = theaterVO;
    }

    public String getType_no() {
        return type_no;
    }
    public void setType_no(String type_no) {
        this.type_no = type_no;
    }
    public String getTheater_no() {
        return theater_no;
    }
    public void setTheater_no(String theater_no) {
        this.theater_no = theater_no;
    }
    public String getIdentify() {
        return identify;
    }
    public void setIdentify(String identify) {
        this.identify = identify;
    }
    // public String getEquipment() {
        // return equipment;
    // }
    // public void setEquipment(String equipment) {
        // this.equipment = equipment;
    // }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
}
