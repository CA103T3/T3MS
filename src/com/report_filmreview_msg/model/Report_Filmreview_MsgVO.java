package com.report_filmreview_msg.model;

import java.sql.Date;

public class Report_Filmreview_MsgVO {

	private String rfm_no ;
	private String frm_no;
	private String mem_no;
	private Integer state;
	private String content;
	public String getRfm_no() {
		return rfm_no;
	}
	public void setRfm_no(String rfm_no) {
		this.rfm_no = rfm_no;
	}
	public String getFrm_no() {
		return frm_no;
	}
	public void setFrm_no(String frm_no) {
		this.frm_no = frm_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	

}
