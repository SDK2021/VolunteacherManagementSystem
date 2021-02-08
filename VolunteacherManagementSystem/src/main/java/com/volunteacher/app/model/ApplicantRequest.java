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
	@Column(length = 40 , nullable = false)
	String emailId;
	
	@NotNull
	@Size(min = 3 , max = 20)
	@Column(nullable = false , columnDefinition = "Char" , length = 20)
	String name;
	
	@NotNull
	@Column(nullable = false , length = 10 , unique = true)
	String phoneNumber;
	
	@NotNull
	@Column(nullable = false , columnDefinition = "TinyInt" , length = 1)
	int gender;
	
	@NotNull
	@OneToOne
	UserType userType;
	
}
