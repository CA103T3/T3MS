package com.role_permission_bs.model;

import java.util.List;



public interface Role_Permission_BsDAO_interface {
	public void insert(Role_Permission_BsVO role_Permission_bsVO);
	public void update(Role_Permission_BsVO role_Permission_bsVO);
	public void delete(String permission__no);	
	public Role_Permission_BsVO findByPrimaryKey(String permission_no);
	public List<Role_Permission_BsVO> getAll();
	public List<Role_Permission_BsVO> getAll2();
	
	//同時新增部門與員工 (實務上並不常用, 但,可用在訂單主檔與明細檔一次新增成功)
    public void insert2 (Role_Permission_BsVO role_Permission_bsVO , java.sql.Connection con);
    
}
