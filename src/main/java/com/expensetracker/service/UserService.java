package com.expensetracker.service;

import com.expensetracker.entity.User;

public interface UserService {

	public User findByEmail(String email);
	
	public boolean createUser(User user);
	
	public User getUser(Long id);
	
	
}
