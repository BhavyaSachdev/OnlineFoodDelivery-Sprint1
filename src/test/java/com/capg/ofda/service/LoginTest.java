package com.capg.ofda.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.ofda.Exceptions.UserNotFoundException;
import com.capg.ofda.Repository.loginRepository;
import com.capg.ofda.entities.Login;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class LoginTest {
	
	@InjectMocks
	ILogin service;
	@Mock
	loginRepository dao;
	@Test
	public void addUser()
	{
		Login login = new Login();
		login.setUserid("1");
		when(service.addNewUser(login)).thenReturn(login);
	}
	
	@Test
	public void loginn() throws UserNotFoundException
	{
		Login login = new Login();
		login.setUserid("1");
		login.setUserName("leena");
		login.setPassword("leena123");
		when(dao.existsById("1")).thenReturn(true);
		Login user1 = new Login();
		user1.setUserid("1");
		user1.setUserName("leena");
		user1.setPassword("lee123");	
		assertEquals(login.getUserid(), "1");
		assertNotEquals(service.signIn("leena", "lee123"), "Logged In SuccessFully");
		
		
	}

}
