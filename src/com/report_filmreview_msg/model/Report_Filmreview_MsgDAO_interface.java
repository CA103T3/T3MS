package com.report_filmreview_msg.model;

import java.util.List;
import java.util.Set;

import com.filmreview.model.FilmreviewVO;

public interface Report_Filmreview_MsgDAO_interface {
	public void insert(Report_Filmreview_MsgVO report_filmreview_msgVO);
	public void update(Report_Filmreview_MsgVO report_filmreview_msgVO);
	public void delete(String rfm_no);
	public List<Report_Filmreview_MsgVO> getAll();
	public Report_Filmreview_MsgVO getAllByFrmNo(String frm_no);
	public Report_Filmreview_MsgVO getAllByRfmNo(String rfm_no);
	
}
