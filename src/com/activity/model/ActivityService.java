package com.activity.model;

import java.util.List;

public class ActivityService {
	private ActivityDAO_interface dao;

	public ActivityService() {
		dao = new ActivityJNDIDAO();
	}

	public ActivityVO addAct(String activity_name, String activity_desc, String backstage_no,
			Integer active, byte[] img_path, String activity_url) {
		ActivityVO actVO = new ActivityVO();

		actVO.setActivity_name(activity_name);
		actVO.setActivity_desc(activity_desc);
		actVO.setBackstage_no(backstage_no);
		actVO.setActive(active);
		actVO.setImg_path(img_path);
		actVO.setActivity_url(activity_url);
		dao.insert(actVO);

		return actVO;
	}

	public ActivityVO updateAct(String activity_name, String activity_desc, String backstage_no, Integer active,
			byte[] img_path, String activity_url) {

		ActivityVO actVO = new ActivityVO();
		actVO.setActivity_name(activity_name);
		actVO.setActivity_desc(activity_desc);
		actVO.setBackstage_no(backstage_no);
		actVO.setActive(active);
		actVO.setImg_path(img_path);
		actVO.setActivity_url(activity_url);
		dao.update(actVO);

		return actVO;
	}
	
	public void deleteAct(String activity_no) {
		dao.delete(activity_no);
	}
	
	public ActivityVO getOneActivity(String activity_no) {
		return dao.findByPrimaryKey(activity_no);
	}
	public List<ActivityVO> getAll(){
		return dao.getAll();
	}
}
