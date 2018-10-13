package com.nav_backstage.model;

public class NavVO implements java.io.Serializable{
	private static final long serialVersionUID = 6775358354620817951L;
	
	private String listitem_no;
	private String listitem_name;
	private String url;
	private String parent_id;
	private Integer item_order;
	
	
	
	
	public String getListitem_no() {
		return listitem_no;
	}
	public void setListitem_no(String listitem_no) {
		this.listitem_no = listitem_no;
	}
	public String getListitem_name() {
		return listitem_name;
	}
	public void setListitem_name(String listitem_name) {
		this.listitem_name = listitem_name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public Integer getItem_order() {
		return item_order;
	}
	public void setItem_order(Integer item_order) {
		this.item_order = item_order;
	}

	
	
}
