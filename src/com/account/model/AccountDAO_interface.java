package com.account.model;

import java.util.*;

import com.account_backstage.model.Account_BackstageVO;
import com.role_permission_bs.model.Role_Permission_BsVO;


public interface AccountDAO_interface {
	public void insert(AccountVO account_BackstageVO);
	public void update(AccountVO account_BackstageVO);
	public void delete(String bs_acc_no);	
	public AccountVO findVO(String bs_acc_name);
	public List<AccountVO> getAll();
	public boolean login(String bs_acc_name,String bs_acc_psw);
}
