package com.expensetracker.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.expensetracker.user.entity.User;
import com.expensetracker.user.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public User findByEmail(String email) {
		Optional<User> byEmail = userRepo.findByEmail(email);
		return byEmail.orElse(null);
	}

	@Override
	public boolean createUser(User user) {
		String email = user.getEmail().toLowerCase().trim();
		String mobile = user.getMobile().trim();
	    user.setEmail(email);
	    user.setMobile(mobile);
		
		 boolean userbyEmail = userRepo.findByEmail(email).isPresent();
		if(userbyEmail) {
			return false;
		}
		
		
		return userRepo.save(user) != null;

	}

	@Override
	public User getUser(Long id) {
		return userRepo.findById(id).orElse(null);
	}

	@Override
	public void deleteAllUser() {
		userRepo.deleteAll();
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isUserExist(String emailorMobile) {
		return  userRepo.findByEmail(emailorMobile).isPresent() ||
		        userRepo.findByMobile(emailorMobile).isPresent();
		
	}

}
