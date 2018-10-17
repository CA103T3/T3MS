package com.member.model;

import java.util.List;
import java.util.Set;

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
		return dao.check(email);	
	}
	public boolean checkdupID(String IDNUM) {
		return dao.checkID(IDNUM);	
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
	
	//編號找VO
	public MemVO getMemVOByNO(String memno) {
		return dao.findBymemno(memno);
	}
	
	
	//輸入認證信的認證碼後把狀態由0->1
	public void passemail(String email) {
		dao.passregistered(email);
	}
	
	//修改會員資料
	public MemVO updatemem(String lname,String fname,String phone, String addr, Integer locno, byte[] memimg,String memno,String idnum,Integer gender,Integer status,Integer type,String extname,String birthday,Integer violation,String introduction, String email) {
		MemVO memVO = new MemVO();
		memVO.setLastname(lname);
		memVO.setFirstname(fname);
		memVO.setPhone(phone);
		memVO.setAddr(addr);
		memVO.setLocno(locno);
		memVO.setMemimg(memimg);
		memVO.setEmail(email);

		memVO.setmemno(memno);
		memVO.setIDNUM(idnum);
		memVO.setGender(gender);
		memVO.setStatus(status);
		memVO.setType(type);
		memVO.setExtname(extname);
		memVO.setBirthday(birthday);
		memVO.setViolation(violation);
		memVO.setIntroduction(introduction);
		dao.update(memVO);
		return memVO;
	}
	
	//忘記密碼的傻屌
		public boolean forgotpaw(String email,String idnum) {
		    if (dao.forgotcheck(email, idnum))
		      return true;
		    else
		      return false;
		  }
	//修改密碼
		public void changepaw(String paw, String email) {
			dao.changepassword(paw, email);
		}
		
	//抓全部
		public List<MemVO> getall(){
			return dao.getAll();
		}
		
	//後臺封解鎖
		public void ban(String memno) {
			dao.banmember(memno);
		}
		public void unban(String memno) {
			dao.unbanmember(memno);
		}
	//送出影評審核申請&成為影評
		public void wanttobeFC(String memno) {
			dao.wanttobeFC(memno);
		}
		public void becomeFC(String memno) {
			dao.betheFC(memno);
		}
	//違規次數+1
		public void foul(String memno) {
			dao.foul(memno);
		}
		
		public Set<Mem_Ticket_SearchVO> mem_ticket_search(String memno){
			return dao.mem_ticket_search(memno);
		}
		
}
