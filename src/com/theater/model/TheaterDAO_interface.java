package com.theater.model;

import java.util.*;
import com.theater.model.TheaterVO;

public interface TheaterDAO_interface {
    public String insert(TheaterVO theaterVO);
    public void update(TheaterVO theaterVO);
    public void delete(String theater_no);
    public TheaterVO findByPrimaryKey(String theater_no);
    public List<TheaterVO> getAllByCinemaNOEquipment(String cinema_no, String equipment);
    public List<TheaterVO> getAll();
    public List<TheaterVO> getAllofCinema(String cinema_no);
    public List<TheaterVO> getAllofNoConflictingTheater(String cinema_no, String sessionTimeFirst, String sessionTimeSec);
}
