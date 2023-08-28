package com.jsp.JShopSpringBootMiniProject.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {

	@Id
	private int productId;
	private String productName;
	private double productPrice;
	private String productBrand;
	private String productVerified;
	private int productQuantity;
	
	
	@ManyToOne
	@JoinColumn(name ="ownerid")
	private ProductOwner productOwner;


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public double getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}


	public String getProductBrand() {
		return productBrand;
	}


	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}


	public String getProductVerified() {
		return productVerified;
	}


	public void setProductVerified(String productVerified) {
		this.productVerified = productVerified;
	}


	public int getProductQuantity() {
		return productQuantity;
	}


	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}


	public ProductOwner getProductOwner() {
		return productOwner;
	}


	public void setProductOwner(ProductOwner productOwner) {
		this.productOwner = productOwner;
	}
	
	
}
