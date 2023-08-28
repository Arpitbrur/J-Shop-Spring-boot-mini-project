package com.jsp.JShopSpringBootMiniProject.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.JShopSpringBootMiniProject.dto.Product;
import com.jsp.JShopSpringBootMiniProject.repository.ProductRepository;

@Repository
public class ProductDao {

	@Autowired
	private ProductRepository productRepository;
	
	// insert Product-------------------------------------------------------------------------------
	public Product insertProduct(Product product) {
		return productRepository.save(product);
	}
	
}
