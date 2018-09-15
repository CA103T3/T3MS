package com.moviesession.model;

import java.util.List;

public interface Movie_SessionDAO_interface {

	public void insert(Movie_SessionVO movie_SessionVO);

	public void update(Movie_SessionVO movie_SessionVO);

	public void delete(String movie_session_no);

	public Movie_SessionVO findByPrimaryKey(String movie_session_no);

	public List<Movie_SessionVO> getAll();

	// 查某電影有多少場次
	public List<Movie_SessionVO> getMovieSessionAll(String movie_session_no);
	
	//現正熱映
	//即將上映
	
	

}
