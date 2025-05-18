package com.expensetracker.loginController.webmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.expensetracker.otpauthentication.service.OTPService;
import com.expensetracker.user.entity.User;
import com.expensetracker.user.service.UserService;

@Controller

public class LoginController {

	@Autowired
	private OTPService otpService;

	@Autowired
	UserService userService;

	// login first page
	@GetMapping("/")
	public String loginPage() {
		return "login";
	}

	// sign up page
	@GetMapping("/signUpuser")
	public String signUp(Model model) {
		  model.addAttribute("user", new User());  // or your User DTO class
		return "signup";
	}

	@PostMapping("/createUser-web")
	public String createUser(@ModelAttribute("user") User user, Model model, RedirectAttributes redirectAttributes) {
		 boolean isUserCreated = userService.createUser(user);
		 if (isUserCreated) {
		       redirectAttributes.addFlashAttribute("message", "User created successfully");
		        return "redirect:/"; // create a GET mapping for "/success" to show success page
		    }
		   redirectAttributes.addFlashAttribute("error", "User Already exist with this Email/mobile");
		    return "redirect:/";
		
	}

	@PostMapping("/webRequest-otp")
	public String requestOtp(@RequestParam("emailOrPhone") String emailOrPhone, Model model) {
		
		boolean isUserExist = userService.isUserExist(emailOrPhone);
		if (isUserExist) {
		String otp = otpService.generateOTP(emailOrPhone);
		System.out.println("Generated OTP: " + otp); // For debugging
		model.addAttribute("message", "OTP sent to " + emailOrPhone);
		model.addAttribute("emailOrPhone", emailOrPhone);
		model.addAttribute("otpSent", true); // This will show the OTP form
		
		}else {
			 model.addAttribute("message", "User with this email or phone does not exist.");
		}
		return "login";
	}

	@PostMapping("/webVerify-otp")
	public String verifyOtp(@RequestParam String emailOrPhone, @RequestParam String otp, Model model) {
		
		boolean isvalid =otpService.verifyOTP(emailOrPhone, otp);
		System.out.println(isvalid);

		if (isvalid == true) {
			return "dashboard"; // success
		} else {
			model.addAttribute("error", "Invalid OTP. Try again.");
			model.addAttribute("emailOrPhone", emailOrPhone);
			model.addAttribute("otpSent", true); // show OTP form again
			return "login"; // failure
		}
	}

	@GetMapping("/category")
	public String viewCategoryPage() {
		return "category";
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
