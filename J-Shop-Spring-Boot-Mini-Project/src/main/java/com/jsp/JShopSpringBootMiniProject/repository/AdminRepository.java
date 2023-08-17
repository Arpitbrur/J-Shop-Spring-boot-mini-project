package com.jsp.JShopSpringBootMiniProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.JShopSpringBootMiniProject.dto.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	//findByAdminEmail
	public Admin findByAdminEmail(String adminEmail);
}
