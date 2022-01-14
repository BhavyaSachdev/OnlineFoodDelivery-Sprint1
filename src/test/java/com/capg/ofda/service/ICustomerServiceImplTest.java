package com.capg.ofda.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
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

import com.capg.ofda.Exceptions.CustomerNotFoundException;
import com.capg.ofda.Repository.ICustomerRepository;
import com.capg.ofda.entities.Customer;



@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ICustomerServiceImplTest {
	@InjectMocks
	ICustomerServiceImpl service;
	@Mock
	ICustomerRepository dao;
	
	
	@Test
	public void addCustomerTest() {
		Customer cust = new Customer();
		cust.setCustomerId(1);
		cust.setCustomerName("sonal");
		cust.setCustomerMobile((long) 999999);
		cust.setCustomerAddress("Pune");
		cust.setCustomerEmail("sonal@capg.com");
		cust.setUserName("sonal");
		cust.setPassword("sonal123");
		when(service.addCustomer(cust)).thenReturn(cust);

	}
	
	
	@Test
	public void deleteCustomer() throws CustomerNotFoundException {

		Customer cust = new Customer();
		cust.setCustomerId(2);
		Mockito.when(dao.existsById(cust.getCustomerId())).thenReturn(true);
		service.deleteCustomer(cust.getCustomerId());
		verify(dao, Mockito.atLeastOnce()).deleteById(2);
	}
	

	@Test
	public void viewCustomer() {
		List<Customer> customer = new ArrayList<Customer>();
		Customer cust = new Customer();
		Customer cobj = new Customer();
		cobj.setCustomerId(1);
		cobj.setCustomerName("bani");
		cobj.setCustomerMobile((long) 999999);
		cobj.setCustomerAddress("kharag");
		cobj.setCustomerEmail("abc@gmail");
		cobj.setUserName("bani");
		cobj.setPassword("123");
		customer.add(cobj);
		cust.setCustomerName("bidya");
		cust.setCustomerId(2);
		cust.setCustomerAddress("inda");
		cust.setCustomerEmail("xyz@gmail.com");
		cust.setPassword("bidya");
		cust.setUserName("456");
		cust.setCustomerMobile((long) 77767788);
		customer.add(cust);
		when(service.viewCustomers()).thenReturn(customer);
		assertEquals(2, service.viewCustomers().size());
	}
	
	
	@Test
	public void viewCostomerById() throws CustomerNotFoundException {

		List<Customer> cust = new ArrayList<Customer>();
		Customer cobj = new Customer();
		cobj.setCustomerId(1);
		cobj.setCustomerName("bani");
		cobj.setCustomerMobile((long) 999999);
		cobj.setCustomerAddress("kharag");
		cobj.setCustomerEmail("abc@gmail");
		cobj.setUserName("bani");
		cobj.setPassword("123");
		cust.add(cobj);
		Mockito.when(dao.existsById(1)).thenReturn(true);
		Mockito.when(dao.findById(1)).thenReturn(Optional.of(cobj));
		assertEquals(cobj.getCustomerId(), service.viewCustomer(1).get().getCustomerId());
	}
	
	
	@Test
	void updateCustomer() throws CustomerNotFoundException {
		Customer cust = new Customer();
		cust.setCustomerId(1);
		cust.setCustomerName("bani");
		cust.setCustomerMobile((long) 999999);
		cust.setCustomerAddress("kharag");
		cust.setCustomerEmail("abc@gmail");
		cust.setUserName("bani");
		cust.setPassword("123");
		when(dao.existsById(cust.getCustomerId())).thenReturn(true);
		cust.setCustomerId(1);
		cust.setCustomerName("banik");
		cust.setCustomerMobile((long) 999999);
		cust.setCustomerAddress("kharag");
		cust.setCustomerEmail("abc@gmail");
		cust.setUserName("bani");
		cust.setPassword("123");
		when(service.updateCustomer(cust)).thenReturn(cust);
		assertEquals("banik", cust.getCustomerName());

	}
}
