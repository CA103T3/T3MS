package com.activity.model;

import java.util.List;

public interface ActivityDAO_interface {
	
	public void insert(ActivityVO actVO);
	
	public void update(ActivityVO actVO);
	
	public void delete(String activity_no);
	
	public ActivityVO findByPrimaryKey(String activity_no);
	
	public List<ActivityVO> getAll();

}
