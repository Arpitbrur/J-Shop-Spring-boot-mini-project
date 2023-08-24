package com.jsp.JShopSpringBootMiniProject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jsp.JShopSpringBootMiniProject.dao.AdminDao;
import com.jsp.JShopSpringBootMiniProject.dto.Admin;
import com.jsp.JShopSpringBootMiniProject.dto.ProductOwner;
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
	
	@Autowired
	private ResponseStructure<ProductOwner> responseStructure2;
	
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
				httpSession.setMaxInactiveInterval(20);
				
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
	
	// getAllProductOwnerAdmin-----------------------------------------------------------------
	public List<ProductOwner> getAllProductOwnerAdmin() {
		List<ProductOwner> owners = new ArrayList<ProductOwner>();
		
		for (ProductOwner productOwner : adminDao.getAllProductOwnerAdmin()) {
			if(productOwner.getAdminVerify().equalsIgnoreCase("no")) {
				owners.add(productOwner);
			}
		}
		return owners;
	}
	
	//getProductOwnerByIdAdmin-----------------------------------------------------------------
	public ResponseStructure<ProductOwner> getProductOwnerByIdAdmin(int productOwnerId) {
		
		ProductOwner owner= adminDao.getProductOwnerByIdAdmin(productOwnerId);
		if(owner != null) {
			if(httpSession.getAttribute("password")!= null){
//				httpSession.setMaxInactiveInterval(20);
				responseStructure2.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructure2.setMsg("Successfully");
				responseStructure2.setDescription("please find your data");
				responseStructure2.setData(owner);
			}else {
				responseStructure2.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure2.setMsg("your session is logout");
				responseStructure2.setDescription("please login again");
				responseStructure2.setData(null);
			}
		}else {
			responseStructure2.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure2.setMsg("productOwner is not present");
			responseStructure2.setDescription("##########################################");
			responseStructure2.setData(null);
		}
		return responseStructure2;
	}	
	
	// Verify Product Owner from no to yes  and UnVarified from yes to no----------------------
	public ResponseStructure<ProductOwner> verifyProductOwner(int productOwnerId) {
		
		ProductOwner productOwner = adminDao.getProductOwnerByIdAdmin(productOwnerId);
		
		if(productOwner != null) {
			if(httpSession.getAttribute("password") != null) {
				ProductOwner owner = adminDao.verifyProductOwner(productOwnerId);
				
				responseStructure2.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructure2.setMsg("Success");
				responseStructure2.setDescription("product-Owner is verfied Succefully");
				responseStructure2.setData(owner);
				
			}else {
				responseStructure2.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure2.setMsg("Your session is logout");
				responseStructure2.setDescription("please login again");
				responseStructure2.setData(null);
			}
		}else {
			responseStructure2.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure2.setMsg("productOwner is not present");
			responseStructure2.setDescription("#######################################");
			responseStructure2.setData(null);
		}
		return responseStructure2;
	}	
}
