package com.boxoffice.model;

import java.sql.Date;
import java.util.List;

public class BoxOfficeService {

    private BoxOfficeDAO_interface dao;

    public BoxOfficeService() {
        dao = new BoxOfficeDAO();
    }

    public String addBoxOffice(String movie_no, String moviename, Date statistics, Integer rank, Integer loc) {

        BoxOfficeVO boVO = new BoxOfficeVO();
        boVO.setMovie_no(movie_no);
        boVO.setMoviename(moviename);
        boVO.setStatistics(statistics);
        boVO.setRank(rank);
        boVO.setLoc(loc);
        
        String ranking_no = dao.insert(boVO);

        return ranking_no;
    }

    public void updateBoxOffice(String ranking_no, String movie_no, String moviename, Date statistics, Integer rank,
            Integer loc) {

        BoxOfficeVO boVO = new BoxOfficeVO();
        boVO.setRanking_no(ranking_no);
        boVO.setMovie_no(movie_no);
        boVO.setMoviename(moviename);
        boVO.setStatistics(statistics);
        boVO.setRank(rank);
        boVO.setLoc(loc);

        dao.update(boVO);
    }

    public void deleteBoxOffice(String ranking_no) {
        dao.delete(ranking_no);
    }

    public BoxOfficeVO getOneBoxOffice(String ranking_no) {
        return dao.findByPrimaryKey(ranking_no);
    }

    public List<BoxOfficeVO> getAll() {
        return dao.getAll();
    }

    public List<BoxOfficeVO> getLatestTenByLoc(Integer loc) {
        return dao.getLatestTenByLoc(loc);
    }
}
