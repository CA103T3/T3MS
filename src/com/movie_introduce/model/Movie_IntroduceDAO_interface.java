package com.movie_introduce.model;

import java.util.*;

public interface Movie_IntroduceDAO_interface {
	
	public void insert(Movie_IntroduceVO movie_introduceVO);
	public void update(Movie_IntroduceVO movie_introduceVO);
	public void delete(String introd_no);
	public Movie_IntroduceVO findByPrimaryKey(String introd_no);	               
	public List<Movie_IntroduceVO> getAll();

}
