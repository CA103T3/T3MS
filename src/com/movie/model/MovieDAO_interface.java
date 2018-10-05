package com.movie.model;

import java.util.*;

public interface MovieDAO_interface 
{
	public void insert(MovieVO movieVO);
	public void update(MovieVO movieVO);
	public void delete(String movie_no);
	public MovieVO findByPrimaryKey(String movie_no);
	public MovieVO findByMovieName(String movie_name);
	public List<MovieVO> getAll();

	//現正熱映
	public List<MovieVO> getNow();

	//即將上映
	public List<MovieVO> getComingsoon();
}
