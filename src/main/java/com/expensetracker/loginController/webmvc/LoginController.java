package com.expensetracker.loginController.webmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.expensetracker.service.OTPService;

@Controller

public class LoginController {
	
	@Autowired
	private OTPService otpService;
	
	@GetMapping("/")
	public String loginPage() {
		return "login";
	}
	
     
    @PostMapping("/webRequest-otp")
    public String requestOtp(@RequestParam("emailOrPhone") String emailOrPhone, Model model) {
        String otp = otpService.generateOTP(emailOrPhone);
        System.out.println("Generated OTP: " + otp); // For debugging
        model.addAttribute("message", "OTP sent to " + emailOrPhone);
        model.addAttribute("emailOrPhone", emailOrPhone);
        model.addAttribute("otpSent", true); // This will show the OTP form
        return "login";
    }
    
        
    @PostMapping("/webVerify-otp")
    public String verifyOtp(@RequestParam String emailOrPhone,
                            @RequestParam String otp,
                            Model model) {
    	
        if (otpService.verifyOTP(emailOrPhone, otp)) {
            return "dashboard"; // success
        } else {
            model.addAttribute("error", "Invalid OTP. Try again.");
            model.addAttribute("emailOrPhone", emailOrPhone);
            model.addAttribute("otpSent", true); // show OTP form again
            return "login";
        }
    }
                       
    
//    @PostMapping("/verifyRequest-otp")
//    public String verifyRequest(@RequestParam("emailOrPhone") String emailOrPhone, Model model) {
//    	System.out.println("emailOrPhone: "+emailOrPhone);
//        String otp = otpService.generateOTP(emailOrPhone);  // Generate OTP for the given email or phone
//        model.addAttribute("message", "Your OTP is: " + otp);  // Add OTP message to model
//        return "login";  // Return to login page with the message
//    }
//	
    
	
	
}
