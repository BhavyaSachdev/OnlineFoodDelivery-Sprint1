package com.capg.ofda.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.capg.ofda.Exceptions.CustomerNotFoundException;
import com.capg.ofda.Exceptions.FoodNotFoundException;
import com.capg.ofda.Exceptions.ItemNotFoundException;
import com.capg.ofda.entities.Cart;
import com.capg.ofda.entities.CartItem;

public interface ICartService {

	public Cart createCart(int customerId)throws CustomerNotFoundException;
	public CartItem updateCartQuantity(int customerId, int itemId, int quantity)throws CustomerNotFoundException,ItemNotFoundException;
	public String deleteCartItem(int customerId, int itemId)throws CustomerNotFoundException,ItemNotFoundException;
	public String clearCart(int customerId)throws CustomerNotFoundException;
	public CartItem addFoodToCart(Map<String, Object> requestData)throws CustomerNotFoundException,FoodNotFoundException;
	public List<CartItem> viewitemList(int custid)throws CustomerNotFoundException;
	public List<CartItem> viewallitemList();
	public List<Cart> viewCarts();
	public Optional<Cart> viewCart(int custid)throws CustomerNotFoundException;
}


