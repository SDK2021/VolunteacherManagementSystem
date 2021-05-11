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
import com.volunteacher.app.model.Notification;
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
			String startamPm = "";
			String endamPm = "";
			if(event.getEventStartingTime().get(Calendar.HOUR_OF_DAY) < 12)
			{
				startamPm = "AM";
			}
			else 
			{
				startamPm = "PM";
			}
			
			if(event.getEventEndingTime().get(Calendar.HOUR_OF_DAY) < 12)
			{
				endamPm = "AM";
			}
			else 
			{
				endamPm = "PM";
			}
			String imageSrc = "https://firebasestorage.googleapis.com/v0/b/volunteacher-management-system.appspot.com/o/eventimage.jpg?alt=media&token=59fe43ff-d1b1-4683-b6b1-0572ae8f1671"; 
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
			mimeMessage.setSubject("Participate Successfully for " + event.getTitle() + " " + event.getEventDate().get(Calendar.YEAR) + "(AIREP)");
			mimeMessage.setContent("<div style='height:1000px; background-color: #DCDCDC'>"
					+ "<center><div class='box' style='height: 1000px;width: 60%;background-color: white;margin-top:10px;'>"
					+ "<br><center><img src=" + imageSrc + "></center>"
					+ "<div class='body' style=text-align:center;>"
					+ "<h3><br><span style='float:left;'>&nbsp;&nbsp;&nbsp;&nbsp; Hello " + name + ",</span></h3>"
					+"<br><span style='float:left;'>"
					+ "I am so glad You have successfully registered for " + "<b>" + event.getTitle() 
					+ " " + event.getEventDate().get(Calendar.YEAR) + "!</span></b>"
					+ "<br><h2><b>Event Date: </b></h2>" + "<b>" + event.getEventDate().get(Calendar.DAY_OF_MONTH) + "-" 
					+ event.getEventDate().get(Calendar.MONTH) + "-" + event.getEventDate().get(Calendar.YEAR) + "</b>" 
					+"<br><h2><b>Event Venue:</b></h2>" + "<b>"+ event.getVillage().getVillageName() +"</b>" 
					+"<br><h2><b>Event Starting Time:</b></h2>" 
					+ "<b>" + event.getEventStartingTime().get(Calendar.HOUR) + ":" + event.getEventStartingTime().get(Calendar.MINUTE) + " " + startamPm + "</b>"
					+"<br><h2><b>Event Ending Time:</b></h2>" 
					+ "<b>" +event.getEventEndingTime().get(Calendar.HOUR) + ":" + event.getEventEndingTime().get(Calendar.MINUTE) + " " + endamPm + "</b>"
					+ "</div>"
					+ "<br><span style='float:left;'>&nbsp;&nbsp;&nbsp;&nbsp;We will also send you a reminder before one day of Event. Thank you for registration.</span>"
					+ "<br><br>&nbsp;&nbsp;&nbsp;&nbsp;<span style='float:left;'><b>Regards"
					+ "<br>&nbsp;&nbsp;&nbsp;&nbsp;VMS TEAM </b></span>"
					+ "	</div></center>"
					+ "</div>"
					, "text/html");
			
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
	public boolean notificationSessionMail(String userType,Notification notification) throws AddressException {
		
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
			mimeMessage.setSubject("New Notification from AIREP");
			mimeMessage.setContent("<div style='height:200px; background-color: #DCDCDC'>"
					+ "<br><center><div class='box' style='height: 150px;width: 60%;background-color: white;'>"
					+ "<center><div class='card'"
					+ "  max-width: 300px;"
					+ "  margin: auto;"
					+ "  text-align: center;"
					+ "  font-family: arial;'>"
					+ "  <h1 style='color:gray'>Session Notification</h1>"
					+ "  <p><center><a href='http://localhost:4200/user/notifications' target=\\\"_blank\\\" style='color:white';text-decoration:none;><button style='border: none;\r\n"
					+ "  outline: 0;"
					+ "  padding: 12px;"
					+ "  color: white;"
					+ "  background-color: #4285f4;"
					+ "  text-align: center;"
					+ "  cursor: pointer;"
					+ "  font-size: 18px;'>Open</button></a></center></p>"
					+ "</div></div></div></center>", "text/html");
			
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
	public boolean notificationEventMail(String userType,Notification notification) throws AddressException {
		
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
			mimeMessage.setSubject("New Notification from AIREP");
			mimeMessage.setContent("<div style='height:200px; background-color: #DCDCDC'>"
					+ "<br><center><div class='box' style='height: 150px;width: 60%;background-color: white;'>"
					+ "<center><div class='card'"
					+ "  max-width: 300px;"
					+ "  margin: auto;"
					+ "  text-align: center;"
					+ "  font-family: arial;'>"
					+ "  <h1 style='color:gray'>Event Notification</h1>"
					+ "  <p><center><a href='http://localhost:4200/user/notifications' target=\\\"_blank\\\" style='color:white';text-decoration:none;><button style='border: none;\r\n"
					+ "  outline: 0;"
					+ "  padding: 12px;"
					+ "  color: white;"
					+ "  background-color: #4285f4;"
					+ "  text-align: center;"
					+ "  cursor: pointer;"
					+ "  font-size: 18px;'>Open</button></a></center></p>"
					+ "</div></div></div></center>", "text/html");
			
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
			mimeMessage.setSubject("Request accepted from AIREP");
			mimeMessage.setContent("<div style='height:200px; background-color: #DCDCDC'>"
					+ "<br><center><div class='box' style='height: 150px;width: 60%;background-color: white;'>"
					+ "<center><div class='card'"
					+ "  max-width: 300px;"
					+ "  margin: auto;"
					+ "  text-align: center;"
					+ "  font-family: arial;'>"
					+ "  <h2 style='color:gray;text-align:center'>Your request Accepted Successfully<br>"
					+ "	Please fill form from below link to proceed for Login.</h2>"
					+ "  <p><center><a href='http://localhost:4200/volunteacher-registration' target=\\\"_blank\\\" style='color:white';text-decoration:none;><button style='border: none;\r\n"
					+ "  outline: 0;"
					+ "  padding: 12px;"
					+ "  color: white;"
					+ "  background-color: #5cb85c;"
					+ "  text-align: center;"
					+ "  cursor: pointer;"
					+ "  font-size: 18px;'>Login</button></a></center></p>"
					+ "</div></div></div></center>", "text/html");
			
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
