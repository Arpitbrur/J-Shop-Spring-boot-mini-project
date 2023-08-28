package com.jsp.JShopSpringBootMiniProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jsp.JShopSpringBootMiniProject.dao.ProductDao;
import com.jsp.JShopSpringBootMiniProject.dao.ProductOwnerDao;
import com.jsp.JShopSpringBootMiniProject.dto.Product;
import com.jsp.JShopSpringBootMiniProject.dto.ProductOwner;
import com.jsp.JShopSpringBootMiniProject.responseStructure.ResponseStructure;

import jakarta.servlet.http.HttpSession;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private ProductOwnerDao ownerDao;
	
	@Autowired
	private ResponseStructure<Product> responseStructure;
	
	// insert Product-------------------------------------------------------------------------------
	public ResponseStructure<Product> insertProduct(Product product) {
		if(httpSession.getAttribute("email") != null) {
			
			ProductOwner productOwner = ownerDao.getProductOwnerById(ownerDao.getProductOwnerId());
			
			if(productOwner != null) {
				product.setProductOwner(productOwner);
			}
			product.setProductVerified("no");
			
			Product product1 = productDao.insertProduct(product);
			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure.setMsg("Product Addedd");
			responseStructure.setDescription("Now your product is added....your login session is valid for 20 seconds");
			responseStructure.setData(product1);
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setMsg("Your session is out ....please login");
			responseStructure.setDescription("only product owner can add product details if he/she is logged in");
			responseStructure.setData(null);
		}
		return responseStructure;
			
	}	
}
