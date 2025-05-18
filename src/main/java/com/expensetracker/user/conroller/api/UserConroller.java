package com.expensetracker.user.conroller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.user.entity.User;
import com.expensetracker.user.service.UserServiceImpl;

@RestController
public class UserConroller {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping("/createUser")
	public ResponseEntity<String> createUser(@RequestBody User user) {
		
		boolean isUserCreated = userServiceImpl.createUser(user);
		if (isUserCreated) {
			return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not created");
		}
	}

	
	
	@GetMapping("/findByEmail/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable String email) {
		User user = userServiceImpl.findByEmail(email);
		if (user == null || user.getEmail() == null || user.getEmail().isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with this email :" + email);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(user);
		}

	}

	@GetMapping("/findUserById/{id}")
	public ResponseEntity<?> getUser(@PathVariable Long id) {

		User user = userServiceImpl.getUser(id);
		if (user == null || user.getId() == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with this id:" + id);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(user);
		}

	}
	
	@DeleteMapping("/deleteAllUser")
	public void deleteAllUser() {
		userServiceImpl.deleteAllUser();
	}
	
	

}
