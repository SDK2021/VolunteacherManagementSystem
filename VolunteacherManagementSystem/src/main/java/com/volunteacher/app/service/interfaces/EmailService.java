package com.volunteacher.app.service.interfaces;

import javax.mail.internet.AddressException;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.ApplicantRequest;
import com.volunteacher.app.model.Event;

public interface EmailService {

	public boolean sendMail(String mail,String otp,String name);
	
	public ResponseEntity<Object> sendOTP(String email);
	
	public boolean verifyOTP(String OTP,long userId);
	
	public ResponseEntity<Object> updatePassword(String newPassword,long userId);
	
	public boolean registerSuccessfullyMail(String mail,String name);
	
	public boolean participantSuccessfullyMail(String mail,String name,Event event);
	
	public boolean notificationSessionMail(String userType) throws AddressException;
	
	public boolean notificationEventMail(String userType) throws AddressException;
	
	public boolean acceptRequestMail(ApplicantRequest request) throws AddressException;
}
