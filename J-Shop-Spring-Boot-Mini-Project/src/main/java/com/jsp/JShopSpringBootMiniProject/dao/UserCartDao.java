package com.jsp.JShopSpringBootMiniProject.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.JShopSpringBootMiniProject.dto.Product;
import com.jsp.JShopSpringBootMiniProject.dto.UserCart;
import com.jsp.JShopSpringBootMiniProject.repository.UsercartRepository;

@Repository
public class UserCartDao {

	@Autowired
	private UsercartRepository userRepository;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserCart userCart;
	
	@Autowired
	private UserDao userDao;
	
	
	// add product in user cart-------------------------------------------------------------------------------
	public UserCart insertProductDao(int productQuantity, int productId) {
		
		Product  product= productDao.getProductDataById(productId);
		
		if(product != null) {
			userCart.setProduct(product);
			userCart.setProductQuantity(productQuantity);
			userCart.setUser(userDao.getUser());
			
			System.out.println("userId = "+userDao.getUser().getUserId());
			
			return userRepository.save(userCart);
		}else {
			return null;
		}
		
	}
}
