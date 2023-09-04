package com.jsp.JShopSpringBootMiniProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.JShopSpringBootMiniProject.dto.UserCart;
import com.jsp.JShopSpringBootMiniProject.responseStructure.ResponseStructure;
import com.jsp.JShopSpringBootMiniProject.service.UserCartService;

@RestController
public class UserCartController {

	@Autowired
	private UserCartService cartService;
	
	// add product in user cart-------------------------------------------------------------------
	@PostMapping("/userCartInsert/{productQuantity}/{productId}")
	public ResponseStructure<UserCart> insertProduct(@PathVariable int productQuantity, @PathVariable int productId) {
		return cartService.insertProductService(productQuantity, productId);
	}	
}
