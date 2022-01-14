package com.capg.ofda.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.ofda.Exceptions.UserNotFoundException;
import com.capg.ofda.Repository.loginRepository;
import com.capg.ofda.entities.Login;

@Service
@Transactional

public class ILogin {
	
	@Autowired
	loginRepository LoginRepo;
	
	public Login addNewUser(Login user) {
		
		LoginRepo.save(user);
		return user;
	}
	
	
public Login signIn(String userName,String password) throws UserNotFoundException {
	if(!LoginRepo.existsById(userName))
		return null;
	Login user1=LoginRepo.findById(userName).orElse(null);
	if(!user1.getPassword().equals(password))
		return null;
	return user1;
}
}


