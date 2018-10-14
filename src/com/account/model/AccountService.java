package com.account.model;

import java.sql.Timestamp;
import java.util.List;

public class AccountService {
	
	private AccountDAO_interface dao;

	public AccountService() {
		dao = new AccountJNDIDAO();
	}

	public AccountVO addAccount_Backstage(String bs_acc_name, String role_no,
			String cinema_no, String bs_acc_psw,String email,String tel, java.sql.Timestamp last_online_time) {

		AccountVO accountVO = new AccountVO();

		accountVO.setBs_acc_name(bs_acc_name);
		accountVO.setRole_no(role_no);
		accountVO.setCinema_no(cinema_no);
		accountVO.setBs_acc_psw(bs_acc_psw);
		accountVO.setEmail(email);
		accountVO.setTel(tel);
		accountVO.setLast_online_time(new Timestamp(System.currentTimeMillis()));
		
		dao.insert(accountVO);

		return accountVO;
	}

	public AccountVO updateaccount_Backstage(String bs_acc_no,String bs_acc_name, String role_no, String cinema_no,String bs_acc_psw,String email,String tel,
			java.sql.Timestamp last_online_time,Integer state) {

		AccountVO accountVO = new AccountVO();

		accountVO.setBs_acc_no(bs_acc_no);
		accountVO.setBs_acc_name(bs_acc_name);
		accountVO.setRole_no(role_no);
		accountVO.setCinema_no(cinema_no);
		accountVO.setBs_acc_psw(bs_acc_psw);
		accountVO.setEmail(email);
		accountVO.setTel(tel);
		accountVO.setLast_online_time(new Timestamp(System.currentTimeMillis()));
		accountVO.setState(state);
		dao.update(accountVO);

		return accountVO;
	}

	public void deleteAccount_Backstage(String bs_acc_no) {
		dao.delete(bs_acc_no);
	}

	public AccountVO getOneAccount_Backstage(String bs_acc_no) {
		return dao.findByPrimaryKey(bs_acc_no);
	}

	public List<AccountVO> getAll() {
		System.out.println("OK");
		return dao.getAll();
	}

}
