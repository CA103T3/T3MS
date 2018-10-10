package com.filmreview_msg.model;


import java.util.List;
import java.util.Set;

import com.filmreview.model.FilmreviewVO;



public class Filmreview_MsgService {
private Filmreview_MsgDAO_interface dao;
	
	public Filmreview_MsgService() {
		dao = new Filmreview_MsgDAO();
	}
	
	public Filmreview_MsgVO insertFilmrevew_Msg( String fr_no, String mem_no, String content) {
		Filmreview_MsgVO filmreview_msgVO = new Filmreview_MsgVO();
		
	
		filmreview_msgVO.setFr_no(fr_no);
		filmreview_msgVO.setMem_no(mem_no);	
		filmreview_msgVO.setContent(content);

		
		dao.insert(filmreview_msgVO);
		
		return filmreview_msgVO;
	}; 
	public void delete(String frm_no) {
		dao.delete(frm_no);
	}
	public List<Filmreview_MsgVO> getAll(){
    	return dao.getAll();
    }
	public Set<Filmreview_MsgVO> getAllByFrNo(String fr_no){
    	return dao.getAllByFrNo(fr_no);
    }
	public Filmreview_MsgVO getVO(String fr_no) {
		return dao.getVO(fr_no);
	}
}
