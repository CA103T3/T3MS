package com.session.model;

import java.sql.Timestamp;
import java.util.List;

public class SessionService {
    private SessionDAO_interface dao;

    public SessionService() {
        dao = new SessionDAO();
    }

    public String addSession(String theater_no, String movie_no, Timestamp session_time, 
            String seat_table) {

        SessionVO sessionVO = new SessionVO();
        sessionVO.setTheater_no(theater_no);
        sessionVO.setMovie_no(movie_no);
        sessionVO.setSession_time(session_time);
        sessionVO.setSeat_table(seat_table);
        
        String session_no = dao.insert(sessionVO);

        return session_no;
    }

    public void updateSession(String session_no, String theater_no, String movie_no,
            Timestamp session_time, String seat_table) {

        SessionVO sessionVO = new SessionVO();
        sessionVO.setSession_no(session_no);
        sessionVO.setTheater_no(theater_no);
        sessionVO.setMovie_no(movie_no);
        sessionVO.setSession_time(session_time);
        sessionVO.setSeat_table(seat_table);

        dao.update(sessionVO);
    }

    public void deleteSession(String session_no) {
        dao.delete(session_no);
    }

    public SessionVO getOneSession(String session_no) {
        return dao.findByPrimaryKey(session_no);
    }

    public List<SessionVO> getAll() {
        return dao.getAll();
    }
}