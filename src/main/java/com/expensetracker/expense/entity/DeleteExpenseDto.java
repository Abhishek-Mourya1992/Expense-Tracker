package com.expensetracker.expense.entity;

import java.util.List;

import lombok.Data;


@Data
public class DeleteExpenseDto {
	
private Long userid;
private List<Long> ids;

}
