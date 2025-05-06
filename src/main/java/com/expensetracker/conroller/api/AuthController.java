package com.expensetracker.conroller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.service.OTPService;


@RestController
public class AuthController {

	@Autowired
	private OTPService otpService;

	@PostMapping("/request-otp")
	public ResponseEntity<String> requestOTP(@RequestBody Map<String, String> request) {

		String emailOrPhone = request.get("emailOrPhone");
		String otp = otpService.generateOTP(emailOrPhone);
		return ResponseEntity.ok("Your OTP for loginn is :" + otp);
	}

	
	
	@PostMapping("/veify-otp")
	public ResponseEntity<String> verifyOTP(@RequestBody Map<String, String> request) {
		String emailOrPhone = request.get("emailOrPhone");
		String otp = request.get("otp");
		boolean verifyOTP = otpService.verifyOTP(emailOrPhone, otp);
		if (verifyOTP==true) {
			return ResponseEntity.ok("Your Successfully Logged In with OTP :" + otp);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Your OTP is incorrect :" + otp);
		}
	}
	
	
}
