package com.jsp.JShopSpringBootMiniProject.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.JShopSpringBootMiniProject.dto.User;
import com.jsp.JShopSpringBootMiniProject.repository.UserRepository;

@Repository
public class UserDao {

	@Autowired
	private UserRepository userRepository;
	
	// insert User-----------------------------------------------------------------------
	public User insertuser(User user) {
		return userRepository.save(user);
	}
}
