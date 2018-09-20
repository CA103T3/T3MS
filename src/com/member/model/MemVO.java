package com.member.model;

public class MemVO implements java.io.Serializable{

	private String memno;
	private String email;
	private String paw;
	private String lastname;
	private String firstname;
	private String birthday;
	private String phone;
	private String IDNUM;
	private Integer gender;
	
	
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
	
}
