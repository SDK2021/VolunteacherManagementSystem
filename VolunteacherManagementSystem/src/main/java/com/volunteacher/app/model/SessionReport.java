package com.volunteacher.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

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
	
	@NotNull
	@ManyToOne
	private Session session;
		
	@NotNull
	@ManyToOne
	private User user;
}