package com.jsp.JShopSpringBootMiniProject.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.JShopSpringBootMiniProject.dto.ProductOwner;
import com.jsp.JShopSpringBootMiniProject.repository.ProductOwnerRepository;
@Repository
public class ProductOwnerDao {

	@Autowired
	private ProductOwnerRepository ownerRepository;
	
	// insert ProductOwner----------------------------------------------------------------------
	public ProductOwner insertProductOwner(ProductOwner productOwner) {
		
		productOwner.setAdminVerify("no");
		return ownerRepository.save(productOwner);
	}
}
