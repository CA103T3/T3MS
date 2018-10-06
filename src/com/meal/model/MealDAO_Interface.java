package com.meal.model;

import java.util.List;


public interface MealDAO_Interface {

	void add(MealVO mealVO);
	void update(MealVO mealVO);
	void delete(String mealNo);
	MealVO findByPK(String mealNo);
	List<MealVO> getAll();
}
