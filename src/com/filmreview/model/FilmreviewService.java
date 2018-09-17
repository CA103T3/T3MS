package com.filmreview.model;

import java.sql.Date;
import java.util.List;

public class FilmreviewService {
	private FilmreviewDAO_interface dao;
	
	public FilmreviewService() {
		dao = new FilmreviewDAO();
	}
	

	
	public FilmreviewVO addFilmrevew(String movie_no, Date created_at ,Date updated_at ,String content, Double evaluation ,String title ,String source ,String url ,String mem_no ,String author ,Integer active ) {
		FilmreviewVO filmreviewVO = new FilmreviewVO();
		
		filmreviewVO.setMovie_no(movie_no);
		filmreviewVO.setCreated_at(created_at);
		filmreviewVO.setUpdated_at(updated_at);
		filmreviewVO.setContent(content);
		filmreviewVO.setEvaluation(evaluation);
		filmreviewVO.setTitle(title);
		filmreviewVO.setSource(source);
		filmreviewVO.setUrl(url);
		filmreviewVO.setMem_no(mem_no);
		filmreviewVO.setAuthor(author);
		filmreviewVO.setActive(active);
		dao.insert(filmreviewVO);
		
		return filmreviewVO;
	}; 
	
	public FilmreviewVO updateFilmrevew(String fr_no, String movie_no, Date created_at ,Date updated_at ,String content, Double evaluation ,String title ,String source ,String url ,String mem_no ,String author ,Integer active ) {
		FilmreviewVO filmreviewVO = new FilmreviewVO();
		
		filmreviewVO.setFr_no(fr_no);
		filmreviewVO.setMovie_no(movie_no);
		filmreviewVO.setCreated_at(created_at);
		filmreviewVO.setUpdated_at(updated_at);
		filmreviewVO.setContent(content);
		filmreviewVO.setEvaluation(evaluation);
		filmreviewVO.setTitle(title);
		filmreviewVO.setSource(source);
		filmreviewVO.setUrl(url);
		filmreviewVO.setMem_no(mem_no);
		filmreviewVO.setAuthor(author);
		filmreviewVO.setActive(active);
		dao.update(filmreviewVO);
		
		return filmreviewVO;
	}; 
	
	public void delete(String fr_no) {
		dao.delete(fr_no);
	}
    public FilmreviewVO  findByPrimaryKey(String fr_no) {
    	return dao.findByPrimaryKey(fr_no);
    	}
    public List<FilmreviewVO> getAll(){
    	return dao.getAll();
    }
	
	
	
	
}
