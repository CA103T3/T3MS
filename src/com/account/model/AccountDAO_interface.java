package com.account.model;

import java.sql.Timestamp;
import java.util.*;

public interface AccountDAO_interface {
	public String insert(AccountVO account_BackstageVO);
	public void update(AccountVO account_BackstageVO);
	public void delete(String bs_acc_no);	
	public AccountVO findVO(String bs_acc_name);
	public List<AccountVO> getAll();
	public boolean login(String bs_acc_name,String bs_acc_psw);
	public void logintime(String bs_acc_name);
	public AccountVO findVObyno(String bs_acc_no);
	public void stop(String bs_acc_no);
	public void unstop(String bs_acc_no);
	
}
