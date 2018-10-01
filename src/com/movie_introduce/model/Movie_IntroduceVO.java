package com.movie_introduce.model;

import java.sql.Date;

public class Movie_IntroduceVO implements java.io.Serializable{
		
	private static final long serialVersionUID = 1L;
	
	private String introd_no;
	private String movie_no;
	private String source;
	private String url;
	private String author;
	private String title;
	private String content;
	private Date created_at;
	private Date updated_at;
	private Integer active;
	private String photo_path;
    private String photo_small;
	
	public String getIntrod_no() {
		return introd_no;
	}
	public void setIntrod_no(String introd_no) {
		this.introd_no = introd_no;
	}
	public String getMovie_no() {
		return movie_no;
	}
	public void setMovie_no(String movie_no) {
		this.movie_no = movie_no;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	public String getPhoto_path() {
		return photo_path;
	}
	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}
	public String getPhoto_small() {
		return photo_small;
	}
	public void setPhoto_small(String photo_small) {
		this.photo_small = photo_small;
	}
   
}
