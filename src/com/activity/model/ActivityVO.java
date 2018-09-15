package com.activity.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ActivityVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String activity_no;
	private String activity_name;
	private String activity_desc;
	private String backstage_no;
	private Timestamp created_at;
	private Timestamp updated_at;
	private Integer active;
	private String img_path;
	private String activity_url;

	public String getActivity_no() {
		return activity_no;
	}

	public void setActivity_no(String activity_no) {
		this.activity_no = activity_no;
	}

	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}

	public String getActivity_desc() {
		return activity_desc;
	}

	public void setActivity_desc(String activity_desc) {
		this.activity_desc = activity_desc;
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

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	public String getActivity_url() {
		return activity_url;
	}

	public void setActivity_url(String activity_url) {
		this.activity_url = activity_url;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
