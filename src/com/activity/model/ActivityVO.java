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
	private byte[] img_path;
	private String activity_url;
	/**
	 * @return the activity_no
	 */
	public String getActivity_no() {
		return activity_no;
	}
	/**
	 * @param activity_no the activity_no to set
	 */
	public void setActivity_no(String activity_no) {
		this.activity_no = activity_no;
	}
	/**
	 * @return the activity_name
	 */
	public String getActivity_name() {
		return activity_name;
	}
	/**
	 * @param activity_name the activity_name to set
	 */
	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}
	/**
	 * @return the activity_desc
	 */
	public String getActivity_desc() {
		return activity_desc;
	}
	/**
	 * @param activity_desc the activity_desc to set
	 */
	public void setActivity_desc(String activity_desc) {
		this.activity_desc = activity_desc;
	}
	/**
	 * @return the backstage_no
	 */
	public String getBackstage_no() {
		return backstage_no;
	}
	/**
	 * @param backstage_no the backstage_no to set
	 */
	public void setBackstage_no(String backstage_no) {
		this.backstage_no = backstage_no;
	}
	/**
	 * @return the created_at
	 */
	public Timestamp getCreated_at() {
		return created_at;
	}
	/**
	 * @param created_at the created_at to set
	 */
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	/**
	 * @return the updated_at
	 */
	public Timestamp getUpdated_at() {
		return updated_at;
	}
	/**
	 * @param updated_at the updated_at to set
	 */
	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	/**
	 * @return the active
	 */
	public Integer getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(Integer active) {
		this.active = active;
	}
	/**
	 * @return the img_path
	 */
	public byte[] getImg_path() {
		return img_path;
	}
	/**
	 * @param img_path the img_path to set
	 */
	public void setImg_path(byte[] img_path) {
		this.img_path = img_path;
	}
	/**
	 * @return the activity_url
	 */
	public String getActivity_url() {
		return activity_url;
	}
	/**
	 * @param activity_url the activity_url to set
	 */
	public void setActivity_url(String activity_url) {
		this.activity_url = activity_url;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
