package com.announcement.model;

import java.util.List;

public interface AnnouncementDAO_interface {

	public void insert(AnnouncementVO newanc);

	public void update(AnnouncementVO ancmod);

	public void delete(String anc_no);

	public AnnouncementVO findByPrimaryKey(String anc_no);

	public List<AnnouncementVO> getAll();

}
