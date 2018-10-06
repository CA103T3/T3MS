package com.meal.model;

import java.io.Serializable;

public class MealVO implements Serializable
{
	
	private static final long serialVersionUID = 1L;

	private String meal_no;
	private String cinema_no;
	private String meal_name;
	private String meal_food;
	private int meal_price;
	private byte[] meal_photo;
	private String extension;
	private String photo_title;
	private int meal_active;
	
	
	public String getmeal_no() {
		return meal_no;
	}




	public MealVO(String meal_no, String cinema_no, String meal_name, String meal_food, int meal_price,
			byte[] meal_photo, String extension, String photo_title, int meal_active) {
		super();
		this.meal_no = meal_no;
		this.cinema_no = cinema_no;
		this.meal_name = meal_name;
		this.meal_food = meal_food;
		this.meal_price = meal_price;
		this.meal_photo = meal_photo;
		this.extension = extension;
		this.photo_title = photo_title;
		this.meal_active = meal_active;
	}




	




	public String getMeal_no() {
		return meal_no;
	}




	public void setMeal_no(String meal_no) {
		this.meal_no = meal_no;
	}




	public String getCinema_no() {
		return cinema_no;
	}




	public void setCinema_no(String cinema_no) {
		this.cinema_no = cinema_no;
	}




	public String getMeal_name() {
		return meal_name;
	}




	public void setMeal_name(String meal_name) {
		this.meal_name = meal_name;
	}




	public String getMeal_food() {
		return meal_food;
	}




	public void setMeal_food(String meal_food) {
		this.meal_food = meal_food;
	}




	public int getMeal_price() {
		return meal_price;
	}




	public void setMeal_price(int meal_price) {
		this.meal_price = meal_price;
	}




	public byte[] getMeal_photo() {
		return meal_photo;
	}




	public void setMeal_photo(byte[] meal_photo) {
		this.meal_photo = meal_photo;
	}




	public String getExtension() {
		return extension;
	}




	public void setExtension(String extension) {
		this.extension = extension;
	}




	public String getPhoto_title() {
		return photo_title;
	}




	public void setPhoto_title(String photo_title) {
		this.photo_title = photo_title;
	}




	public int getMeal_active() {
		return meal_active;
	}




	public void setMeal_active(int meal_active) {
		this.meal_active = meal_active;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public MealVO() {
		// TODO Auto-generated constructor stub
	}
}
