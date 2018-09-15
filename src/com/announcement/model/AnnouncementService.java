package com.announcement.model;

import java.sql.Timestamp;
import java.util.List;

import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion.Setting;

public class AnnouncementService {

	private AnnouncementDAO_interface dao;

	public AnnouncementService() {
		dao = new AnnouncementJNDIDAO();
	}

	public AnnouncementVO addAnn(String anc_no,String anc_con,String backstage_no,
			Timestamp created_at,Timestamp updated_at,Integer active) {
		
		AnnouncementVO annVO = new AnnouncementVO();
		
		annVO.setAnc_no(anc_no);
		annVO.setAnc_con(anc_con);
		annVO.setBackstage_no(backstage_no);
		annVO.setCreated_at(created_at);
		annVO.setUpdated_at(updated_at);
		annVO.setActive(active);
		dao.insert(annVO);
		
		return annVO;
	}
	
	public void add(AnnouncementVO annVO) {
		dao.insert(annVO);
	}
	
	public AnnouncementVO updateAnn(String anc_no,String anc_con,Timestamp updated_at,Integer active) {
		
		AnnouncementVO annVO = new AnnouncementVO();
		
		annVO.setAnc_con(anc_con);
		annVO.setUpdated_at(updated_at);
		annVO.setActive(active);
		dao.update(annVO);
		
		return dao.findByPrimaryKey(anc_no);
	}

	public void update(AnnouncementVO updateAnn) {
		dao.update(updateAnn);
	}

	public void delete(String anc_no) {
		dao.delete(anc_no);
	}

	public AnnouncementVO getOneAnn(String anc_no) {
		return dao.findByPrimaryKey(anc_no);
	}

	public List<AnnouncementVO> getAll() {
		return dao.getAll();
	}

}
