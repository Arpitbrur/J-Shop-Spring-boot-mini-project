package com.jsp.JShopSpringBootMiniProject.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.JShopSpringBootMiniProject.dto.User;
import com.jsp.JShopSpringBootMiniProject.repository.UserRepository;

@Repository
public class UserDao {

	@Autowired
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Autowired
	private UserRepository userRepository;
	
	// insert User-----------------------------------------------------------------------
	public User insertuser(User user) {
		return userRepository.save(user);
	}
	
	// login User----------------------------------------------------------------------
	public User loginUser(String email)
	{
		User user = userRepository.findByUserEmail(email);
		if(user!=null) {
			setUser(user);
			return user;
		}
		return null;
	}
}
