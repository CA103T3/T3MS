package com.session.model;

import java.util.List;

import com.session.model.SessionVO;

public interface SessionDAO_interface {
    public String insert(SessionVO sessionVO);
    public void update(SessionVO sessionVO);
    public void delete(String session_no);
    public SessionVO findByPrimaryKey(String session_no);
    public List<SessionVO> getAll();
}
