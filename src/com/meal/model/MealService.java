package com.meal.model;

import java.util.List;


public class MealService {

	private MealDAO_Interface dao;

	public MealService() {
		dao = new MealJNDIDAO();
	}
	
	public MealVO addMeal(String cinema_no, String meal_name, String meal_food, int meal_price, byte[] meal_photo, String extension, String photo_title, int meal_active)
	{
		MealVO mealVO = new MealVO();
		
//		mealVO.setMeal_no("10");
		
		mealVO.setCinema_no(cinema_no);
		mealVO.setMeal_name(meal_name);
		mealVO.setMeal_food(meal_food);
		mealVO.setMeal_price(meal_price);
		mealVO.setMeal_photo(meal_photo);
		mealVO.setExtension(extension);
		mealVO.setPhoto_title(photo_title);
		mealVO.setMeal_active(meal_active);
		
		dao.add(mealVO);
		
		return mealVO;
	}
	
	public MealVO updateMeal(String cinema_no, String meal_name, String meal_food, int meal_price, byte[] meal_photo, String extension, String photo_title, int meal_active)
	{
		MealVO mealVO = new MealVO();
		
//		mealVO.setMeal_no(Meal_no);
		mealVO.setCinema_no(cinema_no);
		mealVO.setMeal_name(meal_name);
		mealVO.setMeal_food(meal_food);
		mealVO.setMeal_price(meal_price);
		mealVO.setMeal_photo(meal_photo);
		mealVO.setExtension(extension);
		mealVO.setPhoto_title(photo_title);
		mealVO.setMeal_active(meal_active);
		
		dao.update(mealVO);
		
		return mealVO;
	}
	
	public void deleteMeal(String mealNo)
	{
		dao.delete(mealNo);
	}
	
	public MealVO getOneMeal(String mealNo)
	{
		return dao.findByPK(mealNo);
	}
	
	public List<MealVO> getAll()
	{
		return dao.getAll();
	}
}
