package com.filmreview_msg.model;

import java.sql.Date;

public class Filmreview_MsgVO {

	private String frm_no ;
	private String fr_no;
	private String mem_no;
	private Date created_at;
	private Date updated_at;
	private String content;
	
	public String getFrm_no() {
		return frm_no;
	}
	public void setFrm_no(String frm_no) {
		this.frm_no = frm_no;
	}
	public String getFr_no() {
		return fr_no;
	}
	public void setFr_no(String fr_no) {
		this.fr_no = fr_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

}
