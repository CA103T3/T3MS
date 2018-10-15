package com.permission.model;

import java.util.List;



public class PermissionService {

	private PermissionDAO_interface dao;

	public PermissionService() {
		dao = new PermissionDAO();
	}

	public void add(String permission_no,String role_no) {
		PermissionVO pVO = new PermissionVO();
		pVO.setPermission_no(permission_no);
		pVO.setRole_no(role_no);
		dao.insert(pVO);

	}
	
	public List<String> getOnesP(String role_no){
		return dao.getOnesOwn(role_no);
	}
	public List<PermissionVO> getall(){
		return dao.getAll();
	}
	
	public PermissionVO findbypk(String rp_no) {
		return dao.findByPrimaryKey(rp_no);
	}

}
