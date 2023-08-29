package com.jsp.JShopSpringBootMiniProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.JShopSpringBootMiniProject.dto.User;
import com.jsp.JShopSpringBootMiniProject.responseStructure.ResponseStructure;
import com.jsp.JShopSpringBootMiniProject.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	// insert User-----------------------------------------------------------------------
	@PostMapping("/saveUser")
	public ResponseStructure<User> insertuser(@RequestBody User user) {
		return userService.insertuser(user);
	}	
}
