package com.capg.ofda.service;

import java.util.List;
import java.util.Optional;

import com.capg.ofda.Exceptions.CustomerNotFoundException;
import com.capg.ofda.entities.Customer;


public interface ICustomerService {
	Customer addCustomer(Customer customer);
	
	Customer updateCustomer(Customer customer) throws CustomerNotFoundException;

	String deleteCustomer(int customerId) throws CustomerNotFoundException;

	List<Customer> viewCustomers();

	Optional<Customer> viewCustomer(int customerId) throws CustomerNotFoundException;
}

	