package com.filmreview.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public class FilmreviewService {
	private FilmreviewDAO_interface dao;
	
	public FilmreviewService() {
		dao = new FilmreviewDAO();
	}
	

	
	public FilmreviewVO insertFilmrevew(String movie_no ,String content, Double evaluation ,String title ,String source ,String url ,String mem_no ,String author  ) {
		FilmreviewVO filmreviewVO = new FilmreviewVO();
		
		filmreviewVO.setMovie_no(movie_no);
		
	
		filmreviewVO.setContent(content);
		filmreviewVO.setEvaluation(evaluation);
		filmreviewVO.setTitle(title);
		filmreviewVO.setSource(source);
		filmreviewVO.setUrl(url);
		filmreviewVO.setMem_no(mem_no);
		filmreviewVO.setAuthor(author);
		
		dao.insert(filmreviewVO);
		
		return filmreviewVO;
	}; 
	
	public FilmreviewVO updateFilmrevew( String movie_no ,String content, Double evaluation ,String title ,String source ,String url ,String mem_no ,String author , String fr_no) {
		FilmreviewVO filmreviewVO = new FilmreviewVO();
		
		filmreviewVO.setMovie_no(movie_no);
		
		filmreviewVO.setContent(content);
		filmreviewVO.setEvaluation(evaluation);
		filmreviewVO.setTitle(title);
		filmreviewVO.setSource(source);
		filmreviewVO.setUrl(url);
		filmreviewVO.setMem_no(mem_no);
		filmreviewVO.setAuthor(author);
		filmreviewVO.setFr_no(fr_no);
		
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
    
    public Set<FilmreviewVO> getAllByMemNo(String movie_name){
    	return dao.getAllByMemNo(movie_name);
    }
	
	
	
	
}
