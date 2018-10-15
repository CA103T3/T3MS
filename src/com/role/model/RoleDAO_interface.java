package com.role.model;

import java.util.List;



public interface RoleDAO_interface {
	public String insert(RoleVO roleVO);
	public void update(RoleVO roleVO);
	public void delete(String permission__no);	
	public RoleVO findByno(String br_no);
	public List<RoleVO> getAll();
	public RoleVO findnoByname(String br_name);
}
