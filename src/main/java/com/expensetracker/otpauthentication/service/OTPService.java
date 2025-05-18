package com.expensetracker.otpauthentication.service;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class OTPService {

	@Autowired
	private StringRedisTemplate redisTemplate;

	public OTPService(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;

	}

	public String generateOTP(String emailOrphone) {
		String countKey= "OTP_COUNT:"+emailOrphone;
		String key = "OTP:" + emailOrphone;
	    Long requestCount = redisTemplate.opsForValue().increment(countKey, 1);
	    redisTemplate.expire(countKey, Duration.ofMinutes(1)); // Reset after 1 minute

	    if (requestCount > 3) {
	        return "Too many Key Request............. wait for 1 minut"; // Return to login page with rate limit error
	    }else {
	    	String otp = String.valueOf(new SecureRandom().nextInt(900000) + 100000);
			redisTemplate.opsForValue().set(key, otp, Duration.ofMinutes(3));
			System.out.println("Storing OTP: " + otp + " for key: " + key);
			return otp;
	    }
		
		
		
	}

	public boolean verifyOTP(String emailOrphone, String inputOtp) {
		 if (emailOrphone == null || inputOtp == null) {
		        return false; // safeguard against null input
		    }

		    String key = "OTP:" + emailOrphone;
		    String storedOtp = redisTemplate.opsForValue().get(key);

		    System.out.println("email/mobile: " + emailOrphone);
		    System.out.println("input OTP: " + inputOtp);
		    System.out.println("stored OTP: " + storedOtp);

		    System.out.println(inputOtp.equals(storedOtp));
		    
		    
		    return inputOtp.equals(storedOtp);
}}
