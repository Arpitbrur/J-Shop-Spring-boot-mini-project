package com.jsp.JShopSpringBootMiniProject.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.JShopSpringBootMiniProject.dto.Admin;
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
	
	// insert Admin--------------------------------------------------------------------------
	public Admin insertAdmin(Admin admin) {
		return adminRepository.save(admin);
	}
	
	// findByAdminEmail---------------------------------------------------------------------
	public Admin loginByEmail(String adminEmail) {
		return adminRepository.findByAdminEmail(adminEmail);
	}
}
