package com.volunteacher.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Session {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length=8)
	private int sessionId;
	
	//attendance	
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date sessionDate;
	
	@NotNull
	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date startingTime;
	
	@NotNull
	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date endingTime;
	
	@NotNull
	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date creationDate;
	
	//Many session belongs to one project
	@NotNull
	@ManyToOne
	private Project project;
			
	//One session many reports - cascade deletion
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "session")
	private List<SessionReport> sessionReports;
	
	@NotNull
	@ManyToOne
	private Village village;
	
	@ManyToMany(mappedBy = "sessions")
	private List<Volunteacher> volunteachers;
	
	@ManyToMany
	private List<Kid> kids;
	
}