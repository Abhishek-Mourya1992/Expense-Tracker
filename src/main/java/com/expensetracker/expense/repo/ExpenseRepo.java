package com.expensetracker.expense.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.expensetracker.expense.entity.Expense;

import io.lettuce.core.dynamic.annotation.Param;
import jakarta.transaction.Transactional;

public interface ExpenseRepo extends JpaRepository<Expense, Long>{
	
	public List<Expense> findByUserId(Long userId);

	@Modifying
	@Transactional
	@Query("delete from Expense e where e.user.id=:userId")
	public void deleteByUserId( @Param("userId") Long userId);
	

	@Modifying
	@Transactional
	@Query("DELETE FROM Expense e WHERE e.user.id=:userId AND e.id=:id")
	public void deleteExpense( @Param("userId") Long userId, @Param("id")  Long id);
	
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Expense e WHERE e.user.id=:userId AND e.id IN :ids")
	int deleteMultipleExpense(@Param("userId") Long userId, @Param("ids") List<Long> ids );
	
	

    
}
