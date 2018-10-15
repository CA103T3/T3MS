package com.role.model;

import java.util.List;



public class RoleService {

	private RoleDAO_interface dao;

	public RoleService() {
		dao = new RoleDAO();
	}

	public RoleVO addRole(String br_name) {
		RoleVO roleVO = new RoleVO();
		roleVO.setBr_name(br_name);
		dao.insert(roleVO);

		return roleVO;
	}
	
	public RoleVO find(String br_name) {
		return dao.findnoByname(br_name);
	}
	
	public List<RoleVO> getall(){
		return dao.getAll();
	}

}
