package com.jsp.JShopSpringBootMiniProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.JShopSpringBootMiniProject.dto.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
