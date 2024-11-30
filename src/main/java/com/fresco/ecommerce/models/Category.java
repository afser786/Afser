package com.fresco.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer categoryId;

	private String categoryName;

	// Default constructor
	public Category() {
		super();
	}

	// Constructor with categoryName
	public Category(String categoryName) {
		super();
		this.categoryName = categoryName;
	}

	// Getters and Setters
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	// toString method
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}
}
