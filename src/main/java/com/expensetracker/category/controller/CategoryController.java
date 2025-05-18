package com.expensetracker.category.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.category.entity.Category;
import com.expensetracker.category.service.CategoryServiceImpl;

@RestController
public class CategoryController {

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;

	@PostMapping("/createCategory")
	public ResponseEntity<String> createCategory(@RequestBody Category category) {
		categoryServiceImpl.createCategory(category);

		return ResponseEntity.status(HttpStatus.CREATED).body("Category created successfully");

	};

	@GetMapping("/getCategories")
	public List<Category> getAllCategory() {
		List<Category> allCategory = categoryServiceImpl.getAllCategory();
		return allCategory;

	};

	@GetMapping("/getCategoryById/{categoryId}")
	public Category getCategoryByID(@PathVariable Long CategoryId) {
		Optional<Category> categoryByID = categoryServiceImpl.getCategoryByID(CategoryId);
		return categoryByID.get();

	};

	@PostMapping("/updateCategory")
	public boolean editCategory(String categoryName, Long categoryId) {
		return false;

	};

	public void deleteCategoryName(Long categoryId) {

	};

}
