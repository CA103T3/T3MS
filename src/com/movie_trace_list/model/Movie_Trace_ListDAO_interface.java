package com.movie_trace_list.model;

import java.util.List;

import com.movie_trace_list.model.Movie_Trace_ListVO;

public interface Movie_Trace_ListDAO_interface {
	public void insert(Movie_Trace_ListVO movie_trace_ListVO);
	public void update(Movie_Trace_ListVO movie_trace_ListVO);
	public void delete(String mem_no);	
	public Movie_Trace_ListVO findByPrimaryKey(String mem_no);
	public List<Movie_Trace_ListVO> getAll();
	public void addheartgettwo(String mem_no,String movie_no); 
}
