package com.volunteachers.database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

@Entity
public class SessionReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length=8)
	private int sessionReportId;
	
	@NotNull
	@Column(nullable = false , columnDefinition = "TEXT")
	private String description;
	
	@NotNull
	@Column(nullable = false , columnDefinition = "TEXT")
	private String suggestions;
	
	@NotNull
	@Column(length = 20, nullable = false)
	private String experience;
	
	@NotNull
	@Column(nullable = false , columnDefinition = "TEXT")
	private String requirements;
	
	//One report will have One session
	@ManyToOne
	private Session session;
		
	//Many reports belongs to one user
	@ManyToOne
	private User user;
}
