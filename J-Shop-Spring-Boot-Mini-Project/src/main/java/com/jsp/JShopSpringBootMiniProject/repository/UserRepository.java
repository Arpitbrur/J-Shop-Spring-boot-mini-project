package com.jsp.JShopSpringBootMiniProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.JShopSpringBootMiniProject.dto.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUserEmail(String userEmail);

}
