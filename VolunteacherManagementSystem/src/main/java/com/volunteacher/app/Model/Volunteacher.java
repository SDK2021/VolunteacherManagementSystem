package com.volunteachers.database.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
public class Volunteacher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length=8)
	private int volunteacherId;

	//school

	@NotNull
	@Column(length = 20, nullable = false)
	private String employerName;
	
	@NotNull
	@Column(length = 1, nullable = false, columnDefinition = "TINYINT")
	private int status;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date joiningDate;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date endingDate;

	@NotNull
	@Column(length = 6, nullable = false)
	private int pincode;

	@NotNull
	@Column(length = 20, nullable = false)
	private String education;
	
	//One User-- for password
	@OneToOne
	private User user;
	
	@ManyToOne
	private Village village;
	
	@ManyToOne
	private District district;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Session> sessions;
	
	//Does cascading required?
	@ManyToMany
	private List<Project> projects;
	
}
