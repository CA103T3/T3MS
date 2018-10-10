package com.role_permission_bs.model;

import java.sql.Timestamp;
import java.util.List;



public class Role_Permission_BsService {
	private Role_Permission_BsDAO_interface dao;

	public Role_Permission_BsService() {
		dao = new Role_Permission_BsDAO();
	}

	public Role_Permission_BsVO addRole_Permission_Bs(String role_no) {

		Role_Permission_BsVO role_Permission_BsVO = new Role_Permission_BsVO();

//		role_Permission_BsVO.setPermission_no(permission_no);
//		account_BackstageVO.setBs_acc_psw(bs_acc_psw);
		role_Permission_BsVO.setRole_no(role_no);
//		account_BackstageVO.setLast_online_time(new Timestamp(System.currentTimeMillis()));
//		account_BackstageVO.setCinema_no(cinema_no);
//		account_BackstageVO.setBs_acc_psw(bs_acc_psw);
//		account_BackstageVO.setEmail(email);
//		account_BackstageVO.setTel(tel);
//		account_BackstageVO.setLast_online_time(new Timestamp(System.currentTimeMillis()));
//		account_BackstageVO.setState(state);
		dao.insert(role_Permission_BsVO);

		return role_Permission_BsVO;
	}

	public Role_Permission_BsVO updateRole_Permission_Bs(String permission_no,String role_no) {

		Role_Permission_BsVO role_Permission_BsVO = new Role_Permission_BsVO();

		role_Permission_BsVO.setPermission_no(permission_no);
		role_Permission_BsVO.setRole_no(role_no);
//		account_BackstageVO.setRole_no(role_no);
//		account_BackstageVO.setCinema_no(cinema_no);
//		account_BackstageVO.setBs_acc_psw(bs_acc_psw);
//		account_BackstageVO.setEmail(email);
//		account_BackstageVO.setTel(tel);
//		account_BackstageVO.setLast_online_time(new Timestamp(System.currentTimeMillis()));
//		account_BackstageVO.setState(state);
		dao.update(role_Permission_BsVO);

		return role_Permission_BsVO;
	}

	public void deleteRole_Permission_Bs(String permission_no) {
		dao.delete(permission_no);
	}

	public Role_Permission_BsVO getOneRole_Permission_Bs(String permission_no) {
		return dao.findByPrimaryKey(permission_no);
	}

	public List<Role_Permission_BsVO> getAll() {
		System.out.println("OK");
		return dao.getAll();
	}
	public List<Role_Permission_BsVO> getAll2() {
		System.out.println("OK2");
		return dao.getAll2();
	}

}
