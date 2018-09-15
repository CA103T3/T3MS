package com.privatechat.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Private_ChatVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String private_chat_no;
	private String trans_mem_no;
	private String receiver_mem_no;
	private Timestamp created_at;
	private String content;

	public String getPrivate_chat_no() {
		return private_chat_no;
	}

	public void setPrivate_chat_no(String private_chat_no) {
		this.private_chat_no = private_chat_no;
	}

	public String getTrans_mem_no() {
		return trans_mem_no;
	}

	public void setTrans_mem_no(String trans_mem_no) {
		this.trans_mem_no = trans_mem_no;
	}

	public String getReceiver_mem_no() {
		return receiver_mem_no;
	}

	public void setReceiver_mem_no(String receiver_mem_no) {
		this.receiver_mem_no = receiver_mem_no;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
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
