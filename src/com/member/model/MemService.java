package com.member.model;



public class MemService {

	private MemDAO_interface dao;

	public MemService() {
		dao = new MemJNDIDAO();
	}
	
	//會員註冊
	public MemVO addmem(String email, String paw,String Lastname, String Firstname, String birthday, String phone, String IDNUM, Integer gender, String addr, Integer locno){
		MemVO memVO=new MemVO();
        
		memVO.setEmail(email);
		memVO.setPaw(paw);
		memVO.setLastname(Lastname);
		memVO.setFirstname(Firstname);
		memVO.setBirthday(birthday);
		memVO.setPhone(phone);
		memVO.setIDNUM(IDNUM);
		memVO.setGender(gender);
		memVO.setAddr(addr);
		memVO.setLocno(locno);
		
		
        dao.insert(memVO);
        
		return memVO;	
	}
	//檢查重複註冊
	public boolean checkduplicated(String email) {
		MemVO memVO=new MemVO();
		        
		memVO.setEmail(email);
		
		return dao.check(memVO);
		
	}
	//登入檢查
	public boolean allowuser(String email,String paw) {
	    if (dao.isuserlogin(email, paw))
	      return true;
	    else
	      return false;
	  }
	//登入後透過EMAIL找到用戶VO
	public MemVO getMemVO(String email) {
		return dao.findByemail(email);
	}
	//輸入認證信的認證碼後把狀態由0->1
	public void passemail(String email) {
		dao.passregistered(email);
	}
	
}
