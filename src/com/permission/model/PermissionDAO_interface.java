package com.permission.model;

import java.util.List;



public interface PermissionDAO_interface {
	public String insert(PermissionVO pVO);
	public void update(PermissionVO pVO);
	public void delete(String permission_no);	
	public PermissionVO findByPrimaryKey(String rp_no);
	public List<PermissionVO> getAll();
	public List<String> getOnesOwn(String role_no);
    
}
