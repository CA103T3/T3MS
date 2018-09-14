package com.movie.model;

import java.util.*;

public interface MovieDAO_interface 
{
	public void insert(MovieVO movieVO);
	public void update(MovieVO movieVO);
	public void delete(String movie_no);
	public MovieVO findByPrimaryKey(String movie_no);
	public List<MovieVO> getAll();
}
