package com.volunteacher.app.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length=10)
	private int userId;
	
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
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Calendar dob;
	
	@NotNull
	@Column(length=15, nullable = false)
	private String password;
	
	//One User On Type
	@NotNull
	@OneToOne
	private UserType type;
	
	//One User Many Reports- cascade deletion
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<SessionReport> sessionReports;	
	
	//One password of one user
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private Volunteacher volunteacher;
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "createdBy")
	private List<TimelinePost> posts;

	
	public User() {
		super();
	}

	public User( String userName, String email, int gender,
			 String phoneNumber, Calendar dob, String password, UserType type) {
		super();
		this.userName = userName;
		this.email = email;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
		this.password = password;
		this.type = type;
	}
	
	public int getUserId() {
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

	public List<SessionReport> getSessionReports() {
		return sessionReports;
	}

	public void setSessionReports(List<SessionReport> sessionReports) {
		this.sessionReports = sessionReports;
	}

	public Volunteacher getVolunteacher() {
		return volunteacher;
	}

	public void setVolunteacher(Volunteacher volunteacher) {
		this.volunteacher = volunteacher;
	}

	public List<TimelinePost> getPosts() {
		return posts;
	}

	public void setPosts(List<TimelinePost> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", email=" + email + ", gender=" + gender
				+ ", phoneNumber=" + phoneNumber + ", dob=" + dob + ", password=" + password + ", type=" + type
				+ ", sessionReports=" + sessionReports + ", volunteacher=" + volunteacher + ", posts=" + posts + "]";
	}
}
