package com.capg.ofda.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.ofda.entities.Cart;
import com.capg.ofda.entities.Customer;
import com.capg.ofda.entities.Order;


@Repository

public interface IFoodOrderRepository extends JpaRepository<Order,Integer> {

	public List<Order>  findByCustomer(Optional<Customer> byId);
	
	public List<Cart>  findByCart(Cart cart);
	
	public String deleteById(int orderId);
}