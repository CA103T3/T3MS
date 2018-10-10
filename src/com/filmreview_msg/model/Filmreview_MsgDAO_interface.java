package com.filmreview_msg.model;

import java.util.List;
import java.util.Set;

import com.filmreview.model.FilmreviewVO;

public interface Filmreview_MsgDAO_interface {
	public void insert(Filmreview_MsgVO filmreview_msgVO);
	public List<Filmreview_MsgVO> getAll();
	public void delete(String frm_no);
	public Set<Filmreview_MsgVO> getAllByFrNo(String fr_no);
	public Filmreview_MsgVO getVO(String fr_no);
}
