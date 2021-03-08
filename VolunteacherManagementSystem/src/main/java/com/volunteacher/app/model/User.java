package com.volunteacher.app.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(length=10)
	private Long userId;
	
	@NotNull
	@Column(length=20, nullable = false, columnDefinition = "Char")
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
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(nullable = false, columnDefinition = "DATE")
	private Calendar dob;
	
	@NotNull
	@Column(length=15, nullable = false)
	private String password;
	
	//One User On Type
	@NotNull
	@OneToOne
	private UserType type;
	
	private String photo;
	
	//One User Many Reports- cascade deletion
	@OneToMany(mappedBy = "user")
//	@JsonBackReference
	private List<SessionReport> sessionReports;	
	
	//One password of one user
	@OneToOne(mappedBy = "user")
//	@JsonBackReference
	private Volunteacher volunteacher;
	
	@OneToMany(mappedBy = "createdBy")
//	@JsonBackReference
	private List<TimelinePost> posts;

	public User() {
		super();
	}

	public User(String userName, String email,  int gender, String phoneNumber, Calendar dob,  String password, UserType type) {
		super();
		this.userName = userName;
		this.email = email;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
		this.password = password;
		this.type = type;
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

	
//	public List<SessionReport> getSessionReports() {
//		return sessionReports;
//	}
//
//	public Volunteacher getVolunteacher() {
//		return volunteacher;
//	}
//
//	public List<TimelinePost> getPosts() {
//		return posts;
//	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", email=" + email + ", gender=" + gender
				+ ", phoneNumber=" + phoneNumber + ", dob=" + dob + ", password=" + password + ", type=" + type + "]";
	}


}