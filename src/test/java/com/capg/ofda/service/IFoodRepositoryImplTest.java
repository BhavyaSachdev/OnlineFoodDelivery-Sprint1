package com.capg.ofda.service;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.ofda.Exceptions.FoodNotFoundException;
import com.capg.ofda.Repository.IFoodRepositoryDao;
import com.capg.ofda.entities.Food;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class IFoodRepositoryImplTest {
	@InjectMocks
	private IFoodServiceImpl service;
	@Mock
	private IFoodRepositoryDao dao;
	
	@Test
	public void viewFoodListTest()
	{
		List<Food> p = new ArrayList<Food>();
		  
	    Food food = new Food();
	    food.setFoodId(1);
	    food.setFoodName("Dark Fantasy");
	    food.setFoodType("Biscuit");
	    food.setFoodDescription("Choclate Biscuit");
	    food.setFoodCost(50.0);
	    food.setFoodQuantity(5);
	    p.add(food);
	   
		  
	    Food food2 = new Food();
	    food2.setFoodId(2);
	    food2.setFoodName("Salted Wafers");
	    food2.setFoodType("Fried");
	    food2.setFoodDescription("Descrption");
	    food2.setFoodCost(50.0);
	    food2.setFoodQuantity(2);
	    p.add(food2);

		when(service.viewFoodList()).thenReturn(p); 
		assertEquals(2,service.viewFoodList().size());
		 
	}
	
	
	@Test
	public void getFoodById() throws FoodNotFoundException 
	{
	    Food food = new Food();
	    food.setFoodId(1);
	    food.setFoodName("Aloo Sev");
	    food.setFoodType("Ready to eat");
	    food.setFoodDescription("Aloo sev");
	    food.setFoodCost(50.0);
	    food.setFoodQuantity(3);
	    
	    when(dao.existsById(food.getFoodId())).thenReturn(true);
	    Mockito.when(dao.findById(1)).thenReturn(Optional.of(food));
	    assertEquals(food.getFoodId(),service.viewFood(1).get().getFoodId());
		
	}
	
	@Test
	public void deleteFood() throws FoodNotFoundException
	{    when(dao.existsById(75)).thenReturn(true);
		 Mockito.when(service.deleteFood(75)).thenReturn("Deleted Food");
	        
	}
	

	 @Test
	 public void addFood()
	 {
		 List<Food> p = new ArrayList<Food>();
		  
		    Food food = new Food();
		    food.setFoodId(1);
		    food.setFoodName("Dark Fantasy");
		    food.setFoodType("Biscuit");
		    food.setFoodDescription("Choclate Biscuit");
		    food.setFoodCost(50.0);
		    food.setFoodQuantity(5);
		    p.add(food);
		    
		    Food food2 = new Food();
		    food.setFoodId(1);
		    food.setFoodName("Aloo Sev");
		    food.setFoodType("Ready to eat");
		    food.setFoodDescription("Aloo sev");
		    food.setFoodCost(50.0);
		    food.setFoodQuantity(5);
		    p.add(food2);
		    
		    when(service.addFood(food)).thenReturn(food);
		    when(service.addFood(food2)).thenReturn(food2);
		    assertEquals(2,p.size());
		    
		   
		    
	 }

	 @Test
	 public void updateFood() throws FoodNotFoundException 
	 {
	  
	    Food food = new Food();
	    food.setFoodId(1);
	    food.setFoodName("Dark Fantasy");
	    food.setFoodType("Biscuit");
	    food.setFoodDescription("Choclate Biscuit");
	    food.setFoodCost(50.0);
	    food.setFoodQuantity(5);
	  
	    when(dao.existsById(food.getFoodId())).thenReturn(true);
	    
	    food.setFoodId(1);
	    food.setFoodName("Dark Fantasy");
	    food.setFoodType("Biscuit");
	    food.setFoodDescription("Choclate Biscuit");
	    food.setFoodCost(50.0);
	    food.setFoodQuantity(5);
	  
	    when(service.updateFood(food)).thenReturn(food);
	    assertEquals("Dark Fantasy", food.getFoodName());
	  
		 
	 }
	

}
