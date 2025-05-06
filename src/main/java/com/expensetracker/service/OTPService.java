package com.expensetracker.service;

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
		
		String key = "OTP:" + emailOrphone;
	    Long requestCount = redisTemplate.opsForValue().increment(key, 1);
	    redisTemplate.expire(key, Duration.ofMinutes(1)); // Reset after 1 minute

	    if (requestCount > 3) {
	        return "Too many Key Request............. wait for 1 minut"; // Return to login page with rate limit error
	    }else {
	    	String otp = String.valueOf(new SecureRandom().nextInt(900000) + 100000);
			redisTemplate.opsForValue().set("OTP:" + key, otp, Duration.ofMinutes(3));
			return otp;
	    }
		
		
		
	}

	public boolean verifyOTP(String emailOrphone, String inputOtp) {
		String storedOtp = redisTemplate.opsForValue().get("OTP:" +emailOrphone);
			return inputOtp.equals(storedOtp);
	
	
	}
}
