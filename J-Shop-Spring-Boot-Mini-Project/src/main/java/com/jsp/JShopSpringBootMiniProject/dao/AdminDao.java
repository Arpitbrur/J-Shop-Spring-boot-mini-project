package com.jsp.JShopSpringBootMiniProject.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.JShopSpringBootMiniProject.dto.Admin;
import com.jsp.JShopSpringBootMiniProject.dto.ProductOwner;
import com.jsp.JShopSpringBootMiniProject.repository.AdminRepository;

/**
 * 
 * @author ARPIT BRUR
 *
 */

@Repository
public class AdminDao {

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private ProductOwnerDao ownerDao;
	
	// insert Admin--------------------------------------------------------------------------
	public Admin insertAdmin(Admin admin) {
		return adminRepository.save(admin);
	}
	
	// findByAdminEmail---------------------------------------------------------------------
	public Admin loginByEmail(String adminEmail) {
		return adminRepository.findByAdminEmail(adminEmail);
	}
	
	// getAllProductOwnerAdmin-----------------------------------------------------------------
	public List<ProductOwner> getAllProductOwnerAdmin() {
		return ownerDao.getAllProductOwner();
	}
	
	//getProductOwnerByIdAdmin-----------------------------------------------------------------
	public ProductOwner getProductOwnerByIdAdmin(int productOwnerId) {
		return ownerDao.getProductOwnerById(productOwnerId);
	}	
}
