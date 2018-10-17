package com.ticketType.model;

import java.util.List;

public class TypeService {
	private TypeDAO_interface dao;

	public TypeService() {
		dao = new TypeDAO();
	}

	// public String addType(String theater_no, String identity, String equipment,
	public String addType(String theater_no, String identity, String time, Integer price) {

		TypeVO typeVO = new TypeVO();
		typeVO.setTheater_no(theater_no);
		typeVO.setIdentity(identity);
		// typeVO.setEquipment(equipment);
		typeVO.setTime(time);
		typeVO.setPrice(price);

		String type_no = dao.insert(typeVO);

		return type_no;
	}

	public void updateType(String type_no, String theater_no, String identity, String time, Integer price) {
		// String equipment, String time, Integer price) {

		TypeVO typeVO = new TypeVO();
		typeVO.setType_no(type_no);
		typeVO.setTheater_no(theater_no);
		typeVO.setIdentity(identity);
		// typeVO.setEquipment(equipment);
		typeVO.setTime(time);
		typeVO.setPrice(price);

		dao.update(typeVO);
	}

	public void deleteType(String type_no) {
		dao.delete(type_no);
	}

	public TypeVO getOneType(String type_no) {
		return dao.findByPrimaryKey(type_no);
	}

	public List<TypeVO> getAll() {
		return dao.getAll();
	}

	public List<TypeVO> getAllofCinema(String cinema_no) {
		return dao.getAllofCinema(cinema_no);
	}

	public TypeVO getOneTypeJoinTheater(String type_no) {
		return dao.getOneTypeJoinTheater(type_no);
	}

	public TypeVO getOneTypeByTheaterNo(String theater_no) {
		return dao.getOneTypeByTheaterNo(theater_no);
	}
}
