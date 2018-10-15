package com.role.model;

import java.util.List;



public class RoleService {

	private RoleDAO_interface dao;

	public RoleService() {
		dao = new RoleDAO();
	}

	public void addRole(String br_name) {
		RoleVO roleVO = new RoleVO();
		roleVO.setBr_name(br_name);
		dao.insert(roleVO);
	}
	
	public RoleVO find(String br_name) {
		return dao.findnoByname(br_name);
	}
	
	
	public RoleVO findbyno(String br_no) {
		return dao.findnoByname(br_no);
	}
	
	
	public List<RoleVO> getall(){
		return dao.getAll();
	}

}
