package com.movie_introduce.model;

import java.util.List;

public class Movie_IntroduceService {

	private Movie_IntroduceDAO_interface dao ;
		
	public Movie_IntroduceService() {
		dao = new Movie_IntroduceDAO();
	}
	
	
	public Movie_IntroduceVO addMovie_Itde(String movie_no,String source,String url,
			String author,String title,String content,Integer active) {
		
		Movie_IntroduceVO movie_introduceVO = new Movie_IntroduceVO();
		
		movie_introduceVO.setMovie_no(movie_no);
		movie_introduceVO.setSource(source);
		movie_introduceVO.setUrl(url);
		movie_introduceVO.setAuthor(author);
		movie_introduceVO.setTitle(title);
		movie_introduceVO.setContent(content);
		movie_introduceVO.setActive(active);
		dao.insert(movie_introduceVO);
		
		return movie_introduceVO;
	}
	
	public Movie_IntroduceVO updateMovie_Itde(String introd_no ,String movie_no,String source,String url,
			String author,String title,String content,Integer active) {
		
		Movie_IntroduceVO movie_introduceVO = new Movie_IntroduceVO();
		
		movie_introduceVO.setIntrod_no(introd_no);
		movie_introduceVO.setMovie_no(movie_no);
		movie_introduceVO.setSource(source);
		movie_introduceVO.setUrl(url);
		movie_introduceVO.setAuthor(author);
		movie_introduceVO.setTitle(title);
		movie_introduceVO.setContent(content);
		movie_introduceVO.setActive(active);
		dao.update(movie_introduceVO);
		
		return movie_introduceVO;
	}
	
	public void delete(String introd_no) {
		dao.delete(introd_no);
	}
	
	public Movie_IntroduceVO getOneMovie_Itde(String introd_no) {
		return dao.findByPrimaryKey(introd_no);
		           
	}
	
	public List<Movie_IntroduceVO> getAll() {
		return dao.getAll();
	}
	
	
}
