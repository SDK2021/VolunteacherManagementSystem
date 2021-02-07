package com.volunteacher.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;



@Entity
public class Participant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length=8)
	private int participantId;
	
	@NotNull
	@Column(length=20, nullable = false , columnDefinition = "Char")
	private String name;
	
	@NotNull
	@Column(length=50, unique=true, nullable = false)
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
	
	@OneToOne
	private UserType type;
	
	@ManyToOne
	private Event event;
	
	@ManyToMany
	private List<Activity> activities;
	
	//participant can be user, if we delete user, though user is not there, but we can see who participated
	@OneToOne
	private User user;
	
}