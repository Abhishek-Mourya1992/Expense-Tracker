package com.expensetracker.service;

import java.util.List;

import com.expensetracker.entity.Expense;

public interface ExpenseService {

	public boolean addExpense(Long userId, Expense expense);

	public List<Expense> getExpense(Long userId);

	public boolean deleteAllExpense(Long userId);

	public boolean deleteExpense(Long userId, Long id);

}
