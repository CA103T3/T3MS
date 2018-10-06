package com.account_backstage.model;

import java.sql.Timestamp;
import java.util.List;

public class Account_BackstageService {
	
	private Account_BackstageDAO_interface dao;

	public Account_BackstageService() {
		dao = new Account_BackstageDAO();
	}

	public Account_BackstageVO addAccount_Backstage(String bs_acc_name, String role_no,
			String cinema_no, String bs_acc_psw,String email,String tel, java.sql.Timestamp last_online_time,Integer state) {

		Account_BackstageVO account_BackstageVO = new Account_BackstageVO();

		account_BackstageVO.setBs_acc_name(bs_acc_name);
//		account_BackstageVO.setBs_acc_psw(bs_acc_psw);
		account_BackstageVO.setRole_no(role_no);
//		account_BackstageVO.setLast_online_time(new Timestamp(System.currentTimeMillis()));
		account_BackstageVO.setCinema_no(cinema_no);
		account_BackstageVO.setBs_acc_psw(bs_acc_psw);
		account_BackstageVO.setEmail(email);
		account_BackstageVO.setTel(tel);
		account_BackstageVO.setLast_online_time(new Timestamp(System.currentTimeMillis()));
		account_BackstageVO.setState(state);
		dao.insert(account_BackstageVO);

		return account_BackstageVO;
	}

	public Account_BackstageVO updateaccount_Backstage(String bs_acc_no,String bs_acc_name, String role_no, String cinema_no,String bs_acc_psw,String email,String tel,
			java.sql.Timestamp last_online_time,Integer state) {

		Account_BackstageVO account_BackstageVO = new Account_BackstageVO();

		account_BackstageVO.setBs_acc_no(bs_acc_no);
		account_BackstageVO.setBs_acc_name(bs_acc_name);
		account_BackstageVO.setRole_no(role_no);
		account_BackstageVO.setCinema_no(cinema_no);
		account_BackstageVO.setBs_acc_psw(bs_acc_psw);
		account_BackstageVO.setEmail(email);
		account_BackstageVO.setTel(tel);
		account_BackstageVO.setLast_online_time(new Timestamp(System.currentTimeMillis()));
		account_BackstageVO.setState(state);
		dao.update(account_BackstageVO);

		return account_BackstageVO;
	}

	public void deleteAccount_Backstage(String bs_acc_no) {
		dao.delete(bs_acc_no);
	}

	public Account_BackstageVO getOneAccount_Backstage(String bs_acc_no) {
		return dao.findByPrimaryKey(bs_acc_no);
	}

	public List<Account_BackstageVO> getAll() {
		System.out.println("OK");
		return dao.getAll();
	}

}
