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
	private Integer locno;
	private String addr;
	private Integer status;
	private Integer type;
	private Byte memimg;
	private String extname;
	private Integer violation;
	private String introduction;
	
	
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
	public Byte getMeming() {
		return memimg;
	}
	public void setMemimg(Byte memimg) {
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
