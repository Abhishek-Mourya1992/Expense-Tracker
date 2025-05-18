package com.expensetracker.otpauthentication.conroller.api;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.otpauthentication.service.OTPService;
import com.expensetracker.user.service.UserService;

@RestController
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private OTPService otpService;

	@PostMapping("/request-otp")
	public ResponseEntity<String> requestOTP(@RequestBody Map<String, String> request) {

		String emailOrPhone = request.get("emailOrPhone");
		boolean isUserExist = userService.isUserExist(emailOrPhone);
		if (isUserExist) {
			String otp = otpService.generateOTP(emailOrPhone);
			System.out.println(otp);
			return ResponseEntity.ok("Your OTP for login is :" + otp);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with provided email or phone.");
			
		}

		
		
	}

	@PostMapping("/veify-otp")
	public ResponseEntity<String> verifyOTP(@RequestBody Map<String, String> request) {
		
		Entry<String, String> entry = request.entrySet().iterator().next();
		
		String emailOrPhone = entry.getKey();
		String otp = entry.getValue();
		boolean verifyOTP = otpService.verifyOTP(emailOrPhone, otp);
		if (verifyOTP == true) {
			return ResponseEntity.ok("Your Successfully Logged In with OTP :" + otp);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Your OTP is incorrect :" + otp);
		}
	}

}
