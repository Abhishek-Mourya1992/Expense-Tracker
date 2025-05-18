package com.expensetracker.category.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expensetracker.category.entity.Category;
import com.expensetracker.category.repo.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public boolean createCategory(Category category) {
		categoryRepo.save(category);
		return category.getCategoryId() != null;
	}

	@Override
	public List<Category> getAllCategory() {
		List<Category> categoryList = categoryRepo.findAll();
		return categoryList;
	}

	@Override
	public Optional<Category> getCategoryByID(Long CategoryId) {
		Optional<Category> categoryById = categoryRepo.findById(CategoryId);
		return categoryById;
	}

	@Override
	public boolean editCategory(String categoryName, Long categoryId) {
		Category category = new Category();
		Optional<Category> byId = categoryRepo.findById(categoryId);
		category.setCategoryId(byId.get().getCategoryId());
		category.setCategoryName(categoryName);
		categoryRepo.save(category);
		return category.getCategoryId() != null;
	}

	@Override
	public void deleteCategoryName(Long categoryId) {
		categoryRepo.deleteById(categoryId);

	}

}
