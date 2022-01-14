package com.capg.ofda.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.ofda.Exceptions.FoodNotFoundException;
import com.capg.ofda.entities.Food;
import com.capg.ofda.service.IFoodServiceImpl;

/*Controller Class For FOOD Items
 * Author : HARSHADA GANGANE
 * Date Created : 12/01/2022
 *
 */


@RestController
@RequestMapping("/food")
public class IFoodController {
	
	@Autowired
	private IFoodServiceImpl serviceobj;
	static final Logger LOGGER = LoggerFactory.getLogger(IFoodController.class);

/*************************************************************************************************************************************************/
	// METHOD : TO ADD FOOD ITEM

@PostMapping("/add")
	public ResponseEntity<Food> addFood(@RequestBody Food food) {
		LOGGER.info("Add Food");
		Food p = serviceobj.addFood(food);
		if (p == null) {
			return new ResponseEntity("Food Not Found!!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Food>(p, HttpStatus.OK);
	}

/*************************************************************************************************************************************************/
// METHOD : TO DELETE FOOD ITEM BY ID

@DeleteMapping("/delete/{id}")
	public String deleteFood(@PathVariable("id") int id) throws FoodNotFoundException {
		LOGGER.info("Delete Food");
		return serviceobj.deleteFood(id); 
	} 

/*************************************************************************************************************************************************/
// METHOD : TO VIEW ALL FOOD ITEMS

@GetMapping("/viewAllFood")
	public List<Food> viewAllFood() {
		LOGGER.info("View All Food");
		return serviceobj.viewFoodList();
	}

/*************************************************************************************************************************************************/
// METHOD : TO VIEW FOOD ITEM BY ID

@GetMapping("/viewfood/{id}")
public ResponseEntity<Object> getFood(@PathVariable("id") int id) throws FoodNotFoundException {
	LOGGER.info("View Food Id");
	Map<String, Object> res = new HashMap<String, Object>();
	try {
		Optional<Food> p=serviceobj.viewFood(id);
	res.put("status", HttpStatus.OK.value());
	res.put("data", p);
	return new ResponseEntity<>(res, HttpStatus.OK);
	}catch(FoodNotFoundException e) {
		res.put("status", HttpStatus.NOT_FOUND.value());
		res.put("data", e.getMessage());
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
	}

}

/*************************************************************************************************************************************************/
// METHOD : TO UPDATE FOOD ITEM

@PutMapping("/UpdateFood")
public ResponseEntity<String> UpdateFood(@RequestBody Food food) {
	LOGGER.info("Update Food");
	Food p= serviceobj.updateFood(food);
	if(p== null)
	return new ResponseEntity("Not Update!!", HttpStatus.NOT_FOUND);
	else
	return new ResponseEntity<>("Updated", HttpStatus.OK);

}
}