package com.capg.ofda.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.ofda.Exceptions.CartNotFoundException;
import com.capg.ofda.Exceptions.CustomerNotFoundException;
import com.capg.ofda.Exceptions.OrderNotFoundException;
import com.capg.ofda.Repository.ICartRepositoryDao;
import com.capg.ofda.Repository.ICustomerRepository;
import com.capg.ofda.Repository.IFoodOrderRepository;
import com.capg.ofda.entities.Cart;
import com.capg.ofda.entities.Customer;
import com.capg.ofda.entities.Order;


@Service
@Transactional

public class IFoodOrderServiceImpl implements IFoodOrderService{
	
	@Autowired
		IFoodOrderRepository repo;

		@Autowired
		ICustomerRepository custdao;

		@Autowired
		ICartRepositoryDao cartrepo;
		

	public List<Order> viewOrder(int customerId) throws CustomerNotFoundException {
			Optional<Customer> byId = custdao.findById(customerId);
			
			if (!byId.isPresent()) {
				throw new CustomerNotFoundException("Wrong Customer Id");
			} else {
					List<Order> ord = repo.findByCustomer(byId);
					double finalprice=0.0;
					for(int i=0;i<ord.size();i++) {
					double finalPrice = (ord.get(i).getCart().getTotal());//- (ord.get(i));
					System.out.println(finalPrice);
					ord.get(i).setFinalPrice(finalPrice);
					repo.save(ord.get(i));
					}
					return repo. findByCustomer(byId);
				
			}
		}

		
		
		// Returns a list of all the orders
		@Override
		public List<Order> listAllOrder() {
			System.out.println("List all orders in service layers");
			List<Order> list = repo.findAll();
			return list;
		}

		// Customer can Update His Order
		@Override
		public Order updateOrder(Order ord) throws OrderNotFoundException {
			boolean b = repo.existsById(ord.getOrderId());
			if (!b) {
				throw new OrderNotFoundException();
			} else
				System.out.println("Update Order Successfully");
			return repo.save(ord);
		}

			
		// Customer can Delete The Particular Order
		public String cancelOrder(int orderid) throws OrderNotFoundException {
			if (!repo.existsById(orderid))
				throw new OrderNotFoundException();
			else
				repo.deleteById(orderid);

			return "Order Deleted Successfully";
		}

			
		
		public Order bookOrderInfo(int cartId, int customerId) throws CartNotFoundException,CustomerNotFoundException{
			Optional<Customer> byId = custdao.findById(customerId);

			Optional<Cart> cartIde = cartrepo.findById(cartId);
			
			Order ord = new Order();
			if (!cartIde.isPresent()) {
	     		throw new CartNotFoundException("Cart not found");
			} 
			else if(!byId.isPresent()) {
				throw new CustomerNotFoundException("Customer Id Not Found");
			}
			
			else {
				ord.setOrderStatus("Booked");
				ord.setCustomer(byId.get());
				ord.setCart(cartIde.get());
				repo.save(ord);
				return ord;
			}

		}

	}



