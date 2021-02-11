package com.volunteacher.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
public class ApplicantRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 4)
	int requestId;
	
	@NotNull
	@Email
	@Column(length = 40, nullable = false)
	String emailId;
	
	@NotNull
	@Size(min = 3 , max = 20)
	@Column(nullable = false, columnDefinition = "Char", length = 20)
	String name;
	
	@NotNull
	@Column(nullable = false, length = 10, unique = true)
	String phoneNumber;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "TinyInt", length = 1)
	int gender;
	
	@NotNull
	@OneToOne
	UserType userType;
	
	
	public ApplicantRequest() {
		super();
	}

	public ApplicantRequest(String emailId,
		               String name, String phoneNumber, int gender,
			 UserType userType) {
		super();
		this.emailId = emailId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.userType = userType;
	}
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getGender() {
		return gender
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "ApplicantRequest [requestId=" + requestId + ", emailId=" + emailId + ", name=" + name + ", phoneNumber="
				+ phoneNumber + ", gender=" + gender + ", userType=" + userType + "]";
	}
}
