package com.expensetracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expensetracker.entity.User;
import com.expensetracker.repo.UserRepo;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public User findByEmail(String email) {
		  Optional<User> byEmail = userRepo.findByEmail(email);
		return byEmail.orElse(null);
	}

	@Override
	public boolean createUser(User user) {
		return userRepo.save(user)!=null;
		 
	}

	@Override
	public User getUser(Long id) {
		return userRepo.findById(id).orElse(null);
	}

}
