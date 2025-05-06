package com.expensetracker.conroller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.entity.Expense;
import com.expensetracker.service.ExpenseServceImpl;

@RestController
public class ExpenseController {

	@Autowired
	private ExpenseServceImpl expenseServceImpl;

	@PostMapping("/addExpense/{userId}")
	public ResponseEntity<?> addExpense(@PathVariable Long userId, @RequestBody Expense expense) {

		boolean isExpenseSaved = expenseServceImpl.addExpense(userId, expense);
		if (isExpenseSaved) {
			return ResponseEntity.ok("Expense details saved");
		} else {

			return ResponseEntity.ok("Expense details not saved");
		}

	}

	@GetMapping("/getExpense/{userId}")
	public ResponseEntity<?> getExpense(@PathVariable Long userId) {
		List<Expense> expense = expenseServceImpl.getExpense(userId);

		if (expense.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body("No expense details added yet");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(expense);
		}

	}

	@DeleteMapping("/deleteAllExpense/{userId}")
	public ResponseEntity<String> deleteAllExpense(@PathVariable Long userId) {
		boolean isExpenseDeleted = expenseServceImpl.deleteAllExpense(userId);

		if (isExpenseDeleted) {
			return ResponseEntity.status(HttpStatus.OK).body("All Expenses are deleted successfully ");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("Unable to delete all expense");
		}

	}

	@DeleteMapping("/deleteAllExpense/{userId}/{id}")
	public ResponseEntity<String> deleteExpenseByids(@PathVariable Long userId, @PathVariable Long id) {
		boolean isExpenseDeleted = expenseServceImpl.deleteExpense(userId, id);
		if (isExpenseDeleted) {
			return ResponseEntity.status(HttpStatus.OK).body("Selected Expenses are deleted successfully ");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("Unable to delete selected expense");
		}

	}

}
