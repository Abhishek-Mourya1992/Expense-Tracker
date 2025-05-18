package com.expensetracker.expense.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expensetracker.expense.entity.DeleteExpenseDto;
import com.expensetracker.expense.entity.Expense;
import com.expensetracker.expense.repo.ExpenseRepo;
import com.expensetracker.user.entity.User;
import com.expensetracker.user.repo.UserRepo;

@Service
public class ExpenseServceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepo expenseRepo;

	@Autowired
	private UserRepo userRepo;

	@Override
	public boolean addExpense(Long userId, Expense expense) {
		Optional<User> user = userRepo.findById(userId);
		if (user.isEmpty()) {
			return false;

		} else {
			expense.setUser(user.get());
			Expense saved = expenseRepo.save(expense);
			return saved.getId() != null;
		}

	}

	@Override
	public List<Expense> getExpense(Long userId) {
		List<Expense> expenseList = expenseRepo.findByUserId(userId);
		return expenseList;
	}

	@Override
	public boolean deleteAllExpense(Long userId) {
		try{
			expenseRepo.deleteByUserId(userId);
			return true;
		}catch(Exception e) {
			 e.printStackTrace();
			return false;
		}
		
		

	}

	@Override
	public boolean deleteExpense(Long userId, Long id) {
		
		try{
			expenseRepo.deleteExpense(userId, id);
			return true;
		}catch(Exception e) {
			 e.printStackTrace();
			return false;
		}
		

	}

	@Override
	public int deleteMultipleExpense(DeleteExpenseDto deleteExpenseDto) {
		Long userid = deleteExpenseDto.getUserid();
		List<Long> ids = deleteExpenseDto.getIds();
		
		
		  int deleteMultipleExpense = expenseRepo.deleteMultipleExpense(userid, ids);
		return deleteMultipleExpense;
	}

}
