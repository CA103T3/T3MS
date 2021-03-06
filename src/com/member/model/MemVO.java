package com.member.model;

import java.util.Date;


import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class MemVO implements java.io.Serializable,HttpSessionBindingListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -626082207712694224L;
	private String memno;
	private String email;
	private String paw;
	private String lastname;
	private String firstname;
	private String birthday;
	private String phone;
	private String IDNUM;
	private Integer gender;
	private Integer locno;
	private String addr;
	private Integer status;
	private Integer type;
	private byte[] memimg;
	private String extname;
	private Integer violation;
	private String introduction;
	
	
	
	//登入登出監聽器
	public void valueBound(HttpSessionBindingEvent event) {
	    System.out.println(("[" + new Date() + "-會員登入] BOUND as " + event.getName()  +" "+ this.firstname + "已登入 to " + event.getSession().getId()));
	  }
	  
	  public void valueUnbound(HttpSessionBindingEvent event) {
	    System.out.println(("[" + new Date() + "-會員登出] UNBOUND as " + event.getName() +" "+ this.firstname + "已登出 from " + event.getSession().getId()));
	  }
	
	
	
	
	
	
	
	public String getmemno() {
		return memno;
	}
	public void setmemno(String memno) {
		this.memno = memno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPaw() {
		return paw;
	}
	public void setPaw(String paw) {
		this.paw = paw;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIDNUM() {
		return IDNUM;
	}
	public void setIDNUM(String iDNUM) {
		IDNUM = iDNUM;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getMemno() {
		return memno;
	}
	public void setMemno(String memno) {
		this.memno = memno;
	}
	public Integer getLocno() {
		return locno;
	}
	public void setLocno(Integer locno) {
		this.locno = locno;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public byte[] getMemimg() {
		return memimg;
	}
	public void setMemimg(byte[] memimg) {
		this.memimg = memimg;
	}
	public String getExtname() {
		return extname;
	}
	public void setExtname(String extname) {
		this.extname = extname;
	}
	public Integer getViolation() {
		return violation;
	}
	public void setViolation(Integer violation) {
		this.violation = violation;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
}
