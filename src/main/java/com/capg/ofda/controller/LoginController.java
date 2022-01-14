package com.capg.ofda.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.ofda.Exceptions.UserNotFoundException;
import com.capg.ofda.entities.Login;
import com.capg.ofda.service.ILogin;

/*Author:Leena
 * Version: 1.0
 * Date:11/01/2022
 * Description: This is Login Controller
 */

@RestController
@RequestMapping("/Login")
public class LoginController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ILogin iLog;
	
	/* Method: Add User
	 * Description: It allows to add new user.
	 * @RequestBody: It is used to bind HTTP request body with a domain object in method parameter or return type.
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	Created By: Leena
    * Created Date - 11-01-2022 
    */
	
	@PostMapping("/Register")
	public Login register(@RequestBody Login login) {
	
		return iLog.addNewUser(login);
	}
	
	/* Method: Login
	 * Description: It allows user to login.
	 * @RequestParam: extract query, parameters, form parameters, and even files from the request.
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
     */
	 
	
	@PostMapping("/{login}/{password}")
    public Login login(@PathVariable("login") String userName,@PathVariable("password") String password) throws UserNotFoundException  {
        System.out.println(userName+","+password);
        return iLog.signIn(userName, password);
    }
	
	/* Method: Login
	 * Description: It allows user to logout
	 */

@GetMapping("/Logout")
	public ResponseEntity<String> logout() {
		session.invalidate();
		return new ResponseEntity<String>("logged out",HttpStatus.OK);
	}
	
	}
	

