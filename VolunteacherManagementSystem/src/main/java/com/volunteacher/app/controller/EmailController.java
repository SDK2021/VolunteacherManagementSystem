package com.volunteacher.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.volunteacher.app.service.interfaces.EmailService;

@RestController
@RequestMapping(path = "/vms")
@CrossOrigin(origins="http://localhost:4200") 
public class EmailController {
	@Autowired
	EmailService emailService;
	
//	@GetMapping("/email")
//	public boolean sendEmail()
//	{
//		return this.emailService.sendMail();
//	}
	
	@PostMapping("/send-otp")
	public ResponseEntity<Object> sendOTP(@RequestParam("email") String email)
	{
		return emailService.sendOTP(email);
	}
	
	@PostMapping("/verify-otp")
	public boolean verifyOTP(@RequestParam("otp") String OTP,@RequestParam("userid") long userId)
	{
		return emailService.verifyOTP(OTP, userId);
	}
	
	@PostMapping("/update-password")
	public ResponseEntity<Object> updatePassword(@RequestParam("password") String password,@RequestParam("userid") long userId)
	{
		return emailService.updatePassword(password, userId);
	}
}
