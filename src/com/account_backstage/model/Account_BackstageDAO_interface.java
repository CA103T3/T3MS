package com.account_backstage.model;

import java.util.*;

import com.account_backstage.model.Account_BackstageVO;


public interface Account_BackstageDAO_interface {
	public void insert(Account_BackstageVO account_BackstageVO);
	public void update(Account_BackstageVO account_BackstageVO);
	public void delete(String bs_acc_no);	
	public Account_BackstageVO findByPrimaryKey(String bs_acc_no);
	public List<Account_BackstageVO> getAll();
}
