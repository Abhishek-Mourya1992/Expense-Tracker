package com.expensetracker.user.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expensetracker.user.entity.User;


public interface UserRepo extends JpaRepository<User, Long>{
	
	public Optional<User> findByEmail(String email);
	public Optional<User> findByMobile(String email);

}
