package com.permission.model;

import java.util.List;



public class PermissionService {

	private PermissionDAO_interface dao;

	public PermissionService() {
		dao = new PermissionDAO();
	}

	public PermissionVO add(String permission_no,String role_no) {
		PermissionVO pVO = new PermissionVO();
		pVO.setPermission_no(permission_no);
		pVO.setRole_no(role_no);
		dao.insert(pVO);

		return pVO;
	}

}
