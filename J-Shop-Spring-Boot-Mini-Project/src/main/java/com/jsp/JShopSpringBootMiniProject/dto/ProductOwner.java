package com.jsp.JShopSpringBootMiniProject.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class ProductOwner {

	@Id
	@Column(name="OwnerId")
	private int productOwnerId;
	@Column(name="OwnerName")
	private String productOwnerName;
	@Column(name ="OwnerEmail")
	private String productOwnerEmail;
	@Column(name="OwnerPassword")
	private String productOwnerPassword;
	@Column(name= "Verify")
	private String adminVerify;
	
	@ManyToOne
	@JoinColumn(name = "adminid")
	private Admin admins;
	
	@OneToMany(mappedBy = "productOwner")
	@JsonIgnore
	private List<Product> products;

	public int getProductOwnerId() {
		return productOwnerId;
	}

	public void setProductOwnerId(int productOwnerId) {
		this.productOwnerId = productOwnerId;
	}

	public String getProductOwnerName() {
		return productOwnerName;
	}

	public void setProductOwnerName(String productOwnerName) {
		this.productOwnerName = productOwnerName;
	}

	public String getProductOwnerEmail() {
		return productOwnerEmail;
	}

	public void setProductOwnerEmail(String productOwnerEmail) {
		this.productOwnerEmail = productOwnerEmail;
	}

	public String getProductOwnerPassword() {
		return productOwnerPassword;
	}

	public void setProductOwnerPassword(String productOwnerPassword) {
		this.productOwnerPassword = productOwnerPassword;
	}

	public String getAdminVerify() {
		return adminVerify;
	}

	public void setAdminVerify(String adminVerify) {
		this.adminVerify = adminVerify;
	}

	public Admin getAdmins() {
		return admins;
	}

	public void setAdmins(Admin admins) {
		this.admins = admins;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
	
}
