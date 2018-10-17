package com.member.model;

import java.util.*;

public interface MemDAO_interface {
	public void insert(MemVO memVO);

	public void update(MemVO memVO);

	public void delete(String memno);

	public MemVO findByemail(String email);

	public MemVO findBymemno(String memno);

	public List<MemVO> getAll();

	public boolean check(String email);

	public boolean checkID(String IDNUM);

	public boolean isuserlogin(String email, String paw);

	public void passregistered(String email);

	public boolean forgotcheck(String email, String idnum);

	public void changepassword(String paw, String email);

	public void banmember(String memno);

	public void unbanmember(String memno);

	public void wanttobeFC(String memno);

	public void betheFC(String memno);

	public String InsertReturnNO(MemVO memVO);

	public void foul(String memno);

	public Set<Mem_Ticket_SearchVO> mem_ticket_search(String memno);

}
