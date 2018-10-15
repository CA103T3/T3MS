package com.nav_backstage.model;

import java.util.List;

import com.member.model.MemVO;

public interface NavDAO_interface {
	
	 public String insert(NavVO navVO);
     public void update(NavVO navVO);
     public void delete(String listitem_no);
     public NavVO findByNO(String listitem_no);
     public List<NavVO> getAll();
     public NavVO findByURL(String url);
	

}
