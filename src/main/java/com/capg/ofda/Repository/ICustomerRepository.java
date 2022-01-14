package com.capg.ofda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.ofda.entities.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Integer> {
	

}