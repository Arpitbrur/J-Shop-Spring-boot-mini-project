package com.jsp.JShopSpringBootMiniProject.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.JShopSpringBootMiniProject.dto.ProductOwner;
import com.jsp.JShopSpringBootMiniProject.repository.ProductOwnerRepository;
@Repository
public class ProductOwnerDao {

	int productOwnerId=0;
	
	public int getProductOwnerId() {
		return productOwnerId;
	}

	public void setProductOwnerId(int productOwnerId) {
		this.productOwnerId = productOwnerId;
	}
	
	@Autowired
	private ProductOwnerRepository ownerRepository;
	
	// insert ProductOwner----------------------------------------------------------------------
	public ProductOwner insertProductOwner(ProductOwner productOwner) {
		
		productOwner.setAdminVerify("no");
		return ownerRepository.save(productOwner);
	}
	
	// getProductOwnerByEmail-------------------------------------------------------------------
	public ProductOwner getProductOwnerEmail(String email) {
		ProductOwner owner = ownerRepository.findByProductOwnerEmail(email);
		
		if(owner != null) {
			setProductOwnerId(owner.getProductOwnerId());
		}
		return owner;
	}
	
	//getAllProductOwner--------------------------------------------------------------------------
	public List<ProductOwner> getAllProductOwner(){
		return ownerRepository.findAll();
	}
	
	//getProductOwnerById
	public ProductOwner getProductOwnerById(int productOwnerId) {
		Optional<ProductOwner> optional = ownerRepository.findById(productOwnerId);
		
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
		
	}
}
