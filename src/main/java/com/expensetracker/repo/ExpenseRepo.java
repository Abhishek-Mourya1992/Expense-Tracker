package com.expensetracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.expensetracker.entity.Expense;

import jakarta.transaction.Transactional;

public interface ExpenseRepo extends JpaRepository<Expense, Long>{
	
	public List<Expense> findByUserId(Long userId);

	@Modifying
	@Transactional
	@Query("delete from Expense e where e.user.id=:userId")
	public void deleteByUserId(Long userId);
	

	@Modifying
	@Transactional
	@Query("DELETE FROM Expense e WHERE e.user.id=:userId AND e.id=:id")
	public void deleteExpense(Long userId, Long id);
	
	
}
