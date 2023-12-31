package com.jsp.JShopSpringBootMiniProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.JShopSpringBootMiniProject.dto.Admin;
import com.jsp.JShopSpringBootMiniProject.dto.Product;
import com.jsp.JShopSpringBootMiniProject.dto.ProductOwner;
import com.jsp.JShopSpringBootMiniProject.responseStructure.ResponseStructure;
import com.jsp.JShopSpringBootMiniProject.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;
	
//	@Autowired
//	private ResponseStructure<Admin> responseStructure;
//	
	// insert Admin--------------------------------------------------------------------------
	@PostMapping("/loginAdmin")
	public ResponseStructure<Admin> insertAdmin(@RequestBody Admin admin) {
		return adminService.insertAdmin(admin);
	}
	
	// LoginByAdminEmail---------------------------------------------------------------------
	@GetMapping("/loginAdmin/{adminEmail}/{password}")
	public ResponseStructure<Admin> loginByEmail(@PathVariable String adminEmail,@PathVariable String password) {
		return adminService.loginByEmail(adminEmail, password);
	}
	
	// getAllProductOwnerAdmin-----------------------------------------------------------------
	@GetMapping("/getAllProductOwner")
	public ResponseStructure<List<ProductOwner>> getAllProductOwnerAdmin() {
		return adminService.getAllProductOwnerAdmin();
	}	
	
	//getProductOwnerByIdAdmin-----------------------------------------------------------------
	@GetMapping("/getproductOwnerById/{productOwnerId}")
	public ResponseStructure<ProductOwner> getProductOwnerByIdAdmin(@PathVariable int productOwnerId) {
		return adminService.getProductOwnerByIdAdmin(productOwnerId);
	}	
	
	// Verify Product Owner from no to yes  and UnVarified from yes to no----------------------
	@PutMapping("/verifyProductOwner/{productOwnerId}")
	public ResponseStructure<ProductOwner> verifyProductOwner(@PathVariable int productOwnerId) {
		return adminService.verifyProductOwner(productOwnerId);
	}	
	
	// verify Product details By id-----------------------------------------------------------------
	@PutMapping("/verifyProductDetailsByAdmin/{productId}")
	public ResponseStructure<Product> verifyProductDetailsByAdmin(@PathVariable int productId) {
		return adminService.verifyProductDetailsByAdmin(productId);
	}	
}
