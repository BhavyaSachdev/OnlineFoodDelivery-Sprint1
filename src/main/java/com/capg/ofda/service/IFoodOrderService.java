package com.capg.ofda.service;

import java.util.List;

import com.capg.ofda.Exceptions.CartNotFoundException;
import com.capg.ofda.Exceptions.CustomerNotFoundException;
import com.capg.ofda.Exceptions.OrderNotFoundException;
import com.capg.ofda.entities.Order;

public interface IFoodOrderService {
	
Order bookOrderInfo(int cartId,int customerId)throws CartNotFoundException,CustomerNotFoundException;
	
	Order updateOrder(Order ord); //done

	String cancelOrder(int orderId) throws OrderNotFoundException; //done

    Iterable<Order> listAllOrder();//done

    List<Order> viewOrder(int customerId) throws CustomerNotFoundException; //done

	
    
    
    
}


