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

import com.capg.ofda.Exceptions.CustomerNotFoundException;
import com.capg.ofda.entities.Customer;
import com.capg.ofda.service.ICustomerServiceImpl;

/*Controller Class For Customer Management
 * Author : Sonal Ravindra Mule
 * Date Created : 12/01/2022
 *
 */

@RestController
@RequestMapping("/customer")
public class ICustomerController {
	
	@Autowired
	private ICustomerServiceImpl customerService;
	static final Logger LOGGER = LoggerFactory.getLogger(ICustomerController.class);
	
	/***********************************************************************************************/
	// Create/Add Customer
	
	@PostMapping("/insert")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
		LOGGER.info("Insert Customer");
		Customer cust=customerService.addCustomer(customer);
		if(cust==null){
		return new ResponseEntity("Customer Not Found!!",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity <Customer>(cust ,HttpStatus.OK);
	}

	/***********************************************************************************************/
	// Get All Customers List

	@GetMapping("/retrieve")
	public ResponseEntity<List<Customer>> viewCustomers(){
		LOGGER.info("Retrieve Customer");
		System.out.println("List of all Customers ");
		List<Customer> cust=customerService.viewCustomers();
		if(cust.isEmpty()) {
			return new ResponseEntity("Customer Not Found!!",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Customer>>(cust, HttpStatus.OK);
	}
	
	/**********************************************************************************************/
	// Updating Customer List

	@PutMapping("/edit")
	public ResponseEntity<Object> editCustomer(@RequestBody Customer customer) throws CustomerNotFoundException{
		LOGGER.info("Edit Customer Details");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			Customer p=customerService.updateCustomer(customer);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(CustomerNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}
}
	
	/**********************************************************************************************/
	// Delete Customer

	@DeleteMapping("/DeleteCustomer/{id}")
	public ResponseEntity<Object> deleteCustomer(@PathVariable("id") int customerId) throws CustomerNotFoundException {
		LOGGER.info("Delete Customer by id");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			String p=customerService.deleteCustomer(customerId);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(CustomerNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}
}
	
	/***********************************************************************************************/
	// Get Particular Customer Data

	@GetMapping("/viewonecustomer/{id}")
	public ResponseEntity<Object> viewCutomer(@PathVariable("id") int customerId) throws CustomerNotFoundException{
		LOGGER.info("View one Customer by id");
		System.out.println("Customer info ");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			Optional<Customer> p=customerService.viewCustomer(customerId);
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
