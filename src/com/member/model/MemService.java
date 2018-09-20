package com.member.model;



public class MemService {

	private MemDAO_interface dao;

	public MemService() {
		dao = new MemJDBCDAO();
	}
	
	//會員註冊
	public MemVO addmem(String email, String paw,String Lastname, String Firstname, String birthday, String phone, String IDNUM, Integer gender){
		MemVO memVO=new MemVO();
        
		memVO.setEmail(email);
		memVO.setPaw(paw);
		memVO.setLastname(Lastname);
		memVO.setFirstname(Firstname);
		memVO.setBirthday(birthday);
		memVO.setPhone(phone);
		memVO.setIDNUM(IDNUM);
		memVO.setGender(gender);
        dao.insert(memVO);
        
		return memVO;	
	}
	//檢查重複註冊
	public boolean checkduplicated(String email) {
		MemVO memVO=new MemVO();
		        
		memVO.setEmail(email);
		
		return dao.check(memVO);
		
	}
	//登入
	public boolean allowuser(String email,String paw) {
	    if (dao.isuserlogin(email, paw))
	      return true;
	    else
	      return false;
	  }
	
	public MemVO getMemVO(String email) {
		
		return dao.findByemail(email);
	}
	
}
