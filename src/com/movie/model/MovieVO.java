package com.movie.model;

import java.sql.Date;

public class MovieVO implements java.io.Serializable{
	private String movie_no;
	private String movie_type;
	private String movie_name;
	private String eng_name;
	private byte[] movie_pic;
	private Date relased;
	private String distributed;
	private Integer length;
	private String language;
	private String madein;
	private Integer imdb;
	private String tomato;
	private String rating;
	private String trailer_url;
	private String brief_intro;
	private Integer active;
	private String director;
	private String starring;
	
	
	
	public String getMovie_no() {
		return movie_no;
	}
	public void setMovie_no(String movie_no) {
		this.movie_no = movie_no;
	}
	public String getMovie_type() {
		return movie_type;
	}
	public void setMovie_type(String movie_type) {
		this.movie_type = movie_type;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public String getEng_name() {
		return eng_name;
	}
	public void setEng_name(String eng_name) {
		this.eng_name = eng_name;
	}
	public byte[] getMovie_pic() {
		return movie_pic;
	}
	public void setMovie_pic(byte[] movie_pic) {
		this.movie_pic = movie_pic;
	}
	public Date getRelased() {
		return relased;
	}
	public void setRelased(Date relased) {
		this.relased = relased;
	}
	public String getDistributed() {
		return distributed;
	}
	public void setDistributed(String distributed) {
		this.distributed = distributed;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getMadein() {
		return madein;
	}
	public void setMadein(String madein) {
		this.madein = madein;
	}
	public Integer getImdb() {
		return imdb;
	}
	public void setImdb(Integer imdb) {
		this.imdb = imdb;
	}
	public String getTomato() {
		return tomato;
	}
	public void setTomato(String tomato) {
		this.tomato = tomato;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getTrailer_url() {
		return trailer_url;
	}
	public void setTrailer_url(String trailer_url) {
		this.trailer_url = trailer_url;
	}
	public String getBrief_intro() {
		return brief_intro;
	}
	public void setBrief_intro(String brief_intro) {
		this.brief_intro = brief_intro;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getStarring() {
		return starring;
	}
	public void setStarring(String starring) {
		this.starring = starring;
	}
	
	
}
