package com.volunteacher.app.model;

import java.util.Date;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	private Date dob;
	
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
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "craetedBy")
	private List<TimelinePost> posts;
	
}