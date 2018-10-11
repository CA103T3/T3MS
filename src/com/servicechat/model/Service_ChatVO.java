package com.servicechat.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Service_ChatVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String chat_id;
	private String backstage_no;
	private String mem_no;
	private Timestamp created_at;
	private Integer owner;
	private String content;

	public String getChat_id() {
		return chat_id;
	}

	public void setChat_id(String chat_id) {
		this.chat_id = chat_id;
	}

	public String getBackstage_no() {
		return backstage_no;
	}

	public void setBackstage_no(String backstage_no) {
		this.backstage_no = backstage_no;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Integer getOwner() {
		return owner;
	}

	public void setOwner(Integer owner) {
		this.owner = owner;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
