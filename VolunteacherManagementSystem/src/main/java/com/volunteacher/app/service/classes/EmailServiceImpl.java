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
	public ResponseEntity<Object> updatePassword(String newPassword,long userId,String oldPassword) {
		try {
			System.out.println(oldPassword);
			User user = userRepository.findByUserId(userId);
			
			if(user == null)
				throw new UsernameNotFoundException("Username not found when update the password");	
			
			if(oldPassword !=null)
			{
				if(!(passwordEncoder.matches(oldPassword, user.getPassword())))
				{
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
				}
			}
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
			mimeMessageHelper.setSubject("Register Successfully in AIREP");
			mimeMessage.setContent("<div bgcolor=\"#f5f5f5\" dir=\"ltr\"><img src=\"https://firebasestorage.googleapis.com/v0/b/volunteacher-management-system.appspot.com/o/AIREP-logo-Email.jpg?alt=media&token=89e2e8aa-160b-4ae6-bd6d-29b9d410d8bf\" width=\"1\" height=\"1\">\r\n"
					+ "  <div id=\"m_1156825266702629747wrapper\" style=\"margin-right:auto;margin-top:0;margin-bottom:0;margin-left:auto\" dir=\"ltr\">\r\n"
					+ "    <table id=\"m_1156825266702629747email-container\" style=\"border-collapse:collapse;color:#4d4d4d;font-size:16px;font-family:Roboto;width:100%!important;background-color:#f5f5f5;border-width:0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" bgcolor=\"#f5f5f5\">\r\n"
					+ "      <tbody>\r\n"
					+ "        <tr>\r\n"
					+ "          <td class=\"m_1156825266702629747mob-pad\" align=\"left\" style=\"line-height:24px;padding:0;margin:0\">\r\n"
					+ "            <table id=\"m_1156825266702629747snippet\" style=\"border-collapse:collapse;color:#333333;font-size:16px;font-family:Roboto;width:600px;border-width:0\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\r\n"
					+ "              <tbody>\r\n"
					+ "                <tr>\r\n"
					+ "                  <td height=\"10\" style=\"line-height:20px;vertical-align:top;height:10px;padding-top:3px;padding-right:0px;padding-bottom:3px;padding-left:0px;margin:0\" valign=\"top\" align=\"left\">\r\n"
					+ "                  </td>\r\n"
					+ "                </tr>\r\n"
					+ "              </tbody>\r\n"
					+ "            </table>\r\n"
					+ "            <table id=\"m_1156825266702629747snippet\" style=\"border-collapse:collapse;color:#333333;font-size:16px;font-family:Roboto;width:600px;border-width:0\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\r\n"
					+ "              <tbody>\r\n"
					+ "                <tr>\r\n"
					+ "                  <td height=\"10\" style=\"text-align:right;line-height:20px;vertical-align:top;height:10px;padding-top:3px;padding-right:0px;padding-bottom:3px;padding-left:0px;margin:0\" valign=\"top\" align=\"left\"> \r\n"
					+ "                  </td>\r\n"
					+ "                </tr>\r\n"
					+ "              </tbody>\r\n"
					+ "            </table>\r\n"
					+ "            <table id=\"m_1156825266702629747sub-container\" style=\"border-collapse:collapse;color:#4d4d4d;font-size:16px;font-family:Roboto;width:600px;border-width:0\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\r\n"
					+ "              <tbody>\r\n"
					+ "                <tr>\r\n"
					+ "                <td width=\"100%\" align=\"center\" id=\"m_1156825266702629747green-masthead\" style=\"text-align:center;background-color:#ffffff\"> \r\n"
					+ "              </td></tr></tbody><tbody>\r\n"
					+ "                <tr>\r\n"
					+ "                  <td id=\"m_1156825266702629747body\" align=\"left\" style=\"line-height:24px;padding:0;margin:0\">\r\n"
					+ "                    <table style=\"border-collapse:collapse;color:#4d4d4d;font-size:16px;font-family:Roboto;width:600px;border-width:1px;border-color:#dddddd;border-style:solid\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
					+ "                      <tbody>\r\n"
					+ "                        <tr>\r\n"
					+ "                          <td bgcolor=\"#ffffff\" id=\"m_1156825266702629747content\" style=\"line-height:24px;padding-top:0px;padding-right:0;padding-bottom:32px;padding-left:0;margin:0;background-color:#ffffff\"><img style=\"border:none\" src=\"https://firebasestorage.googleapis.com/v0/b/volunteacher-management-system.appspot.com/o/AIREP-logo-Email.jpg?alt=media&token=89e2e8aa-160b-4ae6-bd6d-29b9d410d8bf\" alt=\"Google \" width=\"700\" height=\"200\" class=\"CToWUd a6T\" tabindex=\"0\"><div class=\"a6S\" dir=\"ltr\" style=\"opacity: 0.01; left: 652.8px; top: 209.4px;\"><div id=\":1f8\" class=\"T-I J-J5-Ji aQv T-I-ax7 L3 a5q\" role=\"button\" tabindex=\"0\" aria-label=\"Download attachment \" data-tooltip-class=\"a1V\" data-tooltip=\"Download\"><div class=\"akn\"><div class=\"aSK J-J5-Ji aYr\"></div></div></div></div>    <table id=\"m_1156825266702629747sub-container\" style=\"border-collapse:collapse;color:#4d4d4d;font-size:16px;font-family:Roboto;width:600px;border-width:0\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\r\n"
					+ "                          \r\n"
					+ "  <tbody><tr><td><table style=\"border-collapse:collapse;color:#4d4d4d;font-size:16px;font-family:Roboto;color:black;border-width:0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
					+ "                              <tbody>\r\n"
					+ "                                <tr>\r\n"
					+ "                                  <td align=\"left\" style=\"text-align:left;line-height:24px;color:#4d4d4d;font-size:16px;padding-top:32px;padding-right:30px;padding-bottom:0;padding-left:30px;margin:0\"><span>\r\n"
					+ "Hello  "+ name+",\r\n"
					+ "<br>\r\n"
					+ "<br>\r\n"
					+ "You have <span class=\"il\">successfully</span> <span class=\"il\">registered</span> for Volunteaching in <b>A</b>ll <b>I</b>ndia <b>R</b>ular <b>E</b>mpowerment <b>P</b>rogram</b> . \r\n"					
					+ "<br><br><b>Background</b><br><br>\r\n"					
					+ "AIREP is a swayambhoo activity - a people's movement encouraging participation by any person,\r\n"
					+ "institute, and/or organization who would like to work for the development of rural India. The\r\n"
					+ "objective of AIREP is To empower rural population through Education and Vocation based on\r\n"
					+ "character building and to put efforts for enhanced awareness about Health and Hygiene\r\n"
					+ "<br><br>AIREP is a program - not an organization  under which we appeal people to spend one day per\r\n"
					+ "week for nation building. We believe that knowledge and resources should be shared by those who\r\n"
					+ "have it with those who need it. The effort is to empower rural populace by developing a teachable\r\n"
					+ "point of view that promotes innovative ways of sharing knowledge and resources."
					+ "<br>\r\n"
					+ "\r\n"
					+ "<br><br>Reagrds!\r\n"
					+ "<br>VMS Team\r\n"
					+ "</span></td><td id=\"m_1156825266702629747footer\" align=\"left\" style=\"text-align:left;line-height:24px;padding-top:28px;padding-right:0;padding-bottom:0;padding-left:0;margin:0\">\r\n"
					+"</td>\r\n"
					+ "                                </tr>\r\n"
					+ "                              </tbody>\r\n"
					+ "                            </table>\r\n"
					+ "                          </td>\r\n"
					+ "                        </tr></tbody></table></td></tr>\r\n"
					+ "                      </tbody>\r\n"
					+ "                    </table>\r\n"
					+ "                  </td>\r\n"
					+ "                </tr>\r\n"
					+ "              </tbody>\r\n"
					+ "            </table>\r\n"
					+ "  </td></tr></tbody></table></div><div class=\"yj6qo\"></div><div class=\"adL\">\r\n"
					+ "</div></div>","text/html");
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
//			mimeMessage.setContent("<div style='height:1000px; background-color: #DCDCDC'>"
//					+ "<center><div class='box' style='height: 1000px;width: 60%;background-color: white;margin-top:10px;'>"
//					+ "<br><center><img src=" + imageSrc + "></center>"
//					+ "<div class='body' style=text-align:center;>"
//					+ "<h3><br><span style='float:left;'>&nbsp;&nbsp;&nbsp;&nbsp; Hello " + name + ",</span></h3>"
//					+"<br><span style='float:left;'>"
//					+ "I am so glad You have successfully registered for " + "<b>" + event.getTitle() 
//					+ " " + event.getEventDate().get(Calendar.YEAR) + "!</span></b>"
//					+ "<br><h2><b>Event Date: </b></h2>" + "<b>" + event.getEventDate().get(Calendar.DAY_OF_MONTH) + "-" 
//					+ event.getEventDate().get(Calendar.MONTH) + "-" + event.getEventDate().get(Calendar.YEAR) + "</b>" 
//					+"<br><h2><b>Event Venue:</b></h2>" + "<b>"+ event.getVillage().getVillageName() +"</b>" 
//					+"<br><h2><b>Event Starting Time:</b></h2>" 
//					+ "<b>" + event.getEventStartingTime().get(Calendar.HOUR) + ":" + event.getEventStartingTime().get(Calendar.MINUTE) + " " + startamPm + "</b>"
//					+"<br><h2><b>Event Ending Time:</b></h2>" 
//					+ "<b>" +event.getEventEndingTime().get(Calendar.HOUR) + ":" + event.getEventEndingTime().get(Calendar.MINUTE) + " " + endamPm + "</b>"
//					+ "</div>"
//					+ "<br><span style='float:left;'>&nbsp;&nbsp;&nbsp;&nbsp;We will also send you a reminder before one day of Event. Thank you for registration.</span>"
//					+ "<br><br>&nbsp;&nbsp;&nbsp;&nbsp;<span style='float:left;'><b>Regards"
//					+ "<br>&nbsp;&nbsp;&nbsp;&nbsp;VMS TEAM </b></span>"
//					+ "	</div></center>"
//					+ "</div>"
//					, "text/html");
			String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
			mimeMessage.setContent("<table bgcolor=\"#ffffff\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" style=\"border-collapse:collapse;border-spacing:0;background-color:#ffffff\">\r\n"
					+ "                        <tbody>\r\n"
					+ "                            <tr>\r\n"
					+ "                                <td style=\"padding:0px;margin:0px\">\r\n"
					+ "                                    \r\n"
					+ "                                    <table width=\"100%\" bgcolor=\"#f7f7f7\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"m_-2908186609707375218backgroundTable\" style=\"border-collapse:collapse;border-spacing:0\">\r\n"
					+ "                                        <tbody><tr>\r\n"
					+ "                                            <td>\r\n"
					+ "                                                \r\n"
					+ "\r\n"
					+ "                                                <table bgcolor=\"#2d1846\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" style=\"border-collapse:collapse;border-spacing:0;background-color:#2d1846\">\r\n"
					+ "                                                    <tbody><tr>\r\n"
					+ "                                                        <td style=\"padding:0px;margin:0px;border-bottom:4px solid #2d1846\">\r\n"
					+ "                                                            <table bgcolor=\"#2d1846\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" style=\"border-collapse:collapse;border-spacing:0\">\r\n"
					+ "                                                                <tbody><tr>\r\n"
					+ "                                                                    <td>\r\n"
					+ "                                                                        <table bgcolor=\"#2d1846\" width=\"551\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" style=\"border-collapse:collapse;border-spacing:0\">\r\n"
					+ "                                                                            <tbody><tr>\r\n"
					+ "                                                                                <td style=\"padding:0px;vertical-align:top;text-align:center\">\r\n"
					+ "                                                                                    <img style=\"max-width:200px;vertical-align:bottom\" src=\"https://ci3.googleusercontent.com/proxy/Ji7Xuu3AXWzyp_jGiF-LUsN0mq5cLS0pYXvBd_8GkWYCqO_cBtly5XkzzE2LWX_w7lbYn7PNKIA4kbgaSxHkFTWUG5COf79DI3wgvTYNwA=s0-d-e1-ft#https://www.techgig.com/files/nicUploads/824168872795083.png\" alt=\"logo\" class=\"CToWUd\">\r\n"
					+ "                                                                                </td>\r\n"
					+ "                                                                            </tr>\r\n"
					+ "                                                                            <tr>\r\n"
					+ "                                                                                <td style=\"padding:0px\">\r\n"
					+ "                                                                                    <table width=\"100%\" border=\"0\" style=\"border-collapse:collapse;border-spacing:0\">\r\n"
					+ "                                                                                        <tbody><tr>\r\n"
					+ "                                                                                            <td style=\"padding:0px\">\r\n"
					+ "                                                                                                <table align=\"left\" width=\"100%\" border=\"0\" style=\"border-collapse:collapse;border-spacing:0\">\r\n"
					+ "                                                                                                    <tbody><tr>\r\n"
					+ "                                                                                                        <td style=\"padding:0px;padding-left:10px\">\r\n"
					+ "                                                                                                            <table width=\"100%\" border=\"0\" style=\"border-collapse:collapse;border-spacing:0\">\r\n"
					+ "                                                                                                                <tbody><tr>\r\n"
					+ "                                                                                                                    <td style=\"line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left\" height=\"16\">&nbsp;</td>\r\n"
					+ "                                                                                                                </tr>\r\n"
					+ "                                                                                                                <tr>\r\n"
					+ "                                                                                                                    <td style=\"margin:0;padding:0;font-size:22px;text-align:center;color:#ffffff;line-height:28px;font-family:Helvetica,Arial,sans-serif;font-weight:bold;text-transform:uppercase\"><span class=\"il\">"+ event.getTitle() +"</span>"+ "  " + monthNames[event.getEventDate().get(Calendar.MONTH)] +" " + event.getEventDate().get(Calendar.DAY_OF_MONTH)+", " + event.getEventDate().get(Calendar.YEAR)+"   " + event.getEventStartingTime().get(Calendar.HOUR_OF_DAY) +":" +event.getEventStartingTime().get(Calendar.MINUTE) + " " + startamPm + " <b>TO</b> " + event.getEventEndingTime().get(Calendar.HOUR_OF_DAY) +":" +event.getEventEndingTime().get(Calendar.MINUTE) + " " + endamPm +"</td>\r\n"
			
					+"                                                                                                                </tr>\r\n"
					+ "                                                                                                                <tr>\r\n"
					+ "                                                                                                                    <td style=\"line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left\" height=\"4\">&nbsp;</td>\r\n"
					+ "                                                                                                                </tr>\r\n"
					+ "                                                                                                                <tr>\r\n"
					+ "                                                                                                                    <td style=\"margin:0;padding:0;font-size:14px;text-align:center;color:#ffffff;line-height:21px;font-family:Helvetica,Arial,sans-serif;font-weight:bold\">Hosted by: \r\n"
					+ "                                                                                                                    										<b>A</b>ll <b>I</b>ndia <b>R</b>ular <b>E</b>mpowerment <b>P</b>rogram</b> .										</td>\r\n"
					+ "                                                                                                                </tr>\r\n"
					+ "                                                                                                                <tr>\r\n"
					+ "                                                                                                                    <td style=\"line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left\" height=\"16\">&nbsp;</td>\r\n"
					+ "                                                                                                                </tr>\r\n"
					+ "                                                                                                                <tr>\r\n"
					+ "                                                                                                                    <td style=\"margin:0;padding:0;font-size:18px;text-align:center;color:#ffffff;line-height:22px;font-family:Helvetica,Arial,sans-serif;font-weight:bold;text-transform:capitalize\">"+"Venue:" + event.getVillage().getVillageName() + "-Village  "+event.getVillage().getTaluka().getDistrict().getDistrictName()+" , "+event.getVillage().getTaluka().getDistrict().getState().getStateName()+"</td>\r\n"
					+ "                                                                                                                </tr>\r\n"
					+ "                                                                                                                <tr>\r\n"
					+ "                                                                                                                    <td style=\"line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left\" height=\"16\">&nbsp;</td>\r\n"
					+ "                                                                                                                </tr>\r\n"
					+ "                                                                                                                <tr>\r\n"
					+ "                                                                                                                    <td align=\"center\">\r\n"
					+ "                                                                                                                        <table style=\"border-collapse:collapse;border-spacing:0\" width=\"188\">\r\n"
					+ "                                                                                                                            <tbody>\r\n"
					+ "                                                                                                                                <tr>\r\n"
					+ "                                                                                                                                    <td style=\"background-color:#d7263d;text-align:center;color:#ffffff;border-radius:3px;font-size:16px;text-decoration:none;font-weight:bold\">\r\n"
					+ "                                                                                                                                        <a href=\"https://localhost:4200/register\" style=\"margin:0;padding:0px 3px 0px 3px;display:block;color:#ffffff;font-size:16px;line-height:18px;font-family:Arial,Helvetica,sans-serif;text-align:center;font-weight:bold;text-align:center;text-decoration:none;border:10px solid #d7263d;border-radius:3px\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://techgig.com/mail_redirect.php?url%3Dhttps%253A%252F%252Fwww.techgig.com%252Fwebinar%252FBuild-Apps-on-Salesforce-with-Modern-Javascript-1964%253Faction%253Dbook-seat-cHBLK055VjZ6REZUaGxBVSs2aXdQdz09%2526utm_source%253DTG_batch%2526utm_medium%253Demail%2526utm_campaign%253Dwebinar_2021-05-06%2526%2526auto_login%3DNUZmS1lNa1FRMXU4L1poZHc2Mk9KRDhUODNRcGdSdEx0WSswTzJTWHhmMGxkaHo2cGZJUUZvdUNOYk5JMUhDb0t0ak1uNEdMVkh6UU5uZUU2eFMzTGc9PQ%3D%3D%2526etoken%3DNUZmS1lNa1FRMXU4L1poZHc2Mk9KRHdUZy9YZGc3VlAyNjFqV0MwSG9EUT0%3D%2526activity_name%253DNzY5MzIx%2526template_type%253D0&amp;source=gmail&amp;ust=1621709655620000&amp;usg=AFQjCNHycczoMT3mqvDbkEyQzyy-Ofoyew\">JOIN AS A VOLUNTEACHER </a>\r\n"
					+ "                                                                                                                                    </td>\r\n"
					+ "                                                                                                                                </tr>\r\n"
					+ "                                                                                                                            </tbody>\r\n"
					+ "                                                                                                                        </table>\r\n"
					+ "                                                                                                                    </td>\r\n"
					+ "                                                                                                                </tr>\r\n"
					+ "                                                                                                            </tbody></table>\r\n"
					+ "                                                                                                        </td>\r\n"
					+ "                                                                                                    </tr>\r\n"
					+ "                                                                                                    <tr>\r\n"
					+ "                                                                                                        <td style=\"line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left\" height=\"16\">&nbsp;</td>\r\n"
					+ "                                                                                                    </tr>\r\n"
					+ "                                                                                                </tbody></table>\r\n"
					+ "                                                                                            </td>\r\n"
					+ "                                                                                        </tr>\r\n"
					+ "                                                                                    </tbody></table>\r\n"
					+ "                                                                                </td>\r\n"
					+ "                                                                            </tr>\r\n"
					+ "                                                                        </tbody></table>\r\n"
					+ "                                                                    </td>\r\n"
					+ "                                                                </tr>\r\n"
					+ "                                                            </tbody></table>\r\n"
					+ "                                                        </td>\r\n"
					+ "                                                    </tr>\r\n"
					+ "                                                </tbody></table>\r\n"
					+ "                                            </td>\r\n"
					+ "                                        </tr>\r\n"
					+ "                                    </tbody></table>\r\n"
					+ "\r\n"
					+ "                                    \r\n"
					+ "\r\n"
					+ "                                    \r\n"
					+ "\r\n"
					+ "                                    <table bgcolor=\"#ffffff\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" style=\"border-collapse:collapse;border-spacing:0;background-color:#ffffff\">\r\n"
					+ "                                        <tbody><tr>\r\n"
					+ "\r\n"
					+ "                                            <td style=\"padding:0px;margin:0px\">\r\n"
					+ "                                                <table bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" style=\"border-collapse:collapse;border-spacing:0\">\r\n"
					+ "                                                    <tbody><tr>\r\n"
					+ "                                                        <td>\r\n"
					+ "                                                            <table bgcolor=\"#ffffff\" width=\"551\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" style=\"border-collapse:collapse;border-spacing:0\">\r\n"
					+ "                                                                <tbody><tr>\r\n"
					+ "                                                                    <td style=\"padding:0px\">\r\n"
					+ "                                                                        <table width=\"100%\" border=\"0\" style=\"border-collapse:collapse;border-spacing:0\">\r\n"
					+ "                                                                            <tbody><tr>\r\n"
					+ "                                                                                <td style=\"padding:0px\">\r\n"
					+ "                                                                                    <table width=\"100%\" border=\"0\" style=\"border-collapse:collapse;border-spacing:0\">\r\n"
					+ "                                                                                        <tbody><tr>\r\n"
					+ "                                                                                            <td style=\"padding:0px\"></td>\r\n"
					+ "                                                                                        </tr>\r\n"
					+ "                                                                                    </tbody></table>\r\n"
					+ "                                                                                    <table width=\"375\" align=\"left\" border=\"0\" style=\"border-collapse:collapse;border-spacing:0\">\r\n"
					+ "                                                                                        \r\n"
					+ "                                                                                        <tbody><tr>\r\n"
					+ "                                                                                            <td style=\"padding:0px\">\r\n"
					+ "                                                                                                <table width=\"100%\" border=\"0\" style=\"border-collapse:collapse;border-spacing:0\">\r\n"
					+ "                                                                                                    <tbody><tr>\r\n"
					+ "                                                                                                        <td style=\"line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left\" height=\"20\">&nbsp;</td>\r\n"
					+ "                                                                                                    </tr>\r\n"
					+ "                                                                                                    <tr>\r\n"
					+ "                                                                                                        <td style=\"margin:0;padding:0;font-size:16px;text-align:left;color:#4b4649;line-height:22px;font-family:Helvetica,Arial,sans-serif;font-weight:bold\">Hi "+ name+",</td>\r\n"
					+ "                                                                                                    </tr>\r\n"
					+ "                                                                                                    <tr>\r\n"
					+ "                                                                                                        <td style=\"line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left\" height=\"12\">&nbsp;</td>\r\n"
					+ "                                                                                                    </tr>\r\n"
					+ "                                                                                                    <tr>\r\n"
					+ "                                                                                                        <td style=\"margin:0;padding:0;font-size:14px;text-align:left;color:#4b4649;line-height:21px;font-family:Helvetica,Arial,sans-serif;font-weight:normal\">\r\n"
					+ "                                                                                                        								You participated Successfully for the"+event.getTitle() + " " + event.getEventDate().get(Calendar.YEAR) + "(AIREP)</td>\r\n"
					+ "                                                                                                    </tr>\r\n"
					+ "                                                                                                    <tr>\r\n"
					+ "                                                                                                        <td style=\"line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left\" height=\"15\">&nbsp;</td>\r\n"
					+ "                                                                                                    </tr>\r\n"
					+ "                                                                                                    <tr>\r\n"
					+ "                                                                                                        <td style=\"margin:0;padding:0;font-size:14px;text-align:left;color:#4b4649;line-height:21px;font-family:Helvetica,Arial,sans-serif;font-weight:normal\">\r\n"
					+ "                                                                                                            <strong>About <span class=\"il\">Event</span>:</strong>\r\n"
					+ "                                                                                                            "+ event.getEventData() +" " 
					+ "...\r\n"                                                                                                            
					+ "                                                                                                        </td>\r\n"
					+ "                                                                                                    </tr>\r\n"
					+ "                                                                                                    <tr>\r\n"
					+ "                                                                                                        <td style=\"line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left\" height=\"15\">&nbsp;</td>\r\n"
					+ "                                                                                                    </tr>\r\n"
					+ "                                                                                                    <tr>\r\n"
					+ "                                                                                                        <td align=\"center\">\r\n"
					+ "                                                                                                            <table style=\"border-collapse:collapse;border-spacing:0\" width=\"250\">\r\n"
					+ "                                                                                                                <tbody>\r\n"
					+ "                                                                                                                    <tr>\r\n"
					+ "                                                                                                                        <td style=\"background-color:#d7263d;text-align:center;color:#ffffff;border-radius:3px;font-size:16px;text-decoration:none;font-weight:bold\">\r\n"
					+ "                                                                                                                        </td>\r\n"
					+ "                                                                                                                    </tr>\r\n"
					+ "                                                                                                                </tbody>\r\n"
					+ "                                                                                                            </table>\r\n"
					+ "                                                                                                        </td>\r\n"
					+ "                                                                                                    </tr>\r\n"
					+ "                                                                                                </tbody></table>\r\n"
					+ "                                                                                            </td>\r\n"
					+ "                                                                                        </tr>\r\n"
					+ "                                                                                        \r\n"
					+ "                                                                                        \r\n"
					+ "                                                                                        <tr>\r\n"
					+ "                                                                                            <td style=\"padding:0px\">\r\n"
					+ "                                                                                              \r\n"
					+ "                                                                                            </td>\r\n"
					+ "                                                                                        </tr>\r\n"
					+ "                                                                                    </tbody></table>\r\n"
					+ "                                                                                    <table width=\"165\" align=\"right\" border=\"0\" style=\"border-collapse:collapse;border-spacing:0\">\r\n"
					+ "                                                                                        <tbody><tr>\r\n"
					+ "                                                                                            <td style=\"line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left\" height=\"16\">&nbsp;</td>\r\n"
					+ "                                                                                        </tr>\r\n"
					+ "                                                                                        \r\n"
					+ "                                                                                        <tr>\r\n"
					+ "                                                                                            <td style=\"padding:0px\">\r\n"
					+ "                                                                                                <table width=\"100%\" border=\"0\" style=\"border-collapse:collapse;border-spacing:0\">\r\n"
					+ "                                                                                                    <tbody><tr>\r\n"
					+ "                                                                                                        <td style=\"padding:0px;background-color:#f2f2f2;border:1px solid #f2f2f2\">\r\n"
					+ "                                                                                                            <table width=\"100%\" border=\"0\" style=\"border-collapse:collapse;border-spacing:0\">\r\n"
					+ "                                                                                                                <tbody><tr>\r\n"
					+ "                                                                                                                    <td width=\"8\" style=\"padding:0px\">&nbsp;</td>\r\n"
					+ "                                                                                                                    <td style=\"padding:0px\">\r\n"
					+ "                                                                                                                        <table width=\"100%\" border=\"0\">\r\n"
					+ "                                                                                                                            <tbody><tr>\r\n"
					+ "                                                                                                                                <td style=\"line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left\" height=\"8\">&nbsp;</td>\r\n"
					+ "                                                                                                                            </tr>\r\n"
					+ "                                                                                                                            <tr>\r\n"
					+ "                                                                                                                                <td style=\"margin:0;padding:0;font-size:16px;text-align:left;color:#4b4649;line-height:21px;font-family:Helvetica,Arial,sans-serif;font-weight:bold\">Event Details:</td>\r\n"
					+ "                                                                                                                            </tr>\r\n"
					+ "                                                                                                                            <tr>\r\n"
					+ "                                                                                                                                <td style=\"line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left\" height=\"8\">&nbsp;</td>\r\n"
					+ "                                                                                                                            </tr>\r\n"
					+ "                                                                                                                        </tbody></table>\r\n"
					+ "                                                                                                                    </td>\r\n"
					+ "                                                                                                                    <td width=\"8\" style=\"padding:0px\">&nbsp;</td>\r\n"
					+ "                                                                                                                </tr>\r\n"
					+ "                                                                                                            </tbody></table>\r\n"
					+ "                                                                                                        </td>\r\n"
					+ "                                                                                                    </tr>\r\n"
					+ "\r\n"
					+ "                                                                                                    <tr>\r\n"
					+ "                                                                                                        <td style=\"padding:0px;border:1px solid #dadada;border-top:none\">\r\n"
					+ "                                                                                                            <table width=\"100%\" border=\"0\" style=\"border-collapse:collapse;border-spacing:0\">\r\n"
					+ "                                                                                                                <tbody><tr>\r\n"
					+ "                                                                                                                    <td width=\"8\" style=\"padding:0px\">&nbsp;</td>\r\n"
					+ "                                                                                                                    <td style=\"padding:0px\">\r\n"
					+ "\r\n"
					+ "                                                                                                                        <table width=\"100%\" border=\"0\" style=\"border-collapse:collapse;border-spacing:0\">\r\n"
					+ "                                                                                                                            <tbody><tr>\r\n"
					+ "                                                                                                                                <td style=\"line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left\" height=\"8\">&nbsp;</td>\r\n"
					+ "                                                                                                                            </tr>\r\n"
					+ "                                                                                                                            <tr>\r\n"
					+ "                                                                                                                                <td style=\"padding:0px;text-align:center\">\r\n"
					+ "                                                                                                                                    <img style=\"width:100%;max-height:150px\" src=" + event.getPhoto() +"alt=\"image\" class=\"CToWUd\">\r\n"
					+ "                                                                                                                                </td>\r\n"
					+ "                                                                                                                            </tr>\r\n"
					+ "                                                                                                                            <tr>\r\n"
					+ "                                                                                                                                <td style=\"line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left\" height=\"8\">&nbsp;</td>\r\n"
					+ "                                                                                                                            </tr>\r\n"
					+ "                                                                                                                            <tr>\r\n"
					+ "                                                                                                                                <td style=\"margin:0;padding:0;font-size:16px;text-align:left;color:#4b4649;line-height:21px;font-family:Helvetica,Arial,sans-serif;font-weight:bold\">Su lakhiye suhanee ahi?</td>\r\n"
					+ "                                                                                                                            </tr>\r\n"
					+ "                                                                                                                            <tr>\r\n"
					+ "                                                                                                                                <td style=\"margin:0;padding:0;font-size:13px;text-align:left;color:#4b4649;line-height:20px;font-family:Helvetica,Arial,sans-serif;font-weight:normal\">\r\n"
					+ "                                                                                                                                											?											</td>\r\n"
					+ "                                                                                                                            </tr>\r\n"
					+ "                                                                                                                            <tr>\r\n"
					+ "                                                                                                                                <td style=\"line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left\" height=\"8\">&nbsp;</td>\r\n"
					+ "                                                                                                                            </tr>\r\n"
					+ "                                                                                                                            <tr>\r\n"
					                                                                                                                            
					+ "                                                                                                                            </tr>\r\n"
					+ "                                                                                                                            <tr>\r\n"
					+ "                                                                                                                                <td style=\"line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left\" height=\"8\">&nbsp;</td>\r\n"
					+ "                                                                                                                            </tr>\r\n"
					+ "                                                                                                                        </tbody></table>\r\n"
					+ "\r\n"
					+ "                                                                                                                    </td>\r\n"
					+ "                                                                                                                    <td width=\"8\" style=\"padding:0px\">&nbsp;</td>\r\n"
					+ "                                                                                                                </tr>\r\n"
					+ "                                                                                                            </tbody></table>\r\n"
					+ "                                                                                                        </td>\r\n"
					+ "                                                                                                    </tr>\r\n"
					+ "\r\n"
					+ "                                                                                                </tbody></table>\r\n"
					+ "                                                                                            </td>\r\n"
					+ "                                                                                        </tr>\r\n"
					+ "                                                                                        \r\n"
					+ "                                                                                        <tr>\r\n"
					+ "                                                                                            <td style=\"line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left\" height=\"16\">&nbsp;</td>\r\n"
					+ "                                                                                        </tr>\r\n"
					+ "                                                                                    </tbody></table>\r\n"
					+ "                                                                                </td>\r\n"
					+ "                                                                            </tr>\r\n"
					+ "                                                                        </tbody></table>\r\n"
					+ "                                                                    </td>\r\n"
				
					+ "                                                    <tr>\r\n"
					+ "                                                        <td style=\"margin:0;color:#4b4649;font-size:14px;line-height:21px;font-family:Arial,Helvetica,sans-serif;font-style:normal;font-weight:normal;text-align:left\">\r\n"
					+ "                                                            Warm Regards,\r\n"
					+ "                                                            <br>\r\n"
					+ "\r\n"
					+ "                                                                      Team VMS \r\n"
					+ "                                                        </td>\r\n"
					+ "                                                    </tr>\r\n"
					+ "                                                    <tr>\r\n"
					+ "                                                        <td style=\"line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left\" height=\"16\">&nbsp;</td>\r\n"
					+ "                                                    </tr>\r\n"
					+ "                                        \r\n"
					+ "\r\n"
					+ "                                    </tbody></table>\r\n"
					+ "                                </td>\r\n"
					+ "                            </tr>\r\n"
					+ "\r\n"
					+ "                    </tbody></table>\r\n"
					+ "\r\n"
					+ "                    \r\n"
					+ "                </td>\r\n"
					+ "            </tr>\r\n"
					+ "        </tbody>\r\n"
					+ "    </table>"
					, "text/html");
			
			mimeMessageHelper.setFrom("sdkproject2021@gmail.com");
			mimeMessageHelper.setTo(mail);
			
			System.out.println("photo"+event.getPhoto());
			System.out.println(event);
			
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
			mimeMessage.setContent("<div style=\"padding:0;margin:0 auto;width:100%!important;font-family:Lato-Semibold,Helvetica Neue,Arial,&quot;Lucida Grande&quot;,sans-serif\">"
					+ " <div style=\"overflow:hidden;color:transparent;width:0;font-size:0;opacity:0;height:0\">" 
					+ "</div> <table role=\"presentation\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#F3F2EF\" style=\"background-color:#f3f2ef;table-layout:fixed\"> <tbody> <tr> <td align=\"center\" style=\"padding-top:24px\"> <center style=\"width:100%\"> <table role=\"presentation\" border=\"0\" class=\"m_-6280757255082102389mercado-email-container\" cellspacing=\"0\" cellpadding=\"0\" width=\"512\" bgcolor=\"#FFFFFF\" style=\"background-color:#ffffff;margin:0 auto;max-width:512px;width:inherit\"> <tbody> <tr> <td bgcolor=\"#FFFFFF\" style=\"background-color:#ffffff;padding:18px 24px 0 24px\">"
					+ " <table role=\"presentation\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100%!important;min-width:100%!important\"> <tbody> <tr> <td align=\"left\" valign=\"middle\"><a href=\"http//localhost:4200\" style=\"color:#0a66c2;display:inline-block;text-decoration:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.linkedin.com/comm/feed/?midToken%3DAQE8wlgslKNGuA%26midSig%3D0c6QrPJ8hel9M1%26trk%3Deml-b2_professional_identity_digest_02-header-2-home%26trkEmail%3Deml-b2_professional_identity_digest_02-header-2-home-null-b6wnhu%257Ekou2uxcp%257Ev1-null-neptune%252Ffeed%26lipi%3Durn%253Ali%253Apage%253Aemail_b2_professional_identity_digest_02%253BV1uMcwZjRxamgtOdaaPUzw%253D%253D&amp;source=gmail&amp;ust=1621709651230000&amp;usg=AFQjCNHsF1UuteXP7UC3uiYnU_c7BYRdAA\"> "
					+ "<img alt=\"LinkedIn\" border=\"0\" src=\"https://firebasestorage.googleapis.com/v0/b/volunteacher-management-system.appspot.com/o/Suhanee-logo.png?alt=media&token=63d69178-55a0-4cd6-bb4d-799fe4ee55b7\" height=\"50\" width=\"84\" style=\"outline:none;color:#ffffff;text-decoration:none\" class=\"CToWUd\"></a></td> <td valign=\"middle\" width=\"100%\" align=\"right\"><a href=\"http//localhost:4200\" style=\"margin:0;color:#0a66c2;display:inline-block;text-decoration:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.linkedin.com/comm/in/dhrumil-gohil-75677516a?midToken%3DAQE8wlgslKNGuA%26midSig%3D0c6QrPJ8hel9M1%26trk%3Deml-b2_professional_identity_digest_02-header-10-profile%26trkEmail%3Deml-b2_professional_identity_digest_02-header-10-profile-null-b6wnhu%257Ekou2uxcp%257Ev1-null-neptune%252Fprofile%257Evanity%252Eview%26lipi%3Durn%253Ali%253Apage%253Aemail_b2_professional_identity_digest_02%253BV1uMcwZjRxamgtOdaaPUzw%253D%253D&amp;source=gmail&amp;ust=1621709651231000&amp;usg=AFQjCNE74Fuy8uMMdSaWMkf56UYz9g79hg\"> "
					+ "<table role=\"presentation\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\"> <tbody> <tr> <td align=\"left\" valign=\"middle\" style=\"padding:0 0 0 10px\"></td></tr> </tbody> </table></a></td> </tr> </tbody> </table></td> </tr> <tr> <td> <table role=\"presentation\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\"> <tbody> <tr> <td style=\"padding:26px\"> <table role=\"presentation\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\"> <tbody> <tr> <td align=\"center\" style=\"color:#000000;font-weight:bold;font-size:24px\">You have notification from <b>AIREP</b></td> </tr> <td> </td> </tr> <tr> <td align=\"center\" style=\"color:#8f5849;font-size:40px\">Session on <b>"+ notification.getSession().getSessionDate().get(Calendar.DAY_OF_MONTH) +"- " + notification.getSession().getSessionDate().get(Calendar.MONTH) + "- " + notification.getSession().getSessionDate().get(Calendar.YEAR) + "</b> </td> </tr>  <tr> <td> <table width=\"1\" border=\"0\" cellspacing=\"0\" cellpadding=\"1\"> <tbody> <tr> <td> <div style=\"height:24px;font-size:24px;line-height:24px\"> &nbsp; </div></td> </tr> </tbody> </table></td> </tr> <tr> <td align=\"center\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"display:inline-block\"> <tbody> <tr> <td align=\"center\" valign=\"middle\" style=\"font-size:16px\"><a href=\"https://www.linkedin.com/comm/me/profile-views?midToken=AQE8wlgslKNGuA&amp;midSig=0c6QrPJ8hel9M1&amp;trk=eml-b2_professional_identity_digest_02-wvmp-0-null&amp;trkEmail=eml-b2_professional_identity_digest_02-wvmp-0-null-null-b6wnhu%7Ekou2uxcp%7Ev1-null-neptune%2Fme%2Eprofile%7Eviews&amp;lipi=urn%3Ali%3Apage%3Aemail_b2_professional_identity_digest_02%3BV1uMcwZjRxamgtOdaaPUzw%3D%3D\" style=\"word-wrap:normal;color:#0a66c2;word-break:normal;white-space:nowrap;display:block;text-decoration:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.linkedin.com/comm/me/profile-views?midToken%3DAQE8wlgslKNGuA%26midSig%3D0c6QrPJ8hel9M1%26trk%3Deml-b2_professional_identity_digest_02-wvmp-0-null%26trkEmail%3Deml-b2_professional_identity_digest_02-wvmp-0-null-null-b6wnhu%257Ekou2uxcp%257Ev1-null-neptune%252Fme%252Eprofile%257Eviews%26lipi%3Durn%253Ali%253Apage%253Aemail_b2_professional_identity_digest_02%253BV1uMcwZjRxamgtOdaaPUzw%253D%253D&amp;source=gmail&amp;ust=1621709651231000&amp;usg=AFQjCNGH6I1GPr_pJhgIfNn6SyL46Ky3Sg\"> <table role=\"presentation\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\"> <tbody> <tr> <td bgcolor=\"#0A66C2\" style=\"padding:12px 24px;color:#ffffff;font-weight:400;display:inline-block;text-decoration:none;font-size:16px;line-height:1.25em;border-color:#0a66c2;background-color:#0a66c2;border-radius:34px;border-width:1px;border-style:solid\"><a href=\"https://www.linkedin.com/comm/me/profile-views?midToken=AQE8wlgslKNGuA&amp;midSig=0c6QrPJ8hel9M1&amp;trk=eml-b2_professional_identity_digest_02-wvmp-0-null&amp;trkEmail=eml-b2_professional_identity_digest_02-wvmp-0-null-null-b6wnhu%7Ekou2uxcp%7Ev1-null-neptune%2Fme%2Eprofile%7Eviews&amp;lipi=urn%3Ali%3Apage%3Aemail_b2_professional_identity_digest_02%3BV1uMcwZjRxamgtOdaaPUzw%3D%3D\" style=\"word-wrap:normal;color:#ffffff;word-break:normal;white-space:nowrap;display:block;text-decoration:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.linkedin.com/comm/me/profile-views?midToken%3DAQE8wlgslKNGuA%26midSig%3D0c6QrPJ8hel9M1%26trk%3Deml-b2_professional_identity_digest_02-wvmp-0-null%26trkEmail%3Deml-b2_professional_identity_digest_02-wvmp-0-null-null-b6wnhu%257Ekou2uxcp%257Ev1-null-neptune%252Fme%252Eprofile%257Eviews%26lipi%3Durn%253Ali%253Apage%253Aemail_b2_professional_identity_digest_02%253BV1uMcwZjRxamgtOdaaPUzw%253D%253D&amp;source=gmail&amp;ust=1621709651231000&amp;usg=AFQjCNGH6I1GPr_pJhgIfNn6SyL46Ky3Sg\">See Notification</a></td> </tr> </tbody> </table></a></td> </tr>  <tr> <td height=\"3\"> <table width=\"1\" border=\"0\" cellspacing=\"0\" cellpadding=\"1\"> <tbody> <tr> <td> <div style=\"height:1px;font-size:1px;line-height:1px\"> &nbsp; </div></td> </tr> </tbody> </table></td> </tr>  </tbody> </table></td> </tr> <tr> <td> <table width=\"1\" border=\"0\" cellspacing=\"0\" cellpadding=\"1\"> <tbody> <tr> <td> <div style=\"height:8px;font-size:8px;line-height:8px\"> &nbsp; </div></td> </tr> </tbody> </table></td> </tr> </tbody> </table></td> </tr> </tbody> </table></td> </tr> <tr> <td> <table role=\"presentation\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#F3F2EF\" align=\"left\" style=\"background-color:#f3f2ef;padding-top:16px;color:#000000;text-align:left\"> <tbody> <tr> <td> <table width=\"24\" border=\"0\" cellspacing=\"0\" cellpadding=\"1\"> <tbody> <tr> <td> <div style=\"height:0px;font-size:0px;line-height:0px\"> &nbsp; </div></td> </tr> </tbody> </table></td> <td> <table role=\"presentation\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\"> <tbody> <tr> <td> <table role=\"presentation\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\"> <tbody> <tr> <td> <table width=\"1\" border=\"0\" cellspacing=\"0\" cellpadding=\"1\"> <tbody> <tr> <td> <div style=\"height:12px;font-size:12px;line-height:12px\"> &nbsp; </div></td> </tr> </tbody> </table></td> </tr> <tr> <td align=\"left\" style=\"padding:0;color:#000000;text-align:left\"> <p style=\"margin:0;word-wrap:break-word;color:#000000;word-break:break-word;font-weight:400;font-size:12px;line-height:1.333\">"
					+ "</tr> <tr> <td> <table width=\"1\" border=\"0\" cellspacing=\"0\" cellpadding=\"1\"> <tbody> <tr> <td> <div style=\"height:24px;font-size:24px;line-height:24px\"> &nbsp; </div></td> </tr> </tbody> </table></td> </tr> </tbody> </table></td> </tr> </tbody> </table></td> <td> <table width=\"24\" border=\"0\" cellspacing=\"0\" cellpadding=\"1\"> <tbody> <tr> <td> <div style=\"height:0px;font-size:0px;line-height:0px\"> &nbsp; </div></td> </tr> </tbody> </table></td> </tr> </tbody> </table></td> </tr> </tbody> </table> </center></td> </tr> </tbody> </table><div class=\"yj6qo\"></div><div class=\"adL\"> </div></div>", "text/html");
			
			
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
			mimeMessage.setContent("<div style=\"padding:0;margin:0 auto;width:100%!important;font-family:Lato-Semibold,Helvetica Neue,Arial,&quot;Lucida Grande&quot;,sans-serif\">"
					+ " <div style=\"overflow:hidden;color:transparent;width:0;font-size:0;opacity:0;height:0\">" 
					+ "</div> <table role=\"presentation\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#F3F2EF\" style=\"background-color:#f3f2ef;table-layout:fixed\"> <tbody> <tr> <td align=\"center\" style=\"padding-top:24px\"> <center style=\"width:100%\"> <table role=\"presentation\" border=\"0\" class=\"m_-6280757255082102389mercado-email-container\" cellspacing=\"0\" cellpadding=\"0\" width=\"512\" bgcolor=\"#FFFFFF\" style=\"background-color:#ffffff;margin:0 auto;max-width:512px;width:inherit\"> <tbody> <tr> <td bgcolor=\"#FFFFFF\" style=\"background-color:#ffffff;padding:18px 24px 0 24px\">"
					+ " <table role=\"presentation\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100%!important;min-width:100%!important\"> <tbody> <tr> <td align=\"left\" valign=\"middle\"><a href=\"http//localhost:4200\" style=\"color:#0a66c2;display:inline-block;text-decoration:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.linkedin.com/comm/feed/?midToken%3DAQE8wlgslKNGuA%26midSig%3D0c6QrPJ8hel9M1%26trk%3Deml-b2_professional_identity_digest_02-header-2-home%26trkEmail%3Deml-b2_professional_identity_digest_02-header-2-home-null-b6wnhu%257Ekou2uxcp%257Ev1-null-neptune%252Ffeed%26lipi%3Durn%253Ali%253Apage%253Aemail_b2_professional_identity_digest_02%253BV1uMcwZjRxamgtOdaaPUzw%253D%253D&amp;source=gmail&amp;ust=1621709651230000&amp;usg=AFQjCNHsF1UuteXP7UC3uiYnU_c7BYRdAA\"> "
					+ "<img alt=\"LinkedIn\" border=\"0\" src=\"https://firebasestorage.googleapis.com/v0/b/volunteacher-management-system.appspot.com/o/Suhanee-logo.png?alt=media&token=63d69178-55a0-4cd6-bb4d-799fe4ee55b7\" height=\"50\" width=\"84\" style=\"outline:none;color:#ffffff;text-decoration:none\" class=\"CToWUd\"></a></td> <td valign=\"middle\" width=\"100%\" align=\"right\"><a href=\"http//localhost:4200\" style=\"margin:0;color:#0a66c2;display:inline-block;text-decoration:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.linkedin.com/comm/in/dhrumil-gohil-75677516a?midToken%3DAQE8wlgslKNGuA%26midSig%3D0c6QrPJ8hel9M1%26trk%3Deml-b2_professional_identity_digest_02-header-10-profile%26trkEmail%3Deml-b2_professional_identity_digest_02-header-10-profile-null-b6wnhu%257Ekou2uxcp%257Ev1-null-neptune%252Fprofile%257Evanity%252Eview%26lipi%3Durn%253Ali%253Apage%253Aemail_b2_professional_identity_digest_02%253BV1uMcwZjRxamgtOdaaPUzw%253D%253D&amp;source=gmail&amp;ust=1621709651231000&amp;usg=AFQjCNE74Fuy8uMMdSaWMkf56UYz9g79hg\"> "
					+ "<table role=\"presentation\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\"> <tbody> <tr> <td align=\"left\" valign=\"middle\" style=\"padding:0 0 0 10px\"></td></tr> </tbody> </table></a></td> </tr> </tbody> </table></td> </tr> <tr> <td> <table role=\"presentation\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\"> <tbody> <tr> <td style=\"padding:26px\"> <table role=\"presentation\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\"> <tbody> <tr> <td align=\"center\" style=\"color:#000000;font-weight:bold;font-size:24px\">You have notification from <b>AIREP</b></td> </tr> <td> </td> </tr> <tr> <td align=\"center\" style=\"color:#8f5849;font-size:40px\">Event on <b>"+ notification.getEvent().getEventDate().get(Calendar.DAY_OF_MONTH) +"- " + notification.getEvent().getEventDate().get(Calendar.MONTH) + "- " + notification.getEvent().getEventDate().get(Calendar.YEAR) + "</b> </td> </tr>  <tr> <td> <table width=\"1\" border=\"0\" cellspacing=\"0\" cellpadding=\"1\"> <tbody> <tr> <td> <div style=\"height:24px;font-size:24px;line-height:24px\"> &nbsp; </div></td> </tr> </tbody> </table></td> </tr> <tr> <td align=\"center\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"display:inline-block\"> <tbody> <tr> <td align=\"center\" valign=\"middle\" style=\"font-size:16px\"><a href=\"https://www.linkedin.com/comm/me/profile-views?midToken=AQE8wlgslKNGuA&amp;midSig=0c6QrPJ8hel9M1&amp;trk=eml-b2_professional_identity_digest_02-wvmp-0-null&amp;trkEmail=eml-b2_professional_identity_digest_02-wvmp-0-null-null-b6wnhu%7Ekou2uxcp%7Ev1-null-neptune%2Fme%2Eprofile%7Eviews&amp;lipi=urn%3Ali%3Apage%3Aemail_b2_professional_identity_digest_02%3BV1uMcwZjRxamgtOdaaPUzw%3D%3D\" style=\"word-wrap:normal;color:#0a66c2;word-break:normal;white-space:nowrap;display:block;text-decoration:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.linkedin.com/comm/me/profile-views?midToken%3DAQE8wlgslKNGuA%26midSig%3D0c6QrPJ8hel9M1%26trk%3Deml-b2_professional_identity_digest_02-wvmp-0-null%26trkEmail%3Deml-b2_professional_identity_digest_02-wvmp-0-null-null-b6wnhu%257Ekou2uxcp%257Ev1-null-neptune%252Fme%252Eprofile%257Eviews%26lipi%3Durn%253Ali%253Apage%253Aemail_b2_professional_identity_digest_02%253BV1uMcwZjRxamgtOdaaPUzw%253D%253D&amp;source=gmail&amp;ust=1621709651231000&amp;usg=AFQjCNGH6I1GPr_pJhgIfNn6SyL46Ky3Sg\"> <table role=\"presentation\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\"> <tbody> <tr> <td bgcolor=\"#0A66C2\" style=\"padding:12px 24px;color:#ffffff;font-weight:400;display:inline-block;text-decoration:none;font-size:16px;line-height:1.25em;border-color:#0a66c2;background-color:#0a66c2;border-radius:34px;border-width:1px;border-style:solid\"><a href=\"https://www.linkedin.com/comm/me/profile-views?midToken=AQE8wlgslKNGuA&amp;midSig=0c6QrPJ8hel9M1&amp;trk=eml-b2_professional_identity_digest_02-wvmp-0-null&amp;trkEmail=eml-b2_professional_identity_digest_02-wvmp-0-null-null-b6wnhu%7Ekou2uxcp%7Ev1-null-neptune%2Fme%2Eprofile%7Eviews&amp;lipi=urn%3Ali%3Apage%3Aemail_b2_professional_identity_digest_02%3BV1uMcwZjRxamgtOdaaPUzw%3D%3D\" style=\"word-wrap:normal;color:#ffffff;word-break:normal;white-space:nowrap;display:block;text-decoration:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.linkedin.com/comm/me/profile-views?midToken%3DAQE8wlgslKNGuA%26midSig%3D0c6QrPJ8hel9M1%26trk%3Deml-b2_professional_identity_digest_02-wvmp-0-null%26trkEmail%3Deml-b2_professional_identity_digest_02-wvmp-0-null-null-b6wnhu%257Ekou2uxcp%257Ev1-null-neptune%252Fme%252Eprofile%257Eviews%26lipi%3Durn%253Ali%253Apage%253Aemail_b2_professional_identity_digest_02%253BV1uMcwZjRxamgtOdaaPUzw%253D%253D&amp;source=gmail&amp;ust=1621709651231000&amp;usg=AFQjCNGH6I1GPr_pJhgIfNn6SyL46Ky3Sg\">See Notification</a></td> </tr> </tbody> </table></a></td> </tr>  <tr> <td height=\"3\"> <table width=\"1\" border=\"0\" cellspacing=\"0\" cellpadding=\"1\"> <tbody> <tr> <td> <div style=\"height:1px;font-size:1px;line-height:1px\"> &nbsp; </div></td> </tr> </tbody> </table></td> </tr>  </tbody> </table></td> </tr> <tr> <td> <table width=\"1\" border=\"0\" cellspacing=\"0\" cellpadding=\"1\"> <tbody> <tr> <td> <div style=\"height:8px;font-size:8px;line-height:8px\"> &nbsp; </div></td> </tr> </tbody> </table></td> </tr> </tbody> </table></td> </tr> </tbody> </table></td> </tr> <tr> <td> <table role=\"presentation\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#F3F2EF\" align=\"left\" style=\"background-color:#f3f2ef;padding-top:16px;color:#000000;text-align:left\"> <tbody> <tr> <td> <table width=\"24\" border=\"0\" cellspacing=\"0\" cellpadding=\"1\"> <tbody> <tr> <td> <div style=\"height:0px;font-size:0px;line-height:0px\"> &nbsp; </div></td> </tr> </tbody> </table></td> <td> <table role=\"presentation\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\"> <tbody> <tr> <td> <table role=\"presentation\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\"> <tbody> <tr> <td> <table width=\"1\" border=\"0\" cellspacing=\"0\" cellpadding=\"1\"> <tbody> <tr> <td> <div style=\"height:12px;font-size:12px;line-height:12px\"> &nbsp; </div></td> </tr> </tbody> </table></td> </tr> <tr> <td align=\"left\" style=\"padding:0;color:#000000;text-align:left\"> <p style=\"margin:0;word-wrap:break-word;color:#000000;word-break:break-word;font-weight:400;font-size:12px;line-height:1.333\">"
					+ "</tr> <tr> <td> <table width=\"1\" border=\"0\" cellspacing=\"0\" cellpadding=\"1\"> <tbody> <tr> <td> <div style=\"height:24px;font-size:24px;line-height:24px\"> &nbsp; </div></td> </tr> </tbody> </table></td> </tr> </tbody> </table></td> </tr> </tbody> </table></td> <td> <table width=\"24\" border=\"0\" cellspacing=\"0\" cellpadding=\"1\"> <tbody> <tr> <td> <div style=\"height:0px;font-size:0px;line-height:0px\"> &nbsp; </div></td> </tr> </tbody> </table></td> </tr> </tbody> </table></td> </tr> </tbody> </table> </center></td> </tr> </tbody> </table><div class=\"yj6qo\"></div><div class=\"adL\"> </div></div>", "text/html");
			
			
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
//			mimeMessage.setContent("<div style='height:200px; background-color: #DCDCDC'>"
//					+ "<br><center><div class='box' style='height: 150px;width: 60%;background-color: white;'>"
//					+ "<center><div class='card'"
//					+ "  max-width: 300px;"
//					+ "  margin: auto;"
//					+ "  text-align: center;"
//					+ "  font-family: arial;'>"
//					+ "  <h2 style='color:gray;text-align:center'>Your request Accepted Successfully<br>"
//					+ "	Please fill form from below link to proceed for Login.</h2>"
//					+ "  <p><center><a href='http://localhost:4200/volunteacher-registration' target=\\\"_blank\\\" style='color:white';text-decoration:none;><button style='border: none;\r\n"
//					+ "  outline: 0;"
//					+ "  padding: 12px;"
//					+ "  color: white;"
//					+ "  background-color: #5cb85c;"
//					+ "  text-align: center;"
//					+ "  cursor: pointer;"
//					+ "  font-size: 18px;'>Login</button></a></center></p>"
//					+ "</div></div></div></center>", "text/html");
			
			mimeMessage.setContent("<div style=\"margin:0;padding:0\" dir=\"ltr\" bgcolor=\"#ffffff\">"
					+ "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" id=\"m_1686811882427449699email_table\" style=\"border-collapse:collapse\"><tbody><tr><td id=\"m_1686811882427449699email_content\" style=\"font-family:Helvetica Neue,Helvetica,Lucida Grande,tahoma,verdana,arial,sans-serif;background:#ffffff\">"
					+ "<table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\"><tbody><tr><td height=\"20\" style=\"line-height:20px\" colspan=\"3\">&nbsp;</td></tr><tr><td height=\"1\" colspan=\"3\" style=\"line-height:1px\"></td></tr><tr><td><table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;text-align:center;width:100%\"><tbody>"
					+ "<tr><td width=\"15px\" style=\"width:15px\"></td><td style=\"line-height:0px;max-width:600px;padding:0 0 15px 0\"><table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\"><tbody><tr><td style=\"width:100%;text-align:left;height:33px\"><img height=\"33\" src=\"https://firebasestorage.googleapis.com/v0/b/volunteacher-management-system.appspot.com/o/Suhanee-logo.png?alt=media&token=63d69178-55a0-4cd6-bb4d-799fe4ee55b7\" style=\"border:0\" class=\"CToWUd\"></td></tr></tbody></table>"
					+ "</td><td width=\"15px\" style=\"width:15px\"></td></tr></tbody></table></td></tr><tr><td><table border=\"0\" width=\"430\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;margin:0 auto 0 auto\"><tbody><tr><td><table border=\"0\" width=\"430px\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;margin:0 auto 0 auto;width:430px\"><tbody><tr><td width=\"15\" style=\"display:block;width:15px\">&nbsp;&nbsp;&nbsp;</td></tr><tr><td><table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\"><tbody><tr><td>"
					+ "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\"><tbody><tr><td width=\"20\" style=\"display:block;width:20px\">&nbsp;&nbsp;&nbsp;</td><td><table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\"><tbody><tr><td></td></tr><tr><td><p style=\"margin:10px 0 10px 0;color:#565a5c;font-size:18px\">Hi  "+ request.getName()+",</p><p style=\"margin:10px 0 10px 0;color:#565a5c;font-size:18px\">Your request to join in AIREP as a Volunteacher is successfully accepted By VMS team. Now You can login into our System from below link.</p></td></tr><tr></tr><tr><td height=\"10\" style=\"line-height:10px\" colspan=\"1\">&nbsp;"
					+ "</td></tr><tr><td><a href=\"https://localhost:4200/login\" style=\"color:#3b5998;text-decoration:none;display:block;width:370px\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://instagram.com/accounts/password/reset/confirm/?uidb36%3D3s1ma8p%26token%3D7CO7BB94ygQeEU3Qjg40Fq9c0qkMaoSU3wno4yheZ1zb3obMY7VveeVVQHE5uUt1:password_reset_email%26v%3D186.0.0.36.128%26s%3Dpassword_reset_email&amp;source=gmail&amp;ust=1621723050030000&amp;usg=AFQjCNEQrMZh6Ef_tSvpTkEjrngeliFcQw\">"
					+ "<table border=\"0\" width=\"390\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\"><tbody><tr><td style=\"border-collapse:collapse;border-radius:3px;text-align:center;display:block;border:solid 1px #009fdf;padding:10px 16px 14px 16px;margin:0 2px 0 auto;min-width:80px;background-color:#47a2ea\"><a href=\"https://localhost:4200/login\" style=\"color:#3b5998;text-decoration:none;display:block\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://instagram.com/accounts/password/reset/confirm/?uidb36%3D3s1ma8p%26token%3D7CO7BB94ygQeEU3Qjg40Fq9c0qkMaoSU3wno4yheZ1zb3obMY7VveeVVQHE5uUt1:password_reset_email%26v%3D186.0.0.36.128%26s%3Dpassword_reset_email&amp;source=gmail&amp;ust=1621723050030000&amp;usg=AFQjCNEQrMZh6Ef_tSvpTkEjrngeliFcQw\"><center><font size=\"3\"><span style=\"font-family:Helvetica Neue,Helvetica,Roboto,Arial,sans-serif;white-space:nowrap;font-weight:bold;vertical-align:middle;color:#fdfdfd;font-size:16px;line-height:16px\">Get&nbsp;Started</span></font></center></a></td></tr></tbody></table>"
					+ "</a></td></tr><tr><td height=\"10\" style=\"line-height:10px\" colspan=\"1\">&nbsp;</td></tr><tr><td><p style=\"margin:10px 0 10px 0;color:#565a5c;font-size:18px\">If you will not be able to login into our system then feel free to send a mail on <a href='mailto:sdkproject2021@gmail.com'\"\">Email id.</a>.</p></td></tr></tbody></table></td><td width=\"20\" style=\"display:block;width:20px\">&nbsp;&nbsp;&nbsp;</td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr><tr><td><table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;margin:0 auto 0 auto;width:100%;max-width:600px\"><tbody><tr><td height=\"4\" style=\"line-height:4px\" colspan=\"3\">&nbsp;</td></tr><tr><td width=\"15px\" style=\"width:15px\"></td><td width=\"20\" style=\"display:block;width:20px\">&nbsp;&nbsp;&nbsp;</td><td style=\"text-align:center\"><div style=\"padding-top:10px;display:flex\">"
					+ "<br></div></td><td width=\"20\" style=\"display:block;width:20px\">&nbsp;&nbsp;&nbsp;</td><td width=\"15px\" style=\"width:15px\"></td></tr><tr><td height=\"32\" style=\"line-height:32px\" colspan=\"3\">&nbsp;</td></tr></tbody></table></td></tr><tr><td height=\"20\" style=\"line-height:20px\" colspan=\"3\">&nbsp;</td></tr></tbody></table><span></span></td>"
						+ " <tr >                                                       <td style=color:#4b4649;font-size:14px;line-height:21px;font-family:Arial,Helvetica,sans-serif;font-style:normal;font-weight:normal;text-align:left\">\r\n"
						+ "                                                            <p style=\"margin:0px 10px 0px 25px!important;font-size:18px;font-family:Roboto,RobotoDraft,Helvetica,Arial,sans-serif\">Warm Regards,\r\n"
						+ "                                                            <br>\r\n"
						+ "\r\n"
						+ "                                                                      Team VMS \r\n</p>"
						+ "                                                        </td>\r\n"
						+ "                                                    </tr>\r\n"
						+ "                                                    <tr>\r\n"
						+ "                                                        <td style=\"line-height:0;font-size:0;vertical-align:top;padding:10px 0 10px;text-align:left\" height=\"16\">&nbsp;</td>\r\n"
						+ "                                                    </tr>\r\n"   
					+ "</tr></tbody></table></div>", "text/html");
			
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
