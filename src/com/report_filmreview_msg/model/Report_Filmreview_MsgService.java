package com.report_filmreview_msg.model;


import java.util.List;
import java.util.Set;

import com.filmreview.model.FilmreviewVO;



public class Report_Filmreview_MsgService {
private Report_Filmreview_MsgDAO_interface dao;
	
	public Report_Filmreview_MsgService() {
		dao = new Report_Filmreview_MsgDAO();
	}
	
	public Report_Filmreview_MsgVO insert( String frm_no, String mem_no, String content) {
		Report_Filmreview_MsgVO report_filmreview_msgVO = new Report_Filmreview_MsgVO();
		
	
		report_filmreview_msgVO.setFrm_no(frm_no);
		report_filmreview_msgVO.setMem_no(mem_no);	
		report_filmreview_msgVO.setContent(content);

		
		dao.insert(report_filmreview_msgVO);
		
		return report_filmreview_msgVO;
	}; 
	public Report_Filmreview_MsgVO update( Integer state_no, String rfm_no) {
		Report_Filmreview_MsgVO report_filmreview_msgVO = new Report_Filmreview_MsgVO();
		
	
		report_filmreview_msgVO.setState(state_no);
		report_filmreview_msgVO.setRfm_no(rfm_no);	
		

		
		dao.insert(report_filmreview_msgVO);
		
		return report_filmreview_msgVO;
	}; 
	public void delete(String frm_no) {
		dao.delete(frm_no);
	}
	public List<Report_Filmreview_MsgVO> getAll(){
    	return dao.getAll();
    }
	public Report_Filmreview_MsgVO getAllByFrmNo(String frm_no){
    	return dao.getAllByFrmNo(frm_no);
    }
	public Report_Filmreview_MsgVO getAllByRfmNo(String rfm_no){
		return dao.getAllByRfmNo(rfm_no);
	}
}
