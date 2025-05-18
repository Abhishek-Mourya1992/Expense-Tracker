package com.expensetracker.category.service;

import java.util.List;
import java.util.Optional;

import com.expensetracker.category.entity.Category;

public interface CategoryService {
	
	public boolean createCategory(Category category);
	
	public List<Category> getAllCategory();
	
	public Optional<Category> getCategoryByID(Long CategoryId);
	
	public boolean editCategory(String categoryName, Long categoryId);
	
	public void deleteCategoryName(Long categoryId);
	
	

}
