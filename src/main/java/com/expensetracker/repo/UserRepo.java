package com.expensetracker.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expensetracker.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{
	
	public Optional<User> findByEmail(String email);

}
