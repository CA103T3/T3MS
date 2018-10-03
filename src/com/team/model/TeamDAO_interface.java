package com.team.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.filmreview.model.FilmreviewVO;

public interface TeamDAO_interface {
	public void insert(TeamVO teamVO);                    //建立揪團
    public void update(TeamVO teamVO);
    public void delete(String team_no);
    public TeamVO findByPrimaryKey(String team_no);       
    public List<TeamVO> getAll();							//查詢
    public List<TeamVO> getAll(Map<String, String[]> map); 
    public List<TeamVO> getAllByMovie(String movie_no);      //根據電影查詢揪團
    public Set<TeamVO> getAllByMemNo(String team_no);      //我的揪團
}
