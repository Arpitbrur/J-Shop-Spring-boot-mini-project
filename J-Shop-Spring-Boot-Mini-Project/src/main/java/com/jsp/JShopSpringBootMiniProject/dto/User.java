package com.jsp.JShopSpringBootMiniProject.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="users")
public class User {

	@Id
	private int userId;
	private String userName;
	private String userEmail;
	private long userPhone;
	private String userPassword;
	
	@OneToMany(mappedBy = "user")
	private List<UserCart> userCarts;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public long getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<UserCart> getUserCarts() {
		return userCarts;
	}

	public void setUserCarts(List<UserCart> userCarts) {
		this.userCarts = userCarts;
	}
	
	
	
}
