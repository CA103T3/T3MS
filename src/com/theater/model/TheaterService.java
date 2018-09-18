package com.theater.model;

import java.io.Reader;
import java.util.List;

public class TheaterService {

    private TheaterDAO_interface dao;
    
    public TheaterService() {
        dao = new TheaterDao();
    }

    public String addTheater(String cinema_no, String theater_name,
            Integer t_rows, Integer t_columns, Reader seat_model, Integer seats, String equipment) {

        TheaterVO theaterVO = new TheaterVO();

        theaterVO.setCinema_no(cinema_no);
        theaterVO.setTheater_name(theater_name);
        theaterVO.setT_rows(t_rows);
        theaterVO.setT_columns(t_columns);
        theaterVO.setSeat_model(seat_model);
        theaterVO.setSeats(seats);
        theaterVO.setEquipment(equipment);
        
        String theater_no = dao.insert(theaterVO);
        //theaterVO.setTheater_no(theater_no);
        //System.out.println("addTheater seat_model: " + readString(seat_model));
        //return theaterVO;
        return theater_no;
    }

    public void updateTheater(String theater_no, String cinema_no, String theater_name,
            Integer t_rows, Integer t_columns, Reader seat_model, Integer seats, String equipment) {

        TheaterVO theaterVO = new TheaterVO();

        theaterVO.setTheater_no(theater_no);
        theaterVO.setCinema_no(cinema_no);
        theaterVO.setTheater_name(theater_name);
        theaterVO.setT_rows(t_rows);
        theaterVO.setT_columns(t_columns);
        theaterVO.setSeat_model(seat_model);
        theaterVO.setSeats(seats);
        theaterVO.setEquipment(equipment);

        dao.update(theaterVO);
    }

    public void deleteTheater(String theater_no) {
        dao.delete(theater_no);
    }

    public TheaterVO getOneTheater(String theater_no) {
        return dao.findByPrimaryKey(theater_no);
    }

    public List<TheaterVO> getAll() {
        return dao.getAll();
    }

    public List<TheaterVO> getAllofCinema(String cinema_no) {
        return dao.getAllofCinema(cinema_no);
    }
}
