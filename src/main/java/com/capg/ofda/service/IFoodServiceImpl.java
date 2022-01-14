package com.capg.ofda.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.ofda.Exceptions.FoodNotFoundException;
import com.capg.ofda.Repository.IFoodRepositoryDao;
import com.capg.ofda.entities.Food;

@Service
@Transactional

public class IFoodServiceImpl implements IFoodService{
	
	@Autowired
	IFoodRepositoryDao dao;


@Override
public Food addFood(Food food) {
	Food p = dao.saveAndFlush(food);
	return p;
}


@Override
public String deleteFood(int foodId) throws FoodNotFoundException {

	if (!dao.existsById(foodId)) {
		throw new FoodNotFoundException("No Food found for the user id");
	} else {
		dao.deleteById(foodId);
		return "Deleted Food";
	}
}



@Override
public Optional<Food> viewFood(int foodId) throws FoodNotFoundException // throws FoodIdNotFoundException
{
	if (!dao.existsById(foodId)) {
		throw new FoodNotFoundException("No Food found for the user id");
	} else {
		Optional<Food> p = dao.findById(foodId);
		System.out.println("Getting data from db" + p);
		return p;
	}
}


@Override
public List<Food> viewFoodList() {
	List<Food> p = dao.findAll();
	System.out.println("Getting data from db " + p);
	return p;
}

@Override
public Food updateFood(Food food) {
	if (!dao.existsById(food.getFoodId())) {
		return null;
	} else {
		dao.save(food);
		return dao.save(food);
	}
}

}