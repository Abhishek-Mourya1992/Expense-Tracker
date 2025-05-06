package com.expensetracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.expensetracker.entity.Expense;
import com.expensetracker.entity.User;
import com.expensetracker.repo.ExpenseRepo;
import com.expensetracker.repo.UserRepo;

import jakarta.transaction.Transactional;

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

}
