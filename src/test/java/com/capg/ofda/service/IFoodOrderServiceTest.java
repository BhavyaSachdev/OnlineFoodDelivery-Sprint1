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

import com.capg.ofda.Exceptions.CartNotFoundException;
import com.capg.ofda.Exceptions.OrderNotFoundException;
import com.capg.ofda.Repository.IFoodOrderRepository;
import com.capg.ofda.entities.Cart;
import com.capg.ofda.entities.Customer;
import com.capg.ofda.entities.Order;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class IFoodOrderServiceTest {
	
	@InjectMocks
	public IFoodOrderServiceImpl service;
	
	@Mock
	public IFoodOrderRepository repo;
	
	@Mock
	public ICartServiceImpl cartrepo;
	
	@Mock
	public ICustomerServiceImpl custrepo;
	
	
	@Test
	public void listAllOrder() {
		
		List<Order> order=new ArrayList<Order>();
		Customer cust=new Customer();
		Order ord=new Order();
		Cart cart=new Cart();
		ord.setOrderId(101);
		ord.setFinalPrice(2000.0);
		ord.setOrderStatus("Booked");
		cust.setCustomerId(200);
		ord.setCustomer(cust);
		cart.setCartId(200);
		ord.setCart(cart);
		order.add(ord);
		
		when(service.listAllOrder()).thenReturn(order);
		assertEquals(1,service.listAllOrder().size());	
	}
	
	@Test
	public void cancelOrder() throws OrderNotFoundException {
	
		
	   
	 when(repo.existsById(100)).thenReturn(true);
	 Mockito.when(service.cancelOrder(100)).thenReturn("Order Deleted Successfully");
  	
	}	
	
	
	@Test
	public void updateOrder() throws OrderNotFoundException
	{
		Customer customer=new Customer();
		customer.setCustomerId(200);
		Cart cart=new Cart();
		cart.setCartId(200);
		Order order=new Order();
		order.setOrderId(101);
		order.setFinalPrice(2000.0);
		order.setOrderStatus("Booked");
       
		order.setCustomer(customer);
		
		order.setCart(cart);
		
		when(repo.existsById(order.getOrderId())).thenReturn(true);
		
		when(service.updateOrder(order)).thenReturn(order);
		
		assertEquals("Booked", order.getOrderStatus());
		
	}
	
	
	@Test
	public void bookOrderInfo() throws CartNotFoundException
	{
		
		Order order=new Order();
		Customer customer=new Customer();
		customer.setCustomerId(200);
		Cart cart=new Cart();
		cart.setCartId(100);
		order.setCustomer(customer);
		order.setCart(cart);
		order.setOrderStatus("Booked");
		repo.save(order);
		
		when(repo.existsById(cart.getCartId())).thenReturn(true);
		
		assertEquals("Booked", order.getOrderStatus());
		
		
	}
	
	
	   @Test
	   public void viewOrder() throws OrderNotFoundException
	   {
		   Order order=new Order();
		   Customer customer=new Customer();
		   customer.setCustomerId(202);
		   order.setOrderId(101);
		   order.setCustomer(customer);
		   
		   
		  Mockito.when(repo.existsById(101)).thenReturn(true); 
		  
		  repo.save(order);
		  
		  Mockito.when(repo.findById(101)).thenReturn(Optional.of(order));   
		   
		
	   }
}