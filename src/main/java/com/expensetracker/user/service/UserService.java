package com.expensetracker.user.service;

import com.expensetracker.user.entity.User;

public interface UserService {

	public User findByEmail(String email);
	
	public boolean createUser(User user);
	
	public User getUser(Long id);
	
	public void deleteAllUser();
	
	public  boolean isUserExist(String emailorMobile);
	
	
}
