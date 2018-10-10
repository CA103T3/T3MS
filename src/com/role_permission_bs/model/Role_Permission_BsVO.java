package com.role_permission_bs.model;

public class Role_Permission_BsVO implements java.io.Serializable{
	private static final long serialVersionUID = 1677349311558107695L;
	private String permission_no;
	private String role_no;
	private String bs_acc_no;
	
	
	public String getPermission_no() {
		return permission_no;
	}
	public void setPermission_no(String permission_no) {
		this.permission_no = permission_no;
	}
	public String getRole_no() {
		return role_no;
	}
	public void setRole_no(String role_no) {
		this.role_no = role_no;
	}
	public String getBs_acc_no() {
		return bs_acc_no;
	}
	public void setBs_acc_no(String bs_acc_no) {
		this.bs_acc_no = bs_acc_no;
	}
	

}
