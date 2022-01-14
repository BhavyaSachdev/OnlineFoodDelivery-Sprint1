package com.capg.ofda.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.capg.ofda.Exceptions.CustomerNotFoundException;
import com.capg.ofda.Exceptions.FoodNotFoundException;
import com.capg.ofda.Exceptions.ItemNotFoundException;
import com.capg.ofda.entities.Cart;
import com.capg.ofda.entities.CartItem;
import com.capg.ofda.service.ICartServiceImpl;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;


/*Controller Class For CART Management
 * Author : Bhavya Sachdev
 * Date Created : 11/01/2022
 *
 */


@RestController
@RequestMapping("/cart")
public class ICartController {
	
	
static final Logger LOGGER = LoggerFactory.getLogger(ICartController.class);
	
	@Autowired
	ICartServiceImpl serviceobj;

	/******************************************************************************************************************************************/
	/*****************************************************
	 * Method:View all Cart Items By Customer ID
	 * Description:It is Used To Find Cart Items By Customer ID
	 * @GetMapping:It is used to handle the HTTP Post requests matched with given URI Expression
	 * @PathVariable annotation can be used to handle template variables in the request URI mapping, and set them as method parameters.
	 ***************************************************************************************************************************/

@GetMapping("/viewAllCartitems/{id}")
	public ResponseEntity<Object> viewAllCartitem(@PathVariable("id") int custid) throws CustomerNotFoundException{
		LOGGER.info("View All Cartitems");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			List<CartItem> p = serviceobj.viewitemList(custid);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(CustomerNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}

		/****************************************************************************************************************************************/
		//Method:View all Cart Items
	}
	@GetMapping("/viewAllCartitems")
	public List<CartItem> viewAllCartitems(){
		LOGGER.info("View All Cartitems");
		return serviceobj.viewallitemList();
	}
	
	/****************************************************************************************************************************************/
	//Method:View cart By Customer ID
	
	@GetMapping("/viewCart/{id}")
	public ResponseEntity<Object> viewCart(@PathVariable("id") int custid)  throws CustomerNotFoundException{
		LOGGER.info("View All Cartitems");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			java.util.Optional<Cart> p = serviceobj.viewCart(custid);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(CustomerNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}
	}
	/*****************************************************************************************************************************************************/
	//Method: View Only Cart
	
	@GetMapping("/viewCart")
	public List<Cart> viewAllCarts() {
		LOGGER.info("View All Available Carts");
		return serviceobj.viewCarts();
	}
	/*******************************************************************************************************************************************/
	//Method: To ADD Item To Cart
	
	@PostMapping("/addItem")
	public ResponseEntity<Object> addFoodToCart(@RequestBody Map<String, Object> requestData) throws CustomerNotFoundException, FoodNotFoundException {
		LOGGER.info("add-item URL is opened");
		LOGGER.info("addFoodToCart() is initiated");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			CartItem p = serviceobj.addFoodToCart(requestData);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(CustomerNotFoundException | FoodNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}
	}
	/*******************************************************************************************************************************************/
	//Method: Create A Cart By Particular ID
	
	@PostMapping("/createCart/{id}")
	public ResponseEntity<Object> createCart(@PathVariable("id") int customerId) throws CustomerNotFoundException{
		LOGGER.info("create-cart URL is opened");
		LOGGER.info("createCart() is initiated");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
		Cart p= serviceobj.createCart(customerId);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(CustomerNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}

	}
	/*******************************************************************************************************************************************/
	//Method: To Update cart By Customer id,ItemId And Quantity
	
	
	@PutMapping("/updateCartQuantity/{customerid}/{itemid}/{quant}")
	public ResponseEntity<Object> updateCartQuantity(@PathVariable("customerid") int customerId,@PathVariable("itemid") int itemId,@PathVariable("quant") int quant) throws CustomerNotFoundException, ItemNotFoundException{
		LOGGER.info("update-cart-quantity URL is opened");
		LOGGER.info("updateCartQuantity() is initiated");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
		CartItem p= serviceobj.updateCartQuantity(customerId,itemId,quant);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(CustomerNotFoundException | ItemNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}	
		
	}
	
	/*******************************************************************************************************************************************/
	//Method: To Delete An Item From Cart
	
	@DeleteMapping("/deleteItemFromCart/{cutomerid}/{itemid}")
	public ResponseEntity<Object> deleteCartItem(@PathVariable("cutomerid") int customerId,@PathVariable("itemid") int itemId) throws CustomerNotFoundException, ItemNotFoundException{
		LOGGER.info("update-cart-quantity URL is opened");
		LOGGER.info("updateCartQuantity() is initiated");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			String p= serviceobj.deleteCartItem(customerId,itemId);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}
		catch(CustomerNotFoundException | ItemNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}

	}
	
	/*******************************************************************************************************************************************/
	//Method: To clear Cart
	
	
	@DeleteMapping("/clearCart/{id}")
	public ResponseEntity<Object> clearCart(@PathVariable("id") int customerId) throws CustomerNotFoundException{
		LOGGER.info("clear-cart URL is opened");
		LOGGER.info("clearCart() is initiated");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			String p= serviceobj.clearCart(customerId);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(CustomerNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}

	}
	

}



