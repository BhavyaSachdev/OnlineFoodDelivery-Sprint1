package com.capg.ofda.service;

import java.util.List;
import java.util.Optional;

import com.capg.ofda.Exceptions.FoodNotFoundException;
import com.capg.ofda.entities.Food;

public interface IFoodService {

	
	Food addFood(Food food);

	Food updateFood(Food food) throws FoodNotFoundException;

	String deleteFood(int foodId) throws FoodNotFoundException; //throws FoodId Not Found Exception;

	Optional<Food> viewFood(int foodId) throws FoodNotFoundException; //throws FoodId Not Found Exception;

	List<Food> viewFoodList();
}


