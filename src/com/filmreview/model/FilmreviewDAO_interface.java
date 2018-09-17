package com.filmreview.model;
import java.util.*;



public interface FilmreviewDAO_interface {
	
	  public void insert(FilmreviewVO filmreviewVO);
      public void update(FilmreviewVO filmreviewVO);
      public void delete(String fr_no);
      public FilmreviewVO  findByPrimaryKey(String fr_no);
      public List<FilmreviewVO> getAll();
	
}
