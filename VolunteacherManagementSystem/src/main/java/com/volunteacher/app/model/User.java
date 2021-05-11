package com.volunteacher.app.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Component
public class User {
	
	@Id
	@GeneratedValue(
	    strategy= GenerationType.AUTO,
	    generator="native"
	)
	@GenericGenerator(
	    name = "native",
	    strategy = "native"
	)
	@Column(length=10)
	private Long userId;
	
	@NotNull
	@Column(length=20, nullable = false, columnDefinition = "Char(30)")
	private String userName;
	
	@NotNull
	@Column(length=40, unique=true, nullable = false)
	private String email;
	
	@NotNull
	@Column(length=1, nullable = false , columnDefinition = "TinyInt")
	private int gender;
	
	@NotNull
	@Column(length=10, unique=true, nullable = false)
	private String phoneNumber;
	
	@NotNull
	@JsonFormat(shape = Shape.STRING,pattern = "MM-dd-yyyy")
	@Column(nullable = false, columnDefinition = "DATE")
	private Calendar dob;
	
	@NotNull
	@Column(length=60, nullable = false)
	private String password;

	@NotNull
	@OneToOne
	private UserType type;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "TEXT")
	private String photo;
	
	@Column(length = 10)
	private String userOTP;
	
	@Column(columnDefinition = "TIME")
	@JsonFormat(shape = Shape.STRING,timezone = "IST", pattern = "HH-mm-ss")
	private Calendar userOTPTime;
	
	
	@OneToMany(mappedBy = "createdBy")
	private List<Announcement>announcement;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<SessionReport> sessionReports;	
	
	@OneToOne(mappedBy = "user")
	private Volunteacher volunteacher;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
	private List<TimelinePost> posts;
	
	@OneToMany(mappedBy = "createdBy")
	private List<Notification> notifications;
	
	@ManyToMany(mappedBy = "users")
	private List<Project> projects; 
	
	@ManyToMany(mappedBy = "users")
	private List<Session> sessions; 
	
	
	public User() {
		super();
	}
	
	public Long getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Calendar getDob() {
		return dob;
	}

	public void setDob(Calendar dob) {
		this.dob = dob;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getUserOTP() {
		return userOTP;
	}

	public void setUserOTP(String userOTP) {
		this.userOTP = userOTP;
	}

	public Calendar getUserOTPTime() {
		return userOTPTime;
	}

	public void setUserOTPTime(Calendar userOTPTime) {
		this.userOTPTime = userOTPTime;
	}
}