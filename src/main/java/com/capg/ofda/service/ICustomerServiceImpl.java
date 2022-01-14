package com.capg.ofda.service;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.ofda.Exceptions.CustomerNotFoundException;
import com.capg.ofda.Repository.ICustomerRepository;
import com.capg.ofda.entities.Customer;


@Service
public class ICustomerServiceImpl implements ICustomerService {
	
	@Autowired
	ICustomerRepository customerRepo;


	@Override
	public Customer addCustomer(Customer customer) {
				
			Customer cust=customerRepo.save(customer);
			
			return cust;
	}


	@Override
	public Customer updateCustomer(Customer customer)throws CustomerNotFoundException{
		boolean b=customerRepo.existsById(customer.getCustomerId());
		if(!b) {
			throw new  CustomerNotFoundException("No Customer found for this id");
		}
		else {
			
			
			return customerRepo.save(customer);
		}
	}

	@Override
	public String deleteCustomer(int customerId) throws CustomerNotFoundException {
		boolean b=customerRepo.existsById(customerId);
		if(!b) {
			return "No Customer found for this id";
		}
		else {
			customerRepo.deleteById(customerId);
			
			return "Deleted Customer Successfully!";
		}
	}

	@Override
	public List<Customer> viewCustomers() {
		List<Customer> customer=customerRepo.findAll();
		return customer;
	}

	@Override
	public Optional<Customer> viewCustomer(int customerId) throws CustomerNotFoundException {
		
		return customerRepo.findById(customerId);
	}

}
