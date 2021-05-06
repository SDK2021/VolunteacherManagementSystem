package com.volunteacher.app.service.classes;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.EmailNotFoundException;
import com.volunteacher.app.model.ApplicantRequest;
import com.volunteacher.app.model.Event;
import com.volunteacher.app.model.User;
import com.volunteacher.app.repository.UserRepository;
import com.volunteacher.app.service.interfaces.EmailService;
import com.volunteacher.app.service.interfaces.UserService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired 
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	UserService userService;
	
	@Override
	public boolean sendMail(String mail,String otp,String name){
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
			mimeMessageHelper.setSubject("Password Reset Requests");
			mimeMessage.setContent("<h2>Hello  	" + name +"</h2><br>"
					+ "<span style='color:gray;'>We received a request to reset the password on your Volunteacher Management System Account.</span>"
					+"<br><br>"
					+ "Your OTP - <center><h2><strong>" + otp +"</strong></h2></center><br>"
					+ "<span style='color:gray;'>Enter OTP to complete Your Reset Password Step.</span><br><br>"
					+"<span style='color:gray;'>Thanks for helping us keep your account secure.!</span><br><br>"
					+"<strong>VMS Team</strong>","text/html");
			mimeMessageHelper.setFrom("sdkproject2021@gmail.com");
			mimeMessageHelper.setTo(mail);
			System.out.println(mimeMessage);
			
			javaMailSender.send(mimeMessage);
			return true; 
			
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public ResponseEntity<Object> sendOTP(String email) {
		User user = userRepository.findByEmail(email);
		
		if(user == null)
			throw new EmailNotFoundException("Sorry Email id not exist");
		
		try {
			String userOTP = this.createOTP().trim();
			this.sendMail(email,userOTP,user.getUserName());
			
			user.setUserOTP(userOTP);
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR ,12);
			calendar.set(Calendar.MINUTE, 01);
			calendar.set(Calendar.SECOND,30);
			System.out.println(calendar.getTime());
			user.setUserOTPTime(calendar);
			
			userRepository.save(user);
			return ResponseEntity.status(HttpStatus.OK).body(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creating OTP");
		}
		
	}
	
	public String createOTP()
	{
		Random random = new Random();
		String capitalAlphabet = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";
		String smallAlphabet = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "1234567890";
		String value = capitalAlphabet + smallAlphabet + numbers;
		char values[] = value.toCharArray();
		int  OTPsize = 6;
		String otp = "";
		
		for(int i = 0;i< OTPsize;i++)
		{
			otp += values[random.nextInt(values.length)];
			//     values[random.nextInt(62)]
		}
		System.out.println(otp);
		return otp;
	}

	@Override
	public boolean verifyOTP(String OTP, long userId) {
		
		User user = userRepository.findByUserId(userId);
		
		if(user == null)
			throw new UsernameNotFoundException("User not found for verify OTP");
		
		try {
			if(user.getUserOTP().trim().equals(OTP.trim()))
			{
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ResponseEntity<Object> updatePassword(String newPassword,long userId) {
		User user = userRepository.findByUserId(userId);
		
		if(user == null)
			throw new UsernameNotFoundException("Username not found when update the password");	
		
		try {
			String encodedPassword = passwordEncoder.encode(newPassword);
			user.setPassword(encodedPassword);
			userRepository.save(user);
			
			return ResponseEntity.status(HttpStatus.OK).body(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on updating password");
		}
	}
	
	@Override
	public boolean registerSuccessfullyMail(String mail,String name) {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
			mimeMessageHelper.setSubject("Register Successfully in volunteacher Management System");
			mimeMessage.setContent("Hello " 
								   + name 
								   + "<br><br>"
								   + "You register successfully","text/html");
			mimeMessageHelper.setFrom("sdkproject2021@gmail.com");
			mimeMessageHelper.setTo(mail);
			System.out.println(mimeMessage);
			
			javaMailSender.send(mimeMessage);
			return true; 
			
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean participantSuccessfullyMail(String mail, String name, Event event) {
	
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
			mimeMessage.setSubject("Participate Successfully for " + event.getTitle() + " " + event.getEventDate().get(Calendar.YEAR));
			mimeMessage.setContent("You register successfully", "text/html");
			
			mimeMessageHelper.setFrom("sdkproject2021@gmail.com");
			mimeMessageHelper.setTo(mail);
			
			javaMailSender.send(mimeMessage);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean notificationSessionMail(String userType) throws AddressException {
		
		String to = "";
		System.out.println(userType);
		if(userType.equals("VOLUNTEACHER"))
		{
			List<User> userList = userService.userByType(2);
			
			for (User user : userList) {
				to = to + user.getEmail() + ",";
			}
		}
		if(userType.equals("LOCAL VOLUNTEACHER"))
		{
			List<User> userList = userService.userByType(1);
			
			for (User user : userList) {
				to = to + user.getEmail() + ",";
			}
		}
		if(userType.equals("ALL"))
		{
			List<User> userList = (List<User>) userRepository.findAll();
			
			for (User user : userList) {
				to = to + user.getEmail() + ",";
			}
		}
		System.out.println("Hello" + to);
		InternetAddress[] emailAddresses = InternetAddress.parse(to, true);
		
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
			mimeMessage.setSubject("Notification");
			mimeMessage.setContent("Notify for session", "text/html");
			
			mimeMessageHelper.setFrom("sdkproject2021@gmail.com");
			mimeMessageHelper.setTo(emailAddresses);
			
			javaMailSender.send(mimeMessage);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	@Override
	public boolean notificationEventMail(String userType) throws AddressException {
		
		String to = "";
		System.out.println(userType);
		if(userType.equals("VOLUNTEACHER"))
		{
			List<User> userList = userService.userByType(2);
			
			for (User user : userList) {
				to = to + user.getEmail() + ",";
			}
		}
		if(userType.equals("LOCAL VOLUNTEACHER"))
		{
			List<User> userList = userService.userByType(1);
			
			for (User user : userList) {
				to = to + user.getEmail() + ",";
			}
		}
		if(userType.equals("ALL"))
		{
			List<User> userList = (List<User>) userRepository.findAll();
			
			for (User user : userList) {
				to = to + user.getEmail() + ",";
			}
		}
		System.out.println("Hello" + to);
		InternetAddress[] emailAddresses = InternetAddress.parse(to, true);
		
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
			mimeMessage.setSubject("Notification");
			mimeMessage.setContent("Notify for session", "text/html");
			
			mimeMessageHelper.setFrom("sdkproject2021@gmail.com");
			mimeMessageHelper.setTo(emailAddresses);
			
			javaMailSender.send(mimeMessage);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	@Override
	public boolean acceptRequestMail(ApplicantRequest request) throws AddressException {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
			mimeMessage.setSubject("Request accepted");
			mimeMessage.setContent("Request Accepted", "text/html");
			
			mimeMessageHelper.setFrom("sdkproject2021@gmail.com");
			mimeMessageHelper.setTo(request.getEmailId());
			
			javaMailSender.send(mimeMessage);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
