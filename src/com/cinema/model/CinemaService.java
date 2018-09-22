package com.cinema.model;

import java.util.List;

import com.cinema.model.CinemaDAO_interface;
import com.cinema.model.CinemaVO;

public class CinemaService {

    private CinemaDAO_interface dao;

    public CinemaService() {
        dao = new CinemaDAO();
    }

    public String addCinema(String cinema_name, String cinema_engname, String cinema_address,
            String cinema_tel, String introduction, String traffic, String photo_title, String photo_path,
            Integer active, Integer state) {

        CinemaVO cinemaVO = new CinemaVO();
        cinemaVO.setCinema_name(cinema_name);
        cinemaVO.setCinema_engname(cinema_engname);
        cinemaVO.setCinema_address(cinema_address);
        cinemaVO.setCinema_tel(cinema_tel);
        cinemaVO.setIntroduction(introduction);
        cinemaVO.setTraffic(traffic);
        cinemaVO.setPhoto_title(photo_title);
        cinemaVO.setPhoto_path(photo_path);
        cinemaVO.setActive(active);
        cinemaVO.setState(state);
        
        String cinema_no = dao.insert(cinemaVO);

        return cinema_no;
    }

    public void updateCinema(String cinema_no, String cinema_name, String cinema_engname, String cinema_address,
            String cinema_tel, String introduction, String traffic, String photo_title, String photo_path,
            Integer active, Integer state) {

        CinemaVO cinemaVO = new CinemaVO();
        cinemaVO.setCinema_no(cinema_no);
        cinemaVO.setCinema_name(cinema_name);
        cinemaVO.setCinema_engname(cinema_engname);
        cinemaVO.setCinema_address(cinema_address);
        cinemaVO.setCinema_tel(cinema_tel);
        cinemaVO.setIntroduction(introduction);
        cinemaVO.setTraffic(traffic);
        cinemaVO.setPhoto_title(photo_title);
        cinemaVO.setPhoto_path(photo_path);
        cinemaVO.setActive(active);
        cinemaVO.setState(state);

        dao.update(cinemaVO);
    }

    public void deleteCinema(String cinema_no) {
        dao.delete(cinema_no);
    }

    public CinemaVO getOneCinema(String cinema_no) {
        return dao.findByPrimaryKey(cinema_no);
    }

    public List<CinemaVO> getAll() {
        return dao.getAll();
    }
}
