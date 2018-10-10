package com.account_backstage.model;

import java.sql.Date;

import java.sql.Timestamp;

public class Account_BackstageVO implements java.io.Serializable{
	private static final long serialVersionUID = 1677349311558107695L;
	private String bs_acc_no;
	private String bs_acc_name;
	private String role_no;
	private String cinema_no;
	private String bs_acc_psw;
	private String email;
	private String tel;
	private Timestamp last_online_time;
	private Integer state;
	
	public Account_BackstageVO() {
		super();
	
	}

	public String getBs_acc_no() {
		return bs_acc_no;
	}

	public void setBs_acc_no(String bs_acc_no) {
		this.bs_acc_no = bs_acc_no;
	}

	public String getBs_acc_name() {
		return bs_acc_name;
	}

	public void setBs_acc_name(String bs_acc_name) {
		this.bs_acc_name = bs_acc_name;
	}

	public String getRole_no() {
		return role_no;
	}

	public void setRole_no(String role_no) {
		this.role_no = role_no;
	}

	public String getCinema_no() {
		return cinema_no;
	}

	public void setCinema_no(String cinema_no) {
		this.cinema_no = cinema_no;
	}

	public String getBs_acc_psw() {
		return bs_acc_psw;
	}

	public void setBs_acc_psw(String bs_acc_psw) {
		this.bs_acc_psw = bs_acc_psw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Timestamp getLast_online_time() {
		return last_online_time;
	}

	public void setLast_online_time(Timestamp last_online_time) {
		this.last_online_time = last_online_time;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	
	
	

}
