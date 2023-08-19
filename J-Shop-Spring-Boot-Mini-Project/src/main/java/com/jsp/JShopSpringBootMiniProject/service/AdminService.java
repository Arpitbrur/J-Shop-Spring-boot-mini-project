package com.jsp.JShopSpringBootMiniProject.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jsp.JShopSpringBootMiniProject.dao.AdminDao;
import com.jsp.JShopSpringBootMiniProject.dto.Admin;
import com.jsp.JShopSpringBootMiniProject.responseStructure.ResponseStructure;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private ResponseStructure<Admin> responseStructure;
	
	@Autowired
	private HttpSession httpSession;
	
	//Email Validation---------------------------------------------------------------------
	public String validationEmail(String email) {
		
		Pattern alphabets = Pattern.compile("[a-z]");
		Pattern numbers = Pattern.compile("[0-9]");
		Pattern special = Pattern.compile("[@.]");
		
		Matcher alpha = alphabets.matcher(email);
		Matcher num = numbers.matcher(email);
		Matcher spec = special.matcher(email);
		if((alpha.find()) && (num.find()) && (spec.find())){
			
			return email;
		}else {
			return null;
		}
		
	}
	// Password validation-----------------------------------------------------------------
	public String validationPassword(String password) {
		
		if(password.length() == 8) {
			Pattern alphabets = Pattern.compile("[a-zA-Z]");
			Pattern numbers = Pattern.compile("[0-9]");
			Pattern special = Pattern.compile("[!@#$*&%]");
			
			Matcher alpha = alphabets.matcher(password);
			Matcher num = numbers.matcher(password);
			Matcher spec = special.matcher(password);
			
			if((alpha.find()) && (num.find()) && (spec.find())){
				
				return password;
			}else {
				return null;
			}
		}else {
			return null;
		}
		
	}
	
	// insert Admin--------------------------------------------------------------------------
	public ResponseStructure<Admin> insertAdmin(Admin admin) {
		
		String password = validationPassword(admin.getAdminPassword());
		String email = validationEmail(admin.getAdminEmail());
		if(email != null) {
			
		if(password != null) {
			admin.setAdminPassword(password);
			admin.setAdminEmail(email);
			Admin admin1 = adminDao.insertAdmin(admin);
			
			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure.setMsg("Admin-Registered Successfully");
			responseStructure.setData(admin1);
			return responseStructure;
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setMsg("check your password ");
			responseStructure.setData(null);
			return responseStructure;
		}
		
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setMsg("Check your email");
			responseStructure.setData(null);
			return responseStructure;
		}
	}
	
	// LoginByAdminEmail---------------------------------------------------------------------
	public ResponseStructure<Admin> loginByEmail(String adminEmail, String password) {
		Admin admin = adminDao.loginByEmail(adminEmail);
		
		if(admin != null) {
			
			if(admin.getAdminPassword().equals(password) && (admin.getAdminEmail().equals(adminEmail))) {
				httpSession.setAttribute("password", admin.getAdminPassword());
				httpSession.setMaxInactiveInterval(120);
				
				responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructure.setMsg("login successfully");
				responseStructure.setDescription("Congratulation please login");
				responseStructure.setData(admin);
				return responseStructure;
			
			}else {
				responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
				responseStructure.setMsg("not login please check your password");
				responseStructure.setDescription("your password should be 8 character alongwith 1 lowerCase, 1 upperCase, special character(!@#$*&%), 1 number");
				responseStructure.setData(null);
				return responseStructure;
			}
			
	   }else {
		   responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		   responseStructure.setMsg("not login please check your email");
		   responseStructure.setDescription("your email should contain atleast alphabets number @hfh.com");
		   responseStructure.setData(null);
		   return responseStructure;
	   }
		
		
	}	
}
