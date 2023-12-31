package com.jsp.JShopSpringBootMiniProject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jsp.JShopSpringBootMiniProject.dao.AdminDao;
import com.jsp.JShopSpringBootMiniProject.dao.ProductDao;
import com.jsp.JShopSpringBootMiniProject.dto.Admin;
import com.jsp.JShopSpringBootMiniProject.dto.Product;
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
	
	@Autowired
	private ResponseStructure<List<ProductOwner>> responseStructure3;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ResponseStructure<Product> responseStructure4;
	
	
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
			responseStructure.setDescription("congratulation---Please--Login");
			responseStructure.setData(admin1);
			return responseStructure;
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setMsg("check your password ");
			responseStructure.setDescription("your password length should be 8 character along with 1 lower case,1 upperCase,atleast 1 special Character(!@#$*&%),1 number");
			responseStructure.setData(null);
			return responseStructure;
		}
		
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setMsg("Check your email");
			responseStructure.setDescription("your email should contain atleast alphabetnumber@gmail.com");
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
				responseStructure.setDescription("you have logged in....please perform your operation");
				responseStructure.setData(admin);
				return responseStructure;
			
			}else {
				responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
				responseStructure.setMsg("not login please check your password");
				responseStructure.setDescription("please checck your password...and type correctly");
				responseStructure.setData(null);
				return responseStructure;
			}
			
	   }else {
		   responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		   responseStructure.setMsg("not login please check your email");
		   responseStructure.setDescription("your email should contain atleast alphabetsNumber@gmail.com");
		   return responseStructure;
	   }
		
		
	}
	
	// getAllProductOwnerAdmin-----------------------------------------------------------------
	public ResponseStructure<List<ProductOwner>> getAllProductOwnerAdmin() {
		List<ProductOwner> owners = new ArrayList<ProductOwner>();
		
		if(httpSession.getAttribute("password") != null) {
			for (ProductOwner productOwner : adminDao.getAllProductOwnerAdmin()) {
				if(productOwner.getAdminVerify().equalsIgnoreCase("no")) {
					owners.add(productOwner);
				}
			}
			responseStructure3.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure3.setMsg("Success");
			responseStructure3.setDescription("please find your data below....");
			responseStructure3.setData(owners);
		}else {
			responseStructure3.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure3.setMsg("Your session is  logout ");
			responseStructure3.setDescription("please login again");
			responseStructure3.setData(null);
		}
		return responseStructure3;
		
	}
	
	
	//getProductOwnerByIdAdmin-----------------------------------------------------------------
	public ResponseStructure<ProductOwner> getProductOwnerByIdAdmin(int productOwnerId) {
		
		ProductOwner owner= adminDao.getProductOwnerByIdAdmin(productOwnerId);
		if(owner != null) {
			if(httpSession.getAttribute("password")!= null){
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
	
	// verify Product details By id-----------------------------------------------------------------
	public ResponseStructure<Product> verifyProductDetailsByAdmin(int productId) {
		
		Product product=productDao.getProductDataById(productId);
		
		if(product!=null) {
			
			if (httpSession.getAttribute("password") != null) {

				Product product2 = adminDao.verifyProductDetailsByAdmin(productId);
				responseStructure4.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructure4.setMsg("Success");
				responseStructure4.setDescription("product is verified successfully");
				responseStructure4.setData(product2);
			} else {
				responseStructure4.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure4.setMsg("Your session is  logout ");
				responseStructure4.setDescription("please login again");
				responseStructure4.setData(null);
			}
			
		}else {
			responseStructure4.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure4.setMsg("given id is not present");
			responseStructure4.setDescription("please check once");
			responseStructure4.setData(null);
		}
		return responseStructure4;
	}
		
}
