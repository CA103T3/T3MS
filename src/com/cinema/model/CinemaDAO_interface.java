package com.cinema.model;

import java.util.List;

import com.cinema.model.CinemaVO;

public interface CinemaDAO_interface {
    public String insert(CinemaVO cinemaVO);
    public void update(CinemaVO cinemaVO);
    public void delete(String cinema_no);
    public CinemaVO findByPrimaryKey(String cinema_no);
    public List<CinemaVO> getAll();
}
