package com.jsp.JShopSpringBootMiniProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jsp.JShopSpringBootMiniProject.dao.ProductOwnerDao;
import com.jsp.JShopSpringBootMiniProject.dto.ProductOwner;
import com.jsp.JShopSpringBootMiniProject.responseStructure.ResponseStructure;

@Service
public class ProductOwnerService {

	// to call the method of productOwnerDao class
	@Autowired
	private ProductOwnerDao productOwnerDao;
	
	// to display some message on postman API
	@Autowired
	private ResponseStructure<ProductOwner> responseStructure;
	
	// to call validatePassword() and vallidateEmail() method from adminService class
	@Autowired
	private AdminService adminService;
	
	// insert ProductOwner----------------------------------------------------------------------
	public ResponseStructure<ProductOwner> insertProductOwner(ProductOwner productOwner) {
		String email = adminService.validationEmail(productOwner.getProductOwnerEmail());
		String password = adminService.validationPassword(productOwner.getProductOwnerPassword());
		
		
		if(email != null) {
			if(password != null) {
				ProductOwner owner = productOwnerDao.insertProductOwner(productOwner);
				responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructure.setMsg("product-owner register Successfully");
				responseStructure.setDescription("Congratulation please login");
				responseStructure.setData(owner);
			}else {
				responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure.setMsg("Check------your------password");
				responseStructure.setDescription("your password should be 8 character alongwith 1 lowerCase, 1 upperCase, special character(!@#$*&%), 1 number");
				responseStructure.setData(null);
			}
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setMsg("Check------your------Email");
			responseStructure.setDescription("your email should contain atleast alphabets number @hfh.com");
			responseStructure.setData(null);
		}
		return responseStructure;
	}	
}
