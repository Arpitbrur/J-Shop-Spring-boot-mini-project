package com.jsp.JShopSpringBootMiniProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.JShopSpringBootMiniProject.dto.UserCart;

public interface UsercartRepository extends JpaRepository<UserCart, Integer>{

}
