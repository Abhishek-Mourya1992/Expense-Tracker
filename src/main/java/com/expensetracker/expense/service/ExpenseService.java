package com.expensetracker.expense.service;

import java.util.List;

import com.expensetracker.expense.entity.DeleteExpenseDto;
import com.expensetracker.expense.entity.Expense;

public interface ExpenseService {

	public boolean addExpense(Long userId, Expense expense);

	public List<Expense> getExpense(Long userId);

	public boolean deleteAllExpense(Long userId);

	public boolean deleteExpense(Long userId, Long id);
	
	public int deleteMultipleExpense(DeleteExpenseDto deleteDto);
	

}
