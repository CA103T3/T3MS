package com.backstage_role.model;

import java.util.*;


public interface Backstage_RoleDAO_interface {
	public void insert(Backstage_RoleVO backstage_RoleVO);
	public void update(Backstage_RoleVO backstage_RoleVO);
	public void delete(String br_no);	
	public Backstage_RoleVO findByPrimaryKey(String br_no);
	public List<Backstage_RoleVO> getAll();

}
