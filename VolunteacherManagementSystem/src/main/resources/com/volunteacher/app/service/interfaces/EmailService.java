package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

public interface EmailService {

	public boolean sendMail(String mail,String otp,String name);
	
	public ResponseEntity<Object> sendOTP(String email);
	
	public boolean verifyOTP(String OTP,long userId);
	
	public ResponseEntity<Object> updatePassword(String newPassword,long userId);
}
