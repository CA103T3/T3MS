package com.account_backstage.model;

import java.util.*;

import com.account_backstage.model.Account_BackstageVO;
import com.role_permission_bs.model.Role_Permission_BsVO;


public interface Account_BackstageDAO_interface {
	public void insert(Account_BackstageVO account_BackstageVO);
	public void update(Account_BackstageVO account_BackstageVO);
	public void delete(String bs_acc_no);	
	public Account_BackstageVO findByPrimaryKey(String bs_acc_no);
	public List<Account_BackstageVO> getAll();
	
	 //同時新增部門與員工 (實務上並不常用, 但,可用在訂單主檔與明細檔一次新增成功)
    public void insertWithRole_Permission_Bs(Account_BackstageVO account_BackstageVO , List<Role_Permission_BsVO> list);
}
