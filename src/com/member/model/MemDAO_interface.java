package com.member.model;
import java.util.*;


public interface MemDAO_interface {
	 public void insert(MemVO memVO);
     public void update(MemVO memVO);
     public void delete(String memno);
     public MemVO findByemail(String email);
     public List<MemVO> getAll();
     public boolean check(MemVO memVO);
     public boolean isuserlogin(String email,String paw);
     public void passregistered(String email);
     public boolean forgotcheck(String email, String idnum);
     public void changepassword(String paw, String email);
     //萬用複合查詢(傳入參數型態Map)(回傳 List)
//   public List<MemVO> getAll(Map<String, String[]> map); 
	
	
}
