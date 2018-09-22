package com.movie.model;

import java.sql.Date;
import java.util.List;

public class MovieService {
	
	private MovieDAO_interface dao;

	public MovieService() {
		dao = new MovieDAO();
	}

	public MovieVO addMovie(String movie_type, String movie_name, String eng_name, byte[] movie_pic,
			java.sql.Date relased, String distributed, Integer length, String language, String madein, Integer imdb,
			String tomato, String rating, String trailer_url, String brief_intro, Integer active, String director,
			String starring) {
		MovieVO movieVO = new MovieVO();

		movieVO.setMovie_type(movie_type);
		movieVO.setMovie_name(movie_name);
		movieVO.setEng_name(eng_name);
		movieVO.setMovie_pic(movie_pic);
		movieVO.setRelased(relased);
		movieVO.setDistributed(distributed);
		movieVO.setLength(length);
		movieVO.setLanguage(language);
		movieVO.setMadein(madein);
		movieVO.setImdb(imdb);
		movieVO.setTomato(tomato);
		movieVO.setRating(rating);
		movieVO.setTrailer_url(trailer_url);
		movieVO.setBrief_intro(brief_intro);
		movieVO.setActive(active);
		movieVO.setDirector(director);
		movieVO.setStarring(starring);
		dao.insert(movieVO);

		return movieVO;
	}

	public MovieVO updateMovie(String movie_no, String movie_type, String movie_name, String eng_name,
			byte[] movie_pic, java.sql.Date relased, String distributed, Integer length, String language,
			String madein, Integer imdb, String tomato, String rating, String trailer_url, String brief_intro,
			Integer active, String director, String starring) {
		MovieVO movieVO = new MovieVO();

		movieVO.setMovie_no(movie_no);
		movieVO.setMovie_type(movie_type);
		movieVO.setMovie_name(movie_name);
		movieVO.setEng_name(eng_name);
		movieVO.setMovie_pic(movie_pic);
		movieVO.setRelased(relased);
		movieVO.setDistributed(distributed);
		movieVO.setLength(length);
		movieVO.setLanguage(language);
		movieVO.setMadein(madein);
		movieVO.setImdb(imdb);
		movieVO.setTomato(tomato);
		movieVO.setRating(rating);
		movieVO.setTrailer_url(trailer_url);
		movieVO.setBrief_intro(brief_intro);
		movieVO.setActive(active);
		movieVO.setDirector(director);
		movieVO.setStarring(starring);
		dao.update(movieVO);

		return movieVO;
	}

	public void deleteMovie(String movie_no) {
		dao.delete(movie_no);
	}

	public MovieVO getOneMovie(String movie_no) {
		return dao.findByPrimaryKey(movie_no);
		           
	}

	public List<MovieVO> getAll() {
		return dao.getAll();
	}

}
