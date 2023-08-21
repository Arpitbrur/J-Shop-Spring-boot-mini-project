package com.jsp.JShopSpringBootMiniProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.JShopSpringBootMiniProject.dto.ProductOwner;
import com.jsp.JShopSpringBootMiniProject.responseStructure.ResponseStructure;
import com.jsp.JShopSpringBootMiniProject.service.ProductOwnerService;

@RestController
public class ProductOwnerController {

	@Autowired
	private ProductOwnerService ownerService;
	
	// insert ProductOwner----------------------------------------------------------------------
	@PostMapping("/registerProductOwner")
	public ResponseStructure<ProductOwner> insertProductOwner(@RequestBody ProductOwner productOwner) {
		return ownerService.insertProductOwner(productOwner);
	}
	
	// getProductOwnerByEmail-------------------------------------------------------------------
	@GetMapping("/loginWithEmail/{email}/{password}")
	public ResponseStructure<ProductOwner> loginWithProductOwner(@PathVariable String email,@PathVariable String password) {
		return ownerService.loginWithProductOwner(email, password);
	}	
}
