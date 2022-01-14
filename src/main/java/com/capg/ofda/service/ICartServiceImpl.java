package com.capg.ofda.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.ofda.Exceptions.CustomerNotFoundException;
import com.capg.ofda.Exceptions.FoodNotFoundException;
import com.capg.ofda.Exceptions.ItemNotFoundException;
import com.capg.ofda.Repository.ICartItemRepositoryDao;
import com.capg.ofda.Repository.ICartRepositoryDao;
import com.capg.ofda.Repository.ICustomerRepository;
import com.capg.ofda.Repository.IFoodRepositoryDao;
import com.capg.ofda.entities.Cart;
import com.capg.ofda.entities.CartItem;
import com.capg.ofda.entities.Customer;
import com.capg.ofda.entities.Food;

@Service

public class ICartServiceImpl implements ICartService {
	
	@Autowired
	ICartRepositoryDao cartdao;
	@Autowired
	ICartItemRepositoryDao itemdao;
	@Autowired
	IFoodRepositoryDao fooddao;
	@Autowired
	ICustomerRepository customerdao;
	
	
	//customer can create a cart by passing customerID
		@Override
		public Cart createCart(int customerId) throws CustomerNotFoundException{
			Cart cart=new Cart();
			if (!customerdao.existsById(customerId)) {
				throw new  CustomerNotFoundException("No Customer found for this id");
			} else {
				Customer customer=customerdao.findById(customerId).get();
				cart.setCustomer(customer);
				
				List<CartItem> cartItem = itemdao.findByCustomer(customer);
				double total=0.0;
				for(int i=0; i<cartItem.size(); i++)
				{
					total=total+(cartItem.get(i).getFood().getFoodCost())*(cartItem.get(i).getQuantity());
				}
				 cart.setTotal(total);
				cart.setCartItem(cartItem);
				return cartdao.save(cart);
			}
			
			
		}
		@Override
		public List<CartItem> viewitemList(int custid)throws CustomerNotFoundException {
			if (!customerdao.existsById(custid)) {
				throw new  CustomerNotFoundException("No Customer found for this id");
			}else {
				Customer customer=customerdao.findById(custid).get();
				List<CartItem> cartItem = itemdao.findByCustomer(customer);
				System.out.println("Getting data from db" + cartItem);
				return cartItem;
			}
		}
		@Override
		public List<CartItem> viewallitemList(){
			List<CartItem> cartitem=itemdao.findAll();
			return cartitem;
		}
		@Override
		public Optional<Cart> viewCart(int custid)throws CustomerNotFoundException {
			if (!customerdao.existsById(custid)) {
				throw new  CustomerNotFoundException("No Customer found for this id");
			}else {
				Customer customer=customerdao.findById(custid).get();
				Optional<Cart> cart = Optional.ofNullable(cartdao.findByCustomer(customer));
				System.out.println("Getting data from db" + cart);
				return cart;
			}
		}
		@Override
		public List<Cart> viewCarts(){
			List<Cart> cart=cartdao.findAll();
			return cart;
		}
		//customer can clear the cart created by passing customerId
		@Override
		public String clearCart(int customerId)throws CustomerNotFoundException {
			if (!customerdao.existsById(customerId)) {
				throw new  CustomerNotFoundException("No Customer found for this id");
			}else {
				Optional<Customer> byId = customerdao.findById(customerId);
				int cartId=cartdao.findByCustomer(byId.get()).getCartId();
				cartdao.deleteById(cartId);
				return "Cart Deleted";
			}
		}


		//customer can delete cartItem by sending customerId and itemId
		@Override
		public String deleteCartItem(int customerId, int itemId)throws CustomerNotFoundException,ItemNotFoundException {
			if (!customerdao.existsById(customerId)) {
				throw new  CustomerNotFoundException("No Customer found for this id");
			} else {
				boolean b = itemdao.existsById(itemId);
				if (!b)
					throw new  ItemNotFoundException("No CartItem found for this id");
				else {
					itemdao.deleteById(itemId);
					return "deleted";
				}
			}
		}

		//customer can update cart quantity by passing customerId, itemId and quantity.
		@Override
		public CartItem updateCartQuantity(int customerId, int itemId, int quantity)throws CustomerNotFoundException,ItemNotFoundException {
			if (!customerdao.existsById(customerId)) {
				throw new  CustomerNotFoundException("No Customer found for this id");
				
			} else {
				Optional<CartItem> byId2 = itemdao.findById(itemId);
				if (!byId2.isPresent())
					throw new  ItemNotFoundException("No CartItem found for this id");
					
				else {
					CartItem cartItem = byId2.get();
					cartItem.setQuantity(quantity);
					return itemdao.save(cartItem);
				}

			}
		}

		//customer can add Food to cartItem by passing customerId and body 
		@Override
		public CartItem addFoodToCart( Map<String, Object> requestData)throws CustomerNotFoundException,FoodNotFoundException {
			CartItem cartItem = new CartItem();
			int customerId=(int) requestData.get("customerId");
			if (!customerdao.existsById(customerId)) {
				throw new  CustomerNotFoundException("No Customer found for this id");
			} else {
				int foodId = (int) requestData.get("foodId");
				if(!fooddao.existsById(foodId)) {
					throw new  FoodNotFoundException("No Food found for this id");
				}

				else {
					Customer customer=customerdao.findById(customerId).get();
					Food food=fooddao.findById(foodId).get();
					int quantity=(int) requestData.get("quantity");
					String foodSize=(String) requestData.get("foodSize");
					cartItem.setCustomer(customer);
					cartItem.setFood(food);
					//cartItem.setFoodItem(fooditem);
					cartItem.setQuantity(quantity);
					System.out.println(cartItem.toString());
					System.out.println(customer.toString());
					System.out.println(food.toString());
					return itemdao.save(cartItem);
				}
				
			}
		}
}

		
		