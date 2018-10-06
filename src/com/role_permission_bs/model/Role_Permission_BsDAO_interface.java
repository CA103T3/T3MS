package com.role_permission_bs.model;

import java.util.List;



public interface Role_Permission_BsDAO_interface {
	public void insert(Role_Permission_BsVO role_Permission_bsVO);
	public void update(Role_Permission_BsVO role_Permission_bsVO);
	public void delete(String permission__no);	
	public Role_Permission_BsVO findByPrimaryKey(String permission_no);
	public List<Role_Permission_BsVO> getAll();

}
