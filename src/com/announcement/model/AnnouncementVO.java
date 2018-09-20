package com.announcement.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AnnouncementVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String anc_no;
	private String anc_con;
	private String backstage_no;
	private Timestamp created_at;
	private Timestamp updated_at;
	private Integer active;

	public String getAnc_no() {
		return anc_no;
	}

	public void setAnc_no(String anc_no) {
		this.anc_no = anc_no;
	}

	public String getAnc_con() {
		return anc_con;
	}

	public void setAnc_con(String anc_con) {
		this.anc_con = anc_con;
	}

	public String getBackstage_no() {
		return backstage_no;
	}

	public void setBackstage_no(String backstage_no) {
		this.backstage_no = backstage_no;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
