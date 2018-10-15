package com.nav_backstage.model;

import java.util.List;

import com.member.model.MemVO;

public class NavService {
	
	private NavDAO_interface dao;
	
	public NavService(){
		dao = new NavDAO(); 
	}

	public NavVO insert(String listitem_name,String url,String parent_id,int item_order) {
		NavVO navVO = new NavVO();
		navVO.setListitem_name(listitem_name);
		navVO.setUrl(url);
		navVO.setParent_id(parent_id);
		navVO.setItem_order(item_order);
		dao.insert(navVO);
		return navVO;
	}
	
	public List<NavVO> getall(){
		return dao.getAll();
	}
	
	public NavVO findbyurl(String url) {
		return dao.findByURL(url);
	}
	
}
