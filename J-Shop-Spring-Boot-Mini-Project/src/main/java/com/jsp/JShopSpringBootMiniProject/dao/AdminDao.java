package com.jsp.JShopSpringBootMiniProject.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.JShopSpringBootMiniProject.dto.Admin;
import com.jsp.JShopSpringBootMiniProject.dto.ProductOwner;
import com.jsp.JShopSpringBootMiniProject.repository.AdminRepository;
import com.jsp.JShopSpringBootMiniProject.repository.ProductOwnerRepository;

/**
 * 
 * @author ARPIT BRUR
 *
 */

@Repository
public class AdminDao {
	
	int adminId =0;

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private ProductOwnerDao ownerDao;
	
	@Autowired
	private ProductOwnerRepository ownerRepository;
	
	//Sign up code for insert Admin----------------------------------------------------------
	public Admin insertAdmin(Admin admin) {
		return adminRepository.save(admin);
	}
	
	// loginByAdminEmail---------------------------------------------------------------------
	public Admin loginByEmail(String adminEmail) {
	 Admin admin= adminRepository.findByAdminEmail(adminEmail);
	 
	 if(admin!=null) {
		 adminId = admin.getAdminId();
	 }
	 return admin;
	}
	
	// getAllProductOwnerAdmin-----------------------------------------------------------------
	public List<ProductOwner> getAllProductOwnerAdmin() {
		return ownerDao.getAllProductOwner();
	}
	
	//getProductOwnerByIdAdmin-----------------------------------------------------------------
	public ProductOwner getProductOwnerByIdAdmin(int productOwnerId) {
		return ownerDao.getProductOwnerById(productOwnerId);
	}
	
	// Verify Product Owner from no to yes  and UnVarified from yes to no----------------------
	public ProductOwner verifyProductOwner(int productOwnerId) {
		
		ProductOwner productOwner = getProductOwnerByIdAdmin(productOwnerId);
		
		Optional<Admin> optional =adminRepository.findById(adminId);
		Admin admin = null;
		
		if(optional.isPresent()) {
			admin = optional.get();
		}
		
		if(productOwner != null) {
			if(productOwner.getAdminVerify().equalsIgnoreCase("no")) {
				productOwner.setAdminVerify("yes");
				productOwner.setAdmins(admin);
			}else {
				admin = null;
				productOwner.setAdmins(admin);
				productOwner.setAdminVerify("no");
			}
			ownerRepository.save(productOwner);
		}
		return productOwner;
	
	}
}
