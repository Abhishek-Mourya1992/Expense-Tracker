package com.expensetracker.category.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expensetracker.category.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Long>{

}
