package com.account.model;

import java.sql.Timestamp;
import java.util.List;

public class AccountService {
	
	private AccountDAO_interface dao;

	public AccountService() {
		dao = new AccountJNDIDAO();
	}

	public String addAccount_Backstage(String bs_acc_name, String role_no,
			String cinema_no, String bs_acc_psw,String email,String tel) {

		AccountVO accountVO = new AccountVO();

		accountVO.setBs_acc_name(bs_acc_name);
		accountVO.setRole_no(role_no);
		accountVO.setCinema_no(cinema_no);
		accountVO.setBs_acc_psw(bs_acc_psw);
		accountVO.setEmail(email);
		accountVO.setTel(tel);

		return dao.insert(accountVO);
	}

	public void update(String bs_acc_no,String bs_acc_name,String bs_acc_psw,String email,String tel) {

		AccountVO accountVO = new AccountVO();

		accountVO.setBs_acc_no(bs_acc_no);
		accountVO.setBs_acc_name(bs_acc_name);
		accountVO.setBs_acc_psw(bs_acc_psw);
		accountVO.setEmail(email);
		accountVO.setTel(tel);
		dao.update(accountVO);
		
	}

	public void deleteAccount_Backstage(String bs_acc_no) {
		dao.delete(bs_acc_no);
	}

	public AccountVO getVO(String bs_acc_name) {
		return dao.findVO(bs_acc_name);
	}
	
	public AccountVO findVObyno(String bs_acc_no) {
		return dao.findVObyno(bs_acc_no);
	}

	public List<AccountVO> getAll() {
		return dao.getAll();
	}
	
	public boolean login(String bs_acc_name,String bs_acc_psw) {
		return dao.login(bs_acc_name, bs_acc_psw);
	}
	public void logintime(String bs_acc_name) {
		dao.logintime(bs_acc_name);
	}
	public void stop(String bs_acc_no) {
		dao.stop(bs_acc_no);
	}
	public void unstop(String bs_acc_no) {
		dao.unstop(bs_acc_no);
	}
	
	
	
}
