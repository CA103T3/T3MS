package com.backstage_role.model;

import java.sql.Timestamp;
import java.util.List;

public class Backstage_RoleService {
	
	private Backstage_RoleDAO_interface dao;

	public Backstage_RoleService() {
		dao = new Backstage_RoleDAO();
	}

	public Backstage_RoleVO addBackstage_Role(String br_name) {

		Backstage_RoleVO backstage_RoleVO = new Backstage_RoleVO();

		backstage_RoleVO.setBr_name(br_name);
		
		dao.insert(backstage_RoleVO);

		return backstage_RoleVO;
	}

	public Backstage_RoleVO updBackstage_Role(String br_no,String br_name) {

		Backstage_RoleVO backstage_RoleVO = new Backstage_RoleVO();

		backstage_RoleVO.setBr_no(br_no);
		backstage_RoleVO.setBr_name(br_name);
//		account_BackstageVO.setRole_no(role_no);
//		account_BackstageVO.setCinema_no(cinema_no);
//		account_BackstageVO.setBs_acc_psw(bs_acc_psw);
//		account_BackstageVO.setEmail(email);
//		account_BackstageVO.setTel(tel);
//		account_BackstageVO.setLast_online_time(new Timestamp(System.currentTimeMillis()));
//		account_BackstageVO.setState(state);
		dao.update(backstage_RoleVO);

		return backstage_RoleVO;
	}

	public void Backstage_RoleVO(String br_no) {
		dao.delete(br_no);
	}

	public Backstage_RoleVO getOneAccount_BackstageVO(String br_no) {
		return dao.findByPrimaryKey(br_no);
	}

	public List<Backstage_RoleVO> getAll() {
		System.out.println("OK");
		return dao.getAll();
	}
}
