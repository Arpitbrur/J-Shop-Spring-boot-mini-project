package com.jsp.JShopSpringBootMiniProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.JShopSpringBootMiniProject.dto.Product;
import com.jsp.JShopSpringBootMiniProject.responseStructure.ResponseStructure;
import com.jsp.JShopSpringBootMiniProject.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	// insert Product-------------------------------------------------------------------------------
	@PostMapping("/saveProduct")
	public ResponseStructure<Product> insertProduct(@RequestBody Product product) {
		return productService.insertProduct(product);
	}	
}
