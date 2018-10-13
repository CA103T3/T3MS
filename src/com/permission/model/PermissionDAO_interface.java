package com.permission.model;

import java.util.List;



public interface PermissionDAO_interface {
	public void insert(PermissionVO pVO);
	public void update(PermissionVO pVO);
	public void delete(String permission_no);	
	public PermissionVO findByPrimaryKey(String permission_no);
	public List<PermissionVO> getAll();
    
}
