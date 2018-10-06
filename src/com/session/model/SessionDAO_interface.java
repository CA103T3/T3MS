package com.session.model;

import java.sql.Connection;
import java.util.List;

import com.session.model.SessionVO;

public interface SessionDAO_interface {
    public String insert(SessionVO sessionVO);
    public void update(SessionVO sessionVO);
    public void delete(String session_no);
    public SessionVO findByPrimaryKey(String session_no);
    public List<SessionVO> findBySessionTimeMovieNo(String sessionTime, String movie_no);
    public List<SessionVO> getAll();
    public List<SessionVO> getAllofTheater(String theater_no);
    public List<SessionVO> getAllofJoinTheaterMovieWhereTheaterNoCinema(String cinema_no);
    public SessionVO getOneofJoinTheaterMovieWhereSessionNo(String session_no);
    
    public void updateSessionSeat(String seattable,String movie_session_no,Connection conn);
}
