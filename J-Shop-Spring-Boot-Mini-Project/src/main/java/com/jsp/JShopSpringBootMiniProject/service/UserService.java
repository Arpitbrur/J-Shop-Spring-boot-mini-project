package com.jsp.JShopSpringBootMiniProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jsp.JShopSpringBootMiniProject.dao.UserDao;
import com.jsp.JShopSpringBootMiniProject.dto.User;
import com.jsp.JShopSpringBootMiniProject.responseStructure.ResponseStructure;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ResponseStructure<User> responseStructure;
	
	@Autowired
	private HttpSession httpSession;
	// insert User-----------------------------------------------------------------------
	public ResponseStructure<User> insertuser(User user) {
		
		String email = adminService.validationEmail(user.getUserEmail());
		String password = adminService.validationPassword(user.getUserPassword());
		
		if(email != null) {
			if(password != null) {
				User user1 = userDao.insertuser(user);

				responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructure.setMsg("User----Registered");
				responseStructure.setDescription("congratulation---Please--Login");
				responseStructure.setData(user1);
			}else {
				responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure.setMsg("Check--Your---Password");
				responseStructure.setDescription("your password length should be 8 character along with 1 lower case,1 upperCase,atleast 1 special Character(!@#$*&%),1 number");
				responseStructure.setData(null);
			}
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setMsg("Check--Your---Email");
			responseStructure.setDescription("your email should contain atleast alphabetnumber@hgja.com");
			responseStructure.setData(null);
		}
		return responseStructure;
	}	
	
	// login User----------------------------------------------------------------------
	public ResponseStructure<User> loginUser(String email, String password) {
		
		User user = userDao.loginUser(email);
		
		if(user != null) {
			if(password.equals(user.getUserPassword())) {
				httpSession.setAttribute("password", user.getUserPassword());
				httpSession.setMaxInactiveInterval(20);
				
				responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructure.setMsg("login-success");
				responseStructure.setDescription("you have logged in....please perform your operation");
				responseStructure.setData(user);
			}else {
				responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure.setMsg("login credentials failed");
				responseStructure.setDescription("please checck your password...and type correctly");
				responseStructure.setData(null);
			}
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setMsg("login credentials failed");
			responseStructure.setDescription("please check your email...and type correctly");
			responseStructure.setData(null);

		}
		return responseStructure;
	}		
}
